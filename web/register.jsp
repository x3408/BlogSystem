<%--
  Created by IntelliJ IDEA.
  User: 许晨
  Date: 2017/12/13
  Time: 8:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <link href="css/bootstrap.min.css" rel="stylesheet" />
    <script src="js/jquery-1.8.3.js" type="text/javascript"></script>
    <script type="text/javascript">
        function check() {
            /*var password = document.getElementById("password").value;
            var confirmPassword = document.getElementById("confirmPassword").value;

            if(confirmPassword != password){
                alert("两次密码输入的不一致");
                $("#password").val("");
                $("#confirmPassword").val("");
                return false;
            }*/
            var passwordInfo = "";
            var password = $("#password").val();
            var confirmPassword = $("#confirmPassword").val();

            if(password != confirmPassword) {
                passwordInfo = "两次输入的密码不一致";
                $("#passwordInfo").css("color", "red");
            } else {
                passwordInfo = "密码一致";
                $("#passwordInfo").css("color", "green");
            }
            $("#passwordInfo").html(passwordInfo);
        }
        function checkUser() {
            var username = $("#username").val();
            $.post(
                "${pageContext.request.contextPath}/checkUserServlet",
                {"username":username},
                function (data){
                    var isExist = data.isExist;
                    var usernameInfo = "";
                    if(isExist) {
                        usernameInfo = "该用户名已经被注册!";
                        $("#usernameInfo").css("color", "red");
                    } else {
                        usernameInfo = "该用户名可以使用!";
                        $("#usernameInfo").css("color", "green");
                    }

                    $("#usernameInfo").html(usernameInfo);
                },
                "json"
            );
        }
        function flag() {
            var password = $("#passwordInfo").html();
            var user = $("#usernameInfo").html();
            if(user != "该用户名可以使用!" || password != "密码一致") {
                return false;
            }
        }
    </script>
</head>
<body>
<%--提交时判断是否密码相同，否则不能提交--%>
<form id="login" class="form-horizontal" action="${pageContext.request.contextPath}/registerServlet"
      method="post" enctype="multipart/form-data" onsubmit="return flag()" style="margin-left: 650px; margin-top: 200px">
        <%--用户名--%>
    <div class="control-group">
        <label class="control-label" for="username">用户名</label>
        <div class="controls">
            <input type="text" id="username" name="username" placeholder="用户名" onblur="checkUser()">
            <span id="usernameInfo"></span>
        </div>
    </div>
        <%--密码--%>
    <div class="control-group">
        <label class="control-label" for="password">Password</label>
        <div class="controls">
            <input type="password" id="password" name="password" placeholder="Password">
        </div>
    </div>
        <%--确认密码--%>
    <div class="control-group">
        <label class="control-label" for="confirmPassword">rePassword</label>
        <div class="controls">
            <input type="password" id="confirmPassword" placeholder="Password" onblur="check()">
            <span id="passwordInfo"></span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label" for="hobby">爱好</label>
        <div class="controls">
            <input type="text" id="hobby" name="hobby" placeholder="hobby">
        </div>
    </div>
    <div class="control-group">
        <label class="control-label" for="speciality">特长</label>
        <div class="controls">
            <input type="text" id="speciality" name="speciality" placeholder="speciality">
        </div>
    </div>
    <div class="control-group">
        <label class="control-label" for="head">上传头像</label>
        <div class="controls">
            <input type="file" id="head" name="head">
        </div>
    </div>
    <div class="control-group">
        <div class="controls">
            ${msg}
            <button type="submit" class="btn" id="register">注册</button>
        </div>
    </div>
</form>
</body>
</html>
