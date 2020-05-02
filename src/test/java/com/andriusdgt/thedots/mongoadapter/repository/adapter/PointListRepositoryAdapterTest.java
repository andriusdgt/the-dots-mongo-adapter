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
public class PointListRepositoryAdapterTest {

    @Mock
    private PointListMongoRepository mongoRepository;

    private PointListRepositoryAdapter repositoryAdapter;

    @BeforeEach
    public void setUp() {
        repositoryAdapter = new PointListRepositoryAdapter(mongoRepository);
    }

    @Test
    public void savesPointList() {
        PointList pointList = new PointList("listId", "name");

        repositoryAdapter.save(pointList);

        verify(mongoRepository).save(pointList);
    }

    @Test
    public void findsPointListByName() {
        PointList pointList = new PointList("listId", "name");
        doReturn(pointList).when(mongoRepository).findByName("listId");

        assertEquals(pointList, repositoryAdapter.findByName("listId"));
    }

    @Test
    public void findsAllPointLists() {
        List<PointList> pointLists = Collections.singletonList(new PointList("listId", "name"));
        doReturn(pointLists).when(mongoRepository).findAll();

        assertEquals(pointLists, repositoryAdapter.findAll());
    }

    @Test
    public void deletesPointList() {
        PointList pointList = new PointList("listId", "name");

        repositoryAdapter.delete(pointList);

        verify(mongoRepository).delete(pointList);
    }

    @Test
    public void deletesPointListById() {
        repositoryAdapter.deleteById("listId");

        verify(mongoRepository).deleteById("listId");
    }

}
