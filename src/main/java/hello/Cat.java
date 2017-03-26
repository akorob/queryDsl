package hello;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "cat")
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy="cat", cascade = CascadeType.ALL)
    private List<Owner> owners;

    public Cat() {
    }

    public Cat(String name, List<Owner> owners) {
        this.name = name;
        this.owners = owners;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Owner> getOwners() {
        return owners;
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owners=" + (owners == null ? null : String.join(", ", owners.stream()
                .map(object -> Objects.toString(object, null))
                .collect(Collectors.toList()))) +
                '}';
    }
}
