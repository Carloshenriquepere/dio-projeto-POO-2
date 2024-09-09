package com.dio.projeto.entities;

import com.dio.projeto.service.ContaService;
import lombok.Data;

import java.util.List;

@Data
public class Banco {

    private String nome;
    private List<ContaService> conta;
}
