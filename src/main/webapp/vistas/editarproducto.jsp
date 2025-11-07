<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Editar Producto</title>
  <link rel="stylesheet" href="estilos/form.css">
</head>
<body>


  <%
    com.app.logica.Productos producto = (com.app.logica.Productos) request.getAttribute("producto");
  %>

  <form action="EditarDatos" method="POST">
    <h2>Editar Producto</h2>
    <input type="hidden" name="id" value="<%= producto.getId() %>">

    <label>Nombre:</label>
    <input type="text" name="nombre" value="<%= producto.getNombre() %>" required><br>

    <label>Precio:</label>
    <input type="number" name="precio" step="0.01" value="<%= producto.getPrecio() %>" required><br>

    <label>Stock:</label>
    <input type="number" name="stock" value="<%= producto.getStock() %>" required><br>

    <label>Categoría:</label>
    <input type="text" name="categoria" value="<%= producto.getCategoria() %>" required><br>

    <label>Imagen (URL o ruta):</label>
    <input type="text" name="imagen" value="<%= producto.getImagen() %>" required><br>

    <button type="submit">Actualizar producto</button>
  </form>
</body>
</html>
