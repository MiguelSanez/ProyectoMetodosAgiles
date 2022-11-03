
package Objeto_negocio;

import java.util.TimerTask;

public class Temporizador extends TimerTask{

    private int minutos;
    private int segundos;

    public Temporizador(int minutos, int segundos) {
        this.minutos = minutos;
        this.segundos = segundos;
    }
    
    @Override
    public void run() {
        if(segundos == 0 && minutos == 0) cancel();
        if(segundos!=0){
            segundos--;
        } else if(minutos!=0){
            minutos--;
        }
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }
}
