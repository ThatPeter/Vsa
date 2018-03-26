/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvicenie2_03;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author illuSion
 */
public class Cvicenie2_03 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cvicenie2_03PU");
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Person> q = em.createQuery("SELECT p FROM Person p", Person.class);
        
        List<Person> persons = q.getResultList();
        
        for(Person p : persons)
        {
            System.out.println(p.toString());
        }
        
    }
    
}
