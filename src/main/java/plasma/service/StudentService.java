package plasma.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import plasma.dto.request.StudentRequest;
import plasma.dto.response.StudentResponse;
import plasma.mapper.StudentMapper;
import plasma.mapper.UserMapper;
import plasma.models.Student;
import plasma.repository.StudentRepository;
import plasma.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final StudentMapper studentMapper;
    private final UserMapper userMapper;
    private final ModelMapper modelMapper;

    public StudentResponse save(StudentRequest studentRequest) {
        studentRepository.existsByEmail(studentRequest.getEmail());
        userRepository.save(userMapper.user(studentRequest));
        Student student = studentRepository.save(studentMapper.toStudent(studentRequest));
        return modelMapper.map(student, StudentResponse.class);
    }

    public StudentResponse findById(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        return modelMapper.map(student, StudentResponse.class);
    }

    public List<StudentResponse> findAll() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(student -> modelMapper.map(student, StudentResponse.class))
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    public StudentResponse update(Long id, StudentRequest studentRequest) {
        Student student = studentRepository.findById(id).orElse(null);
        modelMapper.map(studentRequest, student);
        student = studentRepository.save(student);
        return modelMapper.map(student, StudentResponse.class);
    }
}
