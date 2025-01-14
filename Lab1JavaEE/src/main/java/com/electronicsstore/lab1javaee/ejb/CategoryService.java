package com.electronicsstore.lab1javaee.ejb;

import com.electronicsstore.lab1javaee.entities.Category;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CategoryService {
    @PersistenceContext(unitName = "default")
    private EntityManager em;

    public void save(Category category) {
        em.persist(category);
    }

    public void update(Category category) {
        em.merge(category);
    }

    public void delete(int id) {
        Category category = em.find(Category.class, id);
        if (category != null) {
            em.remove(category);
        }
    }

    public Category findById(int id) {
        return em.find(Category.class, id);
    }

    public List<Category> findAll() {
        return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
    }
}