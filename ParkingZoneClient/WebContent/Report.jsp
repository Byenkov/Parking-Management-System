<%@  page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.parking.logic.*" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.HashMap" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Parking - Report</title>
<link href="Layout.css" rel="stylesheet" type="text/css">
</head>
<body>
<%
javax.naming.InitialContext ic = new javax.naming.InitialContext();
ParkingZone parkingZone=(ParkingZone)ic.lookup("ejb:/ParkProject//ParkingZoneBean!com.parking.logic.ParkingZone"); 
int id = parkingZone.getParkingSlots(1).get(0).getId();
Report report = parkingZone.getReport();
HashMap<Integer, Integer> slotsByDetections = report.getSlotsByDetections();
HashMap<Integer, Integer> zonesByDetections = report.getZonesByDetections();
HashMap<Integer, Integer> slotsByTickets = report.getSlotsByTickets();
HashMap<Integer, Integer> zonesByTickets = report.getZonesByTickets();
HashMap<Integer, Integer> zonesByTicketsNotBought = report.getZonesByTicketsNotBought();
HashMap<Integer, Integer> zonesByTicketsOverTimes = report.getZonesByTicketsOverTimes();
session.setAttribute("slotsByDetections", slotsByDetections);
session.setAttribute("zonesByDetections", zonesByDetections);
session.setAttribute("slotsByTickets", slotsByTickets);
session.setAttribute("zonesByTickets", zonesByTickets);
session.setAttribute("zonesByTicketsNotBought", zonesByTicketsNotBought);
session.setAttribute("zonesByTicketsOverTimes", zonesByTicketsOverTimes);
%>	

<h1 align="center">REPORT</h1>

<table style="float: left;">
<tr ><th colspan="2">Slots By Detections</th></tr>
	<tr><th>SlotID</th><th>Detections</th></tr>
  <c:forEach items="${slotsByDetections}" var="item">
    <c:if test = "${item.value != 0}">
    <tr>
      <td><c:out value="${item.key}" /></td>
      <td><c:out value="${item.value}" /></td>
    </tr>
    </c:if>
  </c:forEach>
</table>

<table style="float: left;">
<tr ><th colspan="2">Zones By Detections</th></tr>
	<tr><th>Zone ID</th><th>Detections</th></tr>
  <c:forEach items="${zonesByDetections}" var="item">
    <c:if test = "${item.value != 0}">
    <tr>
      <td><c:out value="${item.key}" /></td>
      <td><c:out value="${item.value}" /></td>
    </tr>
    </c:if>
  </c:forEach>
</table>

<table style="float: left;">
<tr ><th colspan="2">Slots By Tickets</th></tr>
	<tr><th>Slot ID</th><th>Tickets</th></tr>
  <c:forEach items="${slotsByTickets}" var="item">
    <c:if test = "${item.value != 0}">
    <tr>
      <td><c:out value="${item.key}" /></td>
      <td><c:out value="${item.value}" /></td>
    </tr>
    </c:if>
  </c:forEach>
</table>

<table style="float: left;">
<tr ><th colspan="2">Zones By Tickets</th></tr>
	<tr><th>Zone ID</th><th>Tickets</th></tr>
  <c:forEach items="${zonesByTickets}" var="item">
    <c:if test = "${item.value != 0}">
    <tr>
      <td><c:out value="${item.key}" /></td>
      <td><c:out value="${item.value}" /></td>
    </tr>
    </c:if>
  </c:forEach>
</table>

<table style="float: left;">
<tr ><th colspan="2">Zones By Tickets Not Bought</th></tr>
	<tr><th>Zone ID</th><th>Tickets Not Bought</th></tr>
  <c:forEach items="${zonesByTicketsNotBought}" var="item">
    <c:if test = "${item.value != 0}">
    <tr>
      <td><c:out value="${item.key}" /></td>
      <td><c:out value="${item.value}" /></td>
    </tr>
    </c:if>
  </c:forEach>
</table>

<table style="float: left;">
<tr ><th colspan="2">Slots By Tickets Over Time</th></tr>
	<tr><th>Zone ID</th><th>Tickets Over Time</th></tr>
  <c:forEach items="${zonesByTicketsOverTimes}" var="item">
    <c:if test = "${item.value != 0}">
    <tr>
      <td><c:out value="${item.key}" /></td>
      <td><c:out value="${item.value}" /></td>
    </tr>
    </c:if>
  </c:forEach>
</table>

</body>
</html>