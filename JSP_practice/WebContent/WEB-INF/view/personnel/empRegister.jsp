<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page info="Include 테스트" %>
<%@ page buffer="none" %>
<%@ page autoFlush="true" %>
<%@ page isThreadSafe="true" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
		<title>급여관리</title>
	</head>
	<body>
		<table width='100%' border='0'>
			<tr height='100'>
				<td align='left'>
					<%@ include file="../../../Site_header.jsp" %>
				</td>
			</tr>
			<tr>
				<td align='left'>
					<%@ include file="../../../Site_menu.jsp" %>
				</td>
			</tr>
			<tr>
				<td align='center'>
				
				<form action = "register.do" method="post">
					<p>
						사원번호 : <br/><input type="text" name="emp_no" value="${newEmployeeNo}">
					</p>
					<p>
						고용형태 : <br/><input type="text" name="emp_type">
					</p>
					<p>
						이름(한글) : <br/><input type="text" name="name_kor">
					</p>
					<p>
						이름(영어) : <br/><input type="text" name="name_eng">
					</p>
					<p>
						입사일자 : <br/><input type="text" name="hired_date">
					</p>
					<p>
						퇴사일자 : <br/><input type="text" name="retired_date">
					</p>
					<p>
						부서 : <br/><input type="text" name="dept">
					</p>
					<p>
						직위 : <br/><input type="text" name="job">
					</p>
					<p>
						재직상태 : <br/><input type="text" name="state">
					</p>
					<p>
						국적 : <br/><input type="text" name="nationality">
					</p>
					<p>
						주민번호 : <br/><input type="text" name="id_number">
					</p>
					<p>
						우편번호 : <br/><input type="text" name="post_code">
					</p>
					<p>
						주소 : <br/><input type="text" name="addr">
					</p>
					<p>
						전화번호 : <br/><input type="text" name="home_number">
					</p>
					<p>
						휴대폰번호 : <br/><input type="text" name="phone">
					</p>
					<p>
						이메일 : <br/><input type="text" name="email">
					</p>
					<p>
						sns : <br/><input type="text" name="sns">
					</p>
					<p>
						비고 : <br/><input type="text" name="note">
					</p>
					<p>
						은행 : <br/><input type="text" name="bank">
					</p>
					<p>
						계좌 : <br/><input type="text" name="account">
					</p>
					
					<input type="submit" value="가입">
					</form>
				</td>
			</tr>

			
		</table>
	</body>
</html>