<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="/css/login.css"/>
</head>
<body>
<div id="login">
    <h1>Login</h1>
    <!--
        表单提交:
            from 表单
                action     目标页面的url      当提交成功时，跳转的目标页面
                method     get   post     网络提交方式
                        例如： get    sucess.html?username='lili'&pwd='1231'
                              post   sucess.html
                onsubmit    return 语句;     若返回值为true或者 ""  均可以提交
    -->
    <form action="/resume/login" method="post">
        <input name="username" type="text" required="required" placeholder="用户名"/>
        <input name="password" type="password" required="required" placeholder="密码"/>
        <button class="but" type="submit">登录</button>
    </form>
</div>
</body>
</html>