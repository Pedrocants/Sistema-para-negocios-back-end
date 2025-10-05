package ar.com.chepps.Companny.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Contacto")
public class Contacto {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idContacto;
    private String telefono;
    private String email;

    public Contacto() {
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getIdContacto() {
        return idContacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }
}
