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

    @JoinColumn(name = "id_type")
    @ManyToOne
    private Type type;
    @JoinColumn(name = "id_lieu")
    @ManyToOne
    private Lieu lieu;

    @JoinColumn(name = "id_user")
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "cache")
    private List<Visite> listeVisite;

    public Cache(String id,
                 String coordonnees,
                 String etat,
                 String nature,
                 Type type,
                 Lieu lieu,
                 User user
    ) {
      this.id = id;
      this.coordonnees = coordonnees;
      this.etat = etat;
      this.nature = nature;
      this.type = type;
      this.lieu = lieu;
      this.user = user;
    }

    public Cache() {}

    @Override
    public String toString() {
        return "\n\tCache{" +
                "id='" + id + '\'' +
                ", coordonnees='" + coordonnees + '\'' +
                ", etat='" + etat + '\'' +
                ", nature='" + nature + '\'' +
                ", type=" + type.toString() +
                ", lieu=" + lieu.toString() +
                ", userPropriétaire=" + user.toString() +
                "}";
    }
}
