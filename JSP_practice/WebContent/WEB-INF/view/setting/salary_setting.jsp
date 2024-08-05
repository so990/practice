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

<!-- 비과세 선택시 hidden 구현 -->
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
        alert('사용자 정보가 저장되었습니다.');
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
			<h3>지급항목 설정</h3>
				<br />
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
						<form action="salitems.do" method="post">
								<td><input type="hidden" name="pay_name_picked" value="${pay.pay_name}"><input type='submit' value="選択"></td>
						</form>
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
									value='${pay_picked.pay_name}'>&nbsp&nbsp&nbsp&nbsp</td>
							</tr>

							<tr>
								<td>과세여부</td>
								<td colspan='3'><input type="radio" name="pay_tax"
									value="전체과세" checked> 전체과세<br> <input type="radio"
									name="pay_tax" value="비과세"> 비과세<br></td>
							</tr>
							<tr class="tax-free-field">
								<td>비과세명</td>
								<td><input type='text' name='tax_free_name'
									value='${payment_items.tax_free_name }'></td>
							</tr>
							<tr class="tax-free-field">
								<td>비과세 한도액</td>
								<td><input type='text' name='tax_free_limit'
									value='${payment_items.tax_free_limit }'>원</td>
							</tr>
							<tr>
								<td>계산방법</td>
								<td><input type='text' name='tax_memo'
									value='${pay_picked.tax_memo}'></td>
							</tr>

							<tr>
								<td>절사단위</td>
								<td><select name="cut_unit">
										<option value="없음">없음</option>
										<option value="1">1원 단위</option>
										<option value="10">10원 단위</option>
										<option value="100">100원 단위</option>
								</select></td>
							</tr>
							<tr>
								<td>근태연결/일괄지급</td>
								<td><select name="attend_conn">
										<option value="선택하세요">선택하세요</option>
										<c:forEach var="att" items="${list_att}">
											<option value="${att.att_name}">${att.att_name}</option>
										</c:forEach>
										<option value="일괄지급">일괄지급</option>
								</select></td>
							</tr>
							<tr>
								<td>사용여부</td>
								<td><input type="radio" name="pay_used" value="사용" checked>
									사용<br> <input type="radio" name="pay_used" value="사용안함">
									사용안함<br></td>
							</tr>
						</table>
						<input type='submit' value='저장'>
					</form>	
					
					<form action="delSal.do" method="post">
						<input type='hidden' name="del_sal_name" value="${pay_picked.pay_name}"><input type='submit' value='삭제'>
					</form>
					<form action="delSal.do" method="post">
						<input type='hidden' name="update_sal_name" value="${pay_picked.pay_name}"><input type='submit' value='수정'>
					</form>
					
				</div>
				</div> <br> <br> <br> <br> <br> <br> <br>
				<br> <br> <br> <br> <br> <br>
				<table width='100%' border='0'>
					<tr>
						<br>
						<br>
						<td><br>
							<h3>공제항목 설정</h3>
							<br>
							<div style="width: 51%; float: left;">
								<table width=90% table border="1">
									<tr>
										<th>공제항목</th>
										<th>절사단위</th>
										<th>사용여부</th>
										<th>비고</th>
									</tr>
									<c:forEach var="ded" items="${list_ded}">
										<tr>
											<td>${ded.ded_name}</td>
											<td>${ded.ded_cut_unit}</td>
											<td>${ded.ded_used}</td>
											<td>${ded.ded_note}</td>
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
												value='${deduction_items.ded_name }'></td>
										</tr>

										<tr>
											<td>계산방법</td>
											<td><input type='text' name='ded_memo'
												value='${deduction_items.ded_memo }'></td>
										</tr>

										<tr>
											<td>절사단위</td>
											<td><select name="ded_cut_unit">
													<option value="없음">없음</option>
													<option value="1">1원 단위</option>
													<option value="10">10원 단위</option>
													<option value="100">100원 단위</option>
											</select></td>
										</tr>

										<tr>
											<td>비고</td>
											<td><input type='text' name='ded_note'
												value='${deduction_items.ded_note }'></td>
										</tr>

										<tr>
											<td>사용여부</td>
											<td><input type="radio" name="ded_used" value="사용"
												checked> 사용<br> <input type="radio"
												name="ded_used" value="사용안함"> 사용안함<br></td>
										</tr>

									</table>
									<input type='submit' value='저장'>
								</form>
							</div>
							</div>
</body>
</html>