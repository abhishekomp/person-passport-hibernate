package org.aom.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author  : Abhishek
 * @since   : 2024-04-10, Wednesday
 **/
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
//@Setter(value = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PUBLIC)
@Getter
@EqualsAndHashCode
//@ToString
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    //Foreign key is stored in the table corresponding to the Entity having @OneToOne without mappedBy
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, optional = false)  //optional = false means that the relationship is mandatory which means you cannot create a person without a passport.
    @JoinColumn(name = "passport")  //In this case, person is the owner of the relationship because the foreign key will be stored in Person entity.
    private Passport passport;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passport=" + passport.getPassportNumber() +
                '}';
    }
}
