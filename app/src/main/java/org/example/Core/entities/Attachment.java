package org.example.Core.entities;

public class Attachment {

    public enum Type {
        LINK, IMAGE, VIDEO, DOCUMENT, POST
    }

    private long id;
    private long ownerId;
    private Type type;
    private String link;
    private String image;
    private String video;
    private String document;
    private Long postRef;

    public Attachment() {
    }

    public long getId() {
        return id;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public Type getType() {
        return type;
    }

    public String getLink() {
        return link;
    }

    public String getImage() {
        return image;
    }

    public String getVideo() {
        return video;
    }

    public String getDocument() {
        return document;
    }

    public Long getPostRef() {
        return postRef;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public void setDocument(String doc) {
        this.document = doc;
    }

    public void setPostRef(Long postRef) {
        this.postRef = postRef;
    }
}
