package pm.nestificationbetweenscrollviewandabslistview;

import pm.nestificationbetweenscrollviewandabslistview.adapters.AdapterForListView2;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ActSolution2 extends Activity {
	
	private ListView lv;
	private AdapterForListView2 adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_solution_2);
		
		lv = (ListView) findViewById(R.id.act_solution_2_lv);
		adapter = new AdapterForListView2(this);
		lv.setAdapter(adapter);
		
	}
	
}
