import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Cliente cliente;
        ArrayList<Cliente> c = new ArrayList<>();

        Banca b = new Banca();
        for (int i = 0; i < 8; i++) {
            cliente = new Cliente(b);
            c.add(cliente);
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