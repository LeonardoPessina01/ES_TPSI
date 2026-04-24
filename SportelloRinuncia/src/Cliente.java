import java.util.Random;

public class Cliente extends Thread {


    //private Banca banca = new Banca();
    private Banca banca;
    private Random random = new Random();
    private int r;

    public Cliente(Banca banca) {
        this.banca = banca;
    }

    @Override
    public void run() {

        try {
            if (!banca.Servire()) {
                System.out.println("Il cliente: " + currentThread().getName() + " è andato via");
            } else {
                r = random.nextInt(2000, 4000);
                System.out.println(r);
                sleep(r);
                banca.Liberare();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
