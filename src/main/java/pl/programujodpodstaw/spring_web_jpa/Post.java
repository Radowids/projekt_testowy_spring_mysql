package pl.programujodpodstaw.spring_web_jpa;

import jakarta.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String body;

    @ManyToOne  //adnotacja mówiąca, że parametr user ma relację wiele do jednego (wiele postów może być przypisanych do jednego usera)
    @JoinColumn(name = "user_id") //adnotacja mówiąca jak nazywa się kolumna łącząca schemat user z post
    private User user;

    public Post() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
