package com.projeto.projetocrud;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurferRepository extends JpaRepository<Surfer,Long> {

}