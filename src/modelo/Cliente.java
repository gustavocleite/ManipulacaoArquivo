/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import util.CpfUtil;

/**
 *
 * @author gustavo
 */
public class Cliente {

    private int id;
    private String nome;
    private String telefone;
    private Endereco endereco;
    private String cpf;

    public Cliente(int id, String nome, String telefone, String cpf, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        setCpf(cpf);
    }

    public Cliente(String nome, String telefone, String cpf, Endereco endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        setCpf(cpf);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {


        if (!CpfUtil.validarCpf(cpf)) {
            throw new IllegalArgumentException("CPF inválido!");
        }
        this.cpf = cpf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
