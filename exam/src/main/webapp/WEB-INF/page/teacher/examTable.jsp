<%--
  Created by IntelliJ IDEA.
  User: wangmaolin
  Date: 2020/11/18
  Time: 10:12 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>教师管理-试卷列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../../lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="../../css/public.css" media="all">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">考卷名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="title" id="title" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">状态</label>
                            <div class="layui-input-block">
                                <select name="status" id="status" lay-filter="aihao">
                                    <option value="-1">全部</option>
                                    <option value="1">已发布，尚未出题</option>
                                    <option value="2" selected="">待考试</option>
                                    <option value="3">已开始</option>
                                    <option value="4">已结束</option>
                                </select>
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">有效</label>
                            <div class="layui-input-block">
                                <select name="valid" id="valid" lay-filter="aihao">
                                    <option value=""></option>
                                    <option value="1" selected>正常</option>
                                    <option value="0">失效</option>
                                </select>
                            </div>
                        </div>


                        <div class="layui-inline">
                            <button type="submit" class="layui-btn layui-btn-primary" onclick="searchExam" lay-submit
                                    lay-filter="data-search-btn"><i class="layui-icon"></i> 搜 索
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>

        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"> 添加</button>
                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"> 删除</button>
            </div>
        </script>

        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="addQuestion">去出题</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="publish">发布</a>
        </script>


    </div>
</div>
<script src="../../lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script>

    layui.use(['form', 'table'], function () {
        let $ = layui.jquery,
            form = layui.form,
            table = layui.table;

        table.render({
            elem: '#currentTableId',
            url: '${basePath}/exam/list',
            toolbar: '#toolbarDemo',
            defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            cols: [[
                {type: "checkbox", width: 50},
                {field: 'id', width: 80, title: 'ID', sort: true},
                {field: 'title', width: 150, title: '考卷名'},
                {field: 'timeLimit', width: 80, title: '时长', sort: true},
                {field: 'startTime', width: 200, title: '开考时间', sort: true},
                {field: 'endTime', width: 200, title: '截止时间', sort: true},
                {field: 'status', title: '状态', minWidth: 150},
                {field: 'classNames', title: '参考班级', minWidth: 180},
                {field: 'singlePoints', width: 130, title: '单选分数', sort: true},
                {field: 'multiPoints', width: 130, title: '多选分数', sort: true},
                {field: 'judgePoints', width: 130, title: '判断分数', sort: true},
                {field: 'valid', width: 120, title: '有效状态'},
                {field: 'type', width: 180, title: '类型'},
                {field: 'classIdsList', width: 100, title: '参考班级id', hide: true},
                {title: '操作', minWidth: 300, toolbar: '#currentTableBar', align: "center"}
            ]],
            contentType: 'application/json',
            method: 'post',
            where: {},
            limits: [10, 15, 20, 25, 50, 100],
            limit: 15,
            page: true,
            skin: 'line',
            done: function (res, curr, count) {
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                console.log(res);
                $("[data-field = 'type']").children().each(function () {

                    if ($(this).text() == '1') {

                        $(this).text("非范围内");

                    } else if ($(this).text() == '2') {

                        $(this).text("范围内考试");
                    }
                })
                $("[data-field = 'valid']").children().each(function () {

                    if ($(this).text() == '1') {

                        $(this).text("正常");

                    } else if ($(this).text() == '0') {

                        $(this).text("失效");
                    }
                })
                $("[data-field = 'status']").children().each(function () {

                    //试卷状态（1、已发布，出卷中2、未开始，3、已开始，4、已结束）
                    if ($(this).text() == '1') {

                        $(this).text("已发布，未出题");

                    } else if ($(this).text() == '2') {

                        $(this).text("待考试");
                    } else if ($(this).text() == '3') {

                        $(this).text("已开始");
                    } else if ($(this).text() == '4') {

                        $(this).text("已结束");
                    }
                })

                //得到当前页码
                console.log(curr);

                //得到数据总量
                console.log(count);
            }
        });

        table.on('edit(currentTableFilter)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
            console.log(obj.value); //得到修改后的值
            console.log(obj.field); //当前编辑的字段名
            console.log(obj.data); //所在行的所有相关数据
        });
        // 监听搜索操作
        form.on('submit(data-search-btn)', function (data) {
            let result = JSON.stringify(data.field);
            //执行搜索重载
            table.reload('currentTableId', {
                page: {
                    curr: 1
                }
                , where: {
                    data: JSON.parse(result)
                },
                contentType: 'application/json'
        }, 'data');

            return false;
        });

        /**
         * toolbar监听事件
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'add') {  // 监听添加操作
                window.location = "/teacher/toExamAdd?examId=-1"
            } else if (obj.event === 'delete') {  // 监听删除操作
                layer.confirm('真的删除行么', function (index) {
                    let checkStatus = table.checkStatus('currentTableId')
                        , delData = checkStatus.data;
                    $.ajax({
                        type: "POST",
                        url: "${basePath}/exam/teacher/del",
                        data: JSON.stringify(delData[0]),
                        contentType: "application/json",
                        dataType: "json",
                        success: function (resp) {
                            if (resp.flag) {
                                layer.msg(resp.message, function () {
                                    location.reload();
                                });
                            } else {
                                layer.msg(resp.message, function () {
                                });
                            }
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {

                        },
                    })
                });
            }
        });

        //监听表格复选框选择
        table.on('checkbox(currentTableFilter)', function (obj) {
            console.log(obj)
        });

        table.on('tool(currentTableFilter)', function (obj) {
            let data = obj.data;
            if (obj.event === 'edit') {
                window.location = "${basePath}/teacher/toExamAdd?examId="+data.id
            } else if (obj.event === 'delete') {
                layer.confirm('真的删除行么', function (index) {
                    obj.del();
                    layer.close(index);
                });
            }else if (obj.event==='addQuestion'){
                //添加问题
                window.location = "/teacher/toQuestionAdd?exam_id="+data.id;
            }else if (obj.event==='publish'){
                //发布考卷（待考试）
                let publishData={
                    "id":data.id,
                    "teacherId":data.teacherId
                }
                layer.confirm('确定发布么？', function (index) {
                    $.ajax({
                        type: "POST",
                        url: "${basePath}/teacher/publish0",
                        data: JSON.stringify(publishData),
                        contentType: "application/json",
                        dataType: "json",
                        success: function (resp) {
                            if (resp.flag) {
                                layer.msg(resp.message, function () {
                                    location.reload();
                                });
                            } else {
                                layer.msg(resp.message, function () {
                                });
                            }
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {

                        },
                    })
                });
            }
        });

    });
</script>

</body>
</html>