<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.app.logica.Productos"%>
<%@ page import="com.app.logica.Menus"%>

<!doctype html>
<html lang="es">
<head>
<meta charset="UTF-8" />
<title>clotyStore - Tienda Online</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/estilos/main.css">
</head>
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
				<a href="${pageContext.request.contextPath}/LecturaDatos"
					class="nav-btn">menú</a>
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
				<p class="hero-subtitle">Descubre nuestra colección de productos
					y menús exclusivos</p>
				<button class="cta-button" id="verProductosBtn">Ver
					productos</button>
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
						<ul class="category-list" id="categoryList">
							<!-- Se generarán dinámicamente -->
						</ul>
					</div>

					<div class="sidebar-actions">
						<form
							action="${pageContext.request.contextPath}/vistas/crearProducto.jsp"
							method="GET">
							<button type="submit" class="action-btn create-btn">Crear
								producto</button>
						</form>
					</div>
				</aside>

				<!-- Modal de confirmación -->
				<div id="confirmModal" class="confirm-modal hidden">
					<div class="confirm-box">
						<p id="confirmMessage"></p>
						<div class="confirm-buttons">
							<button id="confirmYes" class="btn-yes">Sí, eliminar</button>
							<button id="confirmNo" class="btn-no">Cancelar</button>
						</div>
					</div>
				</div>

				<!-- Contenedor de productos -->
				<div class="products-container">
					<div class="products-grid" id="productsGrid">
						<!-- Aquí se inyectan los productos con main.js -->
					</div>
					<div class="pagination">
						<button class="pagination-btn" id="prevBtn">back</button>
						<button class="pagination-btn" id="nextBtn">next</button>
					</div>
				</div>
			</div>
		</section>

		<!-- Sección de menús -->
		<section id="menu" class="page">
			<h2 class="page-title">Menús</h2>
			<div class="productos-layout">
				<!-- Sidebar -->
				<aside class="sidebar">
					<div class="sidebar-section">
						<h3 class="sidebar-title">Categorías</h3>
						<ul class="category-list" id="menuCategoryList">
							<!-- Se generarán dinámicamente -->
						</ul>
					</div>

					<div class="sidebar-actions">
						<form
							action="${pageContext.request.contextPath}/vistas/crearMenu.jsp"
							method="GET">
							<button type="submit" class="action-btn create-btn">Crear
								menú</button>
						</form>
					</div>
				</aside>

				<!-- Contenedor de menús -->
				<div class="products-container">
					<div class="products-grid" id="menusGrid">
						<!-- Aquí se inyectan los menús con main.js -->
					</div>
					<div class="pagination">
						<button class="pagination-btn" id="menuPrevBtn">back</button>
						<button class="pagination-btn" id="menuNextBtn">next</button>
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

	<!-- Bloque JSP que inyecta los datos del servidor -->
	<script>
	const products = [
	  <%List<Productos> productos = (List<Productos>) request.getAttribute("productos");
if (productos != null && !productos.isEmpty()) {
	for (int i = 0; i < productos.size(); i++) {
		Productos p = productos.get(i);%>
	      {
	          id: <%=p.getId()%>,
	          title: "<%=p.getNombre()%>",
	          price: <%=p.getPrecio()%>,
	          category: "<%=p.getCategoria()%>",
	          stock: <%=p.getStock()%>,
	          image: "<%=p.getImagen()%>"
	      }<%=(i < productos.size() - 1) ? "," : ""%>
	  <%}
} else {%>
	      {
	          id: 0,
	          title: "Sin productos disponibles",
	          price: 0,
	          category: "-",
	          stock: 0,
	          image: ""
	      }
	  <%}%>
	];

	const menus = [
	  <%List<Menus> menus = (List<Menus>) request.getAttribute("listaMenus");
	if (menus != null && !menus.isEmpty()) {
		for (int i = 0; i < menus.size(); i++) {
			Menus m = menus.get(i);%>
	      {
	          id: <%=m.getId()%>,
	          title: "<%=m.getNombre()%>",
	          description: "<%=m.getDescripcion()%>",
	          price: <%=m.getPrecio()%>,
	          image: "<%=m.getImagen()%>"
	      }<%=(i < menus.size() - 1) ? "," : ""%>
	  <%}
} else {%>
	      {
	          id: 0,
	          title: "Sin menús disponibles",
	          description: "",
	          price: 0,
	          image: ""
	      }
	  <%}%>
	];
	</script>

	<script src="${pageContext.request.contextPath}/scripts/main.js" defer></script>
</body>
</html>
