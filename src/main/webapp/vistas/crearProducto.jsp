<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Crear producto</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/form.css">
</head>
<body>
  

<form action="${pageContext.request.contextPath}/CrearDatos" method="POST">
<h2>Nuevo Producto</h2>
    <label>Nombre:</label>
    <input type="text" name="nombre" required><br>

    <label>Precio:</label>
    <input type="number" name="precio" step="0.01" required><br>

    <label>Stock:</label>
    <input type="number" name="stock" required><br>

    <label>Categoría:</label>
    <input type="text" name="categoria" required><br>

    <label>Imagen (URL o ruta):</label>
    <input type="text" name="imagen" required><br>

    <button type="submit">Guardar producto</button>
  </form>
</body>
</html>
