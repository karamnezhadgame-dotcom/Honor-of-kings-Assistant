package com.honorassistant.app.service

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class HOKTrackerService : AccessibilityService() {
    companion object {
        val enemyCooldowns = MutableLiveData<Map<String, Int>>()
        val goldDifference = MutableLiveData<Int>()
        val objectiveTimer = MutableLiveData<String>()
    }
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event?.source == null) return
        val root = event.source ?: return
        findNodesByText(root, "敌人")?.forEach { node ->
            val text = node.text?.toString() ?: return@forEach
            val heroName = Regex("敌人\\s*([\\u4e00-\\u9fa5]+)").find(text)?.groupValues?.get(1)
            val cooldown = Regex("(\\d+)s").find(text)?.groupValues?.get(1)?.toIntOrNull()
            if (heroName != null && cooldown != null) {
                val current = enemyCooldowns.value?.toMutableMap() ?: mutableMapOf()
                current[heroName] = cooldown
                enemyCooldowns.postValue(current)
            }
        }
    }
    private fun findNodesByText(node: AccessibilityNodeInfo, keyword: String): List<AccessibilityNodeInfo>? {
        val result = mutableListOf<AccessibilityNodeInfo>()
        findNodesByTextRecursive(node, keyword, result)
        return result.ifEmpty { null }
    }
    private fun findNodesByTextRecursive(node: AccessibilityNodeInfo, keyword: String, acc: MutableList<AccessibilityNodeInfo>) {
        if (node.text?.toString()?.contains(keyword, ignoreCase = true) == true) acc.add(node)
        for (i in 0 until node.childCount) {
            val child = node.getChild(i)
            if (child != null) findNodesByTextRecursive(child, keyword, acc)
        }
    }
    override fun onInterrupt() {
        enemyCooldowns.postValue(emptyMap())
        goldDifference.postValue(0)
        objectiveTimer.postValue("--:--")
    }
}
