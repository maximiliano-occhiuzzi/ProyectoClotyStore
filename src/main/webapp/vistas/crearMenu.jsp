<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Crear Menú - clotyStore</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/menus.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/formMenu.css">
    
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
            <a href="${pageContext.request.contextPath}/pedidos.jsp" class="nav-btn">Pedidos</a>
        </nav>
    </div>
</header>

<main>
    <div class="crear-menu-container">
        <h2>Crear nuevo menú</h2>

        <form action="${pageContext.request.contextPath}/AltaDatos" method="post">
            <div class="form-group">
                <label for="nombre">Nombre del menú:</label>
                <input type="text" id="nombre" name="nombre" required>
            </div>

            <div class="form-group">
                <label for="descripcion">Descripción:</label>
                <textarea id="descripcion" name="descripcion" required></textarea>
            </div>

            <div class="form-group">
                <label for="precio">Precio ($):</label>
                <input type="number" id="precio" name="precio" step="0.01" min="0" required>
            </div>

            <div class="form-group">
                <label for="imagen">URL de imagen:</label>
                <input type="text" id="imagen" name="imagen" placeholder="https://ejemplo.com/imagen.jpg">
            </div>

            <div class="form-actions">
                <button type="submit" class="btn btn-crear">Guardar menú</button>
                <a href="${pageContext.request.contextPath}/LecturaDatos" class="btn btn-cancelar">Cancelar</a>
            </div>
        </form>

        <a href="${pageContext.request.contextPath}/LecturaDatos" class="volver">← Volver al listado</a>
    </div>
</main>

</body>
</html>
