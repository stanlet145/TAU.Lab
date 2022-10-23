import model.Book
import service.CsvParserImpl
import spock.lang.Specification

class CsvParserTest extends Specification {

    def "parseCsvToBookTest"() {
        given:
        def csvParser = new CsvParserImpl()
        when:
        def result = csvParser.parseCsvToBookList(bookCsv)
        then:
        result.get(index) == expectedResult
        where:
        expectedResult              | bookCsv                   | index
        new Book("test", "john", 1) | "test,john,1"             | 0
        new Book("abc", "abs", 14)  | "test,john,2\nabc,abs,14" | 1
        new Book("test", "john", 2) | "test,john,2\nabc,abs,14" | 0
    }
}
