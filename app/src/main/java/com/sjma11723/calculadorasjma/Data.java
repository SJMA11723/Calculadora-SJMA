package com.sjma11723.calculadorasjma;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by SJMA 11723 on 08/04/2018.
 */
public class Data extends RealmObject {
    @PrimaryKey
    private int primaryKey;

    private int magicNumbersFound;
    private String txtOnScreen = "";
    private double lastResult;
    private boolean dotClicked;
    private boolean _11723Found;
    private boolean _1020503Found;
    private boolean _210703Found;
    private boolean _280203Found;

    public Data(){
        setTxtOnScreen("Bienvenido");
        setLastResult(0);
        setPrimaryKey(1);
        _11723Found = false;
        _210703Found = false;
        _280203Found = false;
        _1020503Found = false;
        magicNumbersFound = 0;
        dotClicked = false;
    }

    public Data( String txtOnScreen, double lastResult, int primaryKey){
        setTxtOnScreen(txtOnScreen);
        setLastResult(lastResult);
        setPrimaryKey(primaryKey);
        _11723Found = false;
        _210703Found = false;
        _280203Found = false;
        _1020503Found = false;
        dotClicked = false;
        magicNumbersFound = 0;
    }

    public void setDotClicked(boolean clicked){
        this.dotClicked = clicked;
    }

    public boolean getDotClicked(){
        return dotClicked;
    }

    public void updateMagicNumberFound(final int numberFound){
        switch (numberFound){
            case 11723:
                _11723Found = true;
                magicNumbersFound++;
                break;
            case 20503:
                _1020503Found = true;
                magicNumbersFound++;
                break;
            case 210703:
                _210703Found = true;
                magicNumbersFound++;
                break;
            case 280203:
                _280203Found = true;
                magicNumbersFound++;
                break;
        }
    }

    public int getPrimaryKey() {
        return primaryKey;
    }

    private void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    public void setLastResult(double lastResult){
        this.lastResult = lastResult;
    }

    public double getLastResult(){
        return lastResult;
    }

    public int getMagicNumbersFound() {
        return magicNumbersFound;
    }

    public void setMagicNumbersFound(int magicNumbersFound) {
        this.magicNumbersFound = magicNumbersFound;
    }

    public String getTxtOnScreen() {
        return txtOnScreen;
    }

    public void setTxtOnScreen(String txtOnScreen) {
        this.txtOnScreen = txtOnScreen;
    }

    public boolean is_11723Found() {
        return _11723Found;
    }

    public boolean is_1020503Found() {
        return _1020503Found;
    }

    public boolean is_210703Found() {
        return _210703Found;
    }

    public boolean is_280203Found() {
        return _280203Found;
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
}
