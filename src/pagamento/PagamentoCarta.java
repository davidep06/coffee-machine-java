package pagamento;

public class PagamentoCarta extends MetodoPagamento {

    private String numeroCarta;
    private SistemaBancario banca;

    //contructor
    public PagamentoCarta(String numeroCarta) {
        this.numeroCarta = numeroCarta;
        this.banca = new SistemaBancario();
    }

    @Override
    public boolean doPagamento(int importo) {
        return banca.autorizza(numeroCarta, importo);
    }
}
