<%--
  Created by IntelliJ IDEA.
  User: 许晨
  Date: 2017/12/14
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加博客</title>
    <link href="css/bootstrap.min.css" rel="stylesheet" />
    <style>
        #title{
            height: 4%;
            width: 56%;
            margin-left: 65%;
            margin-top: 15%;
        }
        #content {
            margin-left: 57%;
            margin-top: 6%;
            height: 519px; width: 716px;
        }
        #submit{
            float: right;
            margin-right: -37%;
        }
    </style>
</head>
<body>
    <div class="container-fluid">
        <form action="${pageContext.request.contextPath}/addEssayServlet" method="post">
            <%--标题--%>
            <div class="span12">
                <input type="text" placeholder="标题" id="title" name="title" required="required">
            </div>
            <%--内容--%>
            <div class="span12">
                <textarea name="content" id="content" cols="100" rows="10" placeholder="在此输入内容"></textarea>
            </div>
            <%--按钮--%>
            <div class="span12">
                <input type="submit" class="btn" value="提交" id="submit">
                <input type="button" class="btn" value="返回" onclick="location.href='${pageContext.request.contextPath}/home.jsp'" style="margin-left: 53%">
            </div>
        </form>
    </div>
</body>
</html>
