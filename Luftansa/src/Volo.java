public class Volo extends Thread{

    private TipoVolo type;
    private Terminal terminal;

    public Volo(TipoVolo type, Terminal terminal){
        this.type=type;
        this.terminal=terminal;
    }

    @Override
    public void run() {

        for (int i = 0; i < 2; i++) {


            try {
                sleep(1000);
                if (type == TipoVolo.INTERNAZIONALE) {
                    terminal.occupaGateInternazionale(this);
                } else {
                    terminal.occupaGateNazionale(this);
                }
                sleep(3500);
                if (type == TipoVolo.INTERNAZIONALE) {
                    terminal.rilasciaGateInternazionale(this);
                } else {
                    terminal.rilasciaGateNazionale(this);
                }

            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }


    }
}
