package org.example.infrastructure.services;

public interface IWebServerStart {
    IWebServerStart createWebapp();
    IWebServerStart mapServlets();
    void build();
}