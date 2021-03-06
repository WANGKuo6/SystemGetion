<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="mypackage.model.Classement"%>  
<%@ page language="java" import="java.util.List"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%List list = (List) request.getAttribute("classements");%>
<%  if(list != null){%>
<h2 align = "center">classement</h2>  
<table border="1" align = "center">
  <tr>  
   <th>idclassement</th>  
   <th>Equipe_idEquipe</th>  
   <th>annee</th>  
   <th>position</th><br> 
  </tr> 
<% for(int i = 0;i<list.size();i++){%>
        <%Classement classement = (Classement)list.get(i);%> 
        <tr>
        <th><%=classement.getIdClassement() %></th>
        <th><%=classement.getIdEquipe() %></th>  
        <th><%=classement.getAnnee()%></th>  
        <th><%=classement.getPosition()%></th><br> 
        </tr>    
        
<% }%> 
<% }%>
</table>
</body>
</html>