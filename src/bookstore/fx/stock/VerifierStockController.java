/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.fx.stock;

import bookstore.connexion.bookstoreConnexion;
import bookstore.exception.ReclamationExisteException;
import bookstore.model.Livre;
import bookstore.model.Reclamation;
import bookstore.service.ServiceAdmin;
import bookstore.service.ServiceBibliothécaire;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class VerifierStockController implements Initializable {
    @FXML
    private TableView<Livre> tableL;
    @FXML
    private TableColumn<Livre, String> tableID;
    @FXML
    private TableColumn<Livre, String> tableTitre;
    @FXML
    private TableColumn <Livre, String> tableAuteur;
    @FXML
    private TableColumn<Livre, String> tablePages;
    @FXML
    private TableColumn<Livre, String>tablePrix;
    @FXML
    private TableColumn<?, ?> tableGenre;
  ObservableList<Livre> livres = FXCollections.observableArrayList();
    @FXML
    private Text nbrL;
    @FXML
    private Text nbrL1;
    @FXML
    private Label username;
    @FXML
    private TextField chercher;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          InitColumn();
          livres=loadData(livres);
          //Observable in FilteredList
           FilteredList<Livre> filteredList= new FilteredList<Livre>(livres,b->true);
           //Set Filter predicate
           chercher.textProperty().addListener((livres,oldValue,newValue)->{
                filteredList.setPredicate((Livre livre) -> {
                    //si textField est vide afficher all
                    if (newValue==null||newValue.isEmpty())
                        return true;
                    //comparer username to textField
                    String lowerCase = newValue.toLowerCase();
                    
                    if(livre.getTitre().toLowerCase().indexOf(lowerCase)!= -1)
                        return true; //filter match usernameClient
                    else {
                        return false;}//doesn't match
                });
                  
            });
           //Filtered List in Sorted List
           SortedList<Livre> sortedList= new SortedList<>(filteredList);
           sortedList.comparatorProperty().bind(tableL.comparatorProperty());
           //ajouter items dans le tableau
           tableL.setItems(sortedList);
          
          
          ServiceBibliothécaire sb = new ServiceBibliothécaire();
           int nombre =sb.QuantiteLivres();
          nbrL.setText(String.valueOf(nombre));
           System.out.println(nbrL);
           
           addQuantiteToTableView();
    }  
    public void setUser(String user)
    {
        this.username.setText(user);
    }
    public void InitColumn(){
           tableID.setCellValueFactory(new PropertyValueFactory<>("Identifiant"));
           tableTitre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
           tableAuteur.setCellValueFactory(new PropertyValueFactory<>("Auteur"));
           tablePages.setCellValueFactory(new PropertyValueFactory<>("nbrPages"));
           tablePrix.setCellValueFactory(new PropertyValueFactory<>("prix"));    
           tableGenre.setCellValueFactory(new PropertyValueFactory<>("Genre")); 
    }
     public ObservableList<Livre> loadData(ObservableList<Livre> livres){
        try{
               bookstoreConnexion cnx=bookstoreConnexion.getIstance();
               String req ="select * from livre";
               Statement s= cnx.getConnection().createStatement();
               ResultSet rs = s.executeQuery(req);
               while(rs.next()){
                   Livre l = new Livre();
                   l.setAuteur(rs.getString("Auteur"));
                   l.setTitre(rs.getString("Titre"));
                   l.setNbrPages(rs.getInt("nbrPages"));
                   l.setGenre(rs.getString("Genre"));
                   l.setPrix(rs.getFloat("Prix"));
                   l.setIdentifiant(rs.getInt("Identifiant"));
                   
                
                   livres.add(l);
               }
           }
           catch(Exception e){
               
           }
        return livres;
    }
      public void addQuantiteToTableView(){
        
        TableColumn<Livre, Void> tableQuantite = new TableColumn("Quantite");
        Callback<TableColumn<Livre, Void>, TableCell<Livre, Void>> cellFactory = new Callback<TableColumn<Livre, Void>, TableCell<Livre, Void>>() {
            @Override
            public TableCell<Livre, Void> call(final TableColumn<Livre, Void> param) {
                final TableCell<Livre, Void> cell = new TableCell<Livre, Void>() {

                    String cssButton="-fx-cursor:  hand;\n" +
                    "    -fx-border-color: #ffa500;\n" +
                    "    -fx-border-width: 3px;\n" +
                    "    -fx-border-radius: 20px;\n" +
                    "    -fx-background-color:  #FFF;\n" +
                    "    -fx-text-fill:#ffa500;\n" +
                    "    -fx-font-veigth:bold ;"
                            + "";
                    private final Button btn = new Button("Quantite");
                    {
                        this.btn.setStyle(cssButton);
                    }

                    {
                        
                        
                        btn.setOnAction((ActionEvent event) -> {
                            String t = getTableView().getItems().get(getIndex()).getTitre();
                             String g = getTableView().getItems().get(getIndex()).getGenre();
                              String a = getTableView().getItems().get(getIndex()).getAuteur();
                               int i = getTableView().getItems().get(getIndex()).getIdentifiant();
                               Float p =getTableView().getItems().get(getIndex()).getPrix();
                               int nb=getTableView().getItems().get(getIndex()).getNbrPages();
                            Livre l = new Livre();
                            System.out.println("selected Reclamation : " + i);
                            l.setAuteur(a);
                            l.setGenre(g);
                            l.setIdentifiant(i);
                            l.setNbrPages(nb);
                            l.setPrix(p);
                            l.setTitre(t);
                            int quantite=quantiteButton(l);
                            nbrL1.setText(String.valueOf(quantite));
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
       tableQuantite.setCellFactory(cellFactory);
       tableL.getColumns().add(tableQuantite);
         
    }
      public int quantiteButton(Livre l){
         int quantity=0;
                 try {
            System.out.println("quantite test");
            ServiceBibliothécaire sb=new ServiceBibliothécaire();
           System.out.println("id :"+l.getIdentifiant());
            
            quantity=sb.QuantiteLivre(l);
        } catch (Exception ex) {
            System.err.println("erreur dans l'annulation:  "+ex);      
        }
                 return quantity;
}
  public void alertAlimentation(ActionEvent event)
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Alimentation");
         a.setHeaderText("Alimenter le bookstore");
          a.setContentText("Vous devez chercher un livre");
          a.showAndWait();
    }
    

    @FXML
    private void Alimenter(ActionEvent event) {
         if(chercher.getText().equals(""))
        {
            alertAlimentation(event);
        }
        else
        {
            try {
                String titre= chercher.getText();
                Livre l = new Livre();
                l.setTitre(titre);
                ServiceBibliothécaire sb = new ServiceBibliothécaire();
                sb.passerCommandeLivre(l);
                System.out.println("mail envoyé");
            } catch (Exception ex) {
                System.err.println("erreur dans Alimenter: "+ex);
                ex.printStackTrace();
            }
        
        }
    }
}
