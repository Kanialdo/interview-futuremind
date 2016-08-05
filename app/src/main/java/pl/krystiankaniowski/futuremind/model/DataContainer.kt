package pl.krystiankaniowski.futuremind.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class DataContainer {

    @SerializedName("data")
    @Expose
    var data: List<SingleData> = ArrayList()

}
