package com.example.ghost.giaodienmau;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ghost.giaodienmau.model.Money;

import java.util.ArrayList;

/**
 * Created by Ghost on 1/1/2018.
 */
public class Sua extends AppCompatActivity implements View.OnClickListener{
    private Button bchon;
    private Button bluu;
    private EditText lydo,sotien;
    ArrayList<Money> mangMoney;
    ListviewAdapter listviewAdapter;
    SQLite sqLite;
    Dialog dialog;
    int flag = 1;
    public static final String BUNDLE = "bundle";
    public static final String LYDO = "lydo";
    public static final String SOTIEN = "sotien";
    public static final String LUACHON = "luachon";
    String vitri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_add);
        ConnectView();
        Intent in = getIntent ();
        Money money = (Money) in.getSerializableExtra ("money");

//        sqLite = new SQLite (Sua.this);
//        mangMoney = new ArrayList<> ();
//        sqLite.loadData (mangMoney);
//        mangMoney.remove (money);
//        sqLite.saveData (mangMoney);
//        sqLite.loadData (mangMoney);
//        if(in !=null){
//            vitri = in.getStringExtra (Tongquan.VITRI);
//        }
//        int i = Integer.parseInt (vitri);
//        Money money = mangMoney.get (i);
        if(money.getMucdich ()==1){
            bchon.setText ("Thu");
            flag=money.getMucdich ();
        }else {
            bchon.setText ("Chi");
            flag =money.getMucdich ();
        }
        lydo.setText (money.getLydo ());
        sotien.setText (money.getSotien ());
//        mangMoney.remove (i);
    }

    private void ConnectView() {
        bchon = (Button) findViewById (R.id.bchon);
        bluu = (Button) findViewById (R.id.bluu);
        lydo = (EditText) findViewById (R.id.etldo);
        sotien = (EditText) findViewById (R.id.etmoney);

        bchon.setOnClickListener (this);
        bluu.setOnClickListener (this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId ()==R.id.bchon){
            showAlterDialog();
        }else if(v.getId ()==R.id.bluu){
            doclickluu();
        }
    }

    private void doclickluu() {

        if(lydo.getText().toString().equals("") || sotien.getText().toString().equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(Sua.this);
            builder.setTitle("Thông báo");
            builder.setMessage("Các trường không được rỗng");
            builder.setPositiveButton("OK",null);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            return;
        }
        sqLite = new SQLite (Sua.this);
        Money money = new Money (flag,sotien.getText ().toString ()+"",lydo.getText ().toString ()+"");
        mangMoney = new ArrayList<> ();
        sqLite.loadData (mangMoney);
        mangMoney.add (money);
        sqLite.saveData (mangMoney);
        Intent in = new Intent (Sua.this,Tongquan.class);
        startActivity (in);
    }

    private void showAlterDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder (this);
        builder.setTitle ("Chọn ");
        builder.setCancelable (false);
        builder.setPositiveButton ("Thu", new DialogInterface.OnClickListener () {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText (Sua.this,"Bạn chọn Thu",Toast.LENGTH_SHORT).show ();
                bchon.setText ("Thu");
                flag =1;
            }
        });
        builder.setNegativeButton ("Chi", new DialogInterface.OnClickListener () {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText (Sua.this,"Bạn chọn Chi",Toast.LENGTH_SHORT).show ();
                bchon.setText ("Chi");
                flag = 0;
            }
        });
        AlertDialog alertDialog = builder.create ();
        alertDialog.show ();
    }

}
