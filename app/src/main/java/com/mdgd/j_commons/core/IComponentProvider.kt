package com.mdgd.j_commons.core

import com.mdgd.j_commons.core.repo.IRepo

interface IComponentProvider {
    fun getRepo(): IRepo

}
