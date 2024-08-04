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

<style>
.modal {
	position: absolute;
	display: none;
	justify-content: center;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.4);
}

.modal_body {
	position: absolute;
	top: 50%; /*모달을 화면가운데 놓기위함. */
	width : 250px; /* 모달의 가로크기  */
	height : 200px; /* 모달의 세로크기 */
	padding : 40px;
	background-color: rgb(255, 255, 255); /*  모달창 배경색 흰색 */
	border-radius : 10px; /* 테두리 */
	box-shadow: 0 2px 3px 0 rgba(34, 36, 38, 0.15); /* 테두리그림자 */
	transform:translateY(-50%); /* 모듈창열었을때위치설정가운데로 */
}

.modal_close {
	cursor: pointer;
	float: right;
	font-size: 24px;
	font-weight: bold;
}
</style>

<script>
function openModal() {
    document.getElementById('VacDaysModal').style.display = 'flex';
}

function closeModal() {
    document.getElementById('VacDaysModal').style.display = 'none';
}

window.onload = function() {
    var closeBtn = document.querySelector('.modal_close');
    closeBtn.onclick = closeModal;
    
    var modal = document.getElementById('VacDaysModal');
    window.onclick = function(event) {
        if (event.target === modal) {
            closeModal();
        }
    }
}


</script>
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
								<td><button type="button" onclick="openModal()">관리</button></td>
								<td>${vac.vac_used}</td>
							</tr>
						</c:forEach>

					</table>
				</div>

				<div style="width: 49%; float: right;">
					<form action="att.do" method='post'>
						<table width=90% border='1'>

							<tr>
								<td>휴가항목</td>
								<td><input type='text' name='vac_name'
									value='${vacation_items.vac_name }'></td>
							</tr>
							<tr>
								<td>적용기간</td>
								<td colspan='3'><input type="date" name="vac_start" value='${vacation_items.vac_start }'> ~ 
								 ~ 
								<input type="date" name="vac_end" value='${vacation_items.vac_end }'></td>
							</tr>

							<tr>
								<td>사용여부</td>
								<td>
									<form action method="get">
										<input type="radio" name="vac_used" value="사용" checked> 사용<br>
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
<!-- --------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------- -->
		
		<table width='100%' border='0'>
		<tr>
			<td>
				<br/><h2>근태항목 설정</h2><br/>
				<div style="width: 51%; float: left;">
					<table width=90% table border="1">
						<tr>
							<td>근태항목</td>
							<td>단위</td>
							<td>그룹관리</td>
							<td>휴가공제</td>
							<td>근로시간연계</td>
							<td>사용여부</td>
						</tr>

						<c:forEach var="att" items="${list_att}">
		                     <tr>
		                        <td>${att.att_name}</td>
		                        <td>${att.att_unit}</td>
		                        <td>${att.att_grp}</td>
		                        <td>${att.att_deduction}</td>
		                        <td>${att.att_conn}</td>
		                        <td>${att.att_used}</td>
		                     </tr>
                  </c:forEach>
				

					</table>
				</div>

				<div style="width: 49%; float: right;">
					<form action="att.do" method='post'>
						<table width=90% border='1'>

							<tr>
								<td>근태항목</td>
								<td><input type='text' name='att_name' value='${attend_items.att_name }'></td>
							</tr>
							
							<tr>
								<td>단위</td>
									<td>
										<select name="att_unit">
								            <option value="선택하세요">선택하세요</option>
								            <option value="일">일</option>
								            <option value="시간">시간</option>
								        </select>
									</td>
							</tr>
							
							<tr>
								<td>근태그룹</td>
									<td>
										<select name="att_grp">
								            <option value="선택하세요">선택하세요</option>
								            <option value="휴가">휴가</option>
								            <option value="연장근무">연장근무</option>
								            <option value="지각조퇴">지각조퇴</option>
								            <option value="특근">특근</option>
								            <option value="기타">기타</option>
								        </select>
									</td>
							</tr>
							
							<tr>
								<td>휴가공제</td>
									<td>
										<select name="att_deduction">
								            <option value="선택하세요">선택하세요</option>
									           	<c:forEach var="vac" items="${list_vac}">
									            	<option value="${vac.vac_name}">${vac.vac_name}</option>
									            </c:forEach>
								        </select>
									</td>
							</tr>
							
							<tr>
								<td>근로시간연계</td>
									<td>
										<select name="att_conn">
								            <option value="선택하세요">선택하세요</option>
								            <option value="소정근로">소정근로</option>
								            <option value="연장근로">연장근로</option>
								            <option value="야간근로">야간근로</option>
								            <option value="휴일근로">휴일근로</option>
								        </select>
									</td>
							</tr>

							<tr>
								<td>사용여부</td>
								<td>
									<form action method="get">
										<input type="radio" name="att_used" value="사용" checked> 사용<br>
										<input type="radio" name="att_used" value="사용안함"> 사용안함<br>
									</form>
								</td>				
						</table>
		<br/>
		<input type='submit' value='저장'>
		</form>
		</div>
		
		<!-- 휴가일수 설정 처리 모달 -->
	<div class="modal" id="VacDaysModal">
		<div class="modal_body">
			<span class="modal_close">&times;</span>
			<h2>휴가일수 설정</h2>
			<div>
				<form action="${pageContext.request.contextPath}/vacdays.do" method="post">
					<input type="hidden" name="vacdays" id="modal_vacdays" />

						<table border="1">
							<tr>
								<th>구분</th>
								<th>사원번호</th>
								<th>성명</th>
								<th>부서</th>
								<th>직위</th>
								<th>입사일</th>
								<th>휴가일수</th>
							</tr>
						
						<c:forEach var="vacdays" items="${list_vac_days}">
	                        <tr>
	                           <td>${vacdays.emp_type}</td>
	                           <td>${vacdays.emp_no}</td>
	                           <td>${vacdays.name_kor}</td>
	                           <td>${vacdays.dept}</td>
	                           <td>${vacdays.job}</td>
	                           <td>${vacdays.hired_date}</td>
	                        </tr>
                     </c:forEach>
							
						</table>
				</form>
			</div>
		</div>
	</div>
			</td>
		</tr>
		
	
</body>
</html>