import java.util.Stack;

public class Scarti {
    private final Stack<Selezione> pila = new Stack<>();
    private Selezione top;
    public Selezione getTop() {
        return top;
    }

    public void setTop(Selezione s) {
        top = s;
    }

    public Scarti() {
        top = new Selezione();
    }

    public Stack<Selezione> getPila() {
       return pila;
    }
    public void wipePila() {
        pila.clear();
        setTop(new Selezione());
    }

    public void aggiungi(Selezione scelta) {
        pila.push(scelta);
        if (getTop().getCarta().equals(scelta.getCarta())) {
            setTop(new Selezione(scelta.getCarta(), scelta.getN() + getTop().getN()));
        } else if (scelta.getCarta().equals(new Carta(2))) {
            setTop(new Selezione());
        } else if (!scelta.getCarta().equals(new Carta(3))) {
            setTop(scelta);
        }
    }
}
