package modeles;

import lombok.Data;
import org.mongodb.morphia.annotations.Reference;

import javax.persistence.*;
import java.util.List;

@Entity
@org.mongodb.morphia.annotations.Entity
@Data
@Table(name = "type")
public class Type {

    @Id
    @org.mongodb.morphia.annotations.Id
    @Column(name = "id_type")
    private String id;
    @Column(name = "type")
    private String type;

    @Reference
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
