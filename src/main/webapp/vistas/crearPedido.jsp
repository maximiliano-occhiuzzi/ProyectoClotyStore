<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.app.logica.Menus"%> 
<!-- ajusta el paquete según tu proyecto -->

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Crear Pedido</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/main.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/menus.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/pedidos.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/formPedido.css">

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
            <a href="${pageContext.request.contextPath}/lecturaPedidos" class="nav-btn active">Pedidos</a>
        </nav>
    </div>
</header>

<main class="menu-container">

    <div class="menu-header">
        <h1>Crear Pedido</h1>
        <a href="${pageContext.request.contextPath}/LecturaPedidos" class="create-btn">Volver</a>
    </div>

    <div class="crear-pedido-container">

        <h2>Generar nuevo pedido</h2>

        <!-- Obtener la lista de menús -->
        <%
            List<Menus> listaMenus = (List<Menus>) request.getAttribute("listaMenus");
        %>

        <form action="${pageContext.request.contextPath}/AltaPedidos" method="post">

            <!-- SELECCIÓN DE MENÚ -->
            <div class="form-group">
              <label for="menuSelect">Seleccionar menú:</label>
    <select id="menuSelect" name="idMenu" required class="select-input">
        <option value="">-- Elegir menú --</option>
        <%
            List<Menus> Menus = (List<Menus>) request.getAttribute("listaMenus");
            if (listaMenus != null) {
                for (Menus m : listaMenus) {
        %>
            <option value="<%= m.getId() %>"
                <%= (request.getParameter("idMenu") != null 
                     && request.getParameter("idMenu").equals(String.valueOf(m.getId()))) 
                     ? "selected" : "" %>>
                <%= m.getNombre() %> - $<%= m.getPrecio() %>
            </option>
        <%
                }
            }
        %>
    </select>
            </div>

            <!-- DESCRIPCIÓN -->
            <div class="form-group">
                <label for="descripcion">Descripción del pedido:</label>
                <textarea id="descripcion" name="descripcion" required></textarea>
            </div>

            <!-- HORARIO -->
            <div class="form-group">
                <label for="horario">Horario deseado:</label>
                <input type="time" id="horario" name="horario" required>
            </div>

            <!-- NOMBRE CLIENTE -->
            <div class="form-group">
                <label for="nombreCliente">Nombre de quien realiza el pedido:</label>
                <input type="text" id="nombreCliente" name="nombreCliente" required>
            </div>

            <!-- CURSO/DIVISIÓN -->
            <div class="form-group">
                <label for="cursoDivision">Curso o división:</label>
                <input type="text" id="cursoDivision" name="cursoDivision" required>
            </div>

            <!-- ROL -->
            <div class="form-group">
                <label for="rol">Tipo de usuario:</label>
                <select id="rol" name="rol" required>
                    <option value="alumno">Alumno</option>
                    <option value="profesor">Profesor</option>
                </select>
            </div>

            <div class="form-actions">
                <button type="submit" class="btn btn-crear">Confirmar Pedido</button>
            </div>

        </form>
    </div>

</main>

</body>
</html>
