/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.fx.compte;

import bookstore.fx.livraison.IU_ClivraisonController;
import bookstore.fx.livraison.IU_MlivraisonController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pc
 */
public class WelcomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
     @FXML
    private void ModifierLivraison(ActionEvent event) {
        try {
            
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/bookstore/f/livraison/IU_Mlivraison.fxml"));
             
             Parent root1=(Parent) loader.load();
             IU_MlivraisonController rc = loader.getController();
             Stage stage=new Stage();
             stage.setScene(new Scene(root1));
             stage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
     @FXML
    private void NouvelleLivraison(ActionEvent event) {
        try {
             FXMLLoader loader=new FXMLLoader(getClass().getResource("/bookstore/f/livraison/IU_Nlivraison.fxml"));
             Parent root1=(Parent) loader.load();
             Stage stage=new Stage();
             stage.setScene(new Scene(root1));
             stage.show();
            } catch (IOException ex) {
            System.err.println(ex);
        }
    }
     @FXML
    private void ConsulterLivraison(ActionEvent event) {
        try {
              
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/bookstore/f/livraison/IU_Clivraison.fxml"));
             
             Parent root1=(Parent) loader.load();
             IU_ClivraisonController rc = loader.getController();
             Stage stage=new Stage();
             stage.setScene(new Scene(root1));
             stage.show();
             } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
}
