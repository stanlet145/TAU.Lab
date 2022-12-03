package lab2.service;

import lab2.model.Book;

import java.util.List;

public interface CsvParser {
    List<Book> parseCsvToBookList(String bookCsv);
}
