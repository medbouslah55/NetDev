package gui.hssan.regime;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static gui.hssan.menu.AjouterMenuController.saveToFileImageNormal;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import models.Regime;
import services.ServiceRegime;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class ModifierRegimeController implements Initializable {

    private Regime regime;

    private String id;
    @FXML
    private Button btnModifier;
    @FXML
    private TextArea taDesc;
    @FXML
    private ImageView imgButton;
    @FXML
    private Button img;
    @FXML
    private TextField tfType;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public void initData(Regime regimmme) {
        regime = regimmme;

        id = (Integer.toString(regime.getId()));
        tfType.setText(regime.getType());
        taDesc.setText(regime.getDescription());
        imgButton.setImage(new Image("file:/C:/Users/trabe/Desktop/Integration/src/images/" + regime.getImage()));
    }

    @FXML
    private void Modifier(ActionEvent event) {
        if (controle_saisie()) {
            try{
                Image image1 = null;
                image1 = imgButton.getImage();
                String photo1 = null;
                photo1 = saveToFileImageNormal(image1);
                
                ServiceRegime sp = new ServiceRegime();
                
                int idd = Integer.parseInt(id);
                String type = tfType.getText();
                String desc = taDesc.getText();
                
                sp.modifier(new Regime(idd, type, desc, photo1));
                JOptionPane.showMessageDialog(null, "Régime Modifié!");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
    }
    
    private boolean controle_saisie() {
        if (taDesc.getText().isEmpty() || tfType.getText().isEmpty()){

            TrayNotification tray = null;
            tray = new TrayNotification("Données erronés", "Veuillez bien remplir tous les champs !", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(7));

            return false;
        } else {

            if (!Pattern.matches("^[A-Z a-z 0-9]+$", taDesc.getText())) {
                TrayNotification tray = null;
                tray = new TrayNotification("Données erronés", "Vérifiez la Description !", NotificationType.ERROR);
                tray.showAndDismiss(Duration.seconds(7));
                taDesc.requestFocus();
                taDesc.selectEnd();
                return false;
            }
            
            if (!Pattern.matches("^[A-Z a-z 0-9]+$", tfType.getText())) {
                TrayNotification tray = null;
                tray = new TrayNotification("Données erronés", "Vérifiez le Type !", NotificationType.ERROR);
                tray.showAndDismiss(Duration.seconds(7));
                taDesc.requestFocus();
                taDesc.selectEnd();
                return false;
            }
        }
        return true;
    }


    @FXML
    private void addImage(MouseEvent event) {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgButton.setImage(image);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
