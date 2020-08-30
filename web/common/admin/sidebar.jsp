<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>


<!-- Sidebar -->
        <ul class="navbar-nav sidebar sidebar-light accordion" id="accordionSidebar">
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href='<c:url value="/admin-home"/>'>
                <div class="sidebar-brand-icon">
                    <img src="./template/admin/img/logo.png">
                </div>
                <div class="sidebar-brand-text mx-3">Admin Manager</div>
            </a>
            <hr class="sidebar-divider my-0">
            <li class="nav-item active">
                <a class="nav-link" href='<c:url value="/"/>'>
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Home</span></a>
            </li>
            
            <hr class="sidebar-divider">
            <div class="sidebar-heading">
                Features
            </div>
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUser" aria-expanded="true" aria-controls="collapseBootstrap">
                    <span>User Manager</span>
                </a>
                <div id="collapseUser" class="collapse" aria-labelledby="headingBootstrap" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">USERS</h6>
                        <a class="collapse-item" href='<c:url value="/admin-view"/>'>View all</a>
                    </div>
                </div>
            </li>
            
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseProduct" aria-expanded="true" aria-controls="collapseBootstrap">
                    <span>Product Manager</span>
                </a>
                <div id="collapseProduct" class="collapse" aria-labelledby="headingBootstrap" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">PRODUCTS</h6>
                        <a class="collapse-item" href='<c:url value="/admin-product-view"/>'>View all</a>
                        <a class="collapse-item" href='<c:url value="/admin-code"/>'>Code</a>
                    </div>
                </div>
            </li>
        </ul>