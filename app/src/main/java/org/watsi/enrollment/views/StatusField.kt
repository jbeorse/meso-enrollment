package org.watsi.enrollment.views

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.view_status_field.view.status_key
import kotlinx.android.synthetic.main.view_status_field.view.status_value
import org.watsi.enrollment.R

class StatusField @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_status_field, this, true)
        val customAttributes = context.obtainStyledAttributes(attrs, R.styleable.StatusField)
        status_key.text = customAttributes.getString(R.styleable.StatusField_android_text)
        customAttributes.recycle()
    }

    fun setValue(value: String?) {
        status_value.text = value
    }
}
