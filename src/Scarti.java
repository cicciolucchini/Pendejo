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
        top = new Selezione();
    }
}
