<%-- 
    Document   : login
    Created on : Aug 17, 2020, 9:28:17 PM
    Author     : nguyen
--%>
<%@include file="/common/taglib.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>

        <form class="form" 
              action="login"
              method="POST" 
              style="min-height: 330px;">

            <div class="card-header card-header-primary text-center">
                <h4 class="card-title text-uppercase">Login</h4>
            </div>
            <div class="card-body px-1">

                <c:if test="${not empty message}">
                    <div class="text-center font-weight-bold alert alert-${alert}" style="border-radius: 5px;">
                        <span class="text-uppercase">${message}</span>
                    </div>
                </c:if>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">
                            <i class="material-icons">face</i>
                        </span>
                    </div>
                    <input type="text" 
                           class="form-control" 
                           name="username" 
                           placeholder="User Name..." 
                           value="${param.username}"
                           />
                </div>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">
                            <i class="material-icons">lock_outline</i>
                        </span>
                    </div>
                    <input type="password" 
                           class="form-control" 
                           name="password" 
                           placeholder="Password..." 
                           value=""

                           />
                </div>
            </div>
            <input type="hidden" name="action" value="login" />

            <div class="footer text-center mt-5">
                <button type="submit" 
                        class="btn btn-primary btn-link mt-4">Login</button>
            </div>
        </form>
    </body>
</html>
