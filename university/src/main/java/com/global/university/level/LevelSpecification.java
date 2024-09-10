package com.global.university.level;


import jakarta.persistence.criteria.Path;
import org.springframework.data.jpa.domain.Specification;


public class LevelSpecification {

    public static Specification<Level> specialityLike(String specialityName) {
        return (root, query, cb) -> {
            Path<Level> speciality = root.get("speciality");
            return cb.like(speciality.get("name"), specialityName);

        };
    }

    public static Specification<Level> trackLike(String trackName) {
        return (root, query, cb) -> {
            Path<Level> track = root.get("speciality").get("track");
            return cb.like(track.get("name"), trackName);

        };
    }

    public static Specification<Level> departmentLike(String departmentName) {
        return (root, query, cb) -> {
            Path<Level> department = root.get("speciality").get("track").get("department");
            return cb.like(department.get("name"), departmentName);

        };
    }
}
