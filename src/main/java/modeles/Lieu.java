package modeles;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "lieu")
public class Lieu {

    @Id
    @Column(name = "id_lieu")
    private String id;
    @Column(name = "geodata")
    private String geoData;

}
