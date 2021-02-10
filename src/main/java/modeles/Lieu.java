package modeles;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "lieu")
public class Lieu {

    @Id
    @Column(name = "id_lieu")
    private String id;
    @Column(name = "geodata")
    private String geoData;


    @OneToMany(mappedBy = "lieu")
    private List<Cache> listeCache;

    public Lieu(String UUID, String geoData) {
        this.id = UUID;
        this.geoData = geoData;
    }

    public Lieu() { }

    @Override
    public String toString() {
        return "Lieu{" +
                "id='" + id + '\'' +
                ", geoData='" + geoData + '\'' +
                '}';
    }
}
