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
<title>급여항목 설정</title>

<!-- 비과세 選択시 hidden 구현 -->
<script>
    function toggleTaxFields() {
        const taxFreeFields = document.querySelectorAll('.tax-free-field');
        const isTaxFree = document.querySelector('input[name="pay_tax"]:checked').value === '비과세';

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

<script>
    function successJoin() {
        alert('사용자 정보가 保存되었습니다.');
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
				<h3>지급항목 설정</h3> <br />
				<div style="width: 51%; float: left;">
					<table width=90% table border="1">
						<tr>
							<th>지급항목</th>
							<th>과세여부</th>
							<th>비과세한도액</th>
							<th>절사단위</th>
							<th>근태연결/일괄지급</th>
							<th>사용여부</th>
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
										<input type="hidden" name="pay_name_picked"
											value="${pay.pay_name}"> <input type='submit'
											name="pay_button" value="選択">
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
								<td>지급항목</td>
								<td><input type='text' name='pay_name'
									value='${pay_picked.pay_name}'></td>
							</tr>

							<tr>
								<td>과세여부</td>
								<td colspan='3'><input type="radio" name="pay_tax"
									value="전체과세" ${pay_picked.pay_tax == '전체과세' ? 'checked' : ''}
									checked> 전체과세<br> <input type="radio"
									name="pay_tax" value="비과세"
									${pay_picked.pay_tax == '비과세' ? 'checked' : ''}> 비과세<br>
								</td>
							</tr>
							<tr class="tax-free-field">
								<td>비과세명</td>
								<td><input type='text' name='tax_free_name'
									value='${pay_picked.tax_free_name }'></td>
							</tr>
							<tr class="tax-free-field">
								<td>비과세 한도액</td>
								<td><input type='text' name='tax_free_limit'
									value='${pay_picked.tax_free_limit }'>원</td>
							</tr>
							<tr>
								<td>계산방법</td>
								<td><input type='text' name='tax_memo'
									value='${pay_picked.tax_memo}'></td>
							</tr>

							<tr>
								<td>절사단위</td>
								<td><select name="cut_unit">
										<option value="없음"
											${pay_picked.cut_unit == '없음' ? 'selected' : ''}>없음</option>
										<option value="1"
											${pay_picked.cut_unit == '1' ? 'selected' : ''}>1원
											단위</option>
										<option value="10"
											${pay_picked.cut_unit == '10' ? 'selected' : ''}>10원
											단위</option>
										<option value="100"
											${pay_picked.cut_unit == '100' ? 'selected' : ''}>100원
											단위</option>
								</select></td>
							</tr>
							<tr>
								<td>근태연결/일괄지급</td>
								<td><select name="attend_conn">
										<option value="選択하세요">선택하세요</option>
										<c:forEach var="att" items="${list_att}">
											<option value="${att.att_name}"
												${pay_picked.attend_conn == att.att_name ? 'selected' : ''}>${att.att_name}</option>
										</c:forEach>
										<option value="일괄지급"
											${pay_picked.attend_conn == '일괄지급' ? 'selected' : ''}>일괄지급</option>
								</select></td>
							</tr>
							<tr>
								<td>사용여부</td>
								<td><input type="radio" name="pay_used" value="사용"
									${pay_picked.pay_used == '사용' ? 'checked' : ''} checked> 사용<br>
									<input type="radio" name="pay_used" value="사용안함"
									${pay_picked.pay_used == '사용안함' ? 'checked' : ''}> 사용안함<br>
								</td>
							</tr>
						</table>

						<div
							style="display: flex; justify-content: flex-start; gap: 10px;">
							<input type='submit' name='pay_button' value="保存"> <input
								type='hidden' name="pay_before_name"
								value="${pay_picked.pay_name}"> <input type='submit'
								name='pay_button' value='修正'>
					</form>

					<form action="delSal.do" method="post">
						<input type='hidden' name="del_sal_name"
							value="${pay_picked.pay_name}"><input type='submit'
							value='削除'>
					</form>
					<form action="" method="get">
						<button type="submit">内容消す</button>
					</form>
				</div>
				</div> <br> <br> <br> <br> <br> <br> <br>
				<br> <br> <br> <br> <br> <br>
				<table width='100%' border='0'>
					<tr>
						<br>
						<br>
						<td><br>
							<h3>공제항목 설정</h3> <br>
							<div style="width: 51%; float: left;">
								<table width=90% table border="1">
									<tr>
										<th>공제항목</th>
										<th>절사단위</th>
										<th>사용여부</th>
										<th>비고</th>
										<th></th>
									</tr>
									<c:forEach var="ded" items="${list_ded}">
										<tr>
											<td>${ded.ded_name}</td>
											<td>${ded.ded_cut_unit}</td>
											<td>${ded.ded_used}</td>
											<td>${ded.ded_note}</td>
											<form action="salitems.do" method="post">
												<td><input type="hidden" name="ded_name_picked"
													value="${ded.ded_name}"> <input type='submit'
													name="ded_button" value="選択"></td>
											</form>
										</tr>
									</c:forEach>
								</table>
							</div>
							<div style="width: 49%; float: right;">
								<form action="salitems.do" method='post'>
									<table border='1'>
										<tr>
											<td>공제항목</td>
											<td><input type='text' name='ded_name'
												value='${ded_picked.ded_name }'></td>
										</tr>

										<tr>
											<td>계산방법</td>
											<td><input type='text' name='ded_memo'
												value='${ded_picked.ded_memo }'></td>
										</tr>

										<tr>
											<td>절사단위</td>
											<td><select name="ded_cut_unit">
													<option value="없음"
														${ded_picked.ded_cut_unit == '없음' ? 'selected' : ''}>없음</option>
													<option value="1"
														${ded_picked.ded_cut_unit == '1' ? 'selected' : ''}>1원
														단위</option>
													<option value="10"
														${ded_picked.ded_cut_unit == '10' ? 'selected' : ''}>10원
														단위</option>
													<option value="100"
														${ded_picked.ded_cut_unit == '100' ? 'selected' : ''}>100원
														단위</option>
											</select></td>
										</tr>

										<tr>
											<td>비고</td>
											<td><input type='text' name='ded_note'
												value='${ded_picked.ded_note }'></td>
										</tr>

										<tr>
											<td>사용여부</td>
											<td><input type="radio" name="ded_used" value="사용"
												${ded_picked.ded_used == '사용' ? 'checked' : ''} checked>
												사용<br> <input type="radio" name="ded_used" value="사용안함"
												${ded_picked.ded_used == '사용안함' ? 'checked' : ''}>
												사용안함<br></td>
										</tr>

									</table>
									<div
										style="display: flex; justify-content: flex-start; gap: 10px;">

										<input type='submit' name="ded_button" value='保存'> <input
											type='hidden' name="ded_before_name"
											value="${ded_picked.ded_name}"> <input type='submit'
											name="ded_button" value='修正'>
								</form>

								<form action="delSal.do" method="post">
									<input type='hidden' name="del_ded_name"
										value="${ded_picked.ded_name}"> <input type='submit'
										value='削除'>
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