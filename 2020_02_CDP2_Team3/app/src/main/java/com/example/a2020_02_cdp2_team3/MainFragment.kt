package com.example.a2020_02_cdp2_team3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var coronaInfo: CoronaInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        loadMain()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    private fun loadMain() {
        Thread{
            var api = CoronaAPI()
            api.main(this)
        }.start()
    }

    fun setCoronaInfo(coronaInfo: CoronaInfo) {
        this.coronaInfo = coronaInfo

        setupUI()
    }

    fun getCoronaInfo(): CoronaInfo? {
        return this.coronaInfo
    }

    private fun setupUI() {
        val view = requireView()

        val world_confirm = view.findViewById<TextView>(R.id.world_confirm)
        val world_confirm_variation = view.findViewById<TextView>(R.id.world_confirm_variation)
        val world_death = view.findViewById<TextView>(R.id.world_death)
        val world_death_variation = view.findViewById<TextView>(R.id.world_death_variation)

        val korea_confirm = view.findViewById<TextView>(R.id.korea_confirm)
        val korea_confirm_variation = view.findViewById<TextView>(R.id.korea_confirm_variation)
        val korea_cur_confirm = view.findViewById<TextView>(R.id.korea_cur_confirm)
        val korea_cur_confirm_variation = view.findViewById<TextView>(R.id.korea_cur_confirm_variation)
        val korea_recovered = view.findViewById<TextView>(R.id.korea_recovered)
        val korea_recovered_variation = view.findViewById<TextView>(R.id.korea_recovered_variation)
        val korea_death = view.findViewById<TextView>(R.id.korea_death)
        val korea_death_variation = view.findViewById<TextView>(R.id.korea_death_variation)

        val daily_from_korea = view.findViewById<TextView>(R.id.daily_from_korea)
        val daily_from_oversea = view.findViewById<TextView>(R.id.daily_from_oversea)

        val update_time_world = view.findViewById<TextView>(R.id.update_time_world)
        val update_time_korea = view.findViewById<TextView>(R.id.update_time_korea)

        world_confirm.text = coronaInfo?.getWorldConfirm()
        world_confirm_variation.text = coronaInfo?.getWorldConfirmVariation()
        world_death.text = coronaInfo?.getWorldDeath()
        world_death_variation.text = coronaInfo?.getWorldDeathVariation()

        korea_confirm.text = coronaInfo?.getKoreaConfirm()
        korea_confirm_variation.text = coronaInfo?.getKoreaConfirmVariation()
        korea_cur_confirm.text = coronaInfo?.getKoreaCurConfirm()
        korea_cur_confirm_variation.text = coronaInfo?.getKoreaCurConfirmVariation()
        korea_recovered.text = coronaInfo?.getKoreaRecovered()
        korea_recovered_variation.text = coronaInfo?.getKoreaRecoveredVariation()
        korea_death.text = coronaInfo?.getKoreaDeath()
        korea_death_variation.text = coronaInfo?.getKoreaDeathVariation()

        daily_from_korea.text = "국내발생" + coronaInfo?.getDailyFromKorea()
        daily_from_oversea.text = "해외유입" + coronaInfo?.getDailyFromOversea()

        update_time_world.text = coronaInfo?.getUpdateTimeWorld()
        update_time_korea.text = coronaInfo?.getUpdateTimeKorea()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}