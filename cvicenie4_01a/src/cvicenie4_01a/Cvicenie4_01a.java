/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvicenie4_01a;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author illuSion
 */
public class Cvicenie4_01a {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cvicenie4_01aPU");
        EntityManager em = emf.createEntityManager();
        
        Kniha k1 = new Kniha();
        Kniha k2 = new Kniha();
        
        k1.setNazov("Hobbit");
  
        k1.setAutor(new ArrayList());
        k1.getAutor().add("Peter Malo");
        k1.getAutor().add("Meter Palo");
        
        
        k2.setNazov("Hobler");
        
        k2.setAutor(new ArrayList());
        k2.getAutor().add("Malo Halo");
        
        em.getTransaction().begin();
        em.persist(k1);
        em.persist(k2);
        em.getTransaction().commit();
    }
}
