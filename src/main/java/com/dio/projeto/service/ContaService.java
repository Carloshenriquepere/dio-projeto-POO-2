package com.dio.projeto.service;

import com.dio.projeto.entities.Banco;
import com.dio.projeto.entities.Cliente;
import com.dio.projeto.entities.ContaServiceCorrente;
import com.dio.projeto.entities.ContaServicePoupanca;
import com.dio.projeto.repositories.ContaRepositories;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

import static java.lang.System.out;

@Slf4j
@Data
public abstract class ContaService implements ContaRepositories {

    Scanner sc = new Scanner(System.in);

    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;
    protected Banco banco;


    private static final int AGENCIA = 1;
    private static int SEQUENCIAL = 1;

    public ContaService(Cliente cliente, Banco banco) {

        this.agencia = AGENCIA;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
        this.banco = banco;
    }

    private boolean validarValorPositivo(double valor) {
        if (valor > 0) {
            return true;
        } else {
            out.println("Valor inválido. Insira um valor positivo.");
            out.println("-----------");
            return false;
        }
    }

    private ContaService escolherConta(ContaServiceCorrente contaCorrente, ContaServicePoupanca contaPoupanca) {
        out.println("Selecione uma conta ::: (1 - Conta Corrente, 2 - Conta Poupança)");
        int tipoConta = sc.nextInt();

        switch (tipoConta) {
            case 1:
                out.println("Conta Corrente selecionada.");
                return contaCorrente;
            case 2:
                out.println("Conta Poupança selecionada.");
                return contaPoupanca;
            default:
                out.println("Opção inválida.");
                return null;
        }
    }

    @Override
    public void depositar(ContaServiceCorrente contaCorrente, ContaServicePoupanca contaPoupanca) {
        out.println("Você deseja depositar algum valor? (s/n)");
        String resposta = sc.next().trim().toLowerCase();

        if (resposta.equals("s")) {
            out.println("Digite o valor do depósito:");
            double valor = sc.nextDouble();

            if (validarValorPositivo(valor)) {
                ContaService contaDestino = escolherConta(contaCorrente, contaPoupanca);
                if (contaDestino != null) {
                    contaDestino.saldo += valor;
                    out.printf("Depósito realizado com sucesso! Saldo atual: %.2f%n", contaDestino.saldo);
                    out.println("----------");
                }
            }
        } else {
            out.println("Volte sempre!");
            out.println("----------");
        }
    }

    @Override
    public void sacar(ContaServiceCorrente contaCorrente, ContaServicePoupanca contaPoupanca) {
        out.println("Você deseja sacar algum valor? (s/n)");
        String response = sc.next().trim().toLowerCase();

        if (response.equals("s")) {
            ContaService contaOrigem = escolherConta(contaCorrente, contaPoupanca);

            if (contaOrigem != null) {
                out.println("Digite o valor do saque:");
                double valor = sc.nextDouble();

                if (validarValorPositivo(valor) && contaOrigem.saldo >= valor) {
                    contaOrigem.saldo -= valor;
                    out.printf("Saque realizado com sucesso! Novo saldo: %.2f%n", contaOrigem.saldo);
                    out.println("----------------------");
                } else {
                    out.println("Saldo insuficiente ou valor inválido.");
                    out.println("----------------------");
                }
            } else {
                out.println("Conta não selecionada.");
                out.println("----------------------");
            }
        } else {
            out.println("Volte sempre.");
            out.println("-----------");
        }
    }

    @Override
    public void transferir(ContaServiceCorrente contaCorrente, ContaServicePoupanca contaPoupanca) {
        out.println("Você deseja transferir algum valor? (s/n)");
        String resposta = sc.next().trim().toLowerCase();

        if (resposta.equals("s")) {
            out.println("Qual origem!");
            ContaService contaOrigem = escolherConta(contaCorrente, contaPoupanca);

            if (contaOrigem != null) {
                out.println("Qual destino!");
                ContaService contaDestino = escolherConta(contaCorrente, contaPoupanca);

                if (contaDestino != null && contaOrigem != contaDestino) {
                    out.println("Digite o valor da transferência:");
                    double valor = sc.nextDouble();

                    if (validarValorPositivo(valor) && contaOrigem.saldo >= valor) {
                        contaOrigem.saldo -= valor;
                        contaDestino.saldo += valor;
                        out.printf("Transferência realizada com sucesso! Saldo da origem: %.2f%n", contaOrigem.saldo);
                        out.printf("Saldo da destino: %.2f%n", contaDestino.saldo);
                        out.println("----------------------");
                    } else {
                        out.println("Saldo insuficiente ou valor inválido.");
                        out.println("----------------------");
                    }
                } else {
                    out.println("Conta de origem e destino devem ser diferentes.");
                    out.println("----------------------");
                }
            } else {
                out.println("Conta de origem não selecionada.");
                out.println("----------------------");
            }
        } else {
            out.println("Volte sempre!");
            out.println("----------------------");
        }
    }

    protected void infosComuns() {
        out.printf("Titular ::: %s%n", this.cliente.getNome());
        out.printf("Banco ::: %s%n", this.banco.getNome());
        out.printf("Agencia ::: %d%n", agencia);
        out.printf("Número ::: %d%n", numero);
        out.printf("Saldo ::: %.2f%n", saldo);
    }
}
