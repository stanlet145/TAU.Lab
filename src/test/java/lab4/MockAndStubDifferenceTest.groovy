package lab4

import lab4.model.Courses
import lab4.model.Language
import lab4.model.LanguageCourses
import lab4.service.CourseServiceImpl
import spock.lang.Specification

import java.time.LocalDate

class MockAndStubDifferenceTest extends Specification {

    /**
     * Stubbing is setting a state of an object in testing.
     * Despite the fact that we passed invalid number for given output, test passes.
     * It proves that stubbing works (no matter what, object will always store 4 records).
     */
    def "stubTest"() {
        given:
        def languageCoursesList = List.of(new Courses(Language.POLISH, 200, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 7, 14)),
                new Courses(Language.GERMAN, 500, LocalDate.of(2022, 6, 10), LocalDate.of(2022, 8, 10)),
                new Courses(Language.FRENCH, 400, LocalDate.of(2022, 2, 11), LocalDate.of(2022, 5, 5)),
                new Courses(Language.ENGLISH, 300, LocalDate.of(2022, 7, 14), LocalDate.of(2022, 12, 22)))
        and:
        def languageCourses = Stub(LanguageCourses)
        languageCourses.getCoursesBelowPrice(_) >> languageCoursesList
        when:
        def result = languageCourses.getCoursesBelowPrice(1)
        then:

        result.size() == 4
    }

    /**
     * Mocking is setting a behavior for a call (ex. a function for given service)
     * Test below proves that mocking works, because we have programmed expected behavior of a mock
     * within a service
     */
    def "mockTest"() {
        given:
        def courses = Mock(LanguageCourses)
        def service = new CourseServiceImpl(courses)
        when:
        def result = service.getAllAvailableCourses()
        then:
        1 * courses.getCoursesList() >> List.of()
        then:
        result.size() == 0
    }
}
