package lab4.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class LanguageCourses {
    private final List<Courses> coursesList = new ArrayList<>();

    public LanguageCourses() {
        coursesList.add(new Courses(Language.POLISH, 200, LocalDate.of(2022, 4, 12), LocalDate.of(2022, 7, 14)));
        coursesList.add(new Courses(Language.GERMAN, 500, LocalDate.of(2022, 6, 10), LocalDate.of(2022, 8, 10)));
        coursesList.add(new Courses(Language.FRENCH, 400, LocalDate.of(2022, 2, 11), LocalDate.of(2022, 5, 5)));
        coursesList.add(new Courses(Language.ENGLISH, 300, LocalDate.of(2022, 7, 14), LocalDate.of(2022, 12, 22)));
    }

    public List<Courses> getCoursesBelowPrice(int price) {
        return coursesList
                .stream()
                .filter(course -> course.getPricePerMonth() < price)
                .collect(Collectors.toList());
    }
}
