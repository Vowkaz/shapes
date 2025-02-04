package com.example.geo.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.locationtech.jts.geom.Geometry
import java.util.Date

@Entity(name = "properties")
data class Property (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var code: String,
    var source: String,
    var state: String? = null,
    var stateCode: Long? = null,
    var city: String? = null,
    var cityCode: Long? = null,
    var areaHa: Float? = null,
    var status: String? = null,
    var condicion: String? = null,
    var type: String? = null,
    var taxModule: String? = null,
    var topic: String? = null,
    var geometry: Geometry? = null,
    var propertyCreated: Date? = null,
    var propertyUpdate: Date? = null,
)