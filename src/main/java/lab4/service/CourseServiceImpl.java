package lab4.service;

import lab4.model.Courses;
import lab4.model.LanguageCourses;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final LanguageCourses languageCourses;

    public List<Courses> getAllAvailableCourses() {
        return languageCourses.getCoursesList();
    }
}
