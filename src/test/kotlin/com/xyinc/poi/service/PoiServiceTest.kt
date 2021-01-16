package com.xyinc.poi.service

import com.xyinc.poi.domain.Poi
import com.xyinc.poi.domain.toPoiEntity
import com.xyinc.poi.entity.PoiEntity
import com.xyinc.poi.exceptionhandler.exception.PoiAlreadyExistsException
import com.xyinc.poi.exceptionhandler.exception.PoiInvalidCoordinateException
import com.xyinc.poi.exceptionhandler.exception.PoiNameIsBlankException
import com.xyinc.poi.repository.PoiRepository
import com.xyinc.poi.utils.PoiUtils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import java.util.*

class PoiServiceTest {

    companion object {
        private const val DISTANCE = 10L
    }

    @Mock
    var poiRepository: PoiRepository = Mockito.mock(PoiRepository::class.java)

    @InjectMocks
    private val poiService = PoiService(poiRepository)

    @Test
    fun `should insert poi success test` () {
        val poi = PoiUtils.init().poi().build()
        Mockito.`when`(poiRepository.findById(poi.name)).thenReturn(Optional.empty())
        Mockito.`when`(poiRepository.save(ArgumentMatchers.any(PoiEntity::class.java))).thenReturn(poi.toPoiEntity())
        val poiSaved = poiService.createPoi(poi)
        Assertions.assertEquals(poi, poiSaved)
    }

    @Test
    fun `should fail insert poi by poi already exists test` () {
        val poi = PoiUtils.init().poi().build()
        Mockito.`when`(poiRepository.findById(poi.name)).thenReturn(Optional.of(poi.toPoiEntity()))
        assertThrows<PoiAlreadyExistsException> { poiService.createPoi(poi) }
    }

    @Test
    fun `should fail insert poi by poi name is blank test` () {
        val poi = PoiUtils.init().poiWithBlankName().build()
        Mockito.`when`(poiRepository.findById(poi.name)).thenReturn(Optional.empty())
        Mockito.`when`(poiRepository.save(poi.toPoiEntity())).thenReturn(poi.toPoiEntity())
        assertThrows<PoiNameIsBlankException> { poiService.createPoi(poi) }
    }

    @Test
    fun `should fail insert poi by x coordinate negative value test` () {
        val poi = PoiUtils.init().poiXCoordinateNegative().build()
        Mockito.`when`(poiRepository.findById(poi.name)).thenReturn(Optional.empty())
        Mockito.`when`(poiRepository.save(poi.toPoiEntity())).thenReturn(poi.toPoiEntity())
        assertThrows<PoiInvalidCoordinateException> { poiService.createPoi(poi) }
    }

    @Test
    fun `should fail insert poi by y coordinate negative value test` () {
        val poi = PoiUtils.init().poiYCoordinateNegative().build()
        Mockito.`when`(poiRepository.findById(poi.name)).thenReturn(Optional.empty())
        Mockito.`when`(poiRepository.save(poi.toPoiEntity())).thenReturn(poi.toPoiEntity())
        assertThrows<PoiInvalidCoordinateException> { poiService.createPoi(poi) }
    }

    @Test
    fun `should get all pois success test` () {
        val poiDefault = PoiUtils.init().poi().build()
        val pois = listOf<Poi>(poiDefault,
            PoiUtils.init().poiInsidePerimeterOfDefaultPoi(poiDefault, DISTANCE).build(),
            PoiUtils.init().poiOutPerimeterOfDefaultPoi(poiDefault, DISTANCE).build())
        Mockito.`when`(poiRepository.findAll()).thenReturn(pois.map { poi -> poi.toPoiEntity() })
        val poisListed = poiService.getAllPois()
        Assertions.assertEquals(pois, poisListed)
    }

    @Test
    fun `should get pois by coordinates and distance success test` () {
        val poiDefault = PoiUtils.init().poi().build()
        val anotherPoiInsidePerimeterOfDefaultPoi = PoiUtils.init().poiInsidePerimeterOfDefaultPoi(poiDefault, DISTANCE).build()
        val anotherPoiOutPerimeterOfDefaultPoi = PoiUtils.init().poiOutPerimeterOfDefaultPoi(poiDefault, DISTANCE).build()
        val pois = listOf(poiDefault,
            anotherPoiInsidePerimeterOfDefaultPoi,
            anotherPoiOutPerimeterOfDefaultPoi
            )
        Mockito.`when`(poiRepository.findAll()).thenReturn(pois.map { poi -> poi.toPoiEntity() })
        val poisReturned = poiService.getPoiByCoordinatesAndMaxDistance(PoiUtils.COORDINATE_DEFAULT, PoiUtils.COORDINATE_DEFAULT, DISTANCE)
        Assertions.assertTrue(poiDefault in poisReturned)
        Assertions.assertTrue(anotherPoiInsidePerimeterOfDefaultPoi in poisReturned)
        Assertions.assertTrue(anotherPoiOutPerimeterOfDefaultPoi !in poisReturned)
    }

}