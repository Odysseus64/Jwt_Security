package plasma.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import plasma.dto.request.StudentRequest;
import plasma.models.Role;
import plasma.models.Student;

@Component
@RequiredArgsConstructor
public class StudentMapper {
    private final PasswordEncoder passwordEncoder;

    public Student toStudent(StudentRequest studentRequest) {
        Student student = new Student();
        student.setEmail(studentRequest.getEmail());
        student.setFirst_name(studentRequest.getFirstName());
        student.setLast_name(studentRequest.getLastName());
        student.setEmail(studentRequest.getEmail());
        student.setStudyFormat(Role.STUDENT);
        student.setPassword(passwordEncoder.encode(studentRequest.getPassword()));
        return student;
    }
}