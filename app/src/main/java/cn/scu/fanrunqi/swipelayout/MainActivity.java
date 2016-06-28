package cn.scu.fanrunqi.swipelayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @InjectView(R.id.bt_listview)
    Button btListview;
    @InjectView(R.id.bt_expandablelistview)
    Button btExpandablelistview;
    @InjectView(R.id.bt_gridview)
    Button btGridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }


    @OnClick({R.id.bt_listview, R.id.bt_expandablelistview, R.id.bt_gridview})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_listview:
                startActivity(new Intent(this,ListViewActivity.class));
                break;
            case R.id.bt_expandablelistview:
                startActivity(new Intent(this,ExpandableListViewActivity.class));
                break;
            case R.id.bt_gridview:
                startActivity(new Intent(this,GridViewActivity.class));
                break;
        }
    }
}
