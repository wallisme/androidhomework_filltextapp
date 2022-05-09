package com.example.filltextapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private EditText mssv ;
    private EditText name ;
    private EditText cccd ;
    private EditText email ;
    private EditText  dob;
    private EditText quequan;
    private CheckBox checkAcpt ;
    private Button btn ;
    private RadioGroup radioGr ;

    public String str = "" ;
    public Boolean validate= true ;

    DatePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mssv = (EditText) findViewById(R.id.mssv) ;
        name = (EditText) findViewById(R.id.name) ;
        cccd = (EditText) findViewById(R.id.cccd) ;
        email = (EditText) findViewById(R.id.email) ;
        dob = (EditText) findViewById(R.id.editText1) ;
        quequan = (EditText) findViewById(R.id.editText) ;
        checkAcpt = (CheckBox) findViewById(R.id.checkBox) ;
        btn = (Button)  findViewById(R.id.button) ;
        radioGr = (RadioGroup) findViewById(R.id.radioGroup) ;

        dob.setInputType(InputType.TYPE_NULL);

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        btn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str = "" ;
                validate = true ;

                int selectedId = radioGr.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                if(radioButton.isChecked()){
                    str+= "\n -Nghành học:"+ radioButton.getText() ;
                }

                if(!checkAcpt.isChecked()){
                    Toast.makeText(MainActivity.this, "You dont accept the permission" ,Toast.LENGTH_LONG).show();
                    return ;
                }

                checkNull(mssv,"MSSV" );
                checkNull(name,"Name" );
                checkNull(cccd,"CCCD" );
                checkNull(email,"Email" );
                checkNull(dob,"Ngày tháng năm sinh" );
                checkNull(quequan,"Quê quán" );

                if(validate && checkAcpt.isChecked()){
                    Toast toast =  Toast.makeText(MainActivity.this, str ,Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 20, 30);
                    toast.show();

                }
            }
        }));

    }

    public void checkNull(EditText text,String name){
        String value = text.getText().toString().trim() ;
        if(value.equals("")){
            text.setError("Field "+ name+ " is required");
            text.setHint("Please enter "+ name);
            validate= false ;
        }else{
            str+= "\n  -"+ name + ": " +  value ;
        }
    }
}