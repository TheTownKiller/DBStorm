package utilities;

import java.util.Timer;
import java.util.TimerTask;

public class TimeCounter  {

    private Timer timer = new Timer(); 
    private int segundos=0;

    class Contador extends TimerTask {
        public void run() {
            segundos++;
        }
    }

    public void Contar()
    {
        this.segundos=0;
        timer = new Timer();
        timer.schedule(new Contador(), 0, 100);
    }
    public void Detener() {
        timer.cancel();
    }
    public int getMillis()
    {
        return this.segundos;
    }
}