package com.lmy.recyclerviewtantan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView name;
    private RecyclerView recyclerview;
    private TextView shangyige;
    private TextView xihuan;
    private TextView xiayige;
    private Adapter adapter;
    private ScrollSpeedLinearLayoutManger linearLayoutManager;
    private List<String> nameList = new ArrayList<>();
    private List<String> demoList = new ArrayList<>();
    private int position = 0;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        demoList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590402833720&di=47686d9fbd03033283d439d10692959a&imgtype=0&src=http%3A%2F%2Ft8.baidu.com%2Fit%2Fu%3D3571592872%2C3353494284%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D1200%26h%3D1290");
        demoList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590402833720&di=8d1944ea966852156c37b67f4e46d0d6&imgtype=0&src=http%3A%2F%2Ft8.baidu.com%2Fit%2Fu%3D1484500186%2C1503043093%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D1280%26h%3D853");
        demoList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590402833719&di=38a2beb1d7e8bceda0b9aaabe910b249&imgtype=0&src=http%3A%2F%2Ft9.baidu.com%2Fit%2Fu%3D583874135%2C70653437%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D3607%26h%3D2408");
        demoList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590402833719&di=125ce0afb3a14f32df4cc59efa0d1117&imgtype=0&src=http%3A%2F%2Ft9.baidu.com%2Fit%2Fu%3D1307125826%2C3433407105%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D5760%26h%3D3240");
        demoList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590402833718&di=3377d40346b58362237a0cb6cd6ed075&imgtype=0&src=http%3A%2F%2Ft9.baidu.com%2Fit%2Fu%3D2268908537%2C2815455140%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D1280%26h%3D719");
        demoList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590402833718&di=3ff74cb0128425558ac1aecc9e5353ae&imgtype=0&src=http%3A%2F%2Ft8.baidu.com%2Fit%2Fu%3D581096476%2C2560083681%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D1242%26h%3D1800");
        demoList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590402833717&di=8d9a96bc219be37f071b20c34cdd5c97&imgtype=0&src=http%3A%2F%2Ft9.baidu.com%2Fit%2Fu%3D1761131378%2C1355750940%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D667%26h%3D1000");
        demoList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590402833712&di=dd7099f3bdb5006a3d0028543364e9fb&imgtype=0&src=http%3A%2F%2Ft7.baidu.com%2Fit%2Fu%3D709139973%2C2358713935%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D1280%26h%3D960");
        for (int i = 0; i < 10; i++) {
            nameList.addAll(demoList);
        }
        index = index + 10;
        recyclerview = findViewById(R.id.recyclerview);
        shangyige = findViewById(R.id.shangyige);
        xihuan = findViewById(R.id.xihuan);
        xiayige = findViewById(R.id.xiayige);
        adapter = new Adapter(this, nameList);
//        adapter.setStateRestorationPolicy(PREVENT_WHEN_EMPTY);//恢复recyclerview得状态
        linearLayoutManager = new ScrollSpeedLinearLayoutManger(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerview.setLayoutManager(linearLayoutManager);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerview);
        recyclerview.setAdapter(adapter);
        shangyige.setOnClickListener(this);
        xihuan.setOnClickListener(this);
        xiayige.setOnClickListener(this);
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (recyclerView != null && recyclerView.getChildCount() > 0) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        int currentPosition = ((RecyclerView.LayoutParams) recyclerView.getChildAt(0).getLayoutParams()).getAbsoluteAdapterPosition();
                        Log.e("=====currentPosition", "" + currentPosition);
                        position = currentPosition;
                        name.setText(nameList.get(position));
                        if (currentPosition == nameList.size() - 10) {
                            Log.e("=====currentPosition", "异步加载20条数据");
                            for (int i = index; i < index + 10; i++) {
                                nameList.add(i + "李明");
                            }
                            index = index + 10;
                            adapter.notifyItemInserted(adapter.getItemCount());
                        }
                    }
                    try {
                    } catch (Exception e) {
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shangyige:
                if (position != 0) {
                    position--;
//                    linearLayoutManager.scrollToPositionWithOffset(position, 0);
//                    linearLayoutManager.setStackFromEnd(true);
                    recyclerview.smoothScrollToPosition(position);

                } else {
                    Toast.makeText(this, "已经是第一个", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.xihuan:
                Toast.makeText(this, nameList.get(position), Toast.LENGTH_SHORT).show();
                break;
            case R.id.xiayige:
                if (position != nameList.size() - 1) {
                    position++;
//                    linearLayoutManager.scrollToPositionWithOffset(position, 0);
//                    linearLayoutManager.setStackFromEnd(true);
                    recyclerview.smoothScrollToPosition(position);
                } else {
                    Toast.makeText(this, "已经是最后一个", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}