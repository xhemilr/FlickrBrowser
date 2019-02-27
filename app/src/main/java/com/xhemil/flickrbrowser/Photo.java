package com.xhemil.flickrbrowser;

import java.io.Serializable;

public class Photo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mTitle;
    private String mAuthor;
    private String mAuthorId;
    private String mLink;
    private String mTags;
    private String mImage;
    private String mFullLink;

    public Photo(String title, String author, String authorId, String link, String tags, String image, String fullLink) {
        mTitle = title;
        mAuthor = author;
        mAuthorId = authorId;
        mLink = link;
        mTags = tags;
        mImage = image;
        mFullLink = fullLink;

    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public String getAuthorId() {
        return mAuthorId;
    }

    public void setAuthorId(String authorId) {
        mAuthorId = authorId;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        mLink = link;
    }

    public String getTags() {
        return mTags;
    }

    public void setTags(String tags) {
        mTags = tags;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getFullLink(){
        return mFullLink;
    }

    public void setFullLink(String fullLink){
        mFullLink = fullLink;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "mTitle='" + mTitle + '\'' +
                ", mAuthor='" + mAuthor + '\'' +
                ", mAuthorId='" + mAuthorId + '\'' +
                ", mLink='" + mLink + '\'' +
                ", mTags='" + mTags + '\'' +
                ", mImage='" + mImage + '\'' +
                '}';
    }
}
