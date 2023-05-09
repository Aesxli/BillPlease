package sg.edu.rp.c346.id22012205.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    EditText AMTInput;
    EditText PAXInput;
    ToggleButton SVSButton;
    ToggleButton GSTButton;
    EditText DISCInput;
    RadioGroup Paymentgroup;
    Button SButton;
    Button RButton;
    TextView Tview;
    TextView SView;

    Button CAButton;
    Button PAYButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AMTInput=findViewById(R.id.Amounttext);
        PAXInput=findViewById(R.id.paxtext);
        SVSButton=findViewById(R.id.SVS);
        GSTButton=findViewById(R.id.GST);
        DISCInput=findViewById(R.id.Discounttext);
        Paymentgroup=findViewById(R.id.payment);
        SButton=findViewById(R.id.spiltButton);
        RButton=findViewById(R.id.Resetbutton);
        Tview=findViewById(R.id.totalview);
        SView=findViewById(R.id.spiltview);
        CAButton=findViewById(R.id.cashradioButton);
        PAYButton=findViewById(R.id.paynowradioButton);
        RButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AMTInput.setText("");
                PAXInput.setText("");
                SVSButton.setChecked(false);
                GSTButton.setChecked(false);
                DISCInput.setText("");

            }
        });

        SButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               Double GSTC=1.08;
               Double SVSC=1.1;
                if(AMTInput.getText().toString().trim().length()!=0 && PAXInput.getText().toString().trim().length()!=0){
                    double NAMT = 0.0;

                    if (SVSButton.isChecked() && GSTButton.isChecked()){
                        NAMT = Double.parseDouble(AMTInput.getText().toString()) * SVSC*GSTC;
                    }

                    else if(!SVSButton.isChecked()&&  GSTButton.isChecked()){
                        NAMT = Double.parseDouble(AMTInput.getText().toString())* GSTC;
                    }

                    else if(SVSButton.isChecked() && !GSTButton.isChecked()){
                        NAMT = Double.parseDouble(AMTInput.getText().toString())* SVSC;
                    }
                    else{

                        NAMT = Double.parseDouble(AMTInput.getText().toString());
                    }

                    if(DISCInput.getText().toString().trim().length() != 0){
                        NAMT *= 1 - Double.parseDouble(DISCInput.getText().toString())/100;
                    }

                    Tview.setText("Total Bill: $" + String.format("%.2f", NAMT));

                    int NOP =Integer.parseInt(PAXInput.getText().toString());

                    int RPayment = Paymentgroup.getCheckedRadioButtonId();

                    if (NOP!=1 && RPayment == R.id.paynowradioButton) {
                        SView.setText("Each Pays: $" + String.format("%.2f", NAMT / NOP)+ " via PayNow to 912345678") ;
                    }

                    else if(NOP==1 && RPayment == R.id.paynowradioButton) {
                        SView.setText("Each Pays: $" + String.format("%.2f",NAMT) + "via PayNow to 912345678");
                    }

                    else if(NOP!=1 && RPayment == R.id.cashradioButton){
                        SView.setText("Each Pays: $" + String.format("%.2f", NAMT / NOP)+ " in Cash") ;
                    }

                    else{
                        SView.setText("Each Pays: $" + String.format("%.2f",NAMT) + " in Cash");
                    }


                }else if(AMTInput.getText().toString().trim().length()==0 && PAXInput.getText().toString().trim().length()!=0){
                    AMTInput.setError("Please key in an amount!");

                }else if(AMTInput.getText().toString().trim().length()!=0 && PAXInput.getText().toString().trim().length()==0){
                    PAXInput.setError("Please key in No Of Pax!");
                }
                else{

                    AMTInput.setError("Amount is required!");
                    PAXInput.setError("Number of pax is required!");
                }
            }
        });




    }}

