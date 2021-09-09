package com.example.firebaseimagestut;

public class Upload {

    String name;
    String imageUrl;

    // Required for Firebase Database
    public Upload() {
        // empty constructor needed
    }

    public Upload(String name, String imageUrl) {
        if (name.trim().isEmpty()) {
            name = "No name";
        }
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
