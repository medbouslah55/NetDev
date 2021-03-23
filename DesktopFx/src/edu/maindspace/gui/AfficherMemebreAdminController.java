/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.maindspace.gui;

import edu.maindspace.entities.Membre;
import edu.maindspace.services.MembreServices;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mohamedbouslah
 */
public class AfficherMemebreAdminController implements Initializable {

    @FXML
    private AnchorPane AP_ajouter_coach;
    @FXML
    private Button menu_bt_ajouter_coach;
    @FXML
    private Button menu_bt_afficher_coach;
    @FXML
    private Button menu_bt_afficher_membre;
    @FXML
    private TableView<Membre> tv_afficher_memebre;
    @FXML
    private TableColumn<Membre, Integer> tc_cin_memebre;
    @FXML
    private TableColumn<Membre, String> tc_nom_memebre;
    @FXML
    private TableColumn<Membre, String> tc_prenom_memebre;
    @FXML
    private TableColumn<Membre, String> tc_date_memebre;
    @FXML
    private TableColumn<Membre, String> tc_email_memebre;
    @FXML
    private TableColumn<Membre, String> tc_telephone_memebre;
    @FXML
    private ComboBox<Integer> cb_cin_membre;
    @FXML
    private TextField tf_nom_membre;
    @FXML
    private TextField tf_prenom_membre;
    @FXML
    private TextField tf_email_membre;
    @FXML
    private Button bt_modifier_membre;
    @FXML
    private ComboBox<Integer> cb_cin_membre_supp;
    @FXML
    private Button bt_supp_membre;
    @FXML
    private TextField tf_telephone_membre;
    @FXML
    private TextField tf_password_membre;
    @FXML
    private TextField tf_taille_membre;
    @FXML
    private TextField tf_poids_membre;
    @FXML
    private Button bt_trie_Membre;
    @FXML
    private Button menu_bt_Stat_membre;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        afficher_membre();
        cin_cb_modifier_supprimer();
    }    

    @FXML
    private void menu_ajouter_coach(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AjouterCoachAdmin.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void menu_afficher_coach(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AfficherCoachAdmin.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void menu_afficher_membre(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AfficherMemebreAdmin.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void menu_stat_membre(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("StatMembre.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    MembreServices ms=new MembreServices();
    
    public void afficher_membre(){
        
        ObservableList<Membre> list =  ms.afficher();
        tc_cin_memebre.setCellValueFactory(new PropertyValueFactory<Membre, Integer>("cin"));
        tc_nom_memebre.setCellValueFactory(new PropertyValueFactory<Membre, String>("nom"));
        tc_prenom_memebre.setCellValueFactory(new PropertyValueFactory<Membre, String>("prenom"));
        tc_date_memebre.setCellValueFactory(new PropertyValueFactory<Membre, String>("datee"));
        tc_email_memebre.setCellValueFactory(new PropertyValueFactory<Membre, String>("email"));
        tc_telephone_memebre.setCellValueFactory(new PropertyValueFactory<Membre, String>("telephone"));
        
        tv_afficher_memebre.setItems(list);
    }
    
    ObservableList options;
     String dbUsername = "root";
        String dbPassword = "root";
        String dbURL = "jdbc:mysql://localhost:8889/mindspace1";
        Connection cnx ;
    
    public void cin_cb_modifier_supprimer(){
       
        try {
            cnx = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            options = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = cnx.createStatement().executeQuery("SELECT cin FROM membre");
            while (rs.next()) {
                //get string from db,whichever way 
                options.add(new Membre(rs.getInt("cin")).getCin());
            }

        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }

        cb_cin_membre.setItems(null);
        cb_cin_membre.setItems(options);
        
        cb_cin_membre_supp.setItems(null);
        cb_cin_membre_supp.setItems(options);
        
    }    
    
    @FXML
    private void cb_cin_membre(ActionEvent event) throws SQLException {
        cnx = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        Statement st = cnx.createStatement();
        
        String req = "SELECT nom,prenom,password,email,telephone,taille,poids FROM membre WHERE cin='"+cb_cin_membre.getSelectionModel().getSelectedItem()+"'";
        ResultSet rs = st.executeQuery(req);
        
        while (rs.next()) {
                String a = rs.getString("nom");
                String b = rs.getString("prenom");
                String c = rs.getString("password");
                String d = rs.getString("email");
                String e = rs.getString("telephone");
                String f = rs.getString("taille");
                String g = rs.getString("poids");
               
                tf_nom_membre.setText(a);
                tf_prenom_membre.setText(b);
                tf_password_membre.setText(c);
                tf_email_membre.setText(d);              
                tf_telephone_membre.setText(e);
                tf_taille_membre.setText(f);
                tf_poids_membre.setText(g);
                
                
               
            }
        
    }
    
    @FXML
    private void bt_modifier_membre(ActionEvent event) {
        Membre t = new Membre();
        
        t.setCin(cb_cin_membre.getSelectionModel().getSelectedItem());
        t.setNom(tf_nom_membre.getText());
        t.setPrenom(tf_prenom_membre.getText());
        t.setPassword(tf_password_membre.getText());
        t.setEmail(tf_email_membre.getText());
//        String teleph = tf_telephone_membre.getText();
//        float si2 = Integer.parseInt(teleph);
        //t.setTelephne(tf_telephone_membre.getText());
        //t.setTaille(tf_taille_membre.get());
       // t.setPoids(tf_poids_membre.getSelectionModel().getSelectedItem());
        String tf_taille = tf_taille_membre.getText();
        t.setTaille(Integer.parseInt(tf_taille));
        String tf_poids = tf_poids_membre.getText();
        t.setPoids(Integer.parseInt(tf_poids));
        String telephone = tf_telephone_membre.getText();
        t.setTelephone(Integer.parseInt(telephone));
        ms.modifier(t);
        afficher_membre();
    }

    @FXML
    private void bt_supp_membre(ActionEvent event) {
        Membre t = new Membre();
        t.setCin(cb_cin_membre_supp.getSelectionModel().getSelectedItem());
        
        ms.supprimer(t);
        cin_cb_modifier_supprimer();
        afficher_membre();
    }

    @FXML
    private void bt_trie_Membre(ActionEvent event) {
        ObservableList<Membre> list =  ms.TrieParNom();
        tc_cin_memebre.setCellValueFactory(new PropertyValueFactory<Membre, Integer>("cin"));
        tc_nom_memebre.setCellValueFactory(new PropertyValueFactory<Membre, String>("nom"));
        tc_prenom_memebre.setCellValueFactory(new PropertyValueFactory<Membre, String>("prenom"));
        tc_date_memebre.setCellValueFactory(new PropertyValueFactory<Membre, String>("datee"));
        tc_email_memebre.setCellValueFactory(new PropertyValueFactory<Membre, String>("email"));
        tc_telephone_memebre.setCellValueFactory(new PropertyValueFactory<Membre, String>("telephone"));
        
        tv_afficher_memebre.setItems(list);
    }

    
    
}
