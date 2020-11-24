package com.example.a2020_02_cdp2_team3

import android.util.Xml
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.io.InputStream

private val ns: String? = null
class Covid19XmlParser {
    @Throws(XmlPullParserException::class, IOException::class)
    fun parse(inputStream: InputStream): List<*> {
        inputStream.use { inputStream ->
            val parser: XmlPullParser = Xml.newPullParser()
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(inputStream, null)
            parser.nextTag()
            return readItems(parser)

        }
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun readItems(parser: XmlPullParser): List<Covid19Item> {
        val entries = mutableListOf<Covid19Item>()

        // START_TAG is items
        parser.require(XmlPullParser.START_TAG, ns, "items")
        while (parser.eventType != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) continue

            // Starts by looking for the item tag
            if (parser.name == "item") {
                entries.add(readItem(parser))
            } else {
                skip(parser)
            }
        }

        return entries
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun readItem(parser: XmlPullParser): Covid19Item {
        parser.require(XmlPullParser.START_TAG, ns, "item")
        // TODO: Implement
    }
}