package com.example.roadtrip_api.activity;

public record Activity(int id, String name, String description, String city, int duration, int price, String category, float rating) {
}
