package com.mirkomantovani.matrixcalculatorwear;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import Jama.*;

/**
 * Created by Mirko on 09/04/2016.
 */
public class Calculator extends Activity {

    TextView tvDet1,tvEig1,tvEig2,tvEig3,tvRank1,tv3;
    EditText et1,et2,et3,et4,et5,et6,et7,et8,et9;
    EditText eti1,eti2,eti3,eti4,eti5,eti6,eti7,eti8,eti9;

    Button bCalculate,bColorGrey;

    Switch switch2x2;

    int currMatrix=3;
    Matrix A;
    Matrix Inverse;

    public void onInitialViewPress(View v){
        EditText et = (EditText)v;
        if(et.getText().toString().equals("0"))et.setText("");
    }


    public void onCalculate(View v){
        double n1,n2,n3,n4,n5,n6,n7,n8,n9;
        if(currMatrix==3) {
            n1 = parseWithDefault(et1.getText().toString(),0.0);
            //n1 = Double.parseDouble(et1.getText().toString());
            n2 = parseWithDefault(et2.getText().toString(),0.0);
            n3 = parseWithDefault(et3.getText().toString(),0.0);
            n4 = parseWithDefault(et4.getText().toString(),0.0);
            n5 = parseWithDefault(et5.getText().toString(),0.0);
            n6 = parseWithDefault(et6.getText().toString(),0.0);
            n7 = parseWithDefault(et7.getText().toString(),0.0);
            n8 = parseWithDefault(et8.getText().toString(),0.0);
            n9 = parseWithDefault(et9.getText().toString(),0.0);


            double[][] array = {{n1, n2, n3}, {n4, n5, n6}, {n7, n8, n9}};
            A = new Matrix(array);
            Inverse=new Matrix(array); //provo a commentare sta cosa
            //Inverse=A.inverse();
            //EigenVectorMatrix=A.eig().getV();


            //qui distinguo la funzione che voglio avere

            double det = A.det();           //quando il determinante è uguale a 0 l'app crasha, forse perchè calcola l'inversa dividendo per il determinante?
            if(det!=0) {
                Inverse = A.inverse();
            }
            //else   scrivo che l'inversa non si può fare

            double[] eig = A.eig().getRealEigenvalues();
            double[] imagEig = A.eig().getImagEigenvalues();
            int rank = A.rank();
            det=approximate(det);
            eig[0] = approximate(eig[0]);
            eig[1] = approximate(eig[1]);
            eig[2] = approximate(eig[2]);

            eig[0] = arrotonda(eig[0],5);
            eig[1] = arrotonda(eig[1],5);
            eig[2] = arrotonda(eig[2],5);

            tvDet1.setText(Double.toString(det));
            if(imagEig[0]==0&&imagEig[1]==0&&imagEig[2]==0){
                tvEig1.setText(Double.toString(eig[0]));
                tvEig2.setText(Double.toString(eig[1]));
                tvEig3.setText(Double.toString(eig[2]));
            }
            else {
                imagEig[0] = arrotonda(imagEig[0],5);
                imagEig[1] = arrotonda(imagEig[1],5);
                imagEig[2] = arrotonda(imagEig[2],5);
                tvEig1.setText(Double.toString(eig[0]) + " + " + imagEig[0]+"i");
                tvEig2.setText(Double.toString(eig[1]) + " + " + imagEig[1]+"i");
                tvEig3.setText(Double.toString(eig[2]) + " + " + imagEig[2]+"i");
            }
            tvRank1.setText(Integer.toString(rank));
            if(det!=0) {
                writeInverse();
            }

        }
        else {
            //n1 = Double.parseDouble(et1.getText().toString());
            n1 = parseWithDefault(et1.getText().toString(),0.0);
            n2 = parseWithDefault(et2.getText().toString(), 0.0);
            n4 = parseWithDefault(et4.getText().toString(), 0.0);
            n5 = parseWithDefault(et5.getText().toString(), 0.0);

            double[][] array = {{n1, n2}, {n4, n5}};
            A = new Matrix(array);
            Inverse=new Matrix(array);   //forse da commentare (eliminare)
            //Inverse=A.inverse();

            double det = A.det();
            if(det!=0) {
                Inverse = A.inverse();
            }
            double[] eig = A.eig().getRealEigenvalues();
            double[] imagEig = A.eig().getImagEigenvalues();
            int rank = A.rank();
            det=approximate(det);
            eig[0] = approximate(eig[0]);
            eig[1] = approximate(eig[1]);

            eig[0] = arrotonda(eig[0],5);
            eig[1] = arrotonda(eig[1],5);

            tvDet1.setText(Double.toString(det));
            if(imagEig[0]==0&&imagEig[1]==0){
                tvEig1.setText(Double.toString(eig[0]));
                tvEig2.setText(Double.toString(eig[1]));
            }
            else {
                imagEig[0] = arrotonda(imagEig[0],5);
                imagEig[1] = arrotonda(imagEig[1],5);
                tvEig1.setText(Double.toString(eig[0]) + " + " + imagEig[0]+"i");
                tvEig2.setText(Double.toString(eig[1]) + " + " + imagEig[1]+"i");
            }
            tvRank1.setText(Integer.toString(rank));
            writeInverse();
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        switch2x2= (Switch)findViewById(R.id.switch1);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        et4 = (EditText) findViewById(R.id.et4);
        et5 = (EditText) findViewById(R.id.et5);
        et6 = (EditText) findViewById(R.id.et6);
        et7 = (EditText) findViewById(R.id.et7);
        et8 = (EditText) findViewById(R.id.et8);
        et9 = (EditText) findViewById(R.id.et9);

        eti1 = (EditText) findViewById(R.id.eti1);
        eti2 = (EditText) findViewById(R.id.eti2);
        eti3 = (EditText) findViewById(R.id.eti3);
        eti4 = (EditText) findViewById(R.id.eti4);
        eti5 = (EditText) findViewById(R.id.eti5);
        eti6 = (EditText) findViewById(R.id.eti6);
        eti7 = (EditText) findViewById(R.id.eti7);
        eti8 = (EditText) findViewById(R.id.eti8);
        eti9 = (EditText) findViewById(R.id.eti9);

        tv3 = (TextView) findViewById(R.id.textView3);

        bCalculate = (Button) findViewById(R.id.button);


        tvDet1 = (TextView)findViewById(R.id.tvDet1);
        tvEig1 = (TextView) findViewById(R.id.tvEig1);
        tvEig2 = (TextView) findViewById(R.id.tvEig2);
        tvEig3 = (TextView) findViewById(R.id.tvEig3);
        tvRank1 = (TextView) findViewById(R.id.tvRank1);


        switch2x2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    et3.setVisibility(View.INVISIBLE);
                    et6.setVisibility(View.INVISIBLE);
                    et7.setVisibility(View.INVISIBLE);
                    et8.setVisibility(View.INVISIBLE);
                    et9.setVisibility(View.INVISIBLE);

                    eti3.setVisibility(View.INVISIBLE);
                    eti6.setVisibility(View.INVISIBLE);
                    eti7.setVisibility(View.INVISIBLE);
                    eti8.setVisibility(View.INVISIBLE);
                    eti9.setVisibility(View.INVISIBLE);

                    tvEig3.setVisibility(View.INVISIBLE);
                    switch2x2.setText("Switch to 3X3 Matrix:");
                    currMatrix = 2;

                } else {
                    et3.setVisibility(View.VISIBLE);
                    et6.setVisibility(View.VISIBLE);
                    et7.setVisibility(View.VISIBLE);
                    et8.setVisibility(View.VISIBLE);
                    et9.setVisibility(View.VISIBLE);

                    eti3.setVisibility(View.VISIBLE);
                    eti6.setVisibility(View.VISIBLE);
                    eti7.setVisibility(View.VISIBLE);
                    eti8.setVisibility(View.VISIBLE);
                    eti9.setVisibility(View.VISIBLE);

                    tvEig3.setVisibility(View.VISIBLE);

                    switch2x2.setText("Switch to 2X2 Matrix:");
                    currMatrix = 3;

                }
            }
        });


    }


    private double approximate(double d){    //approssima valori tipo 5.999999999999 a 6 oppure 2.00000000001 a 2   //problema se in input ho un negativo, esce 0
        int n,sign=0;
        double resto;
        if(d<0){
            sign=1;
            d=d*(-1);
        }
        n=(int)d;
        resto=d-n;
        if(resto<0.0001){
            if(sign==1){
                n=n*(-1);
                return n;    //anche se dovrebbe ritornare un double non so perchè ma non mi dà errore
            }
            else return n;
        }
        else if((1-resto)<0.0001){
            n=n+1;
            if(sign==1) {
                n=n*(-1);
                return n;
            }
            else return n;
        }
        else if(sign==1) return d*(-1);
        else return d;
    }

    private void writeInverse(){
        if(currMatrix==3){ approximateInverse();
            eti1.setText(Double.toString(arrotonda(Inverse.get(0, 0), 5)));
            eti2.setText(Double.toString(arrotonda(Inverse.get(0, 1), 5)));
            eti3.setText(Double.toString(arrotonda(Inverse.get(0, 2), 5)));
            eti4.setText(Double.toString(arrotonda(Inverse.get(1, 0), 5)));
            eti5.setText(Double.toString(arrotonda(Inverse.get(1, 1),5)));
            eti6.setText(Double.toString(arrotonda(Inverse.get(1, 2), 5)));
            eti7.setText(Double.toString(arrotonda(Inverse.get(2, 0), 5)));
            eti8.setText(Double.toString(arrotonda(Inverse.get(2, 1), 5)));
            eti9.setText(Double.toString(arrotonda(Inverse.get(2, 2), 5)));
        }
        else {
            eti1.setText(Double.toString(arrotonda(Inverse.get(0, 0), 5)));
            eti2.setText(Double.toString(arrotonda(Inverse.get(0, 1), 5)));
            eti4.setText(Double.toString(arrotonda(Inverse.get(1, 0), 5)));
            eti5.setText(Double.toString(arrotonda(Inverse.get(1, 1),5)));

        }
    }
    private double arrotonda(double d, int p)
    {
        return Math.rint(d*Math.pow(10,p))/Math.pow(10,p);
    }


    double parseWithDefault(String s, Double def) {
        try {
            return Double.parseDouble(s);
        }
        catch (NumberFormatException e) {
            // It's OK to ignore "e" here because returning a default value is the documented behaviour on invalid input.
            //tv3.setText("Error, invalid input");
            Toast.makeText(this, "ERROR, invalid input",
                    Toast.LENGTH_LONG).show();
            return def;

        }
    }

    private void approximateInverse(){
        double d,resto;
        int n,sign=0,i,j;

        if(currMatrix==3) {
            for(i=0;i<3;i++){
                for(j=0;j<3;j++){
                    d = Inverse.get(i,j);
                    if (d < 0) {
                        sign = 1;
                        d = d * (-1);
                    }
                    d = approximate(d);
                    d = d * 10;
                    n = (int) d;
                    resto = d - n;
                    if (1 - resto < 0.0001) {
                        n++;
                        d = n;
                        d = d / 10;
                        if (sign == 1) d = d * (-1);
                        Inverse.set(i,j, d);
                    }
                    d = d * 100;
                    n = (int) d;
                    resto = d - n;
                    if (1 - resto < 0.0001) {
                        n++;
                        d = n;
                        d = d / 100;
                        if (sign == 1) d = d * (-1);
                        Inverse.set(i,j, d);
                    }
                }
            }

        }
        else {
            for(i=0;i<2;i++){
                for(j=0;j<2;j++){
                    d = Inverse.get(i,j);
                    if (d < 0) {
                        sign = 1;
                        d = d * (-1);
                    }
                    d = approximate(d);
                    d = d * 10;
                    n = (int) d;
                    resto = d - n;
                    if (1 - resto < 0.0001) {
                        n++;
                        d = n;
                        d = d / 10;
                        if (sign == 1) d = d * (-1);
                        Inverse.set(i,j, d);
                    }
                    d = d * 100;
                    n = (int) d;
                    resto = d - n;
                    if (1 - resto < 0.0001) {
                        n++;
                        d = n;
                        d = d / 100;
                        if (sign == 1) d = d * (-1);
                        Inverse.set(i,j, d);
                    }
                }
            }

        }
    }



}
