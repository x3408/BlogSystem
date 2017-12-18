<%--
  Created by IntelliJ IDEA.
  User: x3408
  Date: 2017/12/12
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>登陆</title>
    <link href="css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
<form id="login" class="form-horizontal" action="${pageContext.request.contextPath}/loginServlet" method="post" style="margin-left: 650px; margin-top: 200px">
    <div class="control-group">
        <label class="control-label" for="username" >用户名</label>
        <div class="controls">
            <input type="text" id="username" name="username" placeholder="用户名">
        </div>
    </div>
    <div class="control-group">
        <label class="control-label" for="inputPassword">Password</label>
        <div class="controls">
            <input type="password" id="inputPassword" name="inputPassword" placeholder="Password">
        </div>
    </div>
    <div class="control-group">
        <div class="controls">
            <label class="checkbox">
                <input type="checkbox" name="login" value="true"> 自动登录(一分钟有效时间)
            </label>
            <button type="submit" class="btn">登陆</button>
            <span style="color: red;">${msg}</span>
        </div>
    </div>
</form>
</body>
</html>
