package cn.scu.fanrunqi.swipelayout;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.fanrunqi.swipelayoutlibrary.SwipeLayout;

public class ExpandableListViewActivity extends AppCompatActivity {

    @InjectView(R.id.expandablelistview)
    ExpandableListView expandablelistview;
    List<String> group;           //组列表
    List<List<String>> child;     //子列表
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view);
        ButterKnife.inject(this);
        InitData();
        adapter = new MyAdapter(this);
        expandablelistview.setAdapter(adapter);
    }

    private void InitData() {
        group = new ArrayList<String>();
        child = new ArrayList<List<String>>();
        addInfo("1", new String[]{"11", "12", "13","14","15","16","17","18"});
        addInfo("2", new String[]{"21", "22", "23"});
        addInfo("3", new String[]{"31", "32", "33"});
        addInfo("4", new String[]{"41", "42", "43"});
        addInfo("5", new String[]{"51", "52", "53"});
    }

    /**
     * 给组、子列表添加数据
     */
    private void addInfo(String g, String[] c) {
        group.add(g);
        List<String> childitem = new ArrayList<String>();
        for (int i = 0; i < c.length; i++) {
            childitem.add(c[i]);
        }
        child.add(childitem);
    }

    private class MyAdapter extends BaseExpandableListAdapter {

        private Context context;
        private LayoutInflater mInflater;

        public MyAdapter(Context context) {
            super();
            this.context = context;
            this.mInflater = LayoutInflater.from(context);
        }

        //-----------------Child----------------//
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return child.get(groupPosition).get(childPosition);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return child.get(groupPosition).size();
        }

        @Override
        public View getChildView(final int groupPosition,final int  childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
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

            holder.tvName.setText(child.get(groupPosition).get(childPosition));
            holder.llDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    child.get(groupPosition).remove(childPosition);
                    notifyDataSetChanged();

                    /**
                     * 删除swipelayout
                     */
                    SwipeLayout.removeSwipeView(holder.swipelayout);

                }
            });
            return convertView;
        }
        //----------------Group----------------//
        @Override
        public Object getGroup(int groupPosition) {
            return group.get(groupPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public int getGroupCount() {
            return group.size();
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            convertView = mInflater.inflate(R.layout.expandable_group_item, null);
            TextView tv = (TextView)convertView.findViewById(R.id.tv_group);
            tv.setText(group.get(groupPosition));
            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            // TODO Auto-generated method stub
            return true;
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
