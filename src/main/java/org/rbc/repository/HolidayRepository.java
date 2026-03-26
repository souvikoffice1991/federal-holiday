package org.rbc.repository;


import org.rbc.entity.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {
    // Basic CRUD methods are inherited automatically.
    // You can also add custom query methods here:

   @Query("SELECT p FROM Holiday p WHERE p.country = :country")
    Optional<List<Holiday>> findAllHolidayByCountry(@Param("country") String country);

}
