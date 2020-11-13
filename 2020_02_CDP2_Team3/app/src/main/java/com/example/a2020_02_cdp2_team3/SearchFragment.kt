package com.example.a2020_02_cdp2_team3

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.IOException
import java.net.URLEncoder
import javax.xml.parsers.DocumentBuilderFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var resultTest: String? = null

    @RequiresApi(Build.VERSION_CODES.N) //putIfAbsent
    public fun setResulTest(str : String){
        this.resultTest = str
        setSearchUi(str)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        //Not here
    }

    @RequiresApi(Build.VERSION_CODES.N)//SearchStart
    override fun onStart() {
        super.onStart()
        SearchStart()
    }

    private fun setSearchUi(str: String) {
        val view = requireView()
        val tt = view.findViewById<TextView>(R.id.RView)
        tt.setText(str)
    }
    @RequiresApi(Build.VERSION_CODES.N)//hosp
    private fun SearchStart(){
        val view = requireView()
        val btn1 = view.findViewById<Button>(R.id.btn1)
        val btn2 = view.findViewById<Button>(R.id.btn2)
        val tt = view.findViewById<TextView>(R.id.tt)
        val input1 = view.findViewById<EditText>(R.id.input1)
        val input2 = view.findViewById<EditText>(R.id.input2)
        val input3 = view.findViewById<EditText>(R.id.input3)
        var SearchAddr :String = ""

        btn1.setOnClickListener {
            val in1 =input1.text.toString()
            val in2 =input2.text.toString()
            val in3 =input3.text.toString()
            tt.setText(in1+" "+in2)
            hosp(in1, in2, this, )
        }
        btn2.setOnClickListener {
            SearchAddr= input1.text.toString()+" "+input2.text.toString()+" "+input3.text.toString()
            tt.setText("btn2 클릭" + SearchAddr)
            food(SearchAddr)

        }
    }
    @RequiresApi(Build.VERSION_CODES.N)//main
    private fun hosp(in1: String,in2:String,   fr: SearchFragment) {

        val th = Thread({
            var HF = HospFind()
            HF.main(in1, in2, fr)
        }).start()

    }

    private fun food(Adr: String) {

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

    }
}
@RequiresApi(Build.VERSION_CODES.N)//setResulTest
fun setresultPrint(str: String, fr: SearchFragment) {
    fr.setResulTest(str)
    // !OnUiThread
}

// https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15043078
class HospFind {
    @RequiresApi(Build.VERSION_CODES.N)// setresultPrint
    @Throws(IOException::class)
    fun main(in1: String,in2: String,  frame: SearchFragment, ) {//args: Array<String>
        val urlBuilder = StringBuilder("http://apis.data.go.kr/B551182/pubReliefHospService/getpubReliefHospList") /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=KUJ81dB6%2BRS16ZR38dzTr4WUe5faS3nR%2Bfcbd95v8LErhmkK1kjGf40eO4%2F7uyUORiZWrhMXRZgov%2Fgu5JcW%2BA%3D%3D") /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + URLEncoder.encode("KUJ81dB6%2BRS16ZR38dzTr4WUe5faS3nR%2Bfcbd95v8LErhmkK1kjGf40eO4%2F7uyUORiZWrhMXRZgov%2Fgu5JcW%2BA%3D%3D", "UTF-8")) /*공공데이터포털에서 받은 인증키*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")) /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("700", "UTF-8")) /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("spclAdmTyCd", "UTF-8") + "=" + URLEncoder.encode("99", "UTF-8")) /*A0: 국민안심병원/97: 코로나검사 실시기관/99: 코로나 선별진료소 운영기관*/
        val sb = StringBuilder()
        val hosp: Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(urlBuilder.toString())
        hosp.documentElement.normalize()
        val hostL: NodeList =hosp.getElementsByTagName("item")
        for(i in 0..hostL.length-1){
            var n: Node =hostL.item(i)
            if(n.getNodeType()==Node.ELEMENT_NODE){
                val e=n as Element
                    if(  e.getElementsByTagName("sidoNm").item(0).textContent==in1){
                        if(e.getElementsByTagName("sgguNm").item(0).textContent==in2){
                            sb.append( e.getElementsByTagName("yadmNm").item(0).textContent+"  "+
                                    e.getElementsByTagName("telno").item(0).textContent+"\n")
                        }
                    }
            }
        }
        if(sb.toString()==""){
            setresultPrint("입력예시: 시/도:서울 , 시/군/구:강남구 시/도:대구 , 시/군/구:북구", frame)
        }
        else
        setresultPrint(sb.toString(), frame)
    }
}
