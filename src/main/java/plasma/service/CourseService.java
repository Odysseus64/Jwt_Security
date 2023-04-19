package plasma.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import plasma.dto.request.CourseRequest;
import plasma.dto.response.CourseResponse;
import plasma.models.Course;
import plasma.repository.CourseRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    public CourseResponse save(CourseRequest courseRequest) {
        Course course = modelMapper.map(courseRequest, Course.class);
        course = courseRepository.save(course);
        return modelMapper.map(course, CourseResponse.class);
    }

    public CourseResponse findById(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        return modelMapper.map(course, CourseResponse.class);
    }

    public List<CourseResponse> findAll() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(course -> modelMapper.map(course, CourseResponse.class))
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    public CourseResponse updateById(Long id, CourseRequest courseRequest) {
        Course course = courseRepository.findById(id).orElse(null);
        modelMapper.map(courseRequest, course);
        course = courseRepository.save(course);
        return modelMapper.map(course, CourseResponse.class);
    }
}