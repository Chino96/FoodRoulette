package com.example.foodroulette;

public class FoodGenre {
    String GenreName;
    private int mImageID;
    boolean isSelected;

    public FoodGenre(String foodTypeName,int mImageID,boolean isSelected){
        GenreName = foodTypeName;
        this.mImageID = mImageID;
        this.isSelected = isSelected;
    }

    public FoodGenre(String foodTypeName,int mImageID){
        GenreName = foodTypeName;
        this.mImageID = mImageID;
    }

    public String getGenreName() {
        return GenreName;
    }

    public void setGenreName(String genreName) {
        GenreName = genreName;
    }

    public int getmImageID() {
        return mImageID;
    }

    public void setmImageID(int mImageID) {
        this.mImageID = mImageID;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isSelected() {
        return isSelected;
    }
}
