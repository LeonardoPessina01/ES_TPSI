import java.util.Random;
public class Banca {

    private int NUMERO_CASSIERI = 2;
    private Random random = new Random();
    private long prima;
    private long dopo;
    private long tempo = 5000;


    public synchronized boolean Servire() throws InterruptedException {
        while (NUMERO_CASSIERI == 0) {
            System.out.println(Thread.currentThread().getName()+ " sta aspettando di essere servito;");
            prima = System.currentTimeMillis();
            //System.out.println("prima: " + prima + " "+ Thread.currentThread().getName());
            wait(tempo);
            dopo = System.currentTimeMillis();
            //System.out.println("dopo: " + dopo + " " +Thread.currentThread().getName());
            if(dopo - prima >= 5000 ){
                return false;
            }
        }

        NUMERO_CASSIERI--;
        System.out.println("Cliente: "+ Thread.currentThread().getName()+ " sta venendo servito!");

        return true;
    }

    public synchronized void Liberare() throws InterruptedException {
        NUMERO_CASSIERI++;
        System.out.println("Cliente: "+ Thread.currentThread().getName()+ " è stato servito!");
        notifyAll();

    }


}
