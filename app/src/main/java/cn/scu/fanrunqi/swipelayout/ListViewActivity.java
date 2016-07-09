package cn.scu.fanrunqi.swipelayout;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.fanrunqi.swipelayoutlibrary.SwipeLayout;

public class ListViewActivity extends AppCompatActivity {

    List<String> nums;
    MyAdapter adapter;
    @InjectView(R.id.listview)
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ButterKnife.inject(this);
        InitData();
    }

    private void InitData() {
        nums = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            nums.add(i + "");
        }
        adapter = new MyAdapter(this);
        listview.setAdapter(adapter);
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
            return nums.size();
        }

        @Override
        public Object getItem(int position) {
            return nums.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.swipelayout_item, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);

                /**
                 * 添加swipelayout
                 */
                SwipeLayout.addSwipeView(holder.swipelayout);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tvName.setText(nums.get(position));
            holder.llDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nums.remove(position);
                    notifyDataSetChanged();

                    /**
                     * 删除swipelayout
                     */
                    SwipeLayout.removeSwipeView(holder.swipelayout);

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
}
