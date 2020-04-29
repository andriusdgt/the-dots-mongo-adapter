package com.andriusdgt.thedots.mongoplugin.repository;

import com.andriusdgt.thedots.api.model.PointCoordinates;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PointCoordinatesRepository extends MongoRepository<PointCoordinates, String> {

    List<PointCoordinates> findAll();

    List<PointCoordinates> findByListId(String listId);

    List<PointCoordinates> findByListId(String listId, Pageable pageable);

    long countByListId(String listId);

    void deleteByListId(String listId);

}
