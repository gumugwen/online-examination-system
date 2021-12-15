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
                            <label class="layui-form-label">考卷名：</label>
                            <div class="layui-input-inline">
                                <input type="text" name="title" id="title" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">状态：</label>
                            <div class="layui-input-block">
                                <select name="status" id="status" lay-filter="aihao">
                                    <option value="-1">全部</option>
                                    <option value="1">未考试</option>
                                    <option value="2" selected="">已完成</option>
                                    <option value="3">逾期未考</option>
                                    <option value="4">考试中</option>
                                </select>
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">班级：</label>
                            <div class="layui-input-block">
                                <select name="classId" id="valid" lay-filter="aihao">
                                    <c:forEach items="${classList}" var="c">
                                        <option value="${c.id}">${c.cname}</option>
                                    </c:forEach>
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


        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="toDetail">详情</a>
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
            url: '${basePath}/exam/result/exam-record',
            toolbar: '#toolbarDemo',
            defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            cols: [[
                {type: 'checkbox'},
                {field: 'id', title: 'ID', width: 100},
                {field: 'examId', title: '试卷编号', width: 100},
                {field: 'title', title: '考卷名', width: 200},
                {field: 'status', title: '状态', width: 300},
                {field: 'point', title: '得分', width: 300},
                {field: 'studentId', title: '学号', width: 100},
                {field: 'studentName', title: '学生', width: 130},
                {field: 'className', title: '班级', width: 200},
                {field: 'startTime', title: '开始时间', width: 300},
                {field: 'endTime', title: '结束时间', width: 300},

                {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
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
                $("[data-field = 'status']").children().each(function () {

                    //1. 未考试 2 已完成 3 逾期未考 4 考试中
                    if ($(this).text() == '1') {

                        $(this).text("未考试");

                    } else if ($(this).text() == '2') {

                        $(this).text("已完成");
                    } else if ($(this).text() == '3') {

                        $(this).text("逾期未考");
                    } else if ($(this).text() == '4') {

                        $(this).text("考试中");
                    }
                })
            }
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
                window.location = "/teacher/toExamAdd"
            }
        });

        //监听表格复选框选择
        table.on('checkbox(currentTableFilter)', function (obj) {
            console.log(obj)
        });

        table.on('tool(currentTableFilter)', function (obj) {
            let data = obj.data;
            if (obj.event === 'edit') {

                let index = layer.open({
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
            }else if (obj.event==='toDetail'){
                //添加问题
                window.location = "/exam/toExamResultDetail?resultId="+data.id+"&studentId="+data.studentId;
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