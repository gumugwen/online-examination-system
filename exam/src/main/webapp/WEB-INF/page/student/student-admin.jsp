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
            <legend>考试记录</legend>
            <div style="margin: 10px 10px 10px 10px">
            <form class="layui-form layui-form-pane" action="">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">学生姓名</label>
                        <div class="layui-input-inline">
                            <input type="text" name="realName" id="realName" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <select name="className" id="className" lay-filter="mySelect">
                            <option value="">请选择班级</option>
                            <c:forEach items="${tbClassList}" var="tb">
                                <option value="${tb.id}">${tb.cname }</option>                               
                            </c:forEach>
                         </select>
                    </div>
                    <div class="layui-inline">
                        <button type="submit" class="layui-btn layui-btn-primary"  lay-submit lay-filter="data-search-btn"><i class="layui-icon"></i> 搜 索</button>
                    </div>
                </div>
            </form>
        </div>
        </fieldset>

        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"> 添加 </button>
                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"> 删除 </button>
                <button class="layui-btn layui-btn-sm layui-btn-normal" id="importExcel"> 批量导入 </button>
            </div>
        </script>

        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
        </script>

    </div>
</div>
<script src="${basePath}/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script>
    layui.use(['jquery','form', 'table','upload'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table,
            upload = layui.upload;
        table.render({
            elem: '#currentTableId',
            url: '${basePath}/student/student_admin',
            toolbar: '#toolbarDemo',
            defaultToolbar: ['filter', 'exports', 'print', {
                title: '导出模板',
                layEvent: 'template',
                icon: 'layui-icon-template-1'
            },{
                title: '批量导入',
                layEvent: 'import',
                icon: 'layui-icon-upload'
            }],
            cols: [[
                { type: "checkbox", width: 100 },
                { field: 'id', width: 200, title: '序号', sort: true, type:'numbers'},
                { field: 'id', width: 120, title: 'ID', sort: true, hide:true},
                { field: 'username', width: 250, title: '用户名' },
                { field: 'realName', width: 250, title: '真实姓名'},
                { field: 'classId', width: 120, title: '班级编号', hide:true},
                { field: 'className', width: 250, title: '班级名称', sort: true },
                { title: '操作', minWidth: 80, toolbar: '#currentTableBar', align: "center" }
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 15,
            page: true,
            skin: 'line',
            done:function(res,curr,count){
                //批量导入Excel
                upload.render({
                    elem : '#importExcel',
                    size: 1024*10,    //限制文件大小，单位 KB
                    accept: 'file', //普通文件
                    field : 'file',
                    acceptMime: 'application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',   // 规定打开文件选择框时，筛选出的文件类型，值为用逗号隔开的 MIME 类型列表。
                    exts: 'xls|xlsx', //允许上传的文件后缀
                    url : '${basePath}/student/manager/excel/import',
                    before:function(){
                        layer.msg('上传中',{
                            icon:16,
                            shade:0.01
                        });
                    },
                    done : function(res) {
                        layer.close(layer.index); //关闭loading
                        if(parseInt(res.code)==0){
                            layer.msg('导入成功！');
                            window.location.reload();
                        }
                    },
                    error:function(){
                        layer.alert('导入失败，请检查导入模板及数据');
                    }
                });
            }
        });

        // 监听搜索操作
        form.on('submit(data-search-btn)', function (data) {
            console.log(data);
            var realName = data.field.realName;
            var className = data.field.className;
            console.log(className);
            table.reload('currentTableId', {
                page: {
                    curr: 1
                }
                , where: {
                    "realName": realName,
                    "className": className,
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
                    content: '${basePath}/student/manager/add',
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            } else if (obj.event === 'delete') {  // 监听删除操作
                var checkStatus = table.checkStatus('currentTableId')
                    , data = checkStatus.data;
                layer.alert(JSON.stringify(data));
            }else if(obj.event === 'template'){
                window.location.href='${basePath}/student/manager/excel_template';
            }
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
                    content: '${basePath}/student/manager/edit?id='+data.id,
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
                return false;
            } else if (obj.event === 'delete') {
                layer.confirm('您确定要删除吗', function (index) {
                    $.ajax({
                        type:"POST",
                        url:"${basePath}/student/manager/delete/do",
                        data:{'id':data.id,'delFlag':1},//delFlag=1表示逻辑删除
                        dataType:"text",
                        success:function(data){
                            if(data=='ok'){
                                layer.msg('删除成功',{time:1000},function(){
                                    obj.del();
                                    layer.close(index);
                                })
                            }else{
                                layer.alert("删除失败",function (){
                                    layer.close(index);
                                });
                            }
                        }
                    });

                });
            }
        });

        table.on('row(currentTableFilter)', function (obj) {
            console.log(obj.tr);
            console.log(obj.data);
            var examId = obj.data.id;
            console.log(examId);
            // $.ajax({
            //     type: "POST",
            //     url: "${basePath}/student/exam/list/judgeStatus",
            //     data: { examId: examId },
            //     dataType: "text",
            //     success: function (data) {
            //         if (data == "0") {
            //             if (obj.data.status == 3) {
            //                 window.location.href = "${basePath}/student/exam/list/begin?exam_id=" + obj.data.id;
            //             } else if (obj.data.status == 1 || obj.data.status == 2) {
            //                 layer.alert("抱歉！该考试未开始！");
            //             } else if (obj.data.status == 4) {
            //                 layer.alert("抱歉！该考试已结束！");
            //             }
            //         } else {
            //             layer.alert("抱歉！您已参加过该考试！");
            //         }
            //     },
            //     error: function (data) {
            //         console.log(data);
            //     },
            // })
            // if ($("#examAduit") == 0) {
            //     if (obj.data.status == 3) {
            //         window.location.href = "${basePath}/student/exam/list/begin?exam_id=" + obj.data.id;
            //     } else if (obj.data.status == 1 || obj.data.status == 2) {
            //         layer.alert("抱歉！该考试未开始！");
            //     } else if (obj.data.status == 4) {
            //         layer.alert("抱歉！该考试已结束！");
            //     }
            // } else {
            //     layer.alert("抱歉！你已经参加过考试！");
            // }
            // console.log("student/exam/list/begin?exam_id=" + obj.data.id);
            // layer.closeAll();
            // window.location.href= "${basePath}/student/exam/list/begin?exam_id="+obj.data.id;
        });

    });
</script>

</body>

</html>