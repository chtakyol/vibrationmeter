package com.cihatakyol.vibrationmeter.domain.usecase

import com.cihatakyol.vibrationmeter.domain.model.Session
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

/**
 * Use case for exporting session data in different formats.
 */
class ExportSessionUseCase @Inject constructor() {

    /**
     * Export session as CSV string.
     */
    fun exportAsCsv(session: Session): String {
        val csv = StringBuilder()
        csv.append("Timestamp,Magnitude (m/s²)\n")

        session.dataPoints.forEach { point ->
            csv.append("${point.timestamp},${point.magnitude}\n")
        }

        return csv.toString()
    }

    /**
     * Export session as JSON string.
     */
    fun exportAsJson(session: Session): String {
        val json = JSONObject()
        json.put("sessionId", session.id)
        json.put("startTime", session.startTimestamp)
        json.put("endTime", session.endTimestamp)
        json.put("duration", session.duration)
        json.put("maxVibration", session.maxVibration)
        json.put("avgVibration", session.avgVibration)
        json.put("minVibration", session.minVibration)

        val dataPointsArray = JSONArray()
        session.dataPoints.forEach { point ->
            val pointObj = JSONObject()
            pointObj.put("timestamp", point.timestamp)
            pointObj.put("magnitude", point.magnitude)
            dataPointsArray.put(pointObj)
        }
        json.put("dataPoints", dataPointsArray)

        return json.toString(2) // Pretty print with 2-space indentation
    }
}
