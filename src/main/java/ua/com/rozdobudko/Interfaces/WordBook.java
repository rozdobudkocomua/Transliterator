package ua.com.rozdobudko.Interfaces;

import ua.com.rozdobudko.Objects.Word;

/**
 * @author Oleksii Rozdobudko rozdobudko.com.ua
 * @version 1.1.1
 * @since 2017-08-26
 */
public interface WordBook {

    void add(Word word);

    void edit(Word word);

    void delete(Word word);
}
