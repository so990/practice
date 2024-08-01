<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page info="Include 테스트" %>
<%@ page buffer="none" %>
<%@ page autoFlush="true" %>
<%@ page isThreadSafe="true" %>

<style type="text/css">
	* {
		margin: 0;
		padding: 0;
	}
	ul li{
		list-style: none;
	}
	a {
		text-decoration: none;
		color:#333;
	}

	#menu {
		font:bold 16px "malgun gothic";
		width:100%;
		height:50px;
		background: #ccccc;
		color:black;
		line-height: 50px; 
		margin:0 auto;
		text-align: center;
	}

	#menu > ul > li {
		float:left;
		width:170px;
		position:relative;
	}
	#menu > ul > li > ul {
		width:160px;
		display:none;
		position: absolute;
		font-size:14px;
		background: #f7f8f0;
	}
	#menu > ul > li:hover > ul {
		display:block;
		
	}
	#menu > ul > li > ul > li:hover {
		background: #e5e7fb;
		transition: ease 1s;
		}
	</style>
</head>

<body>
<div id="menu">
	<ul>
		<li><a href="#">基本環境設定</a>
			<ul>
				<li><a href="company.do">ユーザー情報</a></li>
				<li><a href="register.do">社員登録</a></li>
				<li><a href="vac.do">休暇・勤怠設定</a></li>
				<li><a href="#">給与項目設定</a></li>
			</ul>
		</li>
		<li><a href="#">人事管理</a>
			<ul>
				<li><a href="#">社員現況・管理</a></li>
				<li><a href="#">人事記録カード</a></li>
				<li><a href="#">諸証明書発給</a></li>
				<li><a href="#">諸証明書発給台帳</a></li>
			</ul>
		</li>
		<li><a href="#">勤怠管理</a>
			<ul>
				<li><a href="#">勤怠記録・管理</a></li>
				<li><a href="#">勤怠照会</a></li>
				<li><a href="#">休暇照会</a></li>
				<li><a href="#">日雇い勤務記録・管理</a></li>
				<li><a href="#">日雇い勤務照会</a></li>
			</ul>
		</li>
		<li><a href="#">給与管理</a>
			<ul>
				<li><a href="#">給与入力・管理</a></li>
				<li><a href="#">給与入力・管理(日雇い)</a></li>
				<li><a href="#">給与台帳</a></li>
				<li><a href="#">給与明細書</a></li>
				<li><a href="#">社員給与内訳</a></li>
				<li><a href="#">給与振込申請</a></li>
				<li><a href="#">給与振込結果確認</a></li>
				<li><a href="#">項目別台帳</a></li>
				<li><a href="#">４大保険控除内訳</a></li>
			</ul>
		</li>
		<li><a href="${pageContext.request.contextPath}/retireProcess.do">退職管理</a>
			<ul>
				<li><a href="${pageContext.request.contextPath}/retireProcess.do">社員退職処理</a></li>
				<li><a href="${pageContext.request.contextPath}/retirePay.do">退職給与入力・管理</a></li>
				<li><a href="${pageContext.request.contextPath}/retirePayLog.do">退職給与明細書</a></li>
			</ul>
		</li>
	</ul>
</div>
<hr class="one">
</body>
</html>