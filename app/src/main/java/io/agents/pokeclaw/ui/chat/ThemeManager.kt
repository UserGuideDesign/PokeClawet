// Copyright 2026 PokeClaw (agents.io). All rights reserved.
// Licensed under the Apache License, Version 2.0.

package io.agents.pokeclaw.ui.chat

import android.graphics.Color
import io.agents.pokeclaw.utils.KVUtils
import androidx.compose.ui.graphics.Color as ComposeColor

/**
 * Runtime theme color provider.
 * Reads saved theme ID from KVUtils and returns the appropriate colors.
 * UPGRADED: Added Cyberpunk, OLED Black, and Forest themes.
 */
object ThemeManager {

    data class ChatColors(
        val bg: Int,
        val toolbarBg: Int,
        val userBubble: Int,
        val userText: Int,
        val aiBubble: Int,
        val aiBubbleBorder: Int,
        val aiText: Int,
        val avatarBg: Int,
        val inputBorder: Int,
        val sendColor: Int,
        val toolOk: Int,
        val toolDefault: Int,
        val divider: Int,
        // NEW semantic colors for upgraded UI
        val toolCardBg: Int,
        val toolCardBorder: Int,
        val toolCardText: Int,
        val tierFast: Int,
        val tierBalanced: Int,
        val tierPowerful: Int,
        val streamingPulse: Int,
        val errorColor: Int,
        val successColor: Int,
        val successBg: Int,
        val warningColor: Int,
        val warningBg: Int,
        val infoColor: Int,
        val infoBg: Int,
    )

    val themes = mapOf(
        "classic_dark" to ChatColors(
            bg = Color.parseColor("#0D1117"), toolbarBg = Color.parseColor("#161B22"),
            userBubble = Color.parseColor("#1F4E9E"), userText = Color.parseColor("#E6EDF3"),
            aiBubble = Color.parseColor("#21262D"), aiBubbleBorder = Color.parseColor("#30363D"),
            aiText = Color.parseColor("#E6EDF3"), avatarBg = Color.parseColor("#1F4E9E"),
            inputBorder = Color.parseColor("#2A2A2A"), sendColor = Color.parseColor("#6B9EFF"),
            toolOk = Color.parseColor("#3FB950"), toolDefault = Color.parseColor("#8B949E"),
            divider = Color.parseColor("#30363D"),
            toolCardBg = Color.parseColor("#1A2340"), toolCardBorder = Color.parseColor("#2D3F7A"),
            toolCardText = Color.parseColor("#93AAFF"),
            tierFast = Color.parseColor("#3FB950"), tierBalanced = Color.parseColor("#6B9EFF"),
            tierPowerful = Color.parseColor("#BC8CFF"), streamingPulse = Color.parseColor("#6B9EFF"),
            errorColor = Color.parseColor("#F85149"), successColor = Color.parseColor("#3FB950"),
            successBg = Color.parseColor("#0D2B1A"), warningColor = Color.parseColor("#D29922"),
            warningBg = Color.parseColor("#2B1D0A"), infoColor = Color.parseColor("#38BDF8"),
            infoBg = Color.parseColor("#0C4A6E"),
        ),
        "classic_light" to ChatColors(
            bg = Color.parseColor("#F8F9FC"), toolbarBg = Color.parseColor("#F0F2F8"),
            userBubble = Color.parseColor("#3B6FE8"), userText = Color.parseColor("#FFFFFF"),
            aiBubble = Color.parseColor("#FFFFFF"), aiBubbleBorder = Color.parseColor("#E2E8F0"),
            aiText = Color.parseColor("#0D1117"), avatarBg = Color.parseColor("#3B6FE8"),
            inputBorder = Color.parseColor("#D1D9E6"), sendColor = Color.parseColor("#3B6FE8"),
            toolOk = Color.parseColor("#16A34A"), toolDefault = Color.parseColor("#4A5568"),
            divider = Color.parseColor("#D1D9E6"),
            toolCardBg = Color.parseColor("#EEF2FF"), toolCardBorder = Color.parseColor("#C7D2FE"),
            toolCardText = Color.parseColor("#3730A3"),
            tierFast = Color.parseColor("#059669"), tierBalanced = Color.parseColor("#3B6FE8"),
            tierPowerful = Color.parseColor("#7C3AED"), streamingPulse = Color.parseColor("#3B6FE8"),
            errorColor = Color.parseColor("#DC2626"), successColor = Color.parseColor("#16A34A"),
            successBg = Color.parseColor("#DCFCE7"), warningColor = Color.parseColor("#D97706"),
            warningBg = Color.parseColor("#FEF3C7"), infoColor = Color.parseColor("#0284C7"),
            infoBg = Color.parseColor("#0C4A6E"),
        ),
        // UPGRADED: New Theme - Cyberpunk
        "cyberpunk_dark" to ChatColors(
            bg = Color.parseColor("#0A001A"), toolbarBg = Color.parseColor("#1A0033"),
            userBubble = Color.parseColor("#FF00FF"), userText = Color.parseColor("#FFFFFF"),
            aiBubble = Color.parseColor("#003333"), aiBubbleBorder = Color.parseColor("#006666"),
            aiText = Color.parseColor("#00FF00"), avatarBg = Color.parseColor("#00FFFF"),
            inputBorder = Color.parseColor("#CC00FF"), sendColor = Color.parseColor("#FFFF00"),
            toolOk = Color.parseColor("#FF8C00"), toolDefault = Color.parseColor("#6699CC"),
            divider = Color.parseColor("#330033"),
            toolCardBg = Color.parseColor("#220044"), toolCardBorder = Color.parseColor("#6600CC"),
            toolCardText = Color.parseColor("#CCFFFF"),
            tierFast = Color.parseColor("#00FF00"), tierBalanced = Color.parseColor("#00FFFF"),
            tierPowerful = Color.parseColor("#FF00FF"), streamingPulse = Color.parseColor("#FFFF00"),
            errorColor = Color.parseColor("#FF3333"), successColor = Color.parseColor("#00FF00"),
            successBg = Color.parseColor("#001A00"), warningColor = Color.parseColor("#FFCC00"),
            warningBg = Color.parseColor("#332200"), infoColor = Color.parseColor("#00FFFF"),
            infoBg = Color.parseColor("#002222"),
        ),
        // UPGRADED: New Theme - OLED Black
        "oled_black" to ChatColors(
            bg = Color.parseColor("#000000"), toolbarBg = Color.parseColor("#0A0A0A"),
            userBubble = Color.parseColor("#004488"), userText = Color.parseColor("#FFFFFF"),
            aiBubble = Color.parseColor("#1A1A1A"), aiBubbleBorder = Color.parseColor("#333333"),
            aiText = Color.parseColor("#CCCCCC"), avatarBg = Color.parseColor("#223344"),
            inputBorder = Color.parseColor("#222222"), sendColor = Color.parseColor("#66CCFF"),
            toolOk = Color.parseColor("#00CC00"), toolDefault = Color.parseColor("#666666"),
            divider = Color.parseColor("#111111"),
            toolCardBg = Color.parseColor("#050505"), toolCardBorder = Color.parseColor("#1A1A1A"),
            toolCardText = Color.parseColor("#999999"),
            tierFast = Color.parseColor("#00CC00"), tierBalanced = Color.parseColor("#66CCFF"),
            tierPowerful = Color.parseColor("#CC66FF"), streamingPulse = Color.parseColor("#66CCFF"),
            errorColor = Color.parseColor("#FF4444"), successColor = Color.parseColor("#00CC00"),
            successBg = Color.parseColor("#001100"), warningColor = Color.parseColor("#FFBB00"),
            warningBg = Color.parseColor("#111100"), infoColor = Color.parseColor("#66CCFF"),
            infoBg = Color.parseColor("#002244"),
        ),
        // UPGRADED: New Theme - Forest
        "forest_light" to ChatColors(
            bg = Color.parseColor("#F0F8EE"), toolbarBg = Color.parseColor("#E0EAE0"),
            userBubble = Color.parseColor("#228B22"), userText = Color.parseColor("#FFFFFF"),
            aiBubble = Color.parseColor("#F5F5DC"), aiBubbleBorder = Color.parseColor("#D0D8C0"),
            aiText = Color.parseColor("#4A3A2A"), avatarBg = Color.parseColor("#6B8E23"),
            inputBorder = Color.parseColor("#C0C8B0"), sendColor = Color.parseColor("#3CB371"),
            toolOk = Color.parseColor("#2E8B57"), toolDefault = Color.parseColor("#808060"),
            divider = Color.parseColor("#C0C8B0"),
            toolCardBg = Color.parseColor("#E8F0E0"), toolCardBorder = Color.parseColor("#B0C0A0"),
            toolCardText = Color.parseColor("#336633"),
            tierFast = Color.parseColor("#2E8B57"), tierBalanced = Color.parseColor("#6B8E23"),
            tierPowerful = Color.parseColor("#8B4513"), streamingPulse = Color.parseColor("#3CB371"),
            errorColor = Color.parseColor("#CC3333"), successColor = Color.parseColor("#2E8B57"),
            successBg = Color.parseColor("#E0F0E0"), warningColor = Color.parseColor("#DDAA00"),
            warningBg = Color.parseColor("#FFF8E0"), infoColor = Color.parseColor("#4682B4"),
            infoBg = Color.parseColor("#E0F0F8"),
        ),
    )

    fun getColors(): ChatColors {
        val id = KVUtils.getString("THEME_ID", "classic_dark") // Default to classic_dark
        return themes[id] ?: themes["classic_dark"]!!
    }

    fun ChatColors.toComposeColors(): PokeclawColors {
        val dark = isDark()
        return PokeclawColors(
            background = ComposeColor(bg),
            surface = ComposeColor(toolbarBg),
            userBubble = ComposeColor(userBubble),
            userText = ComposeColor(userText),
            aiBubble = ComposeColor(aiBubble),
            aiBubbleBorder = ComposeColor(aiBubbleBorder),
            aiText = ComposeColor(aiText),
            avatar = ComposeColor(avatarBg),
            accent = ComposeColor(sendColor),
            textPrimary = if (dark) ComposeColor(0xFFE6EDF3.toInt()) else ComposeColor(0xFF0D1117.toInt()),
            textSecondary = if (dark) ComposeColor(0xFF8B949E.toInt()) else ComposeColor(0xFF4A5568.toInt()),
            textTertiary = if (dark) ComposeColor(0xFF6E7681.toInt()) else ComposeColor(0xFF8896A8.toInt()),
            divider = ComposeColor(divider),
            inputBorder = ComposeColor(inputBorder),
            toolCardBg = ComposeColor(toolCardBg),
            toolCardBorder = ComposeColor(toolCardBorder),
            toolCardText = ComposeColor(toolCardText),
            tierFast = ComposeColor(tierFast),
            tierBalanced = ComposeColor(tierBalanced),
            tierPowerful = ComposeColor(tierPowerful),
            streamingPulse = ComposeColor(streamingPulse),
            errorColor = ComposeColor(errorColor),
            successColor = ComposeColor(successColor),
            successBg = ComposeColor(successBg),
            warningColor = ComposeColor(warningColor),
            warningBg = ComposeColor(warningBg),
            infoColor = ComposeColor(infoColor),
            infoBg = ComposeColor(infoBg),
        )
    }

    fun isDark(): Boolean {
        val id = KVUtils.getString("THEME_ID", "classic_dark")
        return id.endsWith("_dark") || id == "oled_black"
    }

    fun getAllThemes(): List<Pair<String, String>> {
        return themes.keys.map { id ->
            val name = id.replace("_", " ").split(" ").joinToString(" ") { it.capitalize() }
            id to name
        }.sortedBy { it.second }
    }
}

// Add PokeclawColors data class to app/src/main/java/io/agents/pokeclaw/ui/theme/Theme.kt
// (or wherever PokeclawColors is defined, likely in the same package as ChatScreen.kt)
// Ensure it includes all the new semantic colors:
/*
data class PokeclawColors(
    val background: ComposeColor,
    val surface: ComposeColor,
    val userBubble: ComposeColor,
    val userText: ComposeColor,
    val aiBubble: ComposeColor,
    val aiBubbleBorder: ComposeColor,
    val aiText: ComposeColor,
    val avatar: ComposeColor,
    val accent: ComposeColor,
    val textPrimary: ComposeColor,
    val textSecondary: ComposeColor,
    val textTertiary: ComposeColor,
    val divider: ComposeColor,
    val inputBorder: ComposeColor,
    val toolCardBg: ComposeColor,
    val toolCardBorder: ComposeColor,
    val toolCardText: ComposeColor,
    val tierFast: ComposeColor,
    val tierBalanced: ComposeColor,
    val tierPowerful: ComposeColor,
    val streamingPulse: ComposeColor,
    val errorColor: ComposeColor,
    val successColor: ComposeColor,
    val successBg: ComposeColor,
    val warningColor: ComposeColor,
    val warningBg: ComposeColor,
    val infoColor: ComposeColor,
    val infoBg: ComposeColor,
)
*/
