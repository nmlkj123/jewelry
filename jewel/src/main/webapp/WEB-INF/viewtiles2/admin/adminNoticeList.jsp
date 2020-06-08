<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


</head>
<body>
    <h2>공지 사항</h2>
    <table class="board_list">
       
        <thead>
            <tr>
                <th scope="col">공지사항번호</th>
                <th scope="col">공지유형</th>
                <th scope="col">공지사항 제목</th>
                <th scope="col">등록일</th>
                
            </tr>
        </thead>
        <tbody>
             <c:choose>
                <c:when test="${fn:length(list) > 0}">
                    <c:forEach items="${list}" var="row">
                        <tr>
                            <td>${row.NT_NUM}</td>                        
                            <td> ${row.NT_TYPE}</td> 	
                          
                            
                            
                            <td><a href="adminNoticeDetail?NT_NUM=${row.NT_NUM}">${row.NT_TITLE}</a></td>
                            <td> ${row.NT_DATE}</td>
                           
                           
                    </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="4">조회된 결과가 없습니다.</td>
                    </tr>
                </c:otherwise>
            </c:choose> 
            
        </tbody>
    </table>
    <div align="center"class="container pt-5" id="categoryPaging" >
   ${AdminNoticeListPaging.pagingHTML}
   
   </div>
    <br/>
<input type="button" onclick="location.href='<c:url value="/openAdminNoticeWrite"/>'" value="공지사항 등록">
     
     
   
    
</body>
</html>