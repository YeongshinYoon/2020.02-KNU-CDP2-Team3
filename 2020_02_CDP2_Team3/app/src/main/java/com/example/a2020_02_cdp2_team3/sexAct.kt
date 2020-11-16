package com.example.a2020_02_cdp2_team3

import android.app.Activity
import android.os.Bundle
import com.example.a2020_02_cdp2_team3.R
import android.os.StrictMode
import android.view.View
import android.widget.TextView
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.net.URL

class sexAct : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sex)
        StrictMode.enableDefaults()
        val status1 = findViewById<View>(R.id.result) as TextView
        var initem = false
        var inconfCase = false
        var increateDt = false
        var incriticalRate = false
        var inconfCaseRate = false
        var indeath = false
        var indeathRate = false
        var ingubun = false
        var inseq = false
        var inupdateDt = false
        var number = 1
        var confCase: String? = null
        var confCaseRate: String? = null
        var createDt: String? = null
        var criticalRate: String? = null
        var death: String? = null
        var deathRate: String? = null
        var seq: String? = null
        var updateDt: String? = null
        var gubun: String? = null
        try {
            val url = URL("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19GenAgeCaseInfJson?serviceKey=A2vwv2O8EWf4MCBQKD6bR4eVSjK0Jylzm0x5uedn553xDUchtjh%2F8uWq595vL8SYzGbB5ty0uUCWYQk1duB7pw%3D%3D&pageNo=1&numOfRows=10&startCreateDt=20200310&endCreateDt=20200414")
            val paserCreator = XmlPullParserFactory.newInstance()
            val parser = paserCreator.newPullParser()
            parser.setInput(url.openStream(), null)
            var paserEvent = parser.eventType
            println("Loading..")
            while (number != 103) {
                when (paserEvent) {
                    XmlPullParser.START_TAG -> {
                        if (parser.name == "confCase") {
                            inconfCase = true
                        }
                        if (parser.name == "confCaseRate") {
                            inconfCaseRate = true
                        }
                        if (parser.name == "createDt") {
                            increateDt = true
                        }
                        if (parser.name == "criticalRate") {
                            incriticalRate = true
                        }
                        if (parser.name == "death") {
                            indeath = true
                        }
                        if (parser.name == "deathRate") {
                            indeathRate = true
                        }
                        if (parser.name == "gubun") {
                            ingubun = true
                        }
                        if (parser.name == "seq") {
                            inseq = true
                        }
                        if (parser.name == "updateDt") {
                            inupdateDt = true
                        }
                        if (parser.name == "message") {
                            status1.text = status1.text.toString() + "에러"
                        }
                    }
                    XmlPullParser.TEXT -> {
                        if (inconfCase) {
                            confCase = parser.text
                            inconfCase = false
                        }
                        if (increateDt) {
                            createDt = parser.text
                            increateDt = false
                        }
                        if (incriticalRate) {
                            criticalRate = parser.text
                            incriticalRate = false
                        }
                        if (inconfCaseRate) {
                            confCaseRate = parser.text
                            inconfCaseRate = false
                        }
                        if (indeath) {
                            deathRate = parser.text
                            indeath = false
                        }
                        if (indeathRate) {
                            death = parser.text
                            indeathRate = false
                        }
                        if (ingubun) {
                            gubun = parser.text
                            ingubun = false
                        }
                        if (inseq) {
                            seq = parser.text
                            inseq = false
                        }
                        if (inupdateDt) {
                            updateDt = parser.text
                            inupdateDt = false
                        }
                    }
                    XmlPullParser.END_TAG -> {
                        if (parser.name == "item") {
                            status1.text = """${status1.text}확진자 : $confCase
 확진률: $confCaseRate
 신규확진자 : $createDt
 치명률 : $criticalRate
 사망자 : $death
 사망률 : $deathRate
 번호 : $seq
 수정일 : $updateDt
연령별 : $gubun
--------------------
"""
                            initem = false
                        }
                        number++ //하나의 status1
                    }
                }
                paserEvent = parser.next()
            }
        } catch (e: XmlPullParserException) {
            status1.text = "에러"
        } catch (e: IOException) {
            status1.text = "에러"
        }
    }
}