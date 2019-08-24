package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculator.models.ButtonCalculator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int[] btnIDS={
            R.id.btn_AC,
            R.id.btn_percent,
            R.id.btn_divide,
            R.id.btn_multiply,
            R.id.btn_7,
            R.id.btn_8,
            R.id.btn_9,
            R.id.btn_minus,
            R.id.btn_4,
            R.id.btn_5,
            R.id.btn_6,
            R.id.btn_plus,
            R.id.btn_1,
            R.id.btn_2,
            R.id.btn_3,
            R.id.btn_equal,
            R.id.btn_0,
            R.id.btn_dot,
    };
    private TextView calcScreen;
    private TextView resutlScreen;

    private String operator;
    private ArrayList<String> history;
    private String currentNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calcScreen= findViewById(R.id.text_screen);
        calcScreen.setText("");

        resutlScreen= findViewById(R.id.result_screen);
        resutlScreen.setText("");

        operator="";

        currentNumber="";

        history=new ArrayList();
        history.add("");

        for (int id :btnIDS){
            Button temp=findViewById(id);
            temp.setOnClickListener(this);
            System.out.println("hola loco");
        }
    }

    @Override
    public void onClick(View view) {
        int btnID=view.getId();
        Button tempBtn=(Button) view;

        switch (btnID){
            case R.id.btn_AC:{
                history.clear();
                history.add("");

                calcScreen.setText("");
                resutlScreen.setText("");
                operator="";

                currentNumber="";
                break;
            }
            case R.id.btn_plus:{
                if(history.size()>0 && !history.get(0).equals("")){
                    history.add("+");
                    history.add("");

                    operator="SUM";
                    currentNumber="";

                    System.out.println(operator);
                    calcScreen.setText(calcScreen.getText().toString()+"+");
                }
                break;
            }
            case R.id.btn_minus:{
                if(history.size()>0 && !history.get(0).equals("")){
                    history.add("-");
                    history.add("");

                    currentNumber="";
                    operator="MIN";
                    System.out.println(operator);
                    calcScreen.setText(calcScreen.getText().toString()+"-");
                }else{
                    currentNumber="-";
                    calcScreen.setText(calcScreen.getText().toString()+"-");
                    System.out.println(currentNumber);
                }
                break;
            }
            case R.id.btn_multiply:{
                operator="MULT";
                System.out.println(operator);
                calcScreen.setText(calcScreen.getText().toString()+"*");
                break;
            }
            case R.id.btn_divide:{
                operator="DIV";
                System.out.println(operator);
                calcScreen.setText(calcScreen.getText().toString()+"/");
                break;
            }
            case R.id.btn_percent:{
                System.out.println("del");
                if(!currentNumber.isEmpty()){
                    currentNumber=currentNumber.replaceFirst(".$","");
                    calcScreen.setText(calcScreen.getText().toString().replaceFirst(".$",""));
                }else{
                    if(history!=null && !history.isEmpty()){
                        history.remove(history.size()-1);

                        if(!isNumeric(history.get(history.size()-1))){
                            history.remove(history.size()-1);
                            currentNumber=history.get(history.size()-1);
                        }else{
                            System.out.println("es un numero");
                        }
                        calcScreen.setText(calcScreen.getText().toString().replaceFirst(".$",""));
                    }
                }
                calculator();
                break;
            }
            case R.id.btn_dot:{
                operator="DOT";
                System.out.println(operator);
                calcScreen.setText(calcScreen.getText().toString()+".");
                break;
            }
            default:{
                double number=Double.parseDouble(tempBtn.getText().toString());
                concat(number);
                calculator();
                Toast.makeText(this,"toco el boton "+view.getId(),Toast.LENGTH_SHORT).show();

                for(String log : history){
                    System.out.println("este es el log"+log);
                }
                break;
            }
        }
    }

    public void concat(double number){
        currentNumber=currentNumber+(int) number;
        history.set(history.size()-1,currentNumber);

        for (String log : history) {
            System.out.println(log+" este es el log concat");
        }
        calcScreen.setText(calcScreen.getText().toString()+(int) number);
    }

    public void calculator(){
        System.out.println("calculando");
        double calc=0;
        double tmp=0;

        if(history.get(0)!=null){
            calc = Double.parseDouble(history.get(0));
        }

        for(int i=0; i<history.size(); i++) {
            if (history.size() >= 2) {
                if (history.get(i).equals("+")) {
                    tmp = Double.parseDouble(history.get(i + 1));
                    calc += tmp;
                }
                if (history.get(i).equals("-")) {
                    tmp = Double.parseDouble(history.get(i + 1));
                    calc -= tmp;
                }
            }
        }
        resutlScreen.setText(""+(int) calc);
    }

    public static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
}
