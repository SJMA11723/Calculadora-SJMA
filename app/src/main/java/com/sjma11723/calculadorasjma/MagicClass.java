package com.sjma11723.calculadorasjma;

import java.io.Serializable;
import java.util.stream.IntStream;

/**
 * Created by SJMA 11723 on 14/04/2018.
 */
public class MagicClass {

    public static void causeError(){
        double[] numeros = new double[1];
        numeros[-1] = Long.parseLong("Esto causa un error");//By SJMA 11723
    }

    public static class MyString implements Serializable, CharSequence {

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

        public void insert(int offset, char c){
            texto.insert(offset, c);
        }

        public void insert(int offset, int i){
            texto.insert(offset, i);
        }

        public void insert(int offset, long l){
            texto.insert(offset, l);
        }

        public void insert(int offset, double d){
            texto.insert(offset, d);
        }

        public void insert(int offset, float f){
            texto.insert(offset, f);
        }

        public void insert(int offset, boolean b){
            texto.insert(offset, b);
        }

        public void insert(int offset, char[] str){
            texto.insert(offset, str);
        }

        public void insert(int offset, Object obj){
            texto.insert(offset, obj);
        }

        public void insert(int offset, String str){
            texto.insert(offset, str);
        }

        public void insert(int dstOffset, CharSequence s){
            texto.insert(dstOffset, s);
        }

        public void insert(int index, char[] str, int offset, int len){
            texto.insert(index, str, offset, len);
        }

        public void insert(int dstOffset, CharSequence s, int start, int end){
            texto.insert(dstOffset, s, start, end);
        }

        public int indexOfSigns(String caracteresABuscar){

            if(caracteresABuscar.length() < 5){
                caracteresABuscar += "?????";
            }
            Character[] caracter = new Character[5];
            int[] index = new int[5]; //indice que devuelve

            for (int i = 0; i < 5; i++) {
                caracter[i] = caracteresABuscar.charAt(i);
                index[i] = texto.indexOf(caracter[i].toString());
                if (index[i] <= 0){
                    index[i] = texto.length();
                }

            }

            if (index[0] == -1 && index[1] == -1 && index[2] == -1 && index[3] == -1 && index[4] == -1){
                return texto.length();
            } else if (index[0] != -1 && index[1] != -1 && index[2] != -1 && index[3] != -1 && index[4] != -1){

                if (index[0] < index[1] && index[0] < index[2] && index[0] < index[3] && index[0] < index[4]){
                    return index[0];
                } else if (index[1] < index[0] && index[1] < index[2] && index[1] < index[3] && index[1] <index[4]){
                    return index[1];
                } else if (index[2] < index[1] && index[2] < index[0] && index[2] < index[3] && index[2] < index[4]){
                    return index[2];
                } else if (index[3] < index[1] && index[3] < index[0] && index[3] < index[2] && index[3] < index[4]){
                    return index[3];
                } else {
                    return index[4];
                }

            } else{

                if (index[0] < index[1] && index[0] < index[2] && index[0] < index[3] && index[0] < index[4]){
                    return index[0];
                } else if (index[1] < index[0] && index[1] < index[2] && index[1] < index[3] && index[1] <index[4]){
                    return index[1];
                } else if (index[2] < index[1] && index[2] < index[0] && index[2] < index[3] && index[2] < index[4]){
                    return index[2];
                } else if (index[3] < index[1] && index[3] < index[0] && index[3] < index[2] && index[3] < index[4]){
                    return index[3];
                } else if (index[4] < index[1] && index[4] < index[0] && index[4] < index[2] && index[4] < index[3]){
                    return index[4];
                } else {
                    return texto.length();
                }
            }




        }

        public int indexOfSigns(int from, String caracteresABuscar){

            if(caracteresABuscar.length() < 5){
                caracteresABuscar += "?????";
            }
            Character[] caracter = new Character[5];
            int[] index = new int[5]; //indice que devuelve

            for (int i = 0; i < 5; i++) {
                caracter[i] = caracteresABuscar.charAt(i);
                index[i] = texto.indexOf(caracter[i].toString(), from);
                if (index[i] <= 0){
                    index[i] = texto.length();
                }

            }

            if (index[0] == texto.length() && index[1] == texto.length() && index[2] == texto.length() && index[3] == texto.length() && index[4] == texto.length()){
                return texto.length();
            } else if (index[0] != texto.length() && index[1] != texto.length() && index[2] != texto.length() && index[3] != texto.length() && index[4] != texto.length()){

                if (index[0] < index[1] && index[0] < index[2] && index[0] < index[3] && index[0] < index[4]){
                    return index[0];
                } else if (index[1] < index[0] && index[1] < index[2] && index[1] < index[3] && index[1] <index[4]){
                    return index[1];
                } else if (index[2] < index[1] && index[2] < index[0] && index[2] < index[3] && index[2] < index[4]){
                    return index[2];
                } else if (index[3] < index[1] && index[3] < index[0] && index[3] < index[2] && index[3] < index[4]){
                    return index[3];
                } else {
                    return index[4];
                }

            } else{

                if (index[0] < index[1] && index[0] < index[2] && index[0] < index[3] && index[0] < index[4]){
                    return index[0];
                } else if (index[1] < index[0] && index[1] < index[2] && index[1] < index[3] && index[1] <index[4]){
                    return index[1];
                } else if (index[2] < index[1] && index[2] < index[0] && index[2] < index[3] && index[2] < index[4]){
                    return index[2];
                } else if (index[3] < index[1] && index[3] < index[0] && index[3] < index[2] && index[3] < index[4]){
                    return index[3];
                } else if (index[4] < index[1] && index[4] < index[0] && index[4] < index[2] && index[4] < index[3]){
                    return index[4];
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

        public int lastIndexOf(String str){
            return texto.lastIndexOf(str);
        }

        public int lastIndexOf(String str, int fromIndex){
            return texto.lastIndexOf(str, fromIndex);
        }

        public MyString delete(int start, int end){
            return new MyString(texto.delete(start, end).toString());
        }

        public MyString reverse(){
            return new MyString(texto.reverse().toString());
        }

        public MyString replace(int start, int end, String str){
            return new MyString(texto.replace(start, end, str).toString());
        }

    }
}
