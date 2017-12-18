<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: x3408
  Date: 2017/12/12
  Time: 23:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>首页</title>
    <link href="css/bootstrap.min.css" rel="stylesheet" />
      <script>
          function login() {
              location.href="${pageContext.request.contextPath}/login.jsp";
          }
          function register() {
              location.href="${pageContext.request.contextPath}/register.jsp";
          }
      </script>
  </head>
    <style>
        #home{
            float: left;
            margin-left: 750px;
            margin-top: 300px;
        }
    </style>
  <body>
    <div id="home">
     <input type="button" value="登陆" class="btn" onclick="login()">
     <input type="button" value="注册" class="btn" onclick="register()">
    </div>
  </body>
</html>
