//package com.app.persistencia;
//
//import com.app.logica.Pedido;
//import com.app.persistencia.exceptions.NonexistentEntityException;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.Persistence;
//import java.io.Serializable;
//import java.util.List;
//
//public class PedidoJpaController implements Serializable {
//
//    private EntityManagerFactory emf = null;
//
//    public PedidoJpaController() {
//        emf = Persistence.createEntityManagerFactory("clotyStore");
//    }
//
//    public EntityManager getEntityManager() {
//        return emf.createEntityManager();
//    }
//
//    public void create(Pedido pedido) {
//        EntityManager em = getEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.persist(pedido);
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//    }
//
//    public void edit(Pedido pedido) throws Exception {
//        EntityManager em = getEntityManager();
//        try {
//            em.getTransaction().begin();
//            pedido = em.merge(pedido);
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            em.close();
//        }
//    }
//
//    public void destroy(int id) throws NonexistentEntityException {
//        EntityManager em = getEntityManager();
//        try {
//            em.getTransaction().begin();
//            Pedido pedido;
//            try {
//                pedido = em.getReference(Pedido.class, id);
//                pedido.getId();
//            } catch (Exception e) {
//                throw new NonexistentEntityException("El pedido con id " + id + " no existe.");
//            }
//            em.remove(pedido);
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//    }
//
//    public Pedido findPedido(int id) {
//        EntityManager em = getEntityManager();
//        try {
//            return em.find(Pedido.class, id);
//        } finally {
//            em.close();
//        }
//    }
//
//    public List<Pedido> findPedidoEntities() {
//        EntityManager em = getEntityManager();
//        try {
//            return em.createQuery("SELECT p FROM Pedido p", Pedido.class).getResultList();
//        } finally {
//            em.close();
//        }
//    }
//}
