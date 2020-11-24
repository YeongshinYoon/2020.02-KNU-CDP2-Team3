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
        var decideCnt: Int? = null
        var clearCnt: Int? = null
        var stateDt: String? = null

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) continue

            when (parser.name) {
                "decideCnt" -> decideCnt = readDecideCnt(parser)
                "clearCnt" -> clearCnt = readClearCnt(parser)
                "stateDt" -> stateDt = readStateDt(parser)
                else -> skip(parser)
            }
        }

        return Covid19Item(decideCnt, clearCnt, stateDt)
    }

    // Processes "decideCnt" tag in "item" tag.
    @Throws(IOException::class, XmlPullParserException::class)
    private fun readDecideCnt(parser: XmlPullParser): Int {
        parser.require(XmlPullParser.START_TAG, ns, "decideCnt")
        val decideCnt: Int = Integer.parseInt(readText(parser))
        parser.require(XmlPullParser.END_TAG, ns, "decideCnt")
        return decideCnt
    }

    // Processes "clearCnt" tag in "item" tag.
    @Throws(IOException::class, XmlPullParserException::class)
    private fun readClearCnt(parser: XmlPullParser): Int {
        parser.require(XmlPullParser.START_TAG, ns, "clearCnt")
        val clearCnt: Int = Integer.parseInt(readText(parser))
        parser.require(XmlPullParser.END_TAG, ns, "clearCnt")
        return clearCnt
    }

    // Processes "stateDt" tag in "item" tag.
    @Throws(IOException::class, XmlPullParserException::class)
    private fun readStateDt(parser: XmlPullParser): String {
        parser.require(XmlPullParser.START_TAG, ns, "stateDt")
        val stateDt: String = readText(parser)
        parser.require(XmlPullParser.END_TAG, ns, "stateDt")
        return stateDt
    }

    // For the tags title and summary, extracts their text values.
    @Throws(IOException::class, XmlPullParserException::class)
    private fun readText(parser: XmlPullParser): String {
        var result = ""
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.text
            parser.nextTag()
        }
        return result
    }

    // Skips redundant tags.
    @Throws(XmlPullParserException::class, IOException::class)
    private fun skip(parser: XmlPullParser) {
        if (parser.eventType != XmlPullParser.START_TAG) {
            throw IllegalStateException()
        }
        var depth = 1
        while (depth != 0) {
            when (parser.next()) {
                XmlPullParser.END_TAG -> depth--
                XmlPullParser.START_TAG -> depth++
            }
        }
    }
}