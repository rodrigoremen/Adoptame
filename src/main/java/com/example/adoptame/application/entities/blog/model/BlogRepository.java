package com.example.adoptame.application.entities.blog.model;

import com.example.adoptame.application.repository.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BlogRepository extends EntityRepository<Blog,Long> {
    @Query(
            value = "SELECT * FROM blop n WHERE n.is_published = 1 ORDER BY n.created_at LIMIT 5 ",
            nativeQuery = true)
    List<Blog> findTop5ByOrderByCreatedAtDesc();

    List<Blog> findAllByIsPublished(Boolean isPublished);

    List<Blog> findAllByIsMainAndIsPublishedOrderByCreatedAtDesc(Boolean isMain, Boolean isPublished);

    Integer countByIsMainIsTrue();

    Integer countByIsPublishedIsTrue();
}
