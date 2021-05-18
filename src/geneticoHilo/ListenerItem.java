/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticoHilo;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JRadioButton;

/**
 *
 * @author peper
 */
public class ListenerItem implements ItemListener
{
    private JFramePrincipal jfp;

    public ListenerItem(JFramePrincipal jfp) {
        this.jfp = jfp;
    }
    
    
    @Override
    public void itemStateChanged(ItemEvent e) 
    {
        JRadioButton button = (JRadioButton)e.getSource();
        
        switch (button.getText())
        {
            case "Binario":
            {
                jfp.getjLabelAux().setText("Bits");
                jfp.getjPanel_Aux().setVisible(true);
                jfp.setOp(1);
                break;
            }
            case "N Reinas":
            {
                jfp.getjLabelAux().setText("Tamaño tablero");
                jfp.getjPanel_Aux().setVisible(true);
                jfp.setOp(2);
                break;
            }
            case "N Ciudades":
            {
                jfp.getjLabelAux().setText("Ciudad inicial");
                jfp.getjPanel_Aux().setVisible(true);
                jfp.setOp(3);
                break;
            }
            case "Booleana":
            {
                jfp.getjPanel_Aux().setVisible(false);
                jfp.setOp(4);
                break;
            }
        }
        
    }
    
}
