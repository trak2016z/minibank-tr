package pl.librontkrzysztof.model;

import org.hibernate.validator.constraints.NotEmpty;
import pl.librontkrzysztof.validator.TransactionValidator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "saved_transactions")
public class SavedTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotEmpty
    @Column(name = "wallet_number")
    private String wallet_number;

    @NotNull
    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "confidential", nullable = false, columnDefinition = "confidential default true")
    private boolean confidential;

    @NotEmpty
    @Column(name = "title")
    private String title;

    @NotEmpty
    @Column(name = "name")
    private String name;

    @Column(name = "address1", nullable = true)
    private String address1;

    @Column(name = "address2", nullable = true)
    private String address2;

    @Column(name = "transactionname", nullable = false)
    private String transactionname;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public SavedTransaction() {
    }

    public SavedTransaction(String wallet_number, BigDecimal value, boolean confidential, String title, String name, String address1, String address2, String transactionname, User user) {
        this.wallet_number = wallet_number;
        this.value = value;
        this.confidential = confidential;
        this.title = title;
        this.name = name;
        this.address1 = address1;
        this.address2 = address2;
        this.transactionname = transactionname;
        this.user = user;
    }

    public SavedTransaction(TransactionValidator transaction, User user){
        this.wallet_number = transaction.getWallet_number();
        this.value = transaction.getValue();
        this.confidential = true;
        this.title = transaction.getTitle();
        this.name = transaction.getName();
        this.address1 = transaction.getAddress1();
        this.address2 = transaction.getAddress2();
        this.user = user;
        this.transactionname = transaction.getSavedTransactionName();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWallet_number() {
        return wallet_number;
    }

    public void setWallet_number(String wallet_number) {
        this.wallet_number = wallet_number;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public boolean isConfidential() {
        return confidential;
    }

    public void setConfidential(boolean confidential) {
        this.confidential = confidential;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTransactionname() {
        return transactionname;
    }

    public void setTransactionname(String transactionname) {
        this.transactionname = transactionname;
    }
}
