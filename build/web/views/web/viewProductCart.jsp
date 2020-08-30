<%-- 
    Document   : viewCart
    Created on : Aug 22, 2020, 10:52:18 PM
    Author     : nguyen
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>View Cart</title>
</head>
<body>
<c:if test="${sessionScope.USERMODEL.role.id == 1}">
    <c:redirect url="/"/>
</c:if>

<c:if test="${empty sessionScope.USERMODEL}">
    <c:redirect url="/"/>
</c:if>



<h3 class="text-center p-5">Shopping Cart</h3>

<c:if test="${not empty requestScope.MESSAGE}">
    <div class="alert alert-${requestScope.TYPE} alert-dismissible fade show mt-3" style="border-radius: 5px;"
         role="alert">
        <strong>${requestScope.MESSAGE}</strong>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
</c:if>
<table class="table table-hover mt-5" style="text-align: center;">
    <thead>
    <tr>
        <th>No.</th>
        <th style="text-align: left;">Name</th>
        <th class="text-left">Quantity</th>
        <th>Price</th>
        <th>Amount</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <!--  empty cart-->
    <c:if test="${empty sessionScope.CARTPRODUCT}">
        <td colspan="5" class="text-center" style="background-color: #eee;">
            No product in cart.
        </td>
    </c:if>

    <!-- not empty cart-->
    <c:if test="${not empty sessionScope.CARTPRODUCT.cart}">
        <c:forEach var="item" items="${sessionScope.CARTPRODUCT.cart}" varStatus="index">
            <tr>

                <td>${index.count}</td>
                <td style="text-align: left;">${item.value.name}</td>
                <td>
                    <div class="d-flex">
                        <form action="/product-cart-update" method="GET">
                            <div class="form-group p-0 text-center" style="width: 70px;">
                                <input type="number" min="1" class="form-control" id="quantity"
                                       name="quantity" value="${item.value.quantity}">
                            </div>
                            <input type="hidden" name="id" value="${item.key}">
                            <button type="submit" class="btn btn-sm btn-secondary">Update</button>
                        </form>
                    </div>
                </td>
                <td>${item.value.price}</td>
                <td>
                        ${item.value.price * item.value.quantity}
                </td>
                <td>
                    <input type="hidden" name="id" value="${item.key}">
                    <button style="background-color: transparent; border: none; cursor: pointer;" type="button"
                            data-toggle="modal" data-target="#deleteModel-${item.key}">
                        <span class="material-icons" style="color: black;">delete_outline</span>
                    </button>

                    <!-- Modal  -->
                    <div class="modal fade" id="deleteModel-${item.key}" tabindex="-1" role="dialog"
                         aria-labelledby="modelDelete" aria-hidden="true">
                        <div class="modal-dialog" style="margin-top: 150px;" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="modelDelete">Delete</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <p>Are you sure to delete ${item.value.name} ?</p>
                                </div>
                                <div class="modal-footer d-flex">
                                    <c:url var="deleteURL" value="/delete-item-cart">
                                        <c:param name="id" value="${item.key}"/>
                                    </c:url>
                                    <a href="${deleteURL}" class="btn btn-outline-primary">OK</a>


                                    <button type="button" class="btn btn-outline-primary" data-dismiss="modal">
                                        Cancel
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>
<c:if test="${not empty requestScope.TOTAL}">
    <h4 class="text-right">Total: $${requestScope.TOTAL}</h4>
</c:if>

<c:if test="${not empty requestScope.DISCOUNT}">
    <h4 class="text-right">Discount: $${requestScope.DISCOUNT}</h4>
</c:if>

<c:if test="${not empty requestScope.AFTERAPPLY}">
    <h4 class="text-right">Final: $${requestScope.AFTERAPPLY}</h4>
</c:if>    
    
<c:if test="${not empty requestScope.TOTAL}">
    <form action="/apply-code" method="POST">
        <input type="text" name="code" value="${param.code}">
        <input type="hidden" name="total" value="${requestScope.TOTAL}">
        <input type="hidden" name="id" value="${sessionScope.USERMODEL.id}">
        <button type="submit" class="btn btn-primary">Apply</button>
    </form>
</c:if>
    

<c:if test="${not empty sessionScope.CARTPRODUCT.cart}">\
    <form action="/save-cart" method="POST">
        <input type="hidden" name="id" value="${sessionScope.USERMODEL.id}"/>
        <input type="hidden" name="codeId" value="${requestScope.IDCODE}"/>
        <input type="hidden" name="total" value="${requestScope.TOTAL}"/>
        <input type="hidden" name="finalTotal" value="${requestScope.AFTERAPPLY}"/>
        <button type="submit" class="btn btn-sm btn-success" style="margin-left: 90%;">
            Order
        </button>
    </form>
</c:if>

</body>
</html>
