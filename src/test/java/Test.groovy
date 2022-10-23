import spock.lang.Specification

class Test extends Specification {

    def "test"() {
        given:
        def a = 1;
        when:
        def result = a == 1
        then:
        result == true
    }
}
