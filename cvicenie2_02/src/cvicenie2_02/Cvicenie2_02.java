/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvicenie2_02;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author illuSion
 */
public class Cvicenie2_02 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cvicenie2_02PU");
        EntityManager em = emf.createEntityManager();
        
        Osoba o = (Osoba) em.find(Osoba.class, 1L);
        if(o != null) {
            System.out.println(o.toString());
        }
    }
    
}
