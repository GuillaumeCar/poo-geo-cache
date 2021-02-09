package modeles;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "visite")
public class Visite {

    @Id
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

    @ManyToOne
    @JoinColumn(name = "id_cache")
    private Cache cache;
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
}
