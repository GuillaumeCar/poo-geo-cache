package modeles;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "cache")
public class Cache {

    @Id
    @Column(name = "id_cache")
    private String id;
    @Column(name = "coordonnees")
    private String coordonnees;
    @Column(name = "etat")
    private String etat;
    @Column(name = "nature")
    private String nature;
    @Column(name = "id_type")
    private int idType;
    @Column(name = "id_lieu")
    private int idLieu;

    @JoinColumn(name = "id_user")
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "cache")
    private List<Visite> listeVisite;
}
