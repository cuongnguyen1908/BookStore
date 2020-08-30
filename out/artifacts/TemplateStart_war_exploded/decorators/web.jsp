<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title><dec:title default="Home" /></title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
            <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    
        <!-- font, icon -->
     <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">

    <!-- css -->
    <link href="<c:url value='/template/web/bootstrap/css/bootstrap.min.css' />" rel="stylesheet" type="text/css" media="all"/>
    <link href="<c:url value='/template/web/css/material-kit.css?v=2.0.7' />" rel="stylesheet" type="text/css" media="all"/>
    
</head>
<body>
	<!-- header start -->
    <%@ include file="/common/web/header.jsp" %>
    <!-- header end -->
    
    
                    
    <div class="main main-raised">
        <div class="container">
            <div class="section text-center" style="height: 1000px;">
                
                
                <dec:body/>

            </div>
        </div>
    </div>

	<!-- footer -->
	<%@ include file="/common/web/footer.jsp" %>
	<!-- footer -->
	
	<script type="text/javascript" src="<c:url value='/template/web/js/jquery.min.js' />"></script>
        <script type="text/javascript" src="<c:url value='/template/web/js/popper.min.js' />"></script>
        <script src="<c:url value='/template/web/js/material-kit.js' />" type="text/javascript"></script>
        <script type="text/javascript" src="<c:url value='/template/web/js/bootstrap-material-design.min.js' />"></script>
        <script type="text/javascript" src="<c:url value='/template/web/js/moment.min.js' />"></script>
	
</body>
</html>