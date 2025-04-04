package com.InstaCio.repository;

import com.InstaCio.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Integer> {

    List<Post> findPostByUserId(Integer userId);
}
