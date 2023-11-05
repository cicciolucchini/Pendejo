
public class Partita {
    private static class PartitaFinitaException extends RuntimeException {


        private PartitaFinitaException(int i) {
            System.out.println("PARTITA FINITA!\n Il Pendejo è" + i);
        }
    }
    private final Mazzo mazzo = new Mazzo();

    private final Scarti pila = new Scarti();

    private final Giocatore[] players = {new Giocatore(), new Giocatore(), new Giocatore(), new Giocatore()};

    public void giocaPartita() {
        mazzo.distribuisci(players);
        int playingCounter = 0;
        int hannoFinito;
        int pendejo = 0;
        for (int i=0;i<4;i++) {
            if (players[i].haCarta(new Carta(4))) {
                playingCounter = i;
            }
        }
        Giocatore playing;

        while (true) {
            hannoFinito=0;
            playing = players[playingCounter];
            if (playing.haFinito()) {
                playingCounter = (playingCounter + 1) % 4;
            } else {
                System.out.println("\nGiocatore " + playingCounter);
                System.out.println("\nDevi rispondere a " + pila.getTop().toString());
                Selezione giocata = playing.scegliCarta(pila);
                if (!(giocata.getCarta().playable(pila.getTop().getCarta()))) {
                    pila.aggiungi(giocata);
                    playing.pescaPila(pila);
                    pila.wipePila();
                    playingCounter = (playingCounter + 1) % 4;
                } else {
                    pila.aggiungi(giocata);
                    if (mazzo.rimanenti()>0 && playing.getSize()<3) {
                        playing.pesca(mazzo, 3 - playing.getSize());
                    }
                    boolean daPulire = (giocata.getCarta().equals(new Carta(10)) ||
                            (pila.getTop().getN()>0 && pila.getTop().getN()%4==0)
                    );
                    if (daPulire) {
                        pila.wipePila();
                    } else {
                        if (giocata.getCarta().equals(new Carta(8))) {
                            playingCounter = (playingCounter+giocata.getN()+1)%4;
                        } else {
                            playingCounter = (playingCounter + 1) % 4;
                        }
                    }
                }
            }
            for (int i = 0; i < 4; i++) {
                if (players[i].haFinito()) hannoFinito++;
                else pendejo = i;
            }
            if (hannoFinito == 3) throw new PartitaFinitaException(pendejo);
        }
    }
}
