package database;

import javax.persistence.*;
import models.Student;
import java.util.*;

public class DBOperations {
    private EntityManagerFactory factory;
    private EntityManager em;

    public DBOperations() {
        try {
            Map<String, String> props = new HashMap<>();
            props.put("javax.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
            props.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/university_db?useSSL=false&serverTimezone=UTC");
            props.put("javax.persistence.jdbc.user", "root");
            props.put("javax.persistence.jdbc.password", "");

            org.eclipse.persistence.jpa.PersistenceProvider provider = new org.eclipse.persistence.jpa.PersistenceProvider();
            this.factory = provider.createEntityManagerFactory("StudentPU", props);

            if (this.factory != null) {
                this.em = this.factory.createEntityManager();
                System.out.println("Connection Established Successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addStudent(Student s) {
        if (em == null) return;
        try {
            em.getTransaction().begin();
            em.persist(s);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        }
    }

    public void updateStudent(Student s) {
        if (em == null) return;
        try {
            em.getTransaction().begin();
            em.merge(s);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        }
    }

    public void deleteStudent(int id) {
        if (em == null) return;
        try {
            em.getTransaction().begin();
            Student s = em.find(Student.class, id);
            if (s != null) em.remove(s);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        }
    }
}