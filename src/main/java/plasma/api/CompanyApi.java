package plasma.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import plasma.dto.request.CompanyRequest;
import plasma.dto.response.CompanyResponse;
import plasma.service.CompanyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company/api")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class CompanyApi {

    private final CompanyService service;

    @PostMapping("/save/company")
    public String save(@RequestBody CompanyRequest companyRequest) {
        service.save( companyRequest);
        return "Company created!";
    }

    @GetMapping("/findAll")
    public List<CompanyResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/findById/{id}")
    public CompanyResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/deleteById/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "Company deleted!";
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id, @RequestBody CompanyRequest request) {
        service.updateById(id, request);
        return "Company updated!";
    }
}
