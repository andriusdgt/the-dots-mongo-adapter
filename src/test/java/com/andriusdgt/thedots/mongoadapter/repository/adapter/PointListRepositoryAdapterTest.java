package com.andriusdgt.thedots.mongoadapter.repository.adapter;

import com.andriusdgt.thedots.core.model.PointList;
import com.andriusdgt.thedots.mongoadapter.repository.PointListMongoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
final class PointListRepositoryAdapterTest {

    @Mock
    private PointListMongoRepository mongoRepository;

    private PointListRepositoryAdapter repositoryAdapter;

    @BeforeEach
    void setUp() {
        repositoryAdapter = new PointListRepositoryAdapter(mongoRepository);
    }

    @Test
    void savesPointList() {
        PointList pointList = new PointList("listId", "name");

        repositoryAdapter.save(pointList);

        verify(mongoRepository).save(pointList);
    }

    @Test
    void findsPointListByName() {
        PointList pointList = new PointList("listId", "name");
        doReturn(pointList).when(mongoRepository).findByName("listId");

        assertEquals(pointList, repositoryAdapter.findByName("listId"));
    }

    @Test
    void findsAllPointLists() {
        List<PointList> pointLists = Collections.singletonList(new PointList("listId", "name"));
        doReturn(pointLists).when(mongoRepository).findAll();

        assertEquals(pointLists, repositoryAdapter.findAll());
    }

    @Test
    void deletesPointList() {
        PointList pointList = new PointList("listId", "name");

        repositoryAdapter.delete(pointList);

        verify(mongoRepository).delete(pointList);
    }

    @Test
    void deletesPointListById() {
        repositoryAdapter.deleteById("listId");

        verify(mongoRepository).deleteById("listId");
    }

}
