<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${basePath}/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="${basePath}/lib/font-awesome-4.7.0/css/font-awesome.min.css" media="all">
    <link rel="stylesheet" href="${basePath}/css/public.css" media="all">
    <style>
        body {
            background-color: #ffffff;
        }
        .layui-input{
            width: 250px !important;
        }
        .layui-textarea{
            width: 400px !important;
        }
        .layui-unselect{
            width: 250px;
        }
        .layui-form-radio{
            width: 50px !important;
        }
        .icon-green {color:#00CC00!important;}
    </style>
</head>
<body>
<div class="layui-form layuimini-form">
    <div class="layui-form-item">
        <label class="layui-form-label required">姓名</label>
        <div class="layui-input-inline">
            <input type="text" name="realName" lay-verify="required" lay-reqtext="真实姓名不能为空" placeholder="请输入真实姓名" value="${user.realName}" class="layui-input">
            <input type="hidden" name="id" value="${user.id}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">班级</label>
        <div class="layui-input-inline">
            <select name="className" id="className" lay-filter="mySelect">
                <option value="">请选择班级</option>
                <c:forEach items="${tbClassList}" var="tb">
                    <option value="${tb.id}" <c:if test="${tb.id eq user.classId }">selected="selected"</c:if> >${tb.cname }</option>                               
                </c:forEach>
             </select>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认保存</button>
        </div>
    </div>
</div>
</div>
<script src="${basePath}/lib/jquery-3.4.1/jquery-3.4.1.min.js" charset="utf-8"></script>
<script src="${basePath}/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script src="${basePath}/js/nation.js" charset="utf-8"></script>
<script>
    //获取当前日期
    var now = new Date();
    var endDate = now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate();
    layui.use(['form', 'layedit', 'laydate'], function () {
        var form = layui.form
            , layer = layui.layer
            , laydate = layui.laydate;

        //入学日期
        laydate.render({
            elem: '#intime',
            max:endDate//限制最大选择日期不能超过当前日期
        });

        form.verify({
            idnumber:function(value){
                if(value.length<18){
                    return "请输入合法的身份证号";
                }
            },
            phone:function(value){
                if(value!=null && value.trim()!=''){
                    var reg = /^1\d{10}$/;
                    if(!reg.test(value)){
                        return "请输入正确的手机号码";
                    }
                }
            },
            date:function(value){
                if(value!=null && value.trim()!=''){
                    var reg = /^(\d{4})[-\/](\d{1}|0\d{1}|1[0-2])([-\/](\d{1}|0\d{1}|[1-2][0-9]|3[0-1]))*$/;
                    if(!reg.test(value)){
                        return "日期格式不正确";
                    }
                }
            }
        })

        //监听提交
        form.on('submit(saveBtn)', function (data) {
            console.log(data.field);
            $.ajax({
                type:"POST",
                url:"${basePath}/student/manager/edit/do",
                data:data.field,
                dataType:"text",
                success:function(data){
                    if(data=='ok'){
                        layer.msg("修改成功",{time:1000},function(){//time单位是毫秒
                            //关闭修改页面的弹出窗口
                            var iframeIndex = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(iframeIndex);
                            //刷新父页面
                            window.parent.location.reload();
                        });
                    }else{
                        layer.alert("修改失败");
                    }
                }
            });
            return false;
        });

    });
</script>
</body>
</html>