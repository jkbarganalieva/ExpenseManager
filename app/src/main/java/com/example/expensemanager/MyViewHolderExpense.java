package com.example.expensemanager;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderExpense extends RecyclerView.ViewHolder {

    View mView;
    public MyViewHolderExpense(@NonNull View itemView) {
        super(itemView);

        mView=itemView;

    }

    public void setDate(String date){
        TextView mDate=mView.findViewById(R.id.date_txt_expense);
        mDate.setText(date);
    }
    public void setType(String type){
        TextView mType=mView.findViewById(R.id.type_txt_expense);
        mType.setText(type);
    }
    public void setNote(String note){
        TextView mNote=mView.findViewById(R.id.note_txt_expense);
        mNote.setText(note);
    }
    public void setAmount(String amount){
        TextView mAmount=mView.findViewById(R.id.amount_txt_expense);

        String stAmount=String.valueOf(amount);
        mAmount.setText(stAmount);
    }

}
