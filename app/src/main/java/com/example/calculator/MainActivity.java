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
    private double calculator;
    private String operator;
    private String lastNumber;

    private ArrayList<String> history;

    private String currentNumber;
    private String prevNumber;
    private double results;

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
        prevNumber="";
        results=0;

        history=new ArrayList();

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
                calcScreen.setText("");
                resutlScreen.setText("");
                operator="";

                currentNumber="";
                prevNumber="";
                results=0;
                break;
            }
            case R.id.btn_plus:{
                history.add(currentNumber);
                history.add("+");

                operator="SUM";
                currentNumber="";
                prevNumber=""+(int) results;

                System.out.println(operator);
                calcScreen.setText(calcScreen.getText().toString()+"+");
                break;
            }
            case R.id.btn_minus:{
                operator="MIN";
                System.out.println(operator);
                calcScreen.setText(calcScreen.getText().toString()+"-");
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
//                operator="PER";
                System.out.println(operator);
                if(!currentNumber.isEmpty()){
                    currentNumber=currentNumber.replaceFirst(".$","");
                    calcScreen.setText(calcScreen.getText().toString().replaceFirst(".$",""));
                    resutlScreen.setText("");
                }else{
                    if(history!=null && !history.isEmpty()){
                        if(isNumeric(history.get(history.size()-1))){
                            System.out.println("numeric");
                        }else{
                            if(history.size()>=3){
                                System.out.println("NaN");
                                history.remove(history.size()-1);
                                currentNumber=history.get(history.size()-1);
                                history.remove(history.size()-1);
                                prevNumber=history.get(history.size()-2);
                            }else if(history.size()<3){
                                System.out.println("NaN2");
                                history.remove(history.size()-1);
                                currentNumber=history.get(history.size()-1);
                            }
                        }
                        calcScreen.setText(calcScreen.getText().toString().replaceFirst(".$",""));
                        resutlScreen.setText("");
                    }
                }

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
    }

    private void calculate(double number){
        currentNumber=currentNumber+(int) number;
        if(operator.isEmpty()){
            calcScreen.setText(calcScreen.getText().toString()+(int) number);
            results=Double.parseDouble(currentNumber);
            System.out.println(results);
            resutlScreen.setText(""+(int) results);
        }
        if(operator.equals("SUM")){
            System.out.println(prevNumber+"+"+currentNumber);
            results=Double.parseDouble(prevNumber)+Double.parseDouble(currentNumber);
            System.out.println(results);
            calcScreen.setText(calcScreen.getText().toString()+(int) number);
            resutlScreen.setText(""+(int) results);
        }
    }

    public void calculator(){
        double res=0;
        double calc=0;
        double tmp=0;
        String data="";
        String action="";
        for(int i=0; i<history.size(); i++){
            if(history.size()>=2){
                System.out.println("es mayor a 3");
            }
        }
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
