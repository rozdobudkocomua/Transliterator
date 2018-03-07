package ua.com.rozdobudko.Objects;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author Oleksii Rozdobudko rozdobudko.com.ua
 * @version 1.1.2
 * @since 2017-12-20
 */
public class Word {
    private SimpleStringProperty cyr = new SimpleStringProperty("");
    private SimpleStringProperty lat = new SimpleStringProperty("");


    public Word() {
    }

    public Word(String cyr, String lat) {
        this.cyr = new SimpleStringProperty(cyr);
        this.lat = new SimpleStringProperty(lat);
    }

    public String getCyr() {
        return cyr.get();
    }

    public SimpleStringProperty cyrProperty() {
        return cyr;
    }

    public void setCyr(String cyr) {
        this.cyr.set(cyr);
    }

    public String getLat() {
        return lat.get();
    }

    public SimpleStringProperty latProperty() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat.set(lat);
    }

    private String cyr2lat(char ch) {
        switch (ch) {
            case 'А':
                return "a";
            case 'а':
                return "a";
            case 'Б':
                return "b";
            case 'б':
                return "b";
            case 'В':
                return "v";
            case 'в':
                return "v";
            case 'Г':
                return "g";
            case 'г':
                return "g";
            case 'Д':
                return "d";
            case 'д':
                return "d";
            case 'Е':
                return "e";
            case 'е':
                return "e";
            case 'Ё':
                return "yo";
            case 'ё':
                return "yo";
            case 'Ж':
                return "zh";
            case 'ж':
                return "zh";
            case 'З':
                return "z";
            case 'з':
                return "z";
            case 'И':
                return "i";
            case 'и':
                return "i";
            case 'Й':
                return "j";
            case 'й':
                return "j";
            case 'К':
                return "k";
            case 'к':
                return "k";
            case 'Л':
                return "l";
            case 'л':
                return "l";
            case 'М':
                return "m";
            case 'м':
                return "m";
            case 'Н':
                return "n";
            case 'н':
                return "n";
            case 'О':
                return "o";
            case 'о':
                return "o";
            case 'П':
                return "p";
            case 'п':
                return "p";
            case 'Р':
                return "r";
            case 'р':
                return "r";
            case 'С':
                return "s";
            case 'с':
                return "s";
            case 'Т':
                return "t";
            case 'т':
                return "t";
            case 'У':
                return "u";
            case 'у':
                return "u";
            case 'Ф':
                return "f";
            case 'ф':
                return "f";
            case 'Х':
                return "kh";
            case 'х':
                return "kh";
            case 'Ц':
                return "ts";
            case 'ц':
                return "ts";
            case 'Ч':
                return "ch";
            case 'ч':
                return "ch";
            case 'Ш':
                return "sh";
            case 'ш':
                return "sh";
            case 'Щ':
                return "shh";
            case 'щ':
                return "shh";
            case 'Ъ':
                return "";
            case 'ъ':
                return "";
            case 'Ы':
                return "y";
            case 'ы':
                return "y";
            case 'Ь':
                return "";
            case 'ь':
                return "";
            case 'Э':
                return "e";
            case 'э':
                return "e";
            case 'Ю':
                return "yu";
            case 'ю':
                return "yu";
            case 'Я':
                return "ya";
            case 'я':
                return "ya";
            case 'Ї':
                return "yi";
            case 'ї':
                return "yi";
            case 'І':
                return "i";
            case 'і':
                return "i";
            case 'Є':
                return "e";
            case 'є':
                return "e";
            case '~':
                return "-";
            case '#':
                return "-";
            case '%':
                return "-";
            case '&':
                return "-";
            case '*':
                return "-";
            case '{':
                return "-";
            case '}':
                return "-";
            case '\\':
                return "-";
            case '|':
                return "-";
            case '/':
                return "-";
            case ':':
                return "-";
            case '<':
                return "-";
            case '>':
                return "-";
            case '?':
                return "-";
            case '+':
                return "-";
            case '"':
                return "-";
            case ' ':
                return "-";
            default:
                return String.valueOf(ch);
        }
    }

    public String cyr2lat(String s) {
        StringBuilder sb = new StringBuilder(s.length() * 2);
        for (char ch : s.toCharArray()) {
            sb.append(cyr2lat(ch));
        }
        return sb.toString().toLowerCase();
    }

    @Override
    public String toString() {
        return "Word{" +
                "cyr=" + cyr +
                ", lat=" + lat +
                '}';
    }
}
