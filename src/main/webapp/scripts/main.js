var ITEMS_PER_PAGE = 9;
var currentPage = 1;
var productStocks = {};
var currentCategory = 'all';

// --- Inicializar stocks ---
products.forEach(function(product) {
    productStocks[product.id] = product.stock;
});

// --- Funciones de filtrado ---
function getFilteredProducts() {
    if (currentCategory === 'all') return products;
    return products.filter(function(product) {
        return product.category === currentCategory;
    });
}

// --- Renderizado de productos ---
function renderProducts() {
    var container = document.getElementById('productsGrid');
    if (!container) return;
    container.innerHTML = '';

    var filteredProducts = getFilteredProducts();
    var startIndex = (currentPage - 1) * ITEMS_PER_PAGE;
    var endIndex = startIndex + ITEMS_PER_PAGE;
    var productsToShow = filteredProducts.slice(startIndex, endIndex);

    var basePath = window.location.pathname.substring(0, window.location.pathname.indexOf('/', 1));

    // Función global de eliminación
    window.eliminarProducto = function(id, nombre) {
        var modal = document.getElementById('confirmModal');
        var message = document.getElementById('confirmMessage');
        var btnYes = document.getElementById('confirmYes');
        var btnNo = document.getElementById('confirmNo');

        message.textContent = '¿Seguro que deseas eliminar el producto "' + nombre + '"?';
        modal.classList.remove('hidden');

        btnYes.onclick = function() {
            modal.classList.add('hidden');
            window.location.href = window.location.pathname.replace(/\/[^\/]*$/, "") + '/EliminarDatos?id=' + id;
        };

        btnNo.onclick = function() {
            modal.classList.add('hidden');
        };
    };

    // Render de productos
    for (var i = 0; i < productsToShow.length; i++) {
        var p = productsToShow[i];
        var card = document.createElement('div');
        card.classList.add('product-card');

        card.innerHTML =
            '<img src="' + (p.image || "https://via.placeholder.com/150") + '" alt="' + p.title + '" class="product-image">' +
            '<h3>' + p.title + '</h3>' +
            '<p>Precio: ' + p.price + '</p>' +
            '<p>Categoría: ' + p.category + '</p>' +
            '<p>Stock: <span class="stock-value" data-id="' + p.id + '">' + productStocks[p.id] + '</span></p>' +
            '<div class="product-actions">' +
                '<button class="consultar-btn" onclick="window.open(\'https://wa.me/549XXXXXXXXX?text=Hola, quiero consultar por ' + encodeURIComponent(p.title) + '\')">Consultar</button>' +
                '<button class="editar-btn" onclick="window.location.href=\'' + basePath + '/EditarDatos?id=' + p.id + '\'">Editar</button>' +
                '<button class="eliminar-btn" onclick="eliminarProducto(' + p.id + ', \'' + p.title.replace(/'/g, "\\'") + '\')">Eliminar</button>' +
            '</div>';

        container.appendChild(card);
    }

    updatePaginationButtons();
}
// modal para confirmar la eliminacion del menu

// --- Modal de confirmación de eliminación ---
function abrirModal(idMenu) {
  const modal = document.getElementById("modalEliminar");
  const inputId = document.getElementById("menuIdEliminar");

  if (modal && inputId) {
    inputId.value = idMenu;
    modal.style.display = "flex";
  }
}

function cerrarModal() {
  const modal = document.getElementById("modalEliminar");
  if (modal) {
    modal.style.display = "none";
  }
}

// Cerrar modal si se hace clic fuera del cuadro
window.onclick = function (event) {
  const modal = document.getElementById("modalEliminar");
  if (event.target === modal) {
    cerrarModal();
  }
};


document.addEventListener("DOMContentLoaded", function() {
    var menuGrid = document.getElementById("menusGrid");
    if (menuGrid && typeof menus !== "undefined") {
        var contenido = "";
        for (var i = 0; i < menus.length; i++) {
            var m = menus[i];
            contenido +=
                "<div class='menu-card'>" +
                    "<img src='" + m.imagen + "' alt='" + m.nombre + "'>" +
                    "<div class='menu-card-content'>" +
                        "<h3>" + m.nombre + "</h3>" +
                        "<p>" + m.descripcion + "</p>" +
                        "<p class='price'>$" + m.precio.toFixed(2) + "</p>" +
                    "</div>" +
                "</div>";
        }
        menuGrid.innerHTML = contenido;
    }
});

// --- Botones de paginación ---
function updatePaginationButtons() {
    var prevBtn = document.getElementById('prevBtn');
    var nextBtn = document.getElementById('nextBtn');
    var filteredProducts = getFilteredProducts();
    var totalPages = Math.ceil(filteredProducts.length / ITEMS_PER_PAGE);

    if (prevBtn) prevBtn.disabled = currentPage === 1;
    if (nextBtn) prevBtn.disabled = currentPage === totalPages || totalPages === 0;
}

// --- Control manual de stock ---
function handleStockChange(productId, action) {
    if (action === 'increase') {
        productStocks[productId]++;
    } else if (action === 'decrease' && productStocks[productId] > 0) {
        productStocks[productId]--;
    }

    var stockElement = document.querySelector('.stock-value[data-id="' + productId + '"]');
    if (stockElement) {
        stockElement.textContent = productStocks[productId];
    }
}

// --- Navegación entre páginas internas ---
function navigateToPage(page) {
    var pages = document.querySelectorAll('.page');
    var navBtns = document.querySelectorAll('.nav-btn');

    for (var i = 0; i < pages.length; i++) {
        pages[i].classList.remove('active');
    }
    for (var j = 0; j < navBtns.length; j++) {
        navBtns[j].classList.remove('active');
    }

    var targetPage = document.getElementById(page);
    var targetBtn = document.querySelector('[data-page="' + page + '"]');

    if (targetPage) targetPage.classList.add('active');
    if (targetBtn) targetBtn.classList.add('active');

    if (page === 'productos') renderProducts();
}

// --- Generar categorías dinámicamente ---
function generarCategorias() {
    var categoryList = document.getElementById('categoryList');
    if (!categoryList) return;

    categoryList.innerHTML = '';

    var categorias = [];
    for (var i = 0; i < products.length; i++) {
        var categoria = products[i].category;
        if (categorias.indexOf(categoria) === -1) {
            categorias.push(categoria);
        }
    }

    // Agregar botón "Todas"
    var liTodas = document.createElement('li');
    liTodas.innerHTML = '<button class="category-btn active" data-category="all">Todas</button>';
    categoryList.appendChild(liTodas);

    // Crear botones de categoría
    for (var j = 0; j < categorias.length; j++) {
        var li = document.createElement('li');
        li.innerHTML = '<button class="category-btn" data-category="' + categorias[j] + '">' + categorias[j] + '</button>';
        categoryList.appendChild(li);
    }

    // Agregar eventos
    var catBtns = document.querySelectorAll('.category-btn');
    for (var k = 0; k < catBtns.length; k++) {
        catBtns[k].addEventListener('click', function(e) {
            for (var m = 0; m < catBtns.length; m++) {
                catBtns[m].classList.remove('active');
            }
            e.target.classList.add('active');
            currentCategory = e.target.getAttribute('data-category');
            currentPage = 1;
            renderProducts();
        });
    }
}

// --- Inicialización general ---
document.addEventListener('DOMContentLoaded', function() {
    generarCategorias();
    renderProducts();

    // Navegación del menú
    var navButtons = document.querySelectorAll('.nav-btn');
    for (var i = 0; i < navButtons.length; i++) {
        navButtons[i].addEventListener('click', function(e) {
            var page = e.target.getAttribute('data-page');
            navigateToPage(page);
        });
    }

    // Botón "Ver productos"
    var verProductosBtn = document.getElementById('verProductosBtn');
    if (verProductosBtn) {
        verProductosBtn.addEventListener('click', function() {
            navigateToPage('productos');
        });
    }

    // Paginación
    var prevBtn = document.getElementById('prevBtn');
    var nextBtn = document.getElementById('nextBtn');

    if (prevBtn) {
        prevBtn.addEventListener('click', function() {
            if (currentPage > 1) {
                currentPage--;
                renderProducts();
            }
        });
    }

    if (nextBtn) {
        nextBtn.addEventListener('click', function() {
            var filteredProducts = getFilteredProducts();
            var totalPages = Math.ceil(filteredProducts.length / ITEMS_PER_PAGE);
            if (currentPage < totalPages) {
                currentPage++;
                renderProducts();
            }
        });
    }

    // Menú hamburguesa
    var hamburger = document.querySelector('.hamburger');
    var navigation = document.querySelector('.navigation');
    if (hamburger && navigation) {
        hamburger.addEventListener('click', function() {
            navigation.classList.toggle('active');
        });
    }
});