//package com.app.logica.ServletsPedidos;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//import com.app.logica.ControladoraLogica;
//import com.app.logica.Pedido;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@WebServlet("/AltaPedido")
//public class AltaPedido extends HttpServlet {
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        try {
//            String menu = request.getParameter("menu");
//            String descripcion = request.getParameter("descripcion");
//            String horario = request.getParameter("horario");
//            String nombreCliente = request.getParameter("nombre_cliente");
//            String cursoDivision = request.getParameter("curso_division");
//            String rol = request.getParameter("rol");
//
//            ControladoraLogica control = new ControladoraLogica();
//            control.crearPedido(menu, descripcion, horario, nombreCliente, cursoDivision, rol);
//
//            response.sendRedirect("LeerPedidos");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            request.setAttribute("error", "Error al crear el pedido: " + e.getMessage());
//            request.getRequestDispatcher("error.jsp").forward(request, response);
//        }
//    }
//}
