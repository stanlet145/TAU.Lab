import model.Book
import service.CsvParserImpl
import spock.lang.Specification

class CsvParserTest extends Specification {

    def "parseCsvToBookTest"() {
        given:
        def csvParser = new CsvParserImpl()
        def bookCsv = "test,john,1"
        def expectedResult = Book.builder()
                .name("test")
                .author("john")
                .price(1)
                .build()
        when:
        def result = csvParser.parseCsvToBookList(bookCsv)
        then:
        result.get(0) == expectedResult
    }
}
