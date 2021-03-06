<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="mypackage.model.Equipe"%>  
<%@ page language="java" import="java.util.List"%>  

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%List list = (List) request.getAttribute("equipes");%>
<% Equipe equipeInfo = (Equipe) request.getAttribute("equipeInfo");%>
<% String message = (String) request.getAttribute("message");%>
<% String showAdd = (String) request.getAttribute("showAdd");%>
<% Equipe equipeEdit = (Equipe) request.getAttribute("equipeEdit"); %>
<% HttpServletRequest httpRequest= (HttpServletRequest)request; %>
<% boolean admin = false;
   String login = "";
   String password = "";
	Cookie[]  cookies = httpRequest.getCookies();
   if(cookies != null){
	   for(Cookie ck : cookies){
		   if("admin".equals(ck.getValue())){
			   admin = true;
			   
		   }
		   if("login".equals(ck.getName())){
			   login = ck.getValue();
		   }
		   if("password".equals(ck.getName())){
			   password = ck.getValue();
		   }
	   }
   }
%>
	

<% if(admin==false){%>
<a href="roleServlet?role=visiter">return to page choose table</a>
<% }%>
<% if(admin==true){ %>
<% String web = "loginServlet?login=" + login+ "&password=" + password;%>
<a href="<%= web%>" > return to page choose table </a>
<% } %>
<h2 align = "center">equipe</h2>  
    <table border = 1px align = "center">  
    <% if("showAdd".equals(showAdd)){%>
    <!-- add equipe windows -->
	<div id="addDialog" style="padding: 10px">  
    	<form action = "equipeServlet">
	    	<table cellpadding="8" >
	    		<tr>
	    			<td>id:</td>
	    			<td><input id="add_id" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="idEquipe" data-options="required:true, missingMessage:'enter id'" /></td>
	    		</tr>
	    		<tr>
	    			<td>nomEquipe:</td>
	    			<td>
	    				<input id="add_nom" style="width: 200px; height: 30px;" type="text" name="nomEquipe" data-options="required:true, missingMessage:'enter nom'" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>nbParticipation</td>
	    			<td>
	    				<input id="add_nbPar" style="width: 200px; height: 30px;" type="text" name="nbParticipation" data-options="required:true, missingMessage:'enter nbParticipation'" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>nbVictoire</td>
	    			<td>
	    				<input id="add_nbVic" style="width: 200px; height: 30px;" type="text" name="nbVictoire" data-options="required:true, missingMessage:'enter nbVictoire'" />
	    			</td>
	    		</tr>
	    	</table>
	    	<input type = "submit" name = "button" value = "submitAdd">
	    </form>
	</div>
	<% }%>
	
	<% if(equipeEdit != null){%>
	<div id="editDialog" style="padding: 10px"> 
	<form action = "equipeServlet">
	    	<table cellpadding="8" >
	    		<tr>
	    			<td>id:</td>
	    			<td><input id="edit_id" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="editIdEquipe"  value = <%=equipeEdit.getIdEquipe() %> data-options="required:true, missingMessage:'enter id'" /></td>
	    		</tr>
	    		<tr>
	    			<td>nomEquipe:</td>
	    			<td>
	    				<input id="edit_nom" style="width: 200px; height: 30px;" type="text" name="editNomEquipe" value = <%=equipeEdit.getNomEquipe() %> data-options="required:true, missingMessage:'enter nom'" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>nbParticipation</td>
	    			<td>
	    				<input id="edit_nbPar" style="width: 200px; height: 30px;" type="text" name="editNbParticipation" value = <%=equipeEdit.getNbParticipation()%> data-options="required:true, missingMessage:'enter nbParticipation'" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>nbVictoire</td>
	    			<td>
	    				<input id="edit_nbVic" style="width: 200px; height: 30px;" type="text" name="editNbVictoire" value = <%=equipeEdit.getNbVictore() %> data-options="required:true, missingMessage:'enter nbVictoire'" />
	    			</td>
	    		</tr>
	    	</table>
	    	<input type = "submit" name = "button" value = "submitEdit">
		    </form>
	</div>
	<% } %>
	
	
    <% if(!"showAdd".equals(showAdd) && equipeEdit == null) {%>
        <div align="center"> 
        <form action = "equipeServlet">
		please enter the name of the group <input type = "text" name = "searchInfo"><br/>
		<input type = "submit" name = "button" value = "search">
		<input type = "submit" name = "button" value = "ListAll">
		</form>	
		</div>
     	  <tr>  
     	  	<th></th>
            <th>id</th>  
            <th>nom</th>  
            <th>nbPAR</th>  
            <th>nbvic</th>
            <th>classement</th>  
        </tr> 
    	<% if(admin){%>
    	<div align="center"> 
        <form action = "equipeServlet">
        <input type = "submit" name = "button" value = "delete">
		<input type = "submit" name = "button" value = "add"> 
        <input type = "submit" name = "button" value = "edit">
        </div>
        <%} %>
        <%  if(list != null){%>
        <% for(int i = 0;i<list.size();i++){%>
                <%Equipe equipe = (Equipe)list.get(i);%> 
                <tr>       
                <th><input type = checkbox name = "chk" id="chk" value=<%=equipe.getIdEquipe()%>></th> 
                <th><%=equipe.getIdEquipe() %></th>  
                <th><%=equipe.getNomEquipe()%></th>  
                <th><%=equipe. getNbParticipation()%></th>  
                <th><%=equipe.getNbVictore()%></th>
                <th><a href="classementServlet?classement=<%=equipe.getIdEquipe() %>">classement</a></th><br>          
        <% }%> 
        <% }%>
        </form>
        <% if(equipeInfo != null){%>
         <tr>  
         		<th>     </th>
                <th><%=equipeInfo.getIdEquipe() %></th>  
                <th><%=equipeInfo.getNomEquipe()%></th>  
                <th><%=equipeInfo. getNbParticipation()%></th>  
                <th><%=equipeInfo.getNbVictore()%></th>
                <th><a href="classementServlet?classement=<%= equipeInfo.getIdEquipe() %>">classement</a></th><br>
                <% }  
         %>
          <% if("delSuccess".equals(message)){%>
         		<script>
				alert("congratulation! success deleted!");
				</script>
                <% }  
         %>
         <% }  
         %> 
         <% if("editSuccess".equals(message)){%>
         		<script>
				alert(" success edited!");
				</script>
				
         <% } %> 
         
         
            
    </table>
</body>
</html>
