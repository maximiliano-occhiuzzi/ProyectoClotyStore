package com.app.logica.ServletsMenus;

import java.io.IOException;

import com.app.logica.ControladoraLogica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/BajaDatos")
public class BajaDatos extends HttpServlet {
    private static final long serialVersionUID = 1L;

    ControladoraLogica control = new ControladoraLogica();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            // eliminar con validación
            control.eliminarMenu(id);

            // volver al listado
            response.sendRedirect("LecturaDatos");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("❌ Error al eliminar el menú: " + e.getMessage());
        }
    }
}
