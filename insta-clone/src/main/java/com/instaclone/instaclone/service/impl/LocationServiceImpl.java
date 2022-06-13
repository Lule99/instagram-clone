package com.instaclone.instaclone.service.impl;

import com.instaclone.instaclone.dto.location.LocationDto;
import com.instaclone.instaclone.model.Location;
import com.instaclone.instaclone.repository.LocationRepository;
import com.instaclone.instaclone.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public Location preprocessLocation(LocationDto locationDto) {
        List<String> chain = new ArrayList<>();

        for (String place : locationDto.getChain()) {
            if (place == null)
                continue;
            String pl = place.toLowerCase()
                    .replace("city", "")
                    .replace("of", "")
                    .trim();
            chain.add(pl);
        }

        chain = chain.stream().distinct().collect(Collectors.toList());

        locationDto.setChain(chain);

        String parentLocationName = getParent(chain, 0);
        Location parent = locationRepository.findByName(parentLocationName);

        Location newLocation = Location
                .builder()
                .parent(parent.getName())
                .name(locationDto.getLocationName())
                .longitude(locationDto.getLongitude())
                .latitude(locationDto.getLatitude())
                .locationName(locationDto.getLocationName())
                .build();
        newLocation.setTimeCreated(LocalDateTime.now());

        return locationRepository.save(newLocation);
    }

    private String getParent(List<String> chain, int i) {

        if (i == chain.size() || chain.size() == 0)
            return "";
        Location parentLocation = locationRepository.findByName(chain.get(i));

        if (parentLocation != null)
            return parentLocation.getName();

        //ako ne postoji
        Location location = Location
                .builder()
                .name(chain.get(i))
                .parent(getParent(chain, i + 1))
                .locationName(chain.get(i))
                .latitude(-1.0)
                .longitude(-1.0)
                .build();
        location.setTimeCreated(LocalDateTime.now());
        locationRepository.save(location);
        return location.getName();
    }


}
