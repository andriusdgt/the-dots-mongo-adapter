package com.andriusdgt.thedots.mongoadapter.repository;

import com.andriusdgt.thedots.core.model.PointList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PointListMongoRepository extends MongoRepository<PointList, String> {

    PointList findByName(String listId);

}
