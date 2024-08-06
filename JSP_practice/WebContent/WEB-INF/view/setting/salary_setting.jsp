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
<title>給与項目 設定</title>

<!-- 비과세 선택시 hidden 구현 -->
<script>
    function toggleTaxFields() {
        const taxFreeFields = document.querySelectorAll('.tax-free-field');
        const isTaxFree = document.querySelector('input[name="pay_tax"]:checked').value === '非課税';

        taxFreeFields.forEach(field => {
            field.style.display = isTaxFree ? 'table-row' : 'none';
        });
    }

    window.onload = function() {
        // Initialize the visibility based on the default checked radio button
        toggleTaxFields();

        // Attach event listeners to radio buttons
        document.querySelectorAll('input[name="pay_tax"]').forEach(radio => {
            radio.addEventListener('change', toggleTaxFields);
        });
    }
</script>

</head>

<body>
	<table width='100%' border='0'>
		<tr height='100'>
			<td align='left'><%@ include file="../../../Site_header.jsp"%></td>
		</tr>
		<tr>
			<td align='left'><%@ include file="../../../Site_menu.jsp"%></td>
		</tr>
		<tr>
			<td><br />
				<h3>支給項目設定</h3> <br />
				<div style="width: 51%; float: left;">
					<table width=90% table border="1">
						<tr>
							<th>支給項目</th>
							<th>課税可否</th>
							<th>非課税限度額</th>
							<th>切捨単位</th>
							<th>勤怠連絡・一括支給</th>
							<th>使用可否</th>
							<th></th>
						</tr>
						
						<c:forEach var="pay" items="${list_pay}">
							<tr>
								<td>${pay.pay_name}</td>
								<td>${pay.pay_tax}</td>
								<td>${pay.tax_free_limit}</td>
								<td>${pay.cut_unit}</td>
								<td>${pay.attend_conn}</td>
								<td>${pay.pay_used}</td>


								<td>
									<form action="salitems.do" method="post">
										<input type="hidden" name="pay_name_picked" value="${pay.pay_name}"> 
										<input type='submit' name="pay_button" value="選択">
									</form>
								</td>

							</tr>
						</c:forEach>
					</table>
				</div>
				<div style="width: 49%; float: right;">
					<form action="salitems.do" method='post'>
						<table border='1'>
							<tr>
								<td>支給項目</td>
								<td><input type='text' name='pay_name' value='${pay_picked.pay_name}' required>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							</tr>

							<tr>
								<td>課税可否</td>
								<td colspan='3'>
								<input type="radio" name="pay_tax" value=" 全体課税" ${pay_picked.pay_tax == ' 全体課税' ? 'checked' : ''} checked> 全体課税<br> 
								<input type="radio" name="pay_tax" value="非課税" ${pay_picked.pay_tax == '非課税' ? 'checked' : ''}> 非課税<br>
								</td>
							</tr>
							<tr class="tax-free-field">
								<td>非課税名</td>
								<td><input type='text' name='tax_free_name' value='${pay_picked.tax_free_name }'></td>
							</tr>
							<tr class="tax-free-field">
								<td>非課税限度額</td>
								<td><input type='text' name='tax_free_limit' value='${pay_picked.tax_free_limit }'>ウォン</td>
							</tr>
							<tr>
								<td>計算方法</td>
								<td><input type='text' name='tax_memo' value='${pay_picked.tax_memo}'></td>
							</tr>

							<tr>
								<td>切捨単位</td>
								<td><select name="cut_unit">
										<option value="なし" ${pay_picked.cut_unit == 'なし' ? 'selected' : ''}>なし</option>
										<option value="1" ${pay_picked.cut_unit == '1' ? 'selected' : ''}>1ウォン単位</option>
										<option value="10" ${pay_picked.cut_unit == '10' ? 'selected' : ''}>10ウォン単位</option>
										<option value="100" ${pay_picked.cut_unit == '100' ? 'selected' : ''}>100ウォン単位</option>
								</select></td>
							</tr>
							<tr>
								<td>勤怠連絡・一括支給</td>
								<td><select name="attend_conn">
										<option value="選択">選択</option>
										<c:forEach var="att" items="${list_att}">
											<option value="${att.att_name}" ${pay_picked.attend_conn == att.att_name ? 'selected' : ''}>${att.att_name}</option>
										</c:forEach>
											<option value="一括支給" ${pay_picked.attend_conn == '一括支給' ? 'selected' : ''}>一括支給</option>
								</select></td>
							</tr>
							<tr>
								<td>使用可否</td>
								<td><input type="radio" name="pay_used" value="使用" ${pay_picked.pay_used == '使用' ? 'checked' : ''} checked> 使用<br>
									<input type="radio" name="pay_used" value="使用しない" ${pay_picked.pay_used == '使用しない' ? 'checked' : ''}> 使用しない<br>
								</td>
							</tr>
						</table>

						<div style="display: flex; justify-content: flex-start; gap: 10px;">
							<input type='submit' name='pay_button' value="保存"> 
							<input type='hidden' name="pay_before_name" value="${pay_picked.pay_name}"> 
							<input type='submit' name='pay_button' value='修正'>
					</form>

					<form action="delSal.do" method="post">
						<input type='hidden' name="del_sal_name" value="${pay_picked.pay_name}">
						<input type='submit' value='削除'>
					</form>
					<form action="" method="get">
						<button type="submit">内容を消す</button>
					</form>
				</div>
				</div> 
				<br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br><!-- ------------------------------------------- -->
				<table width='100%' border='0'>
					<tr>
						<br>
						<br>
						<td><br>
							<h3>控除項目設定</h3> <br>
							<div style="width: 51%; float: left;">
								<table width=90% table border="1">
									<tr>
										<th>控除項目</th>
										<th>切捨単位</th>
										<th>使用可不</th>
										<th>備考</th>
										<th></th>
									</tr>
									<c:forEach var="ded" items="${list_ded}">
										<tr>
											<td>${ded.ded_name}</td>
											<td>${ded.ded_cut_unit}</td>
											<td>${ded.ded_used}</td>
											<td>${ded.ded_note}</td>
											
											<form action="salitems.do" method="post">
												<td><input type="hidden" name="ded_name_picked" value="${ded.ded_name}"> 
												<input type='submit' name="ded_button" value="選択"></td>
											</form>
										</tr>
									</c:forEach>
								</table>
							</div>
							<div style="width: 49%; float: right;">
								<form action="salitems.do" method='post'>
									<table border='1'>
										<tr>
											<td>控除項目</td>
											<td><input type='text' name='ded_name' value='${ded_picked.ded_name }'></td>
										</tr>

										<tr>
											<td>計算方法</td>
											<td><input type='text' name='ded_memo' value='${ded_picked.ded_memo }'></td>
										</tr>

										<tr>
											<td>切捨単位</td>
											<td><select name="ded_cut_unit">
													<option value="なし" ${ded_picked.ded_cut_unit == 'なし' ? 'selected' : ''}>なし</option>
													<option value="1" ${ded_picked.ded_cut_unit == '1' ? 'selected' : ''}>1ウォン単位</option>
													<option value="10" ${ded_picked.ded_cut_unit == '10' ? 'selected' : ''}>10ウォン単位</option>
													<option value="100" ${ded_picked.ded_cut_unit == '100' ? 'selected' : ''}>100ウォン単位</option>
											</select></td>
										</tr>

										<tr>
											<td>備考</td>
											<td><input type='text' name='ded_note' value='${ded_picked.ded_note }'></td>
										</tr>

										<tr>
											<td>使用可否</td>
											<td><input type="radio" name="ded_used" value="使用" ${ded_picked.ded_used == '使用' ? 'checked' : ''} checked> 使用<br> 
											<input type="radio" name="ded_used" value="使用しない" ${ded_picked.ded_used == '使用しない' ? 'checked' : ''}> 使用しない<br></td>
										</tr>

									</table>
									<div style="display: flex; justify-content: flex-start; gap: 10px;">

										<input type='submit' name="ded_button" value='保存'> 
										<input type='hidden' name="ded_before_name" value="${ded_picked.ded_name}"> 
										<input type='submit' name="ded_button" value='修正'>
								</form>

								<form action="delSal.do" method="post">
									<input type='hidden' name="del_ded_name" value="${ded_picked.ded_name}"> 
									<input type='submit' value='削除'>
								</form>
								<form action="" method="get">
									<button type="submit">内容消す</button>
								</form>
							</div></td>
					</tr>
				</table></td>
		</tr>
	</table>
</body>
</html>