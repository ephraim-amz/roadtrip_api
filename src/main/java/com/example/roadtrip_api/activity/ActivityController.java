package com.example.roadtrip_api.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ActivityController {
    private final List<Activity> activities = new ArrayList<>();
    private final Logger logger = LoggerFactory.getLogger(ActivityController.class);

    @PostMapping(value = "/api/activity/new")
    public ResponseEntity<Activity> newActivity(@RequestBody Activity activity) {
        activities.add(activity);
        logger.info("A new activity has been created");
        return new ResponseEntity<>(activity, HttpStatus.CREATED);
    }

    @GetMapping(path = "/api/activity/{id}", produces = "application/json")
    public ResponseEntity<Activity> getActivity(@PathVariable int id) {
        return ResponseEntity.ok(activities
                .stream()
                .filter(activity -> activity.id() == id)
                .findFirst().orElseThrow(ActivityNotFoundException::new));
    }

    @GetMapping(path = "/api/activities", produces = "application/json")
    public ResponseEntity<List<Activity>> getActivities() {
        if (activities.isEmpty()) {
            logger.error("No activities found");
            return ResponseEntity.noContent().build();
        }
        logger.info("Activities found");
        return ResponseEntity.ok(activities);
    }

}
