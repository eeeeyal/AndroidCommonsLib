package com.mdgd.j_commons.core.repo.network

import com.mdgd.commons.result.ICallback
import com.mdgd.j_commons.dto.Quake
import java.util.*

/**
 * Created by Max
 * on 23-Jun-17.
 */

interface INetwork {
    fun getEarthquakes(start: Date, end: Date, listener: ICallback<List<Quake>>)

    fun checkNewEarthquakes(lastUpdate: Date, listener: ICallback<List<Quake>>)
}
