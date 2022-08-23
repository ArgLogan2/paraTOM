
package com.backendap.backendap.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    
    private String usuario;
    private String password;
    private boolean valido;

    public Users() {
    }

    public Users(Long id, String usuario, String password, boolean valido) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.valido = valido;
    }

   
    
}
