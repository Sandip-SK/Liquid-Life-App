package com.sandip.liquidlifev1beta;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProgrammingAdapter extends RecyclerView.Adapter<ProgrammingAdapter.ProgrammingViewHolder> {

    private String []data;
    private String []phone;
    private String []blood;

    public ProgrammingAdapter(String []data,String []phone, String []blood){
        this.data=data;
        this.phone=phone;
        this.blood=blood;
    }
    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_layout, parent, false);

        return new ProgrammingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammingViewHolder holder, int position) {
        String title = data[position];
        String phoneNo = phone[position];
        String bloodType = blood[position];
        System.out.println("bloodg type"+bloodType);
        holder.txtTitle.setText(title);
        holder.txtPhone.setText(phoneNo);

        switch (bloodType){
            case "A+":
                holder.imgIcon.setImageResource(R.drawable.ap);
                break;
            case "A-":
                holder.imgIcon.setImageResource(R.drawable.an);
                break;
            case "B+":
                holder.imgIcon.setImageResource(R.drawable.bp);
                break;
            case "B-":
                holder.imgIcon.setImageResource(R.drawable.bn);
                break;
            case "AB+":
                holder.imgIcon.setImageResource(R.drawable.abp);
                break;
            case "AB-":
                holder.imgIcon.setImageResource(R.drawable.abp);
                break;
            case "O+":
                holder.imgIcon.setImageResource(R.drawable.op);
                break;
            case "O-":
                holder.imgIcon.setImageResource(R.drawable.on);
                break;
        }
//        if (bloodType=="A+"){
//            holder.imgIcon.setImageResource(R.drawable.ap);
//        }
//        else if(bloodType=="A-"){
//            holder.imgIcon.setImageResource(R.drawable.an);
//        }
//        else if(bloodType=="B+"){
//            holder.imgIcon.setImageResource(R.drawable.bp);
//        }
//        else if (bloodType=="B-"){
//            holder.imgIcon.setImageResource(R.drawable.bn);
//        }
//        else if (bloodType=="AB+"){
//            holder.imgIcon.setImageResource(R.drawable.abp);
//        }
//        else{
//            holder.imgIcon.setImageResource(R.drawable.op);
//        }
    }
    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ProgrammingViewHolder extends RecyclerView.ViewHolder{
        ImageView imgIcon;
        TextView txtTitle;
        TextView txtPhone;
        public ProgrammingViewHolder(View itemView){
            super(itemView);
            imgIcon = (ImageView) itemView.findViewById(R.id.imgIcon);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtPhone = (TextView) itemView.findViewById(R.id.txtPhone);
        }
    }
}


