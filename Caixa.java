// src/service/Caixa.java
package Service;

public class Caixa {
    public static double calcularTroco(double valorTotal, double valorPago) {
        if (valorPago < valorTotal) {
            throw new IllegalArgumentException("Valor pago é insuficiente.");
        }
        return valorPago - valorTotal;
    }
}