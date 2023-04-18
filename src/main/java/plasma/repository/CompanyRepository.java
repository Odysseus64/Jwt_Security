package plasma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import plasma.models.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
