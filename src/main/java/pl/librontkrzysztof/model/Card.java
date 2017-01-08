package pl.librontkrzysztof.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    @OrderBy("id")
    private Set<Token> tokens;

    @Column(name = "active", nullable = false, columnDefinition = "active default false")
    private boolean active;

    public Card(User user, Set<Token> tokens, boolean active) {
        this.user = user;
        this.tokens = tokens;
        this.active = active;
    }

    public Card() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Token> getTokens() {
        return tokens;
    }

    public void setTokens(Set<Token> tokens) {
        this.tokens = tokens;
    }

    public int getActive(){
        int i = 0;
        for(Token token : this.tokens){
            if(!token.isUsed()){
                i++;
            }
        }
        return i;
    }

    public Token getNewToken(){
        int i = 0;
        for(Token token : this.tokens){
            if(!token.isUsed()){
                return token;
            }
        }
        return null;
    }
}
