package pl.librontkrzysztof.validator;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import pl.librontkrzysztof.model.SavedTransaction;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class TransactionValidator {
    @NotNull
    private Integer source_wallet;

    @NotEmpty
    @Length(min = 28, max = 28, message = "To pole musi zawierać 28 znaków.")
    private String wallet_number;

    @NotNull
    @DecimalMin(value = "0.01", message = "Kwota musi być większa lub równa 1 grosz.")
    private BigDecimal value;

    @NotEmpty
    private String title;

    @NotEmpty
    @Length(min = 3, message = "To pole nie może być krótsze niż 3 znaki i dłuższe niż 61 znaków.")
    private String name;

    @Length(min = 0, max = 30, message = "To pole nie może być dłuższe niż 30 znaków.")
    private String address1;

    @Length(min = 0, max = 30, message = "To pole nie może być dłuższe niż 30 znaków.")
    private String address2;

    private boolean template;

    private String token;

    private String savedTransactionName;

    public boolean isTemplate() {
        return template;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getSource_wallet() {
        return source_wallet;
    }

    public void setSource_wallet(Integer source_wallet) {
        this.source_wallet = source_wallet;
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

    public boolean getTemplate() {
        return template;
    }

    public void setTemplate(boolean template) {
        this.template = template;
    }

    public String getSavedTransactionName() {
        return savedTransactionName;
    }

    public void setSavedTransactionName(String savedTransactionName) {
        this.savedTransactionName = savedTransactionName;
    }

    public TransactionValidator(Integer source_wallet, String wallet_number, BigDecimal value, String title, String name, String address1, String address2, boolean template, String token, String savedTransactionName) {
        this.source_wallet = source_wallet;
        this.wallet_number = wallet_number;
        this.value = value;
        this.title = title;
        this.name = name;
        this.address1 = address1;
        this.address2 = address2;
        this.template = template;
        this.token = token;
        this.savedTransactionName = savedTransactionName;
    }

    public TransactionValidator() {

    }

    public TransactionValidator(SavedTransaction savedTransaction){
        this.wallet_number = savedTransaction.getWallet_number();
        this.value = savedTransaction.getValue();
        this.title = savedTransaction.getTitle();
        this.name = savedTransaction.getName();
        this.address1 = savedTransaction.getAddress1();
        this.address2 = savedTransaction.getAddress2();
        this.savedTransactionName = savedTransaction.getTransactionname();
    }
}
