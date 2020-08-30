<%-- 
    Document   : viewCart
    Created on : Aug 22, 2020, 10:52:18 PM
    Author     : nguyen
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
    </head>
    <body>
        <h3>Cart View</h3>

        <c:if test="${not empty MESSAGE}">
            <div class="alert alert-success alert-dismissible fade show mt-3" role="alert">
                <strong>${MESSAGE}</strong>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>
        <form action='<c:url value="/admin-delete-item-cart"/>'>
            <table class="table table-hover mt-5" style="text-align: center;">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th style="text-align: left;">Full name</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <!--  empty cart-->

                    <c:if test="${empty CART}">
                    <td colspan="3" class="text-center" style="background-color: #eee;">
                        No result to show.
                    </td>
                </c:if>

                <!-- not empty cart-->
                <c:if test="${not empty CART.cart}">
                    <c:forEach var="item" items="${CART.cart}" varStatus="index">
                        <tr>

                            <td>${index.count}</td>
                            <td style="text-align: left;">${item.value}</td>
                            <td>
                                <div class="checkbox">
                                    <input type="checkbox" name="ids" value="${item.key}" />
                                </div>
                            </td>

                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
            <c:if test="${not empty CART.cart}">
                <button type="submit" class="btn btn-sm btn-danger" style="margin-left: 90%;">Remove</button>
            </c:if>


        </form>
        <c:if test="${not empty CART.cart}">
            <a href='<c:url value="/admin-save-cart"/>' class="btn btn-sm btn-success" style="margin-left: 90%;">Save</a>
        </c:if>

    </body>
</html>
