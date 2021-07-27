package com.codeup.springblog.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {
//    @Query("FROM Post WHERE id = ?1")

    Post findById(long id);

//    @Query("UPDATE Post p SET p.title = ?1, p.body = ?2 WHERE p.id = ?3")
//    Post findPostById(String title, String body, long id);

    Post save(Post p);


}
