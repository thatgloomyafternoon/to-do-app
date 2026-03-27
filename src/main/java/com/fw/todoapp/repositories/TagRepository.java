package com.fw.todoapp.repositories;

import com.fw.todoapp.entities.Tag;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TagRepository extends JpaRepository<Tag, UUID> {

  @Query(
    value = "select * from tag where name = ?1",
    nativeQuery = true
  )
  Optional<Tag> findOneByName(String name);

  List<Tag> findAllByOrderByNameAsc();

}
