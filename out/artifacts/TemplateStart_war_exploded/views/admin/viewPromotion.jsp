<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Promotion</title>
    </head>
    <body>

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
                    <th>NO.</th>
                    <th>Full name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Rank</th>
                    <th>Photo</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <!--  empty userlist-->

                <c:if test="${empty USERPROMOTIONLIST.listResult}">
                <td colspan="7" class="text-center" style="background-color: #eee;">
                    No result to show.
                </td>
            </c:if>

            <!-- not empty userlist-->
            <c:if test="${not empty USERPROMOTIONLIST.listResult}">
                <c:forEach var="item" items="${USERPROMOTIONLIST.listResult}" varStatus="index">
                    <form action='<c:url value="/admin-update-rank"/>' method="POST">
                        <tr>
                            <td>${index.count}</td>
                            <td>${item.fullName}</td>
                            <td>${item.email}</td>
                            <td>${item.phone}</td>
                            <th>
                                <select class="form-control mr-2" name="rankId">
                                    <c:forEach var="element" items="${RANKLIST.listResult}">
                                        <option value="${element.id}" 
                                                <c:if test="${item.rank.id == element.id}">selected="selected"</c:if>>
                                            ${element.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </th>

                            <td>
                                <img src="/images/${item.photo}" style="height: 100px; width: 100px;">
                            </td>
                            <td>
                                <input type="hidden" value="${item.id}" name="id"/>
                                <button class="btn btn-sm btn-primary" type="submit">Save</button>

                                <!--for create/update-->
                                <c:url var="deleteURL" value="/admin-delete-promotion">
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
                                                <p>Are you sure to delete assign ${item.fullName} ?</p>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-outline-primary" data-dismiss="modal">Cancel</button>
                                                <a href="${deleteURL}" class="btn btn-primary">OK</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </c:if>
        </tbody>
    </table>
</body>
</html>
