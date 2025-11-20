package com.app.persistencia;

import java.util.List;

import com.app.logica.Pedido;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PedidoJpaController {

    private static final EntityManagerFactory emf = 
            Persistence.createEntityManagerFactory("clotyStore");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear pedido
    public void create(Pedido pedido) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(pedido);  // si el pedido tiene un Menus asociado, también lo asocia
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Listar todos los pedidos
    public List<Pedido> findPedidoEntities() {
        EntityManager em = getEntityManager();
        return em.createQuery(
            "SELECT p FROM Pedido p ORDER BY p.fechaCreacion DESC", 
            Pedido.class
        ).getResultList();
    }

    // Buscar uno
    public Pedido findPedido(int id) {
        return getEntityManager().find(Pedido.class, id);
    }

    // Editar
    public void edit(Pedido pedido) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(pedido); // actualiza todos los campos, incluida la FK Menus
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // Eliminar
    public void destroy(int id) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Pedido p = em.find(Pedido.class, id);
            if (p != null) em.remove(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
