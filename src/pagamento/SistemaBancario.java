package pagamento;

public class SistemaBancario {

    public boolean autorizza(String numeroCarta, int importo) {
        return numeroCarta.length() > 0;
    }
}
