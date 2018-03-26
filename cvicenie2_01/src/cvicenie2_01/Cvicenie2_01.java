/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvicenie2_01;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Date;
/**
 *
 * @author illuSion
 */
public class Cvicenie2_01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cvicenie2_01PU");
        EntityManager em = emf.createEntityManager();
        
        Osoba o1 = new Osoba();
        Osoba o2 = new Osoba();
        Osoba o3 = new Osoba();
        
        o1.setMeno("Peter");
        o1.setNarodena(Date.valueOf("1997-11-24"));
        o1.setVaha((float)80.5);
        o1.setVyska((float)175.5);
        
        o2.setMeno("Pavol");
        o2.setVyska((float)158.0);
        
        em.getTransaction().begin();
        em.persist(o1);
        em.persist(o2);
        em.persist(o3);
        em.getTransaction().commit();
    }
}
