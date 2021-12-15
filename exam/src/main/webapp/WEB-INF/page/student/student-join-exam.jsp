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
        .active{background-color: #1E9FFF;color: #ffffff}
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
                                    <input type="text" name="timeLimit_exam" value="${timeLimit}" style="display:none;">
                                </div>
                                <div class="layui-col-md4">
                                    考生姓名:${student_username}
                                </div>
                            </div>

                            <form class="layui-form" action="" id="myform1">

                                <fieldset id="singleList" class="layui-elem-field layui-field-title"
                                    style="margin-top: 20px;">
                                    <legend>
                                        <h3>单选题(${single_count}分)</h3>
                                    </legend>
                                </fieldset>


                                <c:forEach items="${singleList}" var="singleList" varStatus="status">
                                    <div class="layui-form-item singlestyle" id="${singleList.id}_single_${status.count}">
                                        <label style="visibility: hidden;">nihao</label>
                                        <label class="layui-input-block">
                                            <blockquote class="layui-elem-quote layui-quote-nm">
                                                <div style="margin-left: 90px;">${status.count}. ${singleList.title} (${singleList.score}分)
                                                </div>
                                            </blockquote>
                                        </label><br>
                                        <div class="layui-input-block singlestyle1">
                                            <input type="text" name="id" value="${singleList.id}" style="display:none;">

                                            <input type="text" name="question_number" value="${status.count}"
                                                style="display:none;">
                                            <input type="radio" name="single_${singleList.id}_${status.count}"
                                                id="${status.count}_${singleList.id}_a" value="A"
                                                title="${singleList.optiona}" lay-filter="switchTest1"><br>
                                            <input type="radio" name="single_${singleList.id}_${status.count}"
                                                id="${status.count}_${singleList.id}_b" value="B"
                                                title="${singleList.optionb}" lay-filter="switchTest1"><br>
                                            <input type="radio" name="single_${singleList.id}_${status.count}"
                                                id="${status.count}_${singleList.id}_c" value="C"
                                                title="${singleList.optionc}" lay-filter="switchTest1"><br>
                                            <input type="radio" name="single_${singleList.id}_${status.count}"
                                                id="${status.count}_${singleList.id}_d" value="D"
                                                title="${singleList.optiond}" lay-filter="switchTest1"><br>
                                        </div>
                                    </div>
                                </c:forEach>

                                <fieldset id="multiList" class="layui-elem-field layui-field-title"
                                    style="margin-top: 20px;">
                                    <legend>
                                        <h3>多选题(${multi_count}分)</h3>
                                    </legend>
                                </fieldset>

                                <c:forEach items="${multiList}" var="multiList" varStatus="status">
                                    <div class="layui-form-item multistyle" id="${multiList.id}_multi_${status.count}">
                                        <label style="visibility: hidden;">nihao</label>
                                        <label class="layui-input-block">
                                            <blockquote class="layui-elem-quote layui-quote-nm">
                                                <div style="margin-left: 90px;">${status.count}. ${multiList.title} (${multiList.score}分)
                                                </div>
                                            </blockquote>
                                        </label><br>
                                        <div class="layui-input-block multistyle1">
                                            <input type="text" name="id" value="${multiList.id}" style="display:none;">

                                            <input type="text" name="question_number" value="${status.count}"
                                                style="display:none;">
                                            <input type="checkbox" id="multi_${status.count}_${multiList.id}_a"
                                                name="multi_${multiList.id}_${status.count}_a" value="A"
                                                title="${multiList.optiona}" lay-filter="switchTest"><br>
                                            <input type="checkbox" id="multi_${status.count}_${multiList.id}_b"
                                                name="multi_${multiList.id}_${status.count}_b" value="B"
                                                title="${multiList.optionb}" lay-filter="switchTest"><br>
                                            <input type="checkbox" id="multi_${status.count}_${multiList.id}_c"
                                                name="multi_${multiList.id}_${status.count}_c" value="C"
                                                title="${multiList.optionc}" lay-filter="switchTest"><br>
                                            <input type="checkbox" id="multi_${status.count}_${multiList.id}_d"
                                                name="multi_${multiList.id}_${status.count}_d" value="D"
                                                title="${multiList.optiond}" lay-filter="switchTest"><br>
                                        </div>
                                    </div>
                                </c:forEach>

                                <fieldset id="judgeList" class="layui-elem-field layui-field-title"
                                    style="margin-top: 20px;">
                                    <legend>
                                        <h3>判断题(${judge_count}分)</h3>
                                    </legend>
                                </fieldset>

                                <c:forEach items="${judgeList}" var="judgeList" varStatus="status">
                                    <div class="layui-form-item judgestyle" id="${judgeList.id}_judge_${status.count}">
                                        <label style="visibility: hidden;">nihao</label>
                                        <label class="layui-input-block">
                                            <blockquote class="layui-elem-quote layui-quote-nm">
                                                <div style="margin-left: 90px;">${status.count}. ${judgeList.title} (${judgeList.score}分)
                                                </div>
                                            </blockquote>
                                        </label>
                                        <div class="layui-input-block judgestyle1">
                                            <input type="text" name="id" value="${judgeList.id}" style="display:none;">

                                            <input type="text" name="question_number" value="${status.count}"
                                                style="display:none;">
                                            <input type="radio" name="judge_${judgeList.id}_${status.count}"
                                                id="judge_${status.count}_${judgeList.id}_a" value="1" title="正确"
                                                lay-filter="switchTest2">
                                            <input type="radio" name="judge_${judgeList.id}_${status.count}"
                                                id="judge_${status.count}_${judgeList.id}_b" value="0" title="错误"
                                                lay-filter="switchTest2">
                                        </div>
                                    </div>
                                </c:forEach>

                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <button type="button" class="layui-btn" id="submit_button" lay-submit=""
                                            lay-filter="demo1">立即提交</button>
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
                                <div id="timer"></div>
                                <div id="warring"></div>
                            </div>
                        </div>

                        <blockquote class="layui-elem-quote layui-text">
                            <h3>单选题 共${singleCount}题目</h3>
                            <input type="text" id="sinlgeCount" name="sinlgeCount" value="${singleCount}" style="display:none;">
                        </blockquote>
                        <div class="layui-card">
                            <c:forEach items="${singleList}" var="singleList" varStatus="status">
                                <div class="layui-input-inline">
                                    <a href="#${singleList.id}_single_${status.count}">
                                    <button type="button" id="${status.count}_${singleList.id}_single"
                                        value="${status.count}" title="${status.count}"
                                        style="width: 50px; height: 50px;" class="layui-btn layui-btn-primary answer-card">${status.count}</button>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>

                        <blockquote class="layui-elem-quote layui-text">
                            <h3>多选题 共${MultiCount}题目</h3>
                            <input type="text" id="MultiCount" name="MultiCount" value="${MultiCount}" style="display:none;">
                        </blockquote>
                        <div class="layui-card">
                            <c:forEach items="${multiList}" var="multiList" varStatus="status">
                                <div class="layui-input-inline">
                                    <a href="#${multiList.id}_multi_${status.count}">
                                    <button type="button" id="multi_${status.count}_${multiList.id}_multi"
                                        value="${status.count}" title="${status.count}"
                                        style="width: 50px; height: 50px;" class="layui-btn layui-btn-primary answer-card">${status.count}</button>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>

                        <blockquote class="layui-elem-quote layui-text">
                            <h3>判断题 共${judgeCount}题目</h3>
                            <input type="text" id="judgeCount" name="judgeCount" value="${judgeCount}" style="display:none;">
                        </blockquote>
                        <div class="layui-card">
                            <c:forEach items="${judgeList}" var="judgeList" varStatus="status">
                                <div class="layui-input-inline">
                                    <a href="#${judgeList.id}_judge_${status.count}">
                                    <button type="button" id="judge_${status.count}_${judgeList.id}_judge"
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
            <script type="text/javascript" src="${basePath}/js/jquery-1.11.1.min.js" charset="utf-8"></script>
            <script type="text/javascript" src="${basePath}/lib/jquery-3.4.1/jquery-3.4.1.min.js"></script>
            <!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
            <script>
                layui.use(['form', 'layedit', 'laydate'], function () {
                    var form = layui.form
                        , layer = layui.layer
                        , layedit = layui.layedit
                        , laydate = layui.laydate
                        , miniTab = layui.miniTab;
                    //监听指定开关
                    form.on('checkbox(switchTest)', function (data) {
                        var str = data.elem.getAttribute("id");
                        console.log("id为："+str);
                        if (this.checked == true) {
                            var index = str.indexOf("_");
                            index = str.indexOf("_", index + 1);
                            index = str.indexOf("_", index + 1);
                            var str1 = str.substring(0, index);
                            $("#" + str1 + "_multi").attr("checked", true);
                            //console.log("该按钮被选中，按钮变色");
                            $("#" + str1 + "_multi").css("background-color", "MediumSpringGreen");
                        } else if (this.checked == false) {
                            //console.log("该按钮没有被选中，按钮判断开始");
                            var index = str.indexOf("_");
                            index = str.indexOf("_", index + 1);
                            index = str.indexOf("_", index + 1);
                            var str1 = str.substring(0, index);
                            //console.log(str1);
                            var check1 = ($("#" + str1 + "_a").is(':checked'));
                            //console.log("按钮1判断"+"#" + str1 + "_a"+check1);
                            var check2 = ($("#" + str1 + "_b").is(':checked'));
                           //console.log("按钮2判断"+check2);
                            var check3 = ($("#" + str1 + "_c").is(':checked'));
                            //console.log("按钮3判断"+check3);
                            var check4 = ($("#" + str1 + "_d").is(':checked'));
                            //console.log("按钮4判断"+check4);
                            if (check1 == false && check2 == false && check3 == false && check4 == false) {
                                $("#" + str1 + "_multi").attr("checked", false);
                                //console.log("按钮变成白色");
                                $("#" + str1 + "_multi").css("background-color", "White");
                            }
                        }
                    });

                    // 监听单选题
                    form.on('radio(switchTest1)', function (data) {
                        // console.log(data);
                        console.log(data.elem.getAttribute("id"));
                        var str = data.elem.getAttribute("id");
                        if (this.checked == true) {
                            var index = str.indexOf("_");
                            index = str.indexOf("_", index + 1);
                            var str1 = str.substring(0, index);
                            // console.log(str1);
                            $("#" + str1 + "_single").attr("checked", true);
                            $("#" + str1 + "_single").css("background-color", "MediumSpringGreen");
                        } else if (this.checked == false) {
                            var index = str.indexOf("_");
                            index = str.indexOf("_", index + 1);
                            index = str.indexOf("_", index + 1);
                            var str1 = str.substring(0, index);
                            // console.log(str1);
                            var check1 = ($("#" + str1 + "_a").attr("checked") == "checked");
                            var check2 = ($("#" + str1 + "_b").attr("checked") == "checked");
                            var check3 = ($("#" + str1 + "_c").attr("checked") == "checked");
                            var check4 = ($("#" + str1 + "_d").attr("checked") == "checked");
                            if (check1 == false && check2 == false && check3 == false && check4 == false) {
                                $("#" + str1 + "_single").attr("checked", false);
                                $("#" + str1 + "_single").css('background-color','White');
                            }
                        }
                    });

                    // 监听判断题
                    form.on('radio(switchTest2)', function (data) {
                        // console.log(data);
                        console.log(data.elem.getAttribute("id"));
                        var str = data.elem.getAttribute("id");
                        if (this.checked == true) {
                            var index = str.indexOf("_");
                            index = str.indexOf("_", index + 1);
                            index = str.indexOf("_", index + 1);
                            var str1 = str.substring(0, index);
                            // console.log(str1);
                            console.log($("#" + str1 + "_judge").val());
                            $("#" + str1 + "_judge").attr("checked", true);
                            $("#" + str1 + "_judge").css("background-color", "MediumSpringGreen");
                        } else if (this.checked == false) {
                            var index = str.indexOf("_");
                            index = str.indexOf("_", index + 1);
                            index = str.indexOf("_", index + 1);
                            var str1 = str.substring(0, index);
                            // console.log(str1);
                            var check1 = ($("#" + str1 + "_a").attr("checked") == "checked");
                            var check2 = ($("#" + str1 + "_b").attr("checked") == "checked");
                            if (check1 == false && check2 == false && check3 == false && check4 == false) {
                                $("#" + str1 + "_judge").attr("checked", false);
                                $("#" + str1 + "_judge").css('background-color','White');
                            }
                        }
                    });

                    window.getAllQuestionValue = function getQuestionValue() {
                        //获取判断题的值
                        var jsonlist = {};
                        var json = {};
                        var str = "";
                        var strlist = "";
                        var judgelist = $(".judgestyle .judgestyle1");
                        judgelist.each(function () {
                            var judge = $(this).find("input:radio:checked,input:text");
                            judge.each(function () {
                                var key = $(this).attr("name");
                                if (key.indexOf("judge_") != -1) {
                                    key = "answer";
                                    var value = $(this).val();
                                    json[key] = value;
                                } else {
                                    var value = $(this).val();
                                    json[key] = value;
                                    json["answer"] = "";
                                }
                                str = JSON.stringify(json);
                                console.log(str);

                            });
                            if (strlist == "") {
                                strlist = strlist + str;
                            } else {
                                strlist = strlist + "," + str;
                            }
                        });
                        console.log("这是判断题的最终字符串");
                        strlist = "{\"judge\":" + "[" + strlist + "]}";
                        console.log(strlist);
                        jsonlist["judge"] = JSON.parse(strlist);

                        //获取单选题的值
                        var json1 = {};
                        var str1 = "";
                        var strlist1 = "";
                        //console.log("这里！");
                        var judgelist1 = $(".singlestyle .singlestyle1");
                        judgelist1.each(function () {
                            var single = $(this).find("input:radio:checked,input:text");
                            single.each(function () {
                                var key = $(this).attr("name");
                                if (key.indexOf("single_") != -1) {
                                    key = "answer";
                                    var value = $(this).val();
                                    json1[key] = value;
                                } else {
                                    var value = $(this).val();
                                    json1[key] = value;
                                    json1["answer"] = "";
                                }
                                //console.log("value值为:" + value);
                                //json1[key] = value;
                                str1 = JSON.stringify(json1);

                            });
                            if (strlist1 == "") {
                                strlist1 = strlist1 + str1;
                            } else {
                                strlist1 = strlist1 + "," + str1;
                            }
                        });
                        console.log("这是单选题的最终字符串");
                        strlist1 = "{\"single\":" + "[" + strlist1 + "]}";
                        console.log(strlist1);
                        jsonlist["single"] = JSON.parse(strlist1);

                        //获取多选题的值
                        var json2 = {};
                        var str2 = "";
                        var strlist2 = "";
                        var judgelist2 = $(".multistyle .multistyle1");
                        judgelist2.each(function () {
                            var multi = $(this).find("input:checkbox:checked,input:text");
                            var value = "";
                            var value1 = "";
                            multi.each(function () {
                                var key = $(this).attr("name");
                                if (key.indexOf("multi_") != -1) {
                                    key = "answer";
                                    if (value1 == "") {
                                        value1 = $(this).val();
                                        json2[key] = value1;
                                    } else {
                                        value1 = value1 + "," + $(this).val();
                                        json2[key] = value1;
                                    }
                                } else {
                                    value1 = "";
                                    value = $(this).val();
                                    json2[key] = value;
                                    json2["answer"] = value1;
                                }
                                str2 = JSON.stringify(json2);
                            });
                            if (strlist2 == "") {
                                strlist2 = strlist2 + str2;
                            } else {
                                strlist2 = strlist2 + "," + str2;
                            }
                        });
                        strlist2 = "{\"multi\":" + "[" + strlist2 + "]}";
                        jsonlist["multi"] = JSON.parse(strlist2);
                        console.log("展示一下jsonlist");
                        jsonlist["examId"] = $("#examId").val();
                        jsonlist["examStartTime"] = $("#starttime").val();
                        console.log(jsonlist);
                        return jsonlist;
                    }

                    //监听提交
                    form.on('submit(demo1)', function (data) {
                        var jsonlist = {};
                        jsonlist = getAllQuestionValue();
                        data = data.field;
                        console.log(data);

                        console.log(JSON.stringify(data));
                        $.ajax({
                            type: "POST",
                            url: "${basePath}/student/exam/list/save",
                            contentType: "application/json",
                            data: JSON.stringify(jsonlist),
                            dataType: "json",
                            success: function (data) {
                                console.log(data);
                                console.log(typeof (data));
                                if (data["test"] == "1") {
                                    layer.msg("提交成功！", { time: 1000 }, function () {//time单位是毫秒
                                        //关闭修改页面的弹出窗口
                                        var iframeIndex = parent.layer.getFrameIndex(window.name);
                                        parent.layer.close(iframeIndex);
                                        //刷新父页面
                                        window.parent.location.reload();
                                    });
                                } else {
                                    layer.alert("提交失败！请联系管理员！");
                                }
                            },
                            error: function (data) {
                                layer.alert("提交失败请联系管理员！");
                            },
                        })
                        return false;
                    });


                    layer.ready($(function () {
                        var sinlgecount = $('#sinlgeCount').val();
                        var multicount = $('#MultiCount').val();
                        var judgecount = $('#judgeCount').val();
                        console.log(sinlgecount);
                        console.log(multicount);
                        console.log(judgecount);
                        if (sinlgecount == 0) {
                            $("#singleList").hide();
                        }
                        if (multicount == 0) {
                            $("#judgeList").hide();
                        }
                        if (judgecount == 0) {
                            $("#multiList").hide();
                        }
                        if (sinlgecount == 0 && multicount == 0 && judgecount == 0) {
                            $('#submit_button').hide();
                        }
                    }));



                });



                //考试计时器
                var maxtime = ${timeLimit} * 60;
                function CountDown() {
                    if (maxtime >= 0) {
                        minutes = Math.floor(maxtime / 60);
                        seconds = Math.floor(maxtime % 60);
                        msg = "距离结束还有" + minutes + "分" + seconds + "秒";
                        document.all["timer"].innerHTML = msg;
                        if (maxtime == 5 * 60) alert("距离结束仅剩5分钟");
                        --maxtime;
                    } else {
                        var jsonlist = getAllQuestionValue();
                        console.log(jsonlist);
                        $.ajax({
                            type: "POST",
                            url: "${basePath}/student/exam/list/save",
                            contentType: "application/json",
                            data: JSON.stringify(jsonlist),
                            dataType: "json",
                            success: function (data) {
                                console.log(data);
                                console.log(typeof (data));
                                if (data["test"] == "1") {
                                    window.location.href="${basePath}/student/toStudentIndex";
                                } else {
                                    alert("提交失败！请联系管理员！");
                                }
                            },
                            error: function (data) {
                                alert("提交失败请联系管理员！");
                            },
                        })
                        clearInterval(timer);
                        alert("时间到，结束!");
                    }
                }
                timer = setInterval("CountDown()", 1000);
            </script>
</body>

</html>