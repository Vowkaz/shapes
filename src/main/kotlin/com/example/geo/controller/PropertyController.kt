package com.example.geo.controller

import com.example.geo.model.Property
import com.example.geo.repository.property.PropertyRepository
import com.example.geo.service.PropertyService
import com.example.geo.service.ShapefileService
import org.springframework.web.bind.annotation.*
import java.lang.RuntimeException

@RestController
@RequestMapping("/properties")
class PropertyController(
    private val propertyRepository: PropertyRepository,
    private val propertyService: PropertyService,
) {

    @GetMapping
    fun list(): List<Property> = propertyRepository.findAll()

    @GetMapping("/{id}")
    fun find(@PathVariable id: Long): Property = propertyRepository.findById(id).orElseThrow { RuntimeException("Property cannot be finded")}

    @GetMapping("/process")
    @ResponseBody
    fun process(): Unit {
        val filePath = "src/assets/area_imovel_mt/AREA_IMOVEL_1.shp"

        return ShapefileService.processShapefile(filePath, propertyService::processFeature)
    }
}