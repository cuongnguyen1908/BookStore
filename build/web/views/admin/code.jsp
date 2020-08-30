<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Code</title>
    </head>
    <body>
        <a href="views/admin/editCode.jsp" class="btn btn-sm btn-success text-right mb-3">New</a>

   
        <!--alert-->
        <c:if test="${not empty requestScope.MESSAGE}">
            <div class="alert alert-${requestScope.TYPE} alert-dismissible fade show mt-3" role="alert">
                <strong>${requestScope.MESSAGE}</strong>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>



        <table class="table table-hover mt-5">
            <thead>
                <tr>
                    <th>Code</th>
                    <th>Percent number</th>
                    <th>Date</th>
                    <th>Create date</th>
                </tr>
            </thead>
            <tbody>
                <!--  empty CODELIST-->

                <c:if test="${empty requestScope.CODELIST.listResult}">
                <td colspan="4" class="text-center" style="background-color: #eee;">
                    No result to show.
                </td>
            </c:if>

            <!-- not empty CODELIST-->
            <c:if test="${not empty requestScope.CODELIST.listResult}">
                <c:forEach var="item" items="${requestScope.CODELIST.listResult}">
                    <tr>
                        <td>${item.code}</td>
                        <td>${item.percent}</td>
                        <td>${item.date}</td>
                        <td>${item.createdDate}</td>
                    </tr>
                </c:forEach>
            </c:if>
        </tbody>
    </table>
</body>
</html>
