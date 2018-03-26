/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvicenie3_01;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author illuSion
 */
public class Cvicenie3_01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cvicenie3_01PU");
        EntityManager em = emf.createEntityManager();
        
        
        TypedQuery<Osoba> osoby = em.createNamedQuery("Osoba.findAll", Osoba.class);
        
        for (Osoba o : osoby.getResultList()) {
            System.out.println(o.toString());
        }
        
        TypedQuery<Osoba> osobyPodlaMena = em.createNamedQuery("Osoba.findByMeno", Osoba.class);
        osobyPodlaMena.setParameter("meno", "Peter");
        for (Osoba o : osobyPodlaMena.getResultList()) {
            System.out.println(o.toString());
        }
        
        TypedQuery<Osoba> q = em.createNamedQuery("Osoba.findByNotSetVaha", Osoba.class);
        
        List<Osoba> osobyBezVahy = q.getResultList();
        
        em.getTransaction().begin();
        
        for (Osoba o : osobyBezVahy) {
            o.setVaha(80.0);
            em.persist(o);
        }
        
        em.getTransaction().commit(); 
    }
}
