/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class Slider {
    private int sliderID;
    private String imageUrl;
    private String title;
    private String backLink;
    private String description;
    private boolean publish;

    public Slider() {
    }

    public Slider(int sliderID, String imageUrl, String title, String backLink, String description, boolean publish) {
        this.sliderID = sliderID;
        this.imageUrl = imageUrl;
        this.title = title;
        this.backLink = backLink;
        this.description = description;
        this.publish = publish;
    }

    
    public int getSliderID() {
        return sliderID;
    }

    public void setSliderID(int sliderID) {
        this.sliderID = sliderID;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackLink() {
        return backLink;
    }

    public void setBackLink(String backLink) {
        this.backLink = backLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublish() {
        return publish;
    }

    public void setPublish(boolean publish) {
        this.publish = publish;
    }
}
