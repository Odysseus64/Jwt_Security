package plasma.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "course")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "courName")
    private String courName;
    @Column(name = "duration")
    private String duration;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @OneToOne(mappedBy = "course", cascade = CascadeType.ALL)
    private Teacher teacher;
    @ManyToMany(
            mappedBy = "courses",
            cascade = CascadeType.ALL)
    private List<Groups> groups;
}