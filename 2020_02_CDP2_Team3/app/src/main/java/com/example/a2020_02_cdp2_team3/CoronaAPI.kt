package com.example.a2020_02_cdp2_team3

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.RuntimeException
import java.net.MalformedURLException
import java.net.URL


class CoronaAPI {

    val apiURL = "https://api.corona-19.kr/korea/?serviceKey="
    val apiKey = "8c9ed6f4c59597ba4958ecb45665dcca4"

    fun main(fr: MainFragment) {
        val url = apiURL+apiKey
        val temp = "-10".toInt()
        Log.i("CoronaAPI-main", temp.toString())
        get(url, fr)
    }

    private operator fun get(apiURL: String, fr: MainFragment) {
        try {
            val url = URL(apiURL)
            val `in` = url.openStream()
            parseData(`in`, fr)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun parseData(body: InputStream, fr: MainFragment) {
        val streamReader = InputStreamReader(body)

        var total_confirm = ""
        var total_recovered = ""
        var cur_confirm = ""
        var total_death = ""
        var today_confirm = ""
        var today_recovered = ""
        var today_death = ""
        var variation_cur_confirm = ""
        var update_time = ""

        try {
            BufferedReader(streamReader).use({ lineReader ->
                var line: String? = lineReader.readLine()
                while (line != null) {
                    val splited = line.split(":")
                    if (splited[0].length > 6) {
                        val title = splited[0].substring(5, splited[0].length-1)
                        val value = splited[1].substring(2, splited[1].length-2)
                        when (title) {
                            "TotalCase" -> total_confirm = value
                            "TotalRecovered" -> total_recovered = value
                            "TotalDeath" -> total_death = value
                            "NowCase" -> cur_confirm = value
                            "TodayRecovered" -> today_recovered = "+"+value
                            "TodayDeath" -> today_death = "+"+value
                            "TotalCaseBefore" -> variation_cur_confirm = value
                            "updateTime" -> update_time = value
                        }
                    }
                    line = lineReader.readLine()
                }

                var temp = variation_cur_confirm.toInt() + today_recovered.toInt() + today_death.toInt()
                today_confirm = "+"+temp.toString()

                if (variation_cur_confirm.toInt() < 0) {
                    variation_cur_confirm = "-"+variation_cur_confirm
                }
                else {
                    variation_cur_confirm = "+"+variation_cur_confirm
                }

                val coronaInfo = CoronaInfo(total_confirm, cur_confirm, total_recovered, total_death, today_confirm, today_recovered, today_death, variation_cur_confirm, update_time)
                fr.setCoronaInfo(coronaInfo)
            })
        } catch (e: IOException) {
            throw RuntimeException("API 응답을 읽는데 실패했습니다.", e)
        }
    }
}