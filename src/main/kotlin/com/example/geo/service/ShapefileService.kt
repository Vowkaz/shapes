package com.example.geo.service

import org.geotools.data.shapefile.ShapefileDataStore
import org.geotools.data.simple.SimpleFeatureIterator
import org.opengis.feature.simple.SimpleFeature
import org.springframework.stereotype.Service
import java.io.File
import java.nio.charset.StandardCharsets

@Service
class ShapefileService {
    companion object {
        fun processShapefile(
            filePath: String,
            processFeature: (Map<String, Any?>) -> Unit
        ) {
            val file = File(filePath)
            val dataStore = ShapefileDataStore(file.toURI().toURL())
            dataStore.charset = StandardCharsets.UTF_8

            val typeName = dataStore.typeNames[0]
            val featureSource = dataStore.getFeatureSource(typeName)
            val features: SimpleFeatureIterator = featureSource.features.features()

            while (features.hasNext()) {
                val feature: SimpleFeature = features.next()
                val featureMap = mutableMapOf<String, Any?>()

                for (attributeDescriptor in feature.featureType.attributeDescriptors) {
                    val attributeName = attributeDescriptor.localName
                    val attributeValue = feature.getAttribute(attributeName)
                    featureMap[attributeName] = attributeValue
                }

                processFeature(featureMap)
            }

            features.close()
            dataStore.dispose()
        }
    }
}