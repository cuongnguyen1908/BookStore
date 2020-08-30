<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History</title>
    </head>
    <body>

        <c:if test="${sessionScope.USERMODEL.role.id == 1}">
            <c:redirect url="/"/>
        </c:if>

        <c:if test="${empty sessionScope.USERMODEL}">
            <c:redirect url="/"/>
        </c:if>


        <c:if test="${sessionScope.USERMODEL.role.id == 2}">


            <form class="ml-auto" action="/history-search" method="GET">
                <div class="form-group">
                    <input type="text" class="form-control" name="textSearch" placeholder="Search"
                           value="${param.textSearch}">
                </div>
                <button type="submit" class="btn btn-success ml-2">Search</button>
            </form>

            <c:if test="${not empty ORDERLIST}">
                <c:forEach var="item" items="${requestScope.ORDERLIST.listResult}">
                    <h4>Name: ${item.name}</h4>
                    <h4>total: $${item.total}</h4>
                    <h4>Date: ${item.createdDate}</h4>
                    <!--order detail start-->

                    <table class="table table-hover mt-5">
                        <thead>
                            <tr>
                                <th>Title</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="element" 
                                       items="${item.orderDetail}">
                                <tr>
                                    <td>${element.title}</td>
                                    <td>${element.price}</td>
                                    <td>${element.quantity}</td>
                                    <td>${element.quantity * element.price}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <h3 class="text-left">Total: ${item.total}</h3>


                    <h3>----------------------------------------------------<h3>
                        </c:forEach>
                    </c:if>
                            
                    <c:if test="${requestScope.ORDERLIST.listResult.size() == 0 }">
                        <h3 class="text-center">No exist history</h3>
                    </c:if>

                </c:if>



                </body>
                </html>
