package pl.sda.hibernaterelations.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imie;

    @Column(nullable = false)
    private String nazwisko;

    //@Column(nullable = false)
    private  int rokRozpoczeciaStudiow;

    //nie chcemy, aby to byla kolumna
    @Formula("(SELECT AVG(o.wartosc) FROM ocena o WHERE o.uczen_id=id)")
    private Double sredniaOcen;

    //RELACJE
    @OneToMany(mappedBy = "uczen") //nazwa pola !NIE KLASY!
    private Set<Ocena> oceny;
}
