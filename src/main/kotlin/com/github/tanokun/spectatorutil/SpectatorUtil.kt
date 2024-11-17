package com.github.tanokun.spectatorutil

import com.github.tanokun.spectatorutil.ui.SpectatorUI
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

lateinit var plugin: Plugin private set

class SpectatorUtil : JavaPlugin() {
    init {
        plugin = this
    }

    override fun onEnable() {
        saveDefaultConfig()
    }

    override fun onDisable() {
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return false

        SpectatorUI(sender).open()
        return true
    }
}
