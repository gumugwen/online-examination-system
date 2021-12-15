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
    <title>试卷详情</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../../lib/layui-v2.5.5/css/layui.css" media="all">

    <link rel="stylesheet" href="../../css/public.css" media="all">
</head>
<body>
<div class="layuimini-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md8">
            <div class="grid-demo grid-demo-bg1">
                <div class="layui-card">
                    <div class="layui-card-header">试卷信息</div>
                    <div class="layui-card-body">
                        <div>
                            试题总分 ：${examDetail.totalScore}
                        </div>
                        <div>
                            <div class="layui-row">
                                <div class="layui-col-xs4">
                                    <div class="grid-demo grid-demo-bg1">试卷名：${examDetail.title}</div>
                                </div>
                                <div class="layui-col-xs4">
                                    <div class="grid-demo">考试时间：${examDetail.startTime}</div>
                                </div>
                                <div class="layui-col-xs4">
                                    <div class="grid-demo grid-demo-bg1">考生姓名：${examDetail.studentName}</div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-row layui-col-space15">
            <div class="layui-col-md8">
                <div class="grid-demo grid-demo-bg1">
                    <div class="layui-card">
                        <h2 class="layui-card-header" style="font-size: 30px;font-weight: bold">一、单选题 （总分：${examDetail.singlePoints}）</h2>
                        <div class="layui-card-body">
                            <c:forEach items="${examDetail.singleDetailList}" var="single" varStatus="status">
                                <div style="background: #D3D4D3;font-weight: bold">${status.count}：${single.title}</div>
                                <div style="margin: 20px">
                                   <div>A: ${single.optionA}</div>
                                   <div>B: ${single.optionB}</div>
                                   <div>C: ${single.optionC}</div>
                                   <div>D: ${single.optionD}</div>
                               </div>

                                <div style="margin: 20px 20px">
                                    <span style="margin-top: 20px;margin-bottom: 20px"> 答案:${single.rightAnswer}</span><span>  考生答案：${single.right?single.rightAnswer:single.wrongAnswer}</span>
                                   <br/> <div style="color: ${single.right?'green':'red'}">得分：${single.inScore}</div>
                                </div>
                            </c:forEach>

                        </div>
                    </div>
                    <div class="layui-card">
                        <h2 class="layui-card-header" style="font-size: 30px;font-weight: bold">二、多选题 （总分：${examDetail.multiPotints}）</h2>
                        <div class="layui-card-body">
                            <c:forEach items="${examDetail.multiDetailList}" var="multi" varStatus="status">
                                <div style="background: #D3D4D3;font-weight: bold">${status.count}：${multi.title}</div>
                                <div style="margin: 20px">
                                    <div>A: ${multi.optionA}</div>
                                    <div>B: ${multi.optionB}</div>
                                    <div>C: ${multi.optionC}</div>
                                    <div>D: ${multi.optionD}</div>
                                </div>

                                <div style="margin: 20px 20px">
                                    <span style="margin-top: 20px;margin-bottom: 20px"> 答案:${multi.rightAnswer}</span><span>  考生答案：${multi.right?multi.rightAnswer:multi.wrongAnswer}</span>
                                    <br/><div style="color: ${multi.right?'green':'red'}">得分：${multi.inScore}</div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="layui-card">
                        <h2 class="layui-card-header" style="font-size: 30px;font-weight: bold">三、判断题 总分：${examDetail.judgePoints}）</h2>
                        <div class="layui-card-body">
                            <c:forEach items="${examDetail.judgeDetailList}" var="judge" varStatus="status">
                                <div style="background: #D3D4D3;font-weight: bold">${status.count}：${judge.title}</div>

                                <div style="margin: 20px 20px">
                                    <span style="margin-top: 20px;margin-bottom:20px"> 答案:${judge.rightAnswer}</span><span>  考生答案：${judge.right?judge.rightAnswer:judge.wrongAnswer}</span>
                                    <br/><div style="color: ${judge.right?'green':'red'}">得分：${judge.inScore}</div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
     <%--   <div class="layui-col-md4">
            <div class="grid-demo">
                <div class="layui-card">
                    <div class="layui-card-header">答题卡</div>
                </div>
                <div class="layui-card">
                    <div class="layui-card-header">单选题</div>
                    <div class="layui-card-body">
                        <button type="button" class="layui-btn layui-btn-sm layui-btn-primary">
                            <span>1</span>
                        </button>
                        <button type="button" class="layui-btn layui-btn-sm layui-btn-primary">
                            <span>1</span>
                        </button>
                        <button type="button" class="layui-btn layui-btn-sm layui-btn-primary">
                            <span>1</span>
                        </button>
                        <button type="button" class="layui-btn layui-btn-sm layui-btn-primary">
                            <span>1</span>
                        </button>
                    </div>
                </div>
                <div class="layui-card">
                    <div class="layui-card-header">多选题</div>
                    <div class="layui-card-body">

                    </div>
                </div>
                <div class="layui-card">
                    <div class="layui-card-header">判断题</div>
                    <div class="layui-card-body">

                    </div>
                </div>
            </div>
        </div>--%>
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