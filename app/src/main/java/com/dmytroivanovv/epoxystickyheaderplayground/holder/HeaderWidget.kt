package com.dmytroivanovv.epoxystickyheaderplayground.holder

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.google.android.material.textview.MaterialTextView
import kotlin.math.roundToInt

data class HeaderVO(
    val id: String,
    val name: String
)

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class HeaderWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialTextView(context, attrs, defStyleAttr) {

    init {
        gravity = Gravity.CENTER
        setBackgroundColor(Color.parseColor("#D6CFC7"))
    }

    @ModelProp
    fun viewObject(viewObject: HeaderVO) {
        text = viewObject.name
    }

    @AfterPropsSet
    fun onPropsSet() {
        updateLayoutParams<ViewGroup.LayoutParams> {
            height = (30 * resources.displayMetrics.density).roundToInt()
        }
    }
}