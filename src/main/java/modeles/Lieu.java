package modeles;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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

    public Lieu(String UUID, String geoData) {
        this.id = UUID;
        this.geoData = geoData;
    }

    public Lieu() { }
}
