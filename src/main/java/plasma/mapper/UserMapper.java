package plasma.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import plasma.dto.request.StudentRequest;
import plasma.models.Student;
import plasma.models.User;

public class UserMapper {
    private PasswordEncoder passwordEncoder;

    public User student (StudentRequest studentRequest, Student student){
        student.setEmail(studentRequest.getEmail());
        student.setPassword(passwordEncoder.encode(studentRequest.getPassword()));
        student.setStudyFormat();

    }
}
