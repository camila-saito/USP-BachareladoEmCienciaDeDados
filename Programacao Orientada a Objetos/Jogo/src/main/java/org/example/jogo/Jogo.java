/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.example.jogo;

/**
 *
 * @author camil
 */
import org.example.auxiliar.Posicao;
import org.example.entidades.Heroi;
import org.example.graficos.Mapa;
import org.example.graficos.TelaJogo;
import org.example.motor.ControleTeclado;
import org.example.motor.MotorJogo;
import java.awt.Component;
import java.io.File;
import javax.swing.JFrame;

/*import org.example.auxiliar.GerenciadorDePersonagemCustom;
import org.example.entidades.inimigos.Bot;
import org.example.entidades.chefes.ByteCorrompido;*/

public class Jogo {
    public static void main(String[] var0) {
        JFrame var1 = new JFrame("Meu Jogo Tile-Based de Computação");
        var1.setDefaultCloseOperation(3);
        var1.setResizable(false);
        TelaJogo var2 = new TelaJogo((MotorJogo)null);
        var1.add(var2, "Center");
        MotorJogo var3 = new MotorJogo(var2);
        var2.setMotorJogo(var3);
        (new File(System.getProperty("user.dir") + "/imagens/mapas/")).mkdirs();
        Mapa mapa = new Mapa("mapa_fase1_tematico.txt", 1);
        var2.setMapaAtual(mapa);
        Posicao var5 = new Posicao(5, 5);
        new Heroi("Zé Valente", var5, 10, 5);
        ControleTeclado var7 = new ControleTeclado(var3);
        var2.addKeyListener(var7);
        var1.pack();
        var1.setLocationRelativeTo((Component)null);
        var1.setVisible(true);
        var3.iniciar();
        var2.requestFocusInWindow();
        
        /* Codigo utilizado na criacao de um bot.zip
        Bot bot = new Bot(new Posicao(2, 2), var2.getMotorJogo().getHeroi());
        if(GerenciadorDePersonagemCustom.salvarPersonagemEmZip(bot, "bot.zip"))
            System.out.println("Personagem criado com sucesso.");
        else
            System.out.println("Erro ao criar personagem");*/
        
        /* Codigo utilizado na criacao de um byte.zip
        ByteCorrompido b = new ByteCorrompido(new Posicao(2, 10), var2.getMotorJogo().getHeroi());
        if(GerenciadorDePersonagemCustom.salvarPersonagemEmZip(b, "byte.zip"))
            System.out.println("Personagem criado com sucesso.");
        else
            System.out.println("Erro ao criar personagem");*/
    }
}