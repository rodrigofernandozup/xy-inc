package com.xyinc.poi.utils

import com.xyinc.poi.domain.Poi

class PoiUtils {

    private lateinit var poi : Poi

    companion object {

        const val POI_NAME_DEFAULT = "Poi"
        const val COORDINATE_DEFAULT = 0L

        fun init(): PoiUtils =
            PoiUtils()

        private fun get(name: String, xCoordinate : Long, yCoordinate: Long): PoiUtils{
            val poiUtils = PoiUtils()
            poiUtils.poi = Poi(name, xCoordinate, yCoordinate)
            return poiUtils
        }
    }

    fun poi() : PoiUtils =
        get(POI_NAME_DEFAULT, COORDINATE_DEFAULT, COORDINATE_DEFAULT)

    fun poiWithBlankName() : PoiUtils =
        get("", COORDINATE_DEFAULT, COORDINATE_DEFAULT)

    fun poiInsidePerimeterOfDefaultPoi(poi: Poi, distance: Long) : PoiUtils =
        get("Another Poi Inside Perimeter Distance", poi.xCoordinate + distance/2, poi.yCoordinate + distance/2)

    fun poiOutPerimeterOfDefaultPoi(poi: Poi, distance: Long) : PoiUtils =
        get("Another Poi Out Perimeter Distance", poi.xCoordinate + distance, poi.yCoordinate + distance)

    fun poiXCoordinateNegative() : PoiUtils =
        get(POI_NAME_DEFAULT, -1, COORDINATE_DEFAULT)

    fun poiYCoordinateNegative() : PoiUtils =
        get(POI_NAME_DEFAULT, COORDINATE_DEFAULT, -1)

    fun build(): Poi {
        return poi
    }
}