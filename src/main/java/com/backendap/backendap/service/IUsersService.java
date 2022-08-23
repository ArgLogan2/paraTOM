package com.backendap.backendap.service;

import com.backendap.backendap.model.Users;
import java.util.List;

public interface IUsersService {
    public List<Users> verUsers();
    
    public void crearUser(Users user);
    
    public void  borrarUser (Long id);
    
    public Users buscarUser  (Long id);
    
    public void atualizarUser(Users user);
    
}
