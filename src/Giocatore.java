import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Giocatore {
    private final Map<Carta, Integer> mano = new HashMap<>(13);;

    private final Carta[] scoperte = new Carta[3];

    private final Carta[] coperte = new Carta[3];

    private int size = 0;

    public Giocatore() {
        for (int i = 1; i < 14; i++) {
            mano.put(new Carta(i), 0);
        }
    }

    /**
     * Metodo che restituisce l'insieme di selezioni possibili per un giocatore date le carte in
     * cima.
     * @param top le carte in cima alla pila degli scarti.
     * @return un insieme delle possibili selezioni.
    */
    public Set<Selezione> selezioneSet(Selezione top) {
        Set<Selezione> selSet = new HashSet<>();
        Map<Carta, Integer> mano = getMano();
        for (Carta c : mano.keySet()) {
            for (int i = 1; i <= mano.get(c); i++) {
                selSet.add(new Selezione(c, i));
            }
        }
        return selSet;
    }

    /**
     * Metodo che permette al giocatore di pescare carte.
     * @param m il mazzo da cui pescare.
     * @param n il numero di carte da pescare.
     */
    public void pesca(Mazzo m, int n) {
        if (n > m.rimanenti()) {
            for (int i = 0; i < m.rimanenti(); i++) {
                aggiungiCarta(m.pesca());
            }
        } else {
            for (int i = 0; i < n; i++) {
                aggiungiCarta(m.pesca());
            }
        }
    }

    /**
     * Aggiunge una carta alla mano.
     * @param c la carta.
     */
    public void aggiungiCarta(Carta c) {
        int n = getCount(c);
        mano.replace(c, n+1);

    }

    /**
     * Restituisce la mano del giocatore.
     * @return
     */
    public Map<Carta, Integer> getMano() {
        return mano;
    }

    /**
     * Restituisce il numero di copie di una carta in mano al giocatore.
     * @param c la carta.
     * @return il numero di copie in mano.
     */
    public int getCount(Carta c) {
        return mano.get(c);
    }


    /**
     * Pone tre carte come carte coperte.
     * @param c le carte da inserire, devono essere 3.
     * @throws IllegalArgumentException se le carte non sono 3.
     */
    public void setCoperte(Carta[] c) throws IllegalArgumentException {
        if (c.length != 3) {throw new IllegalArgumentException();}
        for (int i = 0; i < 3; i++) {
            coperte[i] = c[i];
        }
    }

    /**
     * Pone tre carte come carte scoperte.
     * @param c le carte da inserire, devono essere 3.
     * @throws IllegalArgumentException se le carte non sono 3.
     */
    public void setScoperte(Carta[] c) throws IllegalArgumentException {
        if (c.length != 3) {throw new IllegalArgumentException();}
        for (int i = 0; i < 3; i++) {
            scoperte[i] = c[i];
        }
    }

    /**
     * Incrementa o diminuisce la dimensione della mano di carte.
     * @param increase determina se la dimensione viene incrementata (True) o diminuita (False).
     */
    public void setSize(boolean increase) {
        if (increase) {size++;}
        else {size--;}
    }

    /**
     * Restituisce il numero di carte in mano.
     * @return il numero di carte.
     */
    public int getSize() {return size;}

    public String toString() {
        StringBuilder sb = new StringBuilder("Mano del giocatore:\n");

        for (Map.Entry<Carta, Integer> entry : mano.entrySet()) {
            if (entry.getValue() != 0) {
                switch (entry.getValue()) {
                    case 1:
                        sb.append("Un ");
                        break;
                    case 2:
                        sb.append("Due ");
                        break;
                    case 3:
                        sb.append("Tre ");
                        break;
                    case 4:
                        sb.append("Quattro ");
                        break;
                }
                sb.append(entry.getKey().toString()).append("\n");
            }
        }
        return sb.toString();
    }


}
