package com.xyinc.gps.controller

import com.xyinc.gps.model.Poi
import com.xyinc.gps.service.PoiService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/pois")
@Api(value = "POI")
class PoiController {

    @Autowired
    lateinit var poiService: PoiService

    @GetMapping
    @ApiOperation (httpMethod = "GET", value = "Busca todos POIs")
    fun getPoi(request: HttpServletRequest): ResponseEntity<List<Poi>> =
        ResponseEntity.ok(poiService.getAllPoi())

    @PostMapping
    @ApiOperation(httpMethod = "POST", value = "Cadastra POI")
    fun addPoi(
        @RequestParam name: String,
        @RequestParam xCoordinate: Long,
        @RequestParam yCoordinate: Long
    ): ResponseEntity<String> =
        poiService.createPoi(name, xCoordinate, yCoordinate)?.let {
            ResponseEntity(it.name, HttpStatus.CREATED)
        } ?: ResponseEntity.status(HttpStatus.CONFLICT).build()

    @GetMapping("/references")
    @ApiOperation (httpMethod = "GET", value = "Busca POIs por coordenadas (X e Y) e distancia")
    fun findAllPoiByCoordinateAndRange(@RequestParam xCoordinate: Long,
                                       @RequestParam yCoordinate: Long,
                                       @RequestParam distance: Long): ResponseEntity<List<Poi>> =
        ResponseEntity.ok(poiService.getPoiByCoordinatesAndMaxDistance(xCoordinate, yCoordinate, distance))
}