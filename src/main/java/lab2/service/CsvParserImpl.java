package lab2.service;

import io.vavr.control.Try;
import lab2.model.Book;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

public class CsvParserImpl implements CsvParser {

    @Override
    public List<Book> parseCsvToBookList(String bookCsv) {
        return Try.of(() -> getCsvParserFromCsvString(bookCsv))
                .map(csvRecords -> csvRecords
                        .stream()
                        .map(inlineValues -> buildBook(inlineValues.get(0), inlineValues.get(1), inlineValues.get(2)))
                        .collect(Collectors.toList()))
                .getOrElseThrow(() -> new RuntimeException());
    }

    private CSVParser getCsvParserFromCsvString(String bookCsv) {
        return Try.of(() -> new CSVParser(new StringReader(bookCsv), CSVFormat.EXCEL))
                .getOrElseThrow(() -> new RuntimeException());
    }

    private Book buildBook(String name, String author, String price) {
        return Book.builder()
                .name(name)
                .author(author)
                .price(Integer.parseInt(price))
                .build();
    }
}
