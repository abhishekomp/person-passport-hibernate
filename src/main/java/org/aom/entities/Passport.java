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
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String passportNumber;

    @OneToOne(mappedBy = "passport")    //this is required when we want to have bidirectional one to one mapping.
    private Person person;

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", passportNumber='" + passportNumber + '\'' +
                ", personName=" + person.getName() +
                '}';
    }
}
