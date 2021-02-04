package modeles;

import lombok.Data;

import javax.persistence.*;

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
    private String date;
    @Column(name = "commentaire")
    private String commentaire;
    @Column(name = "is_logged")
    private boolean isLogged;

    @ManyToOne
    @JoinColumn(name = "id_cache")
    private Cache cache;
}
