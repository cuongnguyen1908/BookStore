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

        <script src="https://use.fontawesome.com/0074d94460.js"></script>
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




                        <!-- Modal Logout start -->
                        <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabelLogout" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabelLogout">Logout</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <p>Are you sure you want to logout?</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-outline-primary" data-dismiss="modal">Cancel</button>
                                        <a href='<c:url value="/logout?action=logout"/>' class="btn btn-primary">Logout</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Modal Logout end-->


                    </div>
                    <!---Container Fluid end-->
                </div>
                <!-- Footer start-->
                <%@ include file="/common/admin/footer.jsp" %>
                <!-- Footer end-->
            </div>
        </div>

        <!-- Js-->
        <script src="<c:url value='/template/admin/js/jquery.min.js' />"></script>
        <script src="<c:url value='/template/admin/js/bootstrap.bundle.min.js' />"></script>
        <script src="<c:url value='/template/admin/js/jquery.easing.min.js' />"></script>
        <script src="<c:url value='/template/admin/js/ruang-admin.min.js' />"></script>
    </body>
</html>