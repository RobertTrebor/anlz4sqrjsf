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
@NamedQueries({
        @NamedQuery(name = "Venue.findAll", query = "SELECT g FROM VenueHistory g")
})
public class VenueHistory implements PersistentEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer beenHere;

    @OneToOne
    private CompactVenue venue;

}
