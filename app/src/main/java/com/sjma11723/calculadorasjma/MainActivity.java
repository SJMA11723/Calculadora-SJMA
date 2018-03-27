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
    private Button buttonParentesisApertura;
    private Button buttonParentesisCierre;
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
    private byte igualIsTouched = 1;// 1 = boton igual no ha sido tocado, 0 = fue tocado
    private byte signoIsTouched = 1;// 1 = un boton de signo no ha sido tocado, 0 = fue tocado
    private byte raizIsTouched = 1; // 1 = boton de raiz no ha sido tocado, 0 = fue  tocado
    private byte menorIsTouched = 1;
    private byte mayorIsTouched = 1;//Lo mismo
    private byte puntoIsTouched = 1; // 1 = boton de punto no ha sido tocado, 0 = fue  tocado
    private byte parentIsTouched = 1;
    private int j = 0;// Iterador de botón igual
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
    private MyString operacion, parentesisActual;

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


        // Añadimos el comportamiento a los botones

        buttonMenorQue.setText("<");// Añado aquí los signos ya que en strings.xml no me deja
        buttonMayorQue.setText(">");

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signoIsTouched = 1;

                if (igualIsTouched == 1) {
                    escribirNumero('0');
                } else {
                    iniciarNuevaOperacion('0');
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signoIsTouched = 1;

                if (igualIsTouched == 1) {
                    escribirNumero('1');
                } else {
                    iniciarNuevaOperacion('1');
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signoIsTouched = 1;
                if (igualIsTouched == 1) {
                    escribirNumero('2');
                } else {
                    iniciarNuevaOperacion('2');
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signoIsTouched = 1;
                if (igualIsTouched == 1) {
                    escribirNumero('3');
                } else {
                    iniciarNuevaOperacion('3');
                }
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signoIsTouched = 1;
                if (igualIsTouched == 1) {
                    escribirNumero('4');
                } else {
                    iniciarNuevaOperacion('4');
                }
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signoIsTouched = 1;
                if (igualIsTouched == 1) {
                    escribirNumero('5');
                } else {
                    iniciarNuevaOperacion('5');
                }
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signoIsTouched = 1;
                if (igualIsTouched == 1) {
                    escribirNumero('6');
                } else {
                    iniciarNuevaOperacion('6');
                }
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signoIsTouched = 1;
                if (igualIsTouched == 1) {
                    escribirNumero('7');
                } else {
                    iniciarNuevaOperacion('7');
                }
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signoIsTouched = 1;
                if (igualIsTouched == 1) {
                    escribirNumero('8');
                } else {
                    iniciarNuevaOperacion('8');
                }
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signoIsTouched = 1;
                if (igualIsTouched == 1) {
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

                if (raizIsTouched == 1) {
                    if (numeroText.length() != 0 && i == 0) {
                        numeros[i] = Math.sqrt(Double.parseDouble(numeroText.toString()));

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
                        raizIsTouched = 0;
                        igualIsTouched = 0;

                    } else {
                        Toast.makeText(MainActivity.this, R.string.advertencia_raiz, Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        buttonIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (igualIsTouched == 1 && numeroText.length() != 0 && (!lastCharIsSign(numeroMostrar.toString()) || numeroMostrar.charAt(numeroMostrar.length()-1) == ')') && comprobarParentesis()) {

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

                    } else if (countMoreSigns(numeroMostrar.toString())){
                        operacion = new MyString(numeroMostrar.toString());

                        while (conteoPInicio != 0){
                            parentesisActual = new MyString(operacion.subString(operacion.lastIndexOf("(")+1, operacion.indexOf(")", operacion.lastIndexOf("("))));
                            countMoreSigns(parentesisActual.toString());
                            operarParentesis();
                            if (operacion.lastIndexOf("(") >= 1){
                                if (operacion.subString(operacion.lastIndexOf("(")-1, operacion.lastIndexOf("(")).contains("^") || operacion.subString(operacion.lastIndexOf("(")-1, operacion.lastIndexOf("(")).contains("+") || operacion.subString(operacion.lastIndexOf("(")-1, operacion.lastIndexOf("(")).contains("-") || operacion.subString(operacion.lastIndexOf("(")-1, operacion.lastIndexOf("(")).contains("x") || operacion.subString(operacion.lastIndexOf("(")-1, operacion.lastIndexOf("(")).contains("/") || operacion.subString(operacion.lastIndexOf("(")-1, operacion.lastIndexOf("(")).contains("(")){
                                    deshacerParentesis(operacion.lastIndexOf("("), operacion.indexOf(")", operacion.lastIndexOf("("))+1, parentesisActual.toString());
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
                            int indexSumaActual = operacion.indexOf("+");
                            int indexRestaActual = operacion.indexOf("-");

                            if (indexSumaActual != -1 && indexRestaActual != -1){
                                if (indexRestaActual < indexSumaActual){
                                    operacion("-", indexRestaActual);

                                } else {
                                    operacion("+", indexSumaActual);

                                }

                            } else if (indexSumaActual >= 1){
                                operacion("+", indexSumaActual);

                            } else if (indexRestaActual >= 1){
                                operacion("-", indexRestaActual);
                            }

                        }

                        numeros[0] = Double.valueOf(operacion.toString());

                    } else if (menorIsTouched == 0){
                        if (numeros[0] < numeros[1]){
                            resultBoolean = true;
                        } else{
                            resultBoolean = false;
                        }

                    } else if (mayorIsTouched == 0){
                        if (numeros[0] > numeros[1]){
                            resultBoolean = true;
                        } else{
                            resultBoolean = false;
                        }
                    }


                    numeroText.delete(0, numeroText.length());

                    if (menorIsTouched == 0 || mayorIsTouched == 0){
                        numTextView.setText(numeroMostrar+"\n= "+resultBoolean);
                    } else {

                        if (!comprobarDecimales(numeros[0])) { //Comprueba si tiene decimales
                            numTextView.setText(numeroMostrar + "\n= " + (int)numeros[0]); //Muestra el resultado en tipo int si no tiene decimales
                            numeroMostrar.replace(0, numeroMostrar.length(), String.valueOf(((int) numeros[0]))); //En vez de mostrar la operación (1+1+4...) muestra el resultado para seguir operando
                        } else {
                            numTextView.setText(numeroMostrar + "\n= " + numeros[0]); //Muestra el resultado en double si tiene decimales
                            numeroMostrar.replace(0, numeroMostrar.length(), String.valueOf(numeros[0])); //En vez de mostrar la operación (1+1+4...) muestra el resultado para seguir operando
                        }
                        i++; //Pasa al siguiente número si quiere seguir operando con el resultado
                        signoIsTouched = 1;
                    }

                    igualIsTouched = 0;

                }
            }
        });

        buttonBorrar.setOnClickListener(new View.OnClickListener() { //boton listo :)
            @Override
            public void onClick(View view) {
                if (numeroMostrar.length() != 0 && igualIsTouched == 1) {
                    if (lastCharIsSign(numeroMostrar.toString()) && numeroMostrar.charAt(numeroMostrar.length()-1) != '(') {

                        i--;
                        signoIsTouched = 1;

                        if (!comprobarDecimales(numeros[i])) { //Comprueba si tiene decimales
                            numeroText.append((int)numeros[i]);
                        } else {
                            numeroText.append(numeros[i]);
                        }

                    } else if (numeroMostrar.charAt(numeroMostrar.length()-1) != ')' && numeroMostrar.charAt(numeroMostrar.length()-1) != '('){
                        numeroText.deleteCharAt(numeroText.length() - 1);
                        signoIsTouched = 1;
                    }
                    numeroMostrar.deleteCharAt(numeroMostrar.length() - 1);
                    numTextView.setText(numeroMostrar);
                }

            }
        });

        buttonBorrar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (numeroMostrar.length() != 0 && igualIsTouched == 1) {
                    signoIsTouched = 1;
                    numeroMostrar.delete(0, numeroMostrar.length());
                    numeroText.delete(0, numeroText.length());
                    numTextView.setText(null);
                    i = 0;
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

                raizIsTouched = 1;
                igualIsTouched = 1;
                signoIsTouched = 1;
                puntoIsTouched = 1;
                menorIsTouched = 1;
                mayorIsTouched = 1;
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
                if (puntoIsTouched == 1) {
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

                    puntoIsTouched = 0;

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
                    signoIsTouched = 0;
                    mayorIsTouched = 0;
                    puntoIsTouched = 1;
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
                    signoIsTouched = 0;
                    puntoIsTouched = 1;
                    menorIsTouched = 0;
                }

            }
        });

        buttonParentesisApertura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numeroMostrar.append("(");
                numTextView.setText(numeroMostrar);
                igualIsTouched = 1;
            }
        });

        buttonParentesisCierre.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (numeroMostrar.length() != 0 && !lastCharIsSign(numeroMostrar.toString()) && igualIsTouched == 1 && !comprobarParentesis()){
                    numeroMostrar.append(")");
                    numTextView.setText(numeroMostrar);
                }

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
        igualIsTouched = 1;
    }

    private boolean lastCharIsSign(String numeroMostrar) {

        if (numeroMostrar.charAt(numeroMostrar.length() - 1) == '+' || numeroMostrar.charAt(numeroMostrar.length() - 1) == '-' || numeroMostrar.charAt(numeroMostrar.length() - 1) == '/' ||
                numeroMostrar.charAt(numeroMostrar.length() - 1) == 'x' || numeroMostrar.charAt(numeroMostrar.length() - 1) == '√' || numeroMostrar.charAt(numeroMostrar.length() - 1) == '^' ||
                numeroMostrar.charAt(numeroMostrar.length() - 1) == '{' || numeroMostrar.charAt(numeroMostrar.length() - 1) == '(' || numeroMostrar.charAt(numeroMostrar.length() - 1) == '<' || numeroMostrar.charAt(numeroMostrar.length() - 1) == '>') {
            return true;
        } else {
            return false;
        }
    }

    private void escribirNumero(char numero) {
            numeroText.append(numero);
            numeroMostrar.append(numero);
            numTextView.setText(numeroMostrar);

            raizIsTouched = 1;
    }

    private void funcionSigno(char signo) {
        if (numeroText.length() != 0 && signoIsTouched == 1 || igualIsTouched == 0) { //verifica si ya se han escrito números y si algún otro signo ya fue tocado

            if (i >= 0 && igualIsTouched == 1) {
                numeros[i] = Double.parseDouble(numeroText.toString());//Asigna el valor escrito al número actual
                numeroText.delete(0, numeroText.length());

                numeroMostrar.append(signo);
                numTextView.setText(numeroMostrar);
                if (lastCharIsSign(numeroMostrar.toString())){
                    i++; // Pasa al siguiente número si el digito anterior no fue un signo
                }
            } else if (i >= 1 && igualIsTouched == 0) {

                numeroMostrar.append(signo);
                numTextView.setText(numeroMostrar);
            }
            signoIsTouched = 0;
            puntoIsTouched = 1;

        } else if (numeroMostrar.length() == 0 && (signo == '+' || signo == '-')){//se escribe un signo de 'más' o 'menos' para indicar que el primer número es positivo o negativo
            numeroText.append(signo);
            numeroMostrar.append(signo);
            numTextView.setText(numeroMostrar);
            signoIsTouched = 0;
            puntoIsTouched = 1;
        } else if (numeroText.length() == 0){//si entra aquí significa que no ha escrito números y la base es cero
            numeros[i] = 0;
            numeroMostrar.append('0');
            numeroMostrar.append(signo);
            numTextView.setText(numeroMostrar);
            signoIsTouched = 0;
            i++;
        } else if (raizIsTouched == 0) {
            numeroText.delete(0, numeroText.length());

            numeroMostrar.append(signo);
            numTextView.setText(numeroMostrar);
            i++;
            raizIsTouched = 1;
            signoIsTouched = 0;
            puntoIsTouched = 1;
        }




        igualIsTouched = 1;

    }

    private boolean countMoreSigns(String operacion){

        StringBuilder builderSigno = new StringBuilder(operacion+"1234567890");

        conteoSuma = 0;
        conteoResta = 0;
        conteoMulti = 0;
        conteoDiv = 0;
        conteoPInicio = 0;
        conteoPotencia = 0;

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
        }

        if (conteoSuma != 0 || conteoResta != 0 || conteoMulti != 0 || conteoDiv != 0 || conteoPotencia != 0 || conteoPInicio != 0 || conteoPCierre != 0){
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
        MyString string = new MyString(operacion.subString(operacion.length()-indexOperacionActual, operacion.indexOfSigns(operacion.length()-indexOperacionActual, "/-+x^")));
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

    private MyString operacionParentesis(String signo, int indexOperacionActual, MyString operacion){
        termino[1] = Double.parseDouble(operacion.subString(operacion.indexOf(signo)+1, operacion.indexOfSigns(operacion.indexOf(signo)+1, "/-+^x")));
        operacion.reverse();
        MyString string = new MyString(operacion.subString(operacion.length()-indexOperacionActual, operacion.indexOfSigns(operacion.length()-indexOperacionActual, "/-+x^")));
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
            int indexSumaActual = parentesisActual.indexOf("+");
            int indexRestaActual = parentesisActual.indexOf("-");

            if (indexSumaActual != -1 && indexRestaActual != -1){
                if (indexRestaActual < indexSumaActual){
                    parentesisActual = operacionParentesis("-", indexRestaActual, parentesisActual);

                } else {
                    parentesisActual = operacionParentesis("+", indexSumaActual, parentesisActual);

                }

            } else if (indexSumaActual >= 1){
                parentesisActual = operacionParentesis("+", indexSumaActual, parentesisActual);

            } else if (indexRestaActual >= 1){
                parentesisActual = operacionParentesis("-", indexRestaActual, parentesisActual);
            }

        }
    }
}