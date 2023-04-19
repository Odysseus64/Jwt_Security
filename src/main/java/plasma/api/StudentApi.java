package plasma.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import plasma.dto.request.StudentRequest;
import plasma.dto.response.StudentResponse;
import plasma.service.StudentService;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student/api")
public class StudentApi {

    private final StudentService service;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/save")
    public String save(@RequestBody StudentRequest request) {
        service.save(request);
        return "Student saved!";
    }

    @PermitAll
    @GetMapping("/findAll")
    public List<StudentResponse> findStudentById() {
        return service.findAll(); //Все подозрительно хорошо работает
    } //Раз я стал хорошо писать или же мне мне ошибки не показывают

    @PermitAll
    @GetMapping("/findById/{id}")
    public StudentResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/remove/{id}")
    public String remove(@PathVariable Long id) {
        service.deleteById(id);
        return "Student deleted!";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/update/{id}")              //Могут возникнуть ошибки
    public String update(@PathVariable Long id, @RequestBody StudentRequest request) {
        service.update(id, request);
        return "Student updated!";
    }
}