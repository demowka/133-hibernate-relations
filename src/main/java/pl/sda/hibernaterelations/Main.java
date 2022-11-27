package pl.sda.hibernaterelations;

import pl.sda.hibernaterelations.model.HibernateUtil;

public class Main {
    public static void main(String[] args) {
        HibernateUtil.INSTANCE.getSessionFactory().openSession();

    }
}
