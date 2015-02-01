package pm.nestificationbetweenscrollviewandabslistview;

import pm.nestificationbetweenscrollviewandabslistview.adapters.AdapterForListView;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.ScrollView;

public class ActSolution5 extends Activity {
	
	private ScrollView sv;
	private ListView lv;
	private AdapterForListView adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_solution_5);
		
		sv = (ScrollView) findViewById(R.id.act_solution_5_sv);
		
		
		lv = (ListView) findViewById(R.id.act_solution_5_lv);
		adapter = new AdapterForListView(this);
		lv.setAdapter(adapter);
		
	}
	
}
