package eros_savogin.dao;

import eros_savogin.entities.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class StudentsDAO {
    // DAO (Data Access Object) è un Design Pattern, cioè serve per avere dei metodi già fatti per le operazioni di
    //save, delete, find ecc... così da non dover riformulare i metodi ogni qualvolta tu ne abbia bisogno, ma semplicemente richiamare i metodi al bisogno

    public final EntityManager em;

    public StudentsDAO(EntityManager em) { // sto costruendo un attributo e il suo relativo costruttore per passarmi
        this.em = em;                      // l'EntityManager dall' application, senza doverlo riconfigurare qui,
    }                                      // semplicemente creando un costruttore e richiamarlo nell' application e passargli come parametro l' entityManager appunto

    public void save(Student student) {
        // 1) ho bisogno di una transazione (viene fornita dall' Entity Manager)
        EntityTransaction transaction = em.getTransaction();

        // 2) inizio la transazione
        transaction.begin();

        // 3) aggiungo lo studente al Persistence Context (una specie di memoria di transazione tra java e il database)
        em.persist(student);

        // 4) concludo la transazione col salvataggio effettivo di una nuova riga nella tabella "studenti"
        transaction.commit();

        System.out.println("studente " + student.getName() + " aggiunto con successo!");
    }

    public Student findById(long id) {
        return em.find(Student.class, id);
    }

    public void findByIdAndDelete(long id) {
        // cerco lo studente nel db
        Student student = em.find(Student.class, id); // OPPURE UTILIZZIAMO IL METODO SOPRA : Student student = this.findById(id);

        if (student != null) {

            // lo studente (id passato come parametro) ESISTE, procediamo con l'eliminazione

            // ho bisogno di una transazione (mi vine fornita dall' Entity Manager)
            EntityTransaction transaction = em.getTransaction();

            // inizio la transazione
            transaction.begin();

            // rimuovo l'oggetto dal Persistence Context (in questo momento non è ancora rimosso dal db)
            em.remove(student);

            // 4) concludo la transazione con la rimozione effettiva di una riga dalla tabella "studenti"
            transaction.commit();

            System.out.println("lo studente " + student.getName() + " è stato eliminato correttamente dal database");

        } else {
            //lo studente (id passato come parametro) NON ESISTE
            System.out.println("lo studente con l'id " + id + " non è stato trovato");
        }


    }

}