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

@WebServlet("/EditarPedido")
public class EditarPedido extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        ControladoraLogica control = new ControladoraLogica();
        Pedido pedido = control.buscarPedido(id);
        List<Menus> listaMenus = control.listarMenus();

        request.setAttribute("pedido", pedido);
        request.setAttribute("listaMenus", listaMenus);

        request.getRequestDispatcher("/vistas/editarPedido.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ControladoraLogica control = new ControladoraLogica();

        int id = Integer.parseInt(request.getParameter("id"));
        int idMenu = Integer.parseInt(request.getParameter("idMenu"));

        String nombreCliente = request.getParameter("nombreCliente");
        String descripcion = request.getParameter("descripcion");
        String horario = request.getParameter("horario");
        String cursoDivision = request.getParameter("cursoDivision");
        String estado = request.getParameter("estado");

        Pedido pedido = control.buscarPedido(id);
        Menus menu = control.buscarMenu(idMenu);

        // Actualizar datos
        pedido.setNombreCliente(nombreCliente);
        pedido.setDescripcion(descripcion);
        pedido.setHorario(horario);
        pedido.setCursoDivision(cursoDivision);
        pedido.setEstado(estado);
        pedido.setMenu(menu);

        // Guardar cambios
        control.editarPedido(pedido);

        // Redirigir a la lista (EVITA reenviar formulario)
        response.sendRedirect("lecturaPedidos");
    }
}
