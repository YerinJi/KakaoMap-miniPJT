<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="header.jsp" %>

<!-- Hero -->
<div class="bg-dark text-white py-5 mb-4">
    <div class="container d-flex flex-column flex-md-row align-items-md-end justify-content-between">
        <div>
            <h1 class="display-6 mb-2">MyBoard</h1>
            <p class="mb-0 text-secondary">가볍게 쓰고, 빠르게 찾는 게시판</p>
        </div>
        <div class="mt-3 mt-md-0">
            <a class="btn btn-primary btn-lg" href="<c:url value='/list'/>">게시판 목록 보기</a>
        </div>
    </div>
</div>

<!-- Gallery -->
<div class="container">
    <h2 class="h5 mb-3">최근 글</h2>

    <div class="row row-cols-2 row-cols-md-3 row-cols-lg-4 g-3">
        <c:choose>
            <c:when test="${not empty latest}">
                <c:forEach var="b" items="${latest}">
                    <div class="col">
                        <a class="card h-100 text-decoration-none shadow-sm" href="<c:url value='/view/${b.id}'/>">
                            <img class="card-img-top" src="https://picsum.photos/seed/${b.id}/600/400" alt="" loading="lazy">
                            <div class="card-body">
                                <h5 class="card-title text-truncate mb-1">${fn:escapeXml(b.title)}</h5>
                                <div class="small text-muted text-truncate">
                                        ${fn:escapeXml(b.writer)} · ${b.createdAt}
                                </div>
                            </div>
                            <div class="card-footer bg-transparent border-0 pt-0">
                                <span class="badge bg-secondary">조회 ${b.views}</span>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <c:forEach var="i" begin="1" end="4">
                    <div class="col">
                        <a class="card h-100 text-decoration-none shadow-sm" href="<c:url value='/list'/>">
                            <img class="card-img-top" src="https://picsum.photos/seed/demo${i}/600/400" alt="" loading="lazy">
                            <div class="card-body">
                                <h5 class="card-title mb-1">샘플 카드 ${i}</h5>
                                <div class="small text-muted">게시판으로 이동</div>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="text-center mt-4">
        <a href="<c:url value='/list'/>" class="btn btn-outline-secondary">더 보기</a>
    </div>
</div>

<%@ include file="footer.jsp" %>
