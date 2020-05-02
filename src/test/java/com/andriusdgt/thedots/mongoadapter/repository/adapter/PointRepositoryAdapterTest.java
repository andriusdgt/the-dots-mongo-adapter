package com.andriusdgt.thedots.mongoadapter.repository.adapter;

import com.andriusdgt.thedots.core.model.Point;
import com.andriusdgt.thedots.mongoadapter.repository.PointMongoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

@ExtendWith(MockitoExtension.class)
class PointRepositoryAdapterTest {

    @Mock
    private PointMongoRepository mongoRepository;

    private PointRepositoryAdapter repositoryAdapter;

    @BeforeEach
    public void setUp() {
        repositoryAdapter = new PointRepositoryAdapter(mongoRepository);
    }

    @Test
    public void savesPoint() {
        Point point = new Point(10, 20, "listId");

        repositoryAdapter.save(point);

        verify(mongoRepository).save(point);
    }

    @Test
    public void savesPoints() {
        List<Point> points = Collections.singletonList(new Point(10, 20, "listId"));

        repositoryAdapter.saveAll(points);

        verify(mongoRepository).saveAll(points);
    }

    @Test
    public void findsPointsByListId() {
        List<Point> points = Collections.singletonList(new Point(10, 20, "listId"));
        doReturn(points).when(mongoRepository).findByListId("listId");

        assertEquals(points, repositoryAdapter.findByListId("listId"));
    }

    @Test
    public void findsSortedPointsByListId() {
        List<Point> points = Collections.singletonList(new Point(10, 20, "listId"));
        doReturn(points).when(mongoRepository).findByListIdOrderByXAscYAsc("listId");

        assertEquals(points, repositoryAdapter.findByListIdOrderByXAscYAsc("listId"));
    }

    @Test
    public void findsPaginatedPointsByListId() {
        List<Point> points = Collections.singletonList(new Point(10, 20, "listId"));
        doReturn(points).when(mongoRepository).findByListId("listId", PageRequest.of(3, 50));

        assertEquals(points, repositoryAdapter.findByListId("listId", 3, 50));
    }

    @Test
    public void findsSortedPaginatedPointsByListId() {
        List<Point> points = Collections.singletonList(new Point(10, 20, "listId"));
        doReturn(points).when(mongoRepository).findByListId("listId", PageRequest.of(3, 50, ASC, "x", "y"));

        assertEquals(points, repositoryAdapter.findByListIdOrderByXAndY("listId", 3, 50, "asc"));
    }

    @Test
    public void findsReverseSortedPaginatedPointsByListId() {
        List<Point> points = Collections.singletonList(new Point(10, 20, "listId"));
        doReturn(points).when(mongoRepository).findByListId("listId", PageRequest.of(3, 50, DESC, "x", "y"));

        assertEquals(points, repositoryAdapter.findByListIdOrderByXAndY("listId", 3, 50, "desc"));
    }

    @Test
    public void checksIfPointIsSaved() {
        Point point = new Point(10, 20, "listId");
        doReturn(true).when(mongoRepository).exists(Example.of(point));

        assertTrue(repositoryAdapter.exists(point));
    }

    @Test
    public void countsByListId() {
        doReturn(1L).when(mongoRepository).countByListId("listId");

        assertEquals(1L, repositoryAdapter.countByListId("listId"));
    }

    @Test
    public void deletesPointById() {
        repositoryAdapter.deleteById("id");

        verify(mongoRepository).deleteById("id");
    }

    @Test
    public void deletesPointsByListId() {
        repositoryAdapter.deleteByListId("listId");

        verify(mongoRepository).deleteByListId("listId");
    }

}
