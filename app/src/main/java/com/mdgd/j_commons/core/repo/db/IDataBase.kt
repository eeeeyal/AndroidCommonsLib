package com.mdgd.j_commons.core.repo.db

import com.mdgd.j_commons.dto.Quake

/**
 * Created by Max
 * on 23-Jun-17.
 */

interface IDataBase {

    fun saveQuakes(quakes: List<Quake>)

    fun getQuakesBulk(dateMs: Long): List<Quake>
}
