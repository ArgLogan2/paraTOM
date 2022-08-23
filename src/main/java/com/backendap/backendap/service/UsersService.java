package com.backendap.backendap.service;

import com.backendap.backendap.model.Users;
import com.backendap.backendap.repository.UsersRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements IUsersService {
    @Autowired
    public UsersRepository userRepo;
    
    @Override
    public List<Users> verUsers() {
        return userRepo.findAll();
    }

    @Override
    public void crearUser(Users user) {
        userRepo.save(user);
    }

    @Override
    public void borrarUser(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public Users buscarUser(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public void atualizarUser(Users user) {
        userRepo.save(user);
    }
    
}
