package com.example.geo.repository.property


import com.example.geo.model.Property
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional
import org.springframework.stereotype.Repository

@Repository
class PropertyRepositoryImpl : IPropertyRepository {
    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @Transactional
    override fun updateOrCreate(property: Property): Property {
        val existingProperty = entityManager.createQuery(
            "SELECT p FROM properties p WHERE p.source = :source AND p.code = :code",
            Property::class.java
        )
            .setParameter("source", property.source)
            .setParameter("code", property.code)
            .resultList
            .firstOrNull()

        return if (existingProperty != null) {
            existingProperty.state = property.state
            existingProperty.stateCode = property.stateCode
            existingProperty.city = property.city
            existingProperty.cityCode = property.cityCode
            existingProperty.areaHa = property.areaHa
            existingProperty.status = property.status
            existingProperty.condicion = property.condicion
            existingProperty.type = property.type
            existingProperty.taxModule = property.taxModule
            existingProperty.topic = property.topic
            existingProperty.geometry = property.geometry
            existingProperty.propertyCreated = property.propertyCreated
            existingProperty.propertyUpdate =property.propertyUpdate
            entityManager.merge(existingProperty)
        } else {
            entityManager.persist(property)
            property
        }
    }
}
