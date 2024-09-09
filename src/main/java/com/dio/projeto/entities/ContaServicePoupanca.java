package com.dio.projeto.entities;

import com.dio.projeto.service.ContaService;

import static java.lang.System.out;

public class ContaServicePoupanca extends ContaService {

    public ContaServicePoupanca(Cliente cliente, Banco banco) {
        super(cliente, banco);
    }

    @Override
    public void extrato() {
        out.println("Extrato Conta Poupança ::: ");
        super.infosComuns();
    }
}
