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
final class PointRepositoryAdapterTest {

    @Mock
    private PointMongoRepository mongoRepository;

    private PointRepositoryAdapter repositoryAdapter;

    @BeforeEach
    void setUp() {
        repositoryAdapter = new PointRepositoryAdapter(mongoRepository);
    }

    @Test
    void savesPoint() {
        Point point = new Point(10, 20, "listId");

        repositoryAdapter.save(point);

        verify(mongoRepository).save(point);
    }

    @Test
    void savesPoints() {
        List<Point> points = Collections.singletonList(new Point(10, 20, "listId"));

        repositoryAdapter.saveAll(points);

        verify(mongoRepository).saveAll(points);
    }

    @Test
    void findsPointsByListId() {
        List<Point> points = Collections.singletonList(new Point(10, 20, "listId"));
        doReturn(points).when(mongoRepository).findByListId("listId");

        assertEquals(points, repositoryAdapter.findByListId("listId"));
    }

    @Test
    void findsSortedPointsByListId() {
        List<Point> points = Collections.singletonList(new Point(10, 20, "listId"));
        doReturn(points).when(mongoRepository).findByListIdOrderByXAscYAsc("listId");

        assertEquals(points, repositoryAdapter.findByListIdOrderByXAscYAsc("listId"));
    }

    @Test
    void findsPaginatedPointsByListId() {
        List<Point> points = Collections.singletonList(new Point(10, 20, "listId"));
        doReturn(points).when(mongoRepository).findByListId("listId", PageRequest.of(3, 50));

        assertEquals(points, repositoryAdapter.findByListId("listId", 3, 50));
    }

    @Test
    void findsSortedPaginatedPointsByListId() {
        List<Point> points = Collections.singletonList(new Point(10, 20, "listId"));
        doReturn(points).when(mongoRepository).findByListId("listId", PageRequest.of(3, 50, ASC, "x", "y"));

        assertEquals(points, repositoryAdapter.findByListIdOrderByXAndY("listId", 3, 50, "asc"));
    }

    @Test
    void findsReverseSortedPaginatedPointsByListId() {
        List<Point> points = Collections.singletonList(new Point(10, 20, "listId"));
        doReturn(points).when(mongoRepository).findByListId("listId", PageRequest.of(3, 50, DESC, "x", "y"));

        assertEquals(points, repositoryAdapter.findByListIdOrderByXAndY("listId", 3, 50, "desc"));
    }

    @Test
    void checksIfPointIsSaved() {
        Point point = new Point(10, 20, "listId");
        doReturn(true).when(mongoRepository).exists(Example.of(point));

        assertTrue(repositoryAdapter.exists(point));
    }

    @Test
    void countsByListId() {
        doReturn(1L).when(mongoRepository).countByListId("listId");

        assertEquals(1L, repositoryAdapter.countByListId("listId"));
    }

    @Test
    void deletesPointById() {
        repositoryAdapter.deleteById("id");

        verify(mongoRepository).deleteById("id");
    }

    @Test
    void deletesPointsByListId() {
        repositoryAdapter.deleteByListId("listId");

        verify(mongoRepository).deleteByListId("listId");
    }

}
