package com.example.myapplication1;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1.interfaces.ItemClickListener;

import java.util.List;

public class EmployeeAdapterClass extends RecyclerView.Adapter<EmployeeAdapterClass.ViewHolder> {

    DBManager dbManager;
    List<EmployeeModelClass> employee;
    Context context;
    DatabaseHelperClass databaseHelperClass;

    ItemClickListener itemClickListener;

    Dialog myDialog;

    public EmployeeAdapterClass(List<EmployeeModelClass> employee, Context context, ItemClickListener itemClickListener) {
        this.employee = employee;
        this.context = context;
        databaseHelperClass = new DatabaseHelperClass(context);
        this.itemClickListener=itemClickListener;
        dbManager = new DBManager(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_view_record,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        //Popup

        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.dialog_box);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        viewHolder.item_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView dialog_box_username = (TextView) myDialog.findViewById(R.id.dialog_username);
                TextView dialog_box_password = (TextView) myDialog.findViewById(R.id.dialog_password);
                EditText dialog_box_edit_username = (EditText) myDialog.findViewById(R.id.dialog_Username_edit);

                Button dialog_box_update_button = (Button) myDialog.findViewById(R.id.dialog_update);
                Button dialog_box_delete_button = (Button) myDialog.findViewById(R.id.dialog_delete);

                int dialog_box_delete_id = employee.get(viewHolder.getAdapterPosition()).getId();

                dialog_box_username.setText(employee.get(viewHolder.getAdapterPosition()).getUsername());
                dialog_box_password.setText(employee.get(viewHolder.getAdapterPosition()).getPassword());
                dialog_box_edit_username.setText(employee.get(viewHolder.getAdapterPosition()).getUsername());
                myDialog.show();
                dialog_box_delete_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                            Boolean delete_function = dbManager.deleteEmployee(dialog_box_delete_id);
                            if(delete_function==true){
                                myDialog.dismiss();
                                Intent intent = new Intent(context,UserlistActivity.class);
                                context.startActivity(intent);
                            }

                    }
                });

                dialog_box_update_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Boolean update_function = dbManager.update(dialog_box_delete_id, String.valueOf(dialog_box_edit_username.getText()));
                        if (update_function==true){
                            myDialog.dismiss();
                            Intent intent = new Intent(context,UserlistActivity.class);
                            context.startActivity(intent);
                        }
                    }
                });
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final EmployeeModelClass employeeModelClass = employee.get(position);
        /*holder.id.setOnClickListener(view -> {
            itemClickListener.onItemClick(holder.getAdapterPosition());
            Log.e("list","click");
        });
        holder.username.setOnClickListener(view -> {
            itemClickListener.onItemClick(holder.getAdapterPosition());
            Log.e("list","click");
        });
        holder.password.setOnClickListener(view -> {
            itemClickListener.onItemClick(holder.getAdapterPosition());
            Log.e("list","click");
        });*/
        holder.id.setText(Integer.toString(employeeModelClass.getId()));
        holder.username.setText(employeeModelClass.getUsername());
        holder.password.setText(employeeModelClass.getPassword());
    }

    @Override
    public int getItemCount() {
        return employee.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView id,username,password;

        private LinearLayout item_employee;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_employee = (LinearLayout) itemView.findViewById(R.id.employee_item_id);
            id = itemView.findViewById(R.id.text_id);
            username = itemView.findViewById(R.id.text_username);
            password = itemView.findViewById(R.id.text_password);
        }
    }
}
