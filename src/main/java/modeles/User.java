package modeles;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "users")
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
    @OneToMany(mappedBy = "user")
    private List<Visite> listeVisite;

    public User(String id, String pseudo, String description) {
        this.id = id;
        this.pseudo = pseudo;
        this.description = description;
    }

    public User() {}

    @Override
    public String toString() {
        return "\n\tUser{" +
                "id='" + id + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", description='" + description + '\'' +
                "}";
    }
}
