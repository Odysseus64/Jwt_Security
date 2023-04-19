package plasma.service;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import plasma.dto.request.CompanyRequest;
import plasma.dto.response.CompanyResponse;
import plasma.models.Company;
import plasma.repository.CompanyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    public CompanyResponse save(CompanyRequest companyRequest) {
        Company company = modelMapper.map(companyRequest, Company.class);
        company = companyRepository.save(company);
        return modelMapper.map(company, CompanyResponse.class);
    }

    public CompanyResponse findById(Long id) {
        Company company = companyRepository.findById(id).orElse(null);
        return modelMapper.map(company, CompanyResponse.class);
    }

    public List<CompanyResponse> findAll() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream()
                .map(company -> modelMapper.map(company, CompanyResponse.class))
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }

    public CompanyResponse updateById(Long id, CompanyRequest companyRequest) {
        Company company = companyRepository.findById(id).orElse(null);
        modelMapper.map(companyRequest, company);
        company = companyRepository.save(company);
        return modelMapper.map(company, CompanyResponse.class);
    }
}