package pl.librontkrzysztof.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "helpdesk")
public class Helpdesk  implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotEmpty
    @Column(name = "title")
    private String title;

    @NotEmpty
    @Column(name = "question")
    private String question;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datee", nullable = false, columnDefinition="datee default CURRENT_TIMESTAMP")
    private Date datee = new Date();

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name="user_id", referencedColumnName="id")
    private User user;

    @Column(name = "answer")
    private String answer;

    public Helpdesk() {
    }

    public Helpdesk(String title, String question, Date datee, User user) {
        this.title = title;
        this.question = question;
        this.datee = datee;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Date getDatee() {
        return datee;
    }

    public void setDatee(Date datee) {
        this.datee = datee;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
