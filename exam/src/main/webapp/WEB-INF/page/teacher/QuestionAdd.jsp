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
    <title>出题</title>
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

<div class="layui-row">
    <div class="layui-col-md12">
        <div class="grid-demo grid-demo-bg1">
            <div class="layui-form layuimini-form" lay-filter="formAdd">
                <div class="layui-form-item">
                    <label class="layui-form-label" for="type">类型：</label>
                    <div class="layui-input-block" id="type">
                        <input type="radio" name="type" lay-filter="filter" value="1" title="单选">
                        <input type="radio" name="type" lay-filter="filter" value="2" title="多选" checked>
                        <input type="radio" name="type" lay-filter="filter" value="3" title="判断" checked>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label required" for="title">题目</label>
                    <div class="layui-input-block">
                        <input type="text" name="title" id="title" lay-verify="required" lay-reqtext="考卷名不能为空"
                               placeholder="请输入题目名" value="" class="layui-textarea">
                    </div>
                </div>
                <div class="layui-form-item" hidden>
                    <label class="layui-form-label required" for="titleId">题目id</label>
                    <div class="layui-input-block">
                        <input type="text" name="titleId" id="titleId"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item" hidden>
                    <label class="layui-form-label" for="examId">id</label>
                    <input type="text" name="examId" id="examId" lay-filter="examIdFilter"
                           value="" class="layui-textarea">
                </div>

                <div id="judgeDiv">
                    <div class="layui-form-item">

                        <label class="layui-form-label">答案：</label>
                        <div class="layui-input-block">
                            <input type="radio" name="judgeAnswer" value="1" title="正确" checked="">
                            <input type="radio" name="judgeAnswer" value="2" title="错误">
                        </div>
                    </div>
                </div>

                <div id="singleMultiDiv" class="layui-row layui-col-space1">
                    <div class="layui-col-md3">
                        <div class="grid-demo grid-demo-bg1">
                            <div class="layui-form-item">
                                <label class="layui-form-label " for="singleOptionA">选项A：</label>
                                <input type="text" name="optionA" id="singleOptionA"
                                       placeholder="请输入选项A" value="" class="layui-input">
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-md3">
                        <div class="grid-demo">
                            <div class="layui-form-item">
                                <label class="layui-form-label" for="singleOptionB">选项B：</label>
                                <input type="text" name="optionB" id="singleOptionB"
                                       placeholder="请输入选项B" value="" class="layui-input">
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-md3">
                        <div class="grid-demo grid-demo-bg1">
                            <div class="layui-form-item">
                                <label class="layui-form-label" for="singleOptionC">选项C：</label>
                                <input type="text" name="optionC" id="singleOptionC"
                                       placeholder="请输入选项C" value="" class="layui-input">
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-md3">
                        <div class="grid-demo">
                            <div class="layui-form-item">
                                <label class="layui-form-label required" for="singleOptionD">选项D：</label>
                                <input type="text" name="optionD" id="singleOptionD"
                                       placeholder="请输入选项D" value="" class="layui-input">
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-md3">
                        <div class="grid-demo">
                            <div class="layui-form-item">
                                <label class="layui-form-label required" for="singleMultiAnswer">答案：</label>
                                <input type="text" name="singleMultiAnswer" id="singleMultiAnswer"
                                       placeholder="多个选项用英文逗号分割" value="" class="layui-input">
                            </div>
                        </div>
                    </div>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label required" for="score">分数：</label>
                    <div class="layui-input-block">

                        <input type="text" name="score" id="score" lay-verify="required" lay-reqtext="请输入分数"
                               placeholder="请输入分数" value="" class="layui-input">
                    </div>
                </div>


                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">提交</button>
                    </div>
                </div>
            </div>

        </div>
    </div>

</div>

<div class="layuimini-container">
    <div class="layuimini-main">

        <fieldset class="table-search-fieldset">
            <legend>搜索当前考卷试题</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="" lay-filter="searchForm">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">题目内容</label>
                            <div class="layui-input-inline">
                                <input type="text" name="title" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item" hidden>
                            <label class="layui-form-label" for="examIdSearch">id</label>
                            <input type="text" name="examId" id="examIdSearch" lay-filter="examIdFilter"
                                   value="" class="layui-textarea">
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">类型</label>
                            <div class="layui-input-block">
                                <select name="status" id="status" lay-filter="aihao">
                                    <option value="-1">全部</option>
                                    <option value="1">单选题</option>
                                    <option value="2">多选题</option>
                                    <option value="3">判断题</option>
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
                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"> 删除</button>
                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" id="importExcel" lay-event="in"> 导入</button>
                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="export"> 导出模板</button>
            </div>
        </script>

        <table class="layui-hide" id="questionTable" lay-filter="currentTableFilter"></table>




    </div>
</div>
<script src="../../../lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script src="../../../js/lay-config.js?v=1.0.4" charset="utf-8"></script>

<script>
    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);//search,查询？后面的参数，并匹配正则
        if (r != null) return unescape(r[2]);
        return null;
    }

    layui.use(['form'], function () {
        let form = layui.form,
            layer = layui.layer,
            $ = layui.$;

        form.on('radio(filter)', function (data) {
            console.log(data.elem); //得到radio原始DOM对象
            console.log(data.value); //被点击的radio的value值
            if (data.value === '1' || data.value === '2') {
                $("#judgeDiv").hide();
                $("#singleMultiDiv").show()
            } else {
                $("#singleMultiDiv").hide()
                $("#judgeDiv").show();
            }
        });
        var typeVal = form.val("formAdd", "type");
        if (typeVal === '1' || typeVal === '2') {
            $("#judgeDiv").hide();
            $("#singleMultiDiv").show()
        } else {
            $("#singleMultiDiv").hide()
            $("#judgeDiv").show();
        }
        $("#examId").val(getQueryString("exam_id"))
        $("#examIdSearch").val(getQueryString("exam_id"))

        //监听提交
        form.on('submit(saveBtn)', function (data) {
            let exam = data.field;
            console.log(exam)
            if (exam.titleId === undefined || exam.titleId === '') {
                //新增
                $.ajax({
                    type: "POST",
                    url: "${basePath}/exam/addQuestion",
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
                        // location.reload();
                    },
                })
            } else {
                $.ajax({
                    type: "POST",
                    url: "${basePath}/exam/updateQuestion",
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
                                location.reload();

                            });
                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        // location.reload();
                    },
                })
            }


            return false;
        });

    });

    let examId = getQueryString("exam_id")
    /**
     * table 和搜索
     */
    layui.use(['form','upload', 'table'], function () {
        let $ = layui.jquery,
            form = layui.form,
            upload = layui.upload,
            table = layui.table;

        table.render({
            elem: '#questionTable',
            url: '${basePath}/question/single/teacherQs',
            toolbar: '#toolbarDemo',
            defaultToolbar: ['filter', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            cols: [[
                {type: "checkbox", width: 50},
                {field: 'id', width: 80, title: 'ID', sort: true},
                {field: 'title', width: 150, title: '题目'},
                {field: 'type', width: 80, title: '类型', sort: true},
                {field: 'judgeAnswer', width: 200, title: '判断题答案', sort: true},
                {field: 'optiona', width: 200, title: '选项A', sort: true},
                {field: 'optionb', width: 200, title: '选项B', sort: true},
                {field: 'optionc', width: 200, title: '选项C', sort: true},
                {field: 'optiond', width: 200, title: '选项D', sort: true},
                {field: 'answer', width: 200, title: '答案', sort: true},
                {field: 'score', width: 200, title: '分数', sort: true},
                {field: 'delFlag', title: '状态', minWidth: 150},
                {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
            ]],
            contentType: 'application/json',
            method: 'post',
            where: {"examId": examId},
            limits: [3, 5, 10, 15, 20, 50],
            limit: 5,
            page: true,
            skin: 'line',
            done: function (res, curr, count) {
                upload.render({
                    elem:"#importExcel",
                    size: 1024*10,
                    accept: 'file',
                    field: 'file',
                    acceptMime:'',
                    exts:'xls|xlsx',
                    url: '${basePath}/excel/import/question?examId='+examId,
                    before: function () {
                        layer.msg("上传中",{
                            icon:16,
                            shade:0.01
                        });
                    },
                    done:function (res) {
                        layer.close(layer.index);
                        console.log(res)
                        if(res.flag){
                            layer.msg("导入成功!");
                            window.reload()
                        }
                    },
                    error: function () {
                        layer.alert("导入失败，请检查模板及数据")
                    }

                })
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                console.log(res);
                $("[data-field = 'type']").children().each(function () {

                    if ($(this).text() === '1') {

                        $(this).text("单选题");

                    } else if ($(this).text() === '2') {

                        $(this).text("多选题");
                    } else if ($(this).text() === '3') {
                        $(this).text("判断题");
                    }
                })

                $("[data-field = 'delFlag']").children().each(function () {

                    if ($(this).text() == 'true') {

                        $(this).text("已删除");

                    } else if ($(this).text() == 'false') {

                        $(this).text("正常");
                    }
                })
                $("[data-field = 'judgeAnswer']").children().each(function () {

                    console.log($(this).text())
                    if ($(this).text() === 'true') {
                        $(this).text("对");

                    } else if ($(this).text() === 'false') {
                        $(this).text("错");
                    }
                })
                //得到当前页码
                console.log(curr);

                //得到数据总量
                console.log(count);
            }
        });

        //监听行双击事件
        table.on('rowDouble(currentTableFilter)', function (obj) {
            console.log(obj.tr) //得到当前行元素对象
            console.log(obj.data) //得到当前行数据
            let objData = obj.data;
            form.val("formAdd", {
                "titleId": objData.id,
                "type": objData.type,
                "title": objData.title,
                "score": objData.score
            })
            if (objData.type == '3') {
                //判断
                form.val("formAdd", {
                    "judgeAnswer": objData.judgeAnswer ? '1' : '0'
                })
                $("#singleMultiDiv").hide()
                $("#judgeDiv").show();
            } else {
                form.val("formAdd", {
                    "optionA": objData.optiona,
                    "optionB": objData.optionb,
                    "optionC": objData.optionc,
                    "optionD": objData.optiond,
                    "singleMultiAnswer": objData.answer,
                })
                $("#judgeDiv").hide();
                $("#singleMultiDiv").show()
            }

            //obj.del(); //删除当前行
            //obj.update(fields) //修改当前行数据
        });
        // 监听搜索操作
        form.on('submit(data-search-btn)', function (data) {
            let result = JSON.stringify(data.field);
            //执行搜索重载
            table.reload('questionTable', {
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
            } else if (obj.event === 'delete') {  // 监听删除操作
                layer.confirm('真的删除行么', function (index) {
                    let checkStatus = table.checkStatus('questionTable')
                        , delData = checkStatus.data;

                    console.log(delData[0])
                    console.log(JSON.stringify(delData[0]))
                    console.log(JSON.parse(JSON.stringify(delData[0])))
                    $.ajax({
                        type: "POST",
                        url: "${basePath}/question/single/teacher/del",
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

            } else if (obj.event === 'export') { // 监听模板导出
                console.log("e")
                window.location.href="${basePath}/excel/export/ques_template?examId="+examId
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
            } else if (obj.event === 'delete') {
                layer.confirm('真的删除行么', function (index) {
                    obj.del();
                    layer.close(index);
                });
            } else if (obj.event === 'addQuestion') {
                //添加问题
                console.log(data)
                window.location = "/teacher/toQuestionAdd?exam_id=" + data.id;
            }
        });

    });


</script>
</body>
</html>
