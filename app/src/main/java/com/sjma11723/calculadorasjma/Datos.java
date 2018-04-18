package com.sjma11723.calculadorasjma;

import android.content.Context;
import android.content.Intent;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by SJMA 11723 on 08/04/2018.
 */
public class Datos extends RealmObject {

    @PrimaryKey
    private int totalNumerosMagicos;
    @Required
    private String digitoEnPantalla;
    @Required
    private String operacionResultado;
    @Required
    private String resultado;
    private boolean _11723Found;
    private boolean _1020503Found;
    private boolean _210703Found;
    private boolean _280203Found;
    @Required
    private String contra1020503;
    @Required
    private String contra210703;
    @Required
    private String contra280203;
    @Required
    private String priority;
    private int numerosMagicosEncontrados;
    private int iteradorNumeros;
    private int iteradorBotonIgual;

    public Datos(){

    }

    public Datos(String digitoEnPantalla, String operacionResultado, String resultado){
        setDigitoEnPantalla(digitoEnPantalla);
        setOperacionResultado(operacionResultado);
        setResultado(resultado);
        set_11723Found(false);
        set_210703Found(false);
        set_280203Found(false);
        set_1020503Found(false);
        setTotalNumerosMagicos(4);
        setNumerosMagicosEncontrados(0);
        setContra210703("210703");
        setContra280203("280203");
        setContra1020503("020503");
        setPriority("none");
        setIteradorNumeros(0);
        setIteradorBotonIgual(0);
    }

    public int getTotalNumerosMagicos() {
        return totalNumerosMagicos;
    }

    public void setTotalNumerosMagicos(int totalNumerosMagicos) {
        this.totalNumerosMagicos = totalNumerosMagicos;
    }

    public String getDigitoEnPantalla() {
        return digitoEnPantalla;
    }

    public void setDigitoEnPantalla(String digitoEnPantalla) {
        this.digitoEnPantalla = digitoEnPantalla;
    }

    public String getOperacionResultado() {
        return operacionResultado;
    }

    public void setOperacionResultado(String operacionResultado) {
        this.operacionResultado = operacionResultado;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public boolean is_11723Found() {
        return _11723Found;
    }

    public void set_11723Found(boolean _11723Found) {
        this._11723Found = _11723Found;
    }

    public boolean is_1020503Found() {
        return _1020503Found;
    }

    public void set_1020503Found(boolean _1020503Found) {
        this._1020503Found = _1020503Found;
    }

    public boolean is_210703Found() {
        return _210703Found;
    }

    public void set_210703Found(boolean _210703Found) {
        this._210703Found = _210703Found;
    }

    public boolean is_280203Found() {
        return _280203Found;
    }

    public void set_280203Found(boolean _280203Found) {
        this._280203Found = _280203Found;
    }

    public void setContra1020503(String contra1020503) {
        this.contra1020503 = contra1020503;
    }

    public void setContra210703(String contra210703) {
        this.contra210703 = contra210703;
    }

    public void setContra280203(String contra280203) {
        this.contra280203 = contra280203;
    }

    public String getContraOf(int number){
        String ret;
        switch (number){
            case 1020503:
                ret = "020503";
                break;
            case 210703:
                ret = "210703";
                break;
            case 280203:
                ret = "280203";
                break;
            default:
                ret = "byvruneiwnurtvbNotFound";
        }
        return ret;
    }

    public int getNumerosMagicosEncontrados() {
        return numerosMagicosEncontrados;
    }

    public void setNumerosMagicosEncontrados(int numerosMagicosEncontrados) {
        this.numerosMagicosEncontrados = numerosMagicosEncontrados;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public int getIteradorNumeros() {
        return iteradorNumeros;
    }

    public void setIteradorNumeros(int iteradorNumeros) {
        this.iteradorNumeros = iteradorNumeros;
    }

    public int getIteradorBotonIgual() {
        return iteradorBotonIgual;
    }

    public void setIteradorBotonIgual(int iteradorBotonIgual) {
        this.iteradorBotonIgual = iteradorBotonIgual;
    }
}
