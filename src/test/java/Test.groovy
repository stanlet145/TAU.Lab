import model.Book
import service.CsvParser
import spock.lang.Specification

class Test extends Specification {

    def "test"() {
        given:
        def csvParser = Mock(CsvParser)
        def bookCsv = "test, john, 1"
        def expectedResult = Book.builder()
                .name("test")
                .author("john")
                .price(1)
                .build()
        when:
        def result = csvParser.parseCsvToBook(bookCsv)
        then:
        result == expectedResult
    }
}
