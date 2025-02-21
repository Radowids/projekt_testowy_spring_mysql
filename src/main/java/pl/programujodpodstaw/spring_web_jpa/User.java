package pl.programujodpodstaw.spring_web_jpa;

import jakarta.persistence.*;

import java.util.List;

@Entity //oznaczenie klasy User jako encja
public class User {
    @Id //ta adnotacja oznacza, że pierwszy okeśloby parametr (tu Integer id) będzie tzw. kluczem podstawowym (primary key)
    @GeneratedValue(strategy = GenerationType.IDENTITY)//ta adnotacja informuje Hibernate o sposobie generowania wartości dla pola oznaczonego @Id
                                        //(pozwala na automatyczne generowanie wartości klucza głównego, dostosowane do używanej bazy danych
                                        //(gdyby było AUTO), a tu jest IDENTITY co po prostu autoinkrementuje nam ten parametr (tu Integer id)
    private Integer id;
    private String login;
    private String displayName;
    private Integer yearOfBirth;

//    Relacja jeden do wielu (one to many) (jeden user może mieć wiele postów przypisanych)
    @OneToMany(mappedBy = "user")
    List<Post> posts;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
