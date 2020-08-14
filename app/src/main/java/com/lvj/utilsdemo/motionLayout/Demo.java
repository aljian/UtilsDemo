//package com.lvj.utilsdemo.motionLayout;
//
//import android.graphics.Typeface;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.android.material.tabs.TabLayout;
//import com.lvj.utilsdemo.R;
//
//class Demo {
//
//
//    /**
//     * 为了做到TabLayout每个Tab选中之后字体变大变粗
//     */
//    private void initTabView() {
//        holder = null;
//        for (int i = 0; i < datas.size(); i++) {
//            //获取tab
//            TabLayout.Tab tab = mTab.getTabAt(i);
//            //给tab设置自定义布局
//            tab.setCustomView(R.layout.tab_text_sizeitem);
//            holder = new RecyclerView.ViewHolder(tab.getCustomView());
//            //填充数据
//            holder.tab_item_time.setText(String.valueOf(datas.get(i)));
//            //默认选择第一项
//            if (i == 0) {
//                holder.tab_item_time.setSelected(true);
//                holder.tab_item_time.setTextSize(20);
//                holder.tab_item_time.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
//                holder.tab_item_time.setTextColor(getResources().getColor(R.color.home_tab_color1));
//                tv_Name.setText(App.divingActionBeans.get(0).getDivingActionName());
//            }
//        }
//
//        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                holder = new ViewHolder(tab.getCustomView());
//                holder.tab_item_time.setSelected(true);
//                //设置选中后的字体大小
//                holder.tab_item_time.setTextSize(20);
//                //加粗
//                holder.tab_item_time.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
//                holder.tab_item_time.setTextColor(getResources().getColor(R.color.home_tab_color1));
//                if (getActivity().getString(R.string.tournament_category).equals(tab.getText())) {
//                    tv_Name.setText(App.competitionsBeans.get(0).getCompetitionsName());
//                } else if (getActivity().getString(R.string.event).equals(tab.getText())) {
//                    tv_Name.setText(App.tourNamentCategoryBeans.get(0).getCategoryName());
//                } else if (getActivity().getString(R.string.scoring_standard).equals(tab.getText())) {
//                    tv_Name.setText(App.standardScoreBeans.get(0).getStandardName());
//                } else if (getActivity().getString(R.string.technical_term).equals(tab.getText())) {
//                    tv_Name.setText(App.divingTerminologyBeans.get(0).getDescribe());
//                } else if (getActivity().getString(R.string.degree_difficulty).equals(tab.getText())) {
//                    tv_Name.setText(App.divingActionBeans.get(0).getDegreeOfDifficulty() + "");
//                } else if (getActivity().getString(R.string.action_group).equals(tab.getText())) {
//                    tv_Name.setText(App.divingGroupBeans.get(0).getDescribe());
//                } else if (getActivity().getString(R.string.diving_action).equals(tab.getText())) {
//                    tv_Name.setText(App.divingActionBeans.get(0).getDivingActionName());
//                } else if (getActivity().getString(R.string.athletes).equals(tab.getText())) {
//                    tv_Name.setText(App.playerBeans.get(0).getPlayerName());
//                } else if (getActivity().getString(R.string.recommend).equals(tab.getText())) {
//                    tv_Name.setText(App.divingActionBeans.get(0).getDivingActionName());
//                }
//                //关联Viewpager
//                mViewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                holder = new ViewHolder(tab.getCustomView());
//                holder.tab_item_time.setSelected(false);
//                //恢复默认字体大小
//                holder.tab_item_time.setTextSize(17);
//                //常规
//                holder.tab_item_time.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
//                holder.tab_item_time.setTextColor(getResources().getColor(R.color.home_top_color));
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//            }
//        });
//    }
//}
