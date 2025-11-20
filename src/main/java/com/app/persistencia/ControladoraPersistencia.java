package com.app.persistencia;

import java.util.List;

import com.app.logica.Menus;
import com.app.logica.Pedido;
import com.app.logica.Productos;

public class ControladoraPersistencia {

    // Instancias JPA
    private final ProductosJpaController productosJpa = new ProductosJpaController();
    private final MenusJpaController menusJpa = new MenusJpaController();  // ← corregido
    private final PedidoJpaController pedidoJpa = new PedidoJpaController();

    // ======================
    // CRUD - PEDIDOS
    // ======================

    public void crearPedido(Pedido pedido) {
        pedidoJpa.create(pedido);
    }

    public List<Pedido> traerPedidos() {
        return pedidoJpa.findPedidoEntities();
    }

    public Pedido buscarPedido(int id) {
        return pedidoJpa.findPedido(id);
    }

    public void editarPedido(Pedido pedido) {
        try {
            pedidoJpa.edit(pedido);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarPedido(int id) {
        try {
            pedidoJpa.destroy(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ======================
    // CRUD - MENÚS
    // ======================

    public void crearMenu(Menus menu) {
        menusJpa.create(menu);
    }

    public void editarMenu(Menus menu) {
        try {
            menusJpa.edit(menu);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarMenu(int id) {
        try {
            menusJpa.destroy(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Menus buscarMenu(int id) {
        return menusJpa.findMenus(id);
    }

    public List<Menus> buscarTodosLosMenus() {
        return menusJpa.findMenusEntities();
    }

    // ======================
    // CRUD - PRODUCTOS
    // ======================

    public void crearProducto(Productos producto) {
        try {
            productosJpa.create(producto);
        } catch (Exception e) {
            System.out.println("Error al crear producto: " + e.getMessage());
        }
    }

    public Productos buscarProducto(int id) {
        return productosJpa.findProductos(id);
    }

    public List<Productos> buscarTodosLosProductos() {
        return productosJpa.findProductosEntities();
    }

    public void editarProducto(Productos producto) {
        try {
            productosJpa.edit(producto);
        } catch (Exception e) {
            System.out.println("Error al editar producto: " + e.getMessage());
        }
    }

    public void eliminarProducto(int id) {
        try {
            productosJpa.destroy(id);
        } catch (Exception e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }
    }
}
