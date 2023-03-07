package net.jordan.quran_club.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import net.jordan.quran_club.R;
import net.jordan.quran_club.model.studentQuranPart.StudentQuranPart;
import net.jordan.quran_club.ui.teacher.crud.quranPart.InsertAndDeleteQuranPartFragment;

import java.util.ArrayList;

public class ListStudentQuranPartAdapter extends BaseAdapter {

   private Context context;
   private ArrayList<StudentQuranPart>studentQuranParts;
   private int resource;
   private ListListener listListener;
   private InsertAndDeleteQuranPartFragment insertAndDeleteQuranPartFragment;

    public ListStudentQuranPartAdapter(Context context, int resource, ArrayList<StudentQuranPart> studentQuranParts,ListListener listListener){
        this.context=context;
        this.resource=resource;
        this.studentQuranParts=studentQuranParts;
       // this.insertAndDeleteQuranPartFragment=insertAndDeleteQuranPartFragment;
        this.listListener=listListener;

    }

    public void AddAll(ArrayList<StudentQuranPart> studentQuranParts){

        this.studentQuranParts.clear();
        this.studentQuranParts.addAll(studentQuranParts);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.studentQuranParts.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         View view=convertView;
        if(convertView==null) {
         view = LayoutInflater.from(context).inflate(resource, null, false);
        }

       TextView textViewQuranPartId = view.findViewById(R.id.textViewListStQp);
       Button  delete=view.findViewById(R.id.delete);


       textViewQuranPartId.setText(studentQuranParts.get(position).getQuranPartId()+"");
       delete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            listListener.onClickDeleteButton(studentQuranParts.get(position).getStudentQuranPartId());
           }
       });
        return view;


    }


  public interface ListListener{
       void onClickDeleteButton(int studentQuranPartId);

    }

}
