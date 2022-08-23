package com.backendap.backendap.controller;

import com.backendap.backendap.model.Users;
import com.backendap.backendap.service.IUsersService;
import com.backendap.backendap.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins={"http://localhost:4200"})
public class Controller {
    @Autowired IUsersService userServ;
    @Autowired JWTUtil jwtUltil;

    //-------------------------Usuarios------------------------------------------
    @PostMapping("/user/new")
    public void agregaUser(@RequestBody Users user ){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hash);
        userServ.crearUser(user);
    }
    @GetMapping("/user/ver")
    @ResponseBody    
    public List<Users> verUser(){
        return userServ.verUsers();
    }
    @DeleteMapping ("/user/delete/{id}")
    public void borrarUser ( @PathVariable Long id){
        userServ.borrarUser(id);
    }
    @PostMapping("/user/update")
    public void acualizaUser(@RequestBody Users user ){
        userServ.atualizarUser(user);
    }   
    @PostMapping("/user/ok")
    @ResponseBody    
    public String verUser(@RequestBody Users user){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        
        List<Users> usuarios =  userServ.verUsers();
       
        for( Users x:usuarios){
            if((argon2.verify(x.getPassword(), user.getPassword()))&&(x.getUsuario().equals(user.getUsuario())) ){
                String token = jwtUltil.create(user.getId().toString(), user.getUsuario());
                x.setValido(true);
                return token;
            }
   
        }
        return null;
    }
    
    
//------------------------------------------------------------------------- 
    
  
    
}
