// src/controller/VendaController.java
package controller;

import model.ItemVenda;
import model.Produto;
import model.Venda;
import java.util.HashMap;
import java.util.Map;

public class VendaController {
    private Venda vendaAtual;
    private Map<String, Produto> bancoDadosProdutos; // Simulando um banco de dados em memória

    public VendaController() {
        this.vendaAtual = new Venda();
        this.bancoDadosProdutos = new HashMap<>();
        carregarProdutosFicticios();
    }


    public ItemVenda biparProduto(String codigo, int quantidade) {
        Produto produto = bancoDadosProdutos.get(codigo);
        if (produto != null && produto.getEstoque() >= quantidade) {
            ItemVenda item = new ItemVenda(produto, quantidade);
            vendaAtual.adicionarItem(item);
            produto.setEstoque(produto.getEstoque() - quantidade); // Baixa no estoque
            return item;
        }
        return null;
    }

    public double getTotalVenda() {
        return vendaAtual.getValorTotal();
    }

    public void novaVenda() {
        this.vendaAtual = new Venda();
    }
}