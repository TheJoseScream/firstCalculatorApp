package com.example.calculator.models;

public class ButtonCalculator {

    private int buttonID;
    private String data;

    public ButtonCalculator(int buttonID, String data){
        this.buttonID=buttonID;
        this.data=data;
    }

    public int getButtonID() {
        return buttonID;
    }

    public void setButtonID(int buttonID) {
        this.buttonID = buttonID;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
