package plasma.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import plasma.dto.request.TeacherRequest;
import plasma.dto.response.TeacherResponse;
import plasma.service.TeacherService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/teacher/api")
public class TeacherApi {

    private final TeacherService service;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/save")
    public String save(@RequestBody TeacherRequest request) {
        service.save(request);
        return "Teacher saved";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'TEACHER')")
    @GetMapping("/findAll")
    public List<TeacherResponse> findAll() {
        return service.findAll();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'TEACHER')")
    @GetMapping("/findById/{id}")
    public TeacherResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'TEACHER')")
    @DeleteMapping("/remove/{id}")
    public String remove(@PathVariable Long id) {
        service.deleteById(id);
        return "Teacher deleted";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'TEACHER')")
    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id, @RequestBody TeacherRequest request) {
        service.update(id, request);
        return "Teacher updated";
    }
}