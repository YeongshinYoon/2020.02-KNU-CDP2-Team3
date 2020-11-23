package com.example.a2020_02_cdp2_team3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.net.URL

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [sex.newInstance] factory method to
 * create an instance of this fragment.
 */
class sex : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onStart() {
        super.onStart()
        sexStart()
    }

    private fun sexStart()
    {
        val status1 = view?.findViewById(R.id.result_sex) as TextView
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
            val url =
                    URL("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19GenAgeCaseInfJson?serviceKey=A2vwv2O8EWf4MCBQKD6bR4eVSjK0Jylzm0x5uedn553xDUchtjh%2F8uWq595vL8SYzGbB5ty0uUCWYQk1duB7pw%3D%3D&pageNo=1&numOfRows=10&startCreateDt=20200310&endCreateDt=20200414")
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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sex, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment sex.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            sex().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}