/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import java.io.IOException;
import java.util.List;
import modelo.Cliente;

/**
 *
 * @author gustavo
 */
public interface ICliente {

    public void salvar(Cliente cliente) throws IOException;

    public List<Cliente> listar() throws IOException;

    public void atualizar(Cliente clienteAlterado) throws IOException;

    public void excluir(int id) throws IOException;
}
