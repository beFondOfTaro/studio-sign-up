<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <!-- user-scalable=no 可以禁用其缩放（zooming）功能 -->
<#--maximum-scale=1-->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>用户列表</title>
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
        <a href="/usertable" class="list-group-item sidebar-li active">用户列表</a>
        <a href="/sighupinfotable" class="list-group-item sidebar-li">报名信息列表</a>
        <a href="projecttable" class="list-group-item sidebar-li">项目列表</a>
    </div>
</div>
<!-- 数据列表面板 -->
<div class="container-fluid">
    <div id="dataPanel" class="panel panel-default">
        <div class="panel-heading">用户列表
            <button id="insertButton" type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#insertModal">新增用户</button>
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
                <h4 class="modal-title">修改用户信息</h4>
            </div>
            <form id="updateUser">
                <div class="modal-body">

                    <div class="form-group">
                        <label for="inputId">id</label>
                        <input name="id" type="text" class="form-control" id="inputId" data-source="id" placeholder="id" readonly>
                    </div>
                    <div class="form-group">
                        <label for="inputRoleName">角色</label>
                        <input name="roleName" type="text" class="form-control" id="inputRoleName" data-source="role.name" placeholder="角色" readonly>
                    </div>
                    <div class="form-group">
                        <label for="inputUsername">用户名</label>
                        <input name="username" type="text" class="form-control" id="inputUsername" data-source="username" placeholder="用户名">
                    </div>
                    <div class="form-group">
                        <label for="inputPassword">密码</label>
                        <input name="password" type="text" class="form-control" id="inputPassword" data-source="password" placeholder="密码">
                    </div>
                    <div class="form-group">
                        <label for="inputRealName">真实姓名</label>
                        <input name="realName" type="text" class="form-control" id="inputRealName" data-source="realName" placeholder="真实姓名">
                    </div>
                    <div class="form-group">
                        <label for="inputPhone">电话</label>
                        <input name="phone" type="text" class="form-control" id="inputPhone" data-source="phone" placeholder="电话">
                    </div>
                    <div class="form-group">
                        <label for="inputCreatedTime">创建时间</label>
                        <input name="createdTime" type="datetime-local" class="form-control" id="inputCreatedTime" data-source="createdTime" placeholder="创建时间"
                               readonly>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="updateUser();">保存更改</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 新增用户信息的模态框 -->
<div id="insertModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">新增用户信息</h4>
            </div>
            <form id="insertUser">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="roleList">角色</label>
                        <select name="roleName" id="roleList" class="form-control">
                        </select>
                    </div>
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
                        <label for="inputPhone">电话</label>
                        <input name="phone" type="text" class="form-control" placeholder="电话">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="insertUser();">确定</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 删除用户信息的模态框 -->
<div id="deleteModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">删除用户信息</h4>
            </div>
            <div class="modal-body">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="deleteUser(getCurrentEntity().id);">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- 弹出警告框 -->
<div class="alert alert-warning alert-dismissible" role="alert">
    <button type="button" class="close" onclick="hideAlert();">
        <span aria-hidden="true">&times;</span>
    </button>
    <strong></strong>
</div>

<link rel="stylesheet" href="/static/css/bootstrap.min.css">
<link rel="stylesheet" href="/static/css/styles.css">
<script src="/static/js/jquery-1.11.0.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/hyz.js"></script>
<script src="/static/js/studioSighUp.js"></script>
<script>
    var tableConfig = {
        tableId: '#myTable',//绑定的表格元素Id
        cols: [
            {field: 'id', title: 'ID'},
            {field: "role.name",title: '角色'},
            {field: 'username', title: '用户名'},
            {field: 'password', title: '密码'},
            {field: 'realName', title: '真实姓名'},
            {field: 'phone', title: '电话'},
            {field: 'createdTime', title: '创建时间'}
        ],//列的绑定,field为服务端数据包中对应的
        url: '/admin/1/user'//请求的地址
    };
    tableConfig = initMyTable(tableConfig);//务必将返回值给到参数
    fillModalForm('#updateModal','#updateUser');//在点击修改按钮后，填充修改信息的模态框的表单
    fillDeleteUserModal('#deleteModal');
    fillRoleSelect();
</script>

</body>
</html>