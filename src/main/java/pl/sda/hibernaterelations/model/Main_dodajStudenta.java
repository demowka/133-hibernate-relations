package pl.sda.hibernaterelations.model;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main_dodajStudenta {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

            System.out.println("Podaj imie:");
            String imie = scanner.nextLine();

            System.out.println("Podaj nazwisko:");
            String nazwisko = scanner.nextLine();

            System.out.println("Podaj rok rozpoczecia studiow:");
            String rokStudiow = scanner.nextLine();
            int rokRozpoczeciaStudiow = Integer.parseInt(rokStudiow);

        Student student = Student.builder()
                .imie(imie)
                .nazwisko(nazwisko)
                .rokRozpoczeciaStudiow(rokRozpoczeciaStudiow)
                .build();

        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();


            session.persist(student);

            transaction.commit();
        } catch (Exception ioe) {
            // jeśli złapiemy błąd, to wywoła się catch
            System.err.println("Błąd bazy: " + ioe);
        }
    }
}
