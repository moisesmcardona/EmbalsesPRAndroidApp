package msc.app.embalsespuertorico

import android.util.DisplayMetrics
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.*

class AdFunctions : AppCompatActivity()  {
    private lateinit var mAdView: AdView
    private lateinit var adViewContainer: FrameLayout
    private lateinit var activity: AppCompatActivity

    private val adSize: AdSize
        get() {
            val display = activity.windowManager.defaultDisplay
            val outMetrics = DisplayMetrics()
            display.getMetrics(outMetrics)

            val density = outMetrics.density

            var adWidthPixels = adViewContainer.width.toFloat()
            if (adWidthPixels == 0f) {
                adWidthPixels = outMetrics.widthPixels.toFloat()
            }

            val adWidth = (adWidthPixels / density).toInt()
            return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth)
        }

    fun loadBanner(adFrame: FrameLayout, adString: Int, activity: AppCompatActivity){
        MobileAds.initialize(activity) { }
        this.activity = activity
        mAdView = AdView(activity)
        this.adViewContainer = adFrame
        mAdView.adUnitId = activity.resources.getString(adString)
        this.mAdView.adSize = adSize
        this.adViewContainer.addView(mAdView)
        val configuration = RequestConfiguration.Builder().setTestDeviceIds(listOf(activity.resources.getString(R.string.deviceTestID))).build()
        MobileAds.setRequestConfiguration(configuration)
        val adRequest = AdRequest.Builder().build()
        this.mAdView.loadAd(adRequest)
    }
}
