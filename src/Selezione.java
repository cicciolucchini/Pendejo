/**
 * Classe che descrive una selezione, ovvero una scelta di un numero compreso tra 1 e 4 di copie
 * della stessa carta.
 */
public class Selezione {
    /**
     * La carta
     */
    private final Carta carta;
    /**
     * Il numero di copie della carta.
     */
    private int n;

    /**
     * Crea una selezione vuota.
     */
    public Selezione() {
        carta = new Carta(4);
        n = 0;
    }


    /**
     * Costruttore che crea una selezione composta da un numero variabile di copie (compreso tra 1 e
     * 4) di una carta.
     * @param c la carta.
     * @param num il numero di copie.
     */
    public Selezione(Carta c, int num) {
        carta = c;
        n = num;
    }

    /**
     * Aggiunge una carta a questa selezione, se la carta parametro e questa carta coincidono
     * @param c la carta da aggiungere
     * @throws IllegalArgumentException se c e questa carta sono differenti
     */
    public void aggiungiCarta(Carta c) throws IllegalArgumentException {
        if (c.equals(carta)) {
            setN(getN()+1);
        } else throw new IllegalArgumentException();
    }

    /**
     * Aggiunge un numero variabile di carte a questa selezione, se la carta parametro e
     * questa carta coincidono
     * @param c la carta da aggiungere.
     * @param num il numero di copie da aggiungere.
     * @throws IllegalArgumentException se c e questa carta non coincidono.
     */
    public void aggiungiCarta(Carta c, int num) {
        if (c.equals(getCarta())) {
            setN(getN()+num);
        } else throw new IllegalArgumentException();
    }

    /**
     * Restituisce la carta di questa selezione
     * @return la carta.
     */
    public Carta getCarta() {
        return carta;
    }

    /**
     * Restituisce il numero di copie di questa selezione.
     * @return il numero di carte.
     */
    public int getN() {
        return n;
    }

    /**
     * Pone il numero di carte di questa selezione ad un determinato numero.
     * @param n il nuovo numero di carte.
     */
    public void setN(int n) {
        this.n += n;
    }

    public int hashCode() {
        return (13*getN())+getCarta().toString().hashCode();
    }

    public boolean equals(Object o) {
        if (!(o instanceof Selezione)) throw new IllegalArgumentException();
        return (this.hashCode()==o.hashCode());
    };

    public String toString() {
        return getN() + " " + getCarta().toString();

    }
}
