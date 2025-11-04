package com.app.logicaServletsProductos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.app.logica.ControladoraLogica;
import com.app.logica.Productos;

@WebServlet("/EditarDatos")
public class EditarDatos extends HttpServlet {
    private static final long serialVersionUID = 1L;

    ControladoraLogica control = new ControladoraLogica();

    // ======================
    // MOSTRAR FORMULARIO (GET)
    // ======================
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Obtener el ID del producto desde la URL
            int id = Integer.parseInt(request.getParameter("id"));

            // Buscar el producto en la base de datos
            Productos producto = control.buscarUnProducto(id);

            if (producto != null) {
                // Enviar producto al JSP
                request.setAttribute("producto", producto);
                request.getRequestDispatcher("editarproducto.jsp").forward(request, response);
            } else {
                // Si no se encuentra el producto
                response.getWriter().println("Producto no encontrado con ID: " + id);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error al cargar el producto: " + e.getMessage());
        }
    }

    // ======================
    // ACTUALIZAR PRODUCTO (POST)
    // ======================
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Obtener datos del formulario
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            double precio = Double.parseDouble(request.getParameter("precio"));
            int stock = Integer.parseInt(request.getParameter("stock"));
            String categoria = request.getParameter("categoria");
            String imagen = request.getParameter("imagen");

            // Crear objeto producto con los nuevos datos
            Productos producto = new Productos(nombre, precio, stock, categoria, imagen);
            producto.setId(id);

            // Actualizar en BD
            control.editarProducto(producto);

            // Volver al listado
            response.sendRedirect("LeerDatos");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("❌ Error al actualizar producto: " + e.getMessage());
        }
    }
}
