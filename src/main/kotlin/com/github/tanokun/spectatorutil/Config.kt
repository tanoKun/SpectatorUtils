package com.github.tanokun.spectatorutil

object Config {
    val tag: String

    val registerCommand: String
    val unregisterCommand: String

    init {
        val config = plugin.config

        tag = config.getString("tag") ?: "spectator"
        registerCommand = config.getString("command.register") ?: "function werewolf:admin/set_spectator"
        unregisterCommand = config.getString("command.unregister") ?: "function werewolf:admin/remove_spectator"
    }
}