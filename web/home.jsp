<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 许晨
  Date: 2017/12/13
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>博客首页</title>
    <link href="css/bootstrap.min.css" rel="stylesheet" />
    <style>
        #img{
            float: none;
            display: block;
            width: 140px;
            height: 140px;
            margin: 0 auto;
        }

        #content{
            word-wrap: break-word;
            word-break: break-all;
            width: 65%;
            margin-left: 18%;
            padding-top: 1%;
        }
        .pagination{
            margin: 0 auto;
        }
        #logout{
            float: right;
        }
        .span12 h1{
            display: block;
            text-align: center;
        }
        .pagination{
            display: block;
            text-align: center;
        }
        h3,h4 {
            text-align: center;
        }
    </style>
    <script type="text/javascript">
        function logout() {
            location.href = "${pageContext.request.contextPath}/logoutServlet";
        }
        function addEssay() {
            location.href = "${pageContext.request.contextPath}/addEssay.jsp";
        }
    </script>
</head>
<body>
<c:if test="${not empty existUser}">  <%--session域中存在用户--%>
<div class="container-fluid">
        <%--页面头--%>
    <div class="row-fluid">
        <div class="span12">
            <input type="button" value="新增" class="btn-success" onclick="addEssay()">
            <input type="button" class="btn-info" id="logout" value="注销" onclick="logout()">
        </div>
    </div>

        <%--内容--%>
    <div class="row-fluid" >
            <%--个人展示--%>
        <div class="span5">
            <div class="row-fluid" >
                <div class="span12"><img src="./img/${existUser.path}" class="img-circle" id="img"></div>
            </div>
            <div class="row-fluid">
                <div class="span12"><h3 id="username">${existUser.username}</h3></div>
                <div class="span12"><h4 id="hobby">爱好:&nbsp; ${existUser.hobby}</h4></div>
                <div class="span12"><h4 id="speciality">特长:&nbsp; ${existUser.speciality}</h4></div>
            </div>
        </div>



            <%--博客内容--%>
        <c:forEach var="list" items="${blog}">
        <div class="span7">
            <div class="row-fluid">
                <div class="span12">
                    <h1 id="title">${list.title}</h1>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12"  style="height: 527px;">
                    <h3 id="content"><p>${list.content}</p></h3>
                </div>
            </div>

            </c:forEach>



                <%--分页--%>
            <div class="row-fluid">
                <div class="span12">
                    <div class="pagination">
                        <ul>
                            <c:if test="${blogPage.page != 1 && blogPage.totalPage != 0}">
                                <li><a href="${pageContext.request.contextPath}/user_showBlog?page=1">第一篇</a></li>
                                <li><a href="${pageContext.request.contextPath}/user_showBlog?page=${blogPage.page-1}">上一页</a></li>
                            </c:if>
                            <c:forEach var="i" begin="${blogPage.page-2>0?blogPage.page-2:1}"
                                       end="${blogPage.page+3>blogPage.totalPage?blogPage.totalPage:blogPage.page+3}" step="1">
                                <c:if test="${i == blogPage.page}">
                                    <li><a style="background-color: #ddd;">${i}</a></li>
                                </c:if>
                                <c:if test="${i != blogPage.page}">
                                    <li><a href="${pageContext.request.contextPath}/user_showBlog?page=${i}">${i}</a></li>
                                </c:if>
                            </c:forEach>
                            <c:if test="${blogPage.page != blogPage.totalPage && blogPage.totalPage != 0}">
                                <li><a href="${pageContext.request.contextPath}/user_showBlog?page=${blogPage.page+1}">下一篇</a></li>
                                <li><a href="${pageContext.request.contextPath}/user_showBlog?page=${blogPage.totalPage}">最后一篇</a></li>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
    </c:if>
    <c:if test="${empty existUser}">  <%--session域中不存在用户 --%>
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span12">
                    <h1>404</h1>
                </div>
            </div>
        </div>
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span12">
                    <p class="text-center">您要查找的页面不存在或您无权访问,请<a href="${pageContext.request.contextPath}/login.jsp">登陆</a></p>
                </div>
            </div>
        </div>
    </c:if>
</body>
</html>
