package com.example.geo.service

import com.example.geo.model.Property
import com.example.geo.repository.property.PropertyRepository
import org.locationtech.jts.geom.Geometry
import org.springframework.stereotype.Service
import java.util.Date

@Service
class PropertyService(private val repository: PropertyRepository) {
    fun processFeature(featureMap: Map<String, Any?>) {
        val state = featureMap["cod_estado"] as? String
        val city = featureMap["municipio"] as? String
        val code = featureMap["cod_imovel"] as String
        val areaHa = featureMap["num_area"] as? Double
        val status = featureMap["ind_status"] as? String
        val type = featureMap["ind_tipo"] as? String
        val condicion = featureMap["des_condic"] as? String
        val taxModule = featureMap["mod_fiscal"] as? String
        val topic = featureMap["cod_tema"] as? String
        val geometry = featureMap["the_geom"] as? Geometry
        val propertyCreated = featureMap["dat_criaca"] as? Date
        val propertyUpdate = featureMap["dat_atuali"] as? Date

        val property = Property(
            code = code,
            state = state,
            source = "CAR",
            city = city,
            areaHa = areaHa?.toFloat(),
            status = status,
            condicion = condicion,
            type = type,
            taxModule = taxModule,
            topic = topic,
            geometry = geometry,
            propertyCreated = propertyCreated,
            propertyUpdate = propertyUpdate
        )

        //// alterar para salvar em lote
        updateOrCreate(property)
    }

    fun updateOrCreate(property: Property): Property = repository.updateOrCreate(property)
}