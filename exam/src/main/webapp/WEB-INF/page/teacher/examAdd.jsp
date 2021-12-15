<%--
  Created by IntelliJ IDEA.
  User: wangmaolin
  Date: 2020/11/18
  Time: 10:35 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>发布试卷</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../../../lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="../../../css/public.css" media="all">
    <style>
        body {
            background-color: #ffffff;
        }


        /* 下面是页面内样式，无需引用 */
        .layui-block {
            margin-bottom: 10px;
        }

        .layui-form-label {
            width: 180px;
        }

        .code {
            color: gray;
            margin-left: 10px;
        }

        .unshow > #result {
            display: none;
        }

        pre {
            padding: 5px;
            margin: 5px;
        }

        .string {
            color: green;
        }

        .number {
            color: darkorange;
        }

        .boolean {
            color: blue;
        }

        .null {
            color: magenta;
        }

        .key {
            color: red;
        }
    </style>
</head>
<body>
<div class="layui-form layuimini-form" lay-filter="formAdd">
    <div class="layui-form-item" hidden>
        <label class="layui-form-label required" for="id">试卷id</label>
        <div class="layui-input-block">
            <input type="text" name="id" id="id"
                   placeholder="请输入考卷名" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required" for="title">试卷名</label>
        <div class="layui-input-block">
            <input type="text" name="title" id="title" lay-verify="required" lay-reqtext="考卷名不能为空"
                   placeholder="请输入考卷名" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required" for="timeLimit">考试时长：</label>
        <div class="layui-input-block">
            <input type="number" name="timeLimit" id="timeLimit" lay-verify="required" lay-reqtext="考试时长不能为空"
                   placeholder="请输入考试时长" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">类型：</label>
        <div class="layui-input-block">
            <input type="radio" name="type" value="1" title="到时开考" checked="">
            <input type="radio" name="type" value="2" title="时间范围内随时可考">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label required">单选每题分数(默认2.0)：</label>
        <div class="layui-input-block">
            <input type="number" name="singlePoints" lay-verify="required" lay-reqtext="请输入单选分数" placeholder="请输入单选分数"
                   value="2.0" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">多选每题分数(默认4.0)：</label>
        <div class="layui-input-block">
            <input type="number" name="multiPoints" lay-verify="required" lay-reqtext="请输入多选分数" placeholder="请输入多选分数"
                   value="4.0" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">判断每题分数(默认1.0)：</label>
        <div class="layui-input-block">
            <input type="number" name="judgePoints" lay-verify="required" lay-reqtext="请输入判断分数" placeholder="请输入多选分数"
                   value="1.0" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">开始时间：</label>
        <div class="layui-input-block">
            <input type="text" id="startTime" name="startTime" lay-verify="required" lay-reqtext="请选择开始时间"
                   class="layui-input">

        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label required">结束时间：</label>
        <div class="layui-input-block">
            <input type="text" id="endTime" name="endTime" lay-verify="required" lay-reqtext="请选择结束时间"
                   class="layui-input">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">参考班级</label>
        <div class="layui-input-inline">
            <input type="text" name="classList" placeholder="请输入" autocomplete="off" class="layui-input" id="classList">
        </div>
    </div>
    <div class="layui-form-item" hidden >
        <label class="layui-form-label">参考班级id</label>
        <div class="layui-input-block">
            <input type="text" name="classIds" id="classIds"  lay-reqtext="参考班级id列表"
                    value="" class="layui-input">
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认保存</button>
        </div>
    </div>
</div>
<script src="../../../lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script src="../../../js/lay-config.js?v=1.0.4" charset="utf-8"></script>

<script>

</script>
<script>


    layui.use(['table', 'form', 'tableSelect'], function () {
        let form = layui.form,
            table = layui.table,
            tableSelect = layui.tableSelect;
             layer = layui.layer,
            $ = layui.$;

        tableSelect.render({
            elem: '#classList',
            searchKey: 'cname',
            checkedKey: 'id',
            searchPlaceholder: '班级名',
            table: {
                url: '${basePath}/class/list/teacher-class',
                cols: [[
                    {type: 'checkbox'},
                    {field: 'id', title: 'ID', width: 100},
                    {field: 'cname', title: '班级名', width: 300}
                ]]
            },
            done: function (elem, data) {
                console.log(elem)
                console.log(data)
                var cnameList = []
                var cnameIdList = []
                layui.each(data.data, function (index, item) {
                    cnameList.push(item.cname)
                    cnameIdList.push(item.id)
                })
                elem.val(cnameList.join(","))
                $("#classIds").val(cnameIdList.join(","))
            }
        })
        let examId=${examId};
        if (examId!==-1){
            //修改回显
            $.get("${basePath}/exam/exam-by-id?examId="+examId, function(resp){
                console.log(resp)
                let objData=resp.data;
                if (resp.flag){
                    form.val("formAdd", {
                        "id": objData.id,
                        "title": objData.title,
                        "type": objData.type,
                        "timeLimit": objData.timeLimit,
                        "startTime": objData.startTime,
                        "endTime": objData.endTime,
                        "singlePoints": objData.singlePoints,
                        "multiPoints": objData.multiPoints,
                        "judgePoints": objData.judgePoints,
                        "classIds": objData.classIdsList,
                        "classList": objData.classNames
                    })
                }
            });
        }
        //监听提交
        form.on('submit(saveBtn)', function (data) {

            console.log(data)
            if (examId===undefined||examId===-1){
                console.log(data)
                let exam = data.field;
                $.ajax({
                    type: "POST",
                    url: "${basePath}/teacher/publish",
                    data: JSON.stringify(exam),
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

            }else {
                let exam = data.field;
                $.ajax({
                    type: "POST",
                    url: "${basePath}/exam/updateExam",
                    data: JSON.stringify(exam),
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

            }


            return false;
        });

    });



    layui.use('laydate', function () {
        let laydate = layui.laydate;

        laydate.render({
            elem: '#startTime',//指定元素
            type: 'datetime',
            min: 0,
            format: 'yyyy-MM-dd HH:mm:ss'

        });
        laydate.render({
            elem: '#endTime',//指定元素
            type: 'datetime',
            format: 'yyyy-MM-dd HH:mm:ss',
            done: function (value, date) {
                let $ = layui.$;
                let startTimeStr = $("#startTime").val()
                let limit = $("#timeLimit").val()
                if (startTimeStr === undefined || startTimeStr === "") {
                    layer.alert("请先选择开始时间")
                    form.val("form", { //formTest 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值
                        "startTime": ""
                    });
                    return;
                }
                if (limit === undefined || limit === "") {
                    layer.alert("请先选择时长")
                    form.val("form", { //formTest 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值
                        "endTime": ""
                    });
                    // $("#endTime").val('');
                    return;
                }
                /* let startTime = new Date(startTimeStr);
                 let end_time = new Date(value);
                 if (end_time < startTime) {
                     //  截止时间已过
                     layer.alert("应在开始时间之后")
                     $("#endTime").val("");
                 } else {
                     if (end_time.getMilliseconds() - startTime.getMilliseconds() < limit) {
                         layer.alert("时间不足" + limit + "分钟")
                         return;
                     }
                 }*/
            }
        });
    });

</script>
</body>
</html>
