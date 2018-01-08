package com.sjma11723.calculadorasjma;

import java.io.Serializable;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Created by SJMA 11723 on 02/01/2018.
 */

public class MyString implements Serializable, CharSequence {

    private StringBuffer texto;

    public MyString(String texto) {
        super();
        this.texto = new StringBuffer(texto);
    }

    public MyString(){

    }

    @Override
    public IntStream chars() {
        return null;
    }

    @Override
    public int length() {
        return texto.length();
    }

    @Override
    public char charAt(int i) {
        return texto.charAt(i);
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        return texto.subSequence(i, i1);
    }

    @Override
    public String toString() {
        return texto.toString();
    }

    public int indexOfSigns(int from, String textoABuscar){
        Character[] caracter = new Character[textoABuscar.length()];
        int[] index = new int[textoABuscar.length()]; //indice que devuelve

        for (int i = 0; i < textoABuscar.length(); i++) {
            caracter[i] = textoABuscar.charAt(i);
            index[i] = texto.indexOf(caracter[i].toString(), from);
        }

        if (index[0] == -1 && index[1] == -1 && index[2] == -1 && index[3] == -1){
            return texto.length();
        } else if (index[0] != -1 && index[1] != -1 && index[2] != -1 && index[3] != -1){

            if (index[0] < index[1] && index[0] < index[2] && index[0] < index[3]){
                return index[0];
            } else if (index[1] < index[0] && index[1] < index[2] && index[1] < index[3]){
                return index[1];
            } else if (index[2] < index[1] && index[2] < index[0] && index[2] < index[3]){
                return index[2];
            } else {
                return index[3];
            }

        } else{
            if (index[0] == -1){
                index[0] = texto.length();
            }

            if (index[1] == -1){
                index[1] = texto.length();
            }

            if (index[2] == -1){
                index[2] = texto.length();
            }

            if (index[3] == -1){
                index[3] = texto.length();
            }

            if (index[0] < index[1] && index[0] < index[2] && index[0] < index[3]){
                return index[0];
            } else if (index[1] < index[0] && index[1] < index[2] && index[1] < index[3]){
                return index[1];
            } else if (index[2] < index[1] && index[2] < index[0] && index[2] < index[3]){
                return index[2];
            } else if (index[3] < index[1] && index[3] < index[2] && index[3] < index[0]){
                return index[3];
            } else {
                return texto.length();
            }
        }




    }

    public String subString(int inicio){
        return texto.substring(inicio);
    }

    public String subString(int inicio, int end){
    return texto.substring(inicio, end);
    }

    public int indexOf(String str){
        return texto.indexOf(str);
    }

    public int indexOf(String str, int fromIndex){
        return texto.indexOf(str, fromIndex);
    }

    public MyString delete(int start, int end){
        return new MyString(texto.delete(start, end).toString());
    }

    public String reverse(){
        return texto.reverse().toString();
    }

    public String replace(int start, int end, String str){
        return texto.replace(start, end, str).toString();
    }

}
