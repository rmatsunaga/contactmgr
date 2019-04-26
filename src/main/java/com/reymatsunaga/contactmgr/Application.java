package com.reymatsunaga.contactmgr;

import com.reymatsunaga.contactmgr.model.Contact;
import com.reymatsunaga.contactmgr.model.Contact.ContactBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class Application {
    // Hold a reusable reference to a session factory
    private static final SessionFactory sessionFactory = buildSessionFactory();

    public static SessionFactory buildSessionFactory() {
        // Create a standard service registry object
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }
    public static void main(String[] args) {
        Contact contact = new ContactBuilder("Rey", "Matsunaga")
                .withEmail("reymats@gmail.com")
                .withPhone(2068054212L)
                .build();
        // Open a session
        Session session = sessionFactory.openSession();
        // open a transaction
        session.beginTransaction();
        // use session to save contact
        session.save(contact);
        // commit the transaction
        session.getTransaction().commit();
        // close the session
        session.close();
    }
}
