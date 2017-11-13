<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <!-- user-scalable=no 可以禁用其缩放（zooming）功能 -->
<#--maximum-scale=1-->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" href="/static/css/styles.css">
    <title>学生列表</title>
    <style type="text/css">
        body {
            background: #ffffff;
        @font-family-base;
        @font-size-base;
        @line-height-base;
        @link-color;
            padding-top: 40px;
            padding-left: 150px;
        }

        #topNav{
            margin-right: 40px;
        }

        #dataPanel {
            margin: 100px;
        }

        .sidebar {
            left: 0;
            top: 40px;
            border-width: 0 0 1px;
            position: fixed;
            z-index: 1030;
            height: 100%;
            background: black;
            border-radius: 4px;
        }

        @media (min-width: 768px)
            .sidebar {
                border-radius: 0;
            }

        .sidebar-li {
            height: 42px;
            width: 150px;
            overflow: hidden;
        }

        .list-group{
            opacity: 0.8;
        }
    </style>
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
        <div class="panel-heading">学生列表</div>
        <div class="panel-body">
            <div class="table-responsive">
                <table id="myTable" class="table table-hover table-striped">
                </table>
            </div>
        </div>
    </div>
</div>


<link rel="stylesheet" href="/static/css/bootstrap.css">
<script src="/static/js/jquery-1.11.0.min.js"></script>
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
        actionCol: true,//是否生成数据操作操作列
        pageNav: {
            buttonNum: 6,//每页显示的按钮数量
            navAriaLabel: 'PageNavigation',//翻页栏的aria-label属性，用于定位nav标签
            beforeElem: '#myTable'//要在其之后生成翻页组件的元素id
        },
        url: '/admin/1/student',//请求的地址
        method: 'get',//请求方法
        request: {
            page: 1,//分页的页码
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
    initMyTable(tableConfig);
</script>

</body>
</html>