package com.app.logica.ServletsMenus;

import java.io.IOException;

import com.app.logica.ControladoraLogica;
import com.app.logica.Menus;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AltaDatos")
public class AltaDatos extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            double precio = Double.parseDouble(request.getParameter("precio"));
            String imagen = request.getParameter("imagen");

            ControladoraLogica control = new ControladoraLogica();
            control.crearMenu(nombre, descripcion, precio, imagen);

            response.sendRedirect("LecturaDatos");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al crear el menú: " + e.getMessage());
            request.getRequestDispatcher("error.jsp")
                   .forward(request, response);
        }
    }
}
