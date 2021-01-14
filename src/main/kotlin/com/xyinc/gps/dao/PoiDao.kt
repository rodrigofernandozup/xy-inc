package com.xyinc.gps.dao

import com.xyinc.gps.model.Poi
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PoiDao : CrudRepository<Poi, String> {

}