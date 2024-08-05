<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제 증명서 발급대장</title>
</head>
<body>
		<table width='100%' border='0'>
			<tr height='100'>
				<td align='left'>
					<%@ include file="/../../../Site_header.jsp" %>
				</td>
			</tr>
			<tr>
				<td align='left'>
					<%@ include file="/../../../Site_menu.jsp" %>
				</td>
			</tr>
			<tr >
				<td align='left'>
				<h2>&nbsp;&nbsp;&nbsp;제 증명서 발급</h2>
				
				<table width=80% border=1>
					<tr>
						<td>-</td><td>발급번호</td><td>발급대장</td><td>발급용도</td><td>구분</td><td>성명</td>
						<td>부서</td><td>직위</td><td>발급일</td><td>인쇄</td><td>다운로드</td>
					</tr>
					<form action='issuingList.do' method='post'>
					
					
					</form>
					
				</table>
					
					
					
				</td>
			</tr>
			
		</table> <br/><br/><br/><br/>
	</body>
</html>