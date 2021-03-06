/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.fx.compte;

import bookstore.service.ServicePersonnel;
import bookstore.model.Personnel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author oumaima
 */
public class DesactiverClientController implements Initializable {

    @FXML
    private TextField tf1;
    @FXML
    private TextField tf2;
    @FXML
    private TextField tf3;
    @FXML
    private TextField tf5;
    @FXML
    private TextField tf6;
    @FXML
    private TextField tf7;
    @FXML
    private TextField tf8;
    @FXML
    private TextField tf9;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void desactiver(ActionEvent event) {
         ServicePersonnel serv= new ServicePersonnel(); 
     Personnel personnel= new Personnel();
     
       personnel.setCIN(Integer.parseInt(tf1.getText()));
     personnel.setNom(tf2.getText());
    personnel.setPrenom(tf3.getText());
       personnel.setAdresse(tf5.getText());
     personnel.setEmail(tf6.getText());
       personnel.setUsername(tf7.getText());
         personnel.setPassword(tf8.getText());
 personnel.setType(tf9.getText());
        serv.supprimerPersonnel(personnel);
          Notifications notificationBuilder;
        notificationBuilder = Notifications.create()
                .title("Welcome")
                .text("Le Client "+ personnel.getUsername()+ " a été désactivé avec succès ")
                .graphic(null)
                 .onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                System.out.println("");
                            }
                        });
        notificationBuilder.showConfirm();
    }
    
}
