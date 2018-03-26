/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvicenie4_01d;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author illuSion
 */
public class Cvicenie4_01d {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cvicenie4_01dPU");
        EntityManager em = emf.createEntityManager();
        
        Kniha k1 = new Kniha();
        Kniha k2 = new Kniha();
        Kniha k3 = new Kniha();
        
        Vydavatelstvo v1 = new Vydavatelstvo();
        Vydavatelstvo v2 = new Vydavatelstvo();
        
        k1.setNazov("Hobbit");
        k2.setNazov("Modzgo");
        k3.setNazov("Nfyibs");
        
        v1.setAdresa("Adresa1");
        v2.setAdresa("Adresa2");
        
        v1.setPublikacia(new ArrayList());
        v2.setPublikacia(new ArrayList());
        
        v1.getPublikacia().add(k3);
        v2.getPublikacia().add(k1);
        v2.getPublikacia().add(k2);
        
        em.getTransaction().begin();
        em.persist(k1);
        em.persist(k2);
        em.persist(k3);
        em.persist(v1);
        em.persist(v2);
        em.getTransaction().commit();
    }
    
}
