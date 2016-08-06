package pl.krystiankaniowski.futuremind.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import pl.krystiankaniowski.futuremind.adapter.view.ViewType

class SingleData : ViewType {

    // =============================================================================================
    //      VARIABLES
    // =============================================================================================

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("orderId")
    @Expose
    var orderId: Int? = null

    @SerializedName("modificationDate")
    @Expose
    var modificationDate: String? = null

    @SerializedName("image_url")
    @Expose
    var imageUrl: String? = null

    // =============================================================================================
    //      LOGIC
    // =============================================================================================

    override fun getViewType(): Int = ViewType.ROW

    override fun toString(): String {
        return "orderId ${orderId} | title ${title} | desription {$description} | modificationDate ${modificationDate} | imageUrl ${imageUrl}"
    }

}
