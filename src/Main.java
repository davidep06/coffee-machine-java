import modello.Bevanda;
import modello.MacchinaCaffe;
import pagamento.MetodoPagamento;
import pagamento.PagamentoCarta;
import pagamento.PagamentoContanti;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int scelta = -1;

        Scanner in = new Scanner(System.in);

        MacchinaCaffe macchina = new MacchinaCaffe(1000, 500);
        macchina.aggiungiBevanda(new Bevanda("Espresso", 100, 30, 5));
        macchina.aggiungiBevanda(new Bevanda("Lungo", 150, 50, 7));
        macchina.aggiungiBevanda(new Bevanda("Americano", 120, 70, 10));
        macchina.aggiungiBevanda(new Bevanda("Cold Brew", 200,100, 12));
        macchina.aggiungiBevanda(new Bevanda("Ristretto", 50, 20, 5));

        while(scelta != 0)
        {
            System.out.println("\n\t\t Macchinetta del caffe \t\t");
            System.out.println("1. Cliente");
            System.out.println("2. Amministratore");
            System.out.println("0. Esci");
            scelta = in.nextInt();

            if(scelta == 1) {
                menuCliente(macchina,in);
            }
            else if(scelta == 2) {
               menuAmministratore(macchina,in);
           }
        }
    }

    private static void menuCliente(MacchinaCaffe macchina, Scanner in) {

        for(int i = 0; i < macchina.getBevande().size(); i++)
        {
            Bevanda b = macchina.getBevande().get(i);
            System.out.println((i + 1) + ". " + b.getNome() + " - " + b.getPrezzo() + " centesimi");
        }

        System.out.println("\nChe bevanda scegli?");
        System.out.println("Scelgo: ");
        int scelta = in.nextInt();
        if (scelta >= 1 && scelta <= macchina.getBevande().size()) {
            Bevanda b = macchina.getBevande().get(scelta - 1);

            System.out.println("Come desideri pagare? \n1. Contanti \n2. Carta");
            int sceltaPag = in.nextInt();
            MetodoPagamento metodo;

            if (sceltaPag == 1) {
                System.out.println("Quanti centesimi inserisci?");
                int importo = in.nextInt();
                metodo = new PagamentoContanti(importo);
            } else {
                System.out.println("Inserire numero carta: ");
                String numero = in.next();
                metodo = new PagamentoCarta(numero);
            }
            if (macchina.acquista(b, metodo)) {
                System.out.println("\nLa tua bevanda è pronta!");
            }
        }
        else System.out.println("modello.Bevanda non trovata");
    }

    private static void menuAmministratore(MacchinaCaffe macchina, Scanner in) {

        int scelta = -1;
        while(scelta != 0) {

            System.out.println("Cosa desideri fare? \n1. Verificare risorse \n2. Visualizzare incasso \n3. Cambiare prezzo \n4. Modifica nome caffe \n5. Svuotare la macchinetta \n6. Ricarica materie prime \n0. Indietro");

            scelta = in.nextInt();
            switch (scelta) {

                case 0: break;

                case 1: {
                    System.out.println("Acqua: " + macchina.getAcqua());
                    System.out.println("Chicchi: " + macchina.getChicchi());
                }
                break;

                case 2: {
                    System.out.println("Incasso: " + macchina.getIncasso());
                }
                break;

                case 3: {
                    for (int i = 0; i < macchina.getBevande().size(); i++) {
                        Bevanda b = macchina.getBevande().get(i);
                        System.out.println((i + 1) + ". " + b.getNome() + " - " + b.getPrezzo());
                    }
                    System.out.println("\nA che bevanda scegli di cambiare prezzo?");
                    System.out.println("Scelgo: ");
                    int sceltaB = in.nextInt();
                    Bevanda b = macchina.getBevande().get(sceltaB - 1);

                    System.out.println("Inserire prezzo nuovo: ");
                    int prezzoN = in.nextInt();
                    if(prezzoN > 0) {
                        b.setPrezzo(prezzoN);
                        System.out.println(b.getNome() + " - " + b.getPrezzo());
                        System.out.println("Prezzo aggiornato!");
                    }
                    else System.out.println("Prezzo non idoneo, inserire un prezzo positivo");
                }
                break;

                case 4: {
                    for (int i = 0; i < macchina.getBevande().size(); i++) {
                        Bevanda b = macchina.getBevande().get(i);
                        System.out.println((i + 1) + ". " + b.getNome() + " - " + b.getPrezzo());
                    }
                    System.out.println("\nA che bevanda scegli di cambiare nome?");
                    System.out.println("Scelgo: ");
                    int sceltaB = in.nextInt();
                    if (sceltaB >= 1 && sceltaB <= macchina.getBevande().size()) {
                        Bevanda b = macchina.getBevande().get(sceltaB - 1);

                        System.out.println("Inserire nome nuovo: ");
                        in.nextLine();
                        String nomeN = in.nextLine();
                        b.setNome(nomeN);
                        System.out.println(b.getNome() + " - " + b.getPrezzo());
                        System.out.println("Nome aggiornato!");
                    }
                    else System.out.println("modello.Bevanda non trovata");
                }
                break;

                case 5: {
                    System.out.println("Svuotando la macchinetta... ");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Hai ricavato " + macchina.getIncasso() + "!");
                    macchina.svuotaIncasso();
                    System.out.println("Saldo attuale macchinetta: " + macchina.getIncasso());
                }
                break;

                case 6: {
                    System.out.println("Quanta acqua e quanti chicchi?");
                    int a = in.nextInt();
                    int c = in.nextInt();
                    macchina.ricarica(a, c);
                    System.out.println("La macchina è stata ricaricata con successo");
                }
                break;

                default: System.out.println("Scelta non valida");
            }
        }
    }
}
