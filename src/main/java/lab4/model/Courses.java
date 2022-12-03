package lab4.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Courses {
    private Language language;
    private int pricePerMonth;
    private LocalDate startDate;
    private LocalDate endDate;
}
