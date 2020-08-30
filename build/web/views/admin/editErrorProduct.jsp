
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit product</title>
    </head>
    <body>

        <form action='<c:url value="/admin-process-product-edit"/>' method="POST">

                <!--title-->
                <div class="form-group">
                    <label for="title">*Title:</label>
                    <input type="text" class="form-control" placeholder="Enter title" id="title" name="title"
                           <c:if test="${not empty requestScope.BOOK.id}">value="${requestScope.BOOK.title}"</c:if>
                           <c:if test="${empty requestScope.BOOK.id}">value="${param.title}"</c:if>
                    >

                    <c:if test="${not empty requestScope.ERROR.titleLengthError}">
                        <font style="color: red;">${requestScope.ERROR.titleLengthError}</font><br/>
                    </c:if>

                </div>


            <!--author-->
            <div class="form-group">
                <label for="author">*Author:</label>
                <input type="text" class="form-control" placeholder="Enter author" id="author" name="author"
                       <c:if test="${not empty requestScope.BOOK.id}">value="${requestScope.BOOK.author}"</c:if>
                       <c:if test="${empty requestScope.BOOK.id}">value="${param.author}"</c:if>
                >
                <c:if test="${not empty requestScope.ERROR.authorLengthError}">
                    <font style="color: red;">${requestScope.ERROR.authorLengthError}</font><br/>
                </c:if>
            </div>

            <!--quantity-->

            <div class="form-group">
                <label for="quantity">*Quantity:</label>
                <input type="number" min="0" class="form-control" placeholder="Enter quantity" id="quantity" name="quantity"
                       <c:if test="${not empty requestScope.BOOK.id}">value="${requestScope.BOOK.quantity}"</c:if>
                       <c:if test="${empty requestScope.BOOK.id}">value="${param.quantity}"</c:if>
                >
                <c:if test="${not empty requestScope.ERROR.quantityLengthError}">
                    <font style="color: red;">${requestScope.ERROR.quantityLengthError}</font><br/>
                </c:if>

                <c:if test="${not empty requestScope.ERROR.quantityFormatException}">
                    <font style="color: red;">${requestScope.ERROR.quantityFormatException}</font><br/>
                </c:if>
            </div>

            <!--price-->

            <div class="form-group">
                <label for="price">*Price:</label>
                <input type="number" min="0" class="form-control" placeholder="Enter price" pattern="(d{3})([.])(d{2})" id="price" name="price"
                       <c:if test="${not empty requestScope.BOOK.id}">value="${requestScope.BOOK.price}"</c:if>
                       <c:if test="${empty requestScope.BOOK.id}">value="${param.price}"</c:if>
                           >
                <c:if test="${not empty requestScope.ERROR.priceLengthError}">
                    <font style="color: red;">${requestScope.ERROR.priceLengthError}</font><br/>
                </c:if>

                <c:if test="${not empty requestScope.ERROR.priceFormatException}">
                    <p style="color: red;">${requestScope.ERROR.priceFormatException}</p><br/>
                </c:if>
                </div>


                <!--description-->
                <div class="form-group">
                    <label for="description">Description:</label>
                    <input type="description" class="form-control" placeholder="Enter description" id="description" name="description"
                           <c:if test="${not empty requestScope.BOOK.id}">value="${requestScope.BOOK.description}"</c:if>
                           <c:if test="${empty requestScope.BOOK.id}">value="${param.description}"</c:if>
                        >
                </div>


                <!--category-->
                <label for="category">*Category:</label>
                <select class="form-control mr-2" name="categoryId" id="category">

                    <c:if test="${empty requestScope.BOOK.id}">
                        <c:forEach var="item" items="${requestScope.CATEGORYLIST.listResult}">
                            <option value="${item.id}">${item.name}</option>
                        </c:forEach>
                    </c:if>

                    <c:if test="${not empty requestScope.BOOK.id}">
                        <c:forEach var="item" items="${requestScope.CATEGORYLIST.listResult}">
                            <option value="${item.id}"
                                    <c:if test="${item.id == requestScope.BOOK.category.id}">selected="selected"</c:if>>
                                    ${item.name}
                            </option>
                        </c:forEach>
                    </c:if>
            </select>


            <!--status-->

            <label for="status">*Status:</label>
            <select class="form-control mr-2" name="status" id="status"
                    <c:if test="${empty requestScope.BOOK.id}">disabled</c:if>
            >

                <c:if test="${empty requestScope.BOOK.id}">
                    <option value="1">Active</option>
                    <option value="0">Inactive</option>
                </c:if>

                <!--have book-->

                <c:if test="${not empty requestScope.BOOK.id}">
                    <option value="1"
                            <c:if test="${requestScope.BOOK.status == true}">selected="selected"</c:if>>
                        Active
                    </option>
                    <option value="0"
                            <c:if test="${requestScope.BOOK.status == false}">selected="selected"</c:if>>
                        Inactive
                    </option>
                </c:if>

            </select>

            <!--photo-->

            <div class="form-group">
                <label for="file">Photo:</label>
                <input type="file" id="file" accept="image/png, image/jpeg" class="form-control-file border" name="file">
            </div>

            <input type="hidden" value="${requestScope.BOOK.id}" name="id"/>
            <button type="submit" class="btn btn-primary">Save</button>
        </form>
    </body>
</html>
