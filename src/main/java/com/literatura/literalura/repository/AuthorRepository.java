package com.literatura.literalura.repository;

import com.literatura.literalura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByBirthYearLessThanEqualAndDeathYearGreaterThanEqual(Integer birth, Integer death);

}