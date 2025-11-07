package com.app.logicaServletsProductos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.app.logica.ControladoraLogica;

@WebServlet("/EliminarDatos")
public class EliminarDatos extends HttpServlet {
    private static final long serialVersionUID = 1L;

    ControladoraLogica control = new ControladoraLogica();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Obtener el ID del producto desde el parámetro
            int id = Integer.parseInt(request.getParameter("id"));

            // Llamar al método de eliminación en la lógica
            control.eliminarProducto(id);

            // Redirigir nuevamente al listado de productos
            response.sendRedirect("LeerDatos");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("❌ Error al eliminar producto: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // También acepta POST si fuera necesario
    }
}
