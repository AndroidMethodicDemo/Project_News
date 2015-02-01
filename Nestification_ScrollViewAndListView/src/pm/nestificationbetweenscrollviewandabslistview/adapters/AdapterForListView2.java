package pm.nestificationbetweenscrollviewandabslistview.adapters;

import pm.nestificationbetweenscrollviewandabslistview.R;
import pm.nestificationbetweenscrollviewandabslistview.adapters.AdapterForListView.ViewHolder;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterForListView2 extends BaseAdapter {

    private LayoutInflater inflater;

    public AdapterForListView2(Context context){
    	this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 22;
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
    	//列表第一项
    	if(position == 0){
    		convertView = inflater.inflate(R.layout.item_solution2_top, null);
    		return convertView;
    	}
    	//列表最后一项
    	else if(position == 21){
    		convertView = inflater.inflate(R.layout.item_solution2_bottom, null);
    		return convertView;
    	}
    	
    	//普通列表项
    	ViewHolder h = null;
    	if(convertView == null || convertView.getTag() == null){
    		convertView = inflater.inflate(R.layout.item_listview_data, null);
    		h = new ViewHolder();
    		h.tv = (TextView) convertView.findViewById(R.id.item_listview_data_tv);
    		convertView.setTag(h);
    	}else{
    		h = (ViewHolder) convertView.getTag();
    	}
    	
    	h.tv.setText("第"+ position + "条数据");

        return convertView;
    }
    
    static class ViewHolder{
    	TextView tv;
    }
}
