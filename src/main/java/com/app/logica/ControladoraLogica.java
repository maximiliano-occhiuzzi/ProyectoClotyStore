package com.app.logica;

import java.util.List;
import com.app.persistencia.ControladoraPersistencia;

public class ControladoraLogica {

    // Instancia de la capa de persistencia
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

 // ======================
 // CRUD - MENÚS
 // ======================

 // Crear menú
 public void crearMenu(String nombre, String descripcion, double precio, String imagen) throws Exception {

     // Validaciones
     if (nombre == null || nombre.trim().isEmpty()) {
         throw new Exception("El nombre del menú no puede estar vacío.");
     }

     if (descripcion == null || descripcion.trim().isEmpty()) {
         throw new Exception("La descripción no puede estar vacía.");
     }

     if (precio <= 0) {
         throw new Exception("El precio debe ser mayor que 0.");
     }

     if (imagen == null || imagen.trim().isEmpty()) {
         throw new Exception("Debe incluir una imagen para el menú.");
     }

     // Crear el objeto
     Menus menu = new Menus();
     menu.setNombre(nombre);
     menu.setDescripcion(descripcion);
     menu.setPrecio(precio);
     menu.setImagen(imagen);

     // Guardar en BD
     controlPersis.crearMenu(menu);
 }

 // Editar menú
 public void editarMenu(Menus menu) throws Exception {

     Menus existente = controlPersis.buscarMenu(menu.getId());

     if (existente == null) {
         throw new Exception("No existe un menú con ID " + menu.getId());
     }

     // Validaciones
     if (menu.getNombre() == null || menu.getNombre().trim().isEmpty()) {
         throw new Exception("El nombre no puede estar vacío.");
     }

     if (menu.getDescripcion() == null || menu.getDescripcion().trim().isEmpty()) {
         throw new Exception("La descripción es obligatoria.");
     }

     if (menu.getPrecio() <= 0) {
         throw new Exception("El precio debe ser mayor que 0.");
     }

     if (menu.getNombre().length() > 100) {
         throw new Exception("El nombre no puede superar los 100 caracteres.");
     }

     // Guardar cambios
     controlPersis.editarMenu(menu);
 }

 // Eliminar menú
 public void eliminarMenu(int id) throws Exception {

     Menus menu = controlPersis.buscarMenu(id);

     if (menu == null) {
         throw new Exception("No existe un menú con ID " + id);
     }

     controlPersis.eliminarMenu(id);
 }

 // Buscar un menú por ID
 public Menus buscarUnMenu(int id) {
     return controlPersis.buscarMenu(id);
 }

 // Listar todos los menús
 public List<Menus> listarMenus() {
     return controlPersis.buscarTodosLosMenus();
 }

    
    
    // ======================
    // CRUD - PRODUCTOS
    // ======================

    // Crear producto
    public void crearProducto(String nombre, double precio, int stock, String categoria, String imagen) throws Exception {

        // Validaciones
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre del producto no puede estar vacío.");
        }

        if (precio <= 0) {
            throw new Exception("El precio debe ser mayor que 0.");
        }

        if (stock < 0) {
            throw new Exception("El stock no puede ser negativo.");
        }

        if (categoria == null || categoria.trim().isEmpty()) {
            throw new Exception("La categoría del producto es obligatoria.");
        }

        if (imagen == null || imagen.trim().isEmpty()) {
            throw new Exception("Debe incluir una imagen para el producto.");
        }

        // Si pasa todas las validaciones, se crea el objeto
        Productos prod = new Productos();
        prod.setNombre(nombre);
        prod.setPrecio(precio);
        prod.setStock(stock);
        prod.setCategoria(categoria);
        prod.setImagen(imagen);

        // Se guarda en la BD
        controlPersis.crearProducto(prod);
    }

    // Editar producto
    public void editarProducto(Productos prod) throws Exception {

        // Verificar existencia
        Productos existente = controlPersis.buscarProducto(prod.getId());
        if (existente == null) {
            throw new Exception("No existe un producto con ID " + prod.getId());
        }

        // Validaciones
        if (prod.getNombre() == null || prod.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre del producto no puede estar vacío.");
        }

        if (prod.getPrecio() <= 0) {
            throw new Exception("El precio debe ser mayor que 0.");
        }

        if (prod.getStock() < 0) {
            throw new Exception("El stock no puede ser negativo.");
        }

        if (prod.getCategoria() == null || prod.getCategoria().trim().isEmpty()) {
            throw new Exception("Debe especificarse una categoría.");
        }

        // Validación adicional (opcional): verificar longitud del nombre
        if (prod.getNombre().length() > 100) {
            throw new Exception("El nombre no puede superar los 100 caracteres.");
        }

        // Guardar cambios
        controlPersis.editarProducto(prod);
    }

    // Eliminar producto
    public void eliminarProducto(int id) throws Exception {

        Productos prod = controlPersis.buscarProducto(id);

        if (prod == null) {
            throw new Exception("No existe un producto con ID " + id);
        }

        controlPersis.eliminarProducto(id);
    }

    // Buscar un producto por ID
    public Productos buscarUnProducto(int id) {
        return controlPersis.buscarProducto(id);
    }

    // Listar todos los productos
    public List<Productos> listarProductos() {
        return controlPersis.buscarTodosLosProductos();
    }
}
