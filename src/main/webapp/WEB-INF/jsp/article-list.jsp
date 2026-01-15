<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>文章列表</title>
</head>

<body>

<c:if test="${not empty error}">
    <div style="color:red; margin-bottom:10px;">
            ${error}
    </div>
</c:if>

<c:if test="${not empty param.error}">
    <div style="color:red; margin-bottom:10px;">
            ${param.error}
    </div>
</c:if>

<form action="${pageContext.request.contextPath}/article/list" method="get">
    <input type="text" name="keyword"
           value="${keyword}"
           placeholder="请输入标题关键字">
    <button type="submit">搜索</button>
</form>

<hr>

<h2>文章列表</h2>

<a href="${pageContext.request.contextPath}/article/add">新增文章</a>

<hr>


<c:if test="${not empty success}">
    <div style="color:green; margin-bottom:10px;">
            ${success}
    </div>
</c:if>

<c:if test="${not empty error}">
    <div style="color:red; margin-bottom:10px;">
            ${error}
    </div>
</c:if>

<table border="1">
    <tr>
        <th>ID</th>
        <th>标题</th>
        <th>内容</th>
    </tr>

    <c:forEach items="${pageInfo.list}" var="a">

    <tr>
            <td>${a.id}</td>
        <td>
            <a href="${pageContext.request.contextPath}/article/detail?id=${a.id}">
                    ${a.title}
            </a>
        </td>

        <td>${a.content}</td>
            <td>
                <a href="${pageContext.request.contextPath}/article/edit?id=${a.id}">
                    修改
                </a>

                |

                <form action="${pageContext.request.contextPath}/article/delete"
                      method="post"
                      style="display:inline;"
                      onsubmit="return confirm('确定要删除这篇文章吗？');">

                    <input type="hidden" name="id" value="${a.id}">
                    <input type="hidden" name="pageNum" value="${pageInfo.pageNum}">
                    <input type="hidden" name="keyword" value="${keyword}">
                    <input type="hidden" name="csrfToken"
                           value="${sessionScope.csrfToken}">

                    <button type="submit">删除</button>
                </form>





            </td>
        </tr>
    </c:forEach>


</table>
<hr>

<div>
    当前第 ${pageInfo.pageNum} 页 /
    共 ${pageInfo.pages} 页，
    共 ${pageInfo.total} 条
</div>

<div>
    <c:if test="${pageInfo.hasPreviousPage}">
        <a href="${pageContext.request.contextPath}/article/list?pageNum=${pageInfo.prePage}
                 &keyword=${keyword}">
            上一页
        </a>
    </c:if>

    <c:forEach begin="1" end="${pageInfo.pages}" var="p">
        <c:choose>
            <c:when test="${p == pageInfo.pageNum}">
                <strong>${p}</strong>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/article/list
                         ?pageNum=${p}
                         &keyword=${keyword}">
                        ${p}
                </a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:if test="${pageInfo.hasNextPage}">
        <a href="${pageContext.request.contextPath}/article/list?pageNum=${pageInfo.nextPage}
                 &keyword=${keyword}">
            下一页
        </a>
    </c:if>
</div>


</body>
</html>
