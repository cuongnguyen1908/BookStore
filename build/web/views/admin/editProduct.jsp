<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit</title>
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


    </div>


    <!--author-->
    <div class="form-group">
        <label for="author">*Author:</label>
        <input type="text" class="form-control" placeholder="Enter author" id="author" name="author"
               <c:if test="${not empty requestScope.BOOK.id}">value="${requestScope.BOOK.author}"</c:if>
               <c:if test="${empty requestScope.BOOK.id}">value="${param.author}"</c:if>
        >
    </div>

    <!--quantity-->

    <div class="form-group">
        <label for="quantity">*Quantity:</label>
        <input type="number" min="0" class="form-control" placeholder="Enter quantity" id="quantity" name="quantity"
               <c:if test="${not empty requestScope.BOOK.id}">value="${requestScope.BOOK.quantity}"</c:if>
               <c:if test="${empty requestScope.BOOK.id}">value="${param.quantity}"</c:if>
        >

    </div>

    <!--price-->

    <div class="form-group">
        <label for="price">*Price:</label>
        <input type="number" min="0" class="form-control" placeholder="Enter price"
               id="price" name="price"
               <c:if test="${not empty requestScope.BOOK.id}">value="${requestScope.BOOK.price}"</c:if>
               <c:if test="${empty requestScope.BOOK.id}">value="${param.price}"</c:if>
        >
    </div>

    <!--email-->

    <div class="form-group">
        <label for="description">Description:</label>
        <input type="description" class="form-control" placeholder="Enter description" id="description"
               name="description"
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

    <c:if test="${not empty requestScope.BOOK.id}">
        <%--    import date--%>
        <div class="form-group mt-3" id="simple-date3">
            <label for="decadeView">Import date:</label>
            <div class="input-group date">
                <div class="input-group-prepend">
                    <span class="input-group-text"><i class="fas fa-calendar"></i></span>
                </div>
                <input type="text" class="form-control" placeholder="Enter date" name="importDate" value="" id="decadeView">
            </div>
        </div>
    </c:if>



    <!--status-->

    <label for="status">*Status:</label>
    <select class="form-control mr-2" name="status" id="status"
            <c:if test="${empty requestScope.BOOK.id}">disabled</c:if>
    >

        <c:if test="${empty requestScope.BOOK.id}">
            <option value="1">Active</option>
            <option value="0">Inactive</option>
        </c:if>

        <!--have book -->

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


<script>
    $(document).ready(function() {

        $('#simple-date3 .input-group.date').datepicker({
            startView: 2,
            format: 'yyyy/mm/dd',
            autoclose: true,
            todayHighlight: true,
            todayBtn: 'linked',
        });
    });
</script>
</body>
</html>
