package com.example.iicnmademo.ui.common.loadstate

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.view.isVisible
import com.example.iicnmademo.databinding.LayoutPageLoadStateBinding

class PageLoadStateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @Suppress("UNUSED_PARAMETER") @AttrRes defStyleAttr: Int = 0,
    @Suppress("UNUSED_PARAMETER") @StyleRes defStyleRes: Int = 0,
) : FrameLayout(context, attrs) {

    private val binding = LayoutPageLoadStateBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    fun hide() {
        binding.root.isVisible = false
    }

    fun showErrorMessage(message: String, isRetryButtonVisible: Boolean = true) = with(binding) {
        root.isVisible = true
        progressBar.isVisible = false
        retryButton.isVisible = isRetryButtonVisible
        messageTextView.apply {
            isVisible = true
            text = message
        }
    }

    fun hideErrorMessage() = with(binding) {
        retryButton.isVisible = false
        messageTextView.isVisible = true
    }

    var isLoadingVisible: Boolean = false
        set(value) {
            if (value) {
                showLoading()
            } else {
                hideLoading()
            }
            field = value
        }

    private fun showLoading() = with(binding) {
        root.isVisible = true
        progressBar.isVisible = true
        retryButton.isVisible = false
        messageTextView.isVisible = false
    }

    private fun hideLoading() = with(binding) {
        progressBar.isVisible = false
    }

    fun setOnRetryListener(listener: (View) -> Unit) {
        binding.retryButton.setOnClickListener(listener)
    }
}