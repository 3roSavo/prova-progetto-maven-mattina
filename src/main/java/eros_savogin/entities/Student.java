package eros_savogin.entities;

import javax.persistence.*;

@Entity

@Table(name = "studenti") //può essere omesso il Table, in quel caso il nome della tabella sarà uguale a quello della classe "Student"
public class Student {
    @Id // OBBLIGATORIO @ID, indica che sarà la nostra chiave primaria (PK)
    @GeneratedValue // utilizzato per fare gestire gli id in automatico al database
    private long id; // se uso long con @generatedValue mi ritroverò con un bigserial nel database (long id nel database corrisponde a bigserial, int invece diventa serial) non son sicuro delle parentesi
    @Column(name = "nome") // anche Column non è obbligatorio, come Table può essere omesso, il nome della colonna sarà preso dall'attributo stesso
    private String name;
    @Column(name = "cognome")
    private String surname;
    @Column(name = "tipoStudente")
    @Enumerated(EnumType.STRING)
    private StudentType studentType;

    //COSTRUTTORI
    public Student(String name, String surname, StudentType studentType) {
        this.name = name;
        this.surname = surname;
        this.studentType = studentType;
    }
    public Student() { // mi serve un costruttore vuoto per il metodo findById,
    }

    //GETTER
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public StudentType getStudentType() {
        return studentType;
    }
    //SETTER
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setStudentType(StudentType studentType) {
        this.studentType = studentType;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", studentType=" + studentType +
                '}';
    }
}