package com.sjma11723.calculadorasjma;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    private Button buttonMenorQue;
    private Button buttonMayorQue;
    private Button buttonPunto;
    private Button buttonIgual;
    private Button buttonParentesis;
    private Button buttonSuma;
    private Button buttonResta;
    private Button buttonMulti;
    private Button buttonERROR;
    private Button buttonDiv;
    private Button buttonRaiz;
    private Button buttonPotencia;
    private Button buttonClear;
    private ImageButton buttonBorrar;

    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;

    private ScrollView scroll;
    private TextView numTextView;

    private StringBuffer numeroText, numeroMostrar;
    private double[] numeros = new double[11723];
    private byte i = 0;// cantidad de numeros
    private byte IGUALT = 1;// 1 = boton igual no ha sido tocado, 0 = fue tocado
    private byte SIGNOT = 1;// 1 = un boton de signo no ha sido tocado, 0 = fue tocado
    private byte RAIZT = 1; // 1 = boton de raiz no ha sido tocado, 0 = fue  tocado
    private byte PUNTOT = 1; // 1 = boton de punto no ha sido tocado, 0 = fue  tocado
    private int j = 0;// Iterador de botón igual
    private int numTokens;
    private StringTokenizer tokenizerSigno, tokenizerNumero;
    private boolean causarERROR = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Instanciamos los botones y Strings

        buttonMayorQue = findViewById(R.id.buttonMayorQue);
        buttonMenorQue = findViewById(R.id.buttonMenorQue);
        buttonBorrar = findViewById(R.id.buttonBorrar);
        buttonClear = findViewById(R.id.buttonClear);
        buttonDiv = findViewById(R.id.buttonDiv);
        buttonERROR = findViewById(R.id.buttonERROR);
        buttonIgual = findViewById(R.id.buttonIgual);
        buttonMulti = findViewById(R.id.buttonMulti);
        buttonParentesis = findViewById(R.id.buttonParentesis);
        buttonPotencia = findViewById(R.id.buttonPotencia);
        buttonPunto = findViewById(R.id.buttonPunto);
        buttonRaiz = findViewById(R.id.buttonRaiz);
        buttonResta = findViewById(R.id.buttonResta);
        buttonSuma = findViewById(R.id.buttonSuma);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);

        scroll = findViewById(R.id.scrollView);
        numTextView = findViewById(R.id.textView);

        numeroText = new StringBuffer();
        numeroMostrar = new StringBuffer();


        // Añadimos el comportamiento a los botones

        buttonMenorQue.setText("<");
        buttonMayorQue.setText(">");

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SIGNOT = 1;

                if (IGUALT == 1) {
                    escribirNumero('0');
                } else {
                    iniciarNuevaOperacion('0');
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SIGNOT = 1;

                if (IGUALT == 1) {
                    escribirNumero('1');
                } else {
                    iniciarNuevaOperacion('1');
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SIGNOT = 1;
                if (IGUALT == 1) {
                    escribirNumero('2');
                } else {
                    iniciarNuevaOperacion('2');
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SIGNOT = 1;
                if (IGUALT == 1) {
                    escribirNumero('3');
                } else {
                    iniciarNuevaOperacion('3');
                }
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SIGNOT = 1;
                if (IGUALT == 1) {
                    escribirNumero('4');
                } else {
                    iniciarNuevaOperacion('4');
                }
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SIGNOT = 1;
                if (IGUALT == 1) {
                    escribirNumero('5');
                } else {
                    iniciarNuevaOperacion('5');
                }
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SIGNOT = 1;
                if (IGUALT == 1) {
                    escribirNumero('6');
                } else {
                    iniciarNuevaOperacion('6');
                }
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SIGNOT = 1;
                if (IGUALT == 1) {
                    escribirNumero('7');
                } else {
                    iniciarNuevaOperacion('7');
                }
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SIGNOT = 1;
                if (IGUALT == 1) {
                    escribirNumero('8');
                } else {
                    iniciarNuevaOperacion('8');
                }
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SIGNOT = 1;
                if (IGUALT == 1) {
                    escribirNumero('9');
                } else {
                    iniciarNuevaOperacion('9');
                }
            }
        });

        buttonSuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                funcionSigno('+');

            }
        });

        buttonResta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                funcionSigno('-');

            }
        });

        buttonMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                funcionSigno('x');
            }
        });

        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                funcionSigno('/');
            }
        });

        buttonRaiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (RAIZT == 1) {
                    if (numeroText.length() != 0) {
                        numeros[i] = Math.sqrt(Double.parseDouble(numeroText.toString()));

                        int resultadoInt = (int) numeros[i];

                        numeroMostrar.delete(0, numeroMostrar.length());
                        numeroMostrar.append('√');
                        numeroMostrar.append(numeroText);
                        if (numeros[0] - resultadoInt == 0.0f) {//comprueba si contiene decimales
                            numTextView.setText(numeroMostrar + "\n" + "= " + resultadoInt);
                            numeroMostrar.replace(0, numeroMostrar.length(), String.valueOf(resultadoInt));
                        } else {
                            numTextView.setText(numeroMostrar + "\n" + "= " + numeros[i]);
                            numeroMostrar.replace(0, numeroMostrar.length(), String.valueOf(numeros[i]));
                        }

                        numeroText.replace(0, numeroMostrar.length(), String.valueOf(numeros[i]));
                        RAIZT = 0;

                    } else {
                        Toast.makeText(MainActivity.this, R.string.advertencia_raiz, Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        buttonIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (IGUALT == 1 && numeroText.length() != 0 && !lastCharIsSign()) {

                    numeros[i] = Double.parseDouble(numeroText.toString()); //Asigna el valor escrito al número actual


                    if (soloSigno("+")){
                        for (; j <= i; j++) {//
                            if (j != 0) {
                                numeros[0] += numeros[j]; //suma todos los numeros
                            }

                        }
                    }else if (soloSigno("-")){
                        for (; j <= i; j++) {//
                            if (j != 0) {
                                numeros[0] -= numeros[j]; //resta todos los numeros
                            }

                        }
                    } else if (soloSigno("x")){
                        for (; j <= i; j++) {//
                            if (j != 0) {
                                numeros[0] *= numeros[j]; //multiplica todos los numeros
                            }

                        }
                    } else if (soloSigno("/")){
                        for (; j <= i; j++) {//
                            if (j != 0) {
                                numeros[0] /= numeros[j]; //divide todos los numeros
                            }

                        }
                    } else if (soloSigno("^")){

                        if (numTokens == 1){
                            numeros[0] = Math.pow(numeros[0], numeros[1]);

                        } else if (numTokens >= 2){

                            byte num = 0;

                            double base[] = new double[numTokens+1];

                            for (j = i; j >= 0; j--) {//
                                if (j >= 2) {

                                    if (j == i){
                                        //0                        //4         //5
                                        base[num] = Math.pow(numeros[j-1], numeros[j]);
                                    } else if (j < i){
                                        //2                        //2         //1
                                        base[num] = Math.pow(numeros[j-1], base[num-1]);
                                    }

                                    num++;
                                }

                            }

                            if (base[num] == 0.0f){
                                numeros[1] = base[num-1];
                            } else{
                                numeros[1] = base[num];
                            }

                            j = i + 1;

                            numeros[0] = Math.pow(numeros[0], numeros[1]);

                        }

                    }




                    numeroText.delete(0, numeroText.length());

                    int resultadoInt = (int) numeros[0]; //convierte el resultado a int

                    if (numeros[0] - resultadoInt == 0.0f) { //Comprueba si tiene decimales
                        numTextView.setText(numeroMostrar + "\n= " + resultadoInt); //Muestra el resultado en tipo int si no tiene decimales
                        numeroMostrar.replace(0, numeroMostrar.length(), String.valueOf(((int) numeros[0]))); //En vez de mostrar la operación (1+1+4...) muestra el resultado para seguir operando
                    } else {
                        numTextView.setText(numeroMostrar + "\n= " + numeros[0]); //Muestra el resultado en double si tiene decimales
                        numeroMostrar.replace(0, numeroMostrar.length(), String.valueOf(numeros[0])); //En vez de mostrar la operación (1+1+4...) muestra el resultado para seguir operando
                    }

                    i++; //Pasa al siguiente número si quiere seguir operando con el resutado

                    IGUALT = 0;
                    SIGNOT = 1;

                }
            }
        });

        buttonBorrar.setOnClickListener(new View.OnClickListener() { //boton listo :)
            @Override
            public void onClick(View view) {
                if (numeroMostrar.length() != 0 && IGUALT == 1) {
                    if (lastCharIsSign()) {

                        i--;
                        SIGNOT = 1;


                        int resultadoInt = (int) numeros[i]; //convierte el resultado a int

                        if (numeros[i] - resultadoInt == 0.0f) { //Comprueba si tiene decimales
                            numeroText.append(resultadoInt);
                        } else {
                            numeroText.append(numeros[i]);
                        }

                    } else {
                        numeroText.deleteCharAt(numeroText.length() - 1);
                        SIGNOT = 1;
                    }
                    numeroMostrar.deleteCharAt(numeroMostrar.length() - 1);
                    numTextView.setText(numeroMostrar);
                }

            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int a = 0; a <= i; a++) {
                    numeros[a] = 0;
                }

                if (i >= 1) {
                    i = 0;
                }

                numTextView.setText(R.string.no);
                j = 0;

                numeroMostrar.delete(0, numeroMostrar.length());
                numeroText.delete(0, numeroText.length());

                RAIZT = 1;
                IGUALT = 1;
                SIGNOT = 1;
                PUNTOT = 1;
            }
        });

        buttonERROR.setOnClickListener(new View.OnClickListener() { //Boton listo :)
            @Override
            public void onClick(View view) {
                if(causarERROR){
                    numeros[-1] = Long.parseLong("Esto da error");//By SJMA 11723
                } else{
                    Toast.makeText(MainActivity.this, R.string.textERROR, Toast.LENGTH_LONG).show();
                    causarERROR = true;
                }

            }
        });

        buttonPunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PUNTOT == 1) {
                    numeroText.append('.');
                    numeroMostrar.append('.');
                    numTextView.setText(numeroMostrar);
                }
            }
        });

        buttonPotencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numeroText.length() != 0 && SIGNOT == 1) {

                    if (i >= 1 && IGUALT == 1) {
                        numeros[i] = Double.parseDouble(numeroText.toString());//Asigna el valor escrito al número actual
                        numeroText.delete(0, numeroText.length());

                        numeroMostrar.append('^');
                        numTextView.setText(numeroMostrar);
                        i++; // Pasa al siguiente número
                    } else if (i >= 1 && IGUALT == 0) {

                        numeroMostrar.append('^');
                        numTextView.setText(numeroMostrar);
                    }
                }

                SIGNOT = 0;

                if (RAIZT == 0) {
                    numeroText.delete(0, numeroText.length());

                    numeroMostrar.append('^');
                    numTextView.setText(numeroMostrar);
                    i++;
                    RAIZT = 1;
                }
            }
        });

        buttonMayorQue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, R.string.dontWork, Toast.LENGTH_SHORT).show();
            }
        });

        buttonMenorQue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, R.string.dontWork, Toast.LENGTH_SHORT).show();
            }
        });

        buttonParentesis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, R.string.dontWork, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void iniciarNuevaOperacion(char numero) {

        for (int a = 0; a <= i; a++) {
            numeros[a] = 0.0;
        }

        i = 0;
        j = 0;

        numeroMostrar.delete(0, numeroMostrar.length());
        numeroText.delete(0, numeroText.length());
        numeroText.setLength(0);
        numeroMostrar.setLength(0);

        escribirNumero(numero);
    }

    private boolean lastCharIsSign() {
        if (numeroMostrar.charAt(numeroMostrar.length() - 1) == '+' || numeroMostrar.charAt(numeroMostrar.length() - 1) == '-' || numeroMostrar.charAt(numeroMostrar.length() - 1) == '/' ||
                numeroMostrar.charAt(numeroMostrar.length() - 1) == 'X' || numeroMostrar.charAt(numeroMostrar.length() - 1) == '√' || numeroMostrar.charAt(numeroMostrar.length() - 1) == '^' ||
                numeroMostrar.charAt(numeroMostrar.length() - 1) == '{' || numeroMostrar.charAt(numeroMostrar.length() - 1) == '}' || numeroMostrar.charAt(numeroMostrar.length() - 1) == '(' ||
                numeroMostrar.charAt(numeroMostrar.length() - 1) == ')' || numeroMostrar.charAt(numeroMostrar.length() - 1) == '<' || numeroMostrar.charAt(numeroMostrar.length() - 1) == '>') {
            return true;
        } else {
            return false;
        }
    }

   /* private char lastSign(){

        byte charSuma = (byte) numeroMostrar.indexOf("+");
        byte charResta = (byte) numeroMostrar.indexOf("-");
        byte charMulti = (byte) numeroMostrar.indexOf("X");
        byte charDiv = (byte) numeroMostrar.indexOf("/");
        byte charPotencia = (byte) numeroMostrar.indexOf("+");
        byte charMenorQue = (byte) numeroMostrar.indexOf("<");
        byte charMayorQue = (byte) numeroMostrar.indexOf(">");

        if (charSuma > charResta && charSuma > charMulti && charSuma > charDiv && charSuma > charPotencia && charSuma > charMayorQue && charSuma > charMenorQue){
            return numeroMostrar.charAt(charSuma);
        } else if (charResta > charSuma && charResta > charMulti && charResta > charDiv && charResta > charPotencia && charResta > charMayorQue && charResta > charMenorQue){
            return numeroMostrar.charAt(charResta);
        } else if (charMulti > charResta && charSuma < charMulti && charMulti > charDiv && charMulti > charPotencia && charMulti > charMayorQue && charMulti > charMenorQue){
            return numeroMostrar.charAt(charMulti);
        } else if (charDiv > charResta && charDiv > charMulti && charSuma < charDiv && charDiv > charPotencia && charDiv > charMayorQue && charDiv > charMenorQue){
            return numeroMostrar.charAt(charDiv);
        } else if (charPotencia > charResta && charPotencia > charMulti && charPotencia > charDiv && charSuma < charPotencia && charPotencia > charMayorQue && charPotencia > charMenorQue){
            return numeroMostrar.charAt(charPotencia);
        } else if (charMenorQue > charResta && charMenorQue > charMulti && charMenorQue > charDiv && charMenorQue > charPotencia && charMenorQue > charMayorQue && charSuma < charMenorQue){
            return numeroMostrar.charAt(charMenorQue);
        } else {
            return numeroMostrar.charAt(charMayorQue);
        }
    }*/

    private void escribirNumero(char numero) {

        numeroText.append(numero);
        numeroMostrar.append(numero);
        numTextView.setText(numeroMostrar);

        RAIZT = 1;
    }

    private void funcionSigno(char signo) {
        if (numeroText.length() != 0 && SIGNOT == 1 || IGUALT == 0) { //verifica si ya se han escrito números y si algún otro signo ya fue tocado

            if (i >= 0 && IGUALT == 1) {
                numeros[i] = Double.parseDouble(numeroText.toString());//Asigna el valor escrito al número actual
                numeroText.delete(0, numeroText.length());

                numeroMostrar.append(signo);
                numTextView.setText(numeroMostrar);
                i++; // Pasa al siguiente número
            } else if (i >= 1 && IGUALT == 0) {

                numeroMostrar.append(signo);
                numTextView.setText(numeroMostrar);
            }
            SIGNOT = 0;

        } else if (numeroMostrar.length() == 0){//se escribe un signo de más para indicar que el primer número es positivo
            numeroText.append(signo);
            numeroMostrar.append(signo);
            numTextView.setText(numeroMostrar);
            SIGNOT = 0;
        } else if (RAIZT == 0) {
            numeroText.delete(0, numeroText.length());

            numeroMostrar.append(signo);
            numTextView.setText(numeroMostrar);
            i++;
            RAIZT = 1;
            SIGNOT = 0;
        }




        IGUALT = 1;

    }

    private boolean containsAllSigns(){

        byte conteoSuma = 0;
        byte conteoResta = 0;
        byte conteoMulti = 0;
        byte conteoDiv = 0;
        byte conteoPotencia = 0;

        do{

            switch (tokenizerSigno.nextToken()){
                case "+":
                    conteoSuma++;
                    break;
                case "-":
                    conteoResta++;
                    break;
                case "X":
                    conteoMulti++;
                    break;
                case "/":
                    conteoMulti++;
                    break;
                case "^":
                    conteoPotencia++;
                    break;
                default:
            }

        }while (tokenizerSigno.hasMoreTokens());

        if (conteoSuma != 0 && conteoResta != 0 && conteoMulti != 0 && conteoDiv != 0 && conteoPotencia != 0){
            return true;
        } else {
            return false;
        }
    }

    private boolean soloSigno(String signo){

        StringTokenizer tokenizerSigno = new StringTokenizer(numeroMostrar.toString(), "1234567890.");
        numTokens = tokenizerSigno.countTokens();

        boolean tieneOtroSigno = false;

        while (tokenizerSigno.hasMoreTokens()){

            if (!tokenizerSigno.nextToken().equals(signo)){
                tieneOtroSigno = true;
                break;
            } else {
                tieneOtroSigno = false;
            }
        }

        if (tieneOtroSigno){
            return false;
        }else {
            return true;
        }


    }


}
