package lzh.dongruan.com.news.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseBaseAdapter<DataType> extends BaseAdapter {
	protected Context context;
	protected LayoutInflater layoutInflater;

	private List<DataType> dataList;

	public BaseBaseAdapter(Context context) {
		this.context = context;
		dataList = new ArrayList<DataType>();
		layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}


	public List<DataType> getDataList() {
		return dataList;
	}


	public void addDataToAdapter(DataType data) {
		dataList.add(data);
	}

	public void addDataToAdapterHead(List<DataType> datas) {
		dataList.addAll(0, datas);
	}
	
	public void addDataToAdapterEnd(List<DataType> datas) {
		dataList.addAll(dataList.size(), datas);
	}

	public void setDataToAdapter(DataType data) {
		dataList.clear();
		dataList.add(data);
	}


	public void setDataToAdapter(List<DataType> datas) {
		dataList.clear();
		dataList.addAll(datas);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dataList == null ? 0 : dataList.size();
	}

	@Override
	public DataType getItem(int position) {
		// TODO Auto-generated method stub
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		return getItemView(position, view, parent);
	}


	public abstract View getItemView(int position, View view, ViewGroup parent);
}
