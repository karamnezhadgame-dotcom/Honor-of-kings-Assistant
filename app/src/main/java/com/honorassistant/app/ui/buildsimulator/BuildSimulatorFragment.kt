package com.honorassistant.app.ui.buildsimulator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.honorassistant.app.R
import com.honorassistant.app.data.models.Item
import com.honorassistant.app.databinding.FragmentBuildSimulatorBinding
import com.honorassistant.app.ui.buildsimulator.adapters.ItemAdapter

class BuildSimulatorFragment : Fragment() {

    private var _binding: FragmentBuildSimulatorBinding? = null
    private val binding get() = _binding!!

    private val slots = arrayOfNulls<Item>(6)
    private lateinit var slotViews: List<ImageView>

    private val mockItems = listOf(
        Item("i1", "影忍之足", "", 1700, "移速+60 物攻+60", "鞋子"),
        Item("i2", "破军", "", 3100, "物攻+160 暴击+25%", "输出"),
        Item("i3", "无尽之刃", "", 3400, "物攻+180 暴击+30%", "输出"),
        Item("i4", "追命剑", "", 2800, "物攻+120 穿甲+20%", "输出"),
        Item("i5", "回响之杖", "", 3000, "法强+150 冷却-15%", "法术"),
        Item("i6", "法师之靴", "", 1700, "移速+60 法强+45", "鞋子"),
        Item("i7", "日暮之流", "", 3200, "法强+175 法穿+40", "法术"),
        Item("i8", "铁甲战衣", "", 2600, "护甲+250 生命+800", "防御"),
        Item("i9", "聚气千寻铠", "", 2800, "法抗+200 生命+1200", "防御"),
        Item("i10", "不死鸟之誓", "", 3000, "生命+2200 护甲+100", "防御"),
        Item("i11", "制裁圣徽", "", 3200, "生命+900 法抗+180", "防御"),
        Item("i12", "极寒风暴", "", 3100, "物攻+90 攻速+40%", "输出")
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentBuildSimulatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        slotViews = listOf(
            binding.slot1, binding.slot2, binding.slot3,
            binding.slot4, binding.slot5, binding.slot6
        )

        // Long press to clear a slot
        slotViews.forEachIndexed { index, imageView ->
            imageView.setOnLongClickListener {
                slots[index] = null
                imageView.setImageResource(R.drawable.ic_slot_empty)
                updateStats()
                true
            }
        }

        val adapter = ItemAdapter { item -> addItemToSlot(item) }
        binding.rvItems.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvItems.adapter = adapter
        adapter.submitList(mockItems)

        updateStats()
    }

    private fun addItemToSlot(item: Item) {
        val emptyIndex = slots.indexOfFirst { it == null }
        if (emptyIndex == -1) {
            Toast.makeText(requireContext(), "装备栏已满！长按槽位清除装备", Toast.LENGTH_SHORT).show()
            return
        }
        slots[emptyIndex] = item
        slotViews[emptyIndex].setImageResource(R.drawable.ic_item_placeholder)
        slotViews[emptyIndex].contentDescription = item.name
        updateStats()
        Toast.makeText(requireContext(), "已装备：${item.name}", Toast.LENGTH_SHORT).show()
    }

    private fun updateStats() {
        val totalGold = slots.filterNotNull().sumOf { it.price }
        val statsSummary = slots.filterNotNull()
            .joinToString("\n") { "• ${it.name}（${it.stats}）" }
            .ifEmpty { "尚未选择装备" }
        binding.tvTotalGold.text = "总费用：${totalGold}金币"
        binding.tvStatsSummary.text = statsSummary
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
