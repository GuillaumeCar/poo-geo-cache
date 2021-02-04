package modeles;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "type")
public class Type {

    @Id
    @Column(name = "id_type")
    private String id;
    @Column(name = "type")
    private String type;
}
