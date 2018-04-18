package com.sjma11723.calculadorasjma;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.StringTokenizer;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private ImageButton buttonMenorQue;
    private ImageButton buttonMayorQue;
    private ImageButton buttonPunto;
    private ImageButton buttonIgual;
    private ImageButton buttonParentesisApertura;
    private ImageButton buttonParentesisCierre;
    private ImageButton buttonSuma;
    private ImageButton buttonResta;
    private ImageButton buttonMulti;
    private ImageButton buttonERROR;
    private ImageButton buttonDiv;
    private ImageButton buttonRaiz;
    private ImageButton buttonPotencia;
    private ImageButton buttonClear;
    private ImageButton buttonBorrar;

    private ImageButton button0;
    private ImageButton button1;
    private ImageButton button2;
    private ImageButton button3;
    private ImageButton button4;
    private ImageButton button5;
    private ImageButton button6;
    private ImageButton button7;
    private ImageButton button8;
    private ImageButton button9;

    private ScrollView scroll;
    private TextView numTextView;

    private StringBuffer numeroText, numeroMostrar;
    private double[] numeros = new double[11723];
    private byte i;// cantidad de numeros
    private boolean igualIsTouched = false;// 1 = boton igual no ha sido tocado, 0 = fue tocado
    private boolean signoIsTouched = false;// 1 = un boton de signo no ha sido tocado, 0 = fue tocado
    private boolean raizIsTouched = false; // 1 = boton de raiz no ha sido tocado, 0 = fue  tocado
    private boolean menorIsTouched = false;
    private boolean mayorIsTouched = false;//Lo mismo
    private boolean puntoIsTouched = false; // 1 = boton de punto no ha sido tocado, 0 = fue  tocado
    private boolean parentIsTouched = false;
    private boolean porcentajeIsTouched = false;
    private int j;// Iterador de botón igual
    private boolean resultBoolean;
    private int numTokens;
    private boolean causarERROR = false;
    private double termino[] = new double[2];
    private byte conteoSuma = 0;
    private byte conteoResta = 0;
    private byte conteoMulti = 0;
    private byte conteoDiv = 0;
    private byte conteoPotencia = 0;
    private byte conteoPInicio = 0;
    private byte conteoPCierre = 0;
    private byte conteoPorcentaje = 0;
    private MagicClass.MyString operacion, parentesisActual;

    private Realm realm;
    private Datos datosPersistentes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        buttonMayorQue = findViewById(R.id.buttonMayorQue);
        buttonMenorQue = findViewById(R.id.buttonMenorQue);
        buttonBorrar = findViewById(R.id.buttonBorrar);
        buttonClear = findViewById(R.id.buttonClear);
        buttonDiv = findViewById(R.id.buttonDiv);
        buttonERROR = findViewById(R.id.buttonERROR);
        buttonIgual = findViewById(R.id.buttonIgual);
        buttonMulti = findViewById(R.id.buttonMulti);
        buttonParentesisApertura = findViewById(R.id.buttonParentesisApertura);
        buttonParentesisCierre = findViewById(R.id.buttonParentesisCierre);
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

        realm = Realm.getDefaultInstance();
        datosPersistentes = (realm.where(Datos.class).findFirst() != null) ? realm.where(Datos.class).findFirst() : new Datos("", "", "");

        i = (byte) datosPersistentes.getIteradorNumeros();
        j = (byte) datosPersistentes.getIteradorBotonIgual();

        if (!countMoreSigns(datosPersistentes.getDigitoEnPantalla()) && datosPersistentes.getDigitoEnPantalla().length() != 0 && datosPersistentes.getPriority().equals("digito")){
            numeroText.append(datosPersistentes.getDigitoEnPantalla());
            numeroMostrar.append(datosPersistentes.getDigitoEnPantalla());
            numTextView.setText(numeroText);
        } else if (countMoreSigns(datosPersistentes.getDigitoEnPantalla()) && datosPersistentes.getPriority().equals("digito")){
            StringTokenizer tokenizer = new StringTokenizer(datosPersistentes.getDigitoEnPantalla(), "/x-+^()");
            int countTokens = tokenizer.countTokens();
            for (int a = 0; a < countTokens; a++){
                numeros[a] = Double.parseDouble(tokenizer.nextToken());
                if (a == countTokens-1){
                    numeroText.append(numeros[a]);
                }
            }
            numeroMostrar.append(datosPersistentes.getDigitoEnPantalla());
            numTextView.setText(datosPersistentes.getDigitoEnPantalla());
        } else if (datosPersistentes.getPriority().equals("resultado")){
            numeroMostrar.append(datosPersistentes.getOperacionResultado());
            numeros[0] = Double.parseDouble(datosPersistentes.getResultado());
            numTextView.setText(numeroMostrar.toString() + R.string.saltoDeLinea + "= "+ numeros[0]);
        }

        // Añadimos el comportamiento a los botones

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signoIsTouched = false;

                if (!igualIsTouched) {
                    escribirNumero('0');
                } else {
                    iniciarNuevaOperacion('0');
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signoIsTouched = false;

                if (!igualIsTouched) {
                    escribirNumero('1');
                } else {
                    iniciarNuevaOperacion('1');
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signoIsTouched = false;
                if (!igualIsTouched) {
                    escribirNumero('2');
                } else {
                    iniciarNuevaOperacion('2');
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signoIsTouched = false;
                if (!igualIsTouched) {
                    escribirNumero('3');
                } else {
                    iniciarNuevaOperacion('3');
                }
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signoIsTouched = false;
                if (!igualIsTouched) {
                    escribirNumero('4');
                } else {
                    iniciarNuevaOperacion('4');
                }
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signoIsTouched = false;
                if (!igualIsTouched) {
                    escribirNumero('5');
                } else {
                    iniciarNuevaOperacion('5');
                }
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signoIsTouched = false;
                if (!igualIsTouched) {
                    escribirNumero('6');
                } else {
                    iniciarNuevaOperacion('6');
                }
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signoIsTouched = false;
                if (!igualIsTouched) {
                    escribirNumero('7');
                } else {
                    iniciarNuevaOperacion('7');
                }
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signoIsTouched = false;
                if (!igualIsTouched) {
                    escribirNumero('8');
                } else {
                    iniciarNuevaOperacion('8');
                }
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signoIsTouched = false;
                if (!igualIsTouched) {
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

                if (!raizIsTouched) {
                    if (numeroText.length() != 0 && i == 0) {
                        numeros[i] = Math.sqrt(Double.parseDouble(numeroText.toString()));

                        actualizarOperacionResutado();
                        actualizarResultado();

                        numeroMostrar.delete(0, numeroMostrar.length());
                        numeroMostrar.append('√');
                        numeroMostrar.append(numeroText);
                        if (!comprobarDecimales(numeros[i])) {//comprueba si contiene decimales
                            numTextView.setText(numeroMostrar + "\n" + "= " + (int)numeros[i]);
                            numeroMostrar.replace(0, numeroMostrar.length(), String.valueOf((int)numeros[i]));
                        } else {
                            numTextView.setText(numeroMostrar + "\n" + "= " + numeros[i]);
                            numeroMostrar.replace(0, numeroMostrar.length(), String.valueOf(numeros[i]));
                        }

                        numeroText.replace(0, numeroMostrar.length(), String.valueOf(numeros[i]));
                        raizIsTouched = true;
                        igualIsTouched = true;

                    } else {
                        Toast.makeText(MainActivity.this, R.string.advertencia_raiz, Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        buttonIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (numeroMostrar.length() != 0 && !countMoreSigns(numeroMostrar.toString())){
                    int numero = Integer.parseInt(numeroMostrar.toString());
                    if (numero % 11723 == 0 && numero != 11723){
                        if (!datosPersistentes.is_11723Found()){
                            actualizarNumerosMagicoEncontrados();
                            actualizarNumeroEcontrado(11723);
                        }
                        showAlertForMagicNumber(11723, 0, true, "SJMA");
                    } else if (numero % 1020503 == 0 && numero != 1020503){
                        if (!datosPersistentes.is_1020503Found()){
                            actualizarNumerosMagicoEncontrados();
                            actualizarNumeroEcontrado(1020503);
                        }
                        showAlertForMagicNumber(1020503, 0, true, "Cristina");
                    } else if (numero % 210703 == 0 && numero != 210703){
                        if (!datosPersistentes.is_210703Found()){
                            actualizarNumerosMagicoEncontrados();
                            actualizarNumeroEcontrado(210703);
                        }
                        showAlertForMagicNumber(210703, R.drawable.elias, false, "Elías");
                    } else if (numero % 280203 == 0 && numero != 280203){
                        if (!datosPersistentes.is_280203Found()){
                            actualizarNumerosMagicoEncontrados();
                            actualizarNumeroEcontrado(280203);
                        }
                        showAlertForMagicNumber(280203, R.drawable.paulina, true, "Paulina");
                    }
                } else if (!igualIsTouched && numeroText.length() != 0 && (!lastCharIsSign(numeroMostrar.toString()) || numeroMostrar.charAt(numeroMostrar.length()-1) == ')') && comprobarParentesis()) {

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

                                for (j = i; j >= 0; j--)                                            {
                                    if (j >= 2)                                                     {
                                        if (j == i)                                                 {
                                            base[num] = Math.pow(numeros[j-1], numeros[j])          ;
                                        } else if (j < i)                                           {
                                            base[num] = Math.pow(numeros[j-1], base[num-1])         ;
                                                                                                    }
                                        num++                                                       ;
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

                        } else if (soloSigno("%")){
                            numeros[0] = numeros[1] * (numeros[0]/100);
                        } else if (countMoreSigns(numeroMostrar.toString())){
                            operacion = new MagicClass.MyString(numeroMostrar.toString());

                            while (conteoPInicio != 0){
                                parentesisActual = new MagicClass.MyString(operacion.subString(operacion.lastIndexOf("(")+1, operacion.indexOf(")", operacion.lastIndexOf("("))));
                                countMoreSigns(parentesisActual.toString());
                                operarParentesis();
                                if (operacion.lastIndexOf("(") >= 1){
                                    if (operacion.subString(operacion.lastIndexOf("(")-1, operacion.lastIndexOf("(")).contains("^") || operacion.subString(operacion.lastIndexOf("(")-1, operacion.lastIndexOf("(")).contains("+") || operacion.subString(operacion.lastIndexOf("(")-1, operacion.lastIndexOf("(")).contains("-") || operacion.subString(operacion.lastIndexOf("(")-1, operacion.lastIndexOf("(")).contains("x") || operacion.subString(operacion.lastIndexOf("(")-1, operacion.lastIndexOf("(")).contains("/") || operacion.subString(operacion.lastIndexOf("(")-1, operacion.lastIndexOf("(")).contains("(")){
                                        if (operacion.subString(operacion.lastIndexOf("(")-1, operacion.lastIndexOf("(")).equals(operacion.subString(operacion.lastIndexOf("(")+1, operacion.lastIndexOf("(")+2))){
                                            deshacerParentesis(operacion.lastIndexOf("(")-1, operacion.indexOf(")", operacion.lastIndexOf("("))+1, parentesisActual.replace(0, 1, "+").toString());
                                        }else {
                                            deshacerParentesis(operacion.lastIndexOf("("), operacion.indexOf(")", operacion.lastIndexOf("("))+1, parentesisActual.toString());
                                        }
                                    } else {
                                        deshacerParentesis(operacion.lastIndexOf("("), operacion.indexOf(")", operacion.lastIndexOf("("))+1, "x" + parentesisActual.toString());
                                    }
                                } else {
                                    if ((operacion.subString(operacion.lastIndexOf(")")-1, operacion.indexOfSigns(operacion.lastIndexOf(")")-1, "()/-+x^")+1).contains("^") || operacion.subString(operacion.lastIndexOf(")")-1, operacion.indexOfSigns(operacion.lastIndexOf(")")-1, "()/-+x^")+1).contains("+") || operacion.subString(operacion.lastIndexOf(")")-1, operacion.indexOfSigns(operacion.lastIndexOf(")")-1, "()/-+x^")+1).contains("-") || operacion.subString(operacion.lastIndexOf(")")-1, operacion.indexOfSigns(operacion.lastIndexOf(")")-1, "()/-+x^")+1).contains("x") || operacion.subString(operacion.lastIndexOf(")")-1, operacion.indexOfSigns(operacion.lastIndexOf(")")-1, "()/-+x^")+1).contains("/") || operacion.subString(operacion.lastIndexOf(")")-1, operacion.indexOfSigns(operacion.lastIndexOf(")")-1, "()/-+x^")+1).contains(")")) && operacion.indexOfSigns(operacion.lastIndexOf(")")-1, "()/-+x^")+1 == operacion.length()){//comprueba si solo hay un par de parénteis y todas las operaciones están dentro de él;
                                        deshacerParentesis(operacion.lastIndexOf("("), operacion.indexOf(")", operacion.lastIndexOf("("))+1, parentesisActual.toString());
                                    }else if (operacion.subString(operacion.lastIndexOf(")")-1, operacion.indexOfSigns(operacion.lastIndexOf(")")-1, "()/-+x^")+2).contains("^") || operacion.subString(operacion.lastIndexOf(")")-1, operacion.indexOfSigns(operacion.lastIndexOf(")")-1, "()/-+x^")+2).contains("+") || operacion.subString(operacion.lastIndexOf(")")-1, operacion.indexOfSigns(operacion.lastIndexOf(")")-1, "()/-+x^")+2).contains("-") || operacion.subString(operacion.lastIndexOf(")")-1, operacion.indexOfSigns(operacion.lastIndexOf(")")-1, "()/-+x^")+2).contains("x") || operacion.subString(operacion.lastIndexOf(")")-1, operacion.indexOfSigns(operacion.lastIndexOf(")")-1, "()/-+x^")+2).contains("/") || (operacion.subString(operacion.lastIndexOf(")")-1, operacion.indexOfSigns(operacion.lastIndexOf(")")-1, "()/-+x^")+1).contains(")")) && operacion.indexOfSigns(operacion.lastIndexOf(")")-1, "()/-+x^")+1 == operacion.length()){//en otro caso, comprueba si hay signos después del paréntesis de cierre, si hay signos, agrega solo el resultado;
                                        deshacerParentesis(operacion.lastIndexOf("("), operacion.indexOf(")", operacion.lastIndexOf("("))+1, parentesisActual.toString());
                                    } else {
                                        deshacerParentesis(operacion.lastIndexOf("("), operacion.indexOf(")", operacion.lastIndexOf("("))+1, parentesisActual.toString() + "x");//si no hay signos, agrega un signo de multiplicación;
                                    }
                                }

                                countMoreSigns(operacion.toString());
                            }

                            countMoreSigns(operacion.toString());//cuenta otra vez los signos

                            for (int a = 0; a < conteoPotencia ; a++) {
                                int indexPotenciaActual = operacion.indexOf("^");
                                if (indexPotenciaActual != -1){
                                    operacion("^", indexPotenciaActual);
                                }

                            }

                            int conteoSigno = conteoDiv + conteoMulti;

                            for (int a = 0; a < conteoSigno; a++){
                                int indexDivisionActual = operacion.indexOf("/");
                                int indexMultiplicacionActual = operacion.indexOf("x");

                                if (indexDivisionActual != -1 && indexMultiplicacionActual != -1){
                                    if (indexMultiplicacionActual < indexDivisionActual){
                                        operacion("x", indexMultiplicacionActual);

                                    } else {
                                        operacion("/", indexDivisionActual);

                                    }


                                } else if (indexDivisionActual >= 1){
                                    operacion("/", indexDivisionActual);

                                } else if (indexMultiplicacionActual >= 1){
                                    operacion("x", indexMultiplicacionActual);
                                }

                            }


                            conteoSigno = conteoSuma + conteoResta;

                            for (int a = 0; a < conteoSigno; a++){
                                countMoreSigns(operacion.toString());
                                int indexSumaActual = operacion.indexOf("+");
                                int indexRestaActual = operacion.indexOf("-");

                                if (indexSumaActual != -1 && indexRestaActual != -1){
                                    if (indexRestaActual < indexSumaActual && indexRestaActual != 0){
                                        operacion("-", indexRestaActual);

                                    } else if (indexRestaActual == 0){
                                        operacionRestaNegativaConSuma("+", indexSumaActual);

                                    } else {
                                        operacion("+", indexSumaActual);
                                    }

                                } else if (indexSumaActual >= 1){
                                    operacion("+", indexSumaActual);

                                } else if (indexRestaActual >= 1){
                                    operacion("-", indexRestaActual);
                                } else if (indexRestaActual == 0 && conteoResta >= 2){
                                    operacion.insert(0, '0');
                                    indexRestaActual = operacion.lastIndexOf("-");
                                    operacionRestaNegativa("-", indexRestaActual);
                                }

                            }

                            numeros[0] = Double.valueOf(operacion.toString());

                        } else if (menorIsTouched){
                            if (numeros[0] < numeros[1]){
                                resultBoolean = true;
                            } else{
                                resultBoolean = false;
                            }

                        } else if (mayorIsTouched){
                            if (numeros[0] > numeros[1]){
                                resultBoolean = true;
                            } else{
                                resultBoolean = false;
                            }
                        }

                        actualizarOperacionResutado();
                        actualizarResultado();
                        actualizarPrioridad("resultado");


                        numeroText.delete(0, numeroText.length());

                        if (menorIsTouched || mayorIsTouched){
                            numTextView.setText(numeroMostrar+"\n= "+resultBoolean);
                        } else {

                            if (!comprobarDecimales(numeros[0])) { //Comprueba si tiene decimales
                                numTextView.setText(numeroMostrar + "\n= " + (int)numeros[0]); //Muestra el resultado en tipo int si no tiene decimales
                                numeroMostrar.replace(0, numeroMostrar.length(), String.valueOf(((int) numeros[0]))); //En vez de mostrar la operación (1+1+4...) muestra el resultado para seguir operando
                            } else {
                                numTextView.setText(numeroMostrar + "\n= " + numeros[0]); //Muestra el resultado en double si tiene decimales
                                numeroMostrar.replace(0, numeroMostrar.length(), String.valueOf(numeros[0])); //En vez de mostrar la operación (1+1+4...) muestra el resultado para seguir operando
                            }
                            i = 1; //Pasa a la posición número 2 (1) si quiere seguir operando con el resultado que se encuentra en la posición 1 (0);
                            j = 1;
                            signoIsTouched = false;
                        }

                        igualIsTouched = true;
                        actualizarIteradores();

                    }
            }
        });

        buttonBorrar.setOnClickListener(new View.OnClickListener() { //boton listo :)
            @Override
            public void onClick(View view) {
                if (numeroMostrar.length() != 0 && !igualIsTouched) {
                    if (lastCharIsSign(numeroMostrar.toString()) && numeroMostrar.charAt(numeroMostrar.length()-1) != '(') {

                        i--;
                        signoIsTouched = false;

                        if (!comprobarDecimales(numeros[i])) { //Comprueba si tiene decimales
                            numeroText.append((int)numeros[i]);
                        } else {
                            numeroText.append(numeros[i]);
                        }

                    } else if (numeroMostrar.charAt(numeroMostrar.length()-1) != ')' && numeroMostrar.charAt(numeroMostrar.length()-1) != '('){
                        numeroText.deleteCharAt(numeroText.length() - 1);
                        signoIsTouched = false;
                    }
                    numeroMostrar.deleteCharAt(numeroMostrar.length() - 1);
                    numTextView.setText(numeroMostrar);
                }

                actualizarDigitoEnPantalla();
                actualizarIteradores();

            }
        });

        buttonBorrar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (numeroMostrar.length() != 0 && !igualIsTouched) {
                    signoIsTouched = false;
                    numeroMostrar.delete(0, numeroMostrar.length());
                    numeroText.delete(0, numeroText.length());
                    numTextView.setText(null);
                    i = 0;
                    actualizarDigitoEnPantalla();
                    actualizarIteradores();
                    return true;
                } else {
                    return false;
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

                numTextView.setText(null);
                j = 0;

                numeroMostrar.delete(0, numeroMostrar.length());
                numeroText.delete(0, numeroText.length());

                raizIsTouched = false;
                igualIsTouched = false;
                signoIsTouched = false;
                puntoIsTouched = false;
                menorIsTouched = false;
                mayorIsTouched = false;
                actualizarDigitoEnPantalla();
                actualizarOperacionResutado();
                actualizarResultado();
                actualizarIteradores();
                actualizarPrioridad("none");
            }
        });

        buttonERROR.setOnClickListener(new View.OnClickListener() { //Boton listo :)
            @Override
            public void onClick(View view) {
                if(causarERROR){
                    MagicClass.causeError();
                } else{
                    Toast.makeText(MainActivity.this, R.string.textERROR, Toast.LENGTH_LONG).show();
                    causarERROR = true;
                }

            }
        });

        buttonPunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!puntoIsTouched) {
                    if (numeroText.length() == 0){
                        numeroText.append('0');
                        numeroMostrar.append('0');
                        numeroText.append('.');
                        numeroMostrar.append('.');
                        numTextView.setText(numeroMostrar);
                    }else {
                        numeroText.append('.');
                        numeroMostrar.append('.');
                        numTextView.setText(numeroMostrar);
                    }

                    puntoIsTouched = true;
                    actualizarDigitoEnPantalla();
                    actualizarPrioridad("digito");
                }
            }
        });

        buttonPotencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                funcionSigno('^');

            }
        });

        buttonMayorQue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i == 0 && numeroText.length() != 0){
                    numeros[0] = Double.parseDouble(numeroText.toString());
                    numeroMostrar.append('>');
                    numeroText.delete(0, numeroText.length());
                    numTextView.setText(numeroMostrar);
                    i++;
                    signoIsTouched = true;
                    mayorIsTouched = true;
                    puntoIsTouched = false;
                    actualizarDigitoEnPantalla();
                    actualizarPrioridad("digito");
                }

            }
        });

        buttonMenorQue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i == 0 && numeroText.length() != 0){
                    numeros[0] = Double.parseDouble(numeroText.toString());
                    numeroMostrar.append('<');
                    numeroText.delete(0, numeroText.length());
                    numTextView.setText(numeroMostrar);
                    i++;
                    signoIsTouched = true;
                    puntoIsTouched = false;
                    menorIsTouched = true;
                    actualizarDigitoEnPantalla();
                    actualizarPrioridad("digito");
                }

            }
        });

        buttonParentesisApertura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numeroMostrar.append("(");
                numTextView.setText(numeroMostrar);
                igualIsTouched = false;
                actualizarDigitoEnPantalla();
                actualizarPrioridad("digito");
            }
        });

        buttonParentesisCierre.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (numeroMostrar.length() != 0 && !lastCharIsSign(numeroMostrar.toString()) && !igualIsTouched && !comprobarParentesis()){
                    numeroMostrar.append(")");
                    numTextView.setText(numeroMostrar);
                    actualizarDigitoEnPantalla();
                    actualizarPrioridad("digito");
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.porentaje:

                if (!countMoreSigns(numeroMostrar.toString()) && !igualIsTouched && numeroText.length() != 0){
                    numeros[i] = Double.parseDouble(numeroText.toString());
                    numeroMostrar.append("%");
                    numeroText.delete(0, numeroText.length());
                    numTextView.setText(numeroMostrar);
                    i++;
                    porcentajeIsTouched = true;
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    private void actualizarDigitoEnPantalla(){
        realm.beginTransaction();
        datosPersistentes.setDigitoEnPantalla(numeroMostrar.toString());
        realm.copyToRealmOrUpdate(datosPersistentes);
        realm.commitTransaction();
    }

    private void actualizarOperacionResutado(){
        realm.beginTransaction();
        datosPersistentes.setOperacionResultado(numeroMostrar.toString());
        realm.copyToRealmOrUpdate(datosPersistentes);
        realm.commitTransaction();
    }

    private void actualizarResultado(){
        realm.beginTransaction();
        datosPersistentes.setResultado(String.valueOf(numeros[0]));
        realm.copyToRealmOrUpdate(datosPersistentes);
        realm.commitTransaction();
    }

    private void actualizarPrioridad(String prioridad){
        realm.beginTransaction();
        datosPersistentes.setPriority(prioridad);
        realm.copyToRealmOrUpdate(datosPersistentes);
        realm.commitTransaction();
    }

    private void actualizarNumerosMagicoEncontrados(){
        realm.beginTransaction();
        datosPersistentes.setNumerosMagicosEncontrados(datosPersistentes.getNumerosMagicosEncontrados()+1);
        realm.copyToRealmOrUpdate(datosPersistentes);
        realm.commitTransaction();
    }

    private void actualizarNumeroEcontrado(final int numeroEcontrado){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                switch (numeroEcontrado){
                    case 11723:
                        datosPersistentes.set_11723Found(true);
                        break;
                    case 1020503:
                        datosPersistentes.set_1020503Found(true);
                        break;
                    case 210703:
                        datosPersistentes.set_210703Found(true);
                        break;
                    case 280203:
                        datosPersistentes.set_280203Found(true);
                        break;
                }

                realm.copyToRealmOrUpdate(datosPersistentes);
            }
        });
    }

    private void actualizarIteradores(){
        realm.beginTransaction();
        datosPersistentes.setIteradorNumeros(i);
        datosPersistentes.setIteradorBotonIgual(j);
        realm.copyToRealmOrUpdate(datosPersistentes);
        realm.commitTransaction();
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
        actualizarIteradores();
        igualIsTouched = false;
    }

    private boolean lastCharIsSign(String numeroMostrar) {

        if (numeroMostrar.charAt(numeroMostrar.length() - 1) == '+' || numeroMostrar.charAt(numeroMostrar.length() - 1) == '-' || numeroMostrar.charAt(numeroMostrar.length() - 1) == '/' ||
                numeroMostrar.charAt(numeroMostrar.length() - 1) == 'x' || numeroMostrar.charAt(numeroMostrar.length() - 1) == '√' || numeroMostrar.charAt(numeroMostrar.length() - 1) == '^' ||
                numeroMostrar.charAt(numeroMostrar.length() - 1) == '{' || numeroMostrar.charAt(numeroMostrar.length() - 1) == '(' || numeroMostrar.charAt(numeroMostrar.length() - 1) == '<' ||
                numeroMostrar.charAt(numeroMostrar.length() - 1) == '>' || numeroMostrar.charAt(numeroMostrar.length() - 1) == '%') {
            return true;
        } else {
            return false;
        }
    }

    private void escribirNumero(char numero) {
            numeroText.append(numero);
            numeroMostrar.append(numero);
            numTextView.setText(numeroMostrar);

            actualizarDigitoEnPantalla();
            actualizarPrioridad("digito");

            raizIsTouched = false;
    }

    private void funcionSigno(char signo) {
        if (numeroText.length() != 0 && !signoIsTouched || igualIsTouched) { //verifica si ya se han escrito números y si algún otro signo ya fue tocado

            if (i >= 0 && !igualIsTouched) {
                numeros[i] = Double.parseDouble(numeroText.toString());//Asigna el valor escrito al número actual
                numeroText.delete(0, numeroText.length());

                numeroMostrar.append(signo);
                numTextView.setText(numeroMostrar);
                if (lastCharIsSign(numeroMostrar.toString())){
                    i++; // Pasa al siguiente número si el digito anterior no fue un signo
                }
            } else if (i >= 1 && igualIsTouched) {

                numeroMostrar.append(signo);
                numTextView.setText(numeroMostrar);
            }
            signoIsTouched = true;
            puntoIsTouched = false;

        } else if (numeroMostrar.length() == 0 && (signo == '+' || signo == '-')){//se escribe un signo de 'más' o 'menos' para indicar que el primer número es positivo o negativo
            numeroText.append(signo);
            numeroMostrar.append(signo);
            numTextView.setText(numeroMostrar);
            signoIsTouched = true;
            puntoIsTouched = false;
        } else if (numeroText.length() == 0){//si entra aquí significa que no ha escrito números y la base es cero
            numeros[i] = 0;
            numeroMostrar.append('0');
            numeroMostrar.append(signo);
            numTextView.setText(numeroMostrar);
            signoIsTouched = true;
            i++;
        } else if (raizIsTouched) {
            numeroText.delete(0, numeroText.length());

            numeroMostrar.append(signo);
            numTextView.setText(numeroMostrar);
            i++;
            raizIsTouched = false;
            signoIsTouched = true;
            puntoIsTouched = false;
        }

        actualizarDigitoEnPantalla();
        actualizarPrioridad("digito");
        actualizarIteradores();
        igualIsTouched = false;

    }

    private boolean countMoreSigns(String operacion){

        StringBuilder builderSigno = new StringBuilder(operacion+"1234567890");

        conteoSuma = 0;
        conteoResta = 0;
        conteoMulti = 0;
        conteoDiv = 0;
        conteoPInicio = 0;
        conteoPotencia = 0;
        conteoPorcentaje = 0;

        for (int a = 0; a < builderSigno.length(); a++){
            if (builderSigno.indexOf("(") != -1){
                conteoPInicio++;
                builderSigno.deleteCharAt(builderSigno.indexOf("("));
            }

            if (builderSigno.indexOf("^") != -1){
                conteoPotencia++;
                builderSigno.deleteCharAt(builderSigno.indexOf("^"));
            }

            if (builderSigno.indexOf("x") != -1){
                conteoMulti++;
                builderSigno.deleteCharAt(builderSigno.indexOf("x"));
            }

            if (builderSigno.indexOf("/") != -1){
                conteoDiv++;
                builderSigno.deleteCharAt(builderSigno.indexOf("/"));
            }

            if (builderSigno.indexOf("+") != -1){
                conteoSuma++;
                builderSigno.deleteCharAt(builderSigno.indexOf("+"));
            }

            if (builderSigno.indexOf("-") != -1){
                conteoResta++;
                builderSigno.deleteCharAt(builderSigno.indexOf("-"));
            }

            if (builderSigno.indexOf("%") != -1){
                conteoPorcentaje++;
                builderSigno.deleteCharAt(builderSigno.indexOf("%"));
            }
        }

        if (conteoSuma != 0 || conteoResta != 0 || conteoMulti != 0 || conteoDiv != 0 || conteoPotencia != 0 || conteoPInicio != 0 || conteoPCierre != 0 || conteoPorcentaje != 0){
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

    private boolean comprobarDecimales(double numero){
        int entero = (int) numero;

        if(numero - entero == 0.0f){
            return false;
        } else {
            return true;
        }
    }


    private void operacion(String signo, int indexOperacionActual){
        termino[1] = Double.parseDouble(operacion.subString(operacion.indexOf(signo)+1, operacion.indexOfSigns(operacion.indexOf(signo)+1, "/-+^x")));
        operacion.reverse();
        MagicClass.MyString string = new MagicClass.MyString(operacion.subString(operacion.length()-indexOperacionActual, operacion.indexOfSigns(operacion.length()-indexOperacionActual, "/-+x^")));
        termino[0] = Double.valueOf(string.reverse().toString());
        int tamañoBase = string.length();
        operacion.reverse();
        String stringReplace;
        if(comprobarDecimales(opTerminos(termino[0], termino[1], signo))){
            stringReplace = String.valueOf(opTerminos(termino[0], termino[1], signo));
        } else {
            stringReplace = String.valueOf((int)opTerminos(termino[0], termino[1], signo));
        }

        operacion.replace(indexOperacionActual-tamañoBase, operacion.indexOfSigns( indexOperacionActual+1, "/x-+^"), String.valueOf(stringReplace));
    }

    private void operacionRestaNegativa(String signo, int indexOperacionActual){
        termino[1] = Double.parseDouble(operacion.subString(operacion.lastIndexOf(signo)+1, operacion.indexOfSigns(operacion.lastIndexOf(signo)+1, "/-+^x")));
        operacion.reverse();
        MagicClass.MyString string = new MagicClass.MyString(operacion.subString(operacion.length()-indexOperacionActual, operacion.indexOfSigns(operacion.length()-indexOperacionActual, "/-+x^")+1));
        termino[0] = Double.valueOf(string.reverse().toString());
        int tamañoBase = string.length();
        operacion.reverse();
        String stringReplace;
        if(comprobarDecimales(opTerminos(termino[0], termino[1], signo))){
            stringReplace = String.valueOf(opTerminos(termino[0], termino[1], signo));
        } else {
            stringReplace = String.valueOf((int)opTerminos(termino[0], termino[1], signo));
        }

        stringReplace = (Double.parseDouble(stringReplace) > 0) ? "+" + stringReplace : stringReplace;

        if (Double.parseDouble(stringReplace) == 0){
            operacion.delete(indexOperacionActual-tamañoBase, operacion.indexOfSigns( indexOperacionActual+1, "/x-+^"));
        } else {
            operacion.replace(indexOperacionActual-tamañoBase, operacion.indexOfSigns( indexOperacionActual+1, "/x-+^"), String.valueOf(stringReplace));
        }
    }

    private void operacionRestaNegativaConSuma(String signo, int indexOperacionActual){
        termino[1] = Double.parseDouble(operacion.subString(operacion.indexOf(signo)+1, operacion.indexOfSigns(operacion.indexOf(signo)+1, "/-+^x")));
        operacion.reverse();
        MagicClass.MyString string = new MagicClass.MyString(operacion.subString(operacion.length()-indexOperacionActual, operacion.indexOfSigns(operacion.length()-indexOperacionActual, "/-+x^")+1));
        termino[0] = Double.valueOf(string.reverse().toString());
        int tamañoBase = string.length();
        operacion.reverse();
        String stringReplace;
        if(comprobarDecimales(opTerminos(termino[0], termino[1], signo))){
            stringReplace = String.valueOf(opTerminos(termino[0], termino[1], signo));
        } else {
            stringReplace = String.valueOf((int)opTerminos(termino[0], termino[1], signo));
        }

        stringReplace = (Double.parseDouble(stringReplace) > 0) ? "+" + stringReplace : stringReplace;

        if (Double.parseDouble(stringReplace) == 0){
            operacion.delete(indexOperacionActual-tamañoBase, operacion.indexOfSigns( indexOperacionActual+1, "/x-+^"));
        } else {
            operacion.replace(indexOperacionActual-tamañoBase, operacion.indexOfSigns( indexOperacionActual+1, "/x-+^"), String.valueOf(stringReplace));
        }

    }


    private MagicClass.MyString operacionParentesis(String signo, int indexOperacionActual, MagicClass.MyString operacion){
        termino[1] = Double.parseDouble(operacion.subString(operacion.indexOf(signo)+1, operacion.indexOfSigns(operacion.indexOf(signo)+1, "/-+^x")));
        operacion.reverse();
        MagicClass.MyString string = new MagicClass.MyString(operacion.subString(operacion.length()-indexOperacionActual, operacion.indexOfSigns(operacion.length()-indexOperacionActual, "/-+x^")));
        termino[0] = Double.valueOf(string.reverse().toString());
        int tamañoBase = string.length();
        operacion.reverse();
        String stringReplace;
        if(comprobarDecimales(opTerminos(termino[0], termino[1], signo))){
            stringReplace = String.valueOf(opTerminos(termino[0], termino[1], signo));
        } else {
            stringReplace = String.valueOf((int)opTerminos(termino[0], termino[1], signo));
        }

        return operacion.replace(indexOperacionActual-tamañoBase, operacion.indexOfSigns( indexOperacionActual+1, "/x-+^"), String.valueOf(stringReplace));
    }

    private MagicClass.MyString operacionParentesisRestaNegativa(String signo, int indexOperacionActual, MagicClass.MyString operacion){
        termino[1] = Double.parseDouble(operacion.subString(operacion.lastIndexOf(signo)+1, operacion.indexOfSigns(operacion.lastIndexOf(signo)+1, "/-+^x")));
        operacion.reverse();
        MagicClass.MyString string = new MagicClass.MyString(operacion.subString(operacion.length()-indexOperacionActual, operacion.indexOfSigns(operacion.length()-indexOperacionActual, "/-+x^")+1));
        termino[0] = Double.valueOf(string.reverse().toString());
        int tamañoBase = string.length();
        operacion.reverse();
        String stringReplace;
        if(comprobarDecimales(opTerminos(termino[0], termino[1], signo))){
            stringReplace = String.valueOf(opTerminos(termino[0], termino[1], signo));
        } else {
            stringReplace = String.valueOf((int)opTerminos(termino[0], termino[1], signo));
        }

        stringReplace = (Double.parseDouble(stringReplace) > 0) ? "+" + stringReplace : stringReplace;

        if (Double.parseDouble(stringReplace) == 0){
            operacion.delete(indexOperacionActual-tamañoBase, operacion.indexOfSigns( indexOperacionActual+1, "/x-+^"));
        } else {
            operacion.replace(indexOperacionActual-tamañoBase, operacion.indexOfSigns( indexOperacionActual+1, "/x-+^"), String.valueOf(stringReplace));
        }
        return operacion;
    }

    private MagicClass.MyString operacionParentesisRestaNegativaConSuma(String signo, int indexOperacionActual, MagicClass.MyString operacion){
        termino[1] = Double.parseDouble(operacion.subString(operacion.indexOf(signo)+1, operacion.indexOfSigns(operacion.indexOf(signo)+1, "/-+^x")));
        operacion.reverse();
        MagicClass.MyString string = new MagicClass.MyString(operacion.subString(operacion.length()-indexOperacionActual, operacion.indexOfSigns(operacion.length()-indexOperacionActual, "/-+x^")+1));
        termino[0] = Double.valueOf(string.reverse().toString());
        int tamañoBase = string.length();
        operacion.reverse();
        String stringReplace;
        if(comprobarDecimales(opTerminos(termino[0], termino[1], signo))){
            stringReplace = String.valueOf(opTerminos(termino[0], termino[1], signo));
        } else {
            stringReplace = String.valueOf((int)opTerminos(termino[0], termino[1], signo));
        }

        stringReplace = (Double.parseDouble(stringReplace) > 0) ? "+" + stringReplace : stringReplace;

        if (Double.parseDouble(stringReplace) == 0){
            operacion.delete(indexOperacionActual-tamañoBase, operacion.indexOfSigns( indexOperacionActual+1, "/x-+^"));
        } else {
            operacion.replace(indexOperacionActual-tamañoBase, operacion.indexOfSigns( indexOperacionActual+1, "/x-+^"), String.valueOf(stringReplace));
        }
        return operacion;
    }


    private double opTerminos(double termino1, double termino2, String signo){

        double ret;

        switch (signo){
            case "x":
                ret = termino1 * termino2;
                break;

            case "+":
                ret = termino1 + termino2;
                break;

            case "-":
                ret = termino1 - termino2;
                break;

            case "/":
                ret = termino1 / termino2;
                break;

            case "^":
                ret = Math.pow(termino1, termino2);
                break;

            default:
                ret = 0;
        }

        return ret;
    }

    private boolean comprobarParentesis(){
        boolean ret = false;
        int conteoParentInicio = 0, conteoParentCierre = 0;
        StringBuilder cadena = new StringBuilder(numeroMostrar.toString()+"1234567890");

        for (int a = 0; a < cadena.length(); a++){
            if (cadena.indexOf("(") != -1){
                conteoParentInicio++;
                cadena.deleteCharAt(cadena.indexOf("("));
            }

            if (cadena.indexOf(")") != -1){
                conteoParentCierre++;
                cadena.deleteCharAt(cadena.indexOf(")"));
            } else {
                break;
            }
        }

        if (conteoParentCierre == conteoParentInicio){
            ret = true;
        }

        return ret;
    }

    private void deshacerParentesis(int indexApertura, int indexCierre, String replace){
        operacion.replace(indexApertura, indexCierre, replace);
    }

    private void operarParentesis(){
        for (int a = 0; a < conteoPotencia ; a++) {
            int indexPotenciaActual = parentesisActual.indexOf("^");
            if (indexPotenciaActual != -1){
                parentesisActual = operacionParentesis("^", indexPotenciaActual, parentesisActual);
            }

        }

        int conteoSigno = conteoDiv + conteoMulti;

        for (int a = 0; a < conteoSigno; a++){
            int indexDivisionActual = parentesisActual.indexOf("/");
            int indexMultiplicacionActual = parentesisActual.indexOf("x");

            if (indexDivisionActual != -1 && indexMultiplicacionActual != -1){
                if (indexMultiplicacionActual < indexDivisionActual){
                    parentesisActual = operacionParentesis("x", indexMultiplicacionActual, parentesisActual);

                } else {
                    parentesisActual = operacionParentesis("/", indexDivisionActual, parentesisActual);

                }


            } else if (indexDivisionActual >= 1){
                parentesisActual = operacionParentesis("/", indexDivisionActual, parentesisActual);

            } else if (indexMultiplicacionActual >= 1){
                parentesisActual = operacionParentesis("x", indexMultiplicacionActual, parentesisActual);
            }

        }


        conteoSigno = conteoSuma + conteoResta;

        for (int a = 0; a < conteoSigno; a++){
            countMoreSigns(parentesisActual.toString());
            int indexSumaActual = parentesisActual.indexOf("+");
            int indexRestaActual = parentesisActual.indexOf("-");

            if (indexSumaActual != -1 && indexRestaActual != -1){
                if (indexRestaActual < indexSumaActual && indexRestaActual != 0){
                    parentesisActual = operacionParentesis("-", indexRestaActual, parentesisActual);

                } else if (indexRestaActual == 0){
                    parentesisActual = operacionParentesisRestaNegativaConSuma("+", indexSumaActual, parentesisActual);
                } else {
                    parentesisActual = operacionParentesis("+", indexSumaActual, parentesisActual);

                }

            } else if (indexSumaActual >= 1){
                parentesisActual = operacionParentesis("+", indexSumaActual, parentesisActual);

            } else if (indexRestaActual >= 1){
                parentesisActual = operacionParentesis("-", indexRestaActual, parentesisActual);
            } else if (indexRestaActual == 0 && conteoResta >= 2){
                parentesisActual.insert(0, '0');
                indexRestaActual = parentesisActual.lastIndexOf("-");
                parentesisActual = operacionParentesisRestaNegativa("-", indexRestaActual, parentesisActual);
            }

        }
    }

    private void showAlertForMagicNumber(final int number, final int image, final boolean isPortrait, final String activityTitle){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(R.string.numeroMagico);
        builder.setMessage(R.string.numeroMagicoMessage);
        View view = LayoutInflater.from(this).inflate(R.layout.alert_view, null);
        View viewSJMA = LayoutInflater.from(this).inflate(R.layout.alert_view_sjma, null);
        final EditText input = view.findViewById(R.id.editTextPassword);
        TextView text = view.findViewById(R.id.textViewNumerosMagicos);
        TextView textSJMA = viewSJMA.findViewById(R.id.textViewNumerosMagicosSJMA);

        text.setText(datosPersistentes.getNumerosMagicosEncontrados() + "/" + datosPersistentes.getTotalNumerosMagicos());
        textSJMA.setText(datosPersistentes.getNumerosMagicosEncontrados() + "/" + datosPersistentes.getTotalNumerosMagicos());

        if (number == 11723){
            builder.setView(viewSJMA);
        } else {
            builder.setView(view);
        }


        builder.setPositiveButton(R.string.continuar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (number == 11723){
                    MagicClass.causeError();
                } else if (input.getText().toString().equals(datosPersistentes.getContraOf(number))){
                    Intent intent = new Intent(MainActivity.this, ImageActivity.class);
                    intent.putExtra("image", image);
                    intent.putExtra("isPortrait", isPortrait);
                    intent.putExtra("title", activityTitle);
                    startActivity(intent);
                }

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}