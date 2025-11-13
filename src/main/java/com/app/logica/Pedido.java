//package com.app.logica;
//
//import java.io.Serializable;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name = "pedidos")
//public class Pedido implements Serializable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @Column(name = "nombre_cliente", nullable = false)
//    private String nombreCliente;
//
//    @Column(nullable = false)
//    private String descripcion;
//
//    @Column(nullable = false)
//    private String horario;
//
//    @Column(nullable = false)
//    private String estado;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "fecha_creacion", nullable = false)
//    private Date fechaCreacion;
//
//    // Relación con usuario (muchos pedidos pueden pertenecer a un usuario)
//    @ManyToOne
//    @JoinColumn(name = "usuario_id")
//    private Usuario usuario;
//
//    // Constructor vacío requerido por JPA
//    public Pedido() {
//        this.fechaCreacion = new Date(); // Asignamos la fecha actual automáticamente
//        this.estado = "Pendiente"; // Valor por defecto
//    }
//
//    // Constructor con parámetros (útil para crear pedidos desde el formulario)
//    public Pedido(String nombreCliente, String descripcion, String horario, Usuario usuario) {
//        this.nombreCliente = nombreCliente;
//        this.descripcion = descripcion;
//        this.horario = horario;
//        this.usuario = usuario;
//        this.fechaCreacion = new Date();
//        this.estado = "Pendiente";
//    }
//
//    // Getters y Setters
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getNombreCliente() {
//        return nombreCliente;
//    }
//
//    public void setNombreCliente(String nombreCliente) {
//        this.nombreCliente = nombreCliente;
//    }
//
//    public String getDescripcion() {
//        return descripcion;
//    }
//
//    public void setDescripcion(String descripcion) {
//        this.descripcion = descripcion;
//    }
//
//    public String getHorario() {
//        return horario;
//    }
//
//    public void setHorario(String horario) {
//        this.horario = horario;
//    }
//
//    public String getEstado() {
//        return estado;
//    }
//
//    public void setEstado(String estado) {
//        this.estado = estado;
//    }
//
//    public Date getFechaCreacion() {
//        return fechaCreacion;
//    }
//
//    public void setFechaCreacion(Date fechaCreacion) {
//        this.fechaCreacion = fechaCreacion;
//    }
//
//    public Usuario getUsuario() {
//        return usuario;
//    }
//
//    public void setUsuario(Usuario usuario) {
//        this.usuario = usuario;
//    }
//
//    @Override
//    public String toString() {
//        return "Pedido{" +
//                "id=" + id +
//                ", nombreCliente='" + nombreCliente + '\'' +
//                ", descripcion='" + descripcion + '\'' +
//                ", horario='" + horario + '\'' +
//                ", estado='" + estado + '\'' +
//                ", fechaCreacion=" + fechaCreacion +
//                ", usuario=" + (usuario != null ? usuario.getNombre() : "Sin usuario") +
//                '}';
//    }
//}
