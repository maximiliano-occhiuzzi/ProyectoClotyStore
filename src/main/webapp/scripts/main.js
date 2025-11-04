var ITEMS_PER_PAGE = 9;
var currentPage = 1;
var productStocks = {};
var currentCategory = 'all';

// Inicializar stocks
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
  container.innerHTML = '';

  var filteredProducts = getFilteredProducts();
  var startIndex = (currentPage - 1) * ITEMS_PER_PAGE;
  var endIndex = startIndex + ITEMS_PER_PAGE;
  var productsToShow = filteredProducts.slice(startIndex, endIndex);


  document.addEventListener("DOMContentLoaded", function () {
    const container = document.getElementById("product-container");

    if (!container) return;

    // Objeto auxiliar para stock actual
    const productStocks = {};
    products.forEach(p => productStocks[p.id] = p.stock);

    // Renderizado dinámico
    products.forEach(function (p) {
      const card = document.createElement('div');
      card.classList.add('product-card');

      card.innerHTML = `
        <img src="${p.image}" alt="${p.title}" class="product-image">
        <h3>${p.title}</h3>
        <p>Precio: ${p.price}</p>
        <p>Categoría: ${p.category}</p>
        <p>Stock: <span class="stock-value" data-id="${p.id}">${productStocks[p.id]}</span></p>

        <div class="product-actions">
          <button class="whatsapp-btn"
            onclick="window.open('https://wa.me/549XXXXXXXXX?text=Hola, quiero consultar por ${encodeURIComponent(p.title)}')">
            Consultar
          </button>

          <button class="edit-btn"
            onclick="window.location.href='EditarDatos?id=${p.id}'">
            Editar
          </button>
        </div>
      `;

      container.appendChild(card);
    });
  });

  updatePaginationButtons();
}

function updatePaginationButtons() {
  var prevBtn = document.getElementById('prevBtn');
  var nextBtn = document.getElementById('nextBtn');
  var filteredProducts = getFilteredProducts();
  var totalPages = Math.ceil(filteredProducts.length / ITEMS_PER_PAGE);

  prevBtn.disabled = currentPage === 1;
  nextBtn.disabled = currentPage === totalPages || totalPages === 0;
}

// --- Navegación entre páginas ---
function navigateToPage(page) {
  var pages = document.querySelectorAll('.page');
  var navBtns = document.querySelectorAll('.nav-btn');

  pages.forEach(function(p) { p.classList.remove('active'); });
  navBtns.forEach(function(btn) { btn.classList.remove('active'); });

  var targetPage = document.getElementById(page);
  var targetBtn = document.querySelector('[data-page="' + page + '"]');

  if (targetPage) targetPage.classList.add('active');
  if (targetBtn) targetBtn.classList.add('active');

  if (page === 'productos') renderProducts();

  var navigation = document.querySelector('.navigation');
  if (navigation) navigation.classList.remove('active');
}

// --- Control de stock (botones futuros CRUD) ---
function handleStockChange(productId, action) {
  if (action === 'increase') {
    productStocks[productId]++;
  } else if (action === 'decrease' && productStocks[productId] > 0) {
    productStocks[productId]--;
  }

  var stockElement = document.querySelector('.stock-value[data-id="' + productId + '"]');
  if (stockElement) stockElement.textContent = productStocks[productId];
}

// --- Inicialización ---
document.addEventListener('DOMContentLoaded', function() {
  renderProducts();

  // Navegación entre secciones
  document.querySelectorAll('.nav-btn').forEach(function(btn) {
    btn.addEventListener('click', function(e) {
      var page = e.target.getAttribute('data-page');
      navigateToPage(page);
    });
  });

  // Botón CTA del inicio
  var ctaBtn = document.querySelector('.cta-button');
  if (ctaBtn) {
    ctaBtn.addEventListener('click', function(e) {
      var page = e.target.getAttribute('data-navigate');
      if (page) navigateToPage(page);
    });
  }

  // Paginación
  document.getElementById('prevBtn').addEventListener('click', function() {
    if (currentPage > 1) {
      currentPage--;
      renderProducts();
    }
  });

  document.getElementById('nextBtn').addEventListener('click', function() {
    var filteredProducts = getFilteredProducts();
    var totalPages = Math.ceil(filteredProducts.length / ITEMS_PER_PAGE);
    if (currentPage < totalPages) {
      currentPage++;
      renderProducts();
    }
  });

  // Filtros de categoría
  document.querySelectorAll('.category-btn').forEach(function(btn) {
    btn.addEventListener('click', function(e) {
      document.querySelectorAll('.category-btn').forEach(function(b) {
        b.classList.remove('active');
      });
      e.target.classList.add('active');
      currentCategory = e.target.getAttribute('data-category');
      currentPage = 1;
      renderProducts();
    });
  });

  // Botones CRUD (placeholder)
  document.querySelectorAll('.action-btn.create-btn').forEach(function(btn) {
    btn.addEventListener('click', function() {
      window.location.href = 'crearProducto.jsp';
    });
  });

  // Menú hamburguesa
  var hamburger = document.querySelector('.hamburger');
  var navigation = document.querySelector('.navigation');
  if (hamburger && navigation) {
    hamburger.addEventListener('click', function() {
      navigation.classList.toggle('active');
    });
  }
});
