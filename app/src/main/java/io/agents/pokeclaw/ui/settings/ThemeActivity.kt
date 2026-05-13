// Copyright 2026 PokeClaw (agents.io). All rights reserved.
// Licensed under the Apache License, Version 2.0.

package io.agents.pokeclaw.ui.settings

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import io.agents.pokeclaw.R
import io.agents.pokeclaw.base.BaseActivity
import io.agents.pokeclaw.utils.KVUtils
import io.agents.pokeclaw.widget.CommonToolbar

class ThemeActivity : BaseActivity() {

    data class ThemeConfig(
        val id: String,
        val name: String,
        val isDark: Boolean,
        val bg: Int,
        val userBubble: Int,
        val aiBubble: Int,
        val avatar: Int,
        val inputBar: Int,
        val accent: Int,
        val previewImageResId: Int = 0 // UPGRADED: Add resource ID for theme preview image
    )

    // Only expose ember (brand color). Other themes kept in ThemeManager for future use.
    // UPGRADED: Expanded theme list to include new themes
    private val themes = listOf(
        ThemeConfig("classic_dark", "Classic Dark", true, Color.parseColor("#0D1117"), Color.parseColor("#1F4E9E"), Color.parseColor("#21262D"), Color.parseColor("#1F4E9E"), Color.parseColor("#2A2A2A"), Color.parseColor("#6B9EFF"), R.drawable.theme_preview_classic_dark),
        ThemeConfig("classic_light", "Classic Light", false, Color.parseColor("#F8F9FC"), Color.parseColor("#3B6FE8"), Color.parseColor("#FFFFFF"), Color.parseColor("#3B6FE8"), Color.parseColor("#D1D9E6"), Color.parseColor("#3B6FE8"), R.drawable.theme_preview_classic_light),
        ThemeConfig("cyberpunk_dark", "Cyberpunk", true, Color.parseColor("#0A001A"), Color.parseColor("#FF00FF"), Color.parseColor("#003333"), Color.parseColor("#00FFFF"), Color.parseColor("#CC00FF"), Color.parseColor("#FFFF00"), R.drawable.theme_preview_cyberpunk_dark),
        ThemeConfig("oled_black", "OLED Black", true, Color.parseColor("#000000"), Color.parseColor("#004488"), Color.parseColor("#1A1A1A"), Color.parseColor("#223344"), Color.parseColor("#222222"), Color.parseColor("#66CCFF"), R.drawable.theme_preview_oled_black),
        ThemeConfig("forest_light", "Forest", false, Color.parseColor("#F0F8EE"), Color.parseColor("#228B22"), Color.parseColor("#F5F5DC"), Color.parseColor("#6B8E23"), Color.parseColor("#C0C8B0"), Color.parseColor("#3CB371"), R.drawable.theme_preview_forest_light),
    )

    private var selectedThemeId = "ember_dark"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tc = io.agents.pokeclaw.ui.chat.ThemeManager.getColors()
        window.statusBarColor = tc.toolbarBg
        window.decorView.setBackgroundColor(tc.bg)

        setContentView(R.layout.activity_theme)

        val contentFrame = findViewById<android.view.ViewGroup>(android.R.id.content)
        contentFrame?.setBackgroundColor(tc.bg)
        (contentFrame?.getChildAt(0) as? android.view.View)?.setBackgroundColor(tc.bg)

        findViewById<CommonToolbar>(R.id.toolbar).apply {
            setTitle("Appearance")
            setTitleColor(tc.aiText)
            setBackgroundColor(tc.toolbarBg)
            showBackButton(true) { finish() }
            findViewById<android.widget.ImageView>(R.id.ivBack)?.setColorFilter(tc.aiText)
        }
        findViewById<TextView>(R.id.tvCurrentTheme)?.setTextColor(tc.aiText)

        selectedThemeId = KVUtils.getString("THEME_ID", "classic_dark") // UPGRADED: Default to classic_dark

        val themeContainer = findViewById<LinearLayout>(R.id.themePreviewContainer) // UPGRADED: Use a container for dynamic theme previews
        themeContainer.removeAllViews() // Clear existing views

        themes.forEach { theme ->
            val themePreviewView = layoutInflater.inflate(R.layout.item_theme_preview, themeContainer, false) // UPGRADED: Inflate new item_theme_preview layout
            setupThemePreview(themePreviewView, theme)
            themeContainer.addView(themePreviewView)
        }

        updateSelection()
    }

    private fun setupThemePreview(view: View, theme: ThemeConfig) {
        val card = view.findViewById<LinearLayout>(R.id.cardPreview)
        val name = view.findViewById<TextView>(R.id.tvThemeName)
        val previewImage = view.findViewById<android.widget.ImageView>(R.id.ivThemePreview) // UPGRADED: ImageView for theme preview

        // Card background
        val cardBg = GradientDrawable().apply {
            setColor(theme.bg)
            cornerRadius = dp(12f)
        }
        card.background = cardBg

        // UPGRADED: Load preview image instead of drawing individual elements
        if (theme.previewImageResId != 0) {
            previewImage.setImageResource(theme.previewImageResId)
            previewImage.visibility = View.VISIBLE
            // Hide old individual preview elements
            view.findViewById<View>(R.id.previewUserBubble)?.visibility = View.GONE
            view.findViewById<View>(R.id.previewUserBubble2)?.visibility = View.GONE
            view.findViewById<View>(R.id.previewAiBubble)?.visibility = View.GONE
            view.findViewById<View>(R.id.previewAvatar)?.visibility = View.GONE
            view.findViewById<View>(R.id.previewInputBar)?.visibility = View.GONE
        } else {
            previewImage.visibility = View.GONE
            // Fallback to drawing individual elements if no image provided
            val userBubble = view.findViewById<View>(R.id.previewUserBubble)
            val userBubble2 = view.findViewById<View>(R.id.previewUserBubble2)
            val aiBubble = view.findViewById<View>(R.id.previewAiBubble)
            val avatar = view.findViewById<View>(R.id.previewAvatar)
            val inputBar = view.findViewById<View>(R.id.previewInputBar)

            userBubble?.background = roundRect(theme.userBubble, 8f)
            userBubble2?.background = roundRect(theme.userBubble, 8f)
            aiBubble?.background = roundRect(theme.aiBubble, 8f)
            avatar?.background = oval(theme.avatar)
            inputBar?.background = GradientDrawable().apply {
                setColor(Color.TRANSPARENT)
                setStroke(dp(1).toInt(), theme.inputBar)
                cornerRadius = dp(6f)
            }
        }

        name.text = theme.name

        view.setOnClickListener {
            selectedThemeId = theme.id
            KVUtils.putString("THEME_ID", theme.id)

            // Use system uimode command (works on MIUI where AppCompatDelegate doesn't)
            try {
                val mode = if (theme.isDark) "yes" else "no"
                Runtime.getRuntime().exec(arrayOf("cmd", "uimode", "night", mode))
            } catch (_: Exception) {
                // Fallback to AppCompatDelegate
                val newMode = if (theme.isDark) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
                AppCompatDelegate.setDefaultNightMode(newMode)
            }

            // Restart app to apply theme everywhere
            val intent = packageManager.getLaunchIntentForPackage(packageName)
            intent?.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP or android.content.Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finishAffinity()
        }
    }

    private fun updateSelection() {
        val themeContainer = findViewById<LinearLayout>(R.id.themePreviewContainer)
        for (i in 0 until themeContainer.childCount) {
            val view = themeContainer.getChildAt(i)
            val theme = themes[i]
            val indicator = view.findViewById<View>(R.id.selectedIndicator)
            val isSelected = theme.id == selectedThemeId

            if (isSelected) {
                indicator.visibility = View.VISIBLE
                indicator.background = roundRect(theme.accent, 2f)
            } else {
                indicator.visibility = View.INVISIBLE
            }
        }

        val current = themes.find { it.id == selectedThemeId }
        val label = current?.name ?: selectedThemeId
        findViewById<TextView>(R.id.tvCurrentTheme).text = "Current: $label"
    }

    private fun roundRect(color: Int, radius: Float) = GradientDrawable().apply {
        setColor(color)
        cornerRadius = dp(radius)
    }

    private fun oval(color: Int) = GradientDrawable().apply {
        shape = GradientDrawable.OVAL
        setColor(color)
    }

    private fun dp(v: Float): Float = v * resources.displayMetrics.density
    private fun dp(v: Int): Float = v * resources.displayMetrics.density
}
