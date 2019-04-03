package com.wxd.repository;

import com.wxd.entity.Article;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Query(value = "select a.* from article a where a.id = ?1  for update", nativeQuery = true)
    Optional<Article> findArticleForUpdate(Long id);

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query("select a from Article a where a.id = :id")
    Optional<Article> findArticleWithPessimisticLock(Long id);


    @Modifying
    @Query(value = "update Article set comment_count = :commentCount, version = version + 1 where id = :id and version = :version", nativeQuery = true)
    int updateArticleWithVersion(@Param("id") Long id, @Param("commentCount") Long commentCount, @Param("version") Long version);



}

