package com.xyinc.gps.service

import com.xyinc.gps.dao.PoiDao
import com.xyinc.gps.model.Poi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PoiService {

    @Autowired
    private lateinit var poiDao: PoiDao

    fun getAllPoi(): List<Poi> =
        poiDao.findAll().toList()

    fun createPoi(name: String, xCoordinate: Long, yCoordinate: Long): Poi? =
        if (poiDao.findById(name).isEmpty) {
            val newPoi = Poi()
            newPoi.name = name
            newPoi.xCoordinates = xCoordinate
            newPoi.yCoordinates = yCoordinate
            val poiSaved = poiDao.save(newPoi)
            poiSaved
        } else null

    fun getPoiByCoordinatesAndMaxDistance(xCoordinate: Long, yCoordinate: Long, maxDistance: Long): List<Poi> {
        val xCoordinateMaxDistance = xCoordinate + maxDistance
        val xCoordinateMinDistance = xCoordinate - maxDistance
        val yCoordinateMaxDistance = yCoordinate + maxDistance
        val yCoordinateMinDistance = yCoordinate - maxDistance
        return poiDao.findAll().filter { poi: Poi ->
            isXRangeCoordinate(poi, xCoordinateMaxDistance, xCoordinateMinDistance) and
                    isYRangeCoordinate(poi, yCoordinateMaxDistance, yCoordinateMinDistance)
        }
    }

    fun isXRangeCoordinate(poi: Poi, xCoordinateMaxDistance: Long, xCoordinateMinDistance : Long) : Boolean =
        poi.xCoordinates in xCoordinateMinDistance..xCoordinateMaxDistance

    fun isYRangeCoordinate(poi: Poi, yCoordinateMaxDistance: Long, yCoordinateMinDistance : Long) : Boolean =
        poi.yCoordinates in yCoordinateMinDistance..yCoordinateMaxDistance

}