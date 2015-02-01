package com.wind.activity.listviewadapter;

import java.util.List;
import java.util.Map;

import com.wind.activity.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CommentAdapter extends BaseAdapter {

	private Context context;
	/**
	 * 显示数据的集合
	 */
	private List<Map<String, Object>> strings;
	/**
	 * 每层楼的背景色，此数组的大小必须与显示数据集合的大小相同
	 */
	private int[] color = null;
	/**
	 * 每层楼之间的间距
	 */
	private int pad = 2;

	public CommentAdapter(Context context, List<Map<String, Object>> strings,
			int[] color) {
		this.context = context;
		this.strings = strings;
		this.color = color;
	}

	@Override
	public int getCount() {
		return strings != null ? strings.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		return strings.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.main_item, null);
			holder.layout = (LinearLayout) convertView
					.findViewById(R.id.main_linearLayout);
			holder.textView = (TextView) convertView
					.findViewById(R.id.main_textView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// 显示最后一个用户的评论内容
		holder.textView.setText(strings.get(position).get("lastComment")
				.toString());
		// 获得用户名的字符串数组和用户评论内容的字符串数组
		String[] strs = (String[]) strings.get(position).get("name");
		String[] strs1 = (String[]) strings.get(position).get("comment");
		// 开始盖楼
		holder.layout.addView(add(context, (strs.length - 1), pad, strs, strs1,
				color));
		return convertView;
	}

	/**
	 * 递归加载楼层的方法
	 * 
	 * @param context上下文的对像
	 * @param 递归的控制参数
	 *            ，同时也是取用户评论信息和背景色的下标，引参数的大小必须是从集合中获得的用户名数组或从集合中获得的评论内容数据的大小减一
	 * @param pad
	 *            楼层的间距
	 * @param strs
	 *            用户名的字符串数组
	 * @param strs1
	 *            用户相应评论内容的字符串数组
	 * @param color
	 *            背景色的数组
	 * @return 返回一个楼层的LinearLayout布局对象
	 */
	private LinearLayout add(Context context, int i, int pad, String[] strs,
			String[] strs1, int[] color) {
		// 加载一个布局
		LinearLayout layout1 = (LinearLayout) LayoutInflater.from(context)
				.inflate(R.layout.add, null);
		// 获得显示用户名、楼层数、用户评论内容的TextView
		TextView name = (TextView) layout1.findViewById(R.id.add_textView01);
		TextView page = (TextView) layout1.findViewById(R.id.add_textView02);
		TextView comment = (TextView) layout1.findViewById(R.id.add_textView03);
		// 设置显示用户名、楼层数、用户评论内容TextView的内容
		name.setText(strs[i]);
		page.setText((i + 1) + "");
		comment.setText(strs1[i]);
		// 动态生成一个LinearLayout来装载获得的布局
		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setBackgroundColor(color[i]);
		layout.setPadding(pad, pad, pad, pad);
		// 当i的值为零时，递归结束
		if (i != 0) {
			layout.addView(add(context, --i, pad, strs, strs1, color));
		}
		layout.addView(layout1);
		return layout;
	}

	private class ViewHolder {
		/**
		 * 加载楼层的LinearLayout布局
		 */
		public LinearLayout layout;
		/**
		 * 显示最后一个用户评论的TextView
		 */
		public TextView textView;
	}

}
