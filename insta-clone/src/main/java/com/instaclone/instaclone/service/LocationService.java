package com.instaclone.instaclone.service;

import com.instaclone.instaclone.model.Location;

public interface LocationService extends JPAService<Location> {
    double calculateDistance(Location from, Location to);
}
