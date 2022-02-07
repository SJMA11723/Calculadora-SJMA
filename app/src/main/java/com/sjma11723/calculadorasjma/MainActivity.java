package com.sjma11723.calculadorasjma;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private ImageButton buttonLess;
    private ImageButton buttonGreater;
    private ImageButton buttonDot;
    private ImageButton buttonEqual;
    private ImageButton buttonOpenParenthesis;
    private ImageButton buttonCloseParenthesis;
    private ImageButton buttonPlus;
    private ImageButton buttonMinus;
    private ImageButton buttonCdot;
    private ImageButton buttonERROR;
    private ImageButton buttonDivision;
    private ImageButton buttonSqrt;
    private ImageButton buttonPow;
    private ImageButton buttonClear;
    private ImageButton buttonErase;

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
    private TextView screenTextView;

    private StringBuffer txtTextView;

    private Realm realm;
    private Data data;

    private final int MAGIC_NUMBERS = 4;
    private final int PRIMARY_KEY = 1;
    private double result;

    private boolean errorClicked = false,
            dotClicked = false,
            undefined = false,
            indeterminate = false;

    private int parenthesisStack = 0;

    private char charSqrt, charDiv, charDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        buttonGreater = findViewById(R.id.buttonMayorQue);
        buttonLess = findViewById(R.id.buttonMenorQue);
        buttonErase = findViewById(R.id.buttonBorrar);
        buttonClear = findViewById(R.id.buttonClear);
        buttonDivision = findViewById(R.id.buttonDiv);
        buttonERROR = findViewById(R.id.buttonERROR);
        buttonEqual = findViewById(R.id.buttonIgual);
        buttonCdot = findViewById(R.id.buttonMulti);
        buttonOpenParenthesis = findViewById(R.id.buttonParentesisApertura);
        buttonCloseParenthesis = findViewById(R.id.buttonParentesisCierre);
        buttonPow = findViewById(R.id.buttonPotencia);
        buttonDot = findViewById(R.id.buttonPunto);
        buttonSqrt = findViewById(R.id.buttonRaiz);
        buttonMinus = findViewById(R.id.buttonResta);
        buttonPlus = findViewById(R.id.buttonSuma);

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

        //buttonPow.setEnabled(false);
        //buttonSqrt.setEnabled(false);
        buttonLess.setEnabled(false);
        //buttonLess.setBackgroundColor(getResources().getColor(R.color.black));
        buttonGreater.setEnabled(false);
        //buttonGreater.setBackgroundColor(getResources().getColor(R.color.black_overlay));

        charSqrt = getString(R.string.sqrt).charAt(0);
        charDiv = getString(R.string.div).charAt(0);
        charDot = getString(R.string.dotSymbol).charAt(0);

        scroll = findViewById(R.id.scrollView);
        screenTextView = findViewById(R.id.textView);

        realm = Realm.getDefaultInstance();
        if (realm.where(Data.class).findFirst() != null) data = realm.where(Data.class).findFirst();
        else data = new Data(getString(R.string.welcome), 0, PRIMARY_KEY);

        if(data.getTxtOnScreen() != null) screenTextView.setText(data.getTxtOnScreen());
        result = data.getLastResult();
        dotClicked = data.getDotClicked();

        txtTextView = new StringBuffer(data.getTxtOnScreen());

        if(txtTextView.toString().equals(getString(R.string.welcome))){
            txtTextView.replace(0, txtTextView.length(), "");
        }


        // Añadimos el comportamiento a los botones

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeNumber(getString(R.string.zero).charAt(0));
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeNumber(getString(R.string.one).charAt(0));
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeNumber(getString(R.string.two).charAt(0));
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeNumber(getString(R.string.three).charAt(0));
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeNumber(getString(R.string.four).charAt(0));
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeNumber(getString(R.string.five).charAt(0));
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeNumber(getString(R.string.six).charAt(0));
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeNumber(getString(R.string.seven).charAt(0));
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeNumber(getString(R.string.eight).charAt(0));
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeNumber(getString(R.string.nine).charAt(0));
            }
        });

        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtTextView.length() == 0 || !lastCharIsSign() &&
                    txtTextView.charAt(txtTextView.length() - 1) != charDot) {
                    txtTextView.append(getString(R.string.plus));
                    screenTextView.setText(txtTextView.toString());
                    dotClicked = false;
                }

            }
        });

        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtTextView.length() == 0 || !lastCharIsSign() &&
                        txtTextView.charAt(txtTextView.length() - 1) != charDot){
                    txtTextView.append(getString(R.string.minus));
                    screenTextView.setText(txtTextView.toString());
                    dotClicked = false;
                }
            }
        });

        buttonCdot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtTextView.length() != 0) {
                    char c = txtTextView.charAt(txtTextView.length() - 1);
                    if(c == ')' || '0' <= c && c <= '9') {
                        txtTextView.append(getString(R.string.multiSymbol));
                        screenTextView.setText(txtTextView.toString());
                        dotClicked = false;
                    }
                }
            }
        });

        buttonDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtTextView.length() != 0) {
                    char c = txtTextView.charAt(txtTextView.length() - 1);
                    if(c == ')' || '0' <= c && c <= '9') {
                        txtTextView.append(charDiv);
                        screenTextView.setText(txtTextView.toString());
                        dotClicked = false;
                    }
                }
            }
        });

        buttonSqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtTextView.length() == 0 || txtTextView.charAt(txtTextView.length() - 1) != charDot){
                    txtTextView.append(getString(R.string.sqrt));
                    screenTextView.setText(txtTextView.toString());
                    dotClicked = false;
                }
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtTextView.length() == 0 || parenthesisStack != 0 || isOperator(txtTextView.charAt(txtTextView.length() - 1)) ||
                txtTextView.charAt(txtTextView.length() - 1) == charDot) return;

                //Toast.makeText(MainActivity.this, "Expresión escrita correctamente", Toast.LENGTH_SHORT).show();
                result = processText(0, txtTextView.length());
                if(indeterminate){
                    screenTextView.setText(txtTextView.toString() + "\n= " + getString(R.string.operationIndeterminate));
                    indeterminate = false;
                } else if(undefined){
                    screenTextView.setText(txtTextView.toString() + "\n= " + getString(R.string.operationUndefined));
                    undefined = false;
                } else if(result == (int)result) {
                    screenTextView.setText(txtTextView.toString() + "\n= " + (int) result);
                    txtTextView.replace(0, txtTextView.length(), "" + (int)result);
                }
                else {
                    screenTextView.setText(txtTextView.toString() + "\n= " + result);
                    txtTextView.replace(0, txtTextView.length(), "" + result);
                }
                dotClicked = false;
            }
        });

        buttonErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(0 < txtTextView.length()) {
                    int pos = txtTextView.length() - 1;
                    char c = txtTextView.charAt(pos);
                    if(c == ')') parenthesisStack++;
                    else if(c == '(') parenthesisStack--;
                    else if(c == charDot) dotClicked = false;
                    txtTextView.deleteCharAt(pos);
                    screenTextView.setText(txtTextView.toString());
                }
            }
        });

        buttonErase.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                txtTextView.delete(0, txtTextView.length());
                screenTextView.setText(txtTextView.toString());
                parenthesisStack = 0;
                dotClicked = false;
                return true;
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtTextView.delete(0, txtTextView.length());
                screenTextView.setText(txtTextView.toString());
                result = 0;
                dotClicked = false;
            }
        });

        buttonERROR.setOnClickListener(new View.OnClickListener() { //Boton listo :)
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, getString(R.string.dontWork), Toast.LENGTH_SHORT).show();
                /*if(errorClicked){
                    MagicClass.causeError();
                } else {
                    errorClicked = true;
                }*/
            }
        });

        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtTextView.length() != 0) {
                    char c = txtTextView.charAt(txtTextView.length() - 1);
                    if('0' <= c && c <= '9' && !dotClicked){
                        txtTextView.append(charDot);
                        screenTextView.setText(txtTextView.toString());
                        dotClicked = true;
                    }
                }
            }
        });

        buttonPow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtTextView.length() != 0) {
                    char c = txtTextView.charAt(txtTextView.length() - 1);
                    if(c == getString(R.string.parenthesisClose).charAt(0) || '0' <= c && c <= '9'){
                        txtTextView.append(getString(R.string.powSymbol));
                        screenTextView.setText(txtTextView.toString());
                        dotClicked = false;
                    }
                }
            }
        });

        buttonGreater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtTextView.length() != 0) {
                    char c = txtTextView.charAt(txtTextView.length() - 1);
                    if((c == getString(R.string.parenthesisClose).charAt(0) || '0' <= c && c <= '9') &&
                    parenthesisStack == 0){
                        txtTextView.append(getString(R.string.greaterThanSymbol));
                        screenTextView.setText(txtTextView.toString());
                        dotClicked = false;
                    }
                }
            }
        });

        buttonLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtTextView.length() != 0) {
                    char c = txtTextView.charAt(txtTextView.length() - 1);
                    if((c == getString(R.string.parenthesisClose).charAt(0) || '0' <= c && c <= '9') &&
                    parenthesisStack == 0){
                        txtTextView.append(getString(R.string.lessThanSymbol));
                        screenTextView.setText(txtTextView.toString());
                        dotClicked = false;
                    }
                }
            }
        });

        buttonOpenParenthesis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtTextView.length() == 0 || txtTextView.charAt(txtTextView.length() - 1) != charDot){
                    txtTextView.append(getString(R.string.parenthesisOpen));
                    screenTextView.setText(txtTextView.toString());
                    parenthesisStack++;
                    dotClicked = false;
                }
            }
        });

        buttonCloseParenthesis.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(txtTextView.length() != 0){
                    char c = txtTextView.charAt(txtTextView.length() - 1);
                    if(c != '(' && c != charDot && 0 < parenthesisStack &&
                        !isOperator(c)){
                        txtTextView.append(getString(R.string.parenthesisClose));
                        screenTextView.setText(txtTextView.toString());
                        parenthesisStack--;
                        dotClicked = false;
                    }
                }
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        updatePersistentData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        updatePersistentData();
    }

    private void writeNumber(char num) {
        txtTextView.append(num);
        screenTextView.setText(txtTextView.toString());
    }

    private double processText(int start, int end){
        if(start >= end) return 0;

        boolean pOk = true;
        while(txtTextView.charAt(start) == '(' && pOk){
            int finP = start;
            int stack = 1;
            while(0 < stack){
                finP++;
                char c = txtTextView.charAt(finP);
                if(c == ')') stack--;
                else if(c == '(') stack++;
            }
            if(finP == end - 1){
                start++;
                end--;
            } else pOk = false;
        }

        boolean itHasOperators = txtTextView.charAt(start) == charSqrt;
        for(int i = start + 1; i < end; ++i){
            char c = txtTextView.charAt(i);
            if(isOperator(c) || c == '(' || c == ')') {
                itHasOperators = true;
                break;
            }
        }

        if(!itHasOperators) return Double.parseDouble(txtTextView.substring(start, end));

        int stack = 0;
        double izq = 0, der = 0;

        // Search for signs
        for(int i = start; i < end; ++i){
            char c = txtTextView.charAt(i);
            if(stack == 0 && c == '+'){
                izq = processText(start, i);
                der = processText(i + 1, end);
                izq = operate2(izq, der, c);
                System.out.println("Evaluando: " + txtTextView.substring(start, end));
                System.out.println("Resultado: " + izq);
                return izq;
            }
            if(c == '(') stack++;
            else if(c == ')') stack--;
        }

        stack = 0;
        // Substractions
        for(int i = start; i < end; ++i){
            char c = txtTextView.charAt(i),
                prevC;
            boolean previousOk = false;
            if(i != start){
                prevC = txtTextView.charAt(i - 1);
                if(('0' <= prevC && prevC <= '9') || prevC == ')')
                    previousOk = true;
            } else previousOk = true;

            if(c == '-' && stack == 0 && i != start && previousOk){
                izq = processText(start, i);
                der = processText(i, end);
                izq = operate2(izq, der, '+');
                System.out.println("Evaluando: " + txtTextView.substring(start, end));
                System.out.println("Resultado: " + izq);
                return izq;
            }
            if(c == '(') stack++;
            else if(c == ')') stack--;
        }

        // multiplications
        for(int i = start; i < end - 1; ++i){
            char c = txtTextView.charAt(i);
            if(c == '(') stack++;
            else if(c == ')') stack--;

            if(stack == 0){
                char sig = txtTextView.charAt(i + 1);
                if(c == '×'){
                    izq = processText(start, i);
                    der = processText(i + 1, end);
                    izq = operate2(izq, der, c);
                    System.out.println("Evaluando: " + txtTextView.substring(start, end));
                    System.out.println("Resultado: " + izq);
                    return izq;
                } else if(c == ')' && sig == '('){
                    izq = processText(start + 1, i);
                    der = processText(i + 1, end);
                    izq = operate2(izq, der, '×');
                    System.out.println("Evaluando: " + txtTextView.substring(start, end));
                    System.out.println("Resultado: " + izq);
                    return izq;
                } else if(('0' <= c && c <= '9' && (sig == '(' || sig == charSqrt) ) ||
                        (c == ')' && ('0' <= sig && sig <= '9' || sig == charSqrt) ) ){
                    izq = processText(start, i + 1);
                    der = processText(i + 1, end);
                    izq = operate2(izq, der, '×');
                    System.out.println("Evaluando: " + txtTextView.substring(start, end));
                    System.out.println("Resultado: " + izq);
                    return izq;
                }
            }
        }

        stack = 0;
        // division
        for(int i = end - 1; start <= i; --i){
            char c = txtTextView.charAt(i);
            if(c == '(') stack++;
            else if(c == ')') stack--;

            if(stack == 0){
                if(c == charDiv){
                    izq = processText(start, i);
                    der = processText(i + 1, end);

                    if(izq == 0 && der == 0) indeterminate = true;
                    else if(der == 0) undefined = true;

                    return operate2(izq, der, c);
                }
            }
        }

        stack = 0;
        // pow
        for(int i = end - 1; start <= i; --i){
            char c = txtTextView.charAt(i);
            if(c == '(') stack++;
            else if(c == ')') stack--;

            if(stack == 0){
                if(c == '^'){
                    der = processText(i + 1, end);

                    if(txtTextView.charAt(start) == '-'){
                        izq = processText(start + 1, i);
                        izq = -operate2(izq, der, c);
                    } else {
                        izq = processText(start, i);
                        izq = operate2(izq, der, c);
                    }

                    return izq;
                }
            }
        }

        ///sqrt
        for(int i = start; i < end; ++i){
            char c = txtTextView.charAt(i);
            if(c == charSqrt){
                izq = processText(i + 1, end);
                return Math.sqrt(izq);
            }
        }


        System.out.println("Evaluando: " + txtTextView.substring(start, end));
        System.out.println("Resultado: " + izq);

        return izq;
    }

    private double operate2(double num1, double num2, char op){
        switch (op){
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '×':
                return num1 * num2;
            case '÷':
                return num1 / num2;
            case '^':
                return Math.pow(num1, num2);
            default:
                return 0;
        }
    }

    private boolean isOperator(char c) {
        switch (c){
            case '×':
            case '÷':
            case '-':
            case '+':
            case '^':
            case '√':
                return true;
            default:
                return false;
        }
    }

    private boolean lastCharIsSign() {
        switch (txtTextView.charAt(txtTextView.length() - 1)){
            case '+':
            case '-':
                return true;
            default:
                return false;
        }
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

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void showAlertForMagicNumber(final int number, final int image, final boolean isPortrait, final String activityTitle){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(R.string.magicNumber);
        builder.setMessage(R.string.magicNumberMessage);
        View view = LayoutInflater.from(this).inflate(R.layout.alert_view, null);
        View viewSJMA = LayoutInflater.from(this).inflate(R.layout.alert_view_sjma, null);
        final EditText input = view.findViewById(R.id.editTextPassword);
        TextView text = view.findViewById(R.id.textViewNumerosMagicos);
        TextView textSJMA = viewSJMA.findViewById(R.id.textViewNumerosMagicosSJMA);

        text.setText(data.getMagicNumbersFound() + "/" + MAGIC_NUMBERS);
        textSJMA.setText(data.getMagicNumbersFound() + "/" + MAGIC_NUMBERS);

        if (number == 11723){
            builder.setView(viewSJMA);
        } else {
            builder.setView(view);
        }


        builder.setPositiveButton(R.string.canContinue, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (number == 11723){
                    MagicClass.causeError();
                } else if (input.getText().toString().equals(data.getContraOf(number))){
                    // TODO
                }

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void updatePersistentData(){
        realm.beginTransaction();
        data.setTxtOnScreen(txtTextView.toString());
        data.setLastResult(result);
        data.setDotClicked(dotClicked);
        realm.copyToRealmOrUpdate(data);
        realm.commitTransaction();
    }
}
