package org.soneech.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Person")
@NoArgsConstructor
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)  // saving (persist method)
    // @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE) // saving (save method - deprecated)
    private List<Item> items;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void addItem(Item item) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
        item.setOwner(this);
    }

    @Override
    public String toString() {
        return name + ", " + age;
    }
}
