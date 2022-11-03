
package DAOS;

import Objeto_negocio.Temporizador;
import java.util.Timer;

public class TemporizadorDAO {
    private Timer timer;
    private Temporizador temporizador;
    private int conteoDescanso;
    private int VEINTICINCO = 25;
    private int CINCO = 5;
    private int CERO = 0;
    private int MIL = 1000;

    public TemporizadorDAO() {
        this.timer = new Timer();
        this.temporizador = new Temporizador(this.VEINTICINCO,this.CERO);
        this.conteoDescanso=this.CERO;
    }
    
    public void siguienteActividad(){
        
    }
    
    public void siguienteDescanso(){
        conteoDescanso++;
        if(conteoDescanso==4){
            temporizador.setMinutos(VEINTICINCO);
            temporizador.setSegundos(CERO);
            timer.scheduleAtFixedRate(temporizador, CERO, MIL);
        }
        else{
            temporizador.setMinutos(CINCO);
            temporizador.setSegundos(CERO);
            timer.scheduleAtFixedRate(temporizador, CERO, MIL);
        }
    }
    
    
}
