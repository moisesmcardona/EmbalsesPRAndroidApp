package msc.app.embalsespuertorico

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager.NameNotFoundException
import android.net.Uri
import android.text.format.DateUtils
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentActivity

class RateMeMaybe(private val mActivity: FragmentActivity) : RateMeMaybeFragment.RMMFragInterface {
    private val mPreferences: SharedPreferences
    val `as` = app_settings(mActivity)
    private var dialogTitle: String = "Escribir Review de Embalses de Puerto Rico"
    private var dialogMessage: String = "¿Que te parece este app? Si te gusta, considere escribir un review en el Play Store. ¡Gracias!"
    private var positiveBtn: String =  "Dejar Review"
    private var neutralBtn: String =  "Ahora No"
    private var negativeBtn: String =  "Nunca"

    private var icon: Int = 0

    private var mMinLaunchesUntilInitialPrompt = 0
    private var mMinDaysUntilInitialPrompt = 0

    private var mMinLaunchesUntilNextPrompt = 0
    private var mMinDaysUntilNextPrompt = 0

    private var mHandleCancelAsNeutral: Boolean? = true

    private var mListener: OnRMMUserChoiceListener? = null

    /**
     * @return Whether Google Play Store is installed on device
     */
    private val isPlayStoreInstalled: Boolean
        get() {
            val pacman = mActivity.packageManager
            return try {
                pacman.getApplicationInfo("com.android.vending", 0)
                true
            } catch (e: NameNotFoundException) {
                false
            }

        }

    interface OnRMMUserChoiceListener {
        fun handlePositive()

        fun handleNeutral()

        fun handleNegative()
    }

    init {
        mPreferences = mActivity.getSharedPreferences(PREF.NAME, 0)
    }

    /**
     * Sets requirements for when to prompt the user.
     *
     * @param minLaunchesUntilInitialPrompt
     * Minimum of launches before the user is prompted for the first
     * time. One call of .run() counts as launch.
     * @param minDaysUntilInitialPrompt
     * Minimum of days before the user is prompted for the first
     * time.
     * @param minLaunchesUntilNextPrompt
     * Minimum of launches before the user is prompted for each next
     * time. One call of .run() counts as launch.
     * @param minDaysUntilNextPrompt
     * Minimum of days before the user is prompted for each next
     * time.
     */
    fun setPromptMinimums(minLaunchesUntilInitialPrompt: Int,
                          minDaysUntilInitialPrompt: Int, minLaunchesUntilNextPrompt: Int,
                          minDaysUntilNextPrompt: Int) {
        this.mMinLaunchesUntilInitialPrompt = minLaunchesUntilInitialPrompt
        this.mMinDaysUntilInitialPrompt = minDaysUntilInitialPrompt
        this.mMinLaunchesUntilNextPrompt = minLaunchesUntilNextPrompt
        this.mMinDaysUntilNextPrompt = minDaysUntilNextPrompt
    }

    /**
     * Actually show the dialog (if it is not currently shown)
     */
    private fun showDialog() {
        if (mActivity.supportFragmentManager.findFragmentByTag(
                        "rmmFragment") != null) {
            // the dialog is already shown to the user
            return
        }
        val frag = RateMeMaybeFragment()
        frag.setData(icon, dialogTitle, dialogMessage,
                positiveBtn, neutralBtn, negativeBtn, this)
        frag.show(mActivity.supportFragmentManager, "rmmFragment")

    }

    /**
     * Normal way to update the launch logs and show the user prompt if the
     * requirements are met.
     */
    fun run() {
        if (`as`.language == "English") {
            dialogTitle = "Write Review for Embalses de Puerto Rico"
            dialogMessage= "¿What do you think of this app? If you're enjoying it, consider writing a review in the Play Store. Thanks!"
            positiveBtn = "Write Review"
            neutralBtn = "Not now"
            negativeBtn = "Never"
        }
        if (mPreferences.getBoolean(PREF.DONT_SHOW_AGAIN, false)) {
            return
        }

        if (!isPlayStoreInstalled) {
            Log.d(TAG, "No Play Store installed on device.")
            return
        }

        val editor = mPreferences.edit()

        val totalLaunchCount = mPreferences.getInt(PREF.TOTAL_LAUNCH_COUNT, 0) + 1
        editor.putInt(PREF.TOTAL_LAUNCH_COUNT, totalLaunchCount)

        val currentMillis = System.currentTimeMillis()

        var timeOfAbsoluteFirstLaunch = mPreferences.getLong(
                PREF.TIME_OF_ABSOLUTE_FIRST_LAUNCH, 0)
        if (timeOfAbsoluteFirstLaunch == 0L) {
            // this is the first launch!
            timeOfAbsoluteFirstLaunch = currentMillis
            editor.putLong(PREF.TIME_OF_ABSOLUTE_FIRST_LAUNCH,
                    timeOfAbsoluteFirstLaunch)
        }

        val timeOfLastPrompt = mPreferences.getLong(PREF.TIME_OF_LAST_PROMPT,
                0)

        val launchesSinceLastPrompt = mPreferences.getInt(
                PREF.LAUNCHES_SINCE_LAST_PROMPT, 0) + 1
        editor.putInt(PREF.LAUNCHES_SINCE_LAST_PROMPT, launchesSinceLastPrompt)

        if (totalLaunchCount >= mMinLaunchesUntilInitialPrompt && currentMillis - timeOfAbsoluteFirstLaunch >= mMinDaysUntilInitialPrompt * DateUtils.DAY_IN_MILLIS) {
            // requirements for initial launch are met
            if (timeOfLastPrompt == 0L /* user was not yet shown a prompt */ || launchesSinceLastPrompt >= mMinLaunchesUntilNextPrompt && currentMillis - timeOfLastPrompt >= mMinDaysUntilNextPrompt * DateUtils.DAY_IN_MILLIS) {
                editor.putLong(PREF.TIME_OF_LAST_PROMPT, currentMillis)
                editor.putInt(PREF.LAUNCHES_SINCE_LAST_PROMPT, 0)
                editor.apply()
                showDialog()
            } else {
                editor.commit()
            }
        } else {
            editor.commit()
        }

    }

    override fun handleCancel() {
        if (mHandleCancelAsNeutral!!) {
            handleNeutralChoice()
        } else {
            handleNegativeChoice()
        }
    }

    override fun handleNegativeChoice() {
        val editor = mPreferences.edit()
        editor.putBoolean(PREF.DONT_SHOW_AGAIN, true)
        editor.apply()
        if (mListener != null) {
            mListener!!.handleNegative()
        }
    }

    override fun handleNeutralChoice() {
        if (mListener != null) {
            mListener!!.handleNeutral()
        }
    }

    override fun handlePositiveChoice() {
        val editor = mPreferences.edit()
        editor.putBoolean(PREF.DONT_SHOW_AGAIN, true)
        editor.apply()

        try {
            mActivity
                    .startActivity(Intent(Intent.ACTION_VIEW, Uri
                            .parse("market://details?id=" + mActivity.packageName)))
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(mActivity, "Could not launch Play Store!",
                    Toast.LENGTH_SHORT).show()
        }

        if (mListener != null) {
            mListener!!.handlePositive()
        }

    }

    internal object PREF {
        const val NAME = "rate_me_maybe"

        const val DONT_SHOW_AGAIN = "PREF_DONT_SHOW_AGAIN"
        /**
         * How many times the app was launched in total
         */
        const val TOTAL_LAUNCH_COUNT = "PREF_TOTAL_LAUNCH_COUNT"
        /**
         * Timestamp of when the app was launched for the first time
         */
        const val TIME_OF_ABSOLUTE_FIRST_LAUNCH = "PREF_TIME_OF_ABSOLUTE_FIRST_LAUNCH"
        /**
         * How many times the app was launched since the last prompt
         */
        const val LAUNCHES_SINCE_LAST_PROMPT = "PREF_LAUNCHES_SINCE_LAST_PROMPT"
        /**
         * Timestamp of the last user prompt
         */
        const val TIME_OF_LAST_PROMPT = "PREF_TIME_OF_LAST_PROMPT"
    }

    companion object {
        private const val TAG = "Rate Me"

    }

}
