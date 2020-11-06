package com.example.a2020_02_cdp2_team3

class CoronaInfo {

    private var count_total_confirm: String? = null
    private var count_cur_confirm: String? = null
    private var count_total_recovered: String? = null
    private var count_total_death: String? = null
    private var count_today_confirm: String? = null
    private var count_today_recovered: String? = null
    private var count_today_death: String? = null
    private var count_variation_cur_confirm: String? = null
    private var update_time: String? = null


    constructor(total_confirm: String, cur_confirm: String, total_recovered: String, total_death: String, today_confirm: String, today_recovered: String, today_death: String, variation_cur_confirm: String, update_time: String) {
        this.count_total_confirm = total_confirm
        this.count_cur_confirm = cur_confirm
        this.count_total_recovered = total_recovered
        this.count_total_death = total_death
        this.count_today_confirm = today_confirm
        this.count_today_recovered = today_recovered
        this.count_today_death = today_death
        this.count_variation_cur_confirm = variation_cur_confirm
        this.update_time = update_time
    }

    fun setTotalConfirm(total_confirm: String) {
        this.count_total_confirm = total_confirm
    }

    fun setCurConfirm(cur_confirm: String) {
        this.count_cur_confirm = cur_confirm
    }

    fun setTotalRecovered(total_recovered: String) {
        this.count_total_recovered =total_recovered
    }

    fun setTotalDeath(total_death: String) {
        this.count_total_death = total_death
    }

    fun setTodayConfirm(today_confirm: String) {
        this.count_today_confirm = today_confirm
    }

    fun setTodayRecovered(today_recovered: String) {
        this.count_today_recovered = today_recovered
    }

    fun setTodayDeath(today_death: String) {
        this.count_today_death = today_death
    }

    fun setUpdateTime(update_time: String) {
        this.update_time = update_time
    }

    fun setVariationCurConfirm(variation_cur_confirm: String) {
        this.count_variation_cur_confirm = variation_cur_confirm
    }

    fun getTotalConfirm(): String? {
        return count_total_confirm
    }

    fun getCurConfirm(): String? {
        return count_cur_confirm
    }

    fun getTotalRecovered(): String? {
        return count_total_recovered
    }

    fun getTotalDeath(): String? {
        return count_total_death
    }

    fun getTodayConfirm(): String? {
        return count_today_confirm
    }

    fun getTodayRecovered(): String? {
        return count_today_recovered
    }

    fun getTodayDeath(): String? {
        return count_today_death
    }

    fun getUpdateTime(): String? {
        return update_time
    }

    fun getVariationCurConfirm(): String? {
        return count_variation_cur_confirm
    }

}