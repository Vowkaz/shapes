package com.example.geo.repository.property


import com.example.geo.model.Property
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PropertyRepository : JpaRepository<Property, Long>, IPropertyRepository {

    @Query("SELECT p FROM properties p WHERE p.source = :source AND p.code = :code")
    fun findBySourceAndCodImovel(
        @Param("source") source: String,
        @Param("code") code: String
    ): Property?
}
