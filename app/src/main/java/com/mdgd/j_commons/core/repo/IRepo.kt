package com.mdgd.j_commons.core.repo

import com.mdgd.commons.result.ICallback
import com.mdgd.j_commons.core.repo.prefs.IPrefs
import com.mdgd.j_commons.dto.Quake
import com.mdgd.j_commons.dto.SearchParams

/**
 * Created by Max
 * on 23-Jun-17.
 */

interface IRepo {

    fun getPrefs(): IPrefs

    fun getEarthquakesBeforeDate(params: SearchParams, callback: ICallback<List<Quake>>)

    fun searchQuakes(params: SearchParams, callback: ICallback<List<Quake>>)

    fun save(quakes: List<Quake>)
}
