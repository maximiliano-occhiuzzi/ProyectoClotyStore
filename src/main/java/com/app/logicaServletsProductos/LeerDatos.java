package com.app.logicaServletsProductos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.app.logica.ControladoraLogica;
import com.app.logica.Productos;

@WebServlet("/LeerDatos")
public class LeerDatos extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            ControladoraLogica control = new ControladoraLogica();
            List<Productos> lista = control.listarProductos();

            // Si querés solo los que tengan stock > 0
            lista.removeIf(p -> p.getStock() <= 0);

            request.setAttribute("productos", lista);
            request.getRequestDispatcher("vistas/index.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error al leer productos: " + e.getMessage());
        }
    }
}
