
package com.example.a2020_02_cdp2_team3

import android.graphics.Color
import android.os.Bundle
import android.os.StrictMode
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.net.URL
import java.text.SimpleDateFormat

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
        StrictMode.enableDefaults()
        val search = view?.findViewById<Button>(R.id.searcharea)
        val Date = SimpleDateFormat("yyyyMMdd")
        val EndDate: String = Date.format(System.currentTimeMillis())

        val input = view?.findViewById<EditText>(R.id.inputarea)
        val status1 = view?.findViewById(R.id.result_area) as TextView
        val Allsearch = view?.findViewById<Button>(R.id.Allsearcharea)
        val areacolor= view?.findViewById(R.id.areaColor) as TextView





            var initem = false
            var increateDt = false
            var indeathCnt = false
            var indefCnt = false
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
        Allsearch?.setOnClickListener(){
            status1.setTextColor(Color.parseColor("#006400"))
            StrictMode.enableDefaults()
            try {
                val url =
                    URL("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson?serviceKey=vTyNr6s%2FAnbthjWjdQwjXtaO4qcHv6fDx5WG76mYO0HNVlKx8lICPVvc6%2BYUg929269dQ3qX3srEyhqZvM%2F%2Fdw%3D%3D&pageNo=1&numOfRows=10&startCreateDt=$EndDate&endCreateDt=$EndDate")
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
                            if (parser.name == "defCnt") {
                                indefCnt = true
                            }
                            if (parser.name == "gubun") {
                                ingubun = true
                            }
                            if (parser.name == "incDec") {
                                inincDec = true
                            }
                            if (parser.name == "isolClearCnt") {
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
                            if (indefCnt) {
                                defCnt = parser.text
                                indefCnt = false
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
                            if (parser.name == "item"){

                                status1?.text ="""${status1?.text}
  신규확진자    : $localOccCnt 명
  등록일시      : $createDt
  사망자 수     : $deathCnt 명
  시/도         : $gubun
  전일대비증감률 : $incDec %
  격리해제수     : $isolClearCnt 명
  10만명당발생률 : $qurRate %
  기준일시      : $stdDay
  누적확진자    : $defCnt 명
  격리중 환자수 : $isolingCnt 명
  해외유입수    : $overFlowCnt 명
  
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
        search?.setOnClickListener()
        {
            status1.setTextColor(Color.parseColor("#006400"))
            val inputresult = input?.text.toString()
            try {
                val url =
                    URL("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson?serviceKey=vTyNr6s%2FAnbthjWjdQwjXtaO4qcHv6fDx5WG76mYO0HNVlKx8lICPVvc6%2BYUg929269dQ3qX3srEyhqZvM%2F%2Fdw%3D%3D&pageNo=1&numOfRows=10&startCreateDt=$EndDate&endCreateDt=$EndDate")
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
                            if (parser.name == "defCnt") {
                                indefCnt = true
                            }
                            if (parser.name == "gubun") {
                                ingubun = true
                            }
                            if (parser.name == "incDec") {
                                inincDec = true
                            }
                            if (parser.name == "isolClearCnt") {
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
                            if (indefCnt) {
                                defCnt = parser.text
                                indefCnt = false
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
                            if ((parser.name == "item")&&(inputresult==gubun)) {
  var defCntNum= Integer.parseInt(localOccCnt.toString())
  if((defCntNum==0)){
      areacolor.setBackgroundColor(Color.parseColor("#7CFC00"))
      areacolor.setText("상대적으로 안전한 지역입니다. 위험도 10% ")}
                                if((defCntNum>0)&&(3<defCntNum)){
                                    areacolor.setBackgroundColor(Color.parseColor("#FFFF00"))
                                 areacolor.setText("소규모 확진자가 있습니다. 위험도 30%")}
                                if((3<=defCntNum)&&(5>defCntNum)){  areacolor.setBackgroundColor(Color.parseColor("#FF6347"))
                                    areacolor.setText("외출하실 때 마스크를 꼭 착용하세요. 위험도 60%")}
                                if(5<=defCntNum){  areacolor.setBackgroundColor(Color.parseColor("#FF0000"))
                                    areacolor.setText("오늘은 외출금지를 권장합니다. 위험도 80%")}
                                status1?.text = """${status1?.text}
-$gubun 지역의 실시간 코로나 현황입니다.-
  신규확진자    : $localOccCnt 명
  등록일시      : $createDt
  사망자 수     : $deathCnt 명
  시/도         : $gubun
  전일대비증감률 : $incDec %
  격리해제수     : $isolClearCnt 명
  10만명당발생률 : $qurRate %
  기준일시      : $stdDay
  누적확진자    : $defCnt 명
  격리중 환자수 : $isolingCnt 명
  해외유입수    : $overFlowCnt 명
  
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
            return@setOnClickListener
        }
      return
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

