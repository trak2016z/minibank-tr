package pl.librontkrzysztof.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "source_wallet_id", referencedColumnName = "id")
    private Wallet source_wallet;

    @NotEmpty
    @Column(name = "wallet_number")
    private String wallet_number;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date", nullable = false, columnDefinition = "order_date default CURRENT_TIMESTAMP")
    private Date order_date = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "confirm_date", nullable = true)
    private Date confirm_date;

    @NotNull
    @Column(name = "value", precision = 8, scale = 2)
    private BigDecimal value;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private pl.librontkrzysztof.model.Status status;

    @Column(name = "confirmed", nullable = false, columnDefinition = "confirmed default false")
    private boolean confirmed;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "token_id", referencedColumnName = "id")
    private Token token;

    @NotEmpty
    @Column(name = "title")
    private String title;

    @NotEmpty
    @Column(name = "name")
    @Size(min = 3, max = 61, message = "Imię i nazwisko nie może być krótsze niż 2 znaki i dłuższe niż 61.")
    private String name;

    @Column(name = "address1", nullable = true)
    private String address1;

    @Column(name = "address2", nullable = true)
    private String address2;

    public Transaction() {
    }

    public Transaction(Wallet source_wallet, String wallet_number, Date order_date, Date confirm_date, BigDecimal value, Status status, boolean confirmed, Token token, String title, String name, String address1, String address2) {
        this.source_wallet = source_wallet;
        this.wallet_number = wallet_number;
        this.order_date = order_date;
        this.confirm_date = confirm_date;
        this.value = value;
        this.status = status;
        this.confirmed = confirmed;
        this.token = token;
        this.title = title;
        this.name = name;
        this.address1 = address1;
        this.address2 = address2;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Wallet getSource_wallet() {
        return source_wallet;
    }

    public void setSource_wallet(Wallet source_wallet) {
        this.source_wallet = source_wallet;
    }

    public String getWallet_number() {
        return wallet_number;
    }

    public void setWallet_number(String wallet_number) {
        this.wallet_number = wallet_number;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public Date getConfirm_date() {
        return confirm_date;
    }

    public void setConfirm_date(Date confirm_date) {
        this.confirm_date = confirm_date;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
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
}
