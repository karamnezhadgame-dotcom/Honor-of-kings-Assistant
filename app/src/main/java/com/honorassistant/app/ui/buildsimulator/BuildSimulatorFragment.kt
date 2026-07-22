package com.honorassistant.app.ui.buildsimulator

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.honorassistant.app.R
import com.honorassistant.app.databinding.FragmentBuildSimulatorBinding
import com.honorassistant.app.databinding.ItemBuildSlotBinding
import com.honorassistant.app.data.models.Item
import com.honorassistant.app.ui.buildsimulator.adapters.ItemAdapter

class BuildSimulatorFragment : Fragment() {
    private var _binding: FragmentBuildSimulatorBinding? = null
    private val binding get() = _binding!!
    private val slotImages = arrayOfNulls<ImageView>(6)
    private val slotItems = arrayOfNulls<Item?>(6)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentBuildSimulatorBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val grid = binding.glSlots
        for (i in 0 until 6) {
            val slotBinding = ItemBuildSlotBinding.inflate(layoutInflater, grid, false)
            slotBinding.root.setOnDragListener(SlotDragListener(i))
            grid.addView(slotBinding.root)
            slotImages[i] = slotBinding.ivSlot
            slotBinding.ivSlot.setImageResource(R.drawable.ic_slot_empty)
        }
        val adapter = ItemAdapter { item ->
            val dragView = ImageView(requireContext()).apply { setImageResource(R.drawable.ic_item_placeholder); layoutParams = ViewGroup.LayoutParams(100, 100) }
            requireView().startDragAndDrop(android.content.ClipData.newPlainText("item_id", item.id), View.DragShadowBuilder(dragView), item, 0)
        }
        binding.rvItems.adapter = adapter
        binding.rvItems.layoutManager = GridLayoutManager(requireContext(), 4)
        adapter.submitList(listOf(
            Item("1", "破军", "https://picsum.photos/seed/item1/64/64", 2950, "+180 Attack", "Attack"),
            Item("2", "无尽", "https://picsum.photos/seed/item2/64/64", 2140, "+130 Attack", "Attack")
        ))
    }

    inner class SlotDragListener(private val slotIndex: Int) : View.OnDragListener {
        override fun onDrag(v: View, event: DragEvent): Boolean {
            if (event.action == DragEvent.ACTION_DROP) {
                val item = event.localState as? Item ?: return false
                slotItems[slotIndex] = item
                Glide.with(requireContext()).load(item.iconUrl).placeholder(R.drawable.ic_item_placeholder).into(slotImages[slotIndex]!!)
                return true
            }
            return false
        }
    }
    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
