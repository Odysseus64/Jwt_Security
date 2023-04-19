package plasma.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import plasma.dto.request.StudentRequest;
import plasma.dto.request.TeacherRequest;
import plasma.dto.request.UserRequest;
import plasma.models.Role;
import plasma.models.Student;
import plasma.models.User;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private PasswordEncoder passwordEncoder;

    public User user(UserRequest userRequest) {
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setFirst_name(userRequest.getName());
        return user;
    }

    public User user(StudentRequest studentRequest) {
        User user = new User();
        user.setEmail(studentRequest.getEmail());
        user.setPassword(passwordEncoder.encode(studentRequest.getPassword()));
        user.setRole(Role.STUDENT);
        return user;
    }

    public User user(TeacherRequest teacherRequest) {
        User user = new User();
        user.setEmail(teacherRequest.getEmail());
        user.setPassword(passwordEncoder.encode(teacherRequest.getPassword()));
        user.setFirst_name(teacherRequest.getFirst_name());
        user.setRole(Role.TEACHER);
        return user;
    }
}
