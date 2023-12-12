package eros_savogin;

import eros_savogin.dao.StudentsDAO;
import eros_savogin.entities.Student;
import eros_savogin.entities.StudentType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("progetto_prova_d2");
    // EntityManagerFactory è l'oggetto che mi consente di creare gli entity manager
    // come parametro dell' EntityManagerFactory devo mettere il name del persistence-unit utilizzato nel file xml


    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager(); // Entity Manager è responsabile della gestione delle interazioni col DB
        StudentsDAO sd = new StudentsDAO(em);

        Student aldo = new Student("Aldo", "Baglio", StudentType.FULLSTACK);
        Student giacomo = new Student("Giacomo", "Poretti", StudentType.BACKEND);
        Student giovanni = new Student("Giovanni", "Storti", StudentType.FRONTEND);
        /*aldo.setName("Toni");
        aldo.getName();
        aldo.setStudentType(StudentType.BACKEND);*/

        // invocazione metodo save

        /*sd.save(aldo);
        sd.save(giovanni);
        sd.save(giacomo);*/

        // li ho commentati se no ad ogni avvio dell'applicazione mi vengono generati altri 3 studenti aldo/giovanni/giacomo


        //invocazione metodo findById
        sd.findById(4);

        long id = 5;
        Student studenteCercato = sd.findById(id);

        if (studenteCercato != null) {
            System.out.println(studenteCercato);
        } else {
            System.out.println("studente con id " + id + " non trovato" );
        }

        //invocazione metodo findByIdAndDelete

        sd.findByIdAndDelete(8);

        //terminato il programma è buona prassi chiudere entitymanager e entitymanagerfactory
        em.close();
        emf.close();
    }

}
