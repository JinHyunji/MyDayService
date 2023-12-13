package com.web.myday.MyDay.repository;

import com.web.myday.MyDay.dto.PostDTO;
import com.web.myday.MyDay.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

}
