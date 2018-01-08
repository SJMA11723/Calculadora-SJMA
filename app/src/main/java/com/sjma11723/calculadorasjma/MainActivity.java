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
    private byte igualIsTouched = 1;// 1 = boton igual no ha sido tocado, 0 = fue tocado
    private byte signoIsTouched = 1;// 1 = un boton de signo no ha sido tocado, 0 = fue tocado
    private byte raizIsTouched = 1; // 1 = boton de raiz no ha sido tocado, 0 = fue  tocado
    private byte menorIsTouched = 1;
    private byte mayorIsTouched = 1;//Lo mismo
    private byte puntoIsTouched = 1; // 1 = boton de punto no ha sido tocado, 0 = fue  tocado
    private int j = 0;// Iterador de botón igual
    private boolean resultBoolean;
    private int numTokens;
    private boolean causarERROR = false;

    byte conteoSuma = 0;
    byte conteoResta = 0;
    byte conteoMulti = 0;
    byte conteoDiv = 0;
    byte conteoPotencia = 0;

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

                if (igualIsTouched == 1 && numeroText.length() != 0 && !lastCharIsSign()) {

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

                    } else if (containsMoreSigns()){
                        MyString operacion = new MyString(numeroMostrar.toString());

                        double terminoSuma[] = new double[2];
                        double terminoResta[] = new double[2];
                        double terminoDiv[] = new double[2];
                        double terminoMulti[] = new double[2];
                        double terminoPotencia[] = new double[2];




                        for (int a = 0; a < conteoPotencia ; a++) {
                            int indexPotenciaActual = operacion.indexOf("^");
                            if (indexPotenciaActual != -1){
                                terminoPotencia[1] = Double.parseDouble(operacion.subString(operacion.indexOf("^")+1, operacion.indexOfSigns(operacion.indexOf("^"), "/x-+")));
                                operacion.reverse();
                                MyString string = new MyString(operacion.subString(operacion.length()-indexPotenciaActual, operacion.indexOfSigns(operacion.length()-indexPotenciaActual, "/x-+")));
                                terminoPotencia[0] = Double.valueOf(string.reverse());
                                int tamañoBase = string.length();
                                operacion.reverse();
                                operacion.replace(indexPotenciaActual-tamañoBase, operacion.indexOfSigns( indexPotenciaActual, "/x-+"), String.valueOf(Math.pow(terminoPotencia[0], terminoPotencia[1])));
                            }

                        }

                        int conteoMayor;

                        if (conteoDiv < conteoMulti){
                            conteoMayor = conteoMulti;
                        } else {
                            conteoMayor = conteoDiv;
                        }

                        for (int a = 0; a < conteoMayor*2; a++){
                            int indexDivisionActual = operacion.indexOf("/");
                            int indexMultiplicacionActual = operacion.indexOf("x");

                            if (indexDivisionActual != -1 && indexMultiplicacionActual != -1){
                                if (indexMultiplicacionActual < indexDivisionActual){
                                    terminoMulti[1] = Double.parseDouble(operacion.subString(operacion.indexOf("x")+1, operacion.indexOfSigns(operacion.indexOf("x"), "/-+^")));
                                    operacion.reverse();
                                    MyString string = new MyString(operacion.subString(operacion.length()-indexMultiplicacionActual, operacion.indexOfSigns(operacion.length()-indexMultiplicacionActual, "/-+^")));
                                    terminoMulti[0] = Double.valueOf(string.reverse());
                                    int tamañoBase = string.length();
                                    operacion.reverse();
                                    operacion.replace(indexMultiplicacionActual-tamañoBase, operacion.indexOfSigns( indexMultiplicacionActual, "/-+^"), String.valueOf(terminoMulti[0]*terminoMulti[1]));

                                } else {

                                    terminoDiv[1] = Double.parseDouble(operacion.subString(operacion.indexOf("/")+1, operacion.indexOfSigns(operacion.indexOf("/"), "x-+^")));
                                    operacion.reverse();

                                    MyString string = new MyString(operacion.subString(operacion.length()-indexDivisionActual, operacion.indexOfSigns(operacion.length()-indexDivisionActual, "x-+^")));
                                    terminoDiv[0] = Double.valueOf(string.reverse());

                                    int tamañoBase = string.length();
                                    operacion.reverse();

                                    try {
                                        operacion.replace(indexDivisionActual-tamañoBase, operacion.indexOfSigns( indexDivisionActual, "^x-+"), String.valueOf(terminoDiv[0]/terminoDiv[1]));
                                    }catch (Exception e){
                                        Toast.makeText(MainActivity.this, R.string.divByZero, Toast.LENGTH_SHORT).show();
                                        break;
                                    }

                                }


                            } else if (indexDivisionActual >= 1){
                                terminoDiv[1] = Double.parseDouble(operacion.subString(operacion.indexOf("/")+1, operacion.indexOfSigns(operacion.indexOf("/"), "x-+^")));
                                operacion.reverse();

                                MyString string = new MyString(operacion.subString(operacion.length()-indexDivisionActual, operacion.indexOfSigns(operacion.length()-indexDivisionActual, "x-+^")));
                                terminoDiv[0] = Double.valueOf(string.reverse());

                                int tamañoBase = string.length();
                                operacion.reverse();
                                try {
                                    operacion.replace(indexDivisionActual-tamañoBase, operacion.indexOfSigns( indexDivisionActual, "^x-+"), String.valueOf(terminoDiv[0]/terminoDiv[1]));
                                }catch (Exception e){
                                    Toast.makeText(MainActivity.this, R.string.divByZero, Toast.LENGTH_SHORT).show();
                                    break;
                                }


                            } else if (indexMultiplicacionActual >= 1){
                                terminoMulti[1] = Double.parseDouble(operacion.subString(operacion.indexOf("x")+1, operacion.indexOfSigns(operacion.indexOf("x"), "/-+^")));
                                operacion.reverse();
                                MyString string = new MyString(operacion.subString(operacion.length()-indexMultiplicacionActual, operacion.indexOfSigns(operacion.length()-indexMultiplicacionActual, "/-+^")));
                                terminoMulti[0] = Double.valueOf(string.reverse());
                                int tamañoBase = string.length();
                                operacion.reverse();
                                operacion.replace(indexMultiplicacionActual-tamañoBase, operacion.indexOfSigns( indexMultiplicacionActual, "/-+^"), String.valueOf(terminoMulti[0]*terminoMulti[1]));

                            }

                        }

                        if (conteoSuma < conteoResta){
                            conteoMayor = conteoResta;
                        } else {
                            conteoMayor = conteoSuma;
                        }

                        for (int a = 0; a < conteoMayor*2; a++){
                            int indexSumaActual = operacion.indexOf("+");
                            int indexRestaActual = operacion.indexOf("-");

                            if (indexSumaActual != -1 && indexRestaActual != -1){
                                if (indexRestaActual < indexSumaActual){
                                    terminoResta[1] = Double.parseDouble(operacion.subString(operacion.indexOf("-")+1, operacion.indexOfSigns(operacion.indexOf("-"), "/+^x")));
                                    operacion.reverse();
                                    MyString string = new MyString(operacion.subString(operacion.length()-indexRestaActual, operacion.indexOfSigns(operacion.length()-indexRestaActual, "/x+^")));
                                    terminoResta[0] = Double.valueOf(string.reverse());
                                    int tamañoBase = string.length();
                                    operacion.reverse();
                                    operacion.replace(indexRestaActual-tamañoBase, operacion.indexOfSigns( indexRestaActual, "/+x^"), String.valueOf(terminoResta[0]-terminoResta[1]));

                                } else {

                                    terminoSuma[1] = Double.parseDouble(operacion.subString(operacion.indexOf("+")+1, operacion.indexOfSigns(operacion.indexOf("+"), "x-^/")));
                                    operacion.reverse();

                                    MyString string = new MyString(operacion.subString(operacion.length()-indexSumaActual, operacion.indexOfSigns(operacion.length()-indexSumaActual, "x-^/")));
                                    terminoSuma[0] = Double.valueOf(string.reverse());

                                    int tamañoBase = string.length();
                                    operacion.reverse();
                                    operacion.replace(indexSumaActual-tamañoBase, operacion.indexOfSigns( indexSumaActual, "^x-/"), String.valueOf(terminoSuma[0]+terminoSuma[1]));

                                }

                            } else if (indexSumaActual >= 1){
                                terminoSuma[1] = Double.parseDouble(operacion.subString(operacion.indexOf("+")+1, operacion.indexOfSigns(operacion.indexOf("+")+1, "x-/+")));
                                operacion.reverse();

                                MyString string = new MyString(operacion.subString(operacion.length()-indexSumaActual, operacion.indexOfSigns(operacion.length()-indexSumaActual, "x-/+")));
                                terminoSuma[0] = Double.valueOf(string.reverse());

                                int tamañoBase = string.length();
                                operacion.reverse();
                                operacion.replace(indexSumaActual-tamañoBase, operacion.indexOfSigns( indexSumaActual+1, "x-/+"), String.valueOf(terminoSuma[0]+terminoSuma[1]));

                            } else if (indexRestaActual >= 1){
                                terminoResta[1] = Double.parseDouble(operacion.subString(operacion.indexOf("-")+1, operacion.indexOfSigns(operacion.indexOf("-")+1, "x/+-")));
                                operacion.reverse();
                                MyString string = new MyString(operacion.subString(operacion.length()-indexRestaActual, operacion.indexOfSigns(operacion.length()-indexRestaActual, "x-/+")));
                                terminoResta[0] = Double.valueOf(string.reverse());
                                int tamañoBase = string.length();
                                operacion.reverse();
                                operacion.replace(indexRestaActual-tamañoBase, operacion.indexOfSigns( indexRestaActual+1, "-x+/"), String.valueOf(terminoResta[0]-terminoResta[1]));

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
                        int resultadoInt = (int) numeros[0]; //convierte el resultado a int

                        if (numeros[0] - resultadoInt == 0.0f) { //Comprueba si tiene decimales
                            numTextView.setText(numeroMostrar + "\n= " + resultadoInt); //Muestra el resultado en tipo int si no tiene decimales
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
                    if (lastCharIsSign()) {

                        i--;
                        signoIsTouched = 1;


                        int resultadoInt = (int) numeros[i]; //convierte el resultado a int

                        if (numeros[i] - resultadoInt == 0.0f) { //Comprueba si tiene decimales
                            numeroText.append(resultadoInt);
                        } else {
                            numeroText.append(numeros[i]);
                        }

                    } else {
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

                }
            }
        });

        buttonPotencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numeroText.length() != 0 && signoIsTouched == 1 || igualIsTouched == 0) { //verifica si ya se han escrito números y si algún otro signo ya fue tocado

                    if (i >= 0 && igualIsTouched == 1) {
                        numeros[i] = Double.parseDouble(numeroText.toString());//Asigna el valor escrito al número actual
                        numeroText.delete(0, numeroText.length());

                        numeroMostrar.append('^');
                        numTextView.setText(numeroMostrar);
                        i++; // Pasa al siguiente número
                    } else if (i >= 1 && igualIsTouched == 0) {

                        numeroMostrar.append('^');
                        numTextView.setText(numeroMostrar);
                    }
                    signoIsTouched = 0;

                } else if (numeroText.length() == 0){//si entra aquí significa que no ha escrito números y la base es cero
                    numeros[i] = 0;
                    numeroMostrar.append('0');
                    numeroMostrar.append('^');
                    numTextView.setText(numeroMostrar);
                    signoIsTouched = 0;
                    i++;
                } else if (raizIsTouched == 0) {
                    numeroText.delete(0, numeroText.length());

                    numeroMostrar.append('^');
                    numTextView.setText(numeroMostrar);
                    i++;
                    raizIsTouched = 1;
                    signoIsTouched = 0;
                }




                igualIsTouched = 1;

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
                    menorIsTouched = 0;
                }

            }
        });

        buttonParentesis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

    private void escribirNumero(char numero) {
        if (numeroText.length() <= 9){ // la cantidad máxima de dígitos es nueve
            numeroText.append(numero);
            numeroMostrar.append(numero);
            numTextView.setText(numeroMostrar);

            raizIsTouched = 1;
        }
    }

    private void funcionSigno(char signo) {
        if (numeroText.length() != 0 && signoIsTouched == 1 || igualIsTouched == 0) { //verifica si ya se han escrito números y si algún otro signo ya fue tocado

            if (i >= 0 && igualIsTouched == 1) {
                numeros[i] = Double.parseDouble(numeroText.toString());//Asigna el valor escrito al número actual
                numeroText.delete(0, numeroText.length());

                numeroMostrar.append(signo);
                numTextView.setText(numeroMostrar);
                i++; // Pasa al siguiente número
            } else if (i >= 1 && igualIsTouched == 0) {

                numeroMostrar.append(signo);
                numTextView.setText(numeroMostrar);
            }
            signoIsTouched = 0;

        } else if (numeroMostrar.length() == 0 && (signo == '+' || signo == '-')){//se escribe un signo de más para indicar que el primer número es positivo o negativo
            numeroText.append(signo);
            numeroMostrar.append(signo);
            numTextView.setText(numeroMostrar);
            signoIsTouched = 0;
        } else if (raizIsTouched == 0) {
            numeroText.delete(0, numeroText.length());

            numeroMostrar.append(signo);
            numTextView.setText(numeroMostrar);
            i++;
            raizIsTouched = 1;
            signoIsTouched = 0;
        }




        igualIsTouched = 1;

    }

    private boolean containsMoreSigns(){

        StringTokenizer tokenizerSigno = new StringTokenizer(numeroMostrar.toString(), "1234567890.");

        do{

            switch (tokenizerSigno.nextToken()){
                case "+":
                    conteoSuma++;
                    break;
                case "-":
                    conteoResta++;
                    break;
                case "x":
                    conteoMulti++;
                    break;
                case "/":
                    conteoDiv++;
                    break;
                case "^":
                    conteoPotencia++;
                    break;
                default:
            }

        }while (tokenizerSigno.hasMoreTokens());

        if (conteoSuma != 0 || conteoResta != 0 || conteoMulti != 0 || conteoDiv != 0 || conteoPotencia != 0){
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
