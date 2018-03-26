/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvicenie4_01c;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author illuSion
 */
public class Cvicenie4_01c {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cvicenie4_01cPU");
        EntityManager em = emf.createEntityManager();
        
        Kniha k1 = new Kniha();
        Kniha k2 = new Kniha();
        Kniha k3 = new Kniha();
        
        Vydavatelstvo v1 = new Vydavatelstvo();
        Vydavatelstvo v2 = new Vydavatelstvo();
        
        k1.setNazov("Hobbit");
        k2.setNazov("Modzgo");
        k3.setNazov("Nfyibs");
        
        k1.setVydavatel(v1);
        k2.setVydavatel(v1);
        k3.setVydavatel(v2);
        
        v1.setAdresa("AdresaV1");
        v2.setAdresa("AdresaV2");
        
        em.getTransaction().begin();
        em.persist(k1);
        em.persist(k2);
        em.persist(k3);
        em.persist(v1);
        em.persist(v2);
        em.getTransaction().commit();
    }
    
}
