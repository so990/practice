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
	</table>
	<form action="attend.do" name='' method="post">
		<div style="width: 50%; float: left;">
			<tr>
				<td>
					<table border=1 style='border-collapse: collapse'>
						<tr>
							<td>
								<input type=search placeholder='검색어를 일력하세요'> 
								<input type=submit value=검색> <input type=submit value=전체보기>*다중선택시
							</td>

							<td><select style='float: right'>
									<!-- 요소가 많거나, css에선 ;무조건 -->
									<option>상태별</option>
									<option>재직</option>
									<option>퇴직</option>
							</select></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table width=100% border=1 style='border-collapse: collapse'>
						<tr align=center>
							<td><input type=checkbox></td>
							<td>구분</td>
							<td>사원번호</td>
							<td>성명</td>
							<td>부서</td>
							<td>직위</td>
							<td>근태기록</td>
						</tr>
						<c:forEach var='emp' items='${emp_list}'>
							<tr align=center>
								<td><input type=checkbox name="emp_no" value="${emp.emp_no}"></td>
								<td>${emp.emp_type}</td>
								<td>${emp.emp_no}</td>
								<td>${emp.name_kor}</td>
								<td>${emp.dept}</td>
								<td>${emp.job}</td>
								<td><input type="button" value="관리" onclick=''></td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
		</div>
		<div style="width: 49%; float: right;">
			<table>
				<tr>
					<td>입력일자<input type="date" name="atth_inserted"></td>
				</tr>
				<tr>
					<td>근태일자<select name="atth_name">
							<option selected>선택</option>
							<option value="연차">연차</option>
							<option value="반차">반차</option>
							<option value="지각">지각</option>
							<option value="조퇴">조퇴</option>
							<option value="외근">외근</option>
							<option value="휴일근무">휴일근무</option>
							<option value="연장근무">연장근무</option>
							<option value="포상휴가">포상휴가</option>
							<option value="야간근무">야간근무</option>
							<option value="청원휴가">청원휴가</option>
					</select></td>
				</tr>
				<tr>
					<td>기간<input type="date" name="atth_start"><input
						type="date" name="atth_end"></td>
				</tr>
				<tr>
					<td>근태일수<input type="text" name="atth_days"><input
						type="submit" value=휴가일수현황></td>
				</tr>
				<tr>
					<td>금액(수당)<input type="text" name="atth_cost"></td>
				</tr>
				<tr>
					<td>적요<input type="text" name="atth_note"></td>
				</tr>
				<tr>
					<td align="center"><input type="submit" value="저장"></td>
				</tr>
			</table>
		</div>
	</form>
		
	
	<table>
		<tr >
			<td>성명:${emp.name_kor}<input type=text name='name_kor' id='emp' value='당신은 사랑받기 위해 태어난 사람' readonly></td>
        	<td>부서:${dept}</td>
       		<td>직위:${job}</td>
		</tr>
		<tr>
			 <td><select>
            <option>선택</option>
            <option>2020</option>
            <option>2021</option>
            <option>2022</option>
            <option>2023</option>
            <option>2024</option>
         </select></td>
         <td><select>
            <option>전체</option>
            <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
            <option>5</option>
         </select></td>

		</tr>
		</table>
	
		<table width=100% border=1 style='border-collapse: collapse'>
			<tr align=center>
				<td>번호</td>
				<td>입력</td>
				<td>일자</td>
				<td>근태항목</td>
				<td>근태기간</td>
				<td>근태일수</td>
				<td>금액적요</td>
				<td>수정/삭제</td>
			</tr>
			<c:forEach var='atd' items='${atd_list}' varStatus='s'>
				<tr align=center>
					<td>${s.index+1}</td>
					<td>${atd.atth_inserted}</td>
					<td>${atd.atth_name}</td>
					<td>${atd.atth_start}~${atd.atth_end}</td>
					<td>${atd.atth_days}</td>
					<td>${atd.atth_cost}</td>
					<td>${atd.atth_note}</td>
					<td><input type="submit" value="수정">/<input type="submit" value="삭제"></td>
				</tr>
			</c:forEach>
			
			
		</table>

	</body>
</html>