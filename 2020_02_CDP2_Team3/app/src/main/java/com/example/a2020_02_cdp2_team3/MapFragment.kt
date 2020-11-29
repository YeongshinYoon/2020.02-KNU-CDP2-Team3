package com.example.a2020_02_cdp2_team3
//1129
//manifest-meta  , gradle2 , color
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import org.json.JSONException
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.net.URL
import java.time.LocalDate
import javax.xml.parsers.DocumentBuilderFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MapFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MapFragment : Fragment(), OnMapReadyCallback {
    // TODO: Rename and change types of parameters
    private lateinit var mMap: GoogleMap
    private lateinit var mV: MapView
    @RequiresApi(Build.VERSION_CODES.O)//now
    var today: String = LocalDate.now().toString().replace("-", "")
    var rday : String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_map, container, false)
        mV = v.findViewById(R.id.mapView)
        mV.getMapAsync(this)
        return v
    }

    @RequiresApi(Build.VERSION_CODES.O)//now
    override fun onStart() {
        super.onStart()
        val v = requireView()
        val tt = v.findViewById<TextView>(R.id.textView)
        tt.setText(today+" 격리중 환자수 " )
        mV.onStart()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mV.onSaveInstanceState(outState)
    }

    override fun onPause() {
        super.onPause()
        mV.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mV.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mV.onDestroy()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (mV != null) {
            mV.onCreate(savedInstanceState)
        }

    }

    override fun onStop() {
        super.onStop()
        mV.onStop()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MapFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MapFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)//readItems
    override fun onMapReady(g: GoogleMap?) {
        mMap = g!!
        val Kor = LatLng(36.2, 127.648)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Kor))
        mMap.animateCamera(CameraUpdateFactory.zoomTo(7F))
        readItems()
    }

    // isolIngCnt  격리중 환자수   defCnt 확진자 수
    @RequiresApi(Build.VERSION_CODES.O)//now
    @Throws(JSONException::class)
    private fun readItems() {
        var count=0
         val url =
            URL("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson?serviceKey=psFE%2BPwSbc%2FFEXAPNwaTqTN4Mpzaw2Mi1%2BvvPZsbHb4DL8dEVba%2BAekHQwi9c%2FpTjHhlvBWfJ%2FM4e%2BnQsAe09w%3D%3D&pageNo=1&numOfRows=10&startCreateDt=${today}&endCreateDt=${today}")
        val urls: Document =
            DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url.toString())
        urls.documentElement.normalize()
        val dataL: NodeList = urls.getElementsByTagName("item")
        if(dataL.length<19){
            today = LocalDate.now().minusDays(1).toString().replace("-", "")
            val url =
                URL("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson?serviceKey=psFE%2BPwSbc%2FFEXAPNwaTqTN4Mpzaw2Mi1%2BvvPZsbHb4DL8dEVba%2BAekHQwi9c%2FpTjHhlvBWfJ%2FM4e%2BnQsAe09w%3D%3D&pageNo=1&numOfRows=10&startCreateDt=${today}&endCreateDt=${today}")
            val urls: Document =
                DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url.toString())
            urls.documentElement.normalize()
            val dataL: NodeList = urls.getElementsByTagName("item")
        }
        if(dataL.length>17) {
           // println(dataL.length.toString())
            for (i in 0..dataL.length - 1) {
                var n: Node = dataL.item(i)
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    val e = n as Element
                    if (e.getElementsByTagName("gubun").item(0).textContent == "제주") {
                        count++
                        makeC(
                            LatLng(33.247167, 126.5510603),
                            e.getElementsByTagName("isolIngCnt").item(0).textContent.toDouble()
                        )

                    } else if (e.getElementsByTagName("gubun").item(0).textContent == "경남") {
                        count++
                        makeC(
                            LatLng(35.1051, 128.0628),
                            e.getElementsByTagName("isolIngCnt").item(0).textContent.toDouble()
                        )

                    } else if (e.getElementsByTagName("gubun").item(0).textContent == "경북") {
                        count++
                        makeC(
                            LatLng(36.39519549728635, 128.7040785646239),
                            e.getElementsByTagName("isolIngCnt").item(0).textContent.toDouble()
                        )

                    } else if (e.getElementsByTagName("gubun").item(0).textContent == "전남") {
                        count++
                        makeC(
                            LatLng(34.745832171051205, 126.73774542411417),
                            e.getElementsByTagName("isolIngCnt").item(0).textContent.toDouble()
                        )

                    } else if (e.getElementsByTagName("gubun").item(0).textContent == "전북") {
                        count++
                        makeC(
                            LatLng(35.872550642194895, 127.19990302943614),
                            e.getElementsByTagName("isolIngCnt").item(0).textContent.toDouble()
                        )

                    } else if (e.getElementsByTagName("gubun").item(0).textContent == "충남") {
                        count++
                        makeC(
                            LatLng(36.57769957172524, 126.76365161576369),
                            e.getElementsByTagName("isolIngCnt").item(0).textContent.toDouble()
                        )


                    } else if (e.getElementsByTagName("gubun").item(0).textContent == "충북") {
                        count++
                        makeC(
                            LatLng(36.84098970153244, 127.69461258134841),
                            e.getElementsByTagName("isolIngCnt").item(0).textContent.toDouble()
                        )

                    } else if (e.getElementsByTagName("gubun").item(0).textContent == "강원") {
                        count++
                        makeC(
                            LatLng(37.32546635706039, 128.5310547237115),
                            e.getElementsByTagName("isolIngCnt").item(0).textContent.toDouble()
                        )

                    } else if (e.getElementsByTagName("gubun").item(0).textContent == "경기") {
                        count++
                        makeC(
                            LatLng(37.443699911687865, 127.41811002862957),
                            e.getElementsByTagName("isolIngCnt").item(0).textContent.toDouble()
                        )
                    } else if (e.getElementsByTagName("gubun").item(0).textContent == "세종") {
                        count++
                        makeC(
                            LatLng(36.48030541968669, 127.28905685383258),
                            e.getElementsByTagName("isolIngCnt").item(0).textContent.toDouble()
                        )

                    } else if (e.getElementsByTagName("gubun").item(0).textContent == "울산") {
                        count++
                        makeC(
                            LatLng(35.564238142006666, 129.25772075060706),
                            e.getElementsByTagName("isolIngCnt").item(0).textContent.toDouble()
                        )

                    } else if (e.getElementsByTagName("gubun").item(0).textContent == "대전") {
                        count++
                        makeC(
                            LatLng(36.34942304516053, 127.39985242613885),
                            e.getElementsByTagName("isolIngCnt").item(0).textContent.toDouble()
                        )

                    } else if (e.getElementsByTagName("gubun").item(0).textContent == "광주") {
                        count++
                        makeC(
                            LatLng(35.16722229901042, 126.8846651013883),
                            e.getElementsByTagName("isolIngCnt").item(0).textContent.toDouble()
                        )

                    } else if (e.getElementsByTagName("gubun").item(0).textContent == "인천") {
                        count++
                        makeC(
                            LatLng(37.438717724931266, 126.7628772872234),
                            e.getElementsByTagName("isolIngCnt").item(0).textContent.toDouble()
                        )

                    } else if (e.getElementsByTagName("gubun").item(0).textContent == "대구") {
                        count++
                        makeC(
                            LatLng(35.84781334428778, 128.55962924039264),
                            e.getElementsByTagName("isolIngCnt").item(0).textContent.toDouble()
                        )

                    } else if (e.getElementsByTagName("gubun").item(0).textContent == "부산") {
                        count++
                        makeC(
                            LatLng(35.137, 129.055),
                            e.getElementsByTagName("isolIngCnt").item(0).textContent.toDouble()
                        )

                    } else if (e.getElementsByTagName("gubun").item(0).textContent == "서울") {
                        count++
                        makeC(
                            LatLng(37.5506, 126.991),
                            e.getElementsByTagName("isolIngCnt").item(0).textContent.toDouble()
                        )
                        rday= e.getElementsByTagName("createDt").item(0).textContent
                    }
                    if(count>17){
                        break
                    }
                }//ifE
            }//dataL.for
        }//if19
        val v = requireView()
        val tt = v.findViewById<TextView>(R.id.textView)
        tt.append( rday+" 등록")
    }//read items

    private fun makeC(lat: LatLng, num: Double) {
        var c: Int
        //    " 0~50명:파랑 50~100명:초록 100~200명:노랑 200~500명:주황 500명이상:빨강")
        if (num <= 50) {
            c = Color.rgb(0, 0, 255)  // blue
        } else if (50 < num && num <= 100) {
            c = Color.rgb(0, 255, 0)  // green
        } else if (100 < num && num <= 200) {
            c = Color.rgb(255, 250, 0)  // yellow
        } else if (200 < num && num <= 500) {
            c = Color.rgb(255, 130, 0)  //orange
        } else {
            c = Color.rgb(255, 0, 0) // red
        }

        mMap.addCircle(
            CircleOptions()
                .center(lat)
                .radius(10000.0)
                .strokeColor(c)
                .fillColor(c)
        )

    }

}

