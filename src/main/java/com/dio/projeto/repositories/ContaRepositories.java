package com.dio.projeto.repositories;

import com.dio.projeto.entities.ContaServiceCorrente;
import com.dio.projeto.entities.ContaServicePoupanca;

public interface ContaRepositories {

     void sacar(ContaServiceCorrente contaCorrente, ContaServicePoupanca contaPoupanca);

     void depositar(ContaServiceCorrente contaCorrente, ContaServicePoupanca contaPoupanca);

     void extrato();

     void transferir(ContaServiceCorrente contaCorrente, ContaServicePoupanca contaPoupanca);
}
