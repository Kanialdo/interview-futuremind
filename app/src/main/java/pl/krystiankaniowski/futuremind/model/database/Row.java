package pl.krystiankaniowski.futuremind.model.database;

import pl.krystiankaniowski.futuremind.adapter.view.ViewType;

public class Row implements ViewType {

    // =============================================================================================
    //      VARIABLES
    // =============================================================================================

    private String title;
    private String description;
    private int orderId;
    private String modificationDate;
    private String imageUrl;
    private String url;

    // =============================================================================================
    //      CONSTRUCTOR
    // =============================================================================================

    public Row() {
    }

    public Row(String title, String description, int orderiId, String modificationDate, String imageUrl, String url) {
        this.title = title;
        this.description = description;
        this.orderId = orderiId;
        this.modificationDate = modificationDate;
        this.imageUrl = imageUrl;
        this.url = url;
    }

    // =============================================================================================
    //      LOGIC
    // =============================================================================================

    @Override
    public int getViewType() {
        return ViewType.ROW;
    }

    // =============================================================================================
    //      GETTERS & SETTERS
    // =============================================================================================

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}