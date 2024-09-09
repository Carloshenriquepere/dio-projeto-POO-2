package com.dio.projeto;

import com.dio.projeto.entities.Banco;
import com.dio.projeto.entities.Cliente;
import com.dio.projeto.entities.ContaServiceCorrente;
import com.dio.projeto.entities.ContaServicePoupanca;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ProjetoApplication {

    public static void main(String[] args) {

        SpringApplication.run(ProjetoApplication.class, args);

        Scanner sc = new Scanner(System.in);
        Cliente cliente = new Cliente();
        Banco banco = new Banco();

        System.out.println("Entre com seu nome: ");
        cliente.setNome(sc.next());

        System.out.println("Entre com nome do banco: ");
        banco.setNome(sc.next());

        ContaServiceCorrente cc = new ContaServiceCorrente(cliente, banco);
        ContaServicePoupanca cp = new ContaServicePoupanca(cliente, banco);

        cc.depositar(cc, cp);
        cc.sacar(cc, cp);
        cc.transferir(cc, cp);

        cc.extrato();
        System.out.println("-----------------");
        cp.extrato();


    }

}
