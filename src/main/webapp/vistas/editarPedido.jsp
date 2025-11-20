<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.app.logica.Pedido" %>
<%@ page import="java.util.List" %>
<%@ page import="com.app.logica.Menus" %>

<%
    Pedido p = (Pedido) request.getAttribute("pedido");
    List<Menus> listaMenus = (List<Menus>) request.getAttribute("listaMenus");

    if (p == null || listaMenus == null) {
%>
    <h2 style="color:red;">❌ Error: No se pudo cargar el pedido o la lista de menús.</h2>
    <a href="LeerPedidos">Volver</a>
<%
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Editar Pedido</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/main.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/menus.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/pedidos.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/formPedido.css">
</head>

<body>

<main class="menu-container">

    <div class="menu-header">
        <h1>Editar Pedido</h1>
        <a href="LeerPedidos" class="create-btn">Volver</a>
    </div>

    <div class="crear-pedido-container">
        <form action="${pageContext.request.contextPath}/EditarPedido" method="POST">

            <input type="hidden" name="id" value="<%= p.getId() %>">

            <div class="form-group">
                <label for="menuSelect">Menú</label>
                <select id="menuSelect" name="idMenu" required class="select-input">
                    <option value="">-- Elegir menú --</option>
                    <%
                        for (Menus m : listaMenus) {
                    %>
                       <option value="<%= m.getId() %>" 
    <%= (p.getMenu() != null && p.getMenu().getId() == m.getId()) ? "selected" : "" %>>
    <%= m.getNombre() %> - $<%= m.getPrecio() %>
</option>
                    <% } %>
                </select>
            </div>

            <div class="form-group">
                <label for="nombreCliente">Cliente</label>
                <input type="text" id="nombreCliente" name="nombreCliente" 
                       value="<%= p.getNombreCliente() %>" required>
            </div>

            <div class="form-group">
                <label for="descripcion">Descripción</label>
                <textarea id="descripcion" name="descripcion" required><%= p.getDescripcion() %></textarea>
            </div>

            <div class="form-group">
                <label for="horario">Horario</label>
                <input type="time" id="horario" name="horario" value="<%= p.getHorario() %>" required>
            </div>

            <div class="form-group">
                <label for="cursoDivision">Curso / División</label>
                <input type="text" id="cursoDivision" name="cursoDivision" value="<%= p.getCursoDivision() %>" required>
            </div>

            <div class="form-group">
                <label for="estado">Estado</label>
                <select id="estado" name="estado" class="select-input">
                    <option value="pendiente" <%= p.getEstado().equals("pendiente") ? "selected" : "" %>>Pendiente</option>
                    <option value="entregado" <%= p.getEstado().equals("entregado") ? "selected" : "" %>>Entregado</option>
                </select>
            </div>

            <div class="form-actions">
                <button type="submit" class="btn btn-crear">Guardar cambios</button>
                <a href="LeerPedidos" class="btn btn-cancelar">Cancelar</a>
            </div>

        </form>
    </div>

</main>

</body>
</html>
