package com.app.logica.ServletsPedidos;

import java.io.IOException;
import java.util.List;

import com.app.logica.ControladoraLogica;
import com.app.logica.Menus;
import com.app.logica.Pedido;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/lecturaCrearPedido")
public class lecturaCrearPedido extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ControladoraLogica control = new ControladoraLogica();

        // Cargar todos los menús
        List<Menus> lista = control.listarMenus();

        // Enviar la lista al JSP
        request.setAttribute("listaMenus", lista);

        // Forward CORRECTO
        request.getRequestDispatcher("/vistas/crearPedido.jsp").forward(request, response);
    }
}
