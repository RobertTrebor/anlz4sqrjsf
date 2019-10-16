package de.lengsfeld.anlz4sqr.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode

@Setter
@Getter
@Entity
public class Location implements PersistentEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String address;

    @Column
    private String city;

    @Column
    private String postalCode;

    @Column
    private String country;

    @Column
    private String name;

    @Column
    private Double lat;

    @Column
    private Double lng;


}
