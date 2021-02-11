package modeles;

import lombok.Data;
import org.mongodb.morphia.annotations.Reference;

import javax.persistence.*;
import java.util.Date;

@Entity
@org.mongodb.morphia.annotations.Entity
@Data
@Table(name = "visite")
public class Visite {

    @Id
    @org.mongodb.morphia.annotations.Id
    @Column(name = "id_visite")
    private String id;
    @Column(name = "photo")
    private String photo;
    @Column(name = "date")
    private Date date;
    @Column(name = "commentaire")
    private String commentaire;
    @Column(name = "is_logged")
    private boolean isLogged;

    @Reference
    @ManyToOne
    @JoinColumn(name = "id_cache")
    private Cache cache;
    @Reference
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public Visite(
            String  id,
            String  photo,
            Date    date,
            String  commentaire,
            boolean isLogged,
            Cache   cache,
            User    user
    ) {
        this.id          = id;
        this.photo       = photo;
        this.date        = date;
        this.commentaire = commentaire;
        this.isLogged    = isLogged;
        this.cache       = cache;
        this.user        = user;
    }

    public Visite() {}

    @Override
    public String toString() {
        return "Visite{" +
                "id='" + id + '\'' +
                ", photo='" + photo + '\'' +
                ", date=" + date +
                ", commentaire='" + commentaire + '\'' +
                ", isLogged=" + isLogged +
                ", cache=" + cache.toString() +
                ", userVisiteur=" + user.toString() +
                "}\n";
    }
}
