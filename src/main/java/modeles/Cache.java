package modeles;

import lombok.Data;
import org.mongodb.morphia.annotations.Reference;

import javax.persistence.*;
import java.util.List;

@Entity
@org.mongodb.morphia.annotations.Entity
@Data
@Table(name = "cache")
public class Cache {

    @Id
    @org.mongodb.morphia.annotations.Id
    @Column(name = "id_cache")
    private String id;
    @Column(name = "coordonnees")
    private String coordonnees;
    @Column(name = "etat")
    private String etat;
    @Column(name = "nature")
    private String nature;

    @Reference()
    @JoinColumn(name = "id_type")
    @ManyToOne
    private Type type;
    @Reference()
    @JoinColumn(name = "id_lieu")
    @ManyToOne
    private Lieu lieu;

    @Reference
    @JoinColumn(name = "id_user")
    @ManyToOne
    private User user;
    @Reference
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
