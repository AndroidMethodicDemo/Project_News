package pm.nestificationbetweenscrollviewandabslistview;

import pm.nestificationbetweenscrollviewandabslistview.adapters.AdapterForListView;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

public class ActSolution1 extends Activity {
	
	private ScrollView sv;
	private ListView lv;
	private AdapterForListView adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_solution_1);
		
		lv = (ListView) findViewById(R.id.act_solution_1_lv);
		adapter = new AdapterForListView(this);
		lv.setAdapter(adapter);
		setListViewHeightBasedOnChildren(lv);
		
		sv = (ScrollView) findViewById(R.id.act_solution_1_sv);
		sv.smoothScrollTo(0, 0);
	}
	
	
	/**
	 * 动态设置ListView的高度
	 * @param listView
	 */
	public static void setListViewHeightBasedOnChildren(ListView listView) {  
		if(listView == null) return;
		
        ListAdapter listAdapter = listView.getAdapter();   
        if (listAdapter == null) {  
            // pre-condition  
            return;  
        }  
  
        int totalHeight = 0;  
        for (int i = 0; i < listAdapter.getCount(); i++) {  
            View listItem = listAdapter.getView(i, null, listView);  
            listItem.measure(0, 0);  
            totalHeight += listItem.getMeasuredHeight();  
        }  
  
        ViewGroup.LayoutParams params = listView.getLayoutParams();  
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));  
        listView.setLayoutParams(params);  
    }  
}
