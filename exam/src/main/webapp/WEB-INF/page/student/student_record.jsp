<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${basePath}/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="${basePath}/css/public.css" media="all">
</head>

<body>
    <div class="layuimini-container">
        <div class="layuimini-main">

            <input type="hidden" id="examAduit" name="examAduit" value="${examAduit}">
            <fieldset class="table-search-fieldset">
                <legend>您的考试</legend>
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">考试名称</label>
                            <div class="layui-input-inline">
                                <input type="text" name="examName" id="examName" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <button type="submit" class="layui-btn layui-btn-primary" lay-submit
                                lay-filter="data-search-btn"><i class="layui-icon"></i> 搜 索</button>
                        </div>
                    </div>
                </form>
            </fieldset>

            <!-- <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"> 添加 </button>
                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"> 删除 </button>
            </div>
        </script> -->

            <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

            <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
        </script>

        </div>
    </div>
    <script src="${basePath}/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
    <script>
        layui.use(['form', 'table'], function () {
            var $ = layui.jquery,
                form = layui.form,
                table = layui.table
                , element = layui.element;
            table.render({
                elem: '#currentTableId',
                url: '${basePath}/student/exam/record/list',
                toolbar: '#toolbarDemo',
                defaultToolbar: ['filter', 'exports', 'print', {
                    title: '提示',
                    layEvent: 'LAYTABLE_TIPS',
                    icon: 'layui-icon-tips'
                }],
                cols: [[
                    { type: "checkbox", width: 50 },
                    { field: 'id', width: 250, title: '序号', sort: true, type: 'numbers' },
                    { field: 'id', width: 120, title: 'ID', sort: true, hide: true },
                    { field: 'point', width: 250, title: '分数', sort: true },
                    { field: 'examTitle', width: 300, title: '试卷名' },
                    { field: 'examId', width: 120, title: 'examId', sort: true, hide: true },
                    {
                        field: 'status', width: 300, title: '状态', sort: true, templet: function (a) {
                            if (a.status == 1) return '未完成'; else if (a.status == 2) return '已完成'; else if (a.status == 3) return '逾期未考';
                        }
                    },
                    { title: '备注', width: 0, align: "center" }
                ]],
                limits: [10, 15, 20, 25, 50, 100],
                limit: 15,
                page: true,
                skin: 'line'
            });

            // 监听搜索操作
            form.on('submit(data-search-btn)', function (data) {
                var result = JSON.stringify(data.field);
                console.log(result);
                layer.alert(result, {
                    title: '最终的搜索信息'
                });

                //执行搜索重载
                table.reload('currentTableId', {
                    page: {
                        curr: 1
                    }
                    , where: {
                        searchParams: result
                    }
                }, 'data');

                return false;
            });

            /**
             * toolbar监听事件
             */
            table.on('toolbar(currentTableFilter)', function (obj) {
                if (obj.event === 'add') {  // 监听添加操作
                    var index = layer.open({
                        title: '添加用户',
                        type: 2,
                        shade: 0.2,
                        maxmin: true,
                        shadeClose: true,
                        area: ['100%', '100%'],
                        content: '../page/table/add.html',
                    });
                    $(window).on("resize", function () {
                        layer.full(index);
                    });
                } else if (obj.event === 'delete') {  // 监听删除操作
                    var checkStatus = table.checkStatus('currentTableId')
                        , data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                }
            });

            // 监听搜索操作
            form.on('submit(data-search-btn)', function (data) {
                console.log(data);
                var examName = data.field.examName;
                table.reload('currentTableId', {
                    page: {
                        curr: 1
                    }
                    , where: {
                        "examName": examName,
                    }
                }, 'data');

                return false;
            });

            //监听表格复选框选择
            table.on('checkbox(currentTableFilter)', function (obj) {
                console.log(obj)
            });

            table.on('tool(currentTableFilter)', function (obj) {
                var data = obj.data;
                console.log(obj.event);
                if (obj.event === 'edit') {

                    var index = layer.open({
                        title: '编辑用户',
                        type: 2,
                        shade: 0.2,
                        maxmin: true,
                        shadeClose: true,
                        area: ['100%', '100%'],
                        content: '../page/table/edit.html',
                    });
                    $(window).on("resize", function () {
                        layer.full(index);
                    });
                    return false;
                } else if (obj.event === 'delete') {
                    layer.confirm('真的删除行么', function (index) {
                        obj.del();
                        layer.close(index);
                    });
                }
            });

            table.on('row(currentTableFilter)', function (obj) {
                console.log(obj.tr);
                console.log(obj.data);
                var examId = obj.data.id;
                console.log(examId);
                window.location.href = "${basePath}/student/toStudentRecord/list?exam_result_id=" + obj.data.id + "&examId=" + obj.data.examId;
            })
        });
    </script>

</body>

</html>