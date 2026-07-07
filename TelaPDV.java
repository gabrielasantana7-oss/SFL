import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPDV extends JFrame {

    // Componentes da interface
    private JTextField txtNome, txtPreco, txtEstoque, txtBuscarId;
    private JTable tabelaProdutos;
    private DefaultTableModel modeloTabela;
    private JButton btnAdicionar, btnVender;

    public TelaPDV() {
        // Configurações básicas da janela
        setTitle("Sistema de Frente de Loja - PDV");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- 1. PAINEL DE ENTRADA (Cadastro/Informações) ---
        JPanel painelEntrada = new JPanel(new GridLayout(4, 2, 5, 5));
        painelEntrada.setBorder(BorderFactory.createTitledBorder("Informações do Produto"));

        painelEntrada.add(new JLabel(" Nome do Produto:"));
        txtNome = new JTextField();
        painelEntrada.add(txtNome);

        painelEntrada.add(new JLabel(" Preço (R$):"));
        txtPreco = new JTextField();
        painelEntrada.add(txtPreco);

        painelEntrada.add(new JLabel(" Estoque Atual:"));
        txtEstoque = new JTextField();
        painelEntrada.add(txtEstoque);

        btnAdicionar = new JButton("Adicionar/Atualizar Produto");
        painelEntrada.add(btnAdicionar);

        // Espaço vazio para alinhar o layout
        painelEntrada.add(new JLabel(""));

        // --- 2. PAINEL CENTRAL (Tabela de Estoque) ---
        String[] colunas = {"ID", "Nome", "Preço", "Estoque"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaProdutos = new JTable(modeloTabela);
        JScrollPane scrollTabela = new JScrollPane(tabelaProdutos);

        // Mock de dados iniciais para testar
        modeloTabela.addRow(new Object[]{"1", "Arroz 5kg", "25.50", "40"});
        modeloTabela.addRow(new Object[]{"2", "Feijão 1kg", "8.90", "15"});
        modeloTabela.addRow(new Object[]{"3", "Óleo de Soja", "6.20", "20"});

        // --- 3. PAINEL INFERIOR (Operação de Venda) ---
        JPanel painelVenda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        painelVenda.setBorder(BorderFactory.createTitledBorder("Operação de Venda"));

        painelVenda.add(new JLabel("ID do Produto:"));
        txtBuscarId = new JTextField(5);
        painelVenda.add(txtBuscarId);

        btnVender = new JButton("Confirmar Venda (Baixar 1 no Estoque)");
        btnVender.setBackground(new Color(83, 189, 19)); // Verde
        btnVender.setForeground(Color.BLACK);
        painelVenda.add(btnVender);

        // --- Organizando os Painéis no JFrame ---
        add(painelEntrada, BorderLayout.NORTH);
        add(scrollTabela, BorderLayout.CENTER);
        add(painelVenda, BorderLayout.SOUTH);

        // --- 4. AÇÕES DOS BOTÕES (Listeners) ---

        // Ação de Adicionar Produto
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText();
                String preco = txtPreco.getText();
                String estoque = txtEstoque.getText();

                if (!nome.isEmpty() && !preco.isEmpty() && !estoque.isEmpty()) {
                    int proximoId = modeloTabela.getRowCount() + 1;
                    modeloTabela.addRow(new Object[]{proximoId, nome, preco, estoque});
                    limparCampos();
                } else {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos para adicionar!");
                }
            }
        });

        // Ação de Vender (Dar baixa no estoque)
        btnVender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idBusca = txtBuscarId.getText();
                boolean produtoEncontrado = false;

                for (int i = 0; i < modeloTabela.getRowCount(); i++) {
                    String idTabela = modeloTabela.getValueAt(i, 0).toString();

                    if (idTabela.equals(idBusca)) {
                        produtoEncontrado = true;
                        int estoqueAtual = Integer.parseInt(modeloTabela.getValueAt(i, 3).toString());

                        if (estoqueAtual > 0) {
                            estoqueAtual--;
                            modeloTabela.setValueAt(estoqueAtual, i, 3);
                            JOptionPane.showMessageDialog(null, "Venda realizada com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Erro: Produto sem estoque!");
                        }
                        break;
                    }
                }

                if (!produtoEncontrado) {
                    JOptionPane.showMessageDialog(null, "Produto com ID " + idBusca + " não encontrado.");
                }
                txtBuscarId.setText("");
            }
        });
    }

    private void limparCampos() {
        txtNome.setText("");
        txtPreco.setText("");
        txtEstoque.setText("");
    }

    // Método Principal para rodar a aplicação
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaPDV().setVisible(true);
        });
    }
}