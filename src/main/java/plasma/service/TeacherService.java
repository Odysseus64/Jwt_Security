package plasma.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import plasma.dto.request.TeacherRequest;
import plasma.dto.response.TeacherResponse;
import plasma.mapper.TeacherMapper;
import plasma.mapper.UserMapper;
import plasma.models.Teacher;
import plasma.repository.TeacherRepository;
import plasma.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final TeacherMapper teacherMapper;
    private final UserMapper userMapper;
    private final ModelMapper modelMapper;

    public TeacherResponse save(TeacherRequest teacherRequest) {
        teacherRepository.existsByEmail(teacherRequest.getEmail());
        userRepository.save(userMapper.user(teacherRequest));
        Teacher savedTeacher = teacherRepository.save(teacherMapper.teacher(teacherRequest));
        return modelMapper.map(savedTeacher, TeacherResponse.class);
    }

    public TeacherResponse findById(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElse(null);
        return modelMapper.map(teacher, TeacherResponse.class);
    }

    public List<TeacherResponse> findAll() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers.stream()
                .map(teacher -> modelMapper.map(teacher, TeacherResponse.class))
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        teacherRepository.deleteById(id);
    }

    public TeacherResponse update(Long id, TeacherRequest teacherRequest) {
        Teacher teacher = teacherRepository.findById(id).orElse(null);
        modelMapper.map(teacherRequest, teacher);
        teacher = teacherRepository.save(teacher);
        return modelMapper.map(teacher, TeacherResponse.class);
    }
}