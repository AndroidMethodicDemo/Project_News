package pm.nestificationbetweenscrollviewandabslistview;

import pm.nestificationbetweenscrollviewandabslistview.adapters.AdapterForListView;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView lv;
	private AdapterForListView adapter;
	
	private Button bt_solution1;
	private Button bt_solution2;
	private Button bt_solution3;
	private Button bt_solution4;
	private Button bt_solution5;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lv = (ListView) findViewById(R.id.activity_main_lv);
		adapter = new AdapterForListView(this);
		lv.setAdapter(adapter);

		bt_solution1 = (Button) findViewById(R.id.activity_main_bt_solution1);
		bt_solution2 = (Button) findViewById(R.id.activity_main_bt_solution2);
		bt_solution3 = (Button) findViewById(R.id.activity_main_bt_solution3);
		bt_solution4 = (Button) findViewById(R.id.activity_main_bt_solution4);
		bt_solution5 = (Button) findViewById(R.id.activity_main_bt_solution5);

		bt_solution1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ActSolution1.class);
				startActivity(intent);
			}
		});
		bt_solution2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ActSolution2.class);
				startActivity(intent);
			}
		});
		bt_solution3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ActSolution3.class);
				startActivity(intent);
			}
		});
		bt_solution4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ActSolution4.class);
				startActivity(intent);
			}
		});
		bt_solution5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ActSolution5.class);
				startActivity(intent);
			}
		});
		
	}

}
