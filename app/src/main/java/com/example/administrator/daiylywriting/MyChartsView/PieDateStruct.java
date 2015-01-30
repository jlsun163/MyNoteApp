package com.example.administrator.daiylywriting.MyChartsView;

/**
 * Created by Administrator on 2015/1/25.
 */
public class PieDateStruct {
    private String pieDataName,pieDateVaule;
    private Integer pieDateNumber;
    public PieDateStruct(){
    }

    public void setPieDataName(String pieDataName){
        this.pieDataName=pieDataName;
    }
    public void setPieDateVaule(String pieDateVaule){
        this.pieDateVaule=pieDateVaule;
    }

    public void setPieDateNumber(Integer pieDateNumber){
        this.pieDateNumber=pieDateNumber;
    }

    public String getPieDataName(){
        return pieDataName;
    }

    public String getPieDateVaule(){
        return pieDateVaule;
    }

    public Integer getPieDateNumber(){
        return pieDateNumber;
    }
}
