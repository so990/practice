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
								<td><input type='text' name='emp_no' value='${personnel.emp.emp_no}' readonly></td>
							</tr>
							<tr>
								<td>성명</td>
								<td><input type='text' name='emp_name' value='${personnel.emp.name_kor}' readonly></td>
							</tr>
							<tr>
								<td>부서</td>
								<td><input type='text' name='dept' value='${personnel.emp.dept}' readonly></td>
							</tr>
							<tr>
								<td>직위</td>
								<td><input type='text' name='job' value='${personnel.emp.job}' readonly></td>
							</tr>
							<tr>
								<td>입사일</td>
								<td><input type='text' name='hired_date' value='${personnel.emp.hired_date}' readonly></td>
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
							<td><select name="emp_type" value='${personnel.emp.emp_type}'>
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
							<td>이름(한글) : </td><td><input type="text" name="name_kor" value='${personnel.emp.name_kor}'></td>
							<td>이름(영어) : </td><td><input type="text" name="name_eng" value='${personnel.emp.name_eng}'></td>
						</tr>
						<tr>
							<td>입사일 : </td><td><input type="date" name="hired_date" value='${personnel.emp.hired_date}'></td>
							<td>퇴사일 : </td><td><input type="date" name="retired_date" value='${personnel.rtr.retired_date}'></td>
						</tr>
						<tr>
							<td>부서 : </td>
							<td><select name="dept" value='${personnel.emp.dept}'>
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
							<td><select name="job" value='${personnel.emp.job}'>
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
							<td><select name="nationality" value='${personnel.emp.nationality}'>
									<option value="">선택하세요</option>
									<option value="내국인">내국인</option>
									<option value="외국인">외국인</option>
								</select>
							</td>
							<td>주민번호 : </td><td><input type="text" name="id_number" value='${personnel.emp.id_number}'></td>
						</tr>
						<tr>
							<td>주소 : </td><td colspan='3'><input type="text" name="post_code" value='${personnel.emp.post_code}'>(우편번호) / 
							<input type="text" name="addr" value='${personnel.emp.addr}'></td>
						</tr>
						<tr>
							<td>전화번호 : </td><td><input type="text" name="home_number" value='${personnel.emp.home_number}'></td>
							<td>휴대폰번호 : </td><td><input type="text" name="phone" value='${personnel.emp.phone}'></td>
						</tr>
						<tr>
							<td>이메일 : </td><td><input type="text" name="email" value='${personnel.emp.email}'></td>
							<td>sns : </td><td><input type="text" name="sns" value='${personnel.emp.sns}'></td>
						</tr>
						<tr>
							<td>비고 : </td><td colspan='3'><textarea name="note" cols='90' rows='3' value='${personnel.emp.note}'></textarea></td>
						</tr>
					</table>
				<br/>
				<h3>급여 & 4대보험</h3>
				<br/>
					<table width='90%' border='1'>
						<tr>
							<td>4대보험 : </td>
							<td>
								<input type='checkbox' name='pension' value='pension' >국민연금 &nbsp;&nbsp;
								<input type='checkbox' name='insur_heal' value='insur_heal'>건강보험(감면 30%) &nbsp;&nbsp;
								<input type='checkbox' name='insur_care' value='insur_care'>노인장기요양보험(감면 30%) &nbsp;&nbsp;
								<input type='checkbox' name='insur_hire' value='insur_hire'>고용보험 &nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td>갑근세</td>
							<td>
								<input type='radio' name='gapgeunse' value='근로소득자'>근로소득자
								세액 : <select name="wage_earner_per" value='${personnel.ins.wage_earner_per}'>
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
							<td>기본일/일급</td><td><input type="text" name="salary" value='${personnel.ins.salary}'>원</td>
						</tr>
						<tr>
							<td>기본연금 기준소득액</td><td><input type="text" name="pension_month" value='${personnel.ins.pension_month}'>원</td>
						</tr>
						<tr>
							<td>건강보험 보수월액</td><td><input type="text" name="heal_month" value='${personnel.ins.heal_month}'>원</td>
						</tr>
						<tr>
							<td>고용보험 보수월액</td><td><input type="text" name="hire_month" value='${personnel.ins.hire_month}'>원</td>
						</tr>
						<tr>
							<td>급여계좌</td><td>은행 : <input type="text" name="bank" value='${personnel.emp.bank}'> / 
							계좌번호 : <input type="text" name="account" value='${personnel.emp.account}'></td>
						</tr>
					</table>
					
					
					<table width='90%' border='1'>
                  <tr>
                     <td>구분</td><td>기호번호</td><td>취득일</td><td>상실일</td>
                  </tr>
                  <tr>
                     <td>국민연금</td>
                     <td><input type="text" name="pension_num" value="${personnel.ins.pension_month}"></td>
                     <td><input type="date" name="pension_start" value="${personnel.ins.pension_month}"></td>
                     <td><input type="date" name="pension_end" value="${personnel.ins.pension_month}"></td>
                  </tr>
                  <tr>
                     <td>건강보험</td>
                     <td><input type="text" name="heal_num" value="${personnel.ins.heal_num}"></td>
                     <td><input type="date" name="heal_start" value="${personnel.ins.heal_start}"></td>
                     <td><input type="date" name="heal_end" value="${personnel.ins.heal_end}"></td>
                  </tr>
                  <tr>
                     <td>고용보험</td>
                     <td><input type="text" name="hire_num" value="${personnel.ins.hire_num}"></td>
                     <td><input type="date" name="hire_start" value="${personnel.ins.hire_start}"></td>
                     <td><input type="date" name="hire_end" value="${personnel.ins.hire_end}"></td>
                  </tr>
                  <tr>
                     <td>산재보험</td>
                     <td><input type="text" name="indus_num" value="${personnel.ins.indus_num}"></td>
                     <td><input type="date" name="indus_start" value="${personnel.ins.indus_start}"></td>
                     <td><input type="date" name="indus_end" value="${personnel.ins.indus_end}"></td>
                  </tr>
               </table>
               <br/>
               <h3>학력</h3>
               <table width='90%' border='1'>
                  <tr>
                     <td>구분</td><td>입학년월(yyyy-MM)</td><td>졸업년월(yyyy-MM)</td><td>학교명</td><td>전공</td><td>이수</td>
                  <tr>
                     <td><input type="text" name="hschool" value='고등학교' readonly></td>
                     <td><input type="text" name="hschool_start" value="${personnel.edu[0].school_start}"></td>
                     <td><input type="text" name="hschool_end" value="${personnel.edu[0].school_end}"></td>
                     <td><input type="text" name="hschool_name" value="${personnel.edu[0].school_name}"></td>
                     <td><input type="text" name="hschool_major" value="${personnel.edu[0].school_major}" size=10></td>
                     <td><select name="hschool_state">
                           <option value="">선택</option>
                           <option value="졸업">졸업</option>
                           <option value="수료">수료</option>
                           <option value="자퇴">자퇴</option>
                           <option value="재학중">재학중</option>
                        </select></td>
                  </tr>
                  <tr>
                     <td><input type="text" name="uschool" value='대학교' readonly></td>
                     <td><input type="text" name="uschool_start" value="${personnel.edu[1].school_start}"></td>
                     <td><input type="text" name="uschool_end" value="${personnel.edu[1].school_end}"></td>
                     <td><input type="text" name="uschool_name" value="${personnel.edu[1].school_name}"></td>
                     <td><input type="text" name="uschool_major" value="${personnel.edu[1].school_major}" size=10></td>
                     <td><select name="uschool_state">
                           <option value="">선택</option>
                           <option value="졸업">졸업</option>
                           <option value="수료">수료</option>
                           <option value="자퇴">자퇴</option>
                           <option value="재학중">재학중</option>
                        </select></td>
                  </tr>
               </table>
               <br/>
               <h3>경력</h3>
               <table width='90' border='1'>
                  <tr>
                     <td>회사명</td><td>입사일자</td><td>퇴사일자</td><td>근무기간(개월)</td><td>최종직위</td><td>담당직무</td><td>퇴직사유</td>
                  <tr>
                     <td><input type="text" name="1firm" value="${personnel.car[0].firm}" size=10></td>
                     <td><input type="date" name="1firm_start" value="${personnel.car[0].firm_start}"></td>
                     <td><input type="date" name="1firm_end" value="${personnel.car[0].firm_end}"></td>
                     <td><input type="text" name="1firm_term" value="${personnel.car[0].firm_term}"></td>
                     <td><input type="text" name="1firm_job" value="${personnel.car[0].firm_job}" size=10></td>
                     <td><input type="text" name="1firm_task" value="${personnel.car[0].firm_task}" size=10></td>
                     <td><input type="text" name="1firm_retire" value="${personnel.car[0].firm_retire}" size=21></td>
                  </tr>
                  <tr>
                     <td><input type="text" name="2firm" value="${personnel.car[1].firm}" size=10></td>
                     <td><input type="date" name="2firm_start" value="${personnel.car[1].firm_start}"></td>
                     <td><input type="date" name="2firm_end" value="${personnel.car[1].firm_end}"></td>
                     <td><input type="text" name="2firm_term" value="${personnel.car[1].firm_term}"></td>
                     <td><input type="text" name="2firm_job" value="${personnel.car[1].firm_job}" size=10></td>
                     <td><input type="text" name="2firm_task" value="${personnel.car[1].firm_task}" size=10></td>
                     <td><input type="text" name="2firm_retire" value="${personnel.car[1].firm_retire}" size=21></td>
                  </tr>
               </table>
               <br/>
               <h3>병역</h3>
               <table width='90%' border='1'>
                  <tr>
                     <td>병역구분</td><td>군벌</td><td>복무기간(부터)</td><td>복무기간(까지)</td><td>최종계급</td><td>병과</td><td>미필사유</td>
                  <tr>
                     <td><select name="mil">
                           <option value="">선택</option>
                           <option value="군필">군필</option>
                           <option value="미필">미필</option>
                        </select></td>
                     <td><select name="mil_type">
                           <option value="">선택</option>
                           <option value="육군">육군</option>
                           <option value="해군">해군</option>
                           <option value="공군">공군</option>
                           <option value="상비군">상비군</option>
                           <option value="면제">면제</option>
                           <option value="기타">기타</option>
                        </select></td>
                     <td><input type="date" name="mil_start" value="${personnel.mil.mil_start}"></td>
                     <td><input type="date" name="mil_end" value="${personnel.mil.mil_end}"></td>
                     <td><input type="text" name="mil_rank" value="${personnel.mil.mil_rank}"></td>
                     <td><input type="text" name="mil_job" value="${personnel.mil.mil_job}"></td>
                     <td><input type="text" name="mil_no_reason" value="${personnel.mil.mil_no_reason}"></td>
                  </tr>
               </table>
               
               <br/>
               <h3>자격 면허&어학능력</h3>
               <table width='90%' border='1'>
                  <tr>
                     <td>자격/면허명</td><td>취득일</td><td>발행기관</td><td>증번호</td><td>비고</td>
                  <tr>
                     <td><input type="text" name="lsc_name1" value="${personnel.lcs[0].lsc_name}"></td>
                     <td><input type="text" name="lsc_date1" value="${personnel.lcs[0].lsc_date}"></td>
                     <td><input type="text" name="lsc_dep1" value="${personnel.lcs[0].lsc_dep}"></td>
                     <td><input type="text" name="lsc_num1" value="${personnel.lcs[0].lsc_num}"></td>
                     <td><input type="text" name="lsc_note1" value="${personnel.lcs[0].lsc_note}"></td>
                  </tr>
                  <tr>
                     <td><input type="text" name="lsc_name2" value="${personnel.lcs[1].lsc_name}"></td>
                     <td><input type="text" name="lsc_date2" value="${personnel.lcs[1].lsc_date}"></td>
                     <td><input type="text" name="lsc_dep2" value="${personnel.lcs[1].lsc_dep}"></td>
                     <td><input type="text" name="lsc_num2" value="${personnel.lcs[1].lsc_num}"></td>
                     <td><input type="text" name="lsc_note2" value="${personnel.lcs[1].lsc_note}"></td>
                  </tr>
               </table>
               <br/>
               <table width='90%' border='1'>
                  <tr>
                     <td>외국어명</td><td>시험</td><td>공인점수</td><td>취득일</td><td>독해</td><td>작문</td><td>회화</td>
                  <tr>
                     <td><input type="text" name="lang_name1" value="${personnel.lang[0].lang_name}"></td>
                     <td><input type="text" name="lang_test1" value="${personnel.lang[0].lang_test}"></td>
                     <td><input type="text" name="lang_score1" value="${personnel.lang[0].lang_score}"></td>
                     <td><input type="date" name="lang_date1" value="${personnel.lang[0].lang_date}"></td>
                     <td><select name="lang_read1">
                           <option value="">선택</option>
                           <option value="상">상</option>
                           <option value="중">중</option>
                           <option value="하">하</option>
                        </select></td>
                     <td><select name="lang_listen1">
                           <option value="">선택</option>
                           <option value="상">상</option>
                           <option value="중">중</option>
                           <option value="하">하</option>
                        </select></td>
                     <td><select name="lang_speak1">
                           <option value="">선택</option>
                           <option value="상">상</option>
                           <option value="중">중</option>
                           <option value="하">하</option>
                        </select></td>
                  </tr>
                  <tr>
                     <td><input type="text" name="lang_name2" value="${personnel.lang[1].lang_name}"></td>
                     <td><input type="text" name="lang_test2" value="${personnel.lang[1].lang_test}"></td>
                     <td><input type="text" name="lang_score2" value="${personnel.lang[1].lang_score}"></td>
                     <td><input type="date" name="lang_date2" value="${personnel.lang[1].lang_date}"></td>
                     <td><select name="lang_read2">
                           <option value="">선택</option>
                           <option value="상">상</option>
                           <option value="중">중</option>
                           <option value="하">하</option>
                        </select></td>
                     <td><select name="lang_listen2">
                           <option value="">선택</option>
                           <option value="상">상</option>
                           <option value="중">중</option>
                           <option value="하">하</option>
                        </select></td>
                     <td><select name="lang_speak2">
                           <option value="">선택</option>
                           <option value="상">상</option>
                           <option value="중">중</option>
                           <option value="하">하</option>
                        </select></td>
                  </tr>
               </table>
               
               <br/>
               <h3>퇴직</h3>
               <table width='90%' border='1'>
                  <tr>
                     <td>퇴직구분</td><td>퇴직일자</td><td>퇴사사유</td><td>퇴직후연락처</td><td>퇴직금</td>
                  <tr>
                     <td><select name="retire_type">
                           <option value="">선택</option>
                           <option value="정년퇴직">정년퇴직</option>
                           <option value="정리해고">정리해고</option>
                           <option value="자발적 퇴직">자발적 퇴직</option>
                           <option value="임원퇴직">임원퇴직</option>
                           <option value="중간정산">중간정산</option>
                           <option value="기타">기타</option>
                        </select></td>
                     <td><input type="date" name="retired_date" value="${personnel.rtr.retired_date}" size=10></td>
                     <td><input type="text" name="retire_reason" value="${personnel.rtr.retire_reason}"></td>
                     <td><input type="text" name="retire_phone" value="${personnel.rtr.retire_phone}"></td>
                     <td><input type="text" name="retire_cost" value="${personnel.rtr.retire_cost}">원</td>
                  </tr>
               </table>
               <br/>
               <input type="submit" value="가입">
               
            </form>
         </div>
         </td></tr>
      </table>
      <br/><br/><br/><br/>
   </body>
</html>