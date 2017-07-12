package com.mirkomantovani.matrixcalculatorwear;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import Jama.*;




/**
 * Created by Mirko on 21/03/2016.
 */
public class RoundActivity extends Activity {

    TextView tvDet1,tvEig1,tvEig2,tvEig3,tvRank1;

    Switch switch2x2;
    boolean changeInitialActivity=true;
    boolean checked=true;

    Button b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,bu1,bu2,bu3,bu4,bu5,bu6,bu7,bu8,bu9,bC,bdot,bCalculate,bColorGrey,bInv1,bInv2,bInv3,bInv4,bInv5,bInv6,bInv7,bInv8,bInv9;
    int currCellSelected=0;
    int currMatrix=3;
    public String currText="";



    public static String function="";

    //double[][] array = {{1.,2.,3},{4.,5.,6.},{7.,8.,10.}};
    Matrix A;
    Matrix Inverse;
    Matrix EigenVectorMatrix;
/*
    double a= A.det();
    Matrix b = Matrix.random(3,1);
    Matrix x = A.solve(b);
    Matrix Residual = A.times(x).minus(b);
    double rnorm = Residual.normInf();
*/

    public void onSwitchButtonClick(View v){
        Button butt = (Button)v;
        checked=!checked;
        if (!checked) {
            bu3.setVisibility(View.INVISIBLE);
            bu6.setVisibility(View.INVISIBLE);
            bu7.setVisibility(View.INVISIBLE);
            bu8.setVisibility(View.INVISIBLE);
            bu9.setVisibility(View.INVISIBLE);

            bInv3.setVisibility(View.INVISIBLE);
            bInv6.setVisibility(View.INVISIBLE);
            bInv7.setVisibility(View.INVISIBLE);
            bInv8.setVisibility(View.INVISIBLE);
            bInv9.setVisibility(View.INVISIBLE);

            tvEig3.setVisibility(View.INVISIBLE);
            butt.setText("Switch to 3X3 matrix");
            currMatrix=2;

        } else {
            bu3.setVisibility(View.VISIBLE);
            bu6.setVisibility(View.VISIBLE);
            bu7.setVisibility(View.VISIBLE);
            bu8.setVisibility(View.VISIBLE);
            bu9.setVisibility(View.VISIBLE);

            bInv3.setVisibility(View.VISIBLE);
            bInv6.setVisibility(View.VISIBLE);
            bInv7.setVisibility(View.VISIBLE);
            bInv8.setVisibility(View.VISIBLE);
            bInv9.setVisibility(View.VISIBLE);

            tvEig3.setVisibility(View.VISIBLE);

            butt.setText("Switch to 2X2 matrix");
            currMatrix=3;

        }
    }


    public void onCancelClick(View v){
        Button b;
        b=findSelectedCell();
        b.setText("0");
    }
    public void onMinusClick(View v){
        Button b;
        String s;
        double n;
        int nint;
        b=findSelectedCell();
        s=b.getText().toString();
        n = Double.parseDouble(s);
        if(n>0)n=n*(-1);
        nint=(int)n;
        b.setText(Integer.toString(nint));

    }

    public void onNumberClick(View v){
    Button butt = (Button)v;
        String str=butt.getText().toString();
        Button b;
        String st;
        b=findSelectedCell();
        st=b.getText().toString();

        if(st.equals("0")){
            st="";
        }

        b.setText(st+str);
    }

    public void onCellClick(View v){
        Button butt = (Button)v;
        //bu1.setBackgroundResource(R.drawable.immagine);
        //bu2.setBackgroundResource(R.drawable.immagine);
        //bu1.setBackgroundColor(13158600);
        //bu2.setBackgroundColor(10066329);                                   //sti porco dio di setbackgroundtintlist e getbackgroundtintlist require API 21 e l'ho dovuto cambiare nel gradle wear
        bu1.setBackgroundTintList(bColorGrey.getBackgroundTintList());
        bu2.setBackgroundTintList(bColorGrey.getBackgroundTintList());
        bu3.setBackgroundTintList(bColorGrey.getBackgroundTintList());
        bu4.setBackgroundTintList(bColorGrey.getBackgroundTintList());
        bu5.setBackgroundTintList(bColorGrey.getBackgroundTintList());
        bu6.setBackgroundTintList(bColorGrey.getBackgroundTintList());
        bu7.setBackgroundTintList(bColorGrey.getBackgroundTintList());
        bu8.setBackgroundTintList(bColorGrey.getBackgroundTintList());
        bu9.setBackgroundTintList(bColorGrey.getBackgroundTintList());

        //bu9.setBackgroundColor(10066329);
        //butt.setBackgroundColor(13816530);
        butt.setBackgroundTintList(bCalculate.getBackgroundTintList());
        currCellSelected=butt.getMaxLines();
    }

    public void onCalculate(View v){
        double n1,n2,n3,n4,n5,n6,n7,n8,n9;
        if(currMatrix==3) {
            n1 = Double.parseDouble(bu1.getText().toString());
            n2 = Double.parseDouble(bu2.getText().toString());
            n3 = Double.parseDouble(bu3.getText().toString());
            n4 = Double.parseDouble(bu4.getText().toString());
            n5 = Double.parseDouble(bu5.getText().toString());
            n6 = Double.parseDouble(bu6.getText().toString());
            n7 = Double.parseDouble(bu7.getText().toString());
            n8 = Double.parseDouble(bu8.getText().toString());
            n9 = Double.parseDouble(bu9.getText().toString());


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
            n1 = Double.parseDouble(bu1.getText().toString());
            n2 = Double.parseDouble(bu2.getText().toString());
            n4 = Double.parseDouble(bu4.getText().toString());
            n5 = Double.parseDouble(bu5.getText().toString());

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
/*
    @Override
    protected void onResume() {
        super.onResume();
        TextView t=(TextView)findViewById(R.id.text);
        t.setText(function+"("+currText+")");

    }
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.round_activity_main);
        ShapeWear.initShapeWear(this);

        ShapeWear.setOnShapeChangeListener(new ShapeWear.OnShapeChangeListener() {
            @Override
            public void shapeDetected(ShapeWear.ScreenShape screenShape) {
                //Do your stuff here for example:
                if(changeInitialActivity) {
                    changeInitialActivity=false;
                    switch (screenShape) {
                        case RECTANGLE:
                            setContentView(R.layout.rect_activity_main);
                            b1 = (Button) findViewById(R.id.b1);   //un sacco di questi li posso togliere, credo tutti questi bi
                            b2 = (Button) findViewById(R.id.b2);
                            b3 = (Button) findViewById(R.id.b3);
                            b4 = (Button) findViewById(R.id.b4);
                            b5 = (Button) findViewById(R.id.b5);
                            b6 = (Button) findViewById(R.id.b6);
                            b7 = (Button) findViewById(R.id.b7);
                            b8 = (Button) findViewById(R.id.b8);
                            b9 = (Button) findViewById(R.id.b9);
                            b0 = (Button) findViewById(R.id.b0);
                            bC = (Button) findViewById(R.id.bC);
                            bdot = (Button) findViewById(R.id.bdot);

                            bu1 = (Button) findViewById(R.id.button1);
                            bu2 = (Button) findViewById(R.id.button2);
                            bu3 = (Button) findViewById(R.id.button3);
                            bu4 = (Button) findViewById(R.id.button4);
                            bu5 = (Button) findViewById(R.id.button5);
                            bu6 = (Button) findViewById(R.id.button6);
                            bu7 = (Button) findViewById(R.id.button7);
                            bu8 = (Button) findViewById(R.id.button8);
                            bu9 = (Button) findViewById(R.id.button9);

                            bInv1 = (Button) findViewById(R.id.buttons1);
                            bInv2 = (Button) findViewById(R.id.buttons2);
                            bInv3 = (Button) findViewById(R.id.buttons3);
                            bInv4 = (Button) findViewById(R.id.buttons4);
                            bInv5 = (Button) findViewById(R.id.buttons5);
                            bInv6 = (Button) findViewById(R.id.buttons6);
                            bInv7 = (Button) findViewById(R.id.buttons7);
                            bInv8 = (Button) findViewById(R.id.buttons8);
                            bInv9 = (Button) findViewById(R.id.buttons9);

                            bCalculate = (Button) findViewById(R.id.button);

                            bColorGrey  = (Button) findViewById(R.id.bColorGrey);

                            tvDet1 = (TextView)findViewById(R.id.tvDet1);
                            tvEig1 = (TextView) findViewById(R.id.tvEig1);
                            tvEig2 = (TextView) findViewById(R.id.tvEig2);
                            tvEig3 = (TextView) findViewById(R.id.tvEig3);
                            tvRank1 = (TextView) findViewById(R.id.tvRank1);

                            switch2x2= (Switch) findViewById(R.id.switch1);
                            //bu1.setText("1");
                            //bC.scrollTo(3,3);
                            //bC.setLeft(6);
                            //bC.setPadding(9,0,0,0);
                            //bC.setLayoutParams();
                            //RelativeLayout.LayoutParams params;

                            /*
                            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) bC.getLayoutParams();
                            params.leftMargin = 1;
                            bC.setLayoutParams(params);
                            params = (ViewGroup.MarginLayoutParams) b2.getLayoutParams();
                            params.rightMargin = 1;
                            b2.setLayoutParams(params);
                            params = (ViewGroup.MarginLayoutParams) b1.getLayoutParams();
                            params.topMargin = 1;
                            b1.setLayoutParams(params);
                            bu1.setWidth(30);

                            */
                            //al massimo metto padding top per il numero 6 e qui faccio getpaddingtop e lo cambio, oppure lascio 5 6 e 7 allineati e basta

                            //params.setMargins();

                            break;
                        case ROUND:
                            //bu2.setText("1");
                            //setContentView(R.layout.round_activity_main);
                            break;
                        case MOTO_ROUND:
                            //bu3.setText("1");
                            //setContentView(R.layout.round_activity_main);
                            //as it is special case of ROUND - cut at the bottom.
                            break;
                    }
                }
            }
        });
        b1 = (Button) findViewById(R.id.b1);   //un sacco di questi li posso togliere, credo tutti questi bi
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
        b6 = (Button) findViewById(R.id.b6);
        b7 = (Button) findViewById(R.id.b7);
        b8 = (Button) findViewById(R.id.b8);
        b9 = (Button) findViewById(R.id.b9);
        b0 = (Button) findViewById(R.id.b0);
        bC = (Button) findViewById(R.id.bC);
        bdot = (Button) findViewById(R.id.bdot);

        bu1 = (Button) findViewById(R.id.button1);
        bu2 = (Button) findViewById(R.id.button2);
        bu3 = (Button) findViewById(R.id.button3);
        bu4 = (Button) findViewById(R.id.button4);
        bu5 = (Button) findViewById(R.id.button5);
        bu6 = (Button) findViewById(R.id.button6);
        bu7 = (Button) findViewById(R.id.button7);
        bu8 = (Button) findViewById(R.id.button8);
        bu9 = (Button) findViewById(R.id.button9);

        bInv1 = (Button) findViewById(R.id.buttons1);
        bInv2 = (Button) findViewById(R.id.buttons2);
        bInv3 = (Button) findViewById(R.id.buttons3);
        bInv4 = (Button) findViewById(R.id.buttons4);
        bInv5 = (Button) findViewById(R.id.buttons5);
        bInv6 = (Button) findViewById(R.id.buttons6);
        bInv7 = (Button) findViewById(R.id.buttons7);
        bInv8 = (Button) findViewById(R.id.buttons8);
        bInv9 = (Button) findViewById(R.id.buttons9);

        bCalculate = (Button) findViewById(R.id.button);

        bColorGrey  = (Button) findViewById(R.id.bColorGrey);

        tvDet1 = (TextView)findViewById(R.id.tvDet1);
        tvEig1 = (TextView) findViewById(R.id.tvEig1);
        tvEig2 = (TextView) findViewById(R.id.tvEig2);
        tvEig3 = (TextView) findViewById(R.id.tvEig3);
        tvRank1 = (TextView) findViewById(R.id.tvRank1);

        switch2x2= (Switch) findViewById(R.id.switch1);

        switch2x2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    bu3.setVisibility(View.INVISIBLE);
                    bu6.setVisibility(View.INVISIBLE);
                    bu7.setVisibility(View.INVISIBLE);
                    bu8.setVisibility(View.INVISIBLE);
                    bu9.setVisibility(View.INVISIBLE);

                    bInv3.setVisibility(View.INVISIBLE);
                    bInv6.setVisibility(View.INVISIBLE);
                    bInv7.setVisibility(View.INVISIBLE);
                    bInv8.setVisibility(View.INVISIBLE);
                    bInv9.setVisibility(View.INVISIBLE);

                    tvEig3.setVisibility(View.INVISIBLE);
                    switch2x2.setText("Switch to 3X3 matrix:");
                    currMatrix=2;

                } else {
                    bu3.setVisibility(View.VISIBLE);
                    bu6.setVisibility(View.VISIBLE);
                    bu7.setVisibility(View.VISIBLE);
                    bu8.setVisibility(View.VISIBLE);
                    bu9.setVisibility(View.VISIBLE);

                    bInv3.setVisibility(View.VISIBLE);
                    bInv6.setVisibility(View.VISIBLE);
                    bInv7.setVisibility(View.VISIBLE);
                    bInv8.setVisibility(View.VISIBLE);
                    bInv9.setVisibility(View.VISIBLE);

                    tvEig3.setVisibility(View.VISIBLE);

                    switch2x2.setText("Switch to 2X2 matrix:");
                    currMatrix=3;

                }
            }
        });
        /*

        bu1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                bu1.setText("");
                bu1.setBackgroundColor(13816530);
                currCellSelected=1;
            }
        });
        bu2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                bu1.setText("");
                bu1.setBackgroundColor(13816530);
                currCellSelected=2;
            }
        });
        bu3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                bu1.setText("");
                bu1.setBackgroundColor(13816530);
                currCellSelected=3;
            }
        });
        bu4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                bu1.setText("");
                bu1.setBackgroundColor(13816530);
                currCellSelected=4;
            }
        });
        bu5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                bu1.setText("");
                bu1.setBackgroundColor(13816530);
                currCellSelected=5;
            }
        });
        bu6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                bu1.setText("");
                bu1.setBackgroundColor(13816530);
                currCellSelected=6;
            }
        });
        bu7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                bu1.setText("");
                bu1.setBackgroundColor(13816530);
                currCellSelected=7;
            }
        });
        bu8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                bu1.setText("");
                bu1.setBackgroundColor(13816530);
                currCellSelected=8;
            }
        });
        bu9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                bu1.setText("");
                bu1.setBackgroundColor(13816530);
                currCellSelected=9;
            }
        });

*/
        bC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Button b;
                b=findSelectedCell();
                b.setText("0");


            }

        });
    }

    private Button findSelectedCell(){
        if(currCellSelected==10) return bu1;   //qui ho messo 10 al posto che 1 perchè se no non si vedeva la seconda riga se i numeri diventavano lunghi
        else if(currCellSelected==2) return bu2;
        else if(currCellSelected==3) return bu3;
        else if(currCellSelected==4) return bu4;
        else if(currCellSelected==5) return bu5;
        else if(currCellSelected==6) return bu6;
        else if(currCellSelected==7) return bu7;
        else if(currCellSelected==8) return bu8;
        else if(currCellSelected==9) return bu9;

        return bu1;
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
            bInv1.setText(Double.toString(arrotonda(Inverse.get(0, 0), 5)));
            bInv2.setText(Double.toString(arrotonda(Inverse.get(0, 1), 5)));
            bInv3.setText(Double.toString(arrotonda(Inverse.get(0, 2), 5)));
            bInv4.setText(Double.toString(arrotonda(Inverse.get(1, 0), 5)));
            bInv5.setText(Double.toString(arrotonda(Inverse.get(1, 1),5)));
            bInv6.setText(Double.toString(arrotonda(Inverse.get(1, 2), 5)));
            bInv7.setText(Double.toString(arrotonda(Inverse.get(2, 0), 5)));
            bInv8.setText(Double.toString(arrotonda(Inverse.get(2, 1), 5)));
            bInv9.setText(Double.toString(arrotonda(Inverse.get(2, 2), 5)));
        }
        else {
            bInv1.setText(Double.toString(arrotonda(Inverse.get(0, 0), 5)));
            bInv2.setText(Double.toString(arrotonda(Inverse.get(0, 1), 5)));
            bInv4.setText(Double.toString(arrotonda(Inverse.get(1, 0), 5)));
            bInv5.setText(Double.toString(arrotonda(Inverse.get(1, 1),5)));

        }
    }
    private double arrotonda(double d, int p)
    {
        return Math.rint(d*Math.pow(10,p))/Math.pow(10,p);
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
