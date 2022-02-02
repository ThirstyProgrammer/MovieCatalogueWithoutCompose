package id.bachtiar.harits.moviecatalogue.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object StringHelper {

    fun getDateForView(date: String?): String {
        var dateToReturn = date.orEmpty()
        if (!date.isNullOrBlank()) {

            val from = "yyyy-MM-dd"

            val sdf = SimpleDateFormat(from, Locale("id_ID"))
            sdf.timeZone = TimeZone.getTimeZone("UTC")
            val gmt: Date?
            val sdfOutPutToSend = SimpleDateFormat("MMMM dd, yyyy", Locale("id_ID"))
            sdfOutPutToSend.timeZone = TimeZone.getDefault()
            try {
                gmt = sdf.parse(date)
                dateToReturn = sdfOutPutToSend.format(gmt!!)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }
        return dateToReturn
    }
}