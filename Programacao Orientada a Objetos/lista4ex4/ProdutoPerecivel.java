/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista4ex4;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author camil
 */
public class ProdutoPerecivel extends Produto{
    protected Date validade;

    public ProdutoPerecivel(Date validade, int codigo, float precoUnitario, String descricao, int qtdEstoque) {
        super(codigo, precoUnitario, descricao, qtdEstoque);
        this.validade = validade;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }
    
    private long diasParaVencer(Date hoje) {
        long dif = validade.getTime() - hoje.getTime(); // pega a diferenca, em milissegundos, entre hoje e a validade - getTime() retorna quantos milisseguntos se passaram desde 1970
        return TimeUnit.DAYS.convert(dif, TimeUnit.MILLISECONDS); //converte a diferenca em dias
    }
    
    public int retirarEstoque(int qtd, Date hoje) {
        if(diasParaVencer(hoje) <= 30) {
            qtd = super.getQtdEstoque();
            super.retirarEstoque(super.getQtdEstoque());
            return qtd;
        }
        else {
            return super.retirarEstoque(qtd);
        }
    }
    
    @Override
    public boolean adicionarEstoque(int qtd) {
        if(qtdEstoque == 0) {
            return super.adicionarEstoque(qtd);
        }
        else {
            return false;
        }
    }
}
