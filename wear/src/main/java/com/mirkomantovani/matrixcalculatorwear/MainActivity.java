package com.mirkomantovani.matrixcalculatorwear;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
                   //provo a fare così
                /*

                mRectBackground = (RelativeLayout) findViewById(R.id.rect_layout);
                mRoundBackground = (RelativeLayout) findViewById(R.id.round_layout);

                uno dei due sarà null, l'altro no, in base a questo, o meglio ancora, creo una textview invisible in entrambi i layout, in una gli scrivo dentro 1 e nell'altra 2, con lo stesso id
                poi prendo il testo e faccio if == 1 allora  setContentView(R.layout.    ); (e la procedura per cambiare layout aprendo una nuova activity, con un codice personalizzato di una classe, se no l'altro

*/
            }
        });
    }
}
