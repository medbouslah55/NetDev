/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Mahdi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.Evenement;
import services.ServicePublication;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class AfficherEvenementMembreController implements Initializable {

    @FXML
    private AnchorPane AP_ajouter_coach;
    @FXML
    private AnchorPane tfDate_Pub;
    @FXML
    private TableView<Evenement> tvevenement;
    @FXML
    private TableColumn<?, ?> tvDate_Pub;
    @FXML
    private TableColumn<?, ?> tvDate_Even;
    @FXML
    private TableColumn<?, ?> tvImage;
    @FXML
    private TableColumn<?, ?> tvSujet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherEven();
    }    
    
    public void afficherEven() {

        ServicePublication sp = new ServicePublication();
        ObservableList<Evenement> list = sp.afficher();

        // tvId.setCellValueFactory(new PropertyValueFactory<>("id_pub"));
        tvDate_Pub.setCellValueFactory(new PropertyValueFactory<>("date_pub"));
        tvDate_Even.setCellValueFactory(new PropertyValueFactory<>("date_even"));
        tvImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        tvSujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));

        tvevenement.setItems(list);

    }
    
}
