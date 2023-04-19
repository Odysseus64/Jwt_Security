package plasma.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import plasma.dto.request.GroupsRequest;
import plasma.dto.response.GroupsResponse;
import plasma.service.GroupsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/group/api")
@PreAuthorize("hasAnyAuthority('ADMIN', 'TEACHER')")
public class GroupsApi {

    private final GroupsService service;

    @PostMapping("/save")
    public String save(@RequestBody GroupsRequest request) {
        service.save(request);
        return "Group created!";
    }

    @GetMapping("/findAll")
    public List<GroupsResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/findById/{id}")
    public GroupsResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/remove/{id}")
    public String deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return "Group deleted!";
    }

    @PutMapping("/update/{id}")
    public String updateById(@PathVariable Long id, @RequestBody GroupsRequest request) {
        service.update(id, request);
        return "Company updated!";
    }
}