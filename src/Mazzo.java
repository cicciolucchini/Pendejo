import java.util.*;

/**
 * Classe che descrive un mazzo di carte, dotato di un numero variabile di carte impilate.
 */
public class Mazzo {
    /**
     * La pila delle carte
     */
    private final Stack<Carta> carte;
    /**
     * Il numero di carte rimanenti
     */
    private int size;

    /**
     * Restituisce un nuovo mazzo, con 4 copie di ciascuna delle 13 carte.
     */
    public Mazzo() {
        carte = new Stack<>();
        ArrayList<Carta> l = new ArrayList<>();
        int i;
        for (i = 2; i < 15; i++) {
            l.add(new Carta(i));
            l.add(new Carta(i));
            l.add(new Carta(i));
            l.add(new Carta(i));
        }
        Collections.shuffle(l);
        for (Carta c : l) {
            carte.push(c);
            size ++;
        }
    };

    /**
     * Distribuisce le carte ai giocatori, distribuisce 3 carte coperte, 3 scoperte, e 3 in mano.
     * @param players i giocatori a cui distribuire le carte.
     */
    public void distribuisci(Giocatore[] players) {
        Carta[] carte = new Carta[3];
        for (Giocatore g : players) {
            for (int j = 0; j < 3; j++) {
                carte[j] = pesca();
            }
            g.setCoperte(carte);
        }
        for (Giocatore g : players) {
            for (int j = 0; j < 3; j++) {
                carte[j] = pesca();
            }
            g.setScoperte(carte);
        }
        for (Giocatore g : players) {
            g.pesca(this, 3);
        }

    }

    /**
     * Restituisce la carta in cima al mazzo.
     * @return la carta pescata
     */
    public Carta pesca() throws IllegalStateException {
        if (rimanenti() < 1) {
            throw new IllegalStateException();
        }
        size--;
        return carte.pop();
    };

    /**
     * Restituisce il numero di carte rimaste nel mazzo
     * @return size.
     */
    public int rimanenti(){
        return size;
    };

}
