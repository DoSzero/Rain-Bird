package com.rabb.rainbird.util

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment

class DialogsUtils : AppCompatDialogFragment() {

    private var listenerYes: YesDialogListener? = null
    private var listenerNo: NoDialogListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder
            .setTitle("Внимание!")
            .setMessage("Вы,продолжите без звука. Приятной Вам игры !!")

        return builder.create()
    }

    interface YesDialogListener {
        fun onYesClicked()
    }

    interface NoDialogListener {
        fun onNoClicked()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listenerYes = try {
            context as YesDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + "must implement ExampleDialogListener")
        }
    }
}