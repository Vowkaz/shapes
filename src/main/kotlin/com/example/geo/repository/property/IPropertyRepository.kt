package com.example.geo.repository.property


import com.example.geo.model.Property
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface IPropertyRepository {
    fun updateOrCreate(property: Property): Property
}
