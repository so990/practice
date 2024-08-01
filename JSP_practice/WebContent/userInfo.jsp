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
		
		<script>
	        function successJoin() {
	            alert('사용자 정보가 저장되었습니다.');
	    </script>
		
	</head>
	<body>
		<table width='100%' border='0'>
			<tr height='100'>
				<td align='left'>
					<%@ include file="Site_header.jsp" %>
				</td>
			</tr>
			<tr>
				<td align='left'>
					<%@ include file="Site_menu.jsp" %>
				</td>
			</tr>
			<tr >
				<td align='center'>
					<form action="company.do" method='post'>
					<h3>회사정보</h3>
					<table width='80%' border='1'>
					<tr>
						<td>*상호:</td><td><input type='text' name='cp_name' value='${company.cp_name }'></td>
						<td>*대표자직급/대표자:</td>
						<td><input type='text' name='ceo_job' value='${company.ceo_job }'> / 
						<input type='text' name='ceo_name' value='${company.ceo_name }'></td>
					</tr>
					<tr>
						<td>*사업자번호:</td><td><input type='text' name='bs_num' value='${company.bs_num } '></td>
						<td>법인등록번호:</td><td><input type='text' name='bs_regnum' value='${company.bs_regnum }'></td>
					</tr>
					<tr>
						<td>설립일:</td><td><input type='text' name='founded_date'value='${company.founded_date }'></td>
						<td>홈페이지:</td><td><input type='text' name='hp' value='${company.hp }'></td>
					</tr>
					<tr>
						<td>
						*우편번호/사업자주소:</td><td colspan='3'><input type='text' name='bs_post' value='${company.bs_post }'> / 
						<input type='text' name='bs_addr' value='${company.bs_addr }'></td>
					</tr>
					<tr>
						<td>*전화번호:</td><td><input type='text' name='bs_phone' value='${company.bs_phone }'></td>
						<td>팩스번호:</td><td><input type='text' name='bs_fax' value='${company.bs_fax }'></td>
					</tr>
					<tr>
						<td>업태:</td><td><input type='text' name='bs_type' value='${company.bs_type }'></td>
						<td>종목:</td><td><input type='text' name='cp_type' value='${company.cp_type }'></td>
					</tr>
					</table>
					<br/>
					<h3>급여지급정보</h3>
					<table width='80%' border='1'>
					<tr>
						<td>급여산정기간:</td><td><input type='text' name='calc_start' value='${company.calc_start }'>
					 ~ <input type='text' name='calc_end' value='${company.calc_end }'></td>
						<td>급여지급일:</td><td><input type='text' name='payday' value='${company.payday }'></td>
					</tr>
					<tr>
						<td>금융기관:</td><td><input type='text' name='bs_bank' value='${company.bs_bank }'></td>
						<td>계좌번호:</td><td><input type='text' name='bs_account' value='${company.bs_account }'></td>
						<td>예금주:</td><td><input type='text' name='bs_acc_name' value='${company.bs_acc_name }'></td>
					</tr>
					</table>
					<br/>
					<h3>담당자정보</h3>
					<table width='40%' border='1'>
					<tr>
						<td>성명:</td><td><input type='text' name='emp_no' value='${employee.emp_no }'></td>
					</tr>
					<tr>
						<td>부서:</td><td><input type='text' name='dept' value='${employee.dept }'></td>
					</tr>
					<tr>
						<td>직위:</td><td><input type='text' name='job' value='${employee.job }'></td>
					</tr>
					<tr>
						<td>전화번호:</td><td><input type='text' name='home_number' value='${employee.home_number }'></td>
					</tr>
					<tr>
						<td>휴대폰번호:</td><td><input type='text' name='phone' value='${employee.phone }'></td>
					</tr>
					<tr>
						<td>이메일:</td><td><input type='text' name='email' value='${employee.email }'></td>
					</tr>
					</table>
					
					<br/>
					<br/>
					<input type='submit' value='저장'>
					</form>
					
				</td>
			</tr>
			
		</table>
	</body>
</html>