package com.andriusdgt.thedots.mongoadapter.repository.adapter;

import com.andriusdgt.thedots.core.model.PointList;
import com.andriusdgt.thedots.core.repository.PointListRepository;
import com.andriusdgt.thedots.mongoadapter.repository.PointListMongoRepository;

import java.util.List;

final class PointListRepositoryAdapter implements PointListRepository {

    private final PointListMongoRepository mongoRepository;

    PointListRepositoryAdapter(PointListMongoRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    @Override
    public void save(PointList pointList) {
        mongoRepository.save(pointList);
    }

    @Override
    public PointList findByName(String name) {
        return mongoRepository.findByName(name);
    }

    @Override
    public List<PointList> findAll() {
        return mongoRepository.findAll();
    }

    @Override
    public void delete(PointList pointList) {
        mongoRepository.delete(pointList);
    }

    @Override
    public void deleteById(String id) {
        mongoRepository.deleteById(id);
    }

}
