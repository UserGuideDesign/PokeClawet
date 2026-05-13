// Copyright 2026 PokeClaw (agents.io). All rights reserved.
// Licensed under the Apache License, Version 2.0.

package io.agents.pokeclaw.agent

/**
 * Cloud LLM provider and model definitions.
 * Used by LlmConfigActivity to render the provider tabs + model cards.
 *
 * UPGRADED: Added Groq, OpenRouter, Mistral, Together AI providers.
 * Total: 7 providers, 35+ models with tier badges.
 */

data class CloudModel(
    val id: String,
    val displayName: String,
    val inputPricePerM: Double,
    val outputPricePerM: Double,
    val tier: ModelTier,
    val contextSize: Int,
    val recommended: Boolean = false
)

enum class ModelTier(val stars: String, val label: String) {
    LITE("☆", "Lite"),       // cheapest / fastest
    FAST("★", "Fast"),       // good balance
    SMART("★★", "Smart"),   // higher quality
    PRO("★★★", "Pro")       // most powerful
}

enum class CloudProvider(
    val displayName: String,
    val defaultBaseUrl: String,
    val models: List<CloudModel>,
    val showBaseUrl: Boolean = false
) {
    OPENAI(
        displayName = "OpenAI",
        defaultBaseUrl = "https://api.openai.com/v1",
        models = listOf(
            CloudModel("gpt-4.1-nano",  "GPT-4.1 Nano  ⚡ Cheapest",  0.10,  0.40, ModelTier.LITE,  1_000_000),
            CloudModel("gpt-4o-mini",   "GPT-4o Mini  ★ Fast",         0.15,  0.60, ModelTier.FAST,  128_000, recommended = true),
            CloudModel("gpt-4.1-mini",  "GPT-4.1 Mini  ★ Latest fast", 0.40,  1.60, ModelTier.FAST,  1_000_000),
            CloudModel("gpt-4o",        "GPT-4o  ★★ Balanced",         2.50, 10.00, ModelTier.SMART, 128_000),
            CloudModel("gpt-4.1",       "GPT-4.1  ★★ Latest",          2.00,  8.00, ModelTier.SMART, 1_000_000),
            CloudModel("o4-mini",       "o4-mini  🧠 Fast reasoning",   1.10,  4.40, ModelTier.PRO,   200_000),
            CloudModel("o3",            "o3  🧠 Best reasoning",        10.0, 40.00, ModelTier.PRO,   200_000),
        )
    ),
    ANTHROPIC(
        displayName = "Anthropic",
        defaultBaseUrl = "https://api.anthropic.com/v1",
        models = listOf(
            CloudModel("claude-haiku-4-5",           "Claude Haiku 4.5  ⚡ Fastest",    0.80,  4.00, ModelTier.FAST,  200_000, recommended = true),
            CloudModel("claude-3-5-haiku-20241022",  "Claude 3.5 Haiku  ★ Fast",        0.80,  4.00, ModelTier.FAST,  200_000),
            CloudModel("claude-sonnet-4-5",          "Claude Sonnet 4.5  ★★ Balanced",  3.00, 15.00, ModelTier.SMART, 200_000),
            CloudModel("claude-3-7-sonnet-20250219", "Claude 3.7 Sonnet  🧠 Thinking",  3.00, 15.00, ModelTier.PRO,   200_000),
            CloudModel("claude-opus-4-5",            "Claude Opus 4.5  ★★★ Powerful",  15.00, 75.00, ModelTier.PRO,   200_000),
        )
    ),
    GOOGLE(
        displayName = "Google",
        defaultBaseUrl = "https://generativelanguage.googleapis.com/v1beta",
        models = listOf(
            CloudModel("gemini-2.0-flash-lite", "Gemini 2.0 Flash Lite  ⚡ Fastest",  0.075, 0.30, ModelTier.LITE,  1_000_000),
            CloudModel("gemini-2.0-flash",      "Gemini 2.0 Flash  ★ Fast",           0.10,  0.40, ModelTier.FAST,  1_000_000),
            CloudModel("gemini-2.5-flash",      "Gemini 2.5 Flash  ★★ Balanced",      0.15,  0.60, ModelTier.SMART, 1_000_000, recommended = true),
            CloudModel("gemini-2.5-pro",        "Gemini 2.5 Pro  ★★★ Best",           1.25, 10.00, ModelTier.PRO,   1_000_000),
        )
    ),
    // UPGRADED: New provider — Groq (ultra-fast inference via custom hardware)
    GROQ(
        displayName = "Groq  ⚡ Ultra-fast",
        defaultBaseUrl = "https://api.groq.com/openai/v1",
        models = listOf(
            CloudModel("llama-3.1-8b-instant",          "Llama 3.1 8B Instant  ⚡",      0.05,  0.08, ModelTier.LITE,  128_000, recommended = true),
            CloudModel("llama-3.3-70b-versatile",       "Llama 3.3 70B Versatile  ★★",  0.59,  0.79, ModelTier.SMART, 128_000),
            CloudModel("deepseek-r1-distill-llama-70b", "DeepSeek-R1 Distill 70B  🧠",  0.75,  0.99, ModelTier.PRO,   128_000),
            CloudModel("qwen-qwq-32b",                  "Qwen QwQ 32B  🧠 Reasoning",   0.29,  0.39, ModelTier.SMART, 128_000),
            CloudModel("gemma2-9b-it",                  "Gemma 2 9B  ★ Fast",           0.20,  0.20, ModelTier.FAST,  8_192),
        )
    ),
    // UPGRADED: New provider — OpenRouter (access 200+ models via one API)
    OPENROUTER(
        displayName = "OpenRouter  🌐 200+ models",
        defaultBaseUrl = "https://openrouter.ai/api/v1",
        models = listOf(
            CloudModel("meta-llama/llama-3.3-70b-instruct:free", "Llama 3.3 70B  🆓 Free",    0.00,  0.00, ModelTier.SMART, 128_000, recommended = true),
            CloudModel("google/gemma-3-27b-it:free",             "Gemma 3 27B  🆓 Free",      0.00,  0.00, ModelTier.FAST,  8_192),
            CloudModel("deepseek/deepseek-chat-v3-0324:free",    "DeepSeek V3  🆓 Free",      0.00,  0.00, ModelTier.SMART, 64_000),
            CloudModel("anthropic/claude-3.5-haiku",             "Claude 3.5 Haiku  ★",       0.80,  4.00, ModelTier.FAST,  200_000),
            CloudModel("openai/gpt-4o-mini",                     "GPT-4o mini  ★",            0.15,  0.60, ModelTier.FAST,  128_000),
            CloudModel("google/gemini-2.5-pro-preview",          "Gemini 2.5 Pro  ★★★",      1.25, 10.00, ModelTier.PRO,   1_000_000),
        ),
        showBaseUrl = true
    ),
    // UPGRADED: New provider — Mistral AI
    MISTRAL(
        displayName = "Mistral AI",
        defaultBaseUrl = "https://api.mistral.ai/v1",
        models = listOf(
            CloudModel("mistral-small-latest",  "Mistral Small  ★ Fast & cheap",  0.10,  0.30, ModelTier.FAST,  32_000, recommended = true),
            CloudModel("mistral-medium-latest", "Mistral Medium  ★★ Balanced",    0.40,  1.20, ModelTier.SMART, 32_000),
            CloudModel("mistral-large-latest",  "Mistral Large  ★★★ Powerful",    2.00,  6.00, ModelTier.PRO,   128_000),
            CloudModel("codestral-latest",      "Codestral  💻 Code specialist",  0.20,  0.60, ModelTier.SMART, 32_000),
        )
    ),
    // UPGRADED: New provider — Together AI
    TOGETHER(
        displayName = "Together AI",
        defaultBaseUrl = "https://api.together.xyz/v1",
        models = listOf(
            CloudModel("meta-llama/Llama-3.3-70B-Instruct-Turbo", "Llama 3.3 70B Turbo  ★★", 0.88, 0.88, ModelTier.SMART, 128_000, recommended = true),
            CloudModel("Qwen/Qwen2.5-72B-Instruct-Turbo",         "Qwen 2.5 72B Turbo  ★★",  1.20, 1.20, ModelTier.SMART, 32_000),
            CloudModel("deepseek-ai/DeepSeek-R1",                  "DeepSeek-R1 Full  🧠",    3.00, 7.00, ModelTier.PRO,   64_000),
        )
    ),
    CUSTOM(
        displayName = "Custom",
        defaultBaseUrl = "",
        models = emptyList(),
        showBaseUrl = true
    );

    companion object {
        /**
         * Find provider by name (case-insensitive).
         * Returns OPENAI as default.
         */
        fun fromName(name: String): CloudProvider {
            return entries.find { it.name.equals(name, ignoreCase = true) } ?: OPENAI
        }

        /**
         * Find the provider that contains a given model ID.
         */
        fun findProviderForModel(modelId: String): CloudProvider? {
            return entries.find { provider ->
                provider.models.any { it.id == modelId }
            }
        }

        /** All free models across all providers */
        fun freeModels(): List<Pair<CloudProvider, CloudModel>> =
            entries.flatMap { provider ->
                provider.models
                    .filter { it.inputPricePerM == 0.0 && it.outputPricePerM == 0.0 }
                    .map { provider to it }
            }
    }
}
