package modeles;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id_user")
    private String id;
    @Column(name = "pseudo")
    private String pseudo;
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "user")
    private List<Cache> listeCache;
}
