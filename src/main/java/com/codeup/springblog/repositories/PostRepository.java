package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Ad;
import com.codeup.springblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {
//    @Query("FROM Post WHERE id = ?1")

    Post getById(long id);

//    @Query("UPDATE Post p SET p.title = ?1, p.body = ?2 WHERE p.id = ?3")
//    Post findPostById(String title, String body, long id);

    Post save(Post p);

    @Query("from Post p where p.title like %:term%")
    Post findByTitle(String term);


}
