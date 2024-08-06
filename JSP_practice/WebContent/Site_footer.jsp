<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page info="Include 테스트" %>
<%@ page buffer="none" %>
<%@ page autoFlush="true" %>
<%@ page isThreadSafe="true" %>

  <title>Footer Example</title>
    <style>
        .footer-content {
            display: flex;
            align-items: center;
            padding: 20px;
            background-color: #f1f1f1;
        }
        .footer-content img {
            margin-right: 20px; /* 이미지와 텍스트 사이의 간격 조절 */
        }
        .footer-text {
            display: flex;
            flex-direction: column;
        }
    </style>
</head>
<body>
    <div class="footer-content">
        <img src="./images/footer.png" width="1200" height="150" alt="Company Logo"/>
 
    </div>
</body>
</html>