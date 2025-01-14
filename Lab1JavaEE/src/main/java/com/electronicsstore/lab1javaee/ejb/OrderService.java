package com.electronicsstore.lab1javaee.ejb;

import com.electronicsstore.lab1javaee.entities.Order;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class OrderService {
    @PersistenceContext(unitName = "default")
    private EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public void update(Order order) {
        em.merge(order);
    }

    public void delete(int id) {
        Order order = em.find(Order.class, id);
        if (order != null) {
            em.remove(order);
        }
    }

    public Order findById(int id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll() {
        return em.createQuery("SELECT o FROM Order o", Order.class).getResultList();
    }
}