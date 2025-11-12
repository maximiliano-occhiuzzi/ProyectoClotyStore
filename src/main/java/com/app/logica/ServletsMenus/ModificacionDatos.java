package com.app.logica.ServletsMenus;

import com.app.logica.ControladoraLogica;
import com.app.logica.Menus;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ModificacionDatos")
public class ModificacionDatos extends HttpServlet {
    private static final long serialVersionUID = 1L;

    ControladoraLogica control = new ControladoraLogica();

    // ======================
    // MOSTRAR FORMULARIO (GET)
    // ======================
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Obtener ID desde la URL
            int id = Integer.parseInt(request.getParameter("id"));

            // Buscar menú en BD
            Menus menu = control.buscarUnMenu(id);

            if (menu != null) {
            	request.setAttribute("menuEditar", menu);
                request.getRequestDispatcher("/vistas/editarMenu.jsp").forward(request, response);
            } else {
                response.getWriter().println("❌ No existe un menú con ID: " + id);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("❌ Error al cargar el menú: " + e.getMessage());
        }
    }

    // ======================
    // ACTUALIZAR MENÚ (POST)
    // ======================
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // 1️⃣ Obtener datos del formulario
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            double precio = Double.parseDouble(request.getParameter("precio"));
            String imagen = request.getParameter("imagen");

            // 2️⃣ Crear objeto Menus con los nuevos datos
            Menus menu = new Menus();
            menu.setId(id);
            menu.setNombre(nombre);
            menu.setDescripcion(descripcion);
            menu.setPrecio(precio);
            menu.setImagen(imagen);

            // 3️⃣ Actualizar en la BD usando la lógica con validaciones
            control.editarMenu(menu);

            // 4️⃣ Volver al listado de menús
            response.sendRedirect("LecturaDatos");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "❌ Error al actualizar menú: " + e.getMessage());
            request.getRequestDispatcher("/vistas/editarmenu.jsp").forward(request, response);
        }
    }
}
