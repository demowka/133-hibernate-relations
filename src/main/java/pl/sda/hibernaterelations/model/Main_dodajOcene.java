package pl.sda.hibernaterelations.model;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.Scanner;

public class Main_dodajOcene {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Dodaj studenta
        //  - zapytaj użytkownika o:
        //      - id studenta któremu chce dodać ocene
        System.out.println("Podaj id studenta:");
        String idStudenta = scanner.nextLine();
        Long id = Long.parseLong(idStudenta);

        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            //  Następnie znajdź tego studenta i jeśli nie istnieje, zwróć komunikat.
            Student szukanyStudent = session.get(Student.class, id);

            //  Jeśli student istnieje
            if (szukanyStudent != null) {
                // to zapytaj o wartość oceny.
                System.out.println("Podaj ocenę:");
                String dodawanaOcena = scanner.nextLine();
                double wartoscOceny = Double.parseDouble(dodawanaOcena);

                System.out.println("Podaj przedmiot:");
                String przedmiotOceny = scanner.nextLine();
                Przedmiot przedmiot = Przedmiot.valueOf(przedmiotOceny);

                //  Stwórz obiekt oceny i przypisz do oceny studenta.
                Ocena nowaOcena = Ocena.builder()
                        .uczen(szukanyStudent)
                        .wartosc(wartoscOceny)
                        .build();

                //  Zapisz ocene.
                session.persist(nowaOcena);

            } else {
                System.err.println("Taki student nie istnieje");
            }

            transaction.commit();
        } catch (Exception e) {
            System.err.println("Błąd dodawania studenta do bazy");
        }
}
}
