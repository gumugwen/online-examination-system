<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>教师修改</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${basePath}/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="${basePath}/css/public.css" media="all">
    <style>
        body {
            background-color: #ffffff;
        }
        .layui-input{
            width: 250px !important;
        }
    </style>
</head>
<body>
<div class="layui-form layuimini-form">
    <div class="layui-form-item">
        <label class="layui-form-label required">用户名</label>
        <div class="layui-input-block">
            <input type="text" name="username" id="username" lay-verify="required" lay-reqtext="用户名不能为空" placeholder="请输入用户名" value="${teacher.cno}" class="layui-input">
            <input type="hidden" id="id" name="id" value="${teacher.id}">
            <tip>填写用户名。</tip>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">密码</label>
        <div class="layui-input-block">
            <input type="text" name="password" id="password" lay-verify="required" lay-reqtext="密码不能为空" placeholder="请输入密码" value="${teacher.cname}" class="layui-input">
            <tip>填写密码。</tip>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认修改</button>
        </div>
    </div>
</div>
<script src="${basePath}/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script>
    layui.use(['jquery','form'], function () {
        var form = layui.form,
            layer = layui.layer,
            $ = layui.jquery;

        //监听提交
        form.on('submit(saveBtn)', function (data) {
            if(data.field.cno=='${teacher.cno}'&&data.field.cname=='${teacher.cname}'){
                layer.alert("全部没有变更");
                return false;
            }
            $.ajax({
                type:"POST",
                url:"${basePath}/admin/teacher/edit/do",
                data:data.field,
                dataType:"text",
                success:function(data){
                    console.log(data);
                    if(data=='ok'){
                        layer.msg("修改成功",function(){
                            //关闭修改页面的弹出窗口
                            var iframeIndex = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(iframeIndex);
                            //刷新父页面
                            window.parent.location.reload();
                        });

                    }else if(data=='no'){
                        layer.alert("重复，请重新输入",function(index){
                            $("#name").focus();
                            layer.close(index);
                        })
                    }else{
                        layer.alert("修改失败");
                    }
                },
                error:function (data){
                    console.log(data);
                }
            });

            return false;
        });

    });
</script>
</body>
</html>