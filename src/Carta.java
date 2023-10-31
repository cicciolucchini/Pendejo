import java.util.Map;

import static java.util.Map.entry;

/**
Classe che descrive una Carta, ovvero un oggetto dotato di valore numerico, da 1 a 13 compresi.
Le carte 2, 3, e 10 sono cosiddette speciali.
 */
public class Carta {
    /**
     * Il valore numerico della carta.
     */
    private final int valore;

    /**
     * Il nome della carta.
     */
    private final String nome;

    public Map<Integer, String> CardMap = Map.ofEntries(
            entry(2, "Due"),
            entry(3, "Tre"),
            entry(4, "Quattro"),
            entry(5, "Cinque"),
            entry(6, "Sei"),
            entry(7, "Sette"),
            entry(8, "Otto"),
            entry(9, "Nove"),
            entry(10, "Dieci"),
            entry(11, "Jack"),
            entry(12, "Queen"),
            entry(13, "King"),
            entry(14, "Asso")
    );

    /**
     * Crea una nuova carta col valore specificato
     * @param n il valore della carta
     * @throws IllegalArgumentException se il numero della carta non è compreso tra 1 e 13.
     */
    public Carta(int n) throws IllegalArgumentException{
        if ((n > 15) || (n < 2)) {throw new IllegalArgumentException();}
        valore = n;
        nome = CardMap.get(n);
    }

    /**
     * Restituisce il valore numerico di questa carta.
     * @return il valore.
     */
    public int val() {
        return valore;
    }

    /**
     * Verifica se questa carta è speciale, ovvero se il suo valore è 2, 3, o 10.
     * @return true sse this.val appartiene a {2, 3, 10}
     */
    public boolean isSpecial() {
        return (val() == 2 || val() == 3 || val() == 10);
    }


    public int hashCode() {
        return val();
    };

    public boolean equals(Object o) {
        return (this.hashCode()==o.hashCode());
    };

    public String toString() {
        return nome;
    }

}
