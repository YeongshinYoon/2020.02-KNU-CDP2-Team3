package com.example.a2020_02_cdp2_team3

import android.content.ContentValues.TAG
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.coroutines.*
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class GraphFragment : Fragment() {
    companion object {
        const val ENDPOINT: String = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson"
        const val KEY: String = "HpdkPZVpsnRoZJhs4Rv7eekrs%2BxXmZy1BqZwWXKZo8XmhF5ToGmyxvkI6XoTb9qahbKWosuRawWPuMMI0W3g9g%3D%3D"
    }

    // 누적 확진/격리해제 현황 그래프
    private fun setCumulativeCasesLineChart(covid19Items: List<Covid19Item>) {
        val view = requireView()
        val cumulativeCasesLineChart = view.findViewById<LineChart>(R.id.cumulativeCasesLineChart)
        val dataSize = covid19Items.size

        /*
         Configure xAxis.
         */
        val xAxis = cumulativeCasesLineChart.xAxis

        /*
         Get X labels (stateDt from covid19Items)
         */
        val xAxisLabels: ArrayList<String> = ArrayList()
        for (it in covid19Items) {
            xAxisLabels.add(it.stateDt ?: "null")
        }

        /*
         Set graph styles + axis values.
         */
        xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            textSize = 8f
            setDrawGridLines(true)
            granularity = 1f
            valueFormatter = IndexAxisValueFormatter(xAxisLabels)  // x-axis labeling.
        }

        /*
         Construct graph with covid19Items: List<Covid19Item>.

         Entry := each data
         entries := ArrayList of Entry

         lineDataSet := LineDataSet(entries, "y범례")

         dataSets := ArrayList of ILineDataSet.
         dataSets.add(lineDataSet)

         data := LineData(dataSets)
         chart.data = data
         */
        val entries = ArrayList<Entry>()
        for (i in 0 until dataSize) {
            entries.add(Entry(i.toFloat(), covid19Items.get(i).decideCnt!!.toFloat()))
        }

        val lineDataSet = LineDataSet(entries, "y범례")
        lineDataSet.apply {
            lineWidth = 2f
            circleRadius = 6f
            circleHoleColor = ContextCompat.getColor(requireContext(), R.color.purple_500)
            color = ContextCompat.getColor(requireContext(), R.color.purple_700)
            setCircleColor(ContextCompat.getColor(requireContext(), R.color.purple_200))
            setDrawFilled(true)
        }

        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(lineDataSet)

        val data = LineData(dataSets)
        cumulativeCasesLineChart.data = data
        cumulativeCasesLineChart.setNoDataText("데이터를 불러오는 중입니다...")
        cumulativeCasesLineChart.invalidate()
    }

    // 일별 확진자 현황 그래프
    private fun setDayCasesChart(covid19Items: List<Covid19Item>) {
        val view = requireView()
        val dayCasesChart = view.findViewById<LineChart>(R.id.dayCasesChart)
        val dataSize = covid19Items.size

        val xAxis = dayCasesChart.xAxis

        /*
         Get X labels (stateDt from covid19Items)
         */
        val xAxisLabels: ArrayList<String> = ArrayList()
        for (it in covid19Items) {
            xAxisLabels.add(it.stateDt ?: "null")
        }

        /*
         Set graph styles + axis values.
         */
        xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            textSize = 8f
            setDrawGridLines(true)
            granularity = 1f
            valueFormatter = IndexAxisValueFormatter(xAxisLabels)  // x-axis labeling.
        }

        /*
         Construct graph with covid19Items: List<Covid19Item>.

         Entry := each data
         entries := ArrayList of Entry

         lineDataSet := LineDataSet(entries, "y범례")

         dataSets := ArrayList of ILineDataSet.
         dataSets.add(lineDataSet)

         data := LineData(dataSets)
         chart.data = data
         */
        val entries = ArrayList<Entry>()
        val diff = ArrayList<Float>()
        diff.add(0f)
        for (i in 0 until (dataSize-1)) {
            // Data is equal to diff array.
            diff.add(covid19Items.get(i + 1).decideCnt!!.toFloat() - covid19Items.get(i).decideCnt!!.toFloat())
            entries.add(Entry(i.toFloat(), diff[i]))
        }
        /*
        for (i in 0 until dataSize) {
            entries.add(Entry(i.toFloat(), covid19Items.get(i).decideCnt!!.toFloat()))
        }
         */

        val lineDataSet = LineDataSet(entries, "y범례")
        lineDataSet.apply {
            lineWidth = 2f
            circleRadius = 6f
            circleHoleColor = ContextCompat.getColor(requireContext(), R.color.purple_500)
            color = ContextCompat.getColor(requireContext(), R.color.purple_700)
            setCircleColor(ContextCompat.getColor(requireContext(), R.color.purple_200))
            setDrawFilled(true)
        }

        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(lineDataSet)

        val data = LineData(dataSets)
        dayCasesChart.data = data
        dayCasesChart.setNoDataText("데이터를 불러오는 중입니다...")
        dayCasesChart.invalidate()
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
        urlStringBuilder.append("&startCreateDt=20201101")
        urlStringBuilder.append("&endCreateDt=20201110")

        val urlString: String = urlStringBuilder.toString()

        // Coroutine part.
        GlobalScope.launch(Dispatchers.Main + coroutineHandler) {
            val data: List<Covid19Item> = async(Dispatchers.IO) {
                Log.d(TAG, "Await for parsing!")

                downloadUrl(urlString)?.use { stream ->
                    Covid19XmlParser().parse(stream)
                } ?: emptyList()
            }.await()

            Log.d(TAG, "Parse complete!")

            if (data.isEmpty()) {
                Log.d(TAG, "List is empty!")
            } else {
                for (it in data) {
                    Log.d(TAG, "${it.clearCnt}, ${it.decideCnt}, ${it.stateDt}")
                }
            }

            // Set charts here.
            setCumulativeCasesLineChart(data)
            setDayCasesChart(data)
        }
    }


    /*
        ENTRY POINT
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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