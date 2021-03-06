package com.andriusdgt.thedots.mongoadapter.repository;

import com.andriusdgt.thedots.core.model.Point;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PointMongoRepository extends MongoRepository<Point, String> {

    List<Point> findByListId(String listId);

    List<Point> findByListIdOrderByXAscYAsc(String listId);

    List<Point> findByListId(String listId, Pageable pageable);

    long countByListId(String listId);

    void deleteByListId(String listId);

}
