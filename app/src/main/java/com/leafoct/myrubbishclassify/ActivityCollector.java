package com.leafoct.myrubbishclassify;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {
    private static List<Activity> activities=new ArrayList<>();
    public static void addActivity(Activity a){
        activities.add(a);
    }
    public static void removeActivity(Activity a){
        activities.remove(a);
    }
    public static void finishAll(){
        for(Activity a:activities){
            if(!a.isFinishing()){
                a.finish();
            }
        }
    }
}
