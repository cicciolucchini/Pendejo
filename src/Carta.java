/**
Classe che descrive una Carta, ovvero un oggetto dotato di valore numerico, da 1 a 13 compresi.
Le carte 2, 3, e 10 sono cosiddette speciali.
 */
public class Carta {
    //Il valore numerico della carta
    private final int valore;
    /**
     * Crea una nuova carta col valore specificato
     * @param n il valore della carta
     * @throws IllegalArgumentException se il numero della carta non è compreso tra 1 e 13.
     */
    public Carta(int n) throws IllegalArgumentException{
        if (n > 13 || n < 1) {throw new IllegalArgumentException();}
        valore = n;
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
        Integer val = (Integer) val();
        if (val == 1) {return "A";}
        if (val == 11) {return "J";}
        if (val == 12) {return "Q";}
        if (val == 13) {return "K";}
        return val.toString();
    }

}
