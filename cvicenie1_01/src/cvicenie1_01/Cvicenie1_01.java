/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvicenie1_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author illuSion
 */
public class Cvicenie1_01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/vsadb", "app", "app");
        Statement st = con.createStatement();
       // pridajKniha("NejakyNazov", "NejakyAutor", 69.0);
        ResultSet rs = st.executeQuery("SELECT * FROM KNIHA");
        zlava("Nazov2");
        while(rs.next())
        {
            System.out.println(rs.getString(2));
        }
        
        System.out.println("Cena knihy nazov2: " + cenaKnihy("Nazov2"));
    }
    
/*Vytvoriť nový java-app projekt a v ňom implementovať a otestovať nasledujúce metódy:
double cenaKnihy(String meno), ktorá nájde v DB knihu so zadaným menom a vráti jej cenu. 
Ak neexistuje taká kniha vráti -1 a vypíše spávu "Knihu nemáme"
    
boolean pridajKnihu(String meno, double cena), ktorá pridá do DB knihu s daným menom a cenou.
Ak kniha s daným menom v DB už existuje, vráti false.

void zlava(String meno) ktorá nájde v DB knihu so zadaným menom a zníži jej cenu o o 20% (v databáze).
Ak neexistuje taká kniha neurobí nič.

Pozn.Pre vykonanie SQL príkazov INSERT a UPDATE treba namiesto executeQuery volať metódu executeUpdate.

*/
    public static double cenaKnihy(String meno) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/vsadb", "app", "app");
        Statement st = con.createStatement();
        
        ResultSet rs = st.executeQuery("SELECT CENA FROM KNIHA WHERE NAZOV='" + meno + "'" );
        
        if(!rs.next())
        {
            System.out.print("Knihu nemame");
            return -1;
        }
        
        return rs.getDouble(1);
    }
    
    public static boolean pridajKniha(String meno, String autor, double cena) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/vsadb", "app", "app");
        Statement st = con.createStatement();
        
        ResultSet rs = st.executeQuery("SELECT * FROM KNIHA WHERE NAZOV='" + meno + "'");
        
        if(rs.next())
        {
            return false;
        }
        
        st.executeUpdate("INSERT INTO KNIHA VALUES ('" + meno + "','"+autor+"',"+String.valueOf(cena)+")");
        
        return true;
    }
    
    public static void zlava(String meno) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/vsadb", "app", "app");
        Statement st = con.createStatement();
        
        ResultSet rs = st.executeQuery("SELECT * FROM KNIHA WHERE NAZOV='" + meno + "'");
        
        if(!rs.next())
        {
            return;
        }
        
        st.executeUpdate("UPDATE KNIHA SET CENA=CENA*0.8 WHERE NAZOV='" + meno + "'");
    }
    
}
