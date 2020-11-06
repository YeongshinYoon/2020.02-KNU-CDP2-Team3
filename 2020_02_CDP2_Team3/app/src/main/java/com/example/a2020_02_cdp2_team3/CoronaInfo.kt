package com.example.a2020_02_cdp2_team3

class CoronaInfo {

    private var count_world_confirm: String = ""
    private var count_world_confirm_variation: String = ""
    private var count_world_death: String = ""
    private var count_world_death_variation = ""

    private var count_korea_confirm: String = ""
    private var count_korea_cur_confirm: String = ""
    private var count_korea_recovered: String = ""
    private var count_korea_death: String = ""
    private var count_korea_confirm_variation: String = ""
    private var count_korea_recovered_variation: String = ""
    private var count_korea_death_variation: String = ""
    private var count_korea_cur_confirm_variation: String = ""

    private var count_daily_from_korea: String = ""
    private var count_daily_from_oversea: String = ""

    private var update_time_world: String = ""
    private var update_time_korea: String = ""


    constructor(world_confirm: String, world_confirm_variation: String, world_death: String, world_death_variation: String, korea_confirm: String, korea_cur_confirm: String, korea_recovered: String, korea_death: String, korea_confirm_variation: String, korea_recovered_variation: String, korea_death_variation: String, korea_cur_confirm_variation: String, daily_from_korea: String, daily_from_oversea: String, update_time_world: String, update_time_korea: String) {
        this.count_world_confirm = world_confirm
        this.count_world_confirm_variation = world_confirm_variation
        this.count_world_death = world_death
        this.count_world_death_variation = world_death_variation

        this.count_korea_confirm = korea_confirm
        this.count_korea_cur_confirm = korea_cur_confirm
        this.count_korea_recovered = korea_recovered
        this.count_korea_death = korea_death
        this.count_korea_confirm_variation = korea_confirm_variation
        this.count_korea_recovered_variation = korea_recovered_variation
        this.count_korea_death_variation = korea_death_variation
        this.count_korea_cur_confirm_variation = korea_cur_confirm_variation

        this.count_daily_from_korea = daily_from_korea
        this.count_daily_from_oversea = daily_from_oversea

        this.update_time_world = update_time_world
        this.update_time_korea = update_time_korea
    }

    fun setWorldConfirm(world_confirm: String) {
        this.count_world_confirm = world_confirm
    }

    fun setWorldConfirmVariation(world_confirm_variation: String) {
        this.count_world_confirm_variation = world_confirm_variation
    }

    fun setWorldDeath(world_death: String) {
        this.count_world_death = world_death
    }

    fun setWorldDeathVariation(world_death_variation: String) {
        this.count_world_death_variation = world_death_variation
    }

    fun setKoreaConfirm(korea_confirm: String) {
        this.count_korea_confirm = korea_confirm
    }

    fun setKoreaCurConfirm(korea_cur_confirm: String) {
        this.count_korea_cur_confirm = korea_cur_confirm
    }

    fun setKoreaRecovered(korea_recovered: String) {
        this.count_korea_recovered =korea_recovered
    }

    fun setKoreaDeath(korea_death: String) {
        this.count_korea_death = korea_death
    }

    fun setKoreaConfirmVariation(korea_confirm_variation: String) {
        this.count_korea_confirm_variation = korea_confirm_variation
    }

    fun setKoreaRecoveredVariation(korea_recovered_variation: String) {
        this.count_korea_recovered_variation = korea_recovered_variation
    }

    fun setKoreaDeathVariation(korea_death_variation: String) {
        this.count_korea_death_variation = korea_death_variation
    }

    fun setDailyFromKorea(daily_from_korea: String) {
        this.count_daily_from_korea = daily_from_korea
    }

    fun setDailyFromOversea(daily_from_oversea: String) {
        this.count_daily_from_oversea = daily_from_oversea
    }

    fun setUpdateTimeKorea(update_time_korea: String) {
        this.update_time_korea = update_time_korea
    }

    fun setUpdateTimeWorld(update_time_world: String) {
        this.update_time_world = update_time_world
    }

    fun setKoreaCurConfirmVariation(korea_cur_confirm_variation: String) {
        this.count_korea_cur_confirm_variation = korea_cur_confirm_variation
    }

    fun getWorldConfirm(): String {
        return count_world_confirm
    }

    fun getWorldConfirmVariation(): String {
        return count_world_confirm_variation
    }

    fun getWorldDeath(): String {
        return count_world_death
    }

    fun getWorldDeathVariation(): String {
        return count_world_death_variation
    }

    fun getKoreaConfirm(): String {
        return count_korea_confirm
    }

    fun getKoreaCurConfirm(): String {
        return count_korea_cur_confirm
    }

    fun getKoreaRecovered(): String {
        return count_korea_recovered
    }

    fun getKoreaDeath(): String {
        return count_korea_death
    }

    fun getKoreaConfirmVariation(): String {
        return count_korea_confirm_variation
    }

    fun getKoreaRecoveredVariation(): String {
        return count_korea_recovered_variation
    }

    fun getKoreaDeathVariation(): String {
        return count_korea_death_variation
    }

    fun getDailyFromKorea(): String {
        return count_daily_from_korea
    }

    fun getDailyFromOversea(): String {
        return count_daily_from_oversea
    }

    fun getUpdateTimeKorea(): String {
        return update_time_korea
    }

    fun getUpdateTimeWorld(): String {
        return update_time_world
    }

    fun getKoreaCurConfirmVariation(): String {
        return count_korea_cur_confirm_variation
    }

}