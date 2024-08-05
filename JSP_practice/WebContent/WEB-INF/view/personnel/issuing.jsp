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
		<title>제 증명서 발급</title>
		
		<script>
	        function successJoin() {
	            alert('증명서대장에 저장되었습니다.');
	            }
	            
	    </script>
		
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
				<td align='left'>
					<!--  -->
					
					<h2>&nbsp;&nbsp;&nbsp;제 증명서 발급</h2>
					
					<div style="width: 30%; float: left;">
						<br/><br/><br/>
						<table width=80% border=1>
							<tr>
								<td>구분</td><td>성명</td><td>부서</td><td>직위</td><td>상태</td><td>선택</td>
							</tr>
							<c:forEach var='emp' items="${emp_list}">
							<form action='issuing.do' method='post'>
							<tr>
								<td>${emp.emp_type}</td><td>${emp.name_kor}</td><td>${emp.dept}</td><td>${emp.job}</td><td>${emp.state}</td>
								<td><input type=hidden name='emp_no' value='${emp.emp_no}'><input type=submit value='선택'></td>
							</tr>
							</form>
							</c:forEach>
						</table>
					</div>
					
					<div style="width: 70%; float: right;">
						<div align='left'>
						<br/>
						
						<form action='saveIssuing.do' method='post' onSubmit='successJoin()'>
						
							<div align='left'>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type='radio' name='isu_led' value='재직' checked>재직증명서&nbsp;&nbsp;
							<!-- 	<input type='radio' name='isu_led' value='경력'>경력증명서&nbsp;&nbsp;
								<input type='radio' name='isu_led' value='퇴직'>퇴직증명서 -->
							</div>
							<br/>
							<table width=60% border=1>
								<tr>
									<td style="height:50px" colspan=5 align=center><h2><b>재직증명서</b></h2></td>
								</tr>
								<tr>
									<td style="height:80px" rowspan=2>인적사항</td><td>성명</td><td>${emp_pick.name_kor}</td><td>주민등록번호</td><td>${emp_pick.id_number}</td>
								</tr>
								<tr>
									<td>주소</td><td colspan=3>${emp_pick.addr}</td>
								</tr>
								<tr>
									<td style="height:150px" rowspan=3>재직사항</td><td>회사명</td><td>${cpn.cp_name}</td><td>사업자번호</td><td>${cpn.bs_num}</td>
								</tr>
								<tr>
									<td>부서</td><td>${emp_pick.dept}</td><td>직위</td><td>${emp_pick.job}</td>
								</tr>
								<tr>
									<td>입사일</td><td>${emp_pick.hired_date}</td><td>근속기간</td><td>-</td>
								</tr>
								<tr>
									<td style="height:80px" colspan=2 align=center>발급용도</td><td colspan=3>
									<select name="isu_pur">
										<option value="">선택하세요</option>
										<option value="비자 신청용">비자 신청용</option>
										<option value="은행 제출용">은행 제출용</option>
										<option value="관공서 제출용">관공서 제출용</option>
										<option value="학교 제출용">학교 제출용</option>
										<option value="병무청 제출용">병무청 제출용</option>
										<option value="관련과제 제출용">관련과제 제출용</option>
									</select>
									</td>
								</tr>
								<tr>
									<td style="height:300px" align='center' colspan=5> 
										<textarea name="note" cols='80' rows='15'>상기인은 재직증명서 발급일 현재 위와 같이 당상 재직하고 있음을 증명합니다.</textarea>
										<br/><br/>					
										발급일: <input type='text' name='isu_date' value="${today}" readonly>
									</td>
								</tr>
								<tr>
									<td style="height:40px" colspan=2>발급부서</td>
									<td>
									<select name="dept">
										<option value="">선택하세요</option>
										<option value="사장실">사장실</option>
										<option value="개발팀">개발팀</option>
										<option value="콘텐츠팀">콘텐츠팀</option>
										<option value="업무지원팀">업무지원팀</option>
										<option value="디자인팀">디자인팀</option>
										<option value="관리팀">관리팀</option>
										<option value="기획전략팀">기획전략팀</option>
									</select>
									</td>
										<td>연락처</td><td>[全国]3939-4649</td>
									</tr>
									<tr>
										<td style="height:50px" colspan=5 align=center><h3><b>(株)JSPプロジェクト</b></h3></td>
									</tr>
							
							</table>
							
							<br/><br/>
							
							<input type=hidden name="emp_isu" value='${emp_pick.emp_no}'>
							
							<input type='submit' value='저장'> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type='button' value='인쇄'>
							
						</form>
						
					</div>
					</div>
					
					<!--  -->
				</td>
			</tr>
			
		</table> <br/><br/><br/><br/>
	</body>
</html>



