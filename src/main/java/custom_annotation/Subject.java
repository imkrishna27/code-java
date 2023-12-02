package custom_annotation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    private String subject;

    @PercentageValidator(message = "percentage name must not be empty.")
    private Integer percentage;

}
