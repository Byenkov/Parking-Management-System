<%@  page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.parking.logic.*" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Parking - Dashboard</title>
<link href="Layout.css" rel="stylesheet" type="text/css">
</head>
<body>
<%
javax.naming.InitialContext ic = new javax.naming.InitialContext();
ParkingZone parkingZone=(ParkingZone)ic.lookup("ejb:/ParkProject//ParkingZoneBean!com.parking.logic.ParkingZone"); 
int id = parkingZone.getParkingSlots(1).get(0).getId();
ArrayList<ParkingSlot> list1 = parkingZone.getParkingSlots(1);
session.setAttribute("list1", list1);
ArrayList<ParkingSlot> list2 = parkingZone.getParkingSlots(2);
session.setAttribute("list2", list2);
ArrayList<ParkingSlot> list3 = parkingZone.getParkingSlots(3);
session.setAttribute("list3", list3);
ArrayList<ParkingSlot> list4 = parkingZone.getParkingSlots(4);
session.setAttribute("list4", list4);
%>	

<h1 align="center">DASHBOARD</h1>

<table style="float: left;">
	<tr><th>ID</th><th>Sector</th><th>Street</th><th>Is Taken</th><th>Ticket Bought</th></tr>
  <c:forEach items="${list1}" var="item">
    <tr>
      <td><c:out value="${item.id}" /></td>
      <td><c:out value="${item.sector}" /></td>
      <td><c:out value="${item.street}" /></td>
      <td><c:out value="${item.taken}" /></td>
      <td><c:out value="${item.ticketBought}" /></td>
    </tr>
  </c:forEach>
</table>
  <table style="float: left;">
	<tr><th>ID</th><th>Sector</th><th>Street</th><th>Is Taken</th><th>Ticket Bought</th></tr>
  <c:forEach items="${list2}" var="item">
    <tr>
      <td><c:out value="${item.id}" /></td>
      <td><c:out value="${item.sector}" /></td>
      <td><c:out value="${item.street}" /></td>
      <td><c:out value="${item.taken}" /></td>
      <td><c:out value="${item.ticketBought}" /></td>
    </tr>
  </c:forEach>
</table>
  <table style="float: left;">
	<tr><th>ID</th><th>Sector</th><th>Street</th><th>Is Taken</th><th>Ticket Bought</th></tr>
  <c:forEach items="${list3}" var="item">
    <tr>
      <td><c:out value="${item.id}" /></td>
      <td><c:out value="${item.sector}" /></td>
      <td><c:out value="${item.street}" /></td>
      <td><c:out value="${item.taken}" /></td>
      <td><c:out value="${item.ticketBought}" /></td>
    </tr>
  </c:forEach>
</table>
  <table style="float: left;">
	<tr><th>ID</th><th>Sector</th><th>Street</th><th>Is Taken</th><th>Ticket Bought</th></tr>
  <c:forEach items="${list4}" var="item">
    <tr>
      <td><c:out value="${item.id}" /></td>
      <td><c:out value="${item.sector}" /></td>
      <td><c:out value="${item.street}" /></td>
      <td><c:out value="${item.taken}" /></td>
      <td><c:out value="${item.ticketBought}" /></td>
    </tr>
  </c:forEach>
</table>

</body>
</html>