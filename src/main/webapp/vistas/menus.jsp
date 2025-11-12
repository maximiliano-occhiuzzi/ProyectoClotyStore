<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.app.logica.Menus"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>clotyStore - Menús</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/main.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/menus.css">
    
    <style>
   
    </style>
</head>
<body>

<header class="header">
    <div class="header-container">
        <div class="logo-container">
            <div class="logo-circle"></div>
            <h1 class="brand-name">clotyStore</h1>
        </div>
        <nav class="navigation">
            <a href="${pageContext.request.contextPath}/LeerDatos" class="nav-btn">Inicio</a>
            <a href="${pageContext.request.contextPath}/LeerDatos" class="nav-btn">Productos</a>
            <a href="${pageContext.request.contextPath}/LecturaDatos" class="nav-btn active">Menús</a>
            <a href="pedidos.jsp" class="nav-btn">Pedidos</a>
        </nav>
    </div>
</header>

<main class="menu-container">
    <div class="menu-header">
        <h1>Menús disponibles</h1>
        <form action="${pageContext.request.contextPath}/vistas/crearMenu.jsp" method="GET">
            <button type="submit" class="create-btn">+ Crear nuevo menú</button>
        </form>
    </div>

    <div class="menu-grid">
        <% 
        List<Menus> menus = (List<Menus>) request.getAttribute("listaMenus");
        if (menus != null && !menus.isEmpty()) {
            for (Menus m : menus) { 
        %>
            <div class="menu-card">
                <img src="<%=m.getImagen()%>" alt="Imagen de <%=m.getNombre()%>">
                <div class="menu-card-content">
                    <h3><%=m.getNombre()%></h3>
                    <p><%=m.getDescripcion()%></p>
                </div>
                <div class="menu-card-footer">
                    <span>$<%=m.getPrecio()%></span>
                    <div class="menu-actions">
                        <a href="EditarMenu?id=<%=m.getId()%>" class="edit-btn">Editar</a>
                        <a href="EliminarMenu?id=<%=m.getId()%>" class="delete-btn"
                           onclick="return confirm('¿Seguro que deseas eliminar este menú?');">Eliminar</a>
                    </div>
                </div>
            </div>
        <% 
            }
        } else { 
        %>
            <p class="empty-state">No hay menús registrados aún.</p>
        <% } %>
    </div>
</main>
	<script src="${pageContext.request.contextPath}/scripts/main.js" defer></script>

</body>
</html>
