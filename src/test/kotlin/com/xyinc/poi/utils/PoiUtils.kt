package com.xyinc.poi.utils

import com.xyinc.poi.entity.PoiEntity
import org.springframework.data.geo.Distance

class PoiUtils {

    private lateinit var poi : PoiEntity

    companion object {

        const val COORDINATE_DEFAULT = 0L

        fun init(): PoiUtils =
            PoiUtils()

        private fun get(name: String, xCoordinate : Long, yCoordinate: Long): PoiUtils{
            val poiUtils = PoiUtils()
            poiUtils.poi = PoiEntity(name, xCoordinate, yCoordinate)
            return poiUtils
        }
    }

    fun getPoi() : PoiUtils =
        PoiUtils.get("Poi", COORDINATE_DEFAULT, COORDINATE_DEFAULT)

    fun getPoiWithBlankName() : PoiUtils =
        PoiUtils.get("", COORDINATE_DEFAULT, COORDINATE_DEFAULT)

    fun getAnotherPoiInsidePerimeterOfDefaultPoi(poi: PoiEntity, distance: Long) : PoiUtils =
        PoiUtils.get("Another Poi Inside Perimeter Distance", poi.xCoordinates + distance/2, poi.yCoordinates + distance/2)

    fun getAnotherPoiOutPerimeterOfDefaultPoi(poi: PoiEntity, distance: Long) : PoiUtils =
        PoiUtils.get("Another Poi Out Perimeter Distance", poi.xCoordinates + distance, poi.yCoordinates + distance)

    fun build(): PoiEntity {
        return poi
    }
}