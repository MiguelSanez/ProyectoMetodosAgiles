
package Objeto_negocio;

import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Temporizador extends Timer{
    
    public Temporizador(int tiempo, ActionListener al) {
        super(tiempo, al);
    }
    
}
