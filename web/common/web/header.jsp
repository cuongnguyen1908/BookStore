<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<nav class="navbar navbar-color-on-scroll navbar-transparent fixed-top navbar-expand-lg" color-on-scroll="100">
    <div class="container">
        <div class="navbar-translate">
            <a class="navbar-brand" href="/">Home</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="sr-only">Toggle navigation</span>
                <span class="navbar-toggler-icon"></span>
                <span class="navbar-toggler-icon"></span>
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ml-auto">
                
                <c:if test="${not empty sessionScope.USERMODEL}">
                    <li class="nav-item">
                        <a class="nav-link" href="">
                            Welcome, ${sessionScope.USERMODEL.fullName}
                        </a>
                    </li>

                </c:if>
                    
                <c:if test="${sessionScope.USERMODEL.role.id == 1}">
                    <li class="nav-item">
                        <a class="nav-link" href="/views/admin/home.jsp">
                            Manage
                        </a>
                    </li>
                </c:if>

                <c:if test="${empty USERMODEL}">
                    <li class="nav-item">
                        <a class="nav-link" href='<c:url value="/login?action=login"/>'>
                            <i class="material-icons">unarchive</i> Login
                        </a>
                    </li>
                </c:if>

<c:if test="${sessionScope.USERMODEL.role.id == 2}">
                <li class="nav-item">
                    <a class="nav-link" href="/calculate-cart-product">
                        <i class="material-icons">unarchive</i> View cart
                        <c:if test="${sessionScope.CARTPRODUCT.cart.size() > 0}">(${sessionScope.CARTPRODUCT.cart.size()})</c:if>
                    </a>
                </li>
</c:if>

                <c:if test="${not empty USERMODEL}">
                    <li class="nav-item">
                        <a class="nav-link" href='<c:url value="/logout?action=logout"/>'>
                            <i class="material-icons">unarchive</i> Logout
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
<div class="page-header header-filter" data-parallax="true" style="background-image: url('/template/web/img/bg3.jpg'); height: 500px;">


    <div class="container">
        <div class="row">
            <div class="col-md-8 ml-auto mr-auto">
                <div class="brand text-center">
                    <h1>Book Store</h1>
                </div>
            </div>
        </div>
    </div>
</div>
