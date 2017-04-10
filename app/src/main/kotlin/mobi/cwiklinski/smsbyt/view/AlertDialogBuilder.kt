package mobi.cwiklinski.smsbyt.view

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.database.Cursor
import android.graphics.drawable.Drawable
import android.support.v7.app.AlertDialog
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListAdapter

class AlertDialogBuilder(context: Context, theme: Int) : AlertDialog.Builder(context, theme) {

    private var autoDismiss = true

    private var negativeButtonText: CharSequence = ""

    private var negativeOnClickListener: DialogInterface.OnClickListener? = null

    private var neutralButtonText: CharSequence = ""

    private var neutralOnClickListener: DialogInterface.OnClickListener? = null

    private var onShowListener: DialogInterface.OnShowListener? = null

    private var positiveButtonText: CharSequence = ""

    private var positiveOnClickListener: DialogInterface.OnClickListener? = null

    private val blankOnClickListener = DialogInterface.OnClickListener { _, _ -> }

    override fun create(): AlertDialog {
        val alertDialog = super.create()
        alertDialog.setOnShowListener { dialog ->
            if (!autoDismiss) {
                val positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
                val negativeButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE)
                val neutralButton = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL)
                positiveButton.setOnClickListener {
                    if (positiveOnClickListener != null) {
                        positiveOnClickListener!!.onClick(alertDialog, DialogInterface.BUTTON_POSITIVE)
                    }
                }
                negativeButton.setOnClickListener {
                    if (negativeOnClickListener != null) {
                        negativeOnClickListener!!.onClick(alertDialog, DialogInterface.BUTTON_NEGATIVE)
                    }
                }
                neutralButton.setOnClickListener {
                    if (neutralOnClickListener != null) {
                        neutralOnClickListener!!.onClick(alertDialog, DialogInterface.BUTTON_NEUTRAL)
                    }
                }
            }
            if (onShowListener != null) {
                onShowListener!!.onShow(dialog)
            }
        }
        return alertDialog
    }

    override fun getContext(): Context {
        return super.getContext()
    }

    override fun setAdapter(adapter: ListAdapter, listener: DialogInterface.OnClickListener): AlertDialogBuilder {
        super.setAdapter(adapter, listener)
        return this
    }

    override fun setCancelable(cancelable: Boolean): AlertDialogBuilder {
        super.setCancelable(cancelable)
        return this
    }

    override fun setCursor(cursor: Cursor, listener: DialogInterface.OnClickListener, labelColumn: String): AlertDialogBuilder {
        super.setCursor(cursor, listener, labelColumn)
        return this
    }

    override fun setCustomTitle(customTitleView: View?): AlertDialogBuilder {
        super.setCustomTitle(customTitleView)
        return this
    }

    override fun setIcon(iconId: Int): AlertDialogBuilder {
        super.setIcon(iconId)
        return this
    }

    override fun setIcon(icon: Drawable?): AlertDialogBuilder {
        super.setIcon(icon)
        return this
    }

    override fun setIconAttribute(attrId: Int): AlertDialogBuilder {
        super.setIconAttribute(attrId)
        return this
    }

    override fun setItems(itemsId: Int, listener: DialogInterface.OnClickListener): AlertDialogBuilder {
        super.setItems(itemsId, listener)
        return this
    }

    override fun setItems(items: Array<CharSequence>, listener: DialogInterface.OnClickListener): AlertDialogBuilder {
        super.setItems(items, listener)
        return this
    }

    override fun setMessage(messageId: Int): AlertDialogBuilder {
        super.setMessage(messageId)
        return this
    }

    override fun setMessage(message: CharSequence?): AlertDialogBuilder {
        super.setMessage(message)
        return this
    }

    override fun setMultiChoiceItems(itemsId: Int, checkedItems: BooleanArray, listener: DialogInterface.OnMultiChoiceClickListener): AlertDialogBuilder {
        super.setMultiChoiceItems(itemsId, checkedItems, listener)
        return this
    }

    override fun setMultiChoiceItems(items: Array<CharSequence>, checkedItems: BooleanArray,
                                     listener: DialogInterface.OnMultiChoiceClickListener): AlertDialogBuilder {
        super.setMultiChoiceItems(items, checkedItems, listener)
        return this
    }

    override fun setMultiChoiceItems(cursor: Cursor, isCheckedColumn: String, labelColumn: String,
                                     listener: DialogInterface.OnMultiChoiceClickListener): AlertDialogBuilder {
        super.setMultiChoiceItems(cursor, isCheckedColumn, labelColumn, listener)
        return this
    }

    override fun setNegativeButton(textId: Int, listener: DialogInterface.OnClickListener): AlertDialogBuilder {
        super.setNegativeButton(textId, listener)
        negativeButtonText = context.getText(textId)
        negativeOnClickListener = listener
        return this
    }

    override fun setNegativeButton(text: CharSequence, listener: DialogInterface.OnClickListener): AlertDialogBuilder {
        super.setNegativeButton(text, listener)
        negativeButtonText = text
        negativeOnClickListener = listener
        return this
    }

    override fun setNeutralButton(textId: Int, listener: DialogInterface.OnClickListener): AlertDialogBuilder {
        super.setNeutralButton(textId, listener)
        neutralButtonText = context.getText(textId)
        neutralOnClickListener = listener
        return this
    }

    override fun setNeutralButton(text: CharSequence, listener: DialogInterface.OnClickListener): AlertDialogBuilder {
        super.setNeutralButton(text, listener)
        neutralButtonText = text
        neutralOnClickListener = listener
        return this
    }

    override fun setOnCancelListener(onCancelListener: DialogInterface.OnCancelListener): AlertDialogBuilder {
        super.setOnCancelListener(onCancelListener)
        return this
    }

    override fun setOnDismissListener(onDismissListener: DialogInterface.OnDismissListener): AlertDialogBuilder {
        super.setOnDismissListener(onDismissListener)
        return this
    }

    override fun setOnItemSelectedListener(listener: AdapterView.OnItemSelectedListener): AlertDialogBuilder {
        super.setOnItemSelectedListener(listener)
        return this
    }

    override fun setOnKeyListener(onKeyListener: DialogInterface.OnKeyListener): AlertDialogBuilder {
        super.setOnKeyListener(onKeyListener)
        return this
    }

    override fun setPositiveButton(textId: Int, listener: DialogInterface.OnClickListener): AlertDialogBuilder {
        super.setPositiveButton(textId, listener)
        positiveButtonText = context.getText(textId)
        positiveOnClickListener = listener
        return this
    }

    override fun setPositiveButton(text: CharSequence, listener: DialogInterface.OnClickListener): AlertDialogBuilder {
        super.setPositiveButton(text, listener)
        positiveButtonText = text
        positiveOnClickListener = listener
        return this
    }

    override fun setRecycleOnMeasureEnabled(enabled: Boolean): AlertDialogBuilder {
        super.setRecycleOnMeasureEnabled(enabled)
        return this
    }

    override fun setSingleChoiceItems(itemsId: Int, checkedItem: Int, listener: DialogInterface.OnClickListener): AlertDialogBuilder {
        super.setSingleChoiceItems(itemsId, checkedItem, listener)
        return this
    }

    override fun setSingleChoiceItems(cursor: Cursor, checkedItem: Int, labelColumn: String,
                                      listener: DialogInterface.OnClickListener): AlertDialogBuilder {
        super.setSingleChoiceItems(cursor, checkedItem, labelColumn, listener)
        return this
    }

    override fun setSingleChoiceItems(items: Array<CharSequence>, checkedItem: Int, listener: DialogInterface.OnClickListener): AlertDialogBuilder {
        super.setSingleChoiceItems(items, checkedItem, listener)
        return this
    }

    override fun setSingleChoiceItems(adapter: ListAdapter, checkedItem: Int, listener: DialogInterface.OnClickListener): AlertDialogBuilder {
        super.setSingleChoiceItems(adapter, checkedItem, listener)
        return this
    }

    override fun setTitle(titleId: Int): AlertDialogBuilder {
        super.setTitle(titleId)
        return this
    }

    override fun setTitle(title: CharSequence?): AlertDialogBuilder {
        super.setTitle(title)
        return this
    }

    override fun setView(layoutResId: Int): AlertDialogBuilder {
        super.setView(layoutResId)
        return this
    }

    override fun setView(view: View): AlertDialogBuilder {
        super.setView(view)
        return this
    }

    override fun show(): AlertDialog {
        return super.show()
    }

    fun setAutoDismiss(autoDismiss: Boolean): AlertDialogBuilder {
        this.autoDismiss = autoDismiss
        return this
    }

    fun setNegativeButton(textId: Int): AlertDialogBuilder {
        setNegativeButton(textId, blankOnClickListener)
        return this
    }

    fun setNegativeButton(text: CharSequence): AlertDialogBuilder {
        setNegativeButton(text, blankOnClickListener)
        return this
    }

    fun setNeutralButton(textId: Int): AlertDialogBuilder {
        setNeutralButton(textId, blankOnClickListener)
        return this
    }

    fun setNeutralButton(text: CharSequence): AlertDialogBuilder {
        setNeutralButton(text, blankOnClickListener)
        return this
    }

    fun setOnNegativeClickListener(negativeOnClickListener: DialogInterface.OnClickListener): AlertDialogBuilder {
        this.negativeOnClickListener = negativeOnClickListener
        setNegativeButton(negativeButtonText, negativeOnClickListener)
        return this
    }

    fun setOnNeutralClickListener(neutralOnClickListener: DialogInterface.OnClickListener): AlertDialogBuilder {
        this.neutralOnClickListener = neutralOnClickListener
        setNeutralButton(neutralButtonText, neutralOnClickListener)
        return this
    }

    fun setOnPositiveClickListener(positiveOnClickListener: DialogInterface.OnClickListener): AlertDialogBuilder {
        this.positiveOnClickListener = positiveOnClickListener
        setPositiveButton(positiveButtonText, positiveOnClickListener)
        return this
    }

    fun setOnShowListener(onShowListener: DialogInterface.OnShowListener): AlertDialogBuilder {
        this.onShowListener = onShowListener
        return this
    }

    fun setPositiveButton(text: CharSequence): AlertDialogBuilder {
        setPositiveButton(text, blankOnClickListener)
        return this
    }

    fun setPositiveButton(textId: Int): AlertDialogBuilder {
        setPositiveButton(textId, blankOnClickListener)
        return this
    }
}

fun Dialog.fillScreen() : Dialog {
    window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    return this
}

fun Dialog.showFull() {
    show()
    window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
}