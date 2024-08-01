<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page info="Include 테스트" %>
<%@ page buffer="none" %>
<%@ page autoFlush="true" %>
<%@ page isThreadSafe="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
<title>급여관리</title>

<script>
	        function successJoin() {
	            alert('사용자 정보가 저장되었습니다.');
	    </script>

</head>
<body>
	<table width='100%' border='0'>
		<tr height='100'>
			<td align='left'><%@ include file="../../../Site_header.jsp"%>
			</td>
		</tr>
		<tr>
			<td align='left'><%@ include file="../../../Site_menu.jsp"%>
			</td>
		</tr>

		<tr>
			<td>
				<br/><h2>휴가항목 설정</h2><br/>
				<div style="width: 51%; float: left;">
					<table width=90% table border="1">
						<tr>
							<td>휴가항목</td>
							<td>적용기간</td>
							<td>사원별 휴가일수</td>
							<td>사용여부</td>
						</tr>

						<c:forEach var="vac" items="${list_vac}">
							<tr>
								<td>${vac.vac_name}</td>
								<td>${vac.vac_start}~${vac.vac_end}</td>
								<td><button type="button" onclick="----------">관리</button></td>
								<td>${vac.vac_used}</td>
							</tr>
						</c:forEach>

					</table>
				</div>

				<div style="width: 49%; float: right;">
					<form action="vac.do" method='post'>
						<table width=90% border='1'>

							<tr>
								<td>휴가항목:</td>
								<td><input type='text' name='vac_name'
									value='${vacation_items.vac_name }'></td>
							</tr>
							<tr>
								<td>적용기간:</td>
								<td colspan='3'><input type="date" name="vac_start"
									value='${vacation_items.vac_start }'> ~ <input
									type="date" name="vac_end" value='${vacation_items.vac_end }'></td>
							</tr>

							<tr>
								<td>사용여부:</td>
								<td>
									<form action method="get">
										<input type="radio" name="vac_used" value="사용"> 사용<br>
										<input type="radio" name="vac_used" value="사용안함"> 사용안함<br>
									</form>
								</td>
							</tr>
						</table>
		<br/>
		<input type='submit' value='저장'>
		</form>
		</div>
		</td>
		</tr>
</body>
</html>