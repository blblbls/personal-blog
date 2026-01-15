<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>新增文章</title>
</head>
<body>

<h2>新增文章</h2>

<form action="${pageContext.request.contextPath}/article/add" method="post">
    标题：<br/>
    <input type="text" name="title"/><br/><br/>

    内容：<br/>
    <textarea name="content" rows="5" cols="40"></textarea><br/><br/>

    <input type="submit" value="提交"/>
    <a href="${pageContext.request.contextPath}/article/list">
        返回列表
    </a>

</form>

</body>
</html>
