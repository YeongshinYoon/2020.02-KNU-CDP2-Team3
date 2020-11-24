package com.example.a2020_02_cdp2_team3

import android.R.attr.entries
import android.content.ContentValues.TAG
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.Xml
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.IDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.coroutines.*
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.io.InputStream
import java.lang.Thread.sleep
import java.net.HttpURLConnection
import java.net.URL


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GraphFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GraphFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GraphFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                GraphFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }

        const val ENDPOINT: String = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson"
        const val KEY: String = "HpdkPZVpsnRoZJhs4Rv7eekrs%2BxXmZy1BqZwWXKZo8XmhF5ToGmyxvkI6XoTb9qahbKWosuRawWPuMMI0W3g9g%3D%3D"
    }




    private fun setCumulativeCasesLineChart() {
        val view = requireView()
        val cumulativeCasesLineChart = view.findViewById<LineChart>(R.id.cumulativeCasesLineChart)

        /*
         Set graph styles.
         */
        val xAxis = cumulativeCasesLineChart.xAxis
        xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            textSize = 10f
            setDrawGridLines(true)
            // granularity = 1f
            // axisMinimum = 2f
            // isGranularityEnabled = true

        }

        cumulativeCasesLineChart.apply {
            axisRight.isEnabled = false
            axisLeft.axisMaximum = 50f
            legend.apply {
                textSize = 15f
                verticalAlignment = Legend.LegendVerticalAlignment.TOP
                horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
                orientation = Legend.LegendOrientation.HORIZONTAL
                setDrawInside(false)
            }
        }

        /*
         Construct graph with sample data.

         Entry := each data
         entries := ArrayList of Entry

         lineDataSet := LineDataSet(entries, "y범례")

         dataSets := ArrayList of ILineDataSet.
         dataSets.add(lineDataSet)

         data := LineData(dataSets)
         chart.data = data
         */
        val entries = ArrayList<Entry>()
        entries.add(Entry(0f, 1f))
        entries.add(Entry(1f, 4f))
        entries.add(Entry(2f, 7f))
        entries.add(Entry(3f, 9f))
        entries.add(Entry(4f, 15f))

        val lineDataSet = LineDataSet(entries, "y범례")
        lineDataSet.apply {
            lineWidth = 2f
            circleRadius = 6f
            circleHoleColor = ContextCompat.getColor(context!!, R.color.purple_500)
            color = ContextCompat.getColor(context!!, R.color.purple_700)
            setCircleColor(ContextCompat.getColor(context!!, R.color.purple_200))
            setDrawFilled(true)
            fillColor = Color.BLACK
        }

        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(lineDataSet)

        val data = LineData(dataSets)
        cumulativeCasesLineChart.data = data
        cumulativeCasesLineChart.invalidate()

    }

    @Throws(IOException::class)
    private fun downloadUrl(urlString: String): InputStream? {
        val url = URL(urlString)
        return (url.openConnection() as? HttpURLConnection)?.run {
            readTimeout = 10000
            connectTimeout = 15000
            requestMethod = "GET"
            doInput = true
            // Starts the query
            connect()
            inputStream
        }
    }

    private val coroutineHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, "COROUTINE HANDLER! : $throwable")
    }

    private fun loadChartData() {

        // TODO: Use companion object with parameters.
        val urlStringBuilder: StringBuilder = StringBuilder(GraphFragment.ENDPOINT + "?")
        urlStringBuilder.append("ServiceKey=${GraphFragment.KEY}")
        urlStringBuilder.append("&pageNo=1")
        urlStringBuilder.append("&numOfRows=10")
        urlStringBuilder.append("&startCreateDt=20200310")
        urlStringBuilder.append("&endCreateDt=20200315")

        val urlString: String = urlStringBuilder.toString()

        GlobalScope.launch(Dispatchers.Main + coroutineHandler) {
            val data: List<Covid19Item> = async(Dispatchers.IO) {
                Log.d(TAG, "await!")
                downloadUrl(urlString)?.use { stream ->
                    Covid19XmlParser().parse(stream)
                } ?: emptyList()
            }.await()

            Log.d(TAG, "Parse complete!")

            if (data.isEmpty()) {
                Log.d(TAG, "empty!")
            } else {
                for (it in data) {
                    Log.d(TAG, it.stateDt ?: "null")
                }
            }

        }



    }


    /*
        ENTRY POINT
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        loadChartData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_graph, container, false)
    }
}