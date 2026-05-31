package com.simats.SkillSyncAI

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("SkillSyncPrefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_LOGIN_TIMESTAMP = "login_timestamp"
        private const val THIRTY_DAYS_MILLIS = 30L * 24 * 60 * 60 * 1000
        
        // Profile Keys
        private const val KEY_USER_NAME = "user_full_name"
        private const val KEY_USER_EMAIL = "user_email"
        private const val KEY_USER_PHONE = "user_phone"
        private const val KEY_USER_ROLE = "user_target_role"
        private const val KEY_USER_IMAGE_URI = "user_profile_image_uri"
        
        // Score Keys
        private const val KEY_CURRENT_SCORE = "current_match_score"
        private const val KEY_PREVIOUS_SCORE = "previous_match_score"
        
        // Analysis Keys
        private const val KEY_ANALYSIS_PROS = "analysis_pros"
        private const val KEY_ANALYSIS_CONS = "analysis_cons"
    }

    fun getSharedPrefs(): SharedPreferences = sharedPreferences

    fun saveAnalysisResults(pros: String, cons: String) {
        sharedPreferences.edit()
            .putString(KEY_ANALYSIS_PROS, pros)
            .putString(KEY_ANALYSIS_CONS, cons)
            .apply()
    }

    fun getAnalysisPros(): String = sharedPreferences.getString(KEY_ANALYSIS_PROS, "") ?: ""
    fun getAnalysisCons(): String = sharedPreferences.getString(KEY_ANALYSIS_CONS, "") ?: ""

    fun saveMatchScore(newScore: Int) {
        val currentScore = sharedPreferences.getInt(KEY_CURRENT_SCORE, 0)
        sharedPreferences.edit()
            .putInt(KEY_PREVIOUS_SCORE, currentScore)
            .putInt(KEY_CURRENT_SCORE, newScore)
            .apply()
    }

    fun getCurrentScore(): Int = sharedPreferences.getInt(KEY_CURRENT_SCORE, 0)
    fun getPreviousScore(): Int = sharedPreferences.getInt(KEY_PREVIOUS_SCORE, 0)

    fun saveLoginSession(rememberMe: Boolean) {
        if (rememberMe) {
            sharedPreferences.edit()
                .putBoolean(KEY_IS_LOGGED_IN, true)
                .putLong(KEY_LOGIN_TIMESTAMP, System.currentTimeMillis())
                .apply()
        }
    }

    fun isLoggedIn(): Boolean {
        val isLoggedIn = sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
        if (!isLoggedIn) return false

        val loginTimestamp = sharedPreferences.getLong(KEY_LOGIN_TIMESTAMP, 0L)
        val currentTime = System.currentTimeMillis()

        // Check if 30 days have passed
        if (currentTime - loginTimestamp > THIRTY_DAYS_MILLIS) {
            clearSession()
            return false
        }

        return true
    }

    fun clearSession() {
        sharedPreferences.edit()
            .putBoolean(KEY_IS_LOGGED_IN, false)
            .putLong(KEY_LOGIN_TIMESTAMP, 0L)
            .apply()
    }

    // Profile Management
    fun saveProfileData(name: String, email: String, phone: String, role: String, imageUri: String?) {
        sharedPreferences.edit()
            .putString(KEY_USER_NAME, name)
            .putString(KEY_USER_EMAIL, email)
            .putString(KEY_USER_PHONE, phone)
            .putString(KEY_USER_ROLE, role)
            .putString(KEY_USER_IMAGE_URI, imageUri)
            .apply()
    }

    fun getUserName(): String = sharedPreferences.getString(KEY_USER_NAME, "Alex Rivera") ?: "Alex Rivera"
    fun getUserEmail(): String = sharedPreferences.getString(KEY_USER_EMAIL, "alex.rivera@example.com") ?: "alex.rivera@example.com"
    fun getUserPhone(): String = sharedPreferences.getString(KEY_USER_PHONE, "+1 234 567 890") ?: "+1 234 567 890"
    fun getUserRole(): String = sharedPreferences.getString(KEY_USER_ROLE, "Aspiring Data Scientist") ?: "Aspiring Data Scientist"
    fun getUserImageUri(): String? = sharedPreferences.getString(KEY_USER_IMAGE_URI, null)

    fun saveMatchDetails(json: String) {
        sharedPreferences.edit()
            .putString("match_details_json", json)
            .apply()
    }

    fun getMatchDetailsJson(): String = sharedPreferences.getString("match_details_json", "") ?: ""
}
