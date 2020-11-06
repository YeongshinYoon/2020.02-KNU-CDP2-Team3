package com.example.a2020_02_cdp2_team3

import android.util.Log
import org.jsoup.Jsoup
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
        var doc = Jsoup.connect("https://search.naver.com/search.naver?where=nexearch&sm=top_sly.hst&fbm=0&acr=1&acq=코로나&qdt=0&ie=utf8&query=코로나").get()
        var elements = doc.select("div.status_info ul li p")

        var world_confirm = elements[4].text()
        var world_death = elements[5].text()

        elements = doc.select("div.status_info ul li em")

        var world_confirm_variation = "+"+elements[4].text()
        var world_death_variation = "+"+elements[5].text()

        elements = doc.select("div.csp_infoCheck_area a")
        var update_time_world = elements[5].text()
        var update_time_korea = elements[1].text()

        elements = doc.select("div.status_today ul li em")
        var daily_from_korea = elements[0].text()
        var daily_from_oversea = elements[1].text()

        val streamReader = InputStreamReader(body)

        var total_confirm = ""
        var total_recovered = ""
        var cur_confirm = ""
        var total_death = ""
        var today_confirm = ""
        var today_recovered = ""
        var today_death = ""
        var variation_cur_confirm = ""

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

                val coronaInfo = CoronaInfo(world_confirm, world_confirm_variation, world_death, world_death_variation, total_confirm, cur_confirm, total_recovered, total_death, today_confirm, today_recovered, today_death, variation_cur_confirm, daily_from_korea, daily_from_oversea, update_time_world, update_time_korea)
                fr.setCoronaInfo(coronaInfo)
            })
        } catch (e: IOException) {
            throw RuntimeException("API 응답을 읽는데 실패했습니다.", e)
        }
    }
}