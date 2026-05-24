package com.pokupka;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private ApiClient apiClient;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        apiClient = new ApiClient(
                getConfig().getString("api-url"),
                getConfig().getString("api-key"),
                getConfig().getString("server-id"),
                this
        );
        getServer().getPluginManager().registerEvents(new PurchaseListener(apiClient), this);
        getLogger().info("Pokupka enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("Pokupka disabled");
    }
}
