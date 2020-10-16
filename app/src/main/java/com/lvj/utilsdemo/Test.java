package com.lvj.utilsdemo;

import android.media.browse.MediaBrowser;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class Test {


    public static final String html_demo = "某饲料厂原有旧粮库存Y袋，现购进X袋新粮后，将粮食总库存的" +
            "<img src=\"http://upload.xiaomaigongkao.com//ueditor/20170823/599d15851b11f.png\"" +
            " title=\"小麦公考\" alt=\"小麦公考\" width=\"500\" height=\"500\" border=\"0\" vspace=\"0\" " +
            "style=\"width: 10px; height: 43px;\"/>精加工为饲料。被精加工为饲料的新粮最多为A1袋，最少为A2袋。如所有旧粮" +
            "、新粮每袋重量相同，则以下哪个坐标图最能准确描述A1、A2分别与X的关系：" +
            "<img src=\"http://upload.xiaomaigongkao.com//ueditor/20170823/599d15851b11f.png\" " +
            "title=\"xiaomai\" alt=\"xiaomai\" width=\"900\" height=\"600\" border=\"0\" vspace=\"0\" " +
            "style=\"width: 507px; height: 318px;\"/>";


    private void dotest() {
        ItemTouchHelper.Callback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.DOWN) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            }
        };
    }

}
