package modeles;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "type")
public class Type {

    @Id
    @Column(name = "id_type")
    private String id;
    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "type")
    private List<Cache> listeCache;

    public Type(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public Type() {}

    @Override
    public String toString() {
        return "Type{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
