package com.hamburgeressen.application.repo;

import com.hamburgeressen.application.entity.FilterTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilterTagRepo extends JpaRepository<FilterTag, Long> {
}
