<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
	<form method="post" action="<%=request.getContextPath()%>/gd">
		<input type="hidden" name="a" value="delete"/>
		<input type='hidden' name="no" value="<%=request.getParameter( "no" ) %>">
		<table>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="password"></td>
				<td><input type="submit" value="확인"></td>
			</tr>
		</table>
	</form>
	<p>
		<a href="<%=request.getContextPath() %>/gd">메인으로 돌아가기</a>
	</p>
</body>
</html>