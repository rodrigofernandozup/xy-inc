package com.xyinc.poi.service

import com.xyinc.poi.entity.PoiEntity
import com.xyinc.poi.repository.PoiRepository
import org.springframework.stereotype.Service
import kotlin.math.abs
import kotlin.math.pow

@Service
class PoiService (private val poiRepository: PoiRepository) {

    fun getAllPoi(): List<PoiEntity> =
        poiRepository.findAll().toList()
        /*poiRepository.findAll().toList().map {poiEntity -> poiEntity.toPoiResponse() }*/

    fun createPoi(poiEntity: PoiEntity): PoiEntity? =
        if (!poiRepository.findById(poiEntity.name).isPresent) {
            poiRepository.save(poiEntity)
        } else null

    fun getPoiByCoordinatesAndMaxDistance(xCoordinate: Long, yCoordinate: Long, maxDistance: Long): List<PoiEntity> =
        poiRepository.findAll().filter { poiEntity: PoiEntity ->
            isInsidePerimeterDistance(poiEntity, xCoordinate, yCoordinate, maxDistance) }
        /*poiRepository.findAll().filter { poiEntity: PoiEntity ->
            isInsidePerimeterDistance(poiEntity, xCoordinate, yCoordinate, maxDistance)
        }.map { poiEntity -> poiEntity.toPoiResponse() }*/

    private fun isInsidePerimeterDistance(poiEntity: PoiEntity, xCoordinateReference: Long, yCoordinateReference: Long, maxDistance: Long): Boolean =
        (abs(poiEntity.xCoordinates) - abs(xCoordinateReference)).toDouble().pow(2) +
                ((abs(poiEntity.yCoordinates) - abs(yCoordinateReference)).toDouble()).pow(2) <= maxDistance.toDouble().pow(2)

}


