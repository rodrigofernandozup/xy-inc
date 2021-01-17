package com.xyinc.poi.controller

import com.xyinc.poi.controller.request.PoiRequest
import com.xyinc.poi.controller.request.toPoi
import com.xyinc.poi.controller.response.PoiResponse
import com.xyinc.poi.domain.toPoiResponse
import com.xyinc.poi.service.PoiService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pois")
@Api(value = "POI")
class PoiController {

    @Autowired
    lateinit var poiService: PoiService

    @GetMapping
    @ApiOperation (httpMethod = "GET", value = "Busca todos POIs")
    fun getPois(): ResponseEntity<List<PoiResponse>> =
        ResponseEntity.ok(poiService.getAllPois().map { poi -> poi.toPoiResponse() })

    @PostMapping
    @ApiOperation(httpMethod = "POST", value = "Cadastra POI")
    fun addPoi(@RequestBody poiRequest: PoiRequest): ResponseEntity<PoiResponse> =
        ResponseEntity(poiService.createPoi(poiRequest.toPoi())?.toPoiResponse(), HttpStatus.CREATED)

    @GetMapping("/references")
    @ApiOperation (httpMethod = "GET", value = "Busca POIs por coordenadas (X e Y) e distancia")
    fun getPoisByCoordinateAndRange(@RequestParam(required = false) xCoordinateReference: Long,
                                       @RequestParam yCoordinateReference: Long,
                                       @RequestParam distance: Long): ResponseEntity<List<PoiResponse>> =
        ResponseEntity.ok(poiService.getPoisByCoordinatesAndMaxDistance(xCoordinateReference, yCoordinateReference, distance).map { poi -> poi.toPoiResponse() })

}