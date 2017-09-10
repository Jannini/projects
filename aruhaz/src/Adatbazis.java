import com.sun.java.util.jar.pack.*;
import javafx.scene.control.CheckBox;
import com.mysql.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Jani on 2017.04.22..
 */
public class Adatbazis {
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;

    String url = "jdbc:mysql://localhost/termek";
    String user = "root";
    String password = "Jedike123";
    Product [] products=new Product[100];
    CheckBox[] checkBoxes=new CheckBox[100];
    int rowLength=0;

void SetBazis(String vezNev,String kerNev,String azonosito,String jelsz)throws Exception{
    try {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, user, password);
        st = con.createStatement();
        st.executeUpdate("INSERT INTO users (Vezeteknev, Keresztnev, Azonosito,Jelszo) "
                +"VALUES ('"+vezNev+"','" +kerNev+"','" +azonosito+"','" +jelsz+"')");

    }
    catch(Exception ex) {
        System.out.println(ex.toString());
    }

    }
    ArrayList<String> GetAzonosito()throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, user, password);
        st = con.createStatement();
        rs = st.executeQuery("SELECT * FROM users");
        ArrayList<String> azonositok=new ArrayList<>();


        while(rs.next()) {
            azonositok.add(rs.getString(3));
        }
        return azonositok;

    }
    int Azonositas(String felh,String jelsz)throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, user, password);
        st = con.createStatement();
        rs = st.executeQuery("SELECT * FROM users");

        while(rs.next()) {
            if(rs.getString(3).equals(felh)&& rs.getString(4).equals(jelsz)){
                return 1;

            }

        }
        return 0;
    }
  void GetBazis()throws Exception{
    try {

        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, user, password);
        st = con.createStatement();
        rs = st.executeQuery("SELECT * FROM termek");

        for(int i=0;rs.next();i++){

            String n=rs.getString(1);
            Double ar=rs.getDouble(2);
            String van=rs.getString(3);
            products[i]=new Product(n,ar,van,checkBoxes[i]=new CheckBox());
            rowLength=rs.getRow();


        }
    } catch (SQLException ex) {
        System.out.println(ex.toString());
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }

        }catch (SQLException ex) {
            System.out.println(ex.toString());}

    }
}
}
