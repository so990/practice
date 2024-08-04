<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page info="Include 테스트"%>
<%@ page buffer="none"%>
<%@ page autoFlush="true"%>
<%@ page isThreadSafe="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
<title>근태조회</title>
</head>
<body>
	<table width='100%'>
		<tr height='100'>
			<td align='left'><%@ include file="/../../../Site_header.jsp"%>
			</td>
		</tr>
		<tr>
			<td align='left'><%@ include file="/../../../Site_menu.jsp"%>
			</td>
		</tr>
	</table>

	<table width=100% border=1>
		<!--  <td><input type=button onclick="location.href='~~~.jsp'"></td>-->
		<tr>
			<td>입력일자</td>
			<td>구분</td>
			<td>성명</td>
			<td>부서</td>
			<td>직위</td>
			<td>근태항목</td>
			<td>근태일수</td>
			<td>근태기간</td>
			<td>금액</td>
			<td>적요</td>
		</tr>
		<c:forEach var='atdv' items='${atdv_list}'>
			<tr>	
				<td>${atdv.atth_inserted }</td>
				<td>${atdv.emp_type }</td>
				<td>${atdv.name_kor }</td>
				<td>${atdv.dept }</td>
				<td>${atdv.job }</td>
				<td>${atdv.atth_name }</td>
				<td>${atdv.atth_start }~${atdv.atth_end }</td>
				<td>${atdv.atth_days }</td>
				<td>${atdv.atth_cost }</td>
				<td>${atdv.atth_note }</td>
			</tr>
		</c:forEach>
		<tr><td>합계</td></tr>
	</table>
</body>
</html>