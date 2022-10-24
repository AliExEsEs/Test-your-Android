package com.example.testyourandroid.models;

public class MyModel {

    public String Title;
    public String Description;
    public int ImageVector;

    public MyModel(String title, String description, int imageVector) {
        Title = title;
        Description = description;
        ImageVector = imageVector;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getImageVector() {
        return ImageVector;
    }

    public void setImageVector(int imageVector) {
        ImageVector = imageVector;
    }


}
