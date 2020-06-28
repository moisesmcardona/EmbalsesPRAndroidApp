package msc.app.embalsespuertorico

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.DialogInterface.OnCancelListener
import android.content.DialogInterface.OnClickListener
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class RateMeMaybeFragment : DialogFragment(), OnClickListener, OnCancelListener {

    private var mInterface: RMMFragInterface? = null

    private var title: String? = null
    private var message: String? = null
    private var customIcon: Int = 0
    private var positiveBtn: String? = null
    private var neutralBtn: String? = null
    private var negativeBtn: String? = null

    interface RMMFragInterface {
        fun _handlePositiveChoice()

        fun _handleNeutralChoice()

        fun _handleNegativeChoice()

        fun _handleCancel()
    }

    fun setData(customIcon: Int, title: String, message: String,
                positiveBtn: String, neutralBtn: String, negativeBtn: String,
                myInterface: RMMFragInterface) {
        this.customIcon = customIcon
        this.title = title
        this.message = message
        this.positiveBtn = positiveBtn
        this.neutralBtn = neutralBtn
        this.negativeBtn = negativeBtn
        this.mInterface = myInterface
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Fragment including variables will survive orientation changes
        this.retainInstance = true
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity)

        if (customIcon != 0) {
            builder.setIcon(customIcon)
        }
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(positiveBtn, this)
        builder.setNeutralButton(neutralBtn, this)
        builder.setNegativeButton(negativeBtn, this)
        builder.setOnCancelListener(this)
        return builder.create()
    }

    override fun onCancel(dialog: DialogInterface) {
        mInterface!!._handleCancel()
    }

    override fun onClick(dialog: DialogInterface, choice: Int) {
        when (choice) {
            DialogInterface.BUTTON_POSITIVE -> mInterface!!._handlePositiveChoice()
            DialogInterface.BUTTON_NEUTRAL -> mInterface!!._handleNeutralChoice()
            DialogInterface.BUTTON_NEGATIVE -> mInterface!!._handleNegativeChoice()
        }
    }

    override fun onDestroyView() {
        // Work around bug:
        // http://code.google.com/p/android/issues/detail?id=17423
        val dialog = dialog

        if (dialog != null && retainInstance) {
            dialog.setDismissMessage(null)
        }

        super.onDestroyView()
    }

}