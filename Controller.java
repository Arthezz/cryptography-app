package CryptographyFX;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    Methods methods = new Methods();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        methods.generateKey();
    }

    

    @FXML
    private FontAwesomeIcon btn_close;

    @FXML
    private Button btn_encrypt, btn_decrypt;

    @FXML
    private TextField textToEncrypt, textEncrypted, textToDecrypt, textDecrypted;


//Button events
    @FXML
    private void handleButtonAction(MouseEvent event) {

        if(event.getSource() == btn_close){
            System.exit(0);
        }
        if(event.getSource() == btn_encrypt){
            textEncrypted.setText(methods.encrypting(textToEncrypt.getText()));
        }
        if(event.getSource() == btn_decrypt){
            textDecrypted.setText(methods.decrypting(textToDecrypt.getText()));
        }

    }
    @FXML
    private void handleKeyAction(KeyEvent event){

//Encrytping

        if(!event.isShiftDown() && !event.isAltDown()) {
            if (event.getSource() == textToEncrypt && !methods.allowedKeysEn1.contains(event.getText()) && event.getCode() != KeyCode.ENTER && event.getCode() != KeyCode.TAB   ) {
                methods.warningEn();
            }
        }
        if (event.isShiftDown() && event.getSource() == textToEncrypt && !methods.allowedKeysEn2.contains(event.getText())) {
                methods.warningEn();
        }
        if(event.isAltDown() && event.getSource() == textToEncrypt){
            methods.warningEn();
        }

//Decrytping

        if(!event.isShiftDown() && !event.isAltDown() && event.getCode() != KeyCode.ENTER && event.getCode() != KeyCode.TAB) {
            if (event.getSource() == textToDecrypt && !methods.allowedKeysDe1.contains(event.getText())) {
                methods.warningDe();
            }
        }
        if (event.isShiftDown() && event.getSource() == textToDecrypt && !methods.allowedKeysDe2.contains(event.getText())) {
            methods.warningDe();
        }
        if(event.isAltDown() && event.getSource() == textToDecrypt){
            methods.warningDe();
        }

//Hotkeys

        if(event.getCode() == KeyCode.ENTER && event.getSource() == textToEncrypt){
            textEncrypted.setText(methods.encrypting(textToEncrypt.getText()));
        }

        if(event.getCode() == KeyCode.ENTER && event.getSource() == textToDecrypt){
            textDecrypted.setText(methods.decrypting(textToDecrypt.getText()));
        }

    }
}

