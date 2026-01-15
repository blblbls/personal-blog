<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>文章详情</title>
</head>
<body>

<h2>${article.title}</h2>

<p>
  作者：${article.username}
  |
  发布时间：${article.createTime}
</p>

<hr>

<div>
  ${article.content}
</div>

<hr>

<a href="${pageContext.request.contextPath}/article/list">
  返回列表
</a>

<hr>

<h3>评论列表</h3>

<c:if test="${empty comments}">
  <p>暂无评论</p>
</c:if>

<c:forEach items="${comments}" var="c">
  <div style="border-bottom:1px solid #ccc; margin-bottom:10px;">
    <strong>${c.username}</strong> 说：
    <p>${c.content}</p>
    <small>${c.createTime}</small>

    <c:if test="${not empty sessionScope.loginUser
        && (sessionScope.loginUser.role eq 'ADMIN'
        || sessionScope.loginUser.id eq c.userId)}">

      <form action="${pageContext.request.contextPath}/comment/delete"
            method="post" style="display:inline;">
        <input type="hidden" name="id" value="${c.id}">
        <input type="hidden" name="articleId" value="${article.id}">
        <button type="submit"
                onclick="return confirm('确定删除该评论？')">
          删除
        </button>
      </form>

    </c:if>
  </div>
</c:forEach>


<hr>

<h3>发表评论</h3>

<!-- ⭐ 已登录 -->
<c:if test="${not empty sessionScope.loginUser}">
  <form action="${pageContext.request.contextPath}/comment/add" method="post">

    <input type="hidden" name="articleId" value="${article.id}">

    <textarea name="content"
              rows="4"
              cols="50"
              placeholder="请输入评论内容"
              required></textarea>
    <br><br>

    <button type="submit">提交评论</button>
  </form>
</c:if>

<!-- ⭐ 未登录 -->
<c:if test="${empty sessionScope.loginUser}">
  <p style="color:red">
    请先 <a href="${pageContext.request.contextPath}/user/login">登录</a> 后发表评论
  </p>
</c:if>

</body>
</html>
