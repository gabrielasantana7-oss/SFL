import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLogin extends JFrame {

    // Componentes da interface
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnEntrar;
    private JButton btnSair;

    public TelaLogin() {
        // Configurações básicas da janela
        setTitle("Frente de Loja - Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a tela
        setResizable(false);

        // Inicializa os componentes
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // Criando o painel principal com margens (Padding)
        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painel.setLayout(new GridLayout(5, 1, 10, 10));

        // Campo de Usuário
        painel.add(new JLabel("Usuário:"));
        txtUsuario = new JTextField();
        painel.add(txtUsuario);

        // Campo de Senha
        painel.add(new JLabel("Senha:"));
        txtSenha = new JPasswordField();
        painel.add(txtSenha);

        // Painel para os botões (ficaram lado a lado)
        JPanel painelBotoes = new JPanel(new GridLayout(1, 2, 10, 10));
        btnEntrar = new JButton("Entrar");
        btnSair = new JButton("Sair");

        painelBotoes.add(btnEntrar);
        painelBotoes.add(btnSair);
        painel.add(painelBotoes);

        // Adiciona o painel à janela
        add(painel);

        // Eventos dos botões
        configurarEventos();
    }

    private void configurarEventos() {
        // Ação do botão Sair
        btnSair.addActionListener(e -> System.exit(0));

        // Ação do botão Entrar
        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticarUsuario();
            }
        });
    }

    private void autenticarUsuario() {
        String usuario = txtUsuario.getText();
        String senha = new String(txtSenha.getPassword());

        // VALIDAÇÃO BÁSICA (Substitua pela sua lógica de Banco de Dados)
        if (usuario.equals("gabriel alves") && senha.equals("24042006")) {
            JOptionPane.showMessageDialog(this, "Login efetuado com sucesso!");

            this.dispose(); // Fecha o login
            new TelaPDV().setVisible(true); // Abre o PDV
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos.", "Erro de Autenticação", JOptionPane.ERROR_MESSAGE);
            txtSenha.setText("");
            txtUsuario.requestFocus();
        }
    }

    public static void main(String[] args) {
        // Define o visual do sistema para o padrão do sistema operacional (Look and Feel)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Executa a tela
        SwingUtilities.invokeLater(() -> {
            new TelaLogin().setVisible(true);
        });
    }
}