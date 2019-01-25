<%@page import="com.douzon.guestbook.vo.GuestBookVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	List<GuestBookVo> list = (List)request.getAttribute("list");
	int length = list.size();
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
	<form action="<%=request.getContextPath() %>/gd?a=add" method="post">
		<table border=1 width=500>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name"></td>
				<td>비밀번호</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan=4><textarea name="message" cols=60 rows=5></textarea></td>
			</tr>
			<tr>
				<td colspan=4 align=right><input type="submit" VALUE="등록"></td>
			</tr>
		</table>
	</form>

	<%
		for (int i = length-1; i >= 0; i--)
		{
	%>
	<br>
	<table width=510 border=1>
		<tr>
			<td><%=i+1 %></td>
			<td><%=list.get(i).getName() %></td>
			<td><%=list.get(i).getRegDate() %></td>
			<td><a href="<%=request.getContextPath() %>/gd?a=deleteform&no=<%=list.get(i).getNo() %>">삭제</a></td>
		</tr>
		<tr>
			<td colspan=4><%=list.get(i).getMessage().replaceAll("\r\n", "<br/>") %></td>
		</tr>
	</table>
	<%
		}
	%>

</body>
</html>