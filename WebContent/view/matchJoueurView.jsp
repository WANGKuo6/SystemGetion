<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="mypackage.model.Joueur"%>  
<%@ page import="mypackage.model.Match"%> 
<%@ page language="java" import="java.util.List"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table border="1" align = "center">
  <tr>  
    <th>id</th>  
    <th>nom</th>  
    <th>date</th>  
    <th>ville</th> 
    <th>stade</th> 
  </tr> 
 <%Match match = (Match) request.getAttribute("match");%>
 <%  if(match != null){%>
        <tr>
        <th><%=match.getIdMatch() %></th>  
        <th><%=match.getNomMatch()%></th>  
        <th><%=match.getDate()%></th>  
        <th><%=match.getVille()%></th>
        <th><%=match.getStade()%></th>
        </tr>    
        
<% }%> 

</table>
<%List list = (List) request.getAttribute("joueurss");%>
 <%  if(list != null){%>
<table border="1" align = "center">
  <tr>  
   <th>nomJoueur</th>  
   <th>role</th>  
  </tr> 
<% for(int i = 0;i<list.size();i++){%>
        <%Joueur joueur = (Joueur)list.get(i);%> 
        <tr>
        <th><%=joueur.getNomJoueur() %></th>
        <th><%=joueur.getRole() %></th><br> 
        </tr>    
        
<% }%> 

<% }%> 
</table>
</body>
</html>