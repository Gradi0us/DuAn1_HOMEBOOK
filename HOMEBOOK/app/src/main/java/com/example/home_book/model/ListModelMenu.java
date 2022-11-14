package com.example.home_book.model;

public class ListModelMenu {
    public String tvMenu;
    public int imgMenu;

    public ListModelMenu() {
    }

    public ListModelMenu(int imgMenu, String tvMenu) {
        this.imgMenu = imgMenu;
        this.tvMenu = tvMenu;
    }

    public int getImgMenu() {
        return imgMenu;
    }

    public void setImgMenu(int imgMenu) {
        this.imgMenu = imgMenu;
    }

    public String getTvMenu() {
        return tvMenu;
    }

    public void setTvMenu(String tvMenu) {
        this.tvMenu = tvMenu;
    }
}
