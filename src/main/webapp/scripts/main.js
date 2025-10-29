const products = [
  { id: 1, title: 'Producto 1', price: '$99', category: 'Categoría A', stock: 5 },
  { id: 2, title: 'Producto 2', price: '$149', category: 'Categoría B', stock: 3 },
  { id: 3, title: 'Producto 3', price: '$199', category: 'Categoría A', stock: 8 },
  { id: 4, title: 'Producto 4', price: '$79', category: 'Categoría C', stock: 12 },
  { id: 5, title: 'Producto 5', price: '$129', category: 'Categoría B', stock: 6 },
  { id: 6, title: 'Producto 6', price: '$89', category: 'Categoría A', stock: 15 },
  { id: 7, title: 'Producto 7', price: '$169', category: 'Categoría C', stock: 4 },
  { id: 8, title: 'Producto 8', price: '$219', category: 'Categoría B', stock: 7 },
  { id: 9, title: 'Producto 9', price: '$99', category: 'Categoría A', stock: 10 },
  { id: 10, title: 'Producto 10', price: '$139', category: 'Categoría C', stock: 5 },
  { id: 11, title: 'Producto 11', price: '$179', category: 'Categoría B', stock: 9 },
  { id: 12, title: 'Producto 12', price: '$109', category: 'Categoría A', stock: 11 },
  { id: 13, title: 'Producto 13', price: '$159', category: 'Categoría C', stock: 6 },
  { id: 14, title: 'Producto 14', price: '$189', category: 'Categoría B', stock: 8 },
  { id: 15, title: 'Producto 15', price: '$119', category: 'Categoría A', stock: 13 },
  { id: 16, title: 'Producto 16', price: '$149', category: 'Categoría C', stock: 4 },
  { id: 17, title: 'Producto 17', price: '$199', category: 'Categoría B', stock: 7 },
  { id: 18, title: 'Producto 18', price: '$129', category: 'Categoría A', stock: 12 },
];

const ITEMS_PER_PAGE = 9;
let currentPage = 1;
let productStocks = {};
let currentCategory = 'all';

products.forEach(product => {
  productStocks[product.id] = product.stock;
});

function getFilteredProducts() {
  if (currentCategory === 'all') {
    return products;
  }
  return products.filter(product => product.category === currentCategory);
}

function renderProducts() {
  const grid = document.getElementById('productsGrid');
  const filteredProducts = getFilteredProducts();
  const startIndex = (currentPage - 1) * ITEMS_PER_PAGE;
  const endIndex = startIndex + ITEMS_PER_PAGE;
  const pageProducts = filteredProducts.slice(startIndex, endIndex);

  grid.innerHTML = pageProducts.map(product => `
    <div class="product-card">
      <div class="product-image"></div>
      <h3 class="product-title">${product.title}</h3>
      <p class="product-price">${product.price}</p>
      <p class="product-category">${product.category}</p>
      <p class="stock-info">stock: <span class="stock-value">${productStocks[product.id]}</span></p>
    </div>
  `).join('');

  updatePaginationButtons();
}

function updatePaginationButtons() {
  const prevBtn = document.getElementById('prevBtn');
  const nextBtn = document.getElementById('nextBtn');
  const filteredProducts = getFilteredProducts();
  const totalPages = Math.ceil(filteredProducts.length / ITEMS_PER_PAGE);

  prevBtn.disabled = currentPage === 1;
  nextBtn.disabled = currentPage === totalPages || totalPages === 0;
}

function handleStockChange(productId, action) {
  if (action === 'increase') {
    productStocks[productId]++;
  } else if (action === 'decrease' && productStocks[productId] > 0) {
    productStocks[productId]--;
  }

  const stockElement = document.querySelector(`.stock-value[data-id="${productId}"]`);
  if (stockElement) {
    stockElement.textContent = productStocks[productId];
  }
}

function navigateToPage(page) {
  const pages = document.querySelectorAll('.page');
  const navBtns = document.querySelectorAll('.nav-btn');

  pages.forEach(p => p.classList.remove('active'));
  navBtns.forEach(btn => btn.classList.remove('active'));

  const targetPage = document.getElementById(page);
  const targetBtn = document.querySelector(`[data-page="${page}"]`);

  if (targetPage) targetPage.classList.add('active');
  if (targetBtn) targetBtn.classList.add('active');

  if (page === 'productos') {
    renderProducts();
  }

  const nav = document.querySelector('.navigation');
  nav.classList.remove('active');
}

document.addEventListener('DOMContentLoaded', () => {
  renderProducts();

  document.querySelectorAll('.nav-btn').forEach(btn => {
    btn.addEventListener('click', (e) => {
      const page = e.target.dataset.page;
      navigateToPage(page);
    });
  });

  document.querySelector('.cta-button')?.addEventListener('click', (e) => {
    const page = e.target.dataset.navigate;
    if (page) {
      navigateToPage(page);
    }
  });

  document.getElementById('prevBtn').addEventListener('click', () => {
    if (currentPage > 1) {
      currentPage--;
      renderProducts();
    }
  });

  document.getElementById('nextBtn').addEventListener('click', () => {
    const filteredProducts = getFilteredProducts();
    const totalPages = Math.ceil(filteredProducts.length / ITEMS_PER_PAGE);
    if (currentPage < totalPages) {
      currentPage++;
      renderProducts();
    }
  });

  document.querySelectorAll('.category-btn').forEach(btn => {
    btn.addEventListener('click', (e) => {
      document.querySelectorAll('.category-btn').forEach(b => b.classList.remove('active'));
      e.target.classList.add('active');
      currentCategory = e.target.dataset.category;
      currentPage = 1;
      renderProducts();
    });
  });

  document.querySelectorAll('.action-btn').forEach(btn => {
    btn.addEventListener('click', () => {
      alert('Funcionalidad CRUD próximamente');
    });
  });

  const hamburger = document.querySelector('.hamburger');
  const navigation = document.querySelector('.navigation');

  hamburger?.addEventListener('click', () => {
    navigation.classList.toggle('active');
  });
});
