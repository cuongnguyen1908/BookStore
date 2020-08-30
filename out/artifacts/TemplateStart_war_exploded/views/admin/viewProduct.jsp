<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Product</title>
    </head>
    <body>
        <a href="/admin-edit-product" class="btn btn-sm btn-success text-right mb-3">New</a>


        <form class="ml-auto" action="admin-product-search" method="POST">
            <div class="d-flex">
                <select class="form-control mr-2" style="max-width: 150px;" name="categoryId">
                    <option value="0" selected>All</option>
                    <c:forEach var="item" items="${requestScope.CATEGORYLIST.listResult}">
                        <option value="${item.id}" <c:if test="${param.categoryId == item.id}">selected="selected"</c:if>>${item.name}</option>
                    </c:forEach>
                </select>

                    <div class="form-group" style="max-width: 700px;">
                        <input type="text" class="form-control" name="textSearch" placeholder="Search" value="${param.textSearch}">
                    </div>
            </div>
            <div class="d-flex align-items-center">
                <div class="form-group m-0">
                    <input type="text" class="form-control mr-2" style="max-width: 130px;" name="priceMin" placeholder="Min" value="${param.priceMin}">
                </div>
                <div class="form-group m-0">
                    <input type="text" class="form-control" style="max-width: 130px;"  name="priceMax" placeholder="Max" value="${param.priceMax}">
                </div>
                <button type="submit" class="btn btn-success ml-2">Search</button>

            </div>

        </form>


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
                    <th>No.</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Description</th>
                    <th>Category</th>
                    <th>Photo</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <!--  empty userlist-->

                <c:if test="${empty requestScope.PRODUCTLIST.listResult}">
                <td colspan="9" class="text-center" style="background-color: #eee;">
                    No result to show.
                </td>
            </c:if>

            <!-- not empty userlist-->
            <c:if test="${not empty requestScope.PRODUCTLIST.listResult}">
                <c:forEach var="item" items="${requestScope.PRODUCTLIST.listResult}" varStatus="index">
                    <tr class="text-center">
                        <td>${index.count}</td>
                        <td>${item.title}</td>
                        <td>${item.author}</td>
                        <td class="text-center">${item.quantity}</td>
                        <td class="text-center">${item.price}</td>
                        <td>${item.description}</td>
                        <td>${item.category.name}</td>
                        <td>
                            <img src="/images/${item.photo}"/>
                        </td>
                        <td>

                            <c:if test="${item.status == true}">
                                <span class="badge badge-success">ACTIVE</span>
                            </c:if>
                            <c:if test="${item.status == false}">
                                <span class="badge badge-danger">INACTIVE</span>
                            </c:if>
                        </td>

                        <td>
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
                                            <p>Are you sure to delete ${item.title} ?</p>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-outline-primary" data-dismiss="modal">Cancel</button>
                                            <a href="${deleteURL}" class="btn btn-primary">OK</a>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <!--for create/update-->
                            <c:url var="editURL" value="/admin-edit-product">
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
