package com.app.logica.ServletsPedidos;

import java.io.IOException;

import com.app.logica.ControladoraLogica;
import com.app.logica.Menus;
import com.app.logica.Pedido;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AltaPedidos")
public class AltaPedidos extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // 1) Tomamos el ID del menú
            int menuId = Integer.parseInt(request.getParameter("menuId"));

            // 2) Otros datos del pedido
            String horario = request.getParameter("horario");
            String nombreCliente = request.getParameter("nombreCliente");
            String cursoDivision = request.getParameter("cursoDivision");

            // 3) Cargar controladora
            ControladoraLogica control = new ControladoraLogica();

            // 4) Buscar menú asociado
            Menus menu = control.buscarMenu(menuId);

            if (menu == null) {
                throw new Exception("El menú seleccionado no existe.");
            }

            // 5) Crear pedido
            Pedido pedido = new Pedido();
            pedido.setMenu(menu); // <-- RELACIÓN FK DIRECTA
            pedido.setHorario(horario);
            pedido.setNombreCliente(nombreCliente);
            pedido.setCursoDivision(cursoDivision);
            pedido.setEstado("pendiente");
            pedido.setFechaCreacion(new java.util.Date());

            // 6) Guardar en BD
            control.crearPedido(pedido);

            // 7) Redirigir
            response.sendRedirect("LecturaPedidos");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al crear el pedido: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
