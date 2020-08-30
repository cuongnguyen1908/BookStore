<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit</title>
</head>
<body>

<form action="/admin-create-code" method="POST">

    <div class="form-group">
        <label for="code">Code:</label>
        <input type="text" class="form-control"
               placeholder="Enter code" id="code" name="code" value="${param.code}">

        <c:if test="${not empty ERROR.codeLenghtError}">
            <font color="red">${ERROR.codeLenghtError}</font><br/>
        </c:if>

        <c:if test="${not empty ERROR.codeExist}">
            <font color="red">${ERROR.codeExist}</font><br/>
        </c:if>
    </div>


    <div class="form-group">
        <label for="percent">Percent number:</label>
        <input type="number" min="1" class="form-control"
               placeholder="Enter percent" id="percent" value="${param.percent}" name="percent">
        <c:if test="${not empty ERROR.percentError}">
            <font color="red">${ERROR.percentError}</font><br/>
        </c:if>
    </div>

    <div class="form-group mt-3" id="simple-date3">
        <label for="decadeView">Date:</label>
        <div class="input-group date">
            <div class="input-group-prepend">
                <span class="input-group-text"><i class="fas fa-calendar"></i></span>
            </div>
            <input type="text" class="form-control" value="${param.date}"
                   placeholder="Enter date" name="date" value="" id="decadeView">
        </div>
    </div>

    <input type="hidden" value="${USER.id}" name="id"/>
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
