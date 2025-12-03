package com.cihatakyol.vibrationmeter.presentation.navigation

/**
 * Navigation routes for the app.
 */
object NavigationRoutes {
    const val LIVE = "live"
    const val SESSIONS = "sessions"
    const val SESSION_DETAIL = "session_detail/{sessionId}"

    fun sessionDetail(sessionId: String) = "session_detail/$sessionId"
}
