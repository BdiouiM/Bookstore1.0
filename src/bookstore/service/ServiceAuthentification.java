/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.service;

import bookstore.model.BTC;
import bookstore.Interface.AuthentificationInterface;
import bookstore.connexion.Myconnexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ServiceAuthentification implements AuthentificationInterface{
     Myconnexion cnx;
    
    public ServiceAuthentification()
    {
        cnx=Myconnexion.getInstance();
    }

    @Override
    public boolean clientAuthentification(String username, String password) {
       
        /* try {
            String req1= "select * from client ";
            Statement s= cnx.getConnection().createStatement();
            ResultSet rs = s.executeQuery(req1);
            while(rs.next())
            {
                Client client = new Client();
                client.setUsername(rs.getString("UsernameClient"));
                client.setPassword(rs.getString("Password"));
                if(client.getPassword().equals(password)&&client.getUsername().equals(username))
                    return true;
                
            }
        } catch (SQLException ex) {
            System.err.println("erreur authentification client : "+ex);
        }*/
        return false;
    }

    @Override
    public boolean adminAuthentification(String username, String password) {
        /* try {
            String req1= "select * from admin ";
            Statement s= cnx.getConnection().createStatement();
            ResultSet rs = s.executeQuery(req1);
            while(rs.next())
            {
                Administrateur admin = new Administrateur();
                admin.setUsername(rs.getString("UsernameAdmin"));
                admin.setPassword(rs.getString("Password"));
                if(admin.getPassword().equals(password)&&admin.getUsername().equals(username))
                    return true;
                
            }
        } catch (SQLException ex) {
            System.err.println("erreur authentification admin : "+ex);
        }*/
        return false;
      
    }

    @Override
    public boolean bibliothecaireAuthentification(String username, String password) {
       /*  try {
            String req1= "select * from bibliothecaire ";
            Statement s= cnx.getConnection().createStatement();
            ResultSet rs = s.executeQuery(req1);
            while(rs.next())
            {
                Bibliothécaire biblio = new Bibliothécaire();
                biblio.setUsername(rs.getString("Username"));
                biblio.setPassword(rs.getString("Password"));
                if(biblio.getPassword().equals(password)&&biblio.getUsername().equals(username))
                    return true;
                
            }
        } catch (SQLException ex) {
            System.err.println("erreur authentification bibliothécaire : "+ex);
        }*/
        return false;
    }
    @Override
    public boolean btcauthentification(String username, String password) {
         try {
            String req1= "select * from bussinessToCustomer ";
            Statement s= cnx.getConnection().createStatement();
            ResultSet rs = s.executeQuery(req1);
            while(rs.next())
            {
                BTC biblio = new BTC();
                biblio.setUsername(rs.getString("Username"));
                biblio.setPassword(rs.getString("Password"));
                if(biblio.getPassword().equals(password)&&biblio.getUsername().equals(username))
                    return true;
                
            }
        } catch (SQLException ex) {
            System.err.println("erreur authentification btc : "+ex);
        }
        return false;
    }
}