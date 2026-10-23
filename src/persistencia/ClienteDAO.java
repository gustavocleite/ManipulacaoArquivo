/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;
import modelo.Endereco;

/**
 *
 * @author gustavo
 */
public class ClienteDAO implements ICliente {

    private String arquivo = "";

    public ClienteDAO() {
        arquivo = ".\\src\\dados\\clientesNewNew.csv";
    }

    @Override
    public void salvar(Cliente cliente) throws IOException {

        int proximoId = gerarProximoId();

        cliente.setId(proximoId);

        FileWriter fw = new FileWriter(arquivo, true);
        PrintWriter pw = new PrintWriter(fw);

        pw.println(
                cliente.getId() + ";"
                + cliente.getNome() + ";"
                + cliente.getTelefone() + ";"
                + cliente.getCpf() + ";"
                + cliente.getEndereco().getLogradouro() + ";"
                + cliente.getEndereco().getNumero() + ";"
                + cliente.getEndereco().getBairro() + ";"
                + cliente.getEndereco().getCidade() + ";"
                + cliente.getEndereco().getUf()
        );

        pw.close();
    }

    @Override
    public List<Cliente> listar() {

        List<Cliente> clientes = new ArrayList<>();

        try {
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);

            String linha;

            while ((linha = br.readLine()) != null) {

                String[] dados = linha.split(";");

                Endereco endereco = new Endereco(
                        dados[4],
                        Integer.parseInt(dados[5]),
                        dados[6],
                        dados[7],
                        dados[8]
                );

                Cliente cliente = new Cliente(
                        Integer.parseInt(dados[0]),
                        dados[1],
                        dados[2],
                        dados[3],
                        endereco
                );

                clientes.add(cliente);
            }

            br.close();

        } catch (IOException e) {
            System.out.println("DAO : Erro ao ler: " + e.getMessage());
        }

        return clientes;
    }

    @Override
    public void atualizar(Cliente clienteAlterado) {

        List<Cliente> clientes = listar();

        for (Cliente cliente : clientes) {

            if (cliente.getId() == clienteAlterado.getId()) {
                cliente.setNome(clienteAlterado.getNome());
                cliente.setTelefone(clienteAlterado.getTelefone());
                cliente.setEndereco(clienteAlterado.getEndereco());
            }
        }

        regravar(clientes);
    }

    @Override
    public void excluir(int id) {

        List<Cliente> clientes = listar();

        clientes.removeIf(cliente -> cliente.getId() == id);

        regravar(clientes);
    }

    private void regravar(List<Cliente> clientes) {

        try {
            FileWriter fw = new FileWriter(arquivo);
            PrintWriter pw = new PrintWriter(fw);

            for (Cliente cliente : clientes) {

                pw.println(
                        cliente.getId() + ";"
                        + cliente.getNome() + ";"
                        + cliente.getTelefone() + ";"
                        + cliente.getCpf() + ";"
                        + cliente.getEndereco().getLogradouro() + ";"
                        + cliente.getEndereco().getNumero() + ";"
                        + cliente.getEndereco().getBairro() + ";"
                        + cliente.getEndereco().getCidade() + ";"
                        + cliente.getEndereco().getUf()
                );
            }

            pw.close();

        } catch (IOException e) {
            System.out.println("DAO : Erro ao regravar: " + e.getMessage());
        }
    }

    private int gerarProximoId() {

        List<Cliente> clientes = listar();

        int maiorId = 0;

        for (Cliente cliente : clientes) {

            if (cliente.getId() > maiorId) {
                maiorId = cliente.getId();
            }
        }

        return maiorId + 1;
    }
}
