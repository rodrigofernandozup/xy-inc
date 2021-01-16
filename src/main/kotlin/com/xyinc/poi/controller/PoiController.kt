package com.xyinc.poi.controller

import com.xyinc.poi.entity.PoiEntity
import com.xyinc.poi.service.PoiService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/pois")
@Api(value = "POI")
class PoiController {

    @Autowired
    lateinit var poiService: PoiService

    @GetMapping
    @ApiOperation (httpMethod = "GET", value = "Busca todos POIs")
    fun getPois(): ResponseEntity<List<PoiEntity>> =
        ResponseEntity.ok(poiService.getAllPois())

    @PostMapping
    @ApiOperation(httpMethod = "POST", value = "Cadastra POI")
    fun addPoi(@RequestBody poiEntity: PoiEntity): ResponseEntity<PoiEntity> =
        ResponseEntity(poiService.createPoi(poiEntity), HttpStatus.CREATED)

    @GetMapping("/references")
    @ApiOperation (httpMethod = "GET", value = "Busca POIs por coordenadas (X e Y) e distancia")
    fun findAllPoiByCoordinateAndRange(@RequestParam xCoordinateReference: Long,
                                       @RequestParam yCoordinateReference: Long,
                                       @RequestParam distance: Long): ResponseEntity<List<PoiEntity>> =
        ResponseEntity.ok(poiService.getPoiByCoordinatesAndMaxDistance(xCoordinateReference, yCoordinateReference, distance))
}