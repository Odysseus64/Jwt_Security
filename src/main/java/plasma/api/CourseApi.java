package plasma.api;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import plasma.dto.request.CourseRequest;
import plasma.dto.response.CourseResponse;
import plasma.service.CourseService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course/api")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class CourseApi {

    private final CourseService service;

    @PostMapping("/save")
    public String createCourse(@RequestBody CourseRequest request) {
        service.save(request);
        return "Course created!";
    }

    @GetMapping("/findAll")
    public List<CourseResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/findById/{id}")
    public CourseResponse getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/remove/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "Course deleted!";
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id, @RequestBody CourseRequest request) {
        service.updateById(id, request);
        return "Course updated!";
    }
}