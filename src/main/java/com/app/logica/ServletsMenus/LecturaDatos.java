package com.app.logica.ServletsMenus;

import java.io.IOException;
import java.util.List;

import com.app.logica.ControladoraLogica;
import com.app.logica.Menus;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LecturaDatos")
public class LecturaDatos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Instancia la lógica
        ControladoraLogica control = new ControladoraLogica();

        // Obtiene lista de menús
        List<Menus> lista = control.listarMenus();

        // Envía datos al JSP
        request.setAttribute("listaMenus", lista);
        request.getRequestDispatcher("/vistas/menus.jsp").forward(request, response);

    }
}
