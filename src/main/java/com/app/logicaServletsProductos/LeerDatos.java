package com.app.logicaServletsProductos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.app.logica.Productos;

@WebServlet("/LeerDatos")
public class LeerDatos extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Productos> lista = new ArrayList<>();

        // Conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/cloty_store";  // cambialo por tu DB real
        String user = "root";  // usuario de tu BD
        String pass = "";      // contraseña de tu BD

        try (Connection con = DriverManager.getConnection(url, user, pass)) {

            String sql = "SELECT id, nombre, precio, stock, imagen FROM productos WHERE stock > 0";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Productos p = new Productos();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                p.setImagen(rs.getString("imagen"));
                lista.add(p);
            }

            request.setAttribute("listaProductos", lista);
            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error al leer productos: " + e.getMessage());
        }
    }
}
