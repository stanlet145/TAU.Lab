package service;

import model.Book;

import java.util.List;

public interface CsvParser {
    List<Book> parseCsvToBookList(String bookCsv);
}
