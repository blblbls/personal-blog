<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>修改文章</title>
</head>
<body>

<h2>修改文章</h2>

<form action="${pageContext.request.contextPath}/article/update" method="post">
  <input type="hidden" name="id" value="${article.id}">

  标题：<input type="text" name="title" value="${article.title}"><br>
  内容：<textarea name="content">${article.content}</textarea><br>

  <button type="submit">提交修改</button>
  <a href="${pageContext.request.contextPath}/article/list">
    <button type="button">返回列表</button>
  </a>
  <input type="hidden" name="pageNum" value="${param.pageNum}">
  <input type="hidden" name="keyword" value="${param.keyword}">


</form>


</body>
</html>
