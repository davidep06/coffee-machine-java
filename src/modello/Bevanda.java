package modello;

public class Bevanda {

    private String nome;
    private int prezzo;
    private int acqua;
    private int chicchi;

    //contructor
    public Bevanda(String nome, int prezzo, int acqua, int chicchi) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.acqua = acqua;
        this.chicchi = chicchi;
    }

    //metodi
    public String getNome() {
        return nome;
    }

    public int getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAcqua() {

        return acqua;
    }

    public int getChicchi() {

        return acqua;
    }

}
