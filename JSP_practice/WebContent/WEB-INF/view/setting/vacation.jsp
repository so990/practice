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

<title>休暇項目 設定</title>

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
				<h2>休暇項目 設定</h2> <br />
				<div style="width: 51%; float: left;">
					<table width=90% table border="1">
						<tr>
							<td>休暇項目</td>
							<td>適用期間</td>
							<td>社員別 休暇日数</td>
							<td>使用可否</td>
							<td></td>
						</tr>

						<c:forEach var="vac" items="${list_vac}">
							<tr>
								<td>${vac.vac_name}</td>
								<td>${vac.vac_start}~${vac.vac_end}</td>
								<td>
									<button type="button" class="btn btn-outline-secondary" onclick="window.open('vacDays.do', '_blank', 'width=800,height=800');">休暇日数 管理</button>
								</td>
								<td>${vac.vac_used}</td>

								<form action="att.do" method="post">
									<td><input type="hidden" name="vac_name_picked" value="${vac.vac_name}"> 
									<input type='submit' name="vac_button" value="選択"></td>
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
								<td><input type='text' name='vac_name' value='${vac_picked.vac_name }'></td>
							</tr>
							<tr>
								<td>適用期間</td>
								<td colspan='3'><input type="date" name="vac_start" value='${vac_picked.vac_start }'> 
								~ <input type="date" name="vac_end" value='${vac_picked.vac_end }'></td>
							</tr>

							<tr>
								<td>使用可否</td>
								<td>
									<form action method="get">
										<input type="radio" name="vac_used" value="使用" ${vac_picked.vac_used == '使用' ? 'checked' : ''} checked> 使用<br> 
										<input type="radio" name="vac_used" value="使用しない" ${vac_picked.vac_used == '使用しない' ? 'checked' : ''}> 使用しない<br>
									</form>
								</td>
							</tr>
						</table>
						<div style="display: flex; justify-content: flex-start; gap: 10px;">
							<input type='submit' name="vac_button" value='保存'> 
							<input type='hidden' name="vac_before_name" value="${vac_picked.vac_name}"> 
							<input type='submit' name="vac_button" value='修正'>
					</form>

					<form action="delVac.do" method="post">
						<input type='hidden' name="del_vac_name" value="${vac_picked.vac_name}"> 
						<input type='submit' value='削除'>
					</form>

					<form action="" method="get">
						<button type="submit">内容を消す</button>
					</form>
				</div></td>
		</tr>
		<!-- --------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------- -->

		<table width='100%' border='0'>
			<tr>
				<td><br />
					<h2>勤怠項目 設定</h2> <br />
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
										<td><input type="hidden" name="att_name_picked" value="${att.att_name}"> 
											<input type='submit' name="att_button" value="選択">
										</td>
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
									<td><input type='text' name='att_name' value='${att_picked.att_name }'></td>
								</tr>

								<tr>
									<td>単位</td>
									<td><select name="att_unit">
											<option value="選択" ${att_picked.att_unit == '選択' ? 'selected' : ''}>選択</option>
											<option value="日" ${att_picked.att_unit == '日' ? 'selected' : ''}>日</option>
											<option value="時間" ${att_picked.att_unit == '時間' ? 'selected' : ''}>時間</option>
									</select></td>
								</tr>

								<tr>
									<td>勤怠グループ</td>
									<td><select name="att_grp">
											<option value="選択" ${att_picked.att_grp == '選択' ? 'selected' : ''}>選択</option>
											<option value="休暇" ${att_picked.att_grp == '休暇' ? 'selected' : ''}>休暇</option>
											<option value="延長勤務" ${att_picked.att_grp == '延長勤務' ? 'selected' : ''}>延長勤務</option>
											<option value="遅刻・早退" ${att_picked.att_grp == '遅刻・早退' ? 'selected' : ''}>遅刻・早退</option>
											<option value="特勤" ${att_picked.att_grp == '特勤' ? 'selected' : ''}>特勤</option>
											<option value="他" ${att_picked.att_grp == '他' ? 'selected' : ''}>他</option>
									</select></td>
								</tr>

								<tr>
									<td>休暇控除</td>
									<td><select name="att_deduction">
											<option value="選択" ${att_picked.att_deduction == '選択' ? 'selected' : ''}>選択</option>
											<c:forEach var="vac" items="${list_vac}">
												<option value="${vac.vac_name}" ${att_picked.att_deduction == vac.vac_name ? 'selected' : ''}>${vac.vac_name}</option>
											</c:forEach>
									</select></td>
								</tr>

								<tr>
									<td>勤労時間連携</td>
									<td><select name="att_conn">
											<option value="選択" ${att_picked.att_conn == '選択' ? 'selected' : ''}>選択</option>
											<option value="所定勤労" ${att_picked.att_conn == '所定勤労' ? 'selected' : ''}>所定勤労</option>
											<option value="延長勤労" ${att_picked.att_conn == '延長勤労' ? 'selected' : ''}>延長勤労</option>
											<option value="夜間勤労" ${att_picked.att_conn == '夜間勤労' ? 'selected' : ''}>夜間勤労</option>
											<option value="休日勤労" ${att_picked.att_conn == '休日勤労' ? 'selected' : ''}>休日勤労</option>
									</select></td>
								</tr>

								<tr>
									<td>使用可否</td>
									<td>
										<form action method="get">
											<input type="radio" name="att_used" value="使用" ${att_picked.att_used == '使用' ? 'checked' : ''} checked> 使用<br> 
											<input type="radio" name="att_used" value=" 使用しない" ${att_picked.att_used == ' 使用しない' ? 'checked' : ''}> 使用しない<br>
										</form>
									</td>
							</table>
							<div
								style="display: flex; justify-content: flex-start; gap: 10px;">
								<input type='submit' name="att_button" value='保存'> 
								<input type='hidden' name="att_before_name" value="${att_picked.att_name}"> 
								<input type='submit' name="att_button" value='修正'>
						</form>

						<form action="delVac.do" method="post">
							<input type='hidden' name="del_att_name" value="${att_picked.att_name}"> 
							<input type='submit' value='削除'>
						</form>

						<form action="" method="get">
							<button type="submit">内容を消す</button>
						</form>
					</div>
</body>
</html>