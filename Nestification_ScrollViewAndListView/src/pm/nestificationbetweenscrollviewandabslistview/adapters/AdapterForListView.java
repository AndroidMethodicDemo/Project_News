package pm.nestificationbetweenscrollviewandabslistview.adapters;

import pm.nestificationbetweenscrollviewandabslistview.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterForListView extends BaseAdapter {

    private LayoutInflater inflater;

    public AdapterForListView(Context context){
    	this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	ViewHolder h = null;
    	if(convertView == null){
    		convertView = inflater.inflate(R.layout.item_listview_data, null);
    		h = new ViewHolder();
    		h.tv = (TextView) convertView.findViewById(R.id.item_listview_data_tv);
    		convertView.setTag(h);
    	}else{
    		h = (ViewHolder) convertView.getTag();
    	}
    	
    	h.tv.setText("第"+ (position+1) + "条数据");

        return convertView;
    }
    
    static class ViewHolder{
    	TextView tv;
    }
}
