package pm.nestificationbetweenscrollviewandabslistview;

import pm.nestificationbetweenscrollviewandabslistview.adapters.AdapterForListView;
import pm.nestificationbetweenscrollviewandabslistview.mywidgets.LinearLayoutForListView;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ActSolution3 extends Activity {
	
	private LinearLayoutForListView mylinearlayout;
	private AdapterForListView adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_solution_3);
		
		mylinearlayout = (LinearLayoutForListView) findViewById(R.id.act_solution_3_mylinearlayout);
		adapter = new AdapterForListView(this);
		mylinearlayout.setAdapter(adapter);
		
	}
	
}
