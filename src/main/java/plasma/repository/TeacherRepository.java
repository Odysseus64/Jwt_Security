package plasma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import plasma.models.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query("select case when count(u)>0 then true else false end from Teacher u where u.email like :email")
    boolean existsByEmail(@Param(value = "email") String email);
}
