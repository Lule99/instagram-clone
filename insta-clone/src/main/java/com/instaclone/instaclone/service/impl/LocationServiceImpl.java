package com.instaclone.instaclone.service.impl;

import com.instaclone.instaclone.model.Location;
import com.instaclone.instaclone.repository.LocationRepository;
import com.instaclone.instaclone.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl extends JPAServiceImpl<Location> implements LocationService {

    private final LocationRepository locationRepository;

    @Override
    protected JpaRepository<Location, Long> getEntityRepository() {
        return locationRepository;
    }

    @Override
    public double calculateDistance(Location from, Location to) {
        return org.apache.lucene.util.SloppyMath.haversinMeters(from.getLatitude(),
                from.getLongitude(),
                to.getLatitude(),
                to.getLongitude());
    }
}
