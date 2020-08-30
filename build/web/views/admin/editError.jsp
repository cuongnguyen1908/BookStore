<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit</title>
    </head>
    <body>

        <form action="/admin-process-edit" method="POST">

            <c:if test="${empty requestScope.USER.id}">
                <!--username-->
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" class="form-control" placeholder="Enter username" id="username" name="username" value="${param.username}">
                    <c:if test="${not empty ERROR.usernameLengthError}">
                        <font color="red">${ERROR.usernameLengthError}</font><br/>
                    </c:if>

                    <c:if test="${not empty ERROR.usernameHasExist}">
                        <font color="red">${ERROR.usernameHasExist}</font><br/>
                    </c:if>

                </div>
            </c:if>


            <!--password-->
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" placeholder="Enter password" id="password" name="password">
                <c:if test="${not empty ERROR.passwordLengthError}">
                    <font color="red">${ERROR.passwordLengthError}</font><br/>
                </c:if>

            </div>

            <!--repassword-->

            <div class="form-group">
                <label for="repassword">Repassword:</label>
                <input type="password" class="form-control" placeholder="Enter password" id="repassword" name="rePassword">
                <c:if test="${not empty ERROR.confirmPasswordNotMatch}">
                    <font color="red">${ERROR.confirmPasswordNotMatch}</font><br/>
                </c:if>
            </div>


            <div class="form-group">
                <label for="fullName">Full name:</label>
                <input type="text" class="form-control" placeholder="Enter fullname" id="fullName"
                       <c:if test="${not empty requestScope.USER.id}">value="${requestScope.USER.fullName}"</c:if>
                       <c:if test="${empty requestScope.USER.id}">value="${param.fullName}"</c:if>
                           name="fullName" >

                <c:if test="${not empty ERROR.fullNameLengthError}">
                    <font color="red">${ERROR.fullNameLengthError}</font><br/>
                </c:if>
            </div>

            <!--email-->

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" placeholder="Enter email" id="email"  name="email" 
                       <c:if test="${not empty requestScope.USER.id}">value="${requestScope.USER.email}"</c:if>
                       <c:if test="${empty requestScope.USER.id}">value="${param.email}"</c:if>
                           >

                <c:if test="${not empty ERROR.emailError}">
                    <font color="red">${ERROR.emailError}</font><br/>
                </c:if>
            </div>

            <!--phone-->

            <div class="form-group">
                <label for="phone">Phone:</label>
                <input type="number" min="1" class="form-control" placeholder="Enter phone" id="phone" name="phone" 
                       <c:if test="${not empty requestScope.USER.id}">value="${requestScope.USER.phone}"</c:if>
                       <c:if test="${empty requestScope.USER.id}">value="${param.phone}"</c:if>
                           >

                <c:if test="${not empty ERROR.phoneLengthError}">
                    <font color="red">${ERROR.phoneLengthError}</font><br/>
                </c:if>
            </div>
            <!--role-->

            <label for="role">Role:</label>
            <select class="form-control mr-2" name="typeRoleId" id="role"
            <c:if test="${sessionScope.USERMODEL.id == requestScope.USER.id}">disabled</c:if>

                    >
                <c:if test="${empty requestScope.USER.id}">
                    <c:forEach var="item" items="${ROLELIST.listResult}">
                        <option value="${item.id}">${item.name}</option>
                    </c:forEach>
                </c:if>

                <c:if test="${not empty requestScope.USER.id}">
                    <c:forEach var="item" items="${ROLELIST.listResult}">
                        <option value="${item.id}" 
                                <c:if test="${item.id == requestScope.USER.role.id}">selected="selected"</c:if>>
                            ${item.name}
                        </option>
                    </c:forEach>
                </c:if>
            </select>


            <!--status-->

            <label for="status">Status:</label>
            <select class="form-control mr-2" name="status" id="status"
                    <c:if test="${empty sessionScope.USER.id}">disabled</c:if>
            <c:if test="${sessionScope.USERMODEL.id == requestScope.USER.id}">disabled</c:if>
                    >
                    <c:if test="${empty requestScope.requestScope.USER.id}">
                    <option value="1">Active</option>
                    <option value="0">Inactive</option>
                </c:if>

                <!--have USER-->

                <c:if test="${not empty requestScope.USER.id}">
                    <option value="1"
                            <c:if test="${requestScope.USER.status == true}">selected="selected"</c:if>>
                                Active
                            </option>
                            <option value="0"
                            <c:if test="${sessionScope.USER.status == false}">selected="selected"</c:if>>
                                Inactive
                            </option>
                </c:if>

            </select>


            <input type="hidden" value="${requestScope.USER.id}" name="id"/>
            <button type="submit" class="btn btn-primary">Save</button>
        </form>
    </body>
</html>
