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
    private String idType;
    @Column(name = "id_lieu")
    private String idLieu;

    @JoinColumn(name = "id_user")
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "cache")
    private List<Visite> listeVisite;

    public Cache(String id,
                 String coordonnees,
                 String etat,
                 String nature,
                 String idType,
                 String idLieu,
                 User user
    ) {
      this.id = id;
      this.coordonnees = coordonnees;
      this.etat = etat;
      this.nature = nature;
      this.idType = idType;
      this.idLieu = idLieu;
      this.user = user;
    }

    public Cache() {}
}
