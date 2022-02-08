package com.example.testproject02;

import java.io.Serializable;

public class ListDTO implements Serializable {

    String lv_tvname ,lv_tvmsg;
    int lv_img1 ,lv_img2;

    public ListDTO(String lv_tvname, String lv_tvmsg, int lv_img1, int lv_img2) {
        this.lv_tvname = lv_tvname;
        this.lv_tvmsg = lv_tvmsg;
        this.lv_img1 = lv_img1;
        this.lv_img2 = lv_img2;
    }

    public String getLv_tvname() {
        return lv_tvname;
    }

    public void setLv_tvname(String lv_tvname) {
        this.lv_tvname = lv_tvname;
    }

    public String getLv_tvmsg() {
        return lv_tvmsg;
    }

    public void setLv_tvmsg(String lv_tvmsg) {
        this.lv_tvmsg = lv_tvmsg;
    }

    public int getLv_img1() {
        return lv_img1;
    }

    public void setLv_img1(int lv_img1) {
        this.lv_img1 = lv_img1;
    }

    public int getLv_img2() {
        return lv_img2;
    }

    public void setLv_img2(int lv_img2) {
        this.lv_img2 = lv_img2;
    }
}
