<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
  <c:if test="${not empty message}">
    alert("${message}");
  </c:if>
</script>