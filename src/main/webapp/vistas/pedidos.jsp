<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.app.logica.Pedido" %>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>clotyStore - Pedidos</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/main.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/menus.css"><!-- USAMOS MISMO ESTILO -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/pedidos.css">

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
            <a href="${pageContext.request.contextPath}/LecturaDatos" class="nav-btn">Menús</a>
            <a href="${pageContext.request.contextPath}/LecturaPedidos" class="nav-btn active">Pedidos</a>
        </nav>
    </div>
</header>

<main class="menu-container"><!-- MISMO CONTENEDOR QUE EN MENUS -->

    <div class="menu-header">
        <h1>Pedidos</h1>
        <a href="${pageContext.request.contextPath}/lecturaCrearPedido" class="create-btn">+ Crear Pedido</a>
    </div>

    <div class="menu-grid"><!-- USAMOS GRID IGUAL -->

        <%
            List<Pedido> lista = (List<Pedido>) request.getAttribute("listaPedidos");
            if (lista != null && !lista.isEmpty()) {
                for (Pedido p : lista) {
        %>

        <div class="menu-card"><!-- USAMOS MISMA CARD -->

            <div class="menu-card-content">
                <h3><%= p.getNombreCliente() %></h3>
                <p><strong>Horario:</strong> <%= p.getHorario() %></p>
                <p><strong>Descripción:</strong> <%= p.getDescripcion() %></p>
                
            </div>

            <div class="menu-card-footer">
                <span>ID: <%= p.getId() %></span>

                <div class="menu-actions">
                    <a href="${pageContext.request.contextPath}/EditarPedido?id=<%= p.getId() %>" 
                       class="edit-btn">Editar</a>

                    <a href="${pageContext.request.contextPath}/BajaPedidos?id=<%= p.getId() %>" 
                       class="delete-btn">Eliminar</a>
                </div>
            </div>

        </div>

        <% 
                }
            } else {
        %>

        <p class="empty-state">No hay pedidos registrados.</p>

        <% } %>
    </div>

</main>

</body>
</html>
