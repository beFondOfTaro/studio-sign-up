/**
 * 分页表格生成
 * @param tableConfig 表格的配置
 */
function initMyTable(tableConfig) {
    var defaultTableConfig = {
        //tableId: '#myTable',//绑定的表格元素Id
        // cols: [[
        //     {field: 'id', title: 'ID'},
        //     {field: 'username', title: '用户名'},
        //     {field: 'password', title: '密码'},
        //     {field: 'realName', title: '真实姓名'},
        //     {field: 'teacherNumber', title: '工号'},
        //     {field: 'phone', title: '电话'},
        //     {field: 'createdTime', title: '创建时间'}
        // ]],//列的绑定,field为服务端数据包中对应的
        actionCol: true,//是否生成数据操作操作列
        pageNav: {
            buttonNum: 6,//每页最多显示的按钮数量
            navAriaLabel: 'PageNavigation',//翻页栏的aria-label属性，用于定位nav标签
            beforeElem: tableConfig.tableId//要在其之后生成翻页组件的元素id，默认为在表格之后生成
        },
        //url: '/admin/1/teacher',//请求的地址
        method: 'get',//请求方法
        request: {
            page: 1,//分页的初始页码
            size: 10 //一页的数量
        },//请求的参数
        response: {
            statusName: 'status',//数据状态的字段名称
            statusCode: '200',//成功的状态码
            msgName: 'msg',//状态信息的字段名称
            countName: 'totalElements',//数据总数的字段名称
            totalPages: 'totalPages',//总页数
            dataName: 'content'//数据列表的字段名称
        }
    };
    tableConfig= $.extend(defaultTableConfig,tableConfig);
    //初始化表头
    initThead(tableConfig);
    //生成表身体的内容
    rendPageData(tableConfig);
    return tableConfig;
}

function initThead(tableConfig) {
    var tableElem = $(tableConfig.tableId);
    //初始化表头
    tableElem.html('<thead></thead><tbody></tbody>');
    for (var cols of tableConfig.cols){
        //生成表头的行
        var theadElem = $(tableConfig.tableId+' thead');
        var theadHtml = theadElem.html() + "<tr></tr>";
        theadElem.html(theadHtml);
        //生成表头行内容
        var thead_trElem = $(tableConfig.tableId+' thead tr');
        var thead_trHtml = thead_trElem.html() + '<th>#</th>';
        for (var col of cols){
            thead_trHtml = thead_trHtml + '<th>'+ col.title + '</th>';
            thead_trElem.html(thead_trHtml);
        }
        //生成操作按钮列的表头
        if (tableConfig.actionCol){
            thead_trElem.html(thead_trElem.html()+'<th>操作</th>');
        }
    }
}

function rendPageData(tableConfig) {
    for (var cols of tableConfig.cols){
        $.ajax({
            type: tableConfig.method,
            url: tableConfig.url,
            data: tableConfig.request,
            dataType: "json",
            success: function (data) {
                //生成翻页栏
                rendPagination(tableConfig,data);
                //生成表身体的内容
                rendTbody(tableConfig,cols,data);
            }
        });
    }
}

//生成表身体的内容
function rendTbody(tableConfig,cols,data) {
    var rendActionButton = function (Elem) {
        Elem.innerHTML = Elem.innerHTML + '<td><button type="button" class="btn btn-success" data-toggle="modal" data-target="#updateModal">修改</button>'+
            '<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteModal">删除</button></td>';
    };
    var count = 1;
    for (var col of cols){
        var tbodyHtml = $(tableConfig.tableId+' tbody').html();
        var tbody_trElem;
        var tbody_trHtml;
        for (var i = 0;i<data.data[tableConfig.response.dataName].length;i++) {
            //只有在第一次生成行的时候才写第一列的内容
            if (count === 1) {
                //生成表身体内容
                tbodyHtml = $(tableConfig.tableId + ' tbody').html() + '<tr></tr>';
                $(tableConfig.tableId + ' tbody').html(tbodyHtml);
                tbody_trElem = $(tableConfig.tableId + ' tbody tr')[i];//第i行的tr元素
                //第一列的内容，即序号
                tbody_trHtml = '<th>' + ((tableConfig.request.page-1)*tableConfig.request.size+i+1) + '</th>';
                tbody_trElem.innerHTML = tbody_trHtml;
            }
            //生成当前列的所有数据
            tbody_trElem = $(tableConfig.tableId + ' tbody tr')[i];//第i行的tr元素
            tbody_trHtml = tbody_trElem.innerHTML;
            tbody_trHtml = tbody_trHtml + '<td>' + data.data[tableConfig.response.dataName][i][col.field] + '</td>';
            tbody_trElem.innerHTML = tbody_trHtml;
            //生成操作按钮
            if (tableConfig.actionCol && count === cols.length){
                rendActionButton($(tableConfig.tableId + ' tbody tr')[i]);
            }
        }
        count++;
    }
}

/**生成翻页栏
 *
 * @param tableConfig 表格配置的json
 * @param data  服务端返回的数据
 */
function rendPagination(tableConfig,data) {
    //在beforeElem元素之后初始化翻页组件
    var navAriaLabel = tableConfig.pageNav.navAriaLabel;
    var beforeElem = $(tableConfig.pageNav.beforeElem);
    beforeElem.nextAll("[aria-label='"+navAriaLabel+"']").remove();
    var paginationHtml = '<nav aria-label='+ navAriaLabel +'>' +
        '<ul class="pagination">' +
        '</ul>' +
        '</nav>';
    beforeElem.after(paginationHtml);
    //生成翻页组件按钮
    var pageNum = tableConfig.pageNav.buttonNum;//显示翻页按钮数量
    var totalCounts = data.data[tableConfig.response.countName];
    var totalPages = data.data[tableConfig.response.totalPages];//总的页数
    var nav_ulElem = $(tableConfig.pageNav.beforeElem).nextAll("[aria-label='"+navAriaLabel+"']").find('ul');//列表元素
    var nav_ulHtml = nav_ulElem.html();//列表内容
    //过得已点击状态的按钮
    var getActiveButton = function (pageNum) {
        return '<li class="active"><span>'+ pageNum +'<span class="sr-only">(current)</span></span></li>';
    };
    //获得对应页码的按钮
    var getTurnPageButton = function (pageNum) {
        return '<li><a href="javascript:void(0)" onclick="turnPage(tableConfig,this)">'+ pageNum +'</a></li>';
    };
    //过得向前翻页的按钮
    var getPreviousPageButton = function (isEnable) {
        if (isEnable){
            return '<li><a href="javascript:void(0)" onclick="turnPage(tableConfig,this)" aria-label="Previous">' +
                '<span aria-hidden="true">&laquo;</span>' +
                '</a></li>';
        }
        else {
            return '<li class="disabled">' +
                '<span aria-label="Previous"><span aria-hidden="true">&laquo;</span></span>' +
                '</li>';
        }
    };
    //获得向后翻页的按钮
    var getNextPageButton = function (isEnable) {
        if (isEnable){
            return '<li>' +
                '<a href="javascript:void(0)" onclick="turnPage(tableConfig,this)" aria-label="Next">' +
                '<span aria-hidden="true">&raquo;</span>' +
                '</a>' +
                '</li>';
        }
        else {
            return '<li class="disabled">' +
                '<span><span aria-hidden="true">&raquo;</span></span>' +
                '</li>';
        }
    }
    //显示页码的第一个按钮
    var firstPage = Math.floor((tableConfig.request.page-1) /pageNum)*pageNum + 1;
    //如果要显示的页码按钮未超过设定的数量，则显示全部按钮
    if ((totalPages-firstPage) < pageNum){
        for (var i = firstPage; i <= totalPages;i++){
            //第i页的按钮
            //如果为当前页，样式为active
            if (tableConfig.request.page === i){
                nav_ulHtml = nav_ulHtml + getActiveButton(i);
            }
            else {
                nav_ulHtml = nav_ulHtml + getTurnPageButton(i);
            }
            //如果为第一个页码的按钮，前面添加上一页的按钮
            if (i === firstPage){
                //如果当前请求的页码为1，上一页不可点击
                if (tableConfig.request.page === 1){
                    nav_ulHtml = getPreviousPageButton(false) + nav_ulHtml;
                }
                //否则，上一页可以点击
                else {
                    nav_ulHtml = getPreviousPageButton(true) + nav_ulHtml;
                }
            }
            //如果为最后一个页码的按钮，后面添加下一页的按钮
            if (i === totalPages){
                //如果总页数等于当前页，下一页不可点击
                if (totalPages === tableConfig.request.page){
                    nav_ulHtml = nav_ulHtml + getNextPageButton(false);
                }
                //否则，下一页可点击
                else {
                    nav_ulHtml = nav_ulHtml + getNextPageButton(true);
                }

            }
        }
    }
    //如果总页数超过设定的数量，隐藏部分按钮
    else {
        for (var i = 0; i < pageNum;i++){
            //当前页
            var currentPage = firstPage + i;
            //第i页按钮
            if (tableConfig.request.page === currentPage){
                nav_ulHtml = nav_ulHtml + getActiveButton(currentPage);
            }
            else {
                nav_ulHtml = nav_ulHtml + getTurnPageButton(currentPage);
            }
            //如果为第一个页码的按钮，前面添加上一页的按钮
            if (currentPage === firstPage){
                //如果当前请求的页码为1，上一页不可点击
                if (tableConfig.request.page === 1){
                    nav_ulHtml = getPreviousPageButton(false) + nav_ulHtml;
                }
                //否则，上一页可以点击
                else {
                    nav_ulHtml = getPreviousPageButton(true) + nav_ulHtml;
                }
            }
            //如果为最后一个按钮，则在最后加上下一页的按钮
            if (i === pageNum-1){
                //如果总页数等于当前页，下一页不可点击
                if (totalPages === tableConfig.request.page){
                    nav_ulHtml = nav_ulHtml + getNextPageButton(false);
                }
                //否则，下一页可点击
                else {
                    nav_ulHtml = nav_ulHtml + getNextPageButton(true);
                }
            }
        }
    }
    nav_ulElem.html(nav_ulHtml);//渲染
}

function turnPage(tableConfig,currentElem) {
    var ariaLabel = currentElem.getAttribute('aria-label');
    //如果为上一页的按钮
    if (ariaLabel === 'Previous'){
        tableConfig.request.page--;
        initMyTable(tableConfig);
    }
    //如果为下一页的按钮
    else if (ariaLabel === 'Next'){
        tableConfig.request.page++;
        initMyTable(tableConfig);
    }
    //带页码的按钮
    else {
        tableConfig.request.page = parseInt(currentElem.text);//当前页码
        initMyTable(tableConfig);
    }
}

//------------------以下为非通用js------------------
//studentTable页面开始
/**
 * 模态框显示出来后，自动填写学生信息表单
 */
function fillStudentForm() {
    var updateModal = $('#updateModal');
    var tdList;
    updateModal.on('show.bs.modal', function (e) {
        tdList = $(e.relatedTarget.parentElement.parentElement).find('td');
    });

    updateModal.on('shown.bs.modal', function (e) {
        $('#inputId').val(tdList[0].innerText);
        $('#inputUsername').val(tdList[1].innerText);
        $('#inputPassword').val(tdList[2].innerText);
        $('#inputRealName').val(tdList[3].innerText);
        $('#inputStudentNumber').val(tdList[4].innerText);
        $('#inputMajor').val(tdList[5].innerText);
        $('#inputPhone').val(tdList[6].innerText);
        $('#inputQqNumber').val(tdList[7].innerText);
        $('#inputCreatedTime').val(tdList[8].innerText.replace(' ','T'));
    });
}

/**
 * 生成删除学生信息的模态框中的提示信息
 */
function fillDeleteStudentModal(deletingStudentId) {
    var deleteModal = $('#deleteModal');
    var tdList;
    deleteModal.on('show.bs.modal', function (e) {
        tdList = $(e.relatedTarget.parentElement.parentElement).find('td');
    });
    deleteModal.on('shown.bs.modal', function (e) {
        deletingStudentId.value = tdList[0].innerHTML;
        $('#deleteModal .modal-body')[0].innerHTML = '确定删除用户名为' + tdList[1].innerHTML + '的用户吗？';
    });
}

function updateStudent() {
    $.ajax({
            type: 'put',
            url: '/admin/1/student',
            data:$('#updateStudent').serialize(),
            success:function (data) {
                alert(data.msg);
            }
    });
}

function insertStudent() {
    $.ajax({
        type: 'post',
        url: '/admin/1/student',
        data: $('#insertStudent').serialize(),
        success:function (data) {
            alert(data.msg);
        }
    });
}

function deleteStudent(studentId) {
    $.ajax({
        type: 'delete',
        url: '/admin/1/student/'+ studentId,
        data:$('#insertStudent').serialize(),
        success:function (data) {
            alert(data.msg);
        }
    });
}
//studentTable页面结束

//teacherTable页面开始
function fillTeacherForm() {
    var updateModal = $('#updateModal');
    var tdList;
    updateModal.on('show.bs.modal', function (e) {
        tdList = $(e.relatedTarget.parentElement.parentElement).find('td');
    });

    updateModal.on('shown.bs.modal', function (e) {
        $('#inputId').val(tdList[0].innerText);
        $('#inputUsername').val(tdList[1].innerText);
        $('#inputPassword').val(tdList[2].innerText);
        $('#inputRealName').val(tdList[3].innerText);
        $('#inputTeacherNumber').val(tdList[4].innerText);
        $('#inputPhone').val(tdList[5].innerText);
        $('#inputCreatedTime').val(tdList[6].innerText.replace(' ','T'));
    });
}

/**
 * 生成删除学生信息的模态框中的提示信息
 */
function fillDeleteTeacherModal(deletingTeacherId) {
    var deleteModal = $('#deleteModal');
    var tdList;
    deleteModal.on('show.bs.modal', function (e) {
        tdList = $(e.relatedTarget.parentElement.parentElement).find('td');
    });
    deleteModal.on('shown.bs.modal', function (e) {
        deletingTeacherId.value = tdList[0].innerHTML;
        $('#deleteModal .modal-body')[0].innerHTML = '确定删除用户名为' + tdList[1].innerHTML + '的教师吗？';
    });
}

function updateTeacher() {
    $.ajax({
        type: 'put',
        url: '/admin/1/teacher',
        data:$('#updateTeacher').serialize(),
        success:function (data) {
            alert(data.msg);
        }
    });
}

function insertTeacher() {
    $.ajax({
        type: 'post',
        url: '/admin/1/teacher',
        data: $('#insertTeacher').serialize(),
        success:function (data) {
            alert(data.msg);
        }
    });
}

function deleteTeacher(teacherId) {
    $.ajax({
        type: 'delete',
        url: '/admin/1/teacher/'+ teacherId,
        data:$('#insertTeacher').serialize(),
        success:function (data) {
            alert(data.msg);
        }
    });
}