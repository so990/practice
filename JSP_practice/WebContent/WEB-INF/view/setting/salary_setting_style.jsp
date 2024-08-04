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
<title>급여항목 설정</title>

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
            margin-top: 35px;
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
    </style>
    <script>
    function toggleTaxFields() {
        const isTaxFree = document.querySelector('input[name="pay_tax"]:checked').value === '비과세';
        document.getElementById('tax_free_name').style.display = isTaxFree ? 'block' : 'none';
        document.getElementById('tax_free_limit').style.display = isTaxFree ? 'block' : 'none';
    }

    window.onload = function() {
        // Initialize the visibility based on the default checked radio button
        toggleTaxFields();

        // Attach event listeners to radio buttons
        document.querySelectorAll('input[name="pay_tax"]').forEach((radio) => {
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
    </table>

    <div class="container">
        <div class="scrollable">
            <h3>지급항목 설정</h3>
            <br>
            <table border="1">
                <tr>
                    <th>지급항목</th>
                    <th>과세여부</th>
                    <th>비과세한도액</th>
                    <th>절사단위</th>
                    <th>근태연결/일괄지급</th>
                    <th>사용여부</th>
                </tr>
                <c:forEach var="pay" items="${list_pay}">
                    <tr>
                        <td>${pay.pay_name}</td>
                        <td>${pay.pay_tax}</td>
                        <td>${pay.tax_free_limit}</td>
                        <td>${pay.cut_unit}</td>
                        <td>${pay.attend_conn}</td>
                        <td>${pay.pay_used}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="form-container">
            <form action="salitems.do" method='post'>
                <table border='1'>
                    <tr>
                        <td>지급항목</td>
                        <td><input type='text' name='pay_name' value='${payment_items.pay_name }'></td>
                    </tr>
                    
                    <tr>
                        <td>과세여부</td>
                        <td colspan='3'>
                            <input type="radio" name="pay_tax" value="전체과세" checked> 전체과세<br>
                            <input type="radio" name="pay_tax" value="비과세"> 비과세<br>
                        </td>
                    </tr>
                    <tr class="hidden" id="tax_free_name">
                        <td>비과세명</td>
                        <td><input type='text' name='tax_free_name' value='${payment_items.tax_free_name }'></td>
                    </tr>
                    <tr class="hidden" id="tax_free_limit">
                        <td>비과세 한도액</td>
                        <td><input type='text' name='tax_free_limit' value='${payment_items.tax_free_limit }'>원</td>
                    </tr>
                    
                    
                     <tr>
                        <td>계산방법</td>
                        <td><input type='text' name='tax_memo' value='${payment_items.tax_memo }'></td>
                    </tr>
                    
                    <tr>
                        <td>절사단위</td>
                        <td>
                            <select name="cut_unit">
                                <option value="없음">없음</option>
                                <option value="1">1원 단위</option>
                                <option value="10">10원 단위</option>
                                <option value="100">100원 단위</option>
                            </select>
                        </td>
                    </tr>
                      <tr>
                        <td>근태연결/일괄지급</td>
                        <td>
                            <select name="attend_conn">
                                <option value="선택하세요">선택하세요</option>
                                <c:forEach var="att" items="${list_att}">
                                    <option value="${att.att_name}">${att.att_name}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>사용여부</td>
                        <td>
                            <input type="radio" name="pay_used" value="사용" checked> 사용<br>
                            <input type="radio" name="pay_used" value="사용안함"> 사용안함<br>
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
            <h3>공제항목 설정</h3><br>
            <table border='1'>
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
        <div class="form-container">
            <form action="salitems.do" method='post'>
                <table border='1'>
                    <tr>
                        <td>공제항목</td>
                        <td><input type='text' name='ded_name' value='${deduction_items.ded_name }'></td>
                    </tr>
                    
                    <tr>
                        <td>계산방법</td>
                        <td><input type='text' name='ded_memo' value='${deduction_items.ded_memo }'></td>
                    </tr>
                    
                    <tr>
                        <td>절사단위</td>
                        <td>
                            <select name="ded_cut_unit">
                                <option value="없음">없음</option>
                                <option value="1">1원 단위</option>
                                <option value="10">10원 단위</option>
                                <option value="100">100원 단위</option>
                            </select>
                        </td>
                    </tr>
                    
                     <tr>
                        <td>비고</td>
                        <td><input type='text' name='ded_note' value='${deduction_items.ded_note }'></td>
                    </tr>
                    
                    <tr>
                        <td>사용여부</td>
                        <td>
                            <input type="radio" name="ded_used" value="사용" checked> 사용<br>
                            <input type="radio" name="ded_used" value="사용안함"> 사용안함<br>
                        </td>
                    </tr>
                    
                </table>
                <input type='submit' value='저장'>
            </form>
        </div>
    </div>
</body>
</html>