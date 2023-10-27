import java.util.Stack;

public class Scarti {
    private final Stack<Selezione> pila = new Stack<>();
    private Selezione top;
    public Selezione getTop() {
        return top;
    }
    public Stack<Selezione> getPila() {
       return pila;
    }
    public void wipePila() {
        pila.clear();
        top = null;
    }
}
