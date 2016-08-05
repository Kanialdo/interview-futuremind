
package pl.krystiankaniowski.futuremind.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("orderId")
    @Expose
    private Integer orderId;
    @SerializedName("modificationDate")
    @Expose
    private String modificationDate;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The orderId
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 
     * @param orderId
     *     The orderId
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 
     * @return
     *     The modificationDate
     */
    public String getModificationDate() {
        return modificationDate;
    }

    /**
     * 
     * @param modificationDate
     *     The modificationDate
     */
    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    /**
     * 
     * @return
     *     The imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 
     * @param imageUrl
     *     The image_url
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
