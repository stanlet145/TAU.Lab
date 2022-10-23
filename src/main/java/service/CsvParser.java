package service;

import model.Book;

public interface CsvParser {
    Book parseCsvToBook(String bookCsv);
}
