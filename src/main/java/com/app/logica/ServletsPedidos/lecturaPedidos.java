package com.app.logica.ServletsPedidos;

import java.io.IOException;
import java.util.List;

import com.app.logica.ControladoraLogica;
import com.app.logica.Pedido;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/lecturaPedidos")
public class lecturaPedidos extends HttpServlet {

    private ControladoraLogica control = new ControladoraLogica();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Pedido> lista = control.traerPedidos();

        request.setAttribute("listaPedidos", lista);

        // Redirige a index.jsp en la raíz del proyecto
        request.getRequestDispatcher("/vistas/index.jsp").forward(request, response);
    }
}
