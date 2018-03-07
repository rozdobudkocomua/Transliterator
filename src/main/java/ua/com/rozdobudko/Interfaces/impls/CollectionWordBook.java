package ua.com.rozdobudko.Interfaces.impls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ua.com.rozdobudko.Controllers.Controller;
import ua.com.rozdobudko.Interfaces.WordBook;
import ua.com.rozdobudko.Objects.Word;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author Oleksii Rozdobudko rozdobudko.com.ua
 * @version 1.1.2
 * @since 2017-12-20
 */
public class CollectionWordBook implements WordBook {

    private ObservableList<Word> wordObservableList = FXCollections.observableArrayList();
    Controller controller;

    @Override
    public void add(Word word) {
        wordObservableList.add(word);
    }

    @Override
    public void edit(Word word) {
    }

    @Override
    public void delete(Word word) {
        wordObservableList.remove(word);
    }

    public ObservableList<Word> getWordsList() {
        return wordObservableList;
    }

    public void setWordObservableList(ObservableList<Word> wordObservableList) {
        this.wordObservableList = wordObservableList;
    }

    public void readFromExcel(String fileName) throws IOException {  // тестируем выгрузку из экселя в коллекцию класса Word
        int CYR_CLMN = 0; //номера строк в экселе

        FileInputStream fis = new FileInputStream(fileName);
        XSSFWorkbook wb = new XSSFWorkbook(fis);  //  пока используем на *.xlsx
        XSSFSheet wb_sheet = wb.getSheetAt(0);
        Iterator<Row> rows = wb_sheet.rowIterator();
        /*
        if (rows.hasNext()) //пропускаем заголовок таблицы
        {
            rows.next();
        }
        */
        while (rows.hasNext()) {
            XSSFRow row = (XSSFRow) rows.next();
            Word word = new Word();
            word.setCyr(row.getCell(0).getStringCellValue().trim());
            //необходимо в коллекцию сразу добавлять метод для латиницы  для word.setLat для дальнейшего отображения
            word.setLat(word.cyr2lat(word.getCyr().trim()));
            wordObservableList.add(word);
        }
        fis.close();
    }
}
