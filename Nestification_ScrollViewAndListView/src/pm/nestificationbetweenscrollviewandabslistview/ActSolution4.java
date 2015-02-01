package pm.nestificationbetweenscrollviewandabslistview;

import pm.nestificationbetweenscrollviewandabslistview.adapters.AdapterForListView;
import pm.nestificationbetweenscrollviewandabslistview.mywidgets.ListViewForScrollView;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ScrollView;

public class ActSolution4 extends Activity {
	
	private ScrollView	sv;
	private ListViewForScrollView mylistview;
	private AdapterForListView adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_solution_4);
		
		
		mylistview = (ListViewForScrollView) findViewById(R.id.act_solution_4_mylistview);
		adapter = new AdapterForListView(this);
		mylistview.setAdapter(adapter);
		
		//使ScrollView滑动至最顶端
		sv = (ScrollView) findViewById(R.id.act_solution_4_sv);
		sv.smoothScrollTo(0, 0);
		
	}
	
}
