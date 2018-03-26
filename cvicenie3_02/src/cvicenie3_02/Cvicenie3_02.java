/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvicenie3_02;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author illuSion
 */
public class Cvicenie3_02 {

    //metody entity managera
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cvicenie3_02PU");
        EntityManager em = emf.createEntityManager();
        
        //Opakované volanie persist - vytvori 2 zaznamy osoby v tabulke s rovnakym menom ale inym ID (autogenerovane)
        
      /*  Osoba o1 = new Osoba();
        Osoba o2 = new Osoba();
        
        o1.setMeno("Pateres");
        o2.setMeno("Pateres");
        
        em.getTransaction().begin();
        em.persist(o1);
        em.persist(o2);
        em.getTransaction().commit(); */
        
        //Opakované volanie persist - bez autogenerovaného klúča - vyhodi error lebo rovnake ID
    /*    Osoba o3 = new Osoba();
        Osoba o4 = new Osoba();
        
        o3.setMeno("Neviem");
        o3.setId(678L);
        o4.setMeno("Viem");
        o4.setId(678L);
        em.getTransaction().begin();
        em.persist(o3);
        em.persist(o4);
        em.getTransaction().commit(); */
        
        //persit a merge - persist nakesuje entitny objekt - merge aktualizuje udaje (prepise stary s novym, kdeze id je to iste)
       /* Osoba o1 = new Osoba();
        Osoba o2 = new Osoba();
        
        o1.setId(117L);
        o1.setMeno("Lucia");
        o2.setId(117L);
        o2.setMeno("Laura");
        em.getTransaction().begin();
        em.persist(o1);
        em.merge(o2);
        em.getTransaction().commit(); */
        
        //clear - vycisti nakesovanu pamat entity managera 
    /*    Osoba o1 = new Osoba();
        Osoba o2 = new Osoba();
        
        o1.setId(123L);
        o1.setMeno("Lucia");
        o2.setId(123L);
        o2.setMeno("Laura");
        em.getTransaction().begin();
        em.persist(o1);
        em.clear();
        em.persist(o2);
        em.getTransaction().commit(); */ 
    
        // detach - odstrani detachnuty objekt z kesky
 /*       Osoba o1 = new Osoba();
        Osoba o2 = new Osoba();
        
        o1.setId(188L);
        o1.setMeno("Budka");
        o2.setId(188L);
        o2.setMeno("Dubka");
        em.getTransaction().begin();
        em.persist(o1);
        em.persist(o2);
        em.detach(o1);
        em.getTransaction().commit(); */
 
        //Opakované volanie find
        
      /*  Osoba najdena1 = em.find(Osoba.class, 117L);
        Osoba najdena2 = em.find(Osoba.class, 117L);
        // ak je dany objekt nakesovany, nacitaju sa rovnake objekty
        if (najdena1 == najdena2) {
            System.out.println("rovnake objekty");
        } else {
            System.out.println("Rozne objekty");
        }
        //Opakované volanie find s detach
        Osoba najdena3 = em.find(Osoba.class, 117L);
        em.detach(najdena3); //ak odstranime nakesovany objekt a znova findneme, budu rozne
        Osoba najdena4 = em.find(Osoba.class, 117L);
        
        if (najdena3 == najdena4) {
            System.out.println("rovnake objekty");
        } else {
            System.out.println("Rozne objekty");
        }*/
    }
}
