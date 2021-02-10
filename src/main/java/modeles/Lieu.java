package modeles;

import lombok.Data;
import org.mongodb.morphia.annotations.Reference;

import javax.persistence.*;
import java.util.List;

@Entity
@org.mongodb.morphia.annotations.Entity
@Data
@Table(name = "lieu")
public class Lieu {

    @Id
    @org.mongodb.morphia.annotations.Id
    @Column(name = "id_lieu")
    private String id;
    @Column(name = "geodata")
    private String geoData;

    @Reference
    @OneToMany(mappedBy = "lieu")
    private List<Cache> listeCache;

    public Lieu(String UUID, String geoData) {
        this.id = UUID;
        this.geoData = geoData;
    }

    public Lieu() { }
}
