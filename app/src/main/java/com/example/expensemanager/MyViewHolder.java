package com.example.expensemanager;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
    }

    public void setType(String type) {
        TextView mType = mView.findViewById(R.id.type_txt_income);
        mType.setText(type);
    }

    public void setNote(String note) {
        TextView mNote = mView.findViewById(R.id.note_txt_income);
        mNote.setText(note);
    }

    public void setDate(String date) {
        TextView mDate = mView.findViewById(R.id.date_txt_income);
        mDate.setText(date);
    }

    public void setAmount(int amount) {
        TextView mAmount = mView.findViewById(R.id.amount_txt_income);
        String strAmount = String.valueOf(amount);
        mAmount.setText(strAmount);
    }
}
