package pl.krystiankaniowski.futuremind.model.rest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class DataContainer {

    // =============================================================================================
    //      VARIABLES
    // =============================================================================================

    @SerializedName("data")
    @Expose
    var data: List<SingleData> = ArrayList()

}

class SingleData {

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

    override fun toString(): String {
        return "orderId ${orderId} | title ${title} | desription {$description} | modificationDate ${modificationDate} | imageUrl ${imageUrl}"
    }

}
