<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <!-- user-scalable=no 可以禁用其缩放（zooming）功能 -->
<#--maximum-scale=1-->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>学生列表</title>
</head>
<body>
<!-- 背景图 -->
<div class="wrapper">
    <ul class="bg-bubbles">
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
    </ul>
</div>

<!-- 上部导航条 -->
<nav class="navbar-default navbar-fixed-top">
    <ul id="topNav" class="nav nav-pills navbar-right">
        <li role="presentation" class="active"><a href="#">Home</a></li>
        <li role="presentation"><a href="#">Profile</a></li>
        <li role="presentation"><a href="#">Messages</a></li>
    </ul>
</nav>
<!-- 左侧导航条 -->
<div class="sidebar">
    <div class="list-group">
        <a href="/studenttable" class="list-group-item sidebar-li active">学生列表</a>
        <a href="/teachertable" class="list-group-item sidebar-li">教师列表</a>
        <a href="/sighupinfotable" class="list-group-item sidebar-li">报名信息列表</a>
        <a href="projecttable" class="list-group-item sidebar-li">项目列表</a>
    </div>
</div>
<!-- 数据列表面板 -->
<div class="container-fluid">
    <div id="dataPanel" class="panel panel-default">
        <div class="panel-heading">学生列表
            <button id="insertButton" type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#insertModal">新增学生</button>
        </div>
        <div class="panel-body">
            <div class="table-responsive">
                <table id="myTable" class="table table-hover table-striped">
                </table>
            </div>
        </div>
    </div>
</div>
<!-- 修改信息的模态框 -->
<div id="updateModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">修改学生信息</h4>
            </div>
            <form id="updateStudent">
                <div class="modal-body">

                    <div class="form-group">
                        <label for="inputId">id</label>
                        <input name="id" type="text" class="form-control" id="inputId" placeholder="id" readonly>
                    </div>
                    <div class="form-group">
                        <label for="inputUsername">用户名</label>
                        <input name="username" type="text" class="form-control" id="inputUsername" placeholder="用户名">
                    </div>
                    <div class="form-group">
                        <label for="inputPassword">密码</label>
                        <input name="password" type="text" class="form-control" id="inputPassword" placeholder="密码">
                    </div>
                    <div class="form-group">
                        <label for="inputRealName">真实姓名</label>
                        <input name="realName" type="text" class="form-control" id="inputRealName" placeholder="真实姓名">
                    </div>
                    <div class="form-group">
                        <label for="inputStudentNumber">学号</label>
                        <input name="studentNumber" type="text" class="form-control" id="inputStudentNumber" placeholder="学号">
                    </div>
                    <div class="form-group">
                        <label for="inputMajor">专业</label>
                        <input name="major" type="text" class="form-control" id="inputMajor" placeholder="专业">
                    </div>
                    <div class="form-group">
                        <label for="inputPhone">电话</label>
                        <input name="phone" type="text" class="form-control" id="inputPhone" placeholder="电话">
                    </div>
                    <div class="form-group">
                        <label for="inputQqNumber">qq号</label>
                        <input name="qqNumber" type="text" class="form-control" id="inputQqNumber" placeholder="qq号">
                    </div>
                    <div class="form-group">
                        <label for="inputCreatedTime">创建时间</label>
                        <input name="createdTime" type="datetime-local" class="form-control" id="inputCreatedTime" placeholder="创建时间"
                               readonly>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="updateStudent();">保存更改</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 新增学生信息的模态框 -->
<div id="insertModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">新增学生信息</h4>
            </div>
            <form id="insertStudent">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="inputUsername">用户名</label>
                        <input name="username" type="text" class="form-control" placeholder="用户名">
                    </div>
                    <div class="form-group">
                        <label for="inputPassword">密码</label>
                        <input name="password" type="text" class="form-control" placeholder="密码">
                    </div>
                    <div class="form-group">
                        <label for="inputRealName">真实姓名</label>
                        <input name="realName" type="text" class="form-control" placeholder="真实姓名">
                    </div>
                    <div class="form-group">
                        <label for="inputStudentNumber">学号</label>
                        <input name="studentNumber" type="text" class="form-control" placeholder="学号">
                    </div>
                    <div class="form-group">
                        <label for="inputMajor">专业</label>
                        <input name="major" type="text" class="form-control" placeholder="专业">
                    </div>
                    <div class="form-group">
                        <label for="inputPhone">电话</label>
                        <input name="phone" type="text" class="form-control" placeholder="电话">
                    </div>
                    <div class="form-group">
                        <label for="inputQqNumber">qq号</label>
                        <input name="qqNumber" type="text" class="form-control" placeholder="qq号">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="insertStudent();">确定</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 删除学生信息的模态框 -->
<div id="deleteModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">删除学生信息</h4>
            </div>
                <div class="modal-body">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" onclick="deleteStudent(deletingStudentId.value);">确定</button>
                </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<link rel="stylesheet" href="/static/css/bootstrap.min.css">
<link rel="stylesheet" href="/static/css/styles.css">
<script src="/static/js/jquery-1.11.0.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/hyz.js"></script>
<script>
    var tableConfig = {
        tableId: '#myTable',//绑定的表格元素Id
        cols: [[
            {field: 'id', title: 'ID'},
            {field: 'username', title: '用户名'},
            {field: 'password', title: '密码'},
            {field: 'realName', title: '真实姓名'},
            {field: 'studentNumber', title: '学号'},
            {field: 'major', title: '专业'},
            {field: 'phone', title: '电话'},
            {field: 'qqNumber', title: 'qq号'},
            {field: 'createdTime', title: '创建时间'}
        ]],//列的绑定,field为服务端数据包中对应的
        url: '/admin/1/student'//请求的地址
    };
    tableConfig = initMyTable(tableConfig);//务必将返回值给到参数
    fillStudentForm();
    var deletingStudentId = {value: 0};
    fillDeleteStudentModal(deletingStudentId);
</script>

</body>
</html>