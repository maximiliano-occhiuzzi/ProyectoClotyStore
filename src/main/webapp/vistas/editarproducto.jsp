<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Editar Producto</title>
  <link rel="stylesheet" href="estilos/form.css">
</head>
<body>
  <h2>Editar Producto</h2>

  <form action="${pageContext.request.contextPath}/EditarDatos" method="POST">
    <input type="hidden" name="id" value="${producto.id}">

    <label>Nombre:</label>
    <input type="text" name="nombre" value="${producto.nombre}" required><br>

    <label>Precio:</label>
    <input type="number" name="precio" step="0.01" value="${producto.precio}" required><br>

    <label>Stock:</label>
    <input type="number" name="stock" value="${producto.stock}" required><br>

    <label>Categoría:</label>
    <input type="text" name="categoria" value="${producto.categoria}" required><br>

    <label>Imagen (URL o ruta):</label>
    <input type="text" name="imagen" value="${producto.imagen}" required><br>

    <button type="submit">Actualizar producto</button>
  </form>
</body>
</html>
