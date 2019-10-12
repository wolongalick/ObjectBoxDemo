package com.alick.objectboxdemo.utils;

import android.support.v7.widget.RecyclerView;

import java.util.List;


public class RecyclerUtils {
    /**
     * 插入数据时刷新
     * @param adapter
     * @param list
     * @param model
     * @param insertPosition
     * @param <Model>
     */
    public static <Model> void insertRefresh(RecyclerView.Adapter adapter, List<Model> list, Model model, int insertPosition){
        list.add(insertPosition,model);
        adapter.notifyItemInserted(insertPosition);
        adapter.notifyItemRangeChanged(insertPosition, list.size()+insertPosition);
    }

    /**
     * 移除数据时刷新
     * @param adapter
     * @param list
     * @param removePosition
     * @param <Model>
     */
    public static <Model> void removeRefresh(RecyclerView.Adapter adapter, List<Model> list, int removePosition){
        adapter.notifyItemRemoved(removePosition);
        list.remove(removePosition);
        adapter.notifyItemRangeChanged(removePosition,list.size()-removePosition);
    }
    public static <Model> void removeRefresh(RecyclerView.Adapter adapter, List<Model> list, List<Integer> removePositions){

        int i=0;
        for (int removePosition:removePositions) {
            adapter.notifyItemRemoved(removePosition+i);
            list.remove(removePosition+i);
            i--;
        }

        adapter.notifyItemRangeChanged(removePositions.get(0),list.size()-removePositions.get(0));
    }



}
