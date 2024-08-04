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
				<form action="empSet.do" method="get">
					<table width='100%'>
						<tr>
							<td><select name="searchCategori">
									<option value="all" selected>전체</option>
									<option value="name_kor">名前</option>
									<option value="emp_no">社員番号</option>
									<option value="dept">部署</option>
							</select> <input type="text" placeholder="検索語を入力してください。" name="searchWord">
								<input type="submit" name="button" value="検索"> <input
								type="submit" name="button" value="전체보기"> <spanstyle
									="float:right;"> <select>
									<option value="all" selected>고용형태별</option>
									<option value="정규직">정규직</option>
									<option value="계약직">계약직</option>
									<option value="임시직">임시직</option>
									<option value="파견직">파견직</option>
									<option value="위촉직">위촉직</option>
									<option value="일용직">일용직</option>
								</select> <select>
									<option value="all">상태별</option>
									<option value="on" selected>재직</option>
									<option value="off">퇴직</option>
								</select> <select>
									<option value="all">리스트 수</option>
									<option value="10">10개씩 보기</option>
									<option value="30" selected>30개씩 보기</option>
									<option value="50" selected>50개씩 보기</option>
									<option value="100" selected>100개씩 보기</option>
								</select> </span></td>
						</tr>
						</form>
						<tr>
							<td>
								<table width='100%' border="1">
									<tr align="center">

										<td></td>
										<td><strong>구분</strong></td>
										<td><strong>입사일</strong></td>
										<td><strong>사원번호</strong></td>
										<td><strong>名前</strong></td>
										<td><strong>部署</strong></td>
										<td><strong>職位</strong></td>
										<td><strong>주민번호</strong></td>
										<td><strong>휴대폰</strong></td>
										<td><strong>이메일</strong></td>
										<td><strong>퇴사일</strong></td>
										<td><strong>상태</strong></td>
									</tr>

									<c:forEach var="empset" items="${list_empset}">
										<form action="register.do" method=post>
										<tr>
											<td><input type=hidden name="emp_search"
												value="${empset.emp_no}"><input type=submit
												value="상세보기"></td>
											<td>${empset.emp_type}</td>
											<td>${empset.hired_date}</td>
											<td>${empset.emp_no}</td>
											<td>${empset.name_kor}</td>
											<td>${empset.dept}</td>
											<td>${empset.job}</td>
											<td>${empset.id_number}</td>
											<td>${empset.phone}</td>
											<td>${empset.email}</td>
											<td>${empset.retired_date}</td>
											<td>${empset.state}</td>
										</tr>
										</form>
									</c:forEach>
								</table>
							</td>
						</tr>
					</table>
			</td>
		</tr>
	</table>
	</div>
	</td>
	</tr>
</body>
</html>