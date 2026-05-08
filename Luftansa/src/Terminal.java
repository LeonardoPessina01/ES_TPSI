import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Terminal {
    private int gateNazionali;
    private int gateInternazionali;

    private int gateNazionaliLiberi;
    private int gateInternazionaliLiberi;


    private final ReentrantLock lock;
    private Condition attendiInternazionale;
    private Condition attendiNazionale;

    public Terminal(int gateNazionaliLiberi, int gateInternazionaliLiberi){
        this.gateNazionali=gateNazionali;
        this.gateNazionaliLiberi=gateNazionaliLiberi;
        this.gateInternazionali=gateInternazionali;
        this.gateInternazionaliLiberi=gateNazionaliLiberi;
        this.lock = new ReentrantLock();
        this.attendiInternazionale = lock.newCondition();
        this.attendiNazionale = lock.newCondition();
    }


    public void occupaGateInternazionale(Volo v) throws InterruptedException{
        lock.lock();

        try {
            while (gateInternazionaliLiberi ==0){
                attendiInternazionale.wait();
            }

            gateInternazionaliLiberi--;
            System.out.println("[GATE-INTERNAZIONALE]: "+v.getName()+ " occupa un gate. "+
                    "(int.liberi): "+ gateInternazionaliLiberi + ")");
        } finally {
            lock.unlock();
        }

    }

    public void rilasciaGateInternazionale(Volo v){
        lock.lock();

        try {
            gateInternazionaliLiberi++;
            System.out.println(v.getName()+ " rilascia gate! Gate liberi: "+ gateInternazionaliLiberi );
            attendiInternazionale.signal();
        } finally {
            lock.unlock();
        }

    }

//----------------------------------------------------------------------------------------

    public void occupaGateNazionale(Volo v) throws InterruptedException {

        lock.lock();

        try {
            while (gateNazionaliLiberi ==0){
                attendiNazionale.wait();
            }

            gateNazionaliLiberi--;
            System.out.println("[GATE-NAZIONALE]: "+v.getName()+ " occupa un gate. "+
                    "(int.liberi): "+ gateNazionaliLiberi + ")");
        } finally {
            lock.unlock();
        }

    }

    public void rilasciaGateNazionale(Volo v) {

        lock.lock();

        try {
            gateNazionaliLiberi++;
            System.out.println(v.getName()+ " rilascia gate! Gate liberi: "+ gateNazionaliLiberi );
            attendiNazionale.signal();
        } finally {
            lock.unlock();
        }

    }


}
