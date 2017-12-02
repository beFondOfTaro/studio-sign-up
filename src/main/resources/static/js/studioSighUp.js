hideAlert();
//------------------以下为非通用js------------------
//userTable页面开始
/**
 * 生成删除学生信息的模态框中的提示信息
 */
function fillDeleteUserModal(modalId) {
    var deleteModal = $(modalId);
    setRowIndex(deleteModal);
    deleteModal.on('shown.bs.modal', function (e) {
        var modalBody = deleteModal.find('.modal-body');
        modalBody[0].innerHTML = '删除用户将会删除其所有报名信息！<br>确定删除用户名为' + getValueByName(getCurrentEntity(), 'username') + '的用户吗？';
    });
}

function fillRoleSelect() {
    var roleSelect = $('#roleList');
    $.ajax({
        type: 'get',
        url: '/admin/1/role/',
        success: function (data) {
            var roleSelectHTML = '';
            var roleList = data.data.content;
            for (var role of roleList) {
                roleSelectHTML = roleSelectHTML + '<option>' + role.name + '</option>';
                roleSelect.html(roleSelectHTML);
            }
        }
    });
}

function updateUser() {
    $.ajax({
        type: 'put',
        url: '/admin/1/user',
        data: $('#updateUser').serialize(),
        success: function (data) {
            showAlert(data.msg);
            hideModalAndReloadData('#updateModal');
        }
    });
}

function insertUser() {
    var insertUserForm = $('#insertUser');
    $.ajax({
        type: 'post',
        url: '/admin/1/user',
        data: insertUserForm.serialize(),
        success: function (data) {
            showAlert(data.msg);
            hideModalAndReloadData('#insertModal');
        }
    });
}

function deleteUser(UserId) {
    $.ajax({
        type: 'delete',
        url: '/admin/1/user/' + UserId,
        data: $('#insertUser').serialize(),
        success: function (data) {
            showAlert(data.msg);
            hideModalAndReloadData('#deleteModal');
        }
    });
}


//userTable页面结束

//projectTable页面开始
/**
 * 生成删除学生信息的模态框中的提示信息
 */
function fillDeleteProjectModal(modalId) {
    var deleteModal = $(modalId);
    setRowIndex(deleteModal);
    deleteModal.on('shown.bs.modal', function (e) {
        var modalBody = deleteModal.find('.modal-body');
        modalBody[0].innerHTML = '删除项目将会删除其下所有报名信息！<br>确定删除项目名为' + getValueByName(getCurrentEntity(), 'name') + '的项目吗？';
    });
}

function updateProject() {
    $.ajax({
        type: 'put',
        url: '/admin/1/project',
        data: $('#updateProject').serialize(),
        success: function (data) {
            showAlert(data.msg);
            hideModalAndReloadData('#updateModal');
        }
    });
}

function insertProject() {
    $.ajax({
        type: 'post',
        url: '/admin/1/project',
        data: $('#insertProject').serialize(),
        success: function (data) {
            showAlert(data.msg);
            hideModalAndReloadData('#insertModal');
        }
    });
}

function deleteProject(projectId) {
    $.ajax({
        type: 'delete',
        url: '/admin/1/project/' + projectId,
        data: $('#insertProject').serialize(),
        success: function (data) {
            showAlert(data.msg);
            hideModalAndReloadData('#deleteModal');
        }
    });
}

//projectTable页面开始

//sighUpInfoTable页面开始
/**
 * 生成删除项目信息的模态框中的提示信息
 */
function fillDeleteSighUpInfoModal(modalId) {
    var deleteModal = $(modalId);
    setRowIndex(deleteModal);
    deleteModal.on('shown.bs.modal', function (e) {
        var modalBody = deleteModal.find('.modal-body');
        modalBody[0].innerHTML = '确定删除学生 ' + getValueByName(getCurrentEntity(), 'user.realName') + ' 的报名信息吗？';
    });
}

function fillProjectSelect(selectIdList) {
    fillSelect({
        selectIdList: selectIdList,
        type: 'get',
        url: '/admin/1/project'
    });
}

function fillSelect(params) {
    $.ajax({
        type: params.type,
        url: params.url,
        success: function (data) {
            for (var selectId of params.selectIdList) {
                var select = $(selectId);
                var selectHTML = '';
                for (var entity of data.data.content) {
                    selectHTML = selectHTML + '<option>' + entity.name + '</option>';
                    select.html(selectHTML);
                }
            }
        }
    });
}

function updateSighUpInfo() {
    $.ajax({
        type: 'put',
        url: '/admin/1/sighupinfo',
        data: $('#updateSighUpInfo').serialize(),
        success: function (data) {
            showAlert(data.msg);
            hideModalAndReloadData('#updateModal');
        }
    });
}

function insertSighUpInfo() {
    $.ajax({
        type: 'post',
        url: '/admin/1/sighupinfo',
        data: $('#insertSighUpInfo').serialize(),
        success: function (data) {
            showAlert(data.msg);
            hideModalAndReloadData('#insertModal');
        }
    });
}

function deleteSighUpInfo(sighUpInfoId) {
    $.ajax({
        type: 'delete',
        url: '/admin/1/sighupinfo/' + sighUpInfoId,
        data: $('#insertSighUpInfo').serialize(),
        success: function (data) {
            showAlert(data.msg);
            hideModalAndReloadData('#deleteModal');
        }
    });
}

//sighUpInfoTable页面结束