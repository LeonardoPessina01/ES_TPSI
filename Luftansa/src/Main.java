import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Volo volo;

        ArrayList<Volo> c = new ArrayList<>();

        Terminal b = new Terminal(3, 2);
        for (int i = 0; i < 4; i++) {
            volo = new Volo(TipoVolo.NAZIONALE, b);
            c.add(volo);
            volo = new Volo(TipoVolo.INTERNAZIONALE, b);
            c.add(volo);
        }

        for (int i = 0; i < 4; i++) {
            volo = new Volo(TipoVolo.INTERNAZIONALE, b);
            c.add(volo);
        }


        for (int i = 0; i < 8; i++) {
            c.get(i).start();
        }

        for (int i = 0; i < 8; i++) {
            try {
                c.get(i).join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}