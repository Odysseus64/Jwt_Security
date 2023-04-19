package plasma.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import plasma.dto.response.TeacherResponse;
import plasma.models.Teacher;

@Component
@RequiredArgsConstructor
public class TeacherMapper {
    private PasswordEncoder passwordEncoder;

    public Teacher teacher (TeacherResponse teacherResponse){
        Teacher teacher = new Teacher();
        teacher.setEmail(teacherResponse.getEmail());
        teacher.setFirst_name(teacherResponse.getFirst_name());
        teacher.setLast_name(teacherResponse.getLast_name());
        teacher.setPassword(passwordEncoder.encode(teacherResponse.getPassword()));
        return teacher;
    }
}
