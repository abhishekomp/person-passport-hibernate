package org.aom.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.aom.entities.Passport;
import org.aom.entities.Person;
import org.aom.utils.HibernateUtils;

import java.util.List;

/**
 * @author  : Abhishek
 * @since   : 2024-04-10, Wednesday
 **/
public class PersonPassportDAO {

    private final EntityManagerFactory emf;

    public PersonPassportDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void createNewPersonWithPassport(){
        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();

            Passport passport = Passport.builder().passportNumber("MAR1234").build();
            Person person = Person.builder().name("Maria").passport(passport).build();
            passport.setPerson(person); //this is necessary when we are maintaining bidirectional one to one mapping.

            em.persist(person); //Due to the usage of cascade = persist on @OneToOne mapping, the passport associated with person will also be marked for persist in db.
            //em.persist(passport);

            em.getTransaction().commit();

            System.out.println("person = " + person);
        } finally {
            em.close();
        }
    }

    public void getPersonDetailsUsingPassportNumber(String passportNumber){
        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();

            TypedQuery<Person> query = em.createQuery("SELECT p from Person p where p.passport.passportNumber = :passNumber", Person.class);
            query.setParameter("passNumber", passportNumber);
            List<Person> personList = query.getResultList();
            personList.forEach(System.out::println);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    //this was resulting in StackOverflowError
    public void getPassportDetailsUsingPersonName(String personName){
        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();

            TypedQuery<Passport> query = em.createQuery("SELECT p from Passport p where p.person.name = :personName", Passport.class);
            query.setParameter("personName", personName);
            Passport passport = query.getSingleResult();
            System.out.println("passport = " + passport);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    public void removePersonAndPassport(int personId){
        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();

            Person person = em.find(Person.class, personId);
            em.remove(person);  //Due to the usage of cascade = remove on @OneToOne mapping, the passport associated with person will also be marked for removal from db.

            em.getTransaction().commit();
            System.out.println("Removed person and passport. Check Db.");
        } finally {
            em.close();
        }
    }



    public static void main(String[] args) {
        PersonPassportDAO dao = new PersonPassportDAO(HibernateUtils.createEntityManagerFactory());
        //dao.createNewPersonWithPassport();
        //dao.getPersonDetailsUsingPassportNumber("MAR1234");
        //dao.getPassportDetailsUsingPersonName("Maria");
        dao.removePersonAndPassport(2);
    }


//    public void dummyMethod(){
//        EntityManager em = emf.createEntityManager();
//
//        try{
//            em.getTransaction().begin();
//
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//    }
}
