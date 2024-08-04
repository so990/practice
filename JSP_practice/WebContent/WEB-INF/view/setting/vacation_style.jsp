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
<title>휴가/근태 설정</title>

<style>
        /* Basic reset */
        body, h2, table, input, select, button {
            margin: 0;
            padding: 0;
            border: 0;
            font-size: 100%;
            vertical-align: baseline;
            background: transparent;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            line-height: 1.6;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
            margin-top: 20px;
        }

        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }

        th {
            background-color: #f4f4f4;
            font-weight: bold;
        }

        td {
            background-color: #fff;
        }

        button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 5px 10px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 0.9em;
        }

        button:hover {
            background-color: #0056b3;
        }

        form {
            margin: 20px 0;
        }

        input[type="text"], input[type="date"], select {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="radio"] {
            margin-right: 5px;
        }

        .container {
            display: flex;
            justify-content: space-between;
        }

        .container > div {
            width: 45%;
        }

        .section {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }

        .section h2 {
            margin-bottom: 15px;
            font-size: 1.5em;
            color: #333;
        }

        .form-container {
            padding: 10px;
            margin: 0 auto;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        input[type="submit"] {
            background-color: #28a745;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1em;
        }

        input[type="submit"]:hover {
            background-color: #218838;
        }

        /* Additional styles for table containers */
        .scrollable {
            padding: 10px;
            margin: 0 auto;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
    </style>
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
    </table>

    <div class="container">
        <div class="scrollable">
            <h3>휴가항목 설정</h3>
            <br>
            <table border="1">
                <tr>
                    <th>휴가항목</th>
                    <th>적용기간</th>
                    <th>사원별 휴가일수</th>
                    <th>사용여부</th>
                </tr>
                <c:forEach var="vac" items="${list_vac}">
                    <tr>
                        <td>${vac.vac_name}</td>
                        <td>${vac.vac_start}~${vac.vac_end}</td>
                        <td><button type="button" onclick="----------">관리</button></td>
                        <td>${vac.vac_used}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="form-container">
            <form action="att.do" method='post'>
                <table border='1'>
                    <tr>
                        <td>휴가항목</td>
                        <td><input type='text' name='vac_name' value='${vacation_items.vac_name }'></td>
                    </tr>
                    <tr>
                        <td>적용기간</td>
                        <td colspan='3'>
                            <input type="date" name="vac_start" value='${vacation_items.vac_start }'> ~ 
                            <input type="date" name="vac_end" value='${vacation_items.vac_end }'>
                        </td>
                    </tr>
                    <tr>
                        <td>사용여부</td>
                        <td>
                            <input type="radio" name="vac_used" value="사용" checked> 사용<br>
                            <input type="radio" name="vac_used" value="사용안함"> 사용안함<br>
                        </td>
                    </tr>
                </table>
                <input type='submit' value='저장'>
            </form>
        </div>
    </div>
    <br>
    <br>
    <br>

    <div class="container">
        <div class="scrollable">
            <h3>근태항목 설정</h3><br>
            <table border='1'>
                <tr>
                    <th>근태항목</th>
                    <th>단위</th>
                    <th>그룹관리</th>
                    <th>휴가공제</th>
                    <th>근로시간연계</th>
                    <th>사용여부</th>
                </tr>
                <c:forEach var="att" items="${list_att}">
                    <tr>
                        <td>${att.att_name}</td>
                        <td>${att.att_unit}</td>
                        <td>${att.att_grp}</td>
                        <td>${att.att_deduction}</td>
                        <td>${att.att_conn}</td>
                        <td>${att.att_used}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="form-container">
            <form action="att.do" method='post'>
                <table border='1'>
                    <tr>
                        <td>근태항목</td>
                        <td><input type='text' name='att_name' value='${attend_items.att_name }'></td>
                    </tr>

                    <tr>
                        <td>단위</td>
                        <td>
                            <select name="att_unit">
                                <option value="선택하세요">선택하세요</option>
                                <option value="일">일</option>
                                <option value="시간">시간</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>근태그룹</td>
                        <td>
                            <select name="att_grp">
                                <option value="선택하세요">선택하세요</option>
                                <option value="휴가">휴가</option>
                                <option value="연장근무">연장근무</option>
                                <option value="지각조퇴">지각조퇴</option>
                                <option value="특근">특근</option>
                                <option value="기타">기타</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>휴가공제</td>
                        <td>
                            <select name="att_deduction">
                                <option value="선택하세요">선택하세요</option>
                                <c:forEach var="vac" items="${list_vac}">
                                    <option value="${vac.vac_name}">${vac.vac_name}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>근로시간연계</td>
                        <td>
                            <select name="att_conn">
                                <option value="선택하세요">선택하세요</option>
                                <option value="소정근로">소정근로</option>
                                <option value="연장근로">연장근로</option>
                                <option value="야간근로">야간근로</option>
                                <option value="휴일근로">휴일근로</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>사용여부</td>
                        <td>
                            <input type="radio" name="att_used" value="사용" checked> 사용<br>
                            <input type="radio" name="att_used" value="사용안함"> 사용안함<br>
                        </td>
                    </tr>
                </table>
                <input type='submit' value='저장'>
            </form>
        </div>
    </div>
</body>
</html>