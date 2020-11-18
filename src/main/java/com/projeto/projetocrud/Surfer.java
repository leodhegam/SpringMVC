package com.projeto.projetocrud;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Surfer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

   @Size(min = 3,max = 60) @NotBlank(message = ApiPosts.TAM_ERROR)
    String nome;
    @Email @NotBlank(message = ApiPosts.EMAIL_ERROR)
    String email;
    @Min(value = 1950,message = ApiPosts.YEARS_ERROR)
    Integer anoNascimento;
    @NotBlank(message = ApiPosts.PEAK_ERROR)
    String pico ;
}