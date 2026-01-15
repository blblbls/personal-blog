<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>错误提示</title>
</head>
<body>

<h2 style="color:red;">系统提示</h2>

<p>
    ${errorMsg}
</p>

<hr>

<a href="${pageContext.request.contextPath}/article/list">
    返回文章列表
</a>

</body>
</html>
