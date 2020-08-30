<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View</title>
    </head>
    <body>
        <a href='<c:url value="/admin-edit"/>' class="btn btn-sm btn-success text-right mb-3">New</a>



        <form class="form-inline ml-auto" action='<c:url value="/admin-search"/>'>
            <select class="form-control mr-2" name="typeRoleId">
                <option value="0" selected>All</option>
                <c:forEach var="item" items="${ROLELIST.listResult}">
                    <option value="${item.id}" <c:if test="${param.typeRoleId == item.id}">selected="selected"</c:if>>${item.name}</option>
                </c:forEach>
            </select>
            <div class="form-group">
                <input type="text" class="form-control" name="textSearch" placeholder="Search" value="${param.textSearch}">
            </div>
            <button type="submit" class="btn btn-success ml-2">Search</button>
        </form>

   
        <!--alert-->
        <c:if test="${not empty MESSAGE}">
            <div class="alert alert-${TYPE} alert-dismissible fade show mt-3" role="alert">
                <strong>${MESSAGE}</strong>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>



        <table class="table table-hover mt-5">
            <thead>
                <tr>
                    <th>Username</th>
                    <th>Full name</th>
                    <th>Role</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <!--  empty userlist-->

                <c:if test="${empty USERLIST.listResult}">
                <td colspan="7" class="text-center" style="background-color: #eee;">
                    No result to show.
                </td>
            </c:if>

            <!-- not empty userlist-->
            <c:url value="/images/logo.png" var="PATHROOT"/>
            <c:if test="${not empty USERLIST.listResult}">
                <c:forEach var="item" items="${USERLIST.listResult}">
                    <tr>
                        <td>${item.username}</td>
                        <td>${item.fullName}</td>
                        <td>${item.role.name}</td>
                        <td>${item.email}</td>
                        <td>${item.phone}</td>

                        <td>
                            <c:if test="${USERMODEL.username != item.username}">
                                <c:url var="deleteURL" value="/admin-delete">
                                    <c:param name="id" value="${item.id}"/>
                                </c:url>

                                <a class="btn btn-sm btn-danger"
                                   href='${deleteURL}' data-toggle="modal" data-target="#deleteModel-${item.id}">Delete
                                </a>  

                                <!-- Modal  -->
                                <div class="modal fade" id="deleteModel-${item.id}" tabindex="-1" role="dialog" aria-labelledby="modelDelete" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="modelDelete">Delete</h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <p>Are you sure to delete ${item.fullName} ?</p>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-outline-primary" data-dismiss="modal">Cancel</button>
                                                <a href="${deleteURL}" class="btn btn-primary">OK</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </c:if>

                            <!--for create/update-->
                            <c:url var="editURL" value="/admin-edit">
                                <c:param name="id" value="${item.id}"/>
                            </c:url>
                            <a class="btn btn-sm btn-primary"
                               href='${editURL}'>Update
                            </a>

                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </tbody>
    </table>
</body>
</html>
