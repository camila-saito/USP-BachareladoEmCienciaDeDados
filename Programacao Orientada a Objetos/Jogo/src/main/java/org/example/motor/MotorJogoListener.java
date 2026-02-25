//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.motor;

import org.example.entidades.Chefe;
import org.example.entidades.Entidade;
import org.example.entidades.Heroi;
import org.example.entidades.Projetil;
import java.awt.Rectangle;
import java.util.List;

public interface MotorJogoListener {
    Rectangle getArenaChefe();

    void onChefeDerrotado(Chefe var1);

    void onSpawnProjetilRequisitado(Projetil var1);

    List<Entidade> getEntidadesProximas(Entidade var1, double var2);

    boolean verificarColisaoComMapa(Entidade var1);

    Heroi getAlvoHeroi();
}
