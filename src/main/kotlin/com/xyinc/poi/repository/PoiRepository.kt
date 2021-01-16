package com.xyinc.poi.repository

import com.xyinc.poi.entity.PoiEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PoiRepository : CrudRepository<PoiEntity, String> {

}