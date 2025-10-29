package com.app.persistencia;

import java.util.List;
import com.app.logica.Productos;

public class ControladoraPersistencia {

    // Instancia del controlador JPA para la entidad Productos
    private final ProductosJpaController productosJpa = new ProductosJpaController();

    // ======================
    // CRUD - PRODUCTOS
    // ======================

    // Crear un producto
    public void crearProducto(Productos producto) {
        try {
            productosJpa.create(producto);
        } catch (Exception e) {
            System.out.println("Error al crear producto: " + e.getMessage());
        }
    }

    // Buscar un producto por ID
    public Productos buscarProducto(int id) {
        return productosJpa.findProductos(id);
    }

    // Buscar todos los productos
    public List<Productos> buscarTodosLosProductos() {
        return productosJpa.findProductosEntities();
    }

    // Editar producto existente
    public void editarProducto(Productos producto) {
        try {
            productosJpa.edit(producto);
        } catch (Exception e) {
            System.out.println("Error al editar producto: " + e.getMessage());
        }
    }

    // Eliminar un producto
    public void eliminarProducto(int id) {
        try {
            productosJpa.destroy(id);
        } catch (Exception e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }
    }
}
