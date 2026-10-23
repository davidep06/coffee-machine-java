package pagamento;

public class PagamentoContanti extends MetodoPagamento {

    private int importoInserito;

    //contructor
    public PagamentoContanti(int importoInserito) {
        this.importoInserito = importoInserito;
    }

    //richiamo pagamento.MetodoPagamento abstract
    @Override
    public boolean doPagamento(int importo) {
        if(importoInserito >= importo) {
            return true;
        }
        else {
            return false;
        }
    }
}
