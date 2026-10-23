package modello;

import pagamento.MetodoPagamento;
import java.util.List;
import java.util.ArrayList;

public class MacchinaCaffe {

    private int acqua;
    private int chicchi;
    private int incasso;
    private List<Bevanda> bevande;

    //constructor

    public MacchinaCaffe(int acqua, int chicchi) {
        this.acqua = acqua;
        this.chicchi = chicchi;
        this.incasso = 0;
        this.bevande = new ArrayList<>();
    }

    //metodi
    public boolean acquista(Bevanda b, MetodoPagamento metodo) {
        if(!verificaRisorse(b)) {
            System.out.println("Errore: Risorse insufficienti");
            return false;
        }
        else {
            if(!metodo.doPagamento(b.getPrezzo())) {
                System.out.println("Errore: Pagamento non riuscito");
                return false;
            }
            else {
                preparaCaffe(b);
                acqua -= b.getAcqua();
                chicchi -= b.getChicchi();
                incasso += b.getPrezzo();
                return true;
            }
        }
    }

    public void ricarica(int acqua, int chicchi) {
        this.acqua += acqua;
        this.chicchi += chicchi;
    }

    public int getIncasso() {
        return incasso;
    }

    public void svuotaIncasso() {

        this.incasso = 0;
    };

    public List<Bevanda> getBevande() {

        return bevande;
    }

    private boolean verificaRisorse(Bevanda b) {
        if(acqua >= b.getAcqua() && chicchi >= b.getChicchi()) return true;
        else return false;
    }

    private void preparaCaffe(Bevanda b) {
        System.out.println("Scaldo l'acqua...");
        System.out.println("Macino i chicchi...");
        System.out.println("Preparo il caffe...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Erogo " + b.getNome());
    }

    public void aggiungiBevanda(Bevanda b) {

        bevande.add(b);
    }

    public int getChicchi() {
        return chicchi;
    }

    public int getAcqua() {
        return acqua;
    }
}
