package com.xyinc.poi.service

import com.xyinc.poi.domain.Poi
import com.xyinc.poi.domain.toPoiEntity
import com.xyinc.poi.entity.PoiEntity
import com.xyinc.poi.entity.toPoi
import com.xyinc.poi.exceptionhandler.exception.PoiAlreadyExistsException
import com.xyinc.poi.exceptionhandler.exception.PoiInvalidCoordinateException
import com.xyinc.poi.exceptionhandler.exception.PoiNameIsBlankException
import com.xyinc.poi.repository.PoiRepository
import org.springframework.stereotype.Service
import kotlin.math.abs
import kotlin.math.pow

@Service
class PoiService (private val poiRepository: PoiRepository) {

    fun getAllPois(): List<Poi> =
        poiRepository.findAll().toList().map { poiEntity -> poiEntity.toPoi() }

    fun createPoi(poi: Poi): Poi? =
        if (validatePoi(poi) && !poiRepository.findById(poi.name).isPresent) {
            poiRepository.save(poi.toPoiEntity()).toPoi()
        } else throw PoiAlreadyExistsException()

    fun getPoisByCoordinatesAndMaxDistance(xCoordinate: Long, yCoordinate: Long, maxDistance: Long): List<Poi> =
        poiRepository.findAll().filter { poiEntity: PoiEntity ->
            isInsidePerimeterDistance(poiEntity, xCoordinate, yCoordinate, maxDistance) }.toList().map { poiEntity -> poiEntity.toPoi() }

    private fun validatePoi(poi: Poi) : Boolean {
        if(poi.name.isBlank())
            throw PoiNameIsBlankException();
        if(poi.xCoordinate < 0 || poi.yCoordinate < 0)
            throw PoiInvalidCoordinateException()
        return true
    }

    private fun isInsidePerimeterDistance(poiEntity: PoiEntity, xCoordinateReference: Long, yCoordinateReference: Long, maxDistance: Long): Boolean =
        (abs(poiEntity.xCoordinate) - abs(xCoordinateReference)).toDouble().pow(2) +
                ((abs(poiEntity.yCoordinate) - abs(yCoordinateReference)).toDouble()).pow(2) <= maxDistance.toDouble().pow(2)

}


