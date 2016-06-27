package cn.scu.fanrunqi.swipelayout;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.listview)
    ListView listview;
    List<String> names;
    MyAdapter adapter;
    List<SwipeLayout> swipelayouts = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        InitData();

    }
    private class MyAdapter extends BaseAdapter {
        private Context context;
        private LayoutInflater mInflater;

        public MyAdapter(Context context) {
            super();
            this.context = context;
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return names.size();
        }

        @Override
        public Object getItem(int position) {
            return names.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.listview_item, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
                swipelayouts.add(holder.swipelayout);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tvName.setText(names.get(position));
            holder.llDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    names.remove(position);
                    holder.swipelayout.ScrollShowMenu(SwipeLayout.SHRINK);
                    notifyDataSetChanged();
                }
            });
            holder.swipelayout.setOnSwipeListener(new SwipeLayout.OnSwipeListener() {
                @Override
                public void onHorizontalMoved(SwipeLayout sll, boolean isHorizontal) {
                    if(!isHorizontal){
                        for(SwipeLayout s : swipelayouts){
                            s.ScrollShowMenu(SwipeLayout.SHRINK);
                        }
                    }
                }
            });

            return convertView;
        }
    }
    class ViewHolder {
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.ll_edit)
        LinearLayout llEdit;
        @InjectView(R.id.ll_delete)
        LinearLayout llDelete;
        @InjectView(R.id.swipelayout)
        SwipeLayout swipelayout;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    private void InitData() {
        names = new ArrayList<String>();
        names.add("jack");
        names.add("tom");
        names.add("rose");
        names.add("fanrunqi");
        names.add("jack");
        names.add("tom");
        names.add("rose");
        names.add("fanrunqi");
        names.add("jack");
        names.add("tom");
        names.add("rose");
        names.add("fanrunqi");
        names.add("jack");
        names.add("tom");
        names.add("rose");
        names.add("fanrunqi");

        adapter = new MyAdapter(this);
        listview.setAdapter(adapter);

    }
}
