package ua.com.rozdobudko.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ua.com.rozdobudko.Objects.Word;

/**
 * @author Oleksii Rozdobudko rozdobudko.com.ua
 * @version 1.1.1
 * @since 2017-08-26
 */
public class AddController {

    @FXML
    private TextField txtCyr;

    private Word word;

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    // кнопка Ок
    public void actionOk(ActionEvent actionEvent) {
        boolean bool = true;
        while (bool) {
            word.setCyr(txtCyr.getText());
            word.setLat(word.cyr2lat(word.getCyr().trim()));
            bool = false;
            actionClose(actionEvent);
        }
    }

    protected void setWord(Word word) {
        if (word == null) {
            return;
        }
        this.word = word;
        txtCyr.setText(word.getCyr());
        word.setLat(word.cyr2lat(word.getCyr().trim()));
    }

    protected Word getWord() {
        return word;
    }


}
