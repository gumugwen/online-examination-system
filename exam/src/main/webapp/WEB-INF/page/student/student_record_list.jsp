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
    <style>
    </style>
</head>

<body>
    <div class="layuimini-container">
        <div class="layuimini-main">

            <div>
                <div class="layui-row layui-col-space20">
                    <div class="layui-col-md8">
                        <blockquote class="layui-elem-quote layui-text">
                            <h3>试卷信息</h3>
                        </blockquote>
                        <div class="layui-card ">
                            <div class="layui-card-header">
                                <input type="text" id="starttime" value="${examStart}" style="display: none;">
                                <input type="text" id="examId" value="${examId}" style="display: none;">
                                <div class="layui-col-md4">
                                    试卷名:${examName}
                                </div>
                                <div class="layui-col-md4">
                                    考试时间:${timeLimit}分钟
                                </div>
                                <div class="layui-col-md4">
                                    考生姓名:${student_username}
                                </div>
                            </div>

                            <form class="layui-form" action="" id="myform1">

                                <fieldset id="singleList" class="layui-elem-field layui-field-title"
                                    style="margin-top: 20px;">
                                    <legend>
                                        <h3>单选题</h3>
                                    </legend>
                                </fieldset>


                                <c:forEach items="${singleList}" var="singleList" varStatus="status">
                                    <div class="layui-form-item singlestyle" id="${singleList.id}_single_${status.count}">
                                        <label style="visibility: hidden;">nihao</label>
                                        <label class="layui-input-block">
                                            <blockquote class="layui-elem-quote layui-quote-nm">
                                                <div style="margin-left: 90px;">${status.count}. ${singleList.title} (${singleList.get_score}分)
                                                    <div style="text-align: right;">
                                                        <c:if
                                                            test="${singleList.record_answer eq singleList.correct_answer}">
                                                            回答正确
                                                        </c:if>
                                                        <c:if
                                                            test="${singleList.record_answer ne singleList.correct_answer}">
                                                            回答错误
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </blockquote>
                                        </label><br>
                                        <div class="layui-input-block singlestyle1">
                                            <input type="text" name="record_answer" value="${singleList.record_answer}"
                                                style="display:none;">
                                            <input type="text" name="id" value="${singleList.question_id}"
                                                style="display:none;">
                                            <input type="text" name="correct_answer"
                                                value="${singleList.correct_answer}" style="display:none;">
                                            <input type="text" name="question_number" value="${status.count}"
                                                style="display:none;">
                                            <input type="radio" name="single_${singleList.question_id}_${status.count}"
                                                id="${status.count}_${singleList.question_id}_a" value="A"
                                                title="${singleList.optionA}" lay-filter="switchTest1" disabled <c:if
                                                test="${fn:contains(singleList.record_answer, 'A')}">checked="checked"
                                            </c:if>><br>
                                            <input type="radio" name="single_${singleList.question_id}_${status.count}"
                                                id="${status.count}_${singleList.question_id}_b" value="B"
                                                title="${singleList.optionB}" lay-filter="switchTest1" disabled <c:if
                                                test="${fn:contains(singleList.record_answer, 'B')}">checked="checked"
                                            </c:if>><br>
                                            <input type="radio" name="single_${singleList.question_id}_${status.count}"
                                                id="${status.count}_${singleList.question_id}_c" value="C"
                                                title="${singleList.optionC}" lay-filter="switchTest1" disabled <c:if
                                                test="${fn:contains(singleList.record_answer, 'C')}">checked="checked"
                                            </c:if>><br>
                                            <input type="radio" name="single_${singleList.question_id}_${status.count}"
                                                id="${status.count}_${singleList.question_id}_d" value="D"
                                                title="${singleList.optionD}" lay-filter="switchTest1" disabled <c:if
                                                test="${fn:contains(singleList.record_answer, 'D')}">checked="checked"
                                            </c:if>><br>
                                            <label>您的答案是：${singleList.record_answer}</label>
                                            <label>正确答案是：${singleList.correct_answer}</label>
                                        </div>
                                    </div>
                                </c:forEach>

                                <fieldset id="multiList" class="layui-elem-field layui-field-title"
                                    style="margin-top: 20px;">
                                    <legend>
                                        <h3>多选题</h3>
                                    </legend>
                                </fieldset>

                                <c:forEach items="${multiList}" var="multiList" varStatus="status">
                                    <div class="layui-form-item multistyle" id="${multiList.id}_multi_${status.count}">
                                        <label style="visibility: hidden;">nihao</label>
                                        <label class="layui-input-block">
                                            <blockquote class="layui-elem-quote layui-quote-nm">
                                                <div style="margin-left: 90px;">${status.count}. ${multiList.title} (${multiList.get_score}分)
                                                    <div style="text-align: right;">
                                                        <c:if
                                                            test="${multiList.record_answer eq multiList.correct_answer}">
                                                            回答正确
                                                        </c:if>
                                                        <c:if
                                                            test="${multiList.record_answer ne multiList.correct_answer}">
                                                            回答错误
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </blockquote>
                                        </label><br>
                                        <div class="layui-input-block multistyle1">
                                            <input type="text" name="id" value="${multiList.id}" style="display:none;">

                                            <input type="text" name="question_number" value="${status.count}"
                                                style="display:none;">
                                            <input type="checkbox" id="${status.count}_${multiList.question_id}_a"
                                                name="multi_${multiList.question_id}_${status.count}_a" value="A"
                                                title="${multiList.optionA}" lay-filter="switchTest" disabled <c:if
                                                test="${fn:contains(multiList.record_answer, 'A')}">checked="checked"
                                            </c:if>><br>
                                            <input type="checkbox" id="${status.count}_${multiList.question_id}_b"
                                                name="multi_${multiList.question_id}_${status.count}_b" value="B"
                                                title="${multiList.optionB}" lay-filter="switchTest" disabled <c:if
                                                test="${fn:contains(multiList.record_answer, 'B')}">checked="checked"
                                            </c:if>><br>
                                            <input type="checkbox" id="${status.count}_${multiList.question_id}_c"
                                                name="multi_${multiList.question_id}_${status.count}_c" value="C"
                                                title="${multiList.optionC}" lay-filter="switchTest" disabled <c:if
                                                test="${fn:contains(multiList.record_answer, 'C')}">checked="checked"
                                            </c:if>><br>
                                            <input type="checkbox" id="${status.count}_${multiList.question_id}_d"
                                                name="multi_${multiList.question_id}_${status.count}_d" value="D"
                                                title="${multiList.optionD}" lay-filter="switchTest" disabled <c:if
                                                test="${fn:contains(multiList.record_answer, 'D')}">checked="checked"
                                            </c:if>><br>
                                            <label>您的答案是：${multiList.record_answer}
                                            </label>
                                            <label>正确答案是：${multiList.correct_answer}
                                            </label>
                                        </div>
                                    </div>
                                </c:forEach>

                                <fieldset id="judgeList" class="layui-elem-field layui-field-title"
                                    style="margin-top: 20px;">
                                    <legend>
                                        <h3>判断题</h3>
                                    </legend>
                                </fieldset>

                                <c:forEach items="${judgeList}" var="judgeList" varStatus="status">
                                    <div class="layui-form-item judgestyle" id="${judgeList.id}_judge_${status.count}">
                                        <label style="visibility: hidden;">nihao</label>
                                        <label class="layui-input-block">
                                            <blockquote class="layui-elem-quote layui-quote-nm"> 
                                                <div style="margin-left: 90px;">${status.count}. ${judgeList.title} (${judgeList.get_score}分)
                                                    <div style="text-align: right;">
                                                        <c:if
                                                            test="${judgeList.record_answer eq judgeList.correct_answer}">
                                                            回答正确
                                                        </c:if>
                                                        <c:if
                                                            test="${judgeList.record_answer ne judgeList.correct_answer}">
                                                            回答错误
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </blockquote>
                                        </label>
                                        <div class="layui-input-block judgestyle1">
                                            <input type="text" name="id" value="${judgeList.id}" style="display:none;">

                                            <input type="text" name="question_number" value="${status.count}"
                                                style="display:none;">
                                            <input type="radio" name="judge_${judgeList.question_id}_${status.count}"
                                                id="${status.count}_${judgeList.question_id}_a" value="1" title="正确"
                                                lay-filter="switchTest2" disabled <c:if
                                                test="${fn:contains(judgeList.record_answer, '1')}">checked="checked"
                                            </c:if>>
                                            <input type="radio" name="judge_${judgeList.question_id}_${status.count}"
                                                id="${status.count}_${judgeList.question_id}_b" value="0" title="错误"
                                                lay-filter="switchTest2" disabled <c:if
                                                test="${fn:contains(judgeList.record_answer, '0')}">checked="checked"
                                            </c:if>>
                                            <label>您的答案是：
                                                <c:if test="${judgeList.record_answer eq '1'}">
                                                    正确
                                                </c:if>
                                                <c:if test="${judgeList.record_answer ne '1'}">
                                                    错误
                                                </c:if>
                                            </label>
                                            <label>正确答案是：
                                                <c:if test="${judgeList.correct_answer eq '1'}">
                                                    正确
                                                </c:if>
                                                <c:if test="${judgeList.correct_answer ne '1'}">
                                                    错误
                                                </c:if>
                                            </label>
                                        </div>
                                    </div>
                                </c:forEach>

                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <button type="button" class="layui-btn" id="return">返回</button>
                                    </div>
                                </div>
                            </form>




                        </div>
                    </div>

                    <div class="layui-col-md4">
                        <blockquote class="layui-elem-quote layui-text">
                            <h3>答题卡</h3>
                        </blockquote>
                        <div class="layui-card">
                            <div class="layui-card-header">
                                考试时间:${timeLimit}分钟
                            </div>
                        </div>

                        <blockquote class="layui-elem-quote layui-text">
                            <h3>单选题 共${singleCount}题目</h3>
                        </blockquote>
                        <div class="layui-card">
                            <c:forEach items="${singleList}" var="singleList" varStatus="status">
                                <div class="layui-input-inline">
                                    <a href="#${singleList.id}_single_${status.count}">
                                    <button type="button" id="${status.count}_${singleList.question_id}_single"
                                        value="${status.count}" title="${status.count}"
                                        style="width: 50px; height: 50px;" class="layui-btn layui-btn-primary answer-card">${status.count}</button>
                                        </a>
                                </div>
                            </c:forEach>
                        </div>

                        <blockquote class="layui-elem-quote layui-text">
                            <h3>多选题 共${MultiCount}题目</h3>
                        </blockquote>
                        <div class="layui-card">
                            <c:forEach items="${multiList}" var="multiList" varStatus="status">
                                <div class="layui-input-inline">
                                    <a href="#${multiList.id}_multi_${status.count}">
                                    <button type="button" id="${status.count}_${multiList.question_id}_multi"
                                        value="${status.count}" title="${status.count}"
                                        style="width: 50px; height: 50px;" class="layui-btn layui-btn-primary answer-card">${status.count}</button>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>

                        <blockquote class="layui-elem-quote layui-text">
                            <h3>判断题 共${judgeCount}题目</h3>
                        </blockquote>
                        <div class="layui-card">
                            <c:forEach items="${judgeList}" var="judgeList" varStatus="status">
                                <div class="layui-input-inline">
                                    <a href="#${judgeList.id}_judge_${status.count}">
                                    <button type="button" id="${status.count}_${judgeList.question_id}_judge"
                                        value="${status.count}" title="${status.count}"
                                        style="width: 50px; height: 50px;" class="layui-btn layui-btn-primary answer-card">${status.count}</button>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>

                </div>
            </div>

            <script src="${basePath}/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
            <script type="text/javascript" src="${basePath}/lib/jquery-3.4.1/jquery-3.4.1.min.js"></script>
            <!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
            <script>
                layui.use(['form', 'layedit', 'laydate'], function () {
                    var form = layui.form
                        , layer = layui.layer
                        , layedit = layui.layedit
                        , laydate = layui.laydate
                        , $=layui.jquery
                        , miniTab = layui.miniTab;

                    $(document).on('click', '#return', function () {
                        window.location.href="${basePath}/student/toStudentRecord";
                    });
                });
            </script>
</body>

</html>