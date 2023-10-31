import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Giocatore {
    private final Map<Carta, Integer> mano = new HashMap<>(13);

    private final Carta[] scoperte = new Carta[3];

    private final Carta[] coperte = new Carta[3];

    private int size = 0;
    private int scop_size = 3;

    private int cop_size = 3;

    public Giocatore() {
        for (int i = 2; i < 15; i++) {
            mano.put(new Carta(i), 0);
        }
    }

    /**


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
        size++;
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
        System.arraycopy(c, 0, coperte, 0, 3);
    }

    /**
     * Pone tre carte come carte scoperte.
     * @param c le carte da inserire, devono essere 3.
     * @throws IllegalArgumentException se le carte non sono 3.
     */
    public void setScoperte(Carta[] c) throws IllegalArgumentException {
        if (c.length != 3) {throw new IllegalArgumentException();}
        System.arraycopy(c, 0, scoperte, 0, 3);
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

    /**
     * Effettua la selezione delle carte per conto del giocatore.
     * @param Tavolo le carte a cui rispondere.
     * @return la selezione di carte giocate.
     */
    public Selezione scegliCarta(Selezione Tavolo) {
        Set<Selezione> selSet = selezioneSet(Tavolo);
        System.out.println(stringScelte(selSet));
        System.out.println("Inserire la carta scelta");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Selezione scelta = new Selezione();
        try {
            String carta = reader.readLine();
            for (Selezione s : selSet) {
                if (s.getCarta().toString().equals(carta)) {
                    System.out.println("Inserire il numero di carte da giocare");
                    int n = Integer.parseInt(reader.readLine());
                    scelta = new Selezione(s.getCarta(), n);
                }
            }
        } catch (IOException e) {
        }
        diminuisciCarte(scelta.getCarta(), scelta.getN());
        return scelta;
    }

    private void diminuisciCarte(Carta c, int n) {
        mano.replace(c, mano.get(c) - 1);
    }

    /**
     * Restituisce l'insieme delle scelte possibili.
     * @param Tavolo le carte sul tavolo
     * @return l'insieme di scelte
     */
    private Set<Selezione> selezioneSet(Selezione Tavolo) {
        Set<Selezione> selSet = new HashSet<>();
        if (getSize() == 0 && scop_size == 0) {

        } else {
            if (getSize()== 0) {
                for (Carta c : scoperte) {
                    aggiungiCarta(c);
                }
                if (Tavolo.getCarta().equals(new Carta(7))) {
                    for (Map.Entry<Carta, Integer> entry : mano.entrySet()) {
                        if (entry.getKey().val() <= 7 || entry.getKey().isSpecial()) {
                            selSet.add(new Selezione(entry.getKey(), entry.getValue()));
                        }
                    }
                } else {
                    for (Map.Entry<Carta, Integer> entry : mano.entrySet()) {
                        if (entry.getKey().isSpecial() || entry.getKey().val() >= Tavolo.getCarta().val()) {
                            selSet.add(new Selezione(entry.getKey(), entry.getValue()));
                        }
                    }
                }
            } else {
                if (Tavolo.getCarta().equals(new Carta(7))) {
                    for (Map.Entry<Carta, Integer> entry : mano.entrySet()) {
                        if (entry.getKey().val() <= 7 || entry.getKey().isSpecial()) {
                            selSet.add(new Selezione(entry.getKey(), entry.getValue()));
                        }
                    }
                }
                else {
                    for (Map.Entry<Carta, Integer> entry : mano.entrySet()) {
                        if (entry.getKey().isSpecial() || entry.getKey().val() >= Tavolo.getCarta().val()) {
                            selSet.add(new Selezione(entry.getKey(), entry.getValue()));
                        }
                    }
                }
            }
        }
        if (selSet.isEmpty()) {
            for (Map.Entry<Carta, Integer> entry : mano.entrySet()) {
                selSet.add(new Selezione(entry.getKey(), entry.getValue()));
            }
        }
        return selSet;
    }

    private String stringScelte(Set<Selezione> selSet) {
        StringBuilder sb = new StringBuilder("Lista delle scelte disponibili:\n");
        for (Selezione s: selSet) {
            if (getCount(s.getCarta()) >= 1) {
                sb.append("Fino a ").append(s.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    public void pescaPila(Scarti pila) {
        for (Selezione s : pila.getPila()) {
            for (int i = 0; i < s.getN(); i++) {
                aggiungiCarta(s.getCarta());
            }
        }
    }

}
