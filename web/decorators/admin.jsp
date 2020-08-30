<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
    <head>

        <title><dec:title default="Home" /></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" type="text/css" href="<c:url value='/template/admin/css/bootstrap.min.css'/>" />
        <link rel="stylesheet" href="<c:url value='/template/admin/css/ruang-admin.min.css' />" />
        <link rel="stylesheet" type="text/css" href="<c:url value='/template/admin/css/all.min.css'/>" />
        <link rel="stylesheet" type="text/css" href="<c:url value='/template/admin/css/bootstrap-datepicker.min.css'/>" />
        <script src="<c:url value='/template/admin/js/jquery.min.js' />"></script>


    </head>
    <body id="page-top">
        <div id="wrapper">
            <!-- Sidebar start-->
            <%@ include file="/common/admin/sidebar.jsp" %>
            <!-- Sidebar end -->


            <div id="content-wrapper" class="d-flex flex-column">
                <div id="content">
                    <!-- TopBar -->
                    <%@ include file="/common/admin/topbar.jsp" %>
                    <!-- Topbar -->

                    <!-- Container Fluid-->
                    <div class="container-fluid" id="container-wrapper">

                        <dec:body/>







                    </div>
                    <!---Container Fluid end-->
                </div>
                <!-- Footer start-->
                <%@ include file="/common/admin/footer.jsp" %>
                <!-- Footer end-->
            </div>
        </div>

        <!-- Js-->

        <script src="<c:url value='/template/admin/js/bootstrap.bundle.min.js' />"></script>
        <script src="<c:url value='/template/admin/js/bootstrap-datepicker.min.js' />"></script>
        <script src="<c:url value='/template/admin/js/jquery.easing.min.js' />"></script>
        <script src="<c:url value='/template/admin/js/ruang-admin.min.js' />"></script>
    </body>
</html>