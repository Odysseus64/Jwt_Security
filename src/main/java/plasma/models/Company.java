package plasma.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "company")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "companyName")
    private String companyName;
    @Column(name = "locatedCountry")
    private String locatedCountry;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Course> courses;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Groups> groups;
}
