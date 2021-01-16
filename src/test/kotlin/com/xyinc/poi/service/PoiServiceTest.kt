package com.xyinc.poi.service

import com.xyinc.poi.entity.PoiEntity
import com.xyinc.poi.exceptionhandler.exception.PoiAlreadyExistsException
import com.xyinc.poi.exceptionhandler.exception.PoiNameIsBlankException
import com.xyinc.poi.repository.PoiRepository
import com.xyinc.poi.utils.PoiUtils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
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
        val poi = PoiUtils.init().getPoi().build()
        Mockito.`when`(poiRepository.findById(poi.name)).thenReturn(Optional.empty())
        Mockito.`when`(poiRepository.save(poi)).thenReturn(poi)
        val poiSaved = poiService.createPoi(poi)
        Assertions.assertEquals(poi, poiSaved)
    }

    @Test
    fun `should fail insert poi by poi already exists test` () {
        val poi = PoiUtils.init().getPoi().build()
        Mockito.`when`(poiRepository.findById(poi.name)).thenReturn(Optional.of(poi))
        assertThrows<PoiAlreadyExistsException> { poiService.createPoi(poi) }
    }

    @Test
    fun `should fail insert poi by poi name is blank test` () {
        val poi = PoiUtils.init().getPoiWithBlankName().build()
        Mockito.`when`(poiRepository.findById(poi.name)).thenReturn(Optional.empty())
        Mockito.`when`(poiRepository.save(poi)).thenReturn(poi)
        assertThrows<PoiNameIsBlankException> { poiService.createPoi(poi) }
    }

    @Test
    fun `should get all pois success test` () {
        val poiDefault = PoiUtils.init().getPoi().build()
        val pois = listOf<PoiEntity>(poiDefault,
            PoiUtils.init().getAnotherPoiInsidePerimeterOfDefaultPoi(poiDefault, DISTANCE).build(),
            PoiUtils.init().getAnotherPoiOutPerimeterOfDefaultPoi(poiDefault, DISTANCE).build())
        Mockito.`when`(poiRepository.findAll()).thenReturn(pois)
        val poisListed = poiService.getAllPois()
        Assertions.assertEquals(pois, poisListed)
    }

    @Test
    fun `should get pois by coordinates and distance success test` () {
        val poiDefault = PoiUtils.init().getPoi().build()
        val anotherPoiInsidePerimeterOfDefaultPoi = PoiUtils.init().getAnotherPoiInsidePerimeterOfDefaultPoi(poiDefault, DISTANCE).build()
        val anotherPoiOutPerimeterOfDefaultPoi = PoiUtils.init().getAnotherPoiOutPerimeterOfDefaultPoi(poiDefault, DISTANCE).build()
        val pois = listOf(poiDefault,
            anotherPoiInsidePerimeterOfDefaultPoi,
            anotherPoiOutPerimeterOfDefaultPoi
            )
        Mockito.`when`(poiRepository.findAll()).thenReturn(pois)
        val poisReturned = poiService.getPoiByCoordinatesAndMaxDistance(PoiUtils.COORDINATE_DEFAULT, PoiUtils.COORDINATE_DEFAULT, DISTANCE)
        Assertions.assertTrue(poiDefault in poisReturned)
        Assertions.assertTrue(anotherPoiInsidePerimeterOfDefaultPoi in poisReturned)
        Assertions.assertTrue(anotherPoiOutPerimeterOfDefaultPoi !in poisReturned)
    }

}