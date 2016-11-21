package pl.librontkrzysztof.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.crypto.codec.Base64;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;


@Entity
@Table(name = "image_token")
public class ImageToken {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotEmpty
    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty
    @Column(name = "file")
    private byte[] data;

    public byte[] getData() {
        return data;
    }

    public String getImage() {
        String base64Encoded = null;
        byte[] encodeBase64 = Base64.encode(this.data);
        try {
            base64Encoded = new String(encodeBase64, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return base64Encoded;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public ImageToken() {
    }

    public ImageToken(String name, byte[] data) {
        this.name = name;
        this.data = data;
    }


}
