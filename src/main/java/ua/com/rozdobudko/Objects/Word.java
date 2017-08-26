package ua.com.rozdobudko.Objects;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author Oleksii Rozdobudko rozdobudko.com.ua
 * @version 1.1.1
 * @since 2017-08-26
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
                return "A";
            case 'а':
                return "a";
            case 'Б':
                return "B";
            case 'б':
                return "b";
            case 'В':
                return "V";
            case 'в':
                return "v";
            case 'Г':
                return "G";
            case 'г':
                return "g";
            case 'Д':
                return "D";
            case 'д':
                return "d";
            case 'Е':
                return "E";
            case 'е':
                return "e";
            case 'Ё':
                return "Yo";
            case 'ё':
                return "yo";
            case 'Ж':
                return "Zh";
            case 'ж':
                return "zh";
            case 'З':
                return "Z";
            case 'з':
                return "z";
            case 'И':
                return "I";
            case 'и':
                return "i";
            case 'Й':
                return "J";
            case 'й':
                return "j";
            case 'К':
                return "K";
            case 'к':
                return "k";
            case 'Л':
                return "L";
            case 'л':
                return "l";
            case 'М':
                return "M";
            case 'м':
                return "m";
            case 'Н':
                return "N";
            case 'н':
                return "n";
            case 'О':
                return "O";
            case 'о':
                return "o";
            case 'П':
                return "P";
            case 'п':
                return "p";
            case 'Р':
                return "R";
            case 'р':
                return "r";
            case 'С':
                return "S";
            case 'с':
                return "s";
            case 'Т':
                return "T";
            case 'т':
                return "t";
            case 'У':
                return "U";
            case 'у':
                return "u";
            case 'Ф':
                return "F";
            case 'ф':
                return "f";
            case 'Х':
                return "Kh";
            case 'х':
                return "kh";
            case 'Ц':
                return "Ts";
            case 'ц':
                return "ts";
            case 'Ч':
                return "Ch";
            case 'ч':
                return "ch";
            case 'Ш':
                return "Sh";
            case 'ш':
                return "sh";
            case 'Щ':
                return "Shh";
            case 'щ':
                return "shh";
            case 'Ъ':
                return "";
            case 'ъ':
                return "";
            case 'Ы':
                return "Y";
            case 'ы':
                return "y";
            case 'Ь':
                return "";
            case 'ь':
                return "";
            case 'Э':
                return "E";
            case 'э':
                return "e";
            case 'Ю':
                return "Yu";
            case 'ю':
                return "yu";
            case 'Я':
                return "Ya";
            case 'я':
                return "ya";
            case 'Ї':
                return "Yi";
            case 'ї':
                return "yi";
            case 'І':
                return "I";
            case 'і':
                return "i";
            case 'Є':
                return "E";
            case 'є':
                return "e";
            case '~':
                return "";
            case '#':
                return "";
            case '%':
                return "";
            case '&':
                return "";
            case '*':
                return "";
            case '{':
                return "";
            case '}':
                return "";
            case '\\':
                return "";
            case '|':
                return "";
            case '/':
                return "";
            case ':':
                return "";
            case '<':
                return "";
            case '>':
                return "";
            case '?':
                return "";
            case '+':
                return "";
            case '"':
                return "";
            case ' ':
                return "_";
            default:
                return String.valueOf(ch);
        }
    }

    public String cyr2lat(String s) {
        StringBuilder sb = new StringBuilder(s.length() * 2);
        for (char ch : s.toCharArray()) {
            sb.append(cyr2lat(ch));
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Word{" +
                "cyr=" + cyr +
                ", lat=" + lat +
                '}';
    }
}
