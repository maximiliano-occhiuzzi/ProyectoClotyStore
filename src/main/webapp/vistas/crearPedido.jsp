<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.app.logica.Menus"%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Crear Pedido</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/main.css">
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
            <a href="${pageContext.request.contextPath}/LecturaDatos" class="nav-btn">Menús</a>
            <a href="${pageContext.request.contextPath}/LeerPedidos" class="nav-btn active">Pedidos</a>
        </nav>
    </div>
</header>

<main class="form-container">
    <h2>Generar nuevo pedido</h2>
    <form action="${pageContext.request.contextPath}/AltaPedido" method="post" class="pedido-form">

        <label for="menu">Menú seleccionado:</label>
        <input type="text" id="menu" name="menu" value="<%= request.getParameter("menu") != null ? request.getParameter("menu") : "" %>" readonly>

        <label for="descripcion">Descripción:</label>
        <textarea id="descripcion" name="descripcion" rows="3" required></textarea>

        <label for="horario">Horario deseado:</label>
        <input type="time" id="horario" name="horario" required>

        <label for="nombre_cliente">Nombre de quien realiza el pedido:</label>
        <input type="text" id="nombre_cliente" name="nombre_cliente" required>

        <label for="curso_division">Curso o división:</label>
        <input type="text" id="curso_division" name="curso_division" required>

        <label for="rol">Tipo de usuario:</label>
        <select id="rol" name="rol" required>
            <option value="alumno">Alumno</option>
            <option value="profesor">Profesor</option>
        </select>

        <button type="submit" class="submit-btn">Confirmar Pedido</button>
    </form>
</main>

</body>
</html>
