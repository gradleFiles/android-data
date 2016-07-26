package proveb.gk.com.foresightsqlite.Adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.view.LayoutInflater;

import java.util.ArrayList;

import proveb.gk.com.foresightsqlite.R;
import proveb.gk.com.foresightsqlite.model.Jsonmodel;

/**
 * Created by Nehru on 25-07-2016.
 */
public class CustomAdapter extends BaseAdapter {
     private Activity activity;
    private ArrayList<Jsonmodel> jsonmodelArrayList;
    private LayoutInflater inflater;

    public CustomAdapter(Activity activity, ArrayList<Jsonmodel> jsonmodelArrayList) {
        this.activity = activity;
        this.jsonmodelArrayList = jsonmodelArrayList;
        inflater=LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return jsonmodelArrayList.size() ;
    }

    @Override
    public Object getItem(int position) {
        return jsonmodelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder=null;
        View view=convertView;
        if(view==null){
            view=inflater.inflate(R.layout.jsonlist,parent,false);
            myViewHolder=new MyViewHolder();
            myViewHolder.degree=(TextView)view.findViewById(R.id.tv_degree);
            myViewHolder.specialty=(TextView)view.findViewById(R.id.tv_specialty);
            view.setTag(myViewHolder);
        }else{
            myViewHolder=(MyViewHolder)view.getTag();
        }
        myViewHolder.degree.setText(jsonmodelArrayList.get(position).getDegree());
        myViewHolder.specialty.setText(jsonmodelArrayList.get(position).getSpecialty());
        return view;
    }

    private class MyViewHolder {
        private TextView degree,specialty;
    }
}
