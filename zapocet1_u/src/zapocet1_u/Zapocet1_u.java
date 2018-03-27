/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zapocet1_u;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author illuSion
 */
public class Zapocet1_u {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("zapocet1_uPU");
        EntityManager em = emf.createEntityManager();
        
        Journal j1 = new Journal();
        Journal j2 = new Journal();
        
        j1.setName("j1");
        j2.setName("j2");
        
        j1.setVolume(5);
        j2.setVolume(3);
        
        Article a1 = new Article();
        Article a2 = new Article();
        Article a3 = new Article();
        Article a4 = new Article();
        
        a1.setTitle("a1");
        a2.setTitle("a2");
        a3.setTitle("a3");
        a4.setTitle("a4");
        
        a1.setKeywords(new ArrayList());
        a1.getKeywords().add("1kw1");
        a1.getKeywords().add("1kw2");
        a1.getKeywords().add("1kw2");
        
        a2.setKeywords(new ArrayList());
        a2.getKeywords().add("2kw1");
        a2.getKeywords().add("2kw2");
        a2.getKeywords().add("2kw2");
        
        a3.setKeywords(new ArrayList());
        a3.getKeywords().add("3kw1");
        a3.getKeywords().add("3kw2");
        a3.getKeywords().add("3kw2");
        
        a4.setKeywords(new ArrayList());
        a4.getKeywords().add("4kw1");
        a4.getKeywords().add("4kw2");
        a4.getKeywords().add("4kw2");
        
        a1.setJournal(j1);
        a2.setJournal(j2);
        a3.setJournal(j2);
        a4.setJournal(j2);
        
        em.getTransaction().begin();
        
        em.persist(a1);
        em.persist(a2);
        em.persist(a3);
        em.persist(a4);
        
        em.persist(j1);
        em.persist(j2);
        
        em.getTransaction().commit();
        
        find("j1",5);
        System.out.println(haskey("1kw1", "j1", 5)); 
    }
    
    public static boolean haskey(String kw, String jn, int jv) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("zapocet1_uPU");
        EntityManager em = emf.createEntityManager();
        
        List<Journal> found = find(jn, jv);
        
        for (Journal j : found) {
            TypedQuery<Article> atq = em.createNamedQuery("Article.findArticleByJournalId", Article.class);
            atq.setParameter("journal", j);
            List<Article> articles = atq.getResultList();
          
            for (Article a : articles) {
                List<String> kwds = a.getKeywords();
                
                for (String s : kwds) {
                    System.out.println(s);
                }
                System.out.println("END");
                if (kwds.contains(kw)) {
                    return true;
                }
            }
            
        }
        
        return false;
    }
    
    public static List<Journal> find(String jn, int jv) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("zapocet1_uPU");
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Journal> jq = em.createQuery("SELECT j FROM Journal j WHERE j.name='" + jn + "' AND j.volume = " + jv +"", Journal.class);
        
        List<Journal> journals = jq.getResultList();
        
        for(Journal j : journals)
        {
            System.out.println(j.toString());
        }
        
        return journals;
    }
}
