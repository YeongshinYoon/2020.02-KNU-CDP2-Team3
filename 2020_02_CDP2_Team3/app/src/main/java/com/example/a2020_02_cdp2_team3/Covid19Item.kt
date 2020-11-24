package com.example.a2020_02_cdp2_team3

/**
 * @param decideCnt 누적 확진자 수
 * @param clearCnt 누적 격리해제 수
 * @param stateDt 기준 년월일
 */
data class Covid19Item(val decideCnt: Int?, val clearCnt: Int?, val stateDt: String?)