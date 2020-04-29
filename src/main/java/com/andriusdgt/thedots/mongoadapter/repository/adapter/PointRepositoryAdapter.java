package com.andriusdgt.thedots.mongoadapter.repository.adapter;

import com.andriusdgt.thedots.core.model.Point;
import com.andriusdgt.thedots.core.repository.PointRepository;
import com.andriusdgt.thedots.mongoadapter.repository.PointMongoRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class PointRepositoryAdapter implements PointRepository {

    private final PointMongoRepository mongoRepository;

    public PointRepositoryAdapter(PointMongoRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    @Override
    public void save(Point point) {
        mongoRepository.save(point);
    }

    @Override
    public void saveAll(List<Point> points) {
        mongoRepository.saveAll(points);
    }

    @Override
    public List<Point> findByListId(String listId) {
        return mongoRepository.findByListId(listId);
    }

    @Override
    public List<Point> findByListId(String listId, int pageIndex, int pageSize) {
        return mongoRepository.findByListId(listId, PageRequest.of(pageIndex, pageSize));
    }

    @Override
    public boolean exists(Point point) {
        return mongoRepository.exists(Example.of(point));
    }

    @Override
    public long countByListId(String listId) {
        return mongoRepository.countByListId(listId);
    }

    @Override
    public void deleteByListId(String listId) {
        mongoRepository.deleteByListId(listId);
    }

    @Override
    public void deleteById(String id) {
        mongoRepository.deleteById(id);
    }

}
