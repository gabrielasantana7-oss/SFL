import View.TelaLogin;
import View.TelaPDV;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            //Instancia a Tela de login
            TelaLogin Login = new TelaLogin(null);
            login.setVisible(true);

            if (login.isAutenticado()) {
                // Se o login estiver correto (gabriel alves / 24042006), abre o PDV
                new TelaPDV().setVisible(true);
            } else {
                System.exit(0);
            }
        });
    }
}