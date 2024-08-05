<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인사기록카드</title>
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
		<h2>인사기록 카드</h2>
		<td align='center'>
		<br/>
			<form action='personCard.do' method='post'>
				
				<table width=50% border=1>
					<tr>
						<td>선택</td><td>구분</td><td>사원번호</td><td>성명</td><td>부서</td><td>직위</td><td>상태</td>
					</tr>
					<c:forEach var='emp' items="${emp_list}">
						<tr>
							<td><input type='checkbox' name="emp_no" value='${emp.emp_no}'></td>
							<td>${emp.emp_type}</td><td align=center>${emp.emp_no}</td><td>${emp.name_kor}</td><td>${emp.dept}</td><td>${emp.job}</td><td>${emp.state}</td>
						</tr>
					</c:forEach>
					
					
				</table>
				
				<input type='submit' value='선택'>
			
			</form>
		<br/>
		
		<table width=50% border=1>
		<tr>
			<td>
				<table width=100% border=1>
					<tr>
						<td width=50% rowspan=3 align=center><h2><b>인사기록카드</b></h2></td>
						<td width=25%>사원번호</td><td>${personnel.emp.emp_no}</td>
					</tr>
					<tr>
						<td>입사일</td><td>${personnel.emp.hired_date}</td>
					</tr>
					<tr>
						<td>퇴사일</td><td>${personnel.rtr.retired_date}</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width=100% border=1>
					<tr>
						<td width=15%>성명(한글)</td><td width=25%>${personnel.emp.name_kor}</td><td width=25%>성명(영문)</td><td width=25%>${personnel.emp.name_eng}</td>
					</tr>
					<tr>
						<td>주민등록번호</td><td>${personnel.emp.id_number}</td><td>사원구분</td><td>${personnel.emp.emp_type}</td>
					</tr>
					<tr>
						<td>주소</td><td colspan=3>(${personnel.emp.post_code}) ${personnel.emp.addr}</td>
					</tr>
					<tr>
						<td>휴대전화</td><td>${personnel.emp.phone}</td><td>연락처</td><td>${personnel.emp.home_number}</td>
					</tr>
					<tr>
						<td>E-Mail</td><td colspan=3>${personnel.emp.email}</td>
					</tr>
				</table>
				</td>
			</tr>
		<tr>
			<td colspan=4>
				<table width=100% border=1>
				<tr>
					<td width=15% rowspan=2>국민연금</td><td width=20%>기호번호</td><td colspan=3>${personnel.ins.pension_num}</td>
				</tr>
				<tr>
					<td width=20%>취득일</td><td>${personnel.ins.pension_start}</td><td width=20%>상실일</td><td>${personnel.ins.pension_end}</td>
				</tr>
				<tr>
					<td rowspan=2>건강보험</td><td>기호번호</td><td colspan=3>${personnel.ins.heal_num}</td>
				</tr>
				<tr>
					<td>취득일</td><td>${personnel.ins.heal_start}</td><td>상실일</td><td>${personnel.ins.heal_end}</td>
				</tr>
				<tr>
					<td rowspan=2>고용보험</td><td>기호번호</td><td colspan=3>${personnel.ins.hire_num}</td>
				</tr>
				<tr>
					<td>취득일</td><td>${personnel.ins.hire_start}</td><td>상실일</td><td>${personnel.ins.hire_end}</td>
				</tr>
				<tr>
					<td rowspan=2>산재보험</td><td>기호번호</td><td colspan=3>${personnel.ins.indus_num}</td>
				</tr>
				<tr>
					<td>취득일</td><td>${personnel.ins.indus_start}</td><td>상실일</td><td>${personnel.ins.indus_end}</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan=4>
				<table width=100% border=1>
					<tr>
						<td width=15% rowspan=3>학력</td><td>학교명</td><td>입학년월</td><td>졸업년월</td><td>전공</td><td>이수</td>
					</tr>
					<tr>
						<td>${personnel.edu[0].school_name}</td><td>${personnel.edu[0].school_start}</td><td>${personnel.edu[0].school_end}</td><td>${personnel.edu[0].school_major}</td><td>${personnel.edu[0].school_state}</td>
					</tr>
					<tr>
						<td>${personnel.edu[1].school_name}</td><td>${personnel.edu[1].school_start}</td><td>${personnel.edu[1].school_end}</td><td>${personnel.edu[1].school_major}</td><td>${personnel.edu[1].school_state}</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan=4>
				<table width=100% border=1>
					<tr>
						<td width=15% rowspan=3>병역</td><td>제대구분</td><td>${personnel.mil.mil}</td><td>미필사유</td><td>${personnel.mil.mil_no_reason}</td>
					</tr>
					<tr>
						<td>군별</td><td>최종계급</td><td>병과</td><td>복무기간</td>
					</tr>
					<tr>
						<td>${personnel.mil.mil_type}</td><td>${personnel.mil.mil_rank}</td><td>${personnel.mil.mil_job}</td><td>${personnel.mil.mil}</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan=4>
				<table width=100% border=1>
					<tr>
						<td width=15% rowspan=3>경력</td><td>회사명</td><td>입사일자</td><td>퇴사일자</td><td>최종직위</td><td>담당업무</td>
					</tr>
					<tr>
						<td>${personnel.car[0].firm}</td><td>${personnel.car[0].firm_start}</td><td>${personnel.car[0].firm_end}</td><td>${personnel.car[0].firm_job}</td><td>${personnel.car[0].firm_task}</td>
					</tr>
					<tr>
						<td>${personnel.car[1].firm}</td><td>${personnel.car[1].firm_start}</td><td>${personnel.car[1].firm_end}</td><td>${personnel.car[1].firm_job}</td><td>${personnel.car[1].firm_task}</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan=4>
				<table width=100% border=1>
					<tr>
						<td width=15% rowspan=3>자격/면허</td><td>종류</td><td>취득일</td><td>발행기관</td><td>비고</td>
					</tr>
					<tr>
						<td>${personnel.lcs[0].lsc_name}</td><td>${personnel.lcs[0].lsc_date}</td><td>${personnel.lcs[0].lsc_dep}</td><td>${personnel.lcs[0].lsc_note}</td>
					</tr>
					<tr>
						<td>${personnel.lcs[1].lsc_name}</td><td>${personnel.lcs[1].lsc_date}</td><td>${personnel.lcs[1].lsc_dep}</td><td>${personnel.lcs[1].lsc_note}</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan=4>
				<table width=100% border=1>
					<tr>
						<td width=15% rowspan=3>어학능력</td><td>외국어명</td><td>시험</td><td>공인점수</td><td>취득일</td><td>독해</td><td>작문</td><td>회화</td>
					</tr>
					<tr>
						<td>${personnel.lang[0].lang_name}</td><td>${personnel.lang[0].lang_test}</td><td>${personnel.lang[0].lang_score}</td><td>${personnel.lang[0].lang_date}</td><td>${personnel.lang[0].lang_read}</td><td>${personnel.lang[0].lang_listen}</td><td>${personnel.lang[0].lang_speak}</td>
					</tr>
					<tr>
						<td>${personnel.lang[1].lang_name}</td><td>${personnel.lang[1].lang_test}</td><td>${personnel.lang[1].lang_score}</td><td>${personnel.lang[1].lang_date}</td><td>${personnel.lang[1].lang_read}</td><td>${personnel.lang[1].lang_listen}</td><td>${personnel.lang[1].lang_speak}</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan=4>
				<table width=100% border=1>
					<tr>
						<td width=15% rowspan=3>퇴직사항</td><td>퇴직구분</td><td>퇴직일자</td><td>퇴직사유</td><td>퇴직금</td><td>퇴직후 연락처</td>
					</tr>
					<tr>
						<td>${personnel.rtr.retire_type}</td><td>${personnel.rtr.retired_date}</td><td>${personnel.rtr.retire_reason}</td><td>${personnel.rtr.retire_cost}</td><td>${personnel.rtr.retire_phone}</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width=100% align=center><h2>(주)JSP프로젝트</h2></td>
		</tr>		
		</table>
		
		<br/><br/>
		<input type='button' value='인쇄'> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type='button' value='엑셀 다운로드'>
				
	</td></tr>
</table>
<br/><br/><br/>
	
</body>
</html>