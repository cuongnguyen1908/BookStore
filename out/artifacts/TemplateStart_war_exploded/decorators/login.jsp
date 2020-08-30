<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title><dec:title default="Login" /></title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
            <meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no' name='viewport' />
<!--     Fonts and icons     -->
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" />

        <!-- CSS Files -->

      <link href="<c:url value='/template/web/css/material-kit.css?v=2.0.7' />" rel="stylesheet" type="text/css" media="all"/>
</head>
<body class="login-page sidebar-collapse">
    
    <nav class="navbar navbar-transparent navbar-color-on-scroll fixed-top navbar-expand-lg" color-on-scroll="100" id="sectionsNav">
        <div class="container">
            <div class="navbar-translate">
                <a class="navbar-brand" href="#">
                    Back
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="navbar-toggler-icon"></span>
                    <span class="navbar-toggler-icon"></span>
                    <span class="navbar-toggler-icon"></span>
                </button>
            </div>
        </div>
    </nav>

     <div class="page-header header-filter" style="
        background-image: url('./template/login/bg7.jpg');
        background-size: cover;
        background-position: top center;
      ">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-6 ml-auto mr-auto">
                    <div class="card card-login">
                        <dec:body/>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="<c:url value='/template/web/js/jquery.min.js' />"></script>
        <script type="text/javascript" src="<c:url value='/template/web/js/popper.min.js' />"></script>
        <script type="text/javascript" src="<c:url value='/template/web/js/bootstrap-material-design.min.js' />"></script>
        <script type="text/javascript" src="<c:url value='/template/web/js/moment.min.js' />"></script>
</body>
</html>