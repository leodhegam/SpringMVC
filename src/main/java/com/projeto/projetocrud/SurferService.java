package com.projeto.projetocrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SurferService {

     private SurferRepository repository;

    @Autowired
    public void setRepository(SurferRepository repository) {
        this.repository = repository;
    }

    public List<Surfer> findAll () {
        return repository.findAll();
    }

    public void save(Surfer s){
        repository.save(s);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Surfer getById(Long id) {
        return repository.getOne(id);
    }

}
