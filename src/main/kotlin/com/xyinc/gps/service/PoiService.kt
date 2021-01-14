package com.xyinc.gps.service

import com.xyinc.gps.dao.PoiDao
import com.xyinc.gps.model.Poi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.math.abs
import kotlin.math.pow

@Service
class PoiService {

    @Autowired
    private lateinit var poiDao: PoiDao

    fun getAllPoi(): List<Poi> =
        poiDao.findAll().toList()

    fun createPoi(name: String, xCoordinate: Long, yCoordinate: Long): Poi? =
        if (poiDao.findById(name).isEmpty) {
            poiDao.save(Poi(name = name, xCoordinates = xCoordinate, yCoordinates = yCoordinate))
        } else null

    fun getPoiByCoordinatesAndMaxDistance(xCoordinate: Long, yCoordinate: Long, maxDistance: Long): List<Poi> =
        poiDao.findAll().filter { poi: Poi ->
            isInsidePerimeterDistance(poi, xCoordinate, yCoordinate, maxDistance)
        }

    private fun isInsidePerimeterDistance(poi: Poi, xCoordinateReference: Long, yCoordinateReference: Long, maxDistance: Long): Boolean =
        (abs(poi.xCoordinates) - abs(xCoordinateReference)).toDouble().pow(2) +
                ((abs(poi.yCoordinates) - abs(yCoordinateReference)).toDouble()).pow(2) <= maxDistance.toDouble().pow(2)

}


