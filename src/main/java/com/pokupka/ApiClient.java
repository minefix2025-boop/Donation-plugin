package com.pokupka;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiClient {

    private String apiUrl;
    private String apiKey;
    private String serverId;
    private JavaPlugin plugin;

    public ApiClient(String apiUrl, String apiKey, String serverId, JavaPlugin plugin) {
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
        this.serverId = serverId;
        this.plugin = plugin;
        startChecker();
    }

    private void startChecker() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            try {
                URL url = new URL(apiUrl + "/check?server=" + serverId + "&key=" + apiKey);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String response = in.readLine();
                in.close();

                if (response != null && response.contains("BUY")) {
                    String[] data = response.split(":");

                    String player = data[1];
                    String rank = data[2];

                    Bukkit.getScheduler().runTask(plugin, () -> {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                                "lp user " + player + " parent set " + rank);
                    });
                }

            } catch (Exception ignored) {}
        }, 20L, 100L);
    }
}
