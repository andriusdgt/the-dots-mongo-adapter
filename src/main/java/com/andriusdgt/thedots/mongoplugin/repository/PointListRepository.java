package com.andriusdgt.thedots.mongoplugin.repository;

import com.andriusdgt.thedots.api.model.PointList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PointListRepository extends MongoRepository<PointList, String> {

    PointList findByName(String listId);

}
