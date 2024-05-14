package com.malbyte.pointrecordingapp.feature_poin_recorder.domain.utils

import com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.local.model.PoinHistory

fun List<PoinHistory>.mapToHistoryIndexing(): List<DateWithHistoryIndex> {
    val groupList = this.groupBy { it.createdAt  }
    return groupList.map { (date, historyPoin) ->
        DateWithHistoryIndex(
            date = date,
            recorder = historyPoin.map { it.recorderName },
            employee = historyPoin.map { it.employeeName },
            fault = historyPoin.map { it.fault },
            poinIn = historyPoin.map { it.poinIn },
            poinOut = historyPoin.map { it.poinOut }
        )
    }
}

data class DateWithHistoryIndex(
    val date: String,
    val recorder: List<String>,
    val employee: List<String>,
    val fault: List<String>,
    val poinIn: List<Int>,
    val poinOut: List<Int>
)


