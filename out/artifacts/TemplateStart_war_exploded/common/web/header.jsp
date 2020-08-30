<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<nav class="navbar navbar-color-on-scroll navbar-transparent fixed-top navbar-expand-lg" color-on-scroll="100">
    <div class="container">
        <div class="navbar-translate">
            <a class="navbar-brand" href="#">Home</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="sr-only">Toggle navigation</span>
                <span class="navbar-toggler-icon"></span>
                <span class="navbar-toggler-icon"></span>
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ml-auto">
                
                <c:if test="${not empty USERMODEL}">
                    <li class="nav-item">
                        <a class="nav-link" href="">
                            Welcome, ${USERMODEL.fullName}
                        </a>
                    </li>

                </c:if>
                    
                <c:if test="${USERMODEL.getRole().getId() == 1}">
                    <li class="nav-item">
                        <a class="nav-link" href='<c:url value="/admin-home"/>'>
                            Manage
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





                <c:if test="${empty USERMODEL}">
                    <li class="nav-item">
                        <a class="nav-link" href='<c:url value="/login?action=login"/>'>
                            <i class="material-icons">unarchive</i> Login
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
<div class="page-header header-filter" data-parallax="true" style="background-image: url('./template/web/img/bg3.jpg'); height: 500px;">


    <div class="container">
        <div class="row">
            <div class="col-md-8 ml-auto mr-auto">
                <div class="brand text-center">
                    <h1>User management</h1>
                    <h3 class="title text-center">Create by CN</h3>
                </div>
            </div>
        </div>
    </div>
</div>
