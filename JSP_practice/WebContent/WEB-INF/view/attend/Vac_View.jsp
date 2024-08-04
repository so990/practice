<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page info="Include 테스트"%>
<%@ page buffer="none"%>
<%@ page autoFlush="true"%>
<%@ page isThreadSafe="true"%>
<%@ taglib prefix="a" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
<title>근태관리</title>
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

		<tr>
			<td>
					<table width=100% border=1 style='border-collapse:collapse'>
						<tr>
							<td>
								<input type=text placeholder='검색어를 일력하세요'> 
								<input type=submit value=검색> 
								<input type=submit value=전체보기>*다중선택시~~~
							</td>
							
							<td>
								<select style='float: right'>
									<!-- 요소가 많거나, css에선 ;무조건 -->
									<option>상태별</option>
									<option>재직</option>
									<option>퇴직</option>
								</select>
							</td>
						</tr>
					</table>
			</td>
		</tr>

		<tr>
			<td>
				<form action="" name=attend method='post'>
				<table width=100% border=1 style='border-collapse:collapse'>
					<tr align=center>
						<td>구분</td>
						<td>사원번호</td>
						<td>성명</td>
						<td>부서</td>
						<td>직위</td>
						<td>휴가항목</td>
						<td>전체사용</td>
						<td>잔여</td>
					</tr>
					<a:forEach var='emp' items='${list}' begin='0' end='12'>
					<tr align=center>
						<td><input type=checkbox></td>
						<td>${emp.emp_type}</td>
						<td>${emp.emp_no}</td>
						<td>${emp.name_kor}</td>
						<td>${emp.dept}</td>
						<td>${emp.job}</td>
						<td><input type=submit value=관리></td>
					</tr>
					</a:forEach>
				</table>
			</td>
		</tr>
	</table>
	</form>
	<h4>사원별 근태기록</h4>
	<div style='display: flex; justify-content:space-between'>
		<div style='display: flex'>
			<p>${emp.name_kor}</p>
			<p>${emp.dept}</p>
			<p>${emp.job}</p>
		</div>
		<div>
			<select>
				<option>선택</option>
				<option>2020</option>
				<option>2021</option>
				<option>2022</option>
				<option>2023</option>
				<option>2024</option>
			</select> <select>
				<option>전체</option>
				<option>1</option>
				<option>2</option>
				<option>3</option>
				<option>4</option>
				<option>5</option>
			</select>
		</div>
	</div>
</body>
</html>