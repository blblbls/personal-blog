<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>用户登录</title>
</head>
<body>

<h2>用户登录</h2>

<form action="${pageContext.request.contextPath}/user/login"
      method="post">

    <p>
        用户名：
        <input type="text" name="username" required>
    </p>

    <p>
        密码：
        <input type="password" name="password" required>
    </p>

    <button type="submit">登录</button>
</form>

<c:if test="${not empty errorMsg}">
    <p style="color:red">${errorMsg}</p>
</c:if>

</body>
</html>
