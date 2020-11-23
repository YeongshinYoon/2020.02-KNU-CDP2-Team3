
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
 * Use the [area.newInstance] factory method to
 * create an instance of this fragment.
 */
class area : Fragment() {
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
        areaStart()
    }
    private fun areaStart()
    {
        val status1 = view?.findViewById<View>(R.id.result_area) as TextView
        var initem = false
        var increateDt = false
        var indeathCnt = false
        var indefCnf = false
        var ingubun = false
        var inincDec = false
        var inisolClrearCnt = false
        var inisollngCnt = false
        var inlocalOccCnt = false
        var inoverFlowCnt = false
        var inqurRate = false
        var inseq = false
        var instdDay = false
        var inupdateDt = false


        var createDt: String? = null
        var deathCnt: String? = null
        var defCnt: String? = null
        var gubun: String? = null
        var incDec: String? = null
        var isolClearCnt: String? = null
        var isolingCnt: String? = null
        var localOccCnt: String? = null
        var overFlowCnt: String? = null
        var qurRate: String? = null
        var seq: String? = null
        var stdDay: String? = null
        var updateDt: String? = null
        try {
            val url =
                URL("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson?serviceKey=vTyNr6s%2FAnbthjWjdQwjXtaO4qcHv6fDx5WG76mYO0HNVlKx8lICPVvc6%2BYUg929269dQ3qX3srEyhqZvM%2F%2Fdw%3D%3D&pageNo=1&numOfRows=10&startCreateDt=20200410&endCreateDt=20200410")
            val paserCreator = XmlPullParserFactory.newInstance()
            val parser = paserCreator.newPullParser()
            parser.setInput(url.openStream(), null);

            var paserEvent = parser.eventType
            println("Loading..")
            while (paserEvent != XmlPullParser.END_DOCUMENT) {
                when (paserEvent) {
                    XmlPullParser.START_TAG -> {
                        if (parser.name == "createDt") {
                            increateDt = true
                        }
                        if (parser.name == "deathCnt") {
                            indeathCnt = true
                        }
                        if (parser.name == "defCnf") {
                            indefCnf = true
                        }
                        if (parser.name == "gubun") {
                            ingubun = true
                        }
                        if (parser.name == "incDec") {
                            inincDec = true
                        }
                        if (parser.name == "olClearCnt") {
                            inisolClrearCnt = true
                        }
                        if (parser.name == "isolIngCnt") {
                            inisollngCnt = true
                        }
                        if (parser.name == "localOccCnt") {
                            inlocalOccCnt = true
                        }
                        if (parser.name == "overFlowCnt") {
                            inoverFlowCnt = true
                        }
                        if (parser.name == "qurRate") {
                            inqurRate = true
                        }
                        if (parser.name == "seq") {
                            inseq = true
                        }
                        if (parser.name == "stdDay") {
                            instdDay = true
                        }
                        if (parser.name == "updateDt") {
                            inupdateDt = true
                        }
                        if (parser.name == "message") {
                            status1?.text = status1?.text.toString() + "에러"
                        }
                    }
                    XmlPullParser.TEXT -> {
                        if (increateDt) {
                            createDt = parser.text
                            increateDt = false
                        }
                        if (indeathCnt) {
                            deathCnt = parser.text
                            indeathCnt = false
                        }
                        if (indefCnf) {
                            defCnt = parser.text
                            indefCnf = false
                        }
                        if (ingubun) {
                            gubun = parser.text
                            ingubun = false
                        }
                        if (inincDec) {
                            incDec = parser.text
                            inincDec = false
                        }
                        if (inisolClrearCnt) {
                            isolClearCnt = parser.text
                            inisolClrearCnt = false
                        }
                        if (inisollngCnt) {
                            isolingCnt = parser.text
                            inisollngCnt = false
                        }
                        if (inlocalOccCnt) {
                            localOccCnt = parser.text
                            inlocalOccCnt = false
                        }
                        if (inoverFlowCnt) {
                            overFlowCnt = parser.text
                            inoverFlowCnt = false
                        }
                        if (inqurRate) {
                            qurRate = parser.text
                            inqurRate = false
                        }
                        if (inseq) {
                            seq = parser.text
                            inseq = false
                        }
                        if (instdDay) {
                            stdDay = parser.text
                            instdDay = false
                        }
                        if (inupdateDt) {
                            updateDt = parser.text
                            inupdateDt = false
                        }

                    }
                    XmlPullParser.END_TAG -> {
                        if (parser.name == "item") {

                            status1?.text = """${status1?.text}
 등록일시: $createDt
 사망자 수 : $deathCnt
 시/도 : $gubun
 전일대비증감률 : $incDec
 격리해제수 : $isolClearCnt
 10만명당발생률: $qurRate
 기준일시: $stdDay 
 수정일시: $updateDt
 확진자 수: $defCnt
 격리중 환자수: $isolingCnt
 해외유입수 : $overFlowCnt
 지역발생수 : $localOccCnt
-------------------------
"""
                            initem = false
                        }
                    }


                }
                paserEvent = parser.next()
            }
        } catch (e: XmlPullParserException) {
            status1?.text = "에러"
        } catch (e: IOException) {
            status1?.text = "에러"
        }



    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment area.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            area().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_area, container, false)
    }
}

