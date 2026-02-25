//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.motor;

import org.example.entidades.Heroi;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControleTeclado implements KeyListener {
    private Heroi heroi;
    private MotorJogo motorJogo;

    public ControleTeclado(MotorJogo var1) {
        this.motorJogo = var1;
    }

    public void keyTyped(KeyEvent var1) {
    }

    public void keyPressed(KeyEvent var1) {
        if (this.motorJogo != null) {
            int var2 = var1.getKeyCode();
            switch (var2) {
                case 32:
                    this.motorJogo.heroiTentaAtacar();
                    break;
                case 37:
                case 65:
                    this.motorJogo.setHeroiMovendoEsquerda(true);
                    break;
                case 38:
                case 87:
                    this.motorJogo.setHeroiMovendoCima(true);
                    break;
                case 39:
                case 68:
                    this.motorJogo.setHeroiMovendoDireita(true);
                    break;
                case 40:
                case 83:
                    this.motorJogo.setHeroiMovendoBaixo(true);
                    break;
                case 80:
                    this.motorJogo.alternarPausa();
            }

        }
    }

    public void keyReleased(KeyEvent var1) {
        if (this.motorJogo != null) {
            int var2 = var1.getKeyCode();
            switch (var2) {
                case 37:
                case 65:
                    this.motorJogo.setHeroiMovendoEsquerda(false);
                    break;
                case 38:
                case 87:
                    this.motorJogo.setHeroiMovendoCima(false);
                    break;
                case 39:
                case 68:
                    this.motorJogo.setHeroiMovendoDireita(false);
                    break;
                case 40:
                case 83:
                    this.motorJogo.setHeroiMovendoBaixo(false);
            }

        }
    }
}
