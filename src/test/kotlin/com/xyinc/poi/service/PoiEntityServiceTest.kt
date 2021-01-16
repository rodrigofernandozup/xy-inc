package com.xyinc.poi.service

import com.xyinc.poi.entity.PoiEntity
import com.xyinc.poi.repository.PoiRepository
import org.junit.jupiter.api.Assertions
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito

class PoiEntityServiceTest {

    @Mock
    var poiRepository: PoiRepository = Mockito.mock(PoiRepository::class.java)

    @InjectMocks
    private val poiService = PoiService(poiRepository)

    //@Test
    fun getPoiByCoordinatesAndMaxDistanceTest() {
        Mockito.`when`(poiRepository.findAll()).thenReturn(findAllPoiMock())
        Assertions.assertEquals(getPoiByCoordinatesMock(), poiService.getPoiByCoordinatesAndMaxDistance(20, 10, 10))
    }

    private fun findAllPoiMock() : List<PoiEntity> =
        arrayListOf(PoiEntity(name = "Lanchonete", xCoordinates = 27, yCoordinates = 12),
            PoiEntity(name = "Posto", xCoordinates = 31, yCoordinates = 18),
            PoiEntity(name = "Joalheria", xCoordinates = 15, yCoordinates = 12),
            PoiEntity(name = "Floricultura", xCoordinates = 19, yCoordinates = 21),
            PoiEntity(name = "Pub", xCoordinates = 12, yCoordinates = 8),
            PoiEntity(name = "Supermercado", xCoordinates = 23, yCoordinates = 6),
            PoiEntity(name = "Churrascaria", xCoordinates = 28, yCoordinates = 2))


    private fun getPoiByCoordinatesMock() : List<PoiEntity> =
        arrayListOf(PoiEntity(name = "Lanchonete", xCoordinates = 27, yCoordinates = 12),
            PoiEntity(name = "Joalheria", xCoordinates = 15, yCoordinates = 12),
            PoiEntity(name = "Pub", xCoordinates = 12, yCoordinates = 8),
            PoiEntity(name = "Supermercado", xCoordinates = 23, yCoordinates = 6))

}