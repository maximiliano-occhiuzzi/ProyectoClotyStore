<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.app.logica.Menus"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Menú - clotyStore</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/menus.css">
    <style>
        body {
            background-color: #fafafa;
        }

        .editar-menu-container {
            max-width: 600px;
            margin: 80px auto;
            background: #fff;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 0 15px rgba(0,0,0,0.1);
        }

        .editar-menu-container h2 {
            text-align: center;
            color: #333;
            margin-bottom: 25px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            font-weight: bold;
            color: #555;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="number"],
        textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 8px;
            font-size: 15px;
        }

        textarea {
            resize: vertical;
            height: 100px;
        }

        .form-actions {
            text-align: center;
            margin-top: 25px;
        }

        .btn {
            padding: 10px 25px;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            cursor: pointer;
        }

        .btn-guardar {
            background-color: #0275d8;
            color: #fff;
        }

        .btn-cancelar {
            background-color: #d9534f;
            color: #fff;
            margin-left: 10px;
        }

        .btn:hover {
            opacity: 0.9;
        }

        .volver {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #333;
            text-decoration: none;
        }

        .volver:hover {
            text-decoration: underline;
        }
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
            <a href="${pageContext.request.contextPath}/pedidos.jsp" class="nav-btn">Pedidos</a>
        </nav>
    </div>
</header>

<main>
    <div class="editar-menu-container">
        <h2>Editar menú</h2>

        <%
            Menus menu = (Menus) request.getAttribute("menuEditar");
            if (menu == null) {
        %>
            <p style="color:red; text-align:center;">No se encontró el menú a editar.</p>
        <%
            } else {
        %>

  	<form action="${pageContext.request.contextPath}/ModificacionDatos" method="POST" class="menu-form">
            <input type="hidden" name="id" value="<%=menu.getId()%>">

            <div class="form-group">
                <label for="nombre">Nombre del menú:</label>
                <input type="text" id="nombre" name="nombre" value="<%=menu.getNombre()%>" required>
            </div>

            <div class="form-group">
                <label for="descripcion">Descripción:</label>
                <textarea id="descripcion" name="descripcion" required><%=menu.getDescripcion()%></textarea>
            </div>

            <div class="form-group">
                <label for="precio">Precio ($):</label>
                <input type="number" id="precio" name="precio" step="0.01" min="0" value="<%=menu.getPrecio()%>" required>
            </div>

            <div class="form-group">
                <label for="imagen">URL de imagen:</label>
                <input type="text" id="imagen" name="imagen" value="<%=menu.getImagen()%>">
            </div>

            <div class="form-actions">
                <button type="submit" class="btn btn-guardar">Guardar cambios</button>
                <a href="${pageContext.request.contextPath}/LecturaDatos" class="btn btn-cancelar">Cancelar</a>
            </div>
        </form>

        <a href="${pageContext.request.contextPath}/LecturaDatos" class="volver">← Volver al listado</a>

        <%
            }
        %>
    </div>
</main>

</body>
</html>
