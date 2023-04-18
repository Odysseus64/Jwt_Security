package plasma.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "groups")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "groupName")
    private String groupName;
    @Column(name = "dataStart")
    private String dataStart;
    @Column(name = "dataFinish")
    private String dataFinish;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Student> students;
    @ManyToMany
    @JoinTable(name = "course_group",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;
}