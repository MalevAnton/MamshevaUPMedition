package com.example.malevup;

public class MaskFeeling {

    private int id;
    private String title;
    private String image;
    private int position;

    public MaskFeeling(int id, String title, String image, int position)
    {
        this.id = id;
        this.title = title;
        this.position = position;
        this.image = image;
    }

    public int getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public String getImage()
    {
        return image;
    }

    public int getPosition()
    {
        return position;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public void setPosition(int position)
    {
        this.position = position;
    }
}
