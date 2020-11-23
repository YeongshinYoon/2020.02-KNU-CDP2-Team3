package com.example.a2020_02_cdp2_team3

import android.R.attr.entries
import android.graphics.Color
import android.os.Bundle
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
import kotlinx.coroutines.Dispatchers
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


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

    private fun getAPI(): String {
        val inputStream: InputStream
        val result: String

        val url: URL = URL("http://example.com/")
        val conn: HttpURLConnection = url.openConnection() as HttpURLConnection

        conn.connect()
        inputStream = conn.inputStream

        if (inputStream != null) {
            result = inputStream.toString()
        } else {
            result = "Error"
        }

        return result
    }

    private fun toast(str: String) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
    }


    private fun loadChartData() {
        val th = Thread {
            // var api = CoronaAPI()
            // api.main(this)
            Thread.sleep(3000)
            // toast("Hello!")
            // TODO: Can't toast on a thread that has not called Looper.prepare()


            // SET CHARTS
            // Charts must be set after the view was created
            // https://developer.android.com/reference/android/app/Fragment
            setCumulativeCasesLineChart()
        }.start()


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
    }
}