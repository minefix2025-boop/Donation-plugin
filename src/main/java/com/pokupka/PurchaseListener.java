package com.pokupka;

import org.bukkit.event.Listener;

public class PurchaseListener implements Listener {

    private ApiClient apiClient;

    public PurchaseListener(ApiClient apiClient) {
        this.apiClient = apiClient;
    }
}
