package com.app.persistencia;

import java.util.List;
import com.app.logica.Menus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MenusJpaController {

    // Fábrica de conexiones (EntityManagerFactory)
    private EntityManagerFactory emf = null;

    // Constructor: conecta a la unidad de persistencia
    public MenusJpaController() {
        emf = Persistence.createEntityManagerFactory("clotyStore");
    }

    // Obtener EntityManager
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // ===========================
    // CRUD - MENÚS
    // ===========================

    // Crear menú
    public void create(Menus menu) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(menu);
            em.getTransaction().commit();
            System.out.println("✅ Menú creado correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al crear menú: " + e.getMessage());
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em != null) em.close();
        }
    }

    // Buscar menú por ID
    public Menus findMenus(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Menus.class, id);
        } finally {
            em.close();
        }
    }

    // Buscar todos los menús
    public List<Menus> findMenusEntities() {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT m FROM Menus m";
            return em.createQuery(jpql, Menus.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Editar menú existente
    public void edit(Menus menu) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(menu);
            em.getTransaction().commit();
            System.out.println("✅ Menú editado correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al editar menú: " + e.getMessage());
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (em != null) em.close();
        }
    }

    // Eliminar menú
    public void destroy(int id) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Menus menu = em.find(Menus.class, id);

            if (menu != null) {
                em.remove(menu);
                System.out.println("✅ Menú eliminado correctamente.");
            } else {
                System.out.println("⚠️ No se encontró menú con ID " + id);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("❌ Error al eliminar menú: " + e.getMessage());
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (em != null) em.close();
        }
    }
}
