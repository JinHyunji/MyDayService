package com.web.myday.MyDay.repository;

import com.web.myday.MyDay.dto.PostDTO;
import com.web.myday.MyDay.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {


}
