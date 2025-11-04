package com.app.logicaServletsProductos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.app.logica.ControladoraLogica;
import com.app.logica.Productos;

@WebServlet("/CrearDatos")
public class CrearDatos extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // 1️⃣ Recibir los datos del formulario
            String nombre = request.getParameter("nombre");
            double precio = Double.parseDouble(request.getParameter("precio"));
            int stock = Integer.parseInt(request.getParameter("stock"));
            String categoria = request.getParameter("categoria");
            String imagen = request.getParameter("imagen");

            // 2️⃣ Llamar a la capa lógica
            ControladoraLogica control = new ControladoraLogica();
            control.crearProducto(nombre, precio, stock, categoria, imagen);

            // 3️⃣ Redirigir o mostrar mensaje
            response.sendRedirect("LeerDatos");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al crear el producto: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
