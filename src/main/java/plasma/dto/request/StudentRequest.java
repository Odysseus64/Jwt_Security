package plasma.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import plasma.models.StudyFormat;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private StudyFormat studyFormat;
}
