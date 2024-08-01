<!-- 모달 두 개 다 잘 뜨는 코드 -->

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
<title>社員退職処理</title>
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
</head>
<body>
	<table width='100%'>
		<tr height='100'>
			<td align='left'><%@ include file="../../../Site_header.jsp"%></td>
		</tr>
		<tr>
			<td align='left'><%@ include file="../../../Site_menu.jsp"%></td>
		</tr>
		<tr>
			<td>
				<form action="${pageContext.request.contextPath}/retireProcess.do" method="get">
					<table width='100%'>
						<tr>
							<td>
								<select name="searchCategori">
									<option value="name_kor" selected>名前</option>
									<option value="emp_no">社員番号</option>
									<option value="dept">部署</option>
									<option value="all">전체</option>
								</select>
								<input type="text" placeholder="検索語を入力してください。" name="searchWord">
								<input type="submit" name="button" value="検索">
								<input type="submit" name="button" value="전체보기">
								<span style="float: right;">
									<select>
										<option value="all" selected>상태별</option>
										<option value="on">재직</option>
										<option value="off">퇴직</option>
									</select>
								</span>
							</td>
						</tr>
						<tr>
							<td>
								<table width='100%' border="1">
									<tr>
										<td align="center"><strong>番号</strong></td>
										<td align="center"><strong>状態</strong></td>
										<td align="center"><strong>社員番号</strong></td>
										<td align="center"><strong>名前</strong></td>
										<td align="center"><strong>部署</strong></td>
										<td align="center"><strong>職位</strong></td>
										<td align="center"><strong>入社日</strong></td>
										<td align="center"><strong>退職日</strong></td>
										<td align="center"><strong>근속연수</strong></td>
										<td align="center"><strong>중간정산</strong></td>
										<td align="center"><strong>퇴직정산</strong></td>
									</tr>
									<c:forEach var="n" items="${list}" begin="0" end="13" varStatus="st">
										<tr class="btn-open-modal" data-state="${n.state}" data-emp_no="${n.emp_no}" data-name="${n.name_kor}" data-dept="${n.dept}" data-job="${n.job}" data-hired_date="${n.hired_date}" data-retired_date="${n.retired_date}" data-years_service="${n.years_service}" data-ret_calc_type_mid="${n.ret_calc_type_mid}" data-ret_calc_type_retire="${n.ret_calc_type_retire}" data-retire_type="${n.retire_type}" data-retire_date="${n.retire_date}" data-retire_reason="${n.retire_reason}" data-retire_phone="${n.retire_phone}">
											<td align="center">${st.index+1}</td>
											<td align="center">
												<c:if test="${n.state eq '재직'}">${n.state}</c:if>
												<c:if test="${n.state eq '퇴직'}">
													<span style="color: red"><strong>${n.state}</strong></span>
												</c:if>
											</td>
											<td align="center">${n.emp_no}</td>
											<td align="center">${n.name_kor}</td>
											<td align="center">${n.dept}</td>
											<td align="center">${n.job}</td>
											<td align="center">${n.hired_date}</td>
											<td align="center">${n.retired_date}</td>
											<td align="center">${n.years_service}</td>
											<td align="center"><c:if test="${n.ret_calc_type_mid eq '중간정산'}">O</c:if> <c:if test="${empty n.ret_calc_type_mid}">X</c:if></td>
											<td align="center"><c:if test="${n.ret_calc_type_retire eq '퇴직정산'}">O</c:if> <c:if test="${empty n.ret_calc_type_retire}">X</c:if></td>
										</tr>
									</c:forEach>
									
									
								</table>
							</td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>

	

	<!-- 퇴직 처리 모달 -->
	<div class="modal" id="retireModal">
		<div class="modal_body">
			<span class="modal_close">&times;</span>
			<h2>퇴사자 퇴직처리</h2>
			<div>
				<form action="${pageContext.request.contextPath}/retireProcess.do" method="post">
					<input type="hidden" name="emp_no" id="modal_emp_no" />
					
					<table border="1">
						<tr>
							<td>대상 사원</td>
							<td><input type="text" name="retire_emp_name" id="modal_retire_emp_name" value="" readonly/></td>
						</tr>
						<tr>
							<td>퇴직구분</td>
							<td>
								<select id="modal_retirement_type" name="retirement_type">
									<option selected>선택</option>
									<option value="정년퇴직">정년퇴직</option>
									<option value="정리해고">정리해고</option>
									<option value="자발적 퇴직">자발적 퇴직</option>
									<option value="임원퇴직">임원퇴직</option>
									<option value="중간정산">중간정산</option>
									<option value="기타">기타</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>퇴직일자</td>
							<td><input type="date" id="modal_retirement_date" name="retirement_date" /></td>
						</tr>
						<tr>
							<td>퇴직사유</td>
							<td><input type="text" id="modal_retire_reason" name="retire_reason" /></td>
						</tr>
						<tr>
							<td>퇴직 후 연락처</td>
							<td><input type="text" id="modal_retire_phone" name="retire_phone" /></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input type="submit" name="modalButton" value="저장" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>

	<!-- 퇴직 처리 취소 모달 -->
	<div class="modal" id="retireCancelModal">
		<div class="modal_body">
			<span class="modal_close">&times;</span>
			<h2>퇴직 처리 취소</h2>
			<div>
				<form action="${pageContext.request.contextPath}/retireProcess.do" method="post">
					<input type="hidden" name="emp_no" id="cancel_modal_emp_no" />
					<table border="1">
						<tr>
							<td>대상 사원</td>
							<td><input type="text" name="emp_name" id="cancel_modal_emp_name"  readonly/></td>
						</tr>
						<tr>
							<td>퇴직구분</td>
							<td><input type="text" name="retirement_type" id="cancel_modal_retirement_type"  readonly/></td>
						</tr>
						<tr>
							<td>퇴직일자</td>
							<td><input type="date" name="retirement_date" id="cancel_modal_retirement_date" readonly/></td>
						</tr>
						<tr>
							<td>퇴직사유</td>
							<td><input type="text" name="retire_reason" id="cancel_modal_retire_reason" readonly/></td>
						</tr>
						<tr>
							<td>퇴직 후 연락처</td>
							<td><input type="text" name="cancel_retire_phone" id="cancel_modal_retire_phone"  readonly/></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input type="submit" name="modalButton" value="퇴사처리 취소" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>

	<script>
        document.addEventListener('DOMContentLoaded', () => {
            const retireModal = document.querySelector('#retireModal');
            const retireCancelModal = document.querySelector('#retireCancelModal');
            const btnOpenModals = document.querySelectorAll('.btn-open-modal');
            const modalCloseButtons = document.querySelectorAll('.modal_close');

            function openModal(modal, data) {
                if (modal === retireModal) {
                    modal.querySelector('#modal_retire_emp_name').value = data.name || '';
                    modal.querySelector('#modal_emp_no').value = data.emp_no || '';
                    modal.querySelector('#modal_retirement_type').value = ""; // Default or pre-filled value
                    modal.querySelector('#modal_retirement_date').value = "";
                    modal.querySelector('#modal_retire_reason').value = ""; // Default or pre-filled value
                    modal.querySelector('#modal_retire_phone').value = ""; // Default or pre-filled value
                } else if (modal === retireCancelModal) {
                    modal.querySelector('#cancel_modal_emp_name').value = data.name || '';
                    modal.querySelector('#cancel_modal_emp_no').value = data.emp_no || '';
                    modal.querySelector('#cancel_modal_retirement_type').value = data.retire_type || '';
                    modal.querySelector('#cancel_modal_retirement_date').value = data.retired_date || '';
                    modal.querySelector('#cancel_modal_retire_reason').value = data.retire_reason || ''; // Default or pre-filled value
                    modal.querySelector('#cancel_modal_retire_phone').value = data.retire_phone || ''; // Default or pre-filled value
               
                   
                }

                modal.style.display = 'flex';
            }

            btnOpenModals.forEach(row => {
                row.addEventListener('click', () => {
                    const state = row.getAttribute('data-state');
                    const data = {
                        emp_no: row.getAttribute('data-emp_no'),
                        name: row.getAttribute('data-name'),
                        dept: row.getAttribute('data-dept'),
                        job: row.getAttribute('data-job'),
                        hired_date: row.getAttribute('data-hired_date'),
                        retired_date: row.getAttribute('data-retired_date'),
                        years_service: row.getAttribute('data-years_service'),
                        ret_calc_type_mid: row.getAttribute('data-ret_calc_type_mid'),
                        ret_calc_type_retire: row.getAttribute('data-ret_calc_type_retire'),
                        retire_type: row.getAttribute('data-retire_type'),
                        retire_date: row.getAttribute('data-retire_date'),
                        retire_reason: row.getAttribute('data-retire_reason'),
                        retire_phone: row.getAttribute('data-retire_phone')
                    };

                    if (state === '재직') {
                        openModal(retireModal, data);
                    } else if (state === '퇴직') {
                        openModal(retireCancelModal, data);
                    }
                });
            });

            modalCloseButtons.forEach(btn => {
                btn.addEventListener('click', () => {
                    retireModal.style.display = 'none';
                    retireCancelModal.style.display = 'none';
                });
            });

            window.addEventListener('click', (event) => {
               
                if (event.target === retireModal) {
                    retireModal.style.display = 'none';
                }
                if (event.target === retireCancelModal) {
                    retireCancelModal.style.display = 'none';
                }
            });
        });
    </script>

</body>
</html>
