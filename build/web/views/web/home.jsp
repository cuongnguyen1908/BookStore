<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
</head>
<body>
<c:if test="${sessionScope.USERMODEL.getRole().getId() != 1}">

<%--    alert--%>
    <div class="alert alert-${requestScope.TYPE} alert-dismissible fade show mt-3" role="alert">
        <strong>${requestScope.MESSAGE}</strong>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>


        <form class="ml-auto" action="/product-search" method="GET">
            <div class="d-flex">
                <select class="form-control mr-2" style="max-width: 150px;" name="categoryId">
                    <option value="0" selected>All</option>
                    <c:forEach var="item" items="${requestScope.CATEGORYLIST.listResult}">
                        <option value="${item.id}"
                                <c:if test="${param.categoryId == item.id}">selected="selected"</c:if>>${item.name}</option>
                    </c:forEach>
                </select>

                <div class="form-group" style="max-width: 700px;">
                    <input type="text" class="form-control" name="textSearch" placeholder="Search"
                           value="${param.textSearch}">
                </div>
            </div>
            <div class="d-flex align-items-center">
                <div class="form-group m-0">
                    <input type="text" class="form-control mr-2" style="max-width: 130px;" name="priceMin"
                           placeholder="Min"
                           value="${param.priceMin}">
                </div>
                <div class="form-group m-0">
                    <input type="text" class="form-control" style="max-width: 130px;" name="priceMax" placeholder="Max"
                           value="${param.priceMax}">
                </div>
                <button type="submit" class="btn btn-success ml-2">Search</button>
            </div>

        </form>

    <div class="row">
        <c:forEach var="item" items="${requestScope.PRODUCTLIST.listResult}">
        <div class="col-lg-3 col-md-3 mb-4">
            <div class="card h-100">
                <img class="card-img-top" style="height: 300px;"
                     src="/images/${item.photo}" rel="nofollow" alt="Card image cap">
                <div class="card-body d-flex flex-column justify-content-center align-items-center">
                    <h5 class="card-title">${item.title}</h5>
                    <h4>$${item.price}</h4>
                    <form action="/product-cart-add" method="POST">
                        <input type="hidden" name="id" value="${item.id}">
                        <input type="hidden" name="name" value="${item.title}">
                        <input type="hidden" name="quantity" value="${item.quantity}">
                        <input type="hidden" name="price" value="${item.price}">
                        <button class="btn btn-primary">Add to cart</button>
                    </form>
                </div>
            </div>
        </div>
        </c:forEach>
    </div>
</c:if>

<c:if test="${sessionScope.USERMODEL.getRole().getId() == 1 }">
    <h3 class="text-center mt-3">Hello</h3>
</c:if>


</body>
</html>
