/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvicenie4_01b;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author illuSion
 */
public class Cvicenie4_01b {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cvicenie4_01bPU");
        EntityManager em = emf.createEntityManager();
        
        Kniha k1 = new Kniha();
        Kniha k2 = new Kniha();
        Kniha k3 = new Kniha();
        
        Osoba o1 = new Osoba();
        Osoba o2 = new Osoba();
        Osoba o3 = new Osoba();
        
        o1.setMeno("Pater");
        o2.setMeno("Peter");
        o3.setMeno("Poter");
        
        k1.setNazov("Hobbit");
        k2.setNazov("Modzgo");
        k3.setNazov("Nfyibs");
        
        k1.setAutor(new ArrayList());
        k2.setAutor(new ArrayList());
        k3.setAutor(new ArrayList());
        
        k1.getAutor().add(o1);
        k2.getAutor().add(o1);
        k2.getAutor().add(o2);
        k3.getAutor().add(o3);
        
        em.getTransaction().begin();
        em.persist(k1);
        em.persist(k2);
        em.persist(k3);
        em.persist(o1);
        em.persist(o2);
        em.persist(o3);
        em.getTransaction().commit();
    }
}
