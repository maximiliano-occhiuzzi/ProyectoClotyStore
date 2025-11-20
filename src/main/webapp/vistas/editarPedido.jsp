<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.app.logica.Pedido" %>

<%
    Pedido p = (Pedido) request.getAttribute("pedido");

    if (p == null) {
%>
        <h2 style="color:red;">❌ Error: No se pudo cargar el pedido.</h2>
        <a href="LeerPedido">Volver</a>
<%
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Editar Pedido</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/pedidos.css">
</head>

<body>

<div class="form-container">

    <h2>Editar Pedido</h2>

    <form action="${pageContext.request.contextPath}/EditarPedido" method="POST">

        <!-- ID del pedido -->
        <input type="hidden" name="id" value="<%= p.getId() %>">

        <!-- Menú actual -->
        <label>Menú seleccionado</label>
        <input type="text" value="<%= p.getMenu().getNombre() %>" readonly class="readonly-input">

        <!-- Envío del id real del menú (FK) -->
        <input type="hidden" name="idMenu" value="<%= p.getMenu().getId() %>">

        <label>Cliente</label>
        <input type="text" name="nombreCliente" value="<%= p.getNombreCliente() %>" required>

        <label>Descripción</label>
        <textarea name="descripcion" required><%= p.getDescripcion() %></textarea>

        <label>Horario</label>
        <input type="time" name="horario" value="<%= p.getHorario() %>" required>

        <label>Curso / División</label>
        <input type="text" name="cursoDivision" value="<%= p.getCursoDivision() %>" required>

        <button type="submit" class="btn-guardar">Guardar cambios</button>
        <a href="LeerPedido" class="btn-volver">Cancelar</a>

    </form>

</div>

</body>
</html>
