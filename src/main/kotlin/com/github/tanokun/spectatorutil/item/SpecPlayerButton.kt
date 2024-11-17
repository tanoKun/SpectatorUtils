package com.github.tanokun.spectatorutil.item

import com.github.tanokun.spectatorutil.Config
import com.github.tanokun.spectatorutil.ui.SpectatorUI
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import xyz.xenondevs.invui.item.ItemProvider
import xyz.xenondevs.invui.item.builder.SkullBuilder
import xyz.xenondevs.invui.item.impl.AbstractItem

class SpecPlayerButton(private val player: Player): AbstractItem() {
    override fun getItemProvider(): ItemProvider {
        return SkullBuilder(SkullBuilder.HeadTexture.of(player))
            .setDisplayName("§b§l${player.name} (観戦)")
            .addLoreLines("§7クリックで、スペクテイターを解除します。")
    }

    override fun handleClick(clickType: ClickType, player: Player, event: InventoryClickEvent) {
        player.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 3.0f, 1.0f)

        player.performCommand("execute as ${player.name} run ${Config.unregisterCommand}")
        player.removeScoreboardTag(Config.tag)

        SpectatorUI(player).open()
    }
}