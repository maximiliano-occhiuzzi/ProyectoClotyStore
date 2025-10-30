<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.app.logica.Productos" %>


<!doctype html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <title>clotyStore - Tienda Online</title>
    <link rel="stylesheet" href="estilos/index.css">
  </head>


<%
    List<Productos> lista = (List<Productos>) request.getAttribute("listaProductos");
%>


  <body>
    <header class="header">
      <div class="header-container">
        <div class="logo-container">
          <div class="logo-circle"></div>
          <h1 class="brand-name">clotyStore</h1>
        </div>

        <nav class="navigation">
          <button class="nav-btn active" data-page="inicio">inicio</button>
          <button class="nav-btn" data-page="productos">productos</button>
          <button class="nav-btn" data-page="pedidos">pedidos</button>
        </nav>

        <button class="hamburger" aria-label="Menu">
          <span></span><span></span><span></span>
        </button>
      </div>
    </header>

    <main class="main-content">
      <!-- Sección de inicio -->
      <section id="inicio" class="page active">
        <div class="hero">
          <h2 class="hero-title">Bienvenido a clotyStore</h2>
          <p class="hero-subtitle">Descubre nuestra colección de productos exclusivos</p>
          <button class="cta-button" data-navigate="productos">Ver productos</button>
        </div>
      </section>

      <!-- Sección de productos -->
      <section id="productos" class="page">
        <h2 class="page-title">Productos</h2>

        <div class="productos-layout">
          <!-- Sidebar -->
          <aside class="sidebar">
            <div class="sidebar-section">
              <h3 class="sidebar-title">Categorías</h3>
              <ul class="category-list">
                <li><button class="category-btn active" data-category="all">Todas</button></li>
                <li><button class="category-btn" data-category="Categoría A">Categoría A</button></li>
                <li><button class="category-btn" data-category="Categoría B">Categoría B</button></li>
                <li><button class="category-btn" data-category="Categoría C">Categoría C</button></li>
              </ul>
            </div>

            <div class="sidebar-actions">
              <form action="CrearDatos" method="GET">
                <button type="submit" class="action-btn create-btn">crear</button>
              </form>
              <form action="LeerDatos" method="GET">
                <button type="submit" class="action-btn update-btn">actualizar</button>
              </form>
              <form action="EliminarDatos" method="GET">
                <button type="submit" class="action-btn delete-btn">eliminar</button>
              </form>
            </div>
          </aside>

          <!-- Contenedor de productos -->
          <div class="products-container">
            <div class="products-grid" id="productsGrid">

              <%
                // Este bloque JSP muestra los productos si fueron cargados por el servlet
                List<Productos> lista = (List<Productos>) request.getAttribute("listaProductos");
                if (lista != null && !lista.isEmpty()) {
                    for (Productos p : lista) {
              %>
                      <div class="product-card">
                        <img src="<%= p.getImagen() != null ? p.getImagen() : "imagenes/default.png" %>" alt="Imagen de producto">
                        <h3><%= p.getNombre() %></h3>
                        <p>Precio: $<%= p.getPrecio() %></p>
                        <p>Stock: <%= p.getStock() %></p>
                        <form action="EliminarDatos" method="POST">
                          <input type="hidden" name="id" value="<%= p.getId() %>">
                          <button class="delete-btn">Eliminar</button>
                        </form>
                      </div>
              <%
                    }
                } else {
              %>
                  <p class="empty-state">No hay productos disponibles.</p>
              <%
                }
              %>

            </div>

            <div class="pagination">
              <button class="pagination-btn" id="prevBtn">back</button>
              <button class="pagination-btn" id="nextBtn">next</button>
            </div>
          </div>
        </div>
      </section>

      <!-- Sección de pedidos -->
      <section id="pedidos" class="page">
        <div class="orders-container">
          <h2>Mis Pedidos</h2>
          <div id="ordersList" class="orders-list">
            <p class="empty-state">No tienes pedidos aún</p>
          </div>
        </div>
      </section>
    </main>

    <script type="module" src="scripts/main.js"></script>
  </body>
</html>
