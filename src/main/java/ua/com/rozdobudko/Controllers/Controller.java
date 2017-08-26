package ua.com.rozdobudko.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ua.com.rozdobudko.Interfaces.impls.CollectionWordBook;
import ua.com.rozdobudko.Objects.Word;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Oleksii Rozdobudko rozdobudko.com.ua
 * @version 1.1.1
 * @since 2017-08-26
 */
public class Controller {
    // коллекция слов
    private CollectionWordBook cwb = new CollectionWordBook();

    public String fileName;   //имя файла из окна FileChooser

    //выносим загрузчики для доступа из методов
    private Stage mainStage;
    private Parent fxmlEdit; //для окна редактирования
    private Parent fxmlEditAdd; //для окна добавления
    private FXMLLoader fxmlLoader = new FXMLLoader(); //для окна редактирования
    private FXMLLoader fxmlLoaderAdd = new FXMLLoader(); //для окна добавления
    private EditController editController; //для окна редактирования
    private Stage editStage;  //для окна редактирования
    private Stage editStageAdd; //для окна добавления
    private AddController addController; //для окна добавления

    @FXML
    private TableView tableFirst;

    @FXML
    private TableColumn<Word, String> columnCyr;

    @FXML
    private TableColumn<Word, String> columnTrans;

    @FXML
    private Button BtnLoad;

    @FXML
    private Button BtnToCall;

    @FXML
    private Button BtnDeleteAll;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnAdd;

    public TableView getTable() {
        return tableFirst;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    // загружаем данные в TableView
    @FXML
    private void initialize() {
        columnCyr.setCellValueFactory(new PropertyValueFactory<>("cyr"));
        columnTrans.setCellValueFactory(new PropertyValueFactory<>("lat"));
//Ctrl+C begin
        tableFirst.getSelectionModel().setCellSelectionEnabled(true);
        tableFirst.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        MenuItem item = new MenuItem("Копировать");
        item.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                final ClipboardContent content = new ClipboardContent();
                Word copyWord = (Word) tableFirst.getSelectionModel().getSelectedItem();
                content.putString(copyWord.getLat());
                Clipboard.getSystemClipboard().setContent(content);
            }
        });
        ContextMenu menu = new ContextMenu();
        menu.getItems().add(item);
        tableFirst.setContextMenu(menu);
//Ctrl+c end

        initListeners();
        fillData();
        initLoader();
        initLoaderAdd();
    }


    private void fillData() {
        tableFirst.setItems(cwb.getWordsList());
    }

    // открытие окна для редактирования по двойному клику на записи в TableView
    private void initListeners() {
        tableFirst.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    editController.setWord((Word) tableFirst.getSelectionModel().getSelectedItem());
                    showDialog();
                }
            }
        });
    }

    private void initLoader() { //для окна редактирования
        try {
            fxmlLoader.setLocation(getClass().getResource("/fxml/edit.fxml"));
            fxmlEdit = fxmlLoader.load();
            editController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initLoaderAdd() { //для окна добавления
        try {
            fxmlLoaderAdd.setLocation(getClass().getResource("/fxml/add.fxml"));
            fxmlEditAdd = fxmlLoaderAdd.load();
            addController = fxmlLoaderAdd.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Загружаем внешний файл нужно будет засингелтонить
    public void loadFile(ActionEvent actionEvent) {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Открыть файл с кириллицей...");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("MS Excel files", "*.xlsx"));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try {
                cwb.readFromExcel(file.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void editPerson(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        //если не кнопка - закрываем метод... для примера
        if (!(source instanceof Button)) {
            return;
        }
        editController.setWord((Word) tableFirst.getSelectionModel().getSelectedItem());
        showDialog();
    }

    public void addWord(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        //если не кнопка - закрываем метод... для примера
        if (!(source instanceof Button)) {
            return;
        }
        addController.setWord(new Word());
        showDialogAdd();
        cwb.add(addController.getWord());
    }

    private void showDialogAdd() { //для окна добавления
        if (editStageAdd == null) {
            editStageAdd = new Stage();
            editStageAdd.setTitle("Добавление записи");
            editStageAdd.setMinHeight(75);
            editStageAdd.setMinWidth(495);
            editStageAdd.setResizable(false);
            editStageAdd.setScene(new Scene(fxmlEditAdd));
            editStageAdd.initModality(Modality.WINDOW_MODAL);
            editStageAdd.initOwner(mainStage);
        }
        editStageAdd.showAndWait(); //ожидаем закрытия окна для дальнейших манипуляций
    }

    private void showDialog() {
        if (editStage == null) {
            editStage = new Stage();
            editStage.setTitle("Редактирование записи");
            editStage.setMinHeight(75);
            editStage.setMinWidth(495);
            editStage.setResizable(false);
            editStage.setScene(new Scene(fxmlEdit));
            editStage.initModality(Modality.WINDOW_MODAL);
            editStage.initOwner(mainStage);
        }
        editStage.showAndWait(); //ожидаем закрытия окна для дальнейших манипуляций
    }

    // очистка таблицы
    public void clearAll(ActionEvent actionEvent) {
        cwb.getWordsList().clear();
    }

    //удаление записи из TableView
    public void deletePerson(ActionEvent actionEvent) {
        cwb.delete((Word) tableFirst.getSelectionModel().getSelectedItem());
    }

    private void writeToExcel(String fileName) {
        XSSFWorkbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet();
        int end = cwb.getWordsList().size();

        for (int i = 0; i < end; i++) {
            Row row = sheet.createRow(i);
            org.apache.poi.ss.usermodel.Cell cell = row.createCell(0);
            cell.setCellValue(cwb.getWordsList().get(i).getCyr());
            cell = row.createCell(1);
            cell.setCellValue(cwb.getWordsList().get(i).getLat());
        }
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(fileName);
            wb.write(fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //сохранение в файл
    public void saveToCall(ActionEvent actionEvent) {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Сохранение списка транслитераций...");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("MS Excel files", "*.xlsx"));
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) writeToExcel(file.getPath());
    }
}



