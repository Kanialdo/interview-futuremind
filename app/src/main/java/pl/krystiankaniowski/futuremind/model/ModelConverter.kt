package pl.krystiankaniowski.futuremind.model

import pl.krystiankaniowski.futuremind.model.database.Row
import pl.krystiankaniowski.futuremind.model.rest.SingleData

class ModelConverter {

    companion object {

        val TAG: String? = ModelConverter.javaClass.simpleName

        fun toDatabaseModel(data: SingleData): Row {

            val addressStartPostion = data.description!!.indexOf("http://")

            val row = Row()

            row.title = data.title
            row.description = data.description!!.substring(0, addressStartPostion).trim()
            row.orderId = data.orderId!!
            row.imageUrl = data.imageUrl
            row.url = data.description!!.substring(addressStartPostion)
            row.modificationDate = data.modificationDate

            return row

        }

    }

}
