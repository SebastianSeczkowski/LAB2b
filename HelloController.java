package pl.lublin.wsei.java.cwiczenia;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.applet.AppletContext;
import java.io.File;

public class HelloController {
    public Label lbFile;
    public ListView lstInfografiki;
    public TextField txtAdresStrony;
    public Button btnPokazInfografike;
    public Button btnPrzejdzDoStrony;
    ObservableList<String> tytuly = FXCollections.observableArrayList();
    GusInfoGraphicList igList;
    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter xmlFilter = new FileChooser.ExtensionFilter("Pliki XML (*.xml", "*.xml");

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    public void initialize() {
        fileChooser.getExtensionFilters().add(xmlFilter);

        lstInfografiki.getSelectionModel().selectedIndexProperty().addListener(

                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                        int index = t1.intValue();
                        ImageIcon imgMiniaturka;
                        if (index != -1) {
                            selfInfografika = igList.infografiki.get(index);
                            txtAdresStrony.setText(igList.infografiki.get(index).adresStrony);
                            Image image = new Image(igList.infografiki.get(index).adresMiniaturki);
                            imgMiniaturka.setImage(image);
                        }
                        else {
                            txtAdresStrony.setText("");
                            imgMiniaturka.setImage(null);
                            selfInfografika = null;
                        }
                    }
                }
        );

    }

    public void btnOpenFileAction(ActionEvent actionEvent) {
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            igList = new GusInfoGraphicList(file.getAbsolutePath());
            lbFile.setText(file.getAbsolutePath());
            for (Infografika ig: igList.infografiki) tytuly.add(ig.tytul);
            lstInfografiki.setItems(tytuly);
        }
        else {
            lbFile.setText("Prosz?? wczyta?? plik ...");
        }
    }

    public void btnZaladujStrone(ActionEvent actionEvent) {
        boolean selfInfografika;
        AppletContext hostServices;
        if (selfInfografika != null)
            hostServices.showDocument(selfInfografika.AdresStrony);
    }
}