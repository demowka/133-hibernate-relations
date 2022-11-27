package pl.sda.hibernaterelations.model;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;

public class usunOcene {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();

                System.out.println("Co usunac (studentów / oceny):");
                String wypisanaEncja = scanner.nextLine();


                if (wypisanaEncja.equalsIgnoreCase("ocena")) {
                    System.out.println("Podaj id oceny ktora chcesz usunac:");
                    String id = scanner.nextLine();
                    Long ocenaId = Long.parseLong(id);

                    // wypisz oceny
                    Ocena ocena = session.get(Ocena.class, ocenaId);
                    if (ocena != null) {
                        session.remove(ocena);
                    } else {
                        System.err.println("ocena nie istnieje");
                    }
                } else if (wypisanaEncja.equalsIgnoreCase("student")) {
                    System.out.println("Podaj id studenta ktorego chcesz usunac:");
                    String id = scanner.nextLine();
                    Long studentId = Long.parseLong(id);

                    Student student = session.get(Student.class, studentId);
                    if (student != null) {
                        if (!student.getOceny().isEmpty()) {
                            for (Ocena ocena : student.getOceny()) {
                                session.remove(ocena);
                            }
                        }


                        session.remove(student);
                    } else {
                        System.err.println("student nie istnieje");

                    }

                }
                transaction.commit();
            } catch (Exception ioe) {
                System.err.println("Błąd bazy:" + ioe);
            }
        }
}

