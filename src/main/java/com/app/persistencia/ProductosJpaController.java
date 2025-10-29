package com.app.persistencia;

import java.util.List;
import com.app.logica.Productos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ProductosJpaController {

    // Fábrica de conexiones (EntityManagerFactory)
    private EntityManagerFactory emf = null;

    // Constructor: se conecta a la unidad de persistencia definida en persistence.xml
    public ProductosJpaController() {
        emf = Persistence.createEntityManagerFactory("clotyStore");
    }

    // Obtiene un EntityManager (el objeto que maneja las operaciones con la BD)
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // ===========================
    // CRUD - PRODUCTOS
    // ===========================

    // Crear un producto
    public void create(Productos producto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(producto);
            em.getTransaction().commit();
            System.out.println("✅ Producto creado correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al crear producto: " + e.getMessage());
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em != null) em.close();
        }
    }

    // Buscar un producto por ID
    public Productos findProductos(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Productos.class, id);
        } finally {
            em.close();
        }
    }

    // Buscar todos los productos
    public List<Productos> findProductosEntities() {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT p FROM Productos p";
            return em.createQuery(jpql, Productos.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Editar producto existente
    public void edit(Productos producto) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(producto);
            em.getTransaction().commit();
            System.out.println("✅ Producto editado correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al editar producto: " + e.getMessage());
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (em != null) em.close();
        }
    }

    // Eliminar un producto
    public void destroy(int id) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productos producto = em.find(Productos.class, id);

            if (producto != null) {
                em.remove(producto);
                System.out.println("✅ Producto eliminado correctamente.");
            } else {
                System.out.println("⚠️ No se encontró producto con ID " + id);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("❌ Error al eliminar producto: " + e.getMessage());
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (em != null) em.close();
        }
    }
}
