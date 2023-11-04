import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Giocatore {
    private final Map<Carta, Integer> mano = new HashMap<>(13);

    private final Map<Carta, Integer> scoperte = new HashMap<>(3);

    private final Map<Carta, Integer> coperte = new HashMap<>(3);

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
        for (Carta carta : c) {
            if (coperte.containsKey(carta)) {
                coperte.replace(carta, coperte.get(carta)+1);
            } else {
                coperte.put(carta, 1);
            }
        }
    }

    /**
     * Pone tre carte come carte scoperte.
     * @param c le carte da inserire, devono essere 3.
     * @throws IllegalArgumentException se le carte non sono 3.
     */
    public void setScoperte(Carta[] c) throws IllegalArgumentException {
        if (c.length != 3) {throw new IllegalArgumentException();}
        for (Carta carta : c) {
            if (scoperte.containsKey(carta)) {
                scoperte.replace(carta, scoperte.get(carta)+1);
            } else coperte.put(carta, 1);
        }
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
     * @param pila la pila degli scarti.
     * @return la selezione di carte giocate.
     */
    public Selezione scegliCarta(Scarti pila) {
        Selezione Tavolo = pila.getTop();
        Selezione scelta = new Selezione();
        Set<Selezione> eligibles = new HashSet<>();
        if (size == 0) {
            if (scop_size == 0) {
                for (Map.Entry<Carta, Integer> e: coperte.entrySet()) {
                    Carta c = e.getKey();
                    if (e.getValue() > 0) {
                        if (c.playable(Tavolo.getCarta())) {
                            eligibles.add(new Selezione(c, coperte.get(c)));
                        }
                    }

                }
                if (eligibles.isEmpty()) {
                    scelta = menuNoScelte(coperte);
                    coperte.replace(scelta.getCarta(), coperte.get(scelta.getCarta()) - scelta.getN());
                    cop_size -= scelta.getN();
                } else {
                    scelta = menuScelta(eligibles);
                    coperte.replace(scelta.getCarta(), coperte.get(scelta.getCarta()) - scelta.getN());
                    cop_size -= scelta.getN();
                }
            } else {
                for (Carta c: scoperte.keySet()) {
                    if (scoperte.get(c)>0) {
                        if (c.playable(Tavolo.getCarta())) {
                            eligibles.add(new Selezione(c, scoperte.get(c)));
                        }
                    }

                }
                if (eligibles.isEmpty()) {
                    scelta = menuNoScelte(scoperte);
                    scoperte.replace(scelta.getCarta(), scoperte.get(scelta.getCarta()) - scelta.getN());
                    scop_size -= scelta.getN();
                } else {
                    scelta = menuScelta(eligibles);
                    scoperte.replace(scelta.getCarta(), scoperte.get(scelta.getCarta()) - scelta.getN());
                    scop_size -= scelta.getN();
                }
            }
        } else {
            for (Carta c: mano.keySet()) {
                if (getCount(c)>0) {
                    if (c.playable(Tavolo.getCarta())) {
                        eligibles.add(new Selezione(c, getCount(c)));
                    }
                }

            }
            if (eligibles.isEmpty()) {
                System.out.println("NON HAI SCELTE");
                scelta = menuNoScelte(mano);
                diminuisciCarte(scelta.getCarta(), scelta.getN());
            } else {
                scelta = menuScelta(eligibles);
                diminuisciCarte(scelta.getCarta(), scelta.getN());
            }
        }
        return scelta;
    }

    private Selezione menuScelta(Set<Selezione> set) {
        String nomeScelto = new String();
        Integer numScelto = 0;
        Selezione scelta = new Selezione();
        System.out.println(stringScelte(set));
        System.out.println("Inserire carta da giocare");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            nomeScelto = reader.readLine().trim();
        } catch (IOException e) {}
        System.out.println("Inserire numero di carte da giocare");
        try {
            numScelto = Integer.parseInt(reader.readLine());
        } catch (IOException e) {}
        for (Selezione s : set) {
            if (s.getCarta().toString().equals(nomeScelto)) {
                scelta = new Selezione(s.getCarta(), numScelto);
            }
        }
        return scelta;
    }

    private Selezione menuNoScelte(Map<Carta, Integer> carte) {
        Selezione scelta = new Selezione();
        Set<Selezione> set = new HashSet<>();
        String nomeScelto = new String();
        Integer numScelto = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (Map.Entry<Carta, Integer> entry : carte.entrySet()) {
            set.add(new Selezione(entry.getKey(), entry.getValue()));
        }
        System.out.println(stringScelte(set));
        System.out.println("Inserire la carta da giocare");
        try {
            nomeScelto = reader.readLine();
        } catch (IOException e) {}
        System.out.println("Inserire il numero di carte da giocare");
        try {
            numScelto = Integer.parseInt(reader.readLine());
        } catch(IOException e) {}
        for (Selezione s : set) {
            if (s.getCarta().toString().equals(nomeScelto)) {
                scelta = new Selezione(s.getCarta(), numScelto);
            }
        }
        return scelta;
    }

    private void diminuisciCarte(Carta c, int n) {
        mano.replace(c, mano.get(c) - n);
        size -= n;
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

    public boolean haCarta(Carta c) {
        return (mano.get(c) > 0);
    }

    public boolean haFinito() {
        return (size==0 && scop_size==0 && cop_size==0);
    }
}
