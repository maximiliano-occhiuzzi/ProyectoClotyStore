var ITEMS_PER_PAGE = 9;
var currentPage = 1;
var productStocks = {};
var currentCategory = 'all';

// --- Inicializar stocks ---
products.forEach(function(product) {
    productStocks[product.id] = product.stock;
});

// --- Funciones de filtrado y renderizado ---
function getFilteredProducts() {
    if (currentCategory === 'all') return products;
    return products.filter(function(product) {
        return product.category === currentCategory;
    });
}

function renderProducts() {
    var container = document.getElementById('productsGrid');
    if (!container) return;
    container.innerHTML = '';

    var filteredProducts = getFilteredProducts();
    var startIndex = (currentPage - 1) * ITEMS_PER_PAGE;
    var endIndex = startIndex + ITEMS_PER_PAGE;
    var productsToShow = filteredProducts.slice(startIndex, endIndex);

	productsToShow.forEach(function(p) {
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
	            '<button class="editar-btn" onclick="window.location.href=\'' + window.location.origin + window.location.pathname.replace(/\/[^\/]*$/, "") + '/EditarDatos?id=' + p.id + '\'">Editar</button>' +
	            '<button class="eliminar-btn" onclick="if(confirm(\'¿Seguro que deseas eliminar el producto "' + p.title + '"?\')) window.location.href=\'EliminarDatos?id=' + p.id + '\'">Eliminar</button>' +
	        '</div>';

	    container.appendChild(card);
	});

	updatePaginationButtons();
}
function eliminarProducto(id, nombre) {
    if (confirm('¿Seguro que deseas eliminar el producto "' + nombre + '"?')) {
        window.location.href = 'EliminarDatos?id=' + id;
    }
}

function updatePaginationButtons() {
    var prevBtn = document.getElementById('prevBtn');
    var nextBtn = document.getElementById('nextBtn');
    var filteredProducts = getFilteredProducts();
    var totalPages = Math.ceil(filteredProducts.length / ITEMS_PER_PAGE);

    if (prevBtn) prevBtn.disabled = currentPage === 1;
    if (nextBtn) nextBtn.disabled = currentPage === totalPages || totalPages === 0;
}

// --- Control de stock (botones CRUD futuros) ---
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

// --- Inicialización general ---
document.addEventListener('DOMContentLoaded', function() {
    renderProducts();

    // Navegación con botones del menú
    var navButtons = document.querySelectorAll('.nav-btn');
    for (var i = 0; i < navButtons.length; i++) {
        navButtons[i].addEventListener('click', function(e) {
            var page = e.target.getAttribute('data-page');
            navigateToPage(page);
        });
    }

    // --- BOTÓN “VER PRODUCTOS” (inicio) ---
    var verProductosBtn = document.getElementById('verProductosBtn');
    if (verProductosBtn) {
        verProductosBtn.addEventListener('click', function() {
            navigateToPage('productos');
        });
    }

    // --- Paginación ---
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

    // --- Filtros de categoría ---
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

    // --- Menú hamburguesa ---
    var hamburger = document.querySelector('.hamburger');
    var navigation = document.querySelector('.navigation');
    if (hamburger && navigation) {
        hamburger.addEventListener('click', function() {
            navigation.classList.toggle('active');
        });
    }
});
