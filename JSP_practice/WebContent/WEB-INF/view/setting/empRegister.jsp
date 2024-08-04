<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page info="Include 테스트" %>
<%@ page buffer="none" %>
<%@ page autoFlush="true" %>
<%@ page isThreadSafe="true" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
		<title>급여관리</title>
		
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
			<tr>
				<td align='left'>
				<h2>사원등록</h2>
				<br/>
			<div style="width: 30%; float: left;">
			
				<form action = "인사기록카드.do" method="post">
					<table width=70% border='1'>
							<tr>
								<td>사원번호</td>
								<td><input type='text' name='emp_no' readonly></td>
							</tr>
							<tr>
								<td>성명</td>
								<td><input type='text' name='emp_name' readonly></td>
							</tr>
							<tr>
								<td>부서</td>
								<td><input type='text' name='dept' readonly></td>
							</tr>
							<tr>
								<td>직위</td>
								<td><input type='text' name='job' readonly></td>
							</tr>
							<tr>
								<td>입사일</td>
								<td><input type='text' name='hired_date' readonly></td>
							</tr>
					</table>
					<br/>
					<input type="submit" value="인사기록카드">	
				</form>
			</div>
			
			<div style="width: 70%; float: right;">
				
				<h3>기본정보</h3>
				<br/>
				<form action="register.do" method="post">	
				
					<table width=90% border='1'>
						<tr>
							<td>사원번호 : </td><td><input type="text" name="emp_no" value="${newEmployeeNo}"></td>
							<td>고용형태 : </td>
							<td><select name="emp_type">
									<option value="">선택하세요</option>
									<option value="정규직">정규직</option>
									<option value="계약직">계약직</option>
									<option value="임시직">임시직</option>
									<option value="파견직">파견직</option>
									<option value="위촉직">위촉직</option>
									<option value="일용직">일용직</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>이름(한글) : </td><td><input type="text" name="name_kor"></td>
							<td>이름(영어) : </td><td><input type="text" name="name_eng"></td>
						</tr>
						<tr>
							<td>입사일 : </td><td><input type="date" name="hired_date"></td>
							<td>퇴사일 : </td><td><input type="date" name="retired_date"></td>
						</tr>
						<tr>
							<td>부서 : </td>
							<td><select name="dept">
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
							<td>직위 : </td>
							<td><select name="job">
									<option value="">선택하세요</option>
									<option value="이사">이사</option>
									<option value="차장">차장</option>
									<option value="사장">사장</option>
									<option value="부장">부장</option>
									<option value="과장">과장</option>
									<option value="대리">대리</option>
									<option value="주임">주임</option>
									<option value="사원">사원</option>
									<option value="실장">실장</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>내/외국인 : </td>
							<td><select name="nationality">
									<option value="">선택하세요</option>
									<option value="내국인">내국인</option>
									<option value="외국인">외국인</option>
								</select>
							</td>
							<td>주민번호 : </td><td><input type="text" name="id_number"></td>
						</tr>
						<tr>
							<td>주소 : </td><td colspan='3'><input type="text" name="post_code">(우편번호) / 
							<input type="text" name="addr"></td>
						</tr>
						<tr>
							<td>전화번호 : </td><td><input type="text" name="home_number"></td>
							<td>휴대폰번호 : </td><td><input type="text" name="phone"></td>
						</tr>
						<tr>
							<td>이메일 : </td><td><input type="text" name="email"></td>
							<td>sns : </td><td><input type="text" name="sns"></td>
						</tr>
						<tr>
							<td>비고 : </td><td colspan='3'><textarea name="note" cols='90' rows='3'></textarea></td>
						</tr>
					</table>
				<br/>
				<h3>급여 & 4대보험</h3>
				<br/>
					<table width='90%' border='1'>
						<tr>
							<td>4대보험 : </td>
							<td>
								<input type='checkbox' name='pension' value='pension'>국민연금 &nbsp;&nbsp;
								<input type='checkbox' name='insur_heal' value='insur_heal'>건강보험(감면 30%) &nbsp;&nbsp;
								<input type='checkbox' name='insur_care' value='insur_care'>노인장기요양보험(감면 30%) &nbsp;&nbsp;
								<input type='checkbox' name='insur_hire' value='insur_hire'>고용보험 &nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td>갑근세</td>
							<td>
								<input type='radio' name='gapgeunse' value='근로소득자'>근로소득자
								세액 : <select name="wage_earner_per">
										<option value="">선택</option>
										<option value="10%">10%</option>
										<option value="30%">30%</option>
										<option value="50%">50%</option>
										<option value="60%">60%</option>
								   	</select> &nbsp;&nbsp;
								<input type='radio' name='gapgeunse' value='근로소득자'>근로소득자&nbsp;&nbsp;
								<input type='radio' name='gapgeunse' value='근로소득자'>근로소득자&nbsp;&nbsp;
								<input type='radio' name='gapgeunse' value='근로소득자'>근로소득자&nbsp;&nbsp;
								<input type='radio' name='gapgeunse' value='근로소득자'>근로소득자&nbsp;&nbsp;
								<input type='radio' name='gapgeunse' value='근로소득자'>근로소득자
								<br/>
								<input type='checkbox' name='youth_red' value='youth_red'>중소기업 청년소득세 감면(감면 50%) &nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td>두루누리 사회보험 지원</td>
							<td>
								<input type='radio' name='durunuri' value='해당없음'>근로소득자&nbsp;&nbsp;
								<input type='radio' name='durunuri' value='신규가입자80'>신규가입자(80% 지원)&nbsp;&nbsp;
								<input type='radio' name='durunuri' value='신규가입자90'>신규가입자(90% 지원)
							</td>
						</tr>
						<tr>
							<td>기본일/일급</td><td><input type="text" name="salary">원</td>
						</tr>
						<tr>
							<td>기본연금 기준소득액</td><td><input type="text" name="pension_month">원</td>
						</tr>
						<tr>
							<td>건강보험 보수월액</td><td><input type="text" name="heal_month">원</td>
						</tr>
						<tr>
							<td>고용보험 보수월액</td><td><input type="text" name="hire_month">원</td>
						</tr>
						<tr>
							<td>급여계좌</td><td>은행 : <input type="text" name="bank"> / 
							계좌번호 : <input type="text" name="account"></td>
						</tr>
					</table>
					
					
					<table width='90%' border='1'>
						<tr>
							<td>구분</td><td>기호번호</td><td>취득일</td><td>상실일</td>
						</tr>
						<tr>
							<td>국민연금</td><td></td><td></td><td></td>
						</tr>
						<tr>
							<td>건강보험</td><td></td><td></td><td></td>
						</tr>
						<tr>
							<td>고용보험</td><td></td><td></td><td></td>
						</tr>
						<tr>
							<td>산재보험</td><td></td><td></td><td></td>
						</tr>
					</table>
					
					<h3>학력</h3>
					<table width='90%' border='1'>
						<tr>
							<td>구분</td><td>입학년월(yyyy-MM)</td><td>졸업년월(yyyy-MM)</td><td>학교명</td><td>전공</td><td>이수</td>
						<tr>
							<td><input type="text" name="hschool" value='고등학교' readonly></td>
							<td><input type="text" name="hschool_start"></td>
							<td><input type="text" name="hschool_end"></td>
							<td><input type="text" name="hschool_name"></td>
							<td><input type="text" name="hschool_major"></td>
							<td><input type="text" name="hschool_state"></td>
						</tr>
						<tr>
							<td><input type="text" name="uschool" value='대학교' readonly></td>
							<td><input type="text" name="uschool_start"></td>
							<td><input type="text" name="uschool_end"></td>
							<td><input type="text" name="uschool_name"></td>
							<td><input type="text" name="uschool_major"></td>
							<td><input type="text" name="uschool_state"></td>
						</tr>
					</table>

					<input type="submit" value="가입">
					
				</form>
			</div>
			
		</table>
	</body>
</html>