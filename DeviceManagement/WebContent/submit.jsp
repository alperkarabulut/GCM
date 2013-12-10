<%@page import="com.mbis.gcm.service.Device"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Durum</title>
</head>
<body>
<%if( !request.getParameter("deviceList").equals("") ){
	String devId = request.getParameter("deviceList").toString();
	String MessageText = request.getParameter("message").toString();
	Device dev = new Device();
	dev.SendMessage(devId, MessageText, "");
%>
<span>Mesajınız <%=request.getParameter("deviceList") %> cihazına gönderildi.</span>
<%} %>
</body>
</html>