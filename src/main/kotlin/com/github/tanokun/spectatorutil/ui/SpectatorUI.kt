package com.github.tanokun.spectatorutil.ui

import com.github.tanokun.spectatorutil.Config
import com.github.tanokun.spectatorutil.item.NotSpecPlayerButton
import com.github.tanokun.spectatorutil.item.SpecPlayerButton
import com.github.tanokun.spectatorutil.item.page.BackPageButton
import com.github.tanokun.spectatorutil.item.page.NextPageButton
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.invui.gui.Gui
import xyz.xenondevs.invui.gui.PagedGui
import xyz.xenondevs.invui.gui.structure.Markers
import xyz.xenondevs.invui.item.Item
import xyz.xenondevs.invui.item.impl.SimpleItem
import xyz.xenondevs.invui.window.Window

class SpectatorUI(private val player: Player) {
    private val gui: Gui

    init {
        val spectatorPlayers = arrayListOf<SpecPlayerButton>()
        val notSpectatorPlayers = arrayListOf<NotSpecPlayerButton>()

        Bukkit.getOnlinePlayers().forEach {
            if (it.scoreboardTags.contains(Config.tag)) {
                spectatorPlayers.add(SpecPlayerButton(it))
                return@forEach
            }

            notSpectatorPlayers.add(NotSpecPlayerButton(it))
        }

        val viewItems = arrayListOf<Item>().apply {
            addAll(spectatorPlayers)
            add(SimpleItem(ItemStack(Material.AIR)))
            addAll(notSpectatorPlayers)
        }

        gui = PagedGui.items()
            .setStructure(
                "x x x x x x x x x",
                "x x x x x x x x x",
                "x x x x x x x x x",
                "x x x x x x x x x",
                "x x x x x x x x x",
                "# # z # # # y # #"
            )
            .addIngredient('x', Markers.CONTENT_LIST_SLOT_HORIZONTAL)
            .addIngredient('z', BackPageButton())
            .addIngredient('y', NextPageButton())
            .setContent(viewItems)
            .build()
    }

    fun open() {
        Window.single()
            .setGui(gui)
            .setTitle("スペクテイター設定")
            .setViewer(player)
            .build()
            .open()
    }
}