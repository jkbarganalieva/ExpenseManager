package com.example.expensemanager;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.icu.text.DateFormat;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expensemanager.model.Data;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class DashBoardFragment extends Fragment {


    private FloatingActionButton fab_main_btn;
    private FloatingActionButton fab_income_btn;
    private FloatingActionButton fab_expense_btn;

    private TextView fab_income_tv;
    private TextView fab_expense_tv;

    private boolean isOpen = false;

    private Animation FadOpen, FadClose;


    private FirebaseAuth mAuth;
    private DatabaseReference mIncomeDatabase;
    private DatabaseReference mExpenseDatabase;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_dash_board, container, false);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        String uid = mUser.getUid();

        mIncomeDatabase = FirebaseDatabase.getInstance().getReference().child("IncomeData").child(uid);
        mExpenseDatabase = FirebaseDatabase.getInstance().getReference().child("ExpenseDatabase").child(uid);

        fab_main_btn = myView.findViewById(R.id.fb_main_plus_btn);
        fab_income_btn = myView.findViewById(R.id.income_ft_btn);
        fab_expense_btn = myView.findViewById(R.id.expense_ft_btn);

        fab_income_tv = myView.findViewById(R.id.income_ft_text);
        fab_expense_tv = myView.findViewById(R.id.expense_ft_text);

        FadOpen = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_open);
        FadClose = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_close);

        fab_main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addData();

                if (isOpen) {
                    fab_income_btn.startAnimation(FadClose);
                    fab_expense_btn.startAnimation(FadClose);
                    fab_income_btn.setClickable(false);
                    fab_expense_btn.setClickable(false);

                    fab_income_tv.startAnimation(FadClose);
                    fab_expense_tv.startAnimation(FadClose);
                    fab_income_tv.setClickable(false);
                    fab_expense_tv.setClickable(false);
                    isOpen = false;
                } else {
                    fab_income_btn.startAnimation(FadOpen);
                    fab_expense_btn.startAnimation(FadOpen);
                    fab_income_btn.setClickable(true);
                    fab_expense_btn.setClickable(true);

                    fab_income_tv.startAnimation(FadOpen);
                    fab_expense_tv.startAnimation(FadOpen);
                    fab_income_tv.setClickable(true);
                    fab_expense_tv.setClickable(true);
                    isOpen = true;
                }

            }
        });

        return myView;
    }

    private void ftAnimation(){

        if (isOpen) {
            fab_income_btn.startAnimation(FadClose);
            fab_expense_btn.startAnimation(FadClose);
            fab_income_btn.setClickable(false);
            fab_expense_btn.setClickable(false);

            fab_income_tv.startAnimation(FadClose);
            fab_expense_tv.startAnimation(FadClose);
            fab_income_tv.setClickable(false);
            fab_expense_tv.setClickable(false);
            isOpen = false;
        } else {
            fab_income_btn.startAnimation(FadOpen);
            fab_expense_btn.startAnimation(FadOpen);
            fab_income_btn.setClickable(true);
            fab_expense_btn.setClickable(true);

            fab_income_tv.startAnimation(FadOpen);
            fab_expense_tv.startAnimation(FadOpen);
            fab_income_tv.setClickable(true);
            fab_expense_tv.setClickable(true);
            isOpen = true;
        }

    }
    private void addData() {

        fab_income_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                incomeDataInsert();

            }
        });

        fab_expense_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expenseDataInsert();
            }
        });

    }

    public void incomeDataInsert() {

        AlertDialog.Builder meDialog = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = LayoutInflater.from(getActivity());

        View myviewm = inflater.inflate(R.layout.custom_dialog_for_insertdata, null);
        meDialog.setView(myviewm);
        AlertDialog dialog = meDialog.create();

        dialog.setCancelable(false);

        EditText edtAmmount = myviewm.findViewById(R.id.amount_edt);
        EditText edtType = myviewm.findViewById(R.id.type_edt);
        EditText edtNote = myviewm.findViewById(R.id.note_edt);

        Button btnSave = myviewm.findViewById(R.id.btn_save);
        Button btnCancel = myviewm.findViewById(R.id.btn_cancel);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = edtType.getText().toString().trim();
                String ammount = edtAmmount.getText().toString().trim();
                String note = edtNote.getText().toString().trim();

                if (TextUtils.isEmpty(type)) {
                    edtType.setError("Required Field..  ");
                    return;
                }
                if (TextUtils.isEmpty(ammount)) {
                    edtAmmount.setError("Required Field..  ");
                    return;
                }

                int ourammountint = Integer.parseInt(ammount);

                if (TextUtils.isEmpty(note)) {
                    edtNote.setError("Required Field..  ");
                    return;
                }
                String id = mIncomeDatabase.push().getKey();
                String mDate = DateFormat.getDateInstance().format(new Date());
                Data data = new Data(ourammountint, type, note, id, mDate);
                mIncomeDatabase.child(id).setValue(data);
                Toast.makeText(getActivity(), "DATA ADDED", Toast.LENGTH_SHORT).show();
                ftAnimation();
                dialog.dismiss();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ftAnimation();
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    public void expenseDataInsert() {

        AlertDialog.Builder mydialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = LayoutInflater.from(getActivity());

        View myview = inflater.inflate(R.layout.custom_dialog_for_insertdata, null);
        mydialog.setView(myview);

        final AlertDialog dialog = mydialog.create();
        dialog.setCancelable(false);

        EditText ammount = myview.findViewById(R.id.amount_edt);
        EditText type = myview.findViewById(R.id.type_edt);
        EditText note = myview.findViewById(R.id.note_edt);

        Button btnSave = myview.findViewById(R.id.btn_save);
        Button btnCancel = myview.findViewById(R.id.btn_cancel);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tmAmount = ammount.getText().toString().trim();
                String tmType = type.getText().toString().trim();
                String tmNote = note.getText().toString().trim();

                if (TextUtils.isEmpty(tmAmount)) {
                    ammount.setError("Required Field");
                    return;
                }

                int inamount=Integer.parseInt(tmAmount);

                if (TextUtils.isEmpty(tmType)) {
                    type.setError("Required Field");
                    return;
                }
                if (TextUtils.isEmpty(tmNote)) {
                    note.setError("Required Field");
                    return;
                }

                String id=mExpenseDatabase.push().getKey();
                String mDate=DateFormat.getTimeInstance().format(new Date());

                Data data=new Data(inamount,tmType,tmNote,id,mDate);
                mExpenseDatabase.child(id).setValue(data);

                Toast.makeText(getActivity(),"Data added",Toast.LENGTH_SHORT).show();

                ftAnimation();
                dialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ftAnimation();
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}
