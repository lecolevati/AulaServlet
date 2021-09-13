<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SCRIPTLET</title>
</head>
<body>

	<%
		String x = "Boa tarde";
		out.println(x);
	%>
	<br />
	<%		
		for (int i = 0 ; i < 10 ; i++) {
			out.println(i);
	%>
	<br />
	<%
		}
	%>

</body>
</html>