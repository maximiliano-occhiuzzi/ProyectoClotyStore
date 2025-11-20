package com.app.logica.ServletsPedidos;

import java.io.IOException;

import com.app.logica.ControladoraLogica;
import com.app.logica.Pedido;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EditarPedido")
public class EditarPedido extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        ControladoraLogica control = new ControladoraLogica();
        Pedido pedido = control.buscarPedido(id);

        request.setAttribute("pedido", pedido);

        request.getRequestDispatcher("vistas/pedidos/editarPedido.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre_cliente");
        String descripcion = request.getParameter("descripcion");

        ControladoraLogica control = new ControladoraLogica();
        
        Pedido pedido = control.buscarPedido(id);
        pedido.setNombreCliente(nombre);
        pedido.setDescripcion(descripcion);

        control.editarPedido(pedido);

        response.sendRedirect("LeerPedidos");
    }
}
