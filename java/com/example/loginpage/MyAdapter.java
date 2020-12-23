package com.example.loginpage;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    String data1[],data2[], website[];
    int images[];
    Context context;
    public MyAdapter(Context ct, String s1[], String s2[], String s3[], int img[]){
        context = ct;
        data1 = s1;
        data2 = s2;
        website = s3;
        images = img;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
     holder.programming_languages.setText(data1[position]);
     holder.description.setText(data2[position]);
     holder.myImage.setImageResource(images[position]);
     holder.visit.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(Intent.ACTION_VIEW);
             intent.setData(Uri.parse(website[holder.getAdapterPosition()]));
             context.startActivity(intent);
         }
     }) ;

    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public static class MyViewHolder  extends RecyclerView.ViewHolder{
        TextView programming_languages, description;
        ImageView myImage;
        Button visit;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            programming_languages = itemView.findViewById(R.id.programming_languages);
            description = itemView.findViewById(R.id.description);
            myImage = itemView.findViewById(R.id.myImage);
            visit = itemView.findViewById(R.id.visit);
        }
    }
}
