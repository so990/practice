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

<title>급여관리</title>

<script>
       
        function selectCustomer(customerCode) {
            window.opener.document.getElementById('vac_days').value = vac_days; 
            window.close();
        }
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
			<td><br />
			<h2>休暇項目　設定</h2>
				<br />
				<div style="width: 51%; float: left;">
					<table width=90% table border="1">
						<tr>
							<td>休暇項目</td>
							<td>適用期間</td>
							<td>社員別　休暇日数</td>
							<td>使用可否</td>
							<td></td>
						</tr>

						<c:forEach var="vac" items="${list_vac}">
							<tr>
								<td>${vac.vac_name}</td>
								<td>${vac.vac_start}~${vac.vac_end}</td>
								<td> <button type="button" class="btn btn-outline-secondary" onclick="window.open('vacDays.do', '_blank', 'width=800,height=800');">休暇日数　管理</button></td>
								<td>${vac.vac_used}</td>
								<form action="att.do" method="post">
									<td><input type="hidden" name="vac_name_picked"
										value="${vac.vac_name}"><input type='submit'
										value="選択"></td>
										</form>
							</tr>
						</c:forEach>

					</table>
				</div>

				<div style="width: 49%; float: right;">
					<form action="att.do" method='post'>
						<table width=90% border='1'>

							<tr>
								<td>休暇項目</td>
								<td><input type='text' name='vac_name'
									value='${vac_picked.vac_name }'></td>
							</tr>
							<tr>
								<td>適用期間</td>
								<td colspan='3'><input type="date" name="vac_start"
									value='${vac_picked.vac_start }'> ~  <input
									type="date" name="vac_end" value='${vac_picked.vac_end }'></td>
							</tr>

							<tr>
								<td>使用可否</td>
								<td>
									<form action method="get">
										<input type="radio" name="vac_used" value="사용" ${vac_picked.vac_used == '사용' ? 'checked' : ''} checked>
										使用<br> <input type="radio" name="vac_used" value="사용안함" ${vac_picked.vac_used == '사용안함' ? 'checked' : ''}>
										使用しない<br>
									</form>
								</td>
							</tr>
						</table>
						<div style="display: flex; justify-content: flex-start; gap: 10px;">
							<input type='submit' value='저장'>
					</form>

					<form action="delVac.do" method="post">
						<input type='hidden' name="del_vac_name" value="${vac_picked.vac_name}"><input type='submit' value='삭제'>
					</form>
					<form action="delVac.do" method="post">
						<input type='hidden' name="update_vac_name" value="${vac_picked.vac_name}"><input type='submit' value='수정'>
					</form>
					 <button type="reset">내용 지우기</button>
				</div></td>
		</tr>
		<!-- --------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------- -->

		<table width='100%' border='0'>
			<tr>
				<td><br />
				<h2>勤怠項目　設定</h2>
					<br />
					<div style="width: 51%; float: left;">
						<table width=90% table border="1">
							<tr>
								<td>勤怠項目</td>
								<td>単位</td>
								<td>グループ管理</td>
								<td>休暇控除</td>
								<td>勤労時間連携</td>
								<td>使用可否</td>
								<td></td>
							</tr>

							<c:forEach var="att" items="${list_att}">
								<tr>
									<td>${att.att_name}</td>
									<td>${att.att_unit}</td>
									<td>${att.att_grp}</td>
									<td>${att.att_deduction}</td>
									<td>${att.att_conn}</td>
									<td>${att.att_used}</td>
									<form action="att.do" method="post">
												<td><input type="hidden" name="att_name_picked" value="${att.att_name}"><input type='submit' value="選択"></td>
											</form>
											
								</tr>
							</c:forEach>


						</table>
					</div>

					<div style="width: 49%; float: right;">
						<form action="att.do" method='post'>
							<table width=90% border='1'>

								<tr>
									<td>勤怠項目</td>
									<td><input type='text' name='att_name'
										value='${att_picked.att_name }'></td>
								</tr>

								<tr>
									<td>単位</td>
									<td><select name="att_unit">
											<option value="선택하세요" ${att_picked.att_unit == '선택하세요' ? 'selected' : ''}>選択</option>
											<option value="일" ${att_picked.att_unit == '일' ? 'selected' : ''}>日</option>
											<option value="시간" ${att_picked.att_unit == '시간' ? 'selected' : ''}>時間</option>
									</select></td>
								</tr>

								<tr>
									<td>근태그룹</td>
									<td><select name="att_grp">
											<option value="선택하세요" ${att_picked.att_grp == '선택하세요' ? 'selected' : ''}>選択</option>
											<option value="휴가" ${att_picked.att_grp == '휴가' ? 'selected' : ''}>休暇</option>
											<option value="연장근무" ${att_picked.att_grp == '연장근무' ? 'selected' : ''}>延長勤務</option>
											<option value="지각조퇴" ${att_picked.att_grp == '지각조퇴' ? 'selected' : ''}>遅刻・早退</option>
											<option value="특근" ${att_picked.att_grp == '특근' ? 'selected' : ''}>特勤</option>
											<option value="기타" ${att_picked.att_grp == '기타' ? 'selected' : ''}>他</option>
									</select></td>
								</tr>

								<tr>
									<td>휴가공제</td>
									<td><select name="att_deduction">
											<option value="선택하세요" ${att_picked.att_deduction == '선택하세요' ? 'selected' : ''}>選択</option>
											<c:forEach var="vac" items="${list_vac}">
												<option value="${vac.vac_name}" ${att_picked.att_deduction == vac.vac_name ? 'selected' : ''}>${vac.vac_name}</option>
											</c:forEach>
									</select></td>
								</tr>

								<tr>
									<td>근로시간연계</td>
									<td><select name="att_conn">
											<option value="선택하세요" ${att_picked.att_conn == '선택하세요' ? 'selected' : ''}>선택하세요</option>
											<option value="소정근로" ${att_picked.att_conn == '소정근로' ? 'selected' : ''}>소정근로</option>
											<option value="연장근로" ${att_picked.att_conn == '연장근로' ? 'selected' : ''}>연장근로</option>
											<option value="야간근로" ${att_picked.att_conn == '야간근로' ? 'selected' : ''}>야간근로</option>
											<option value="휴일근로" ${att_picked.att_conn == '휴일근로' ? 'selected' : ''}>휴일근로</option>
									</select></td>
								</tr>

								<tr>
									<td>사용여부</td>
									<td>
										<form action method="get">
											<input type="radio" name="att_used" value="사용" ${att_picked.att_used == '사용' ? 'checked' : ''} checked>
											사용<br> <input type="radio" name="att_used" value="사용안함" ${att_picked.att_used == '사용안함' ? 'checked' : ''}>
											사용안함<br>
										</form>
									</td>
							</table>
									<div style="display: flex; justify-content: flex-start; gap: 10px;">
										<input type='submit' value='저장'>
								</form>

								<form action="delVac.do" method="post">
									<input type='hidden' name="del_att_name" value="${att_picked.att_name}">
									<input type='submit' value='삭제'>
								</form>
								<form action="delVac.do" method="post">
									<input type='hidden' name="update_att_name" value="${att_picked.att_name}">
									<input type='submit' value='수정'>
								</form>
								 <button type="reset">내용 지우기</button>
					</div>  
    </body>
</html>