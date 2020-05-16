package com.kurs.lirie.reposes;

import com.kurs.lirie.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NewsRepository extends JpaRepository<News,Long> {

    void deleteById(Long id);
}
