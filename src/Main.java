public class Main {
    public static void main(String[] args) {
        Mazzo mazzo = new Mazzo();
        Giocatore g1 = new Giocatore();
        System.out.print(g1);
        Giocatore g2 = new Giocatore();
        Giocatore g3 = new Giocatore();
        Giocatore g4 = new Giocatore();
        Giocatore[] players = {g1, g2, g3, g4};
        mazzo.distribuisci(players);
        System.out.print(g1);
    }


}