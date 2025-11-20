package com.app.logica;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menus menu;   // ← Menú completo, no solo el nombre

    private String descripcion;
    private String horario;
    private String nombreCliente;
    private String cursoDivision;

    private String estado;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    public Pedido() {}

    public Pedido(Menus menu, String descripcion, String horario,
                  String nombreCliente, String cursoDivision,
                  String estado, Date fechaCreacion) {
        this.menu = menu;
        this.descripcion = descripcion;
        this.horario = horario;
        this.nombreCliente = nombreCliente;
        this.cursoDivision = cursoDivision;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    // Getters y Setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Menus getMenu() { return menu; }
    public void setMenu(Menus menu) { this.menu = menu; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public String getCursoDivision() { return cursoDivision; }
    public void setCursoDivision(String cursoDivision) { this.cursoDivision = cursoDivision; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Date getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Date fechaCreacion) { this.fechaCreacion = fechaCreacion; }

  
}
