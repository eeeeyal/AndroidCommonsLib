package com.mdgd.j_commons.core

import android.content.Context
import com.mdgd.commons.injection.BasicProvider
import com.mdgd.j_commons.core.repo.IRepo
import com.mdgd.j_commons.core.repo.Repo
import com.mdgd.j_commons.core.repo.db.IDataBase
import com.mdgd.j_commons.core.repo.db.SQLiteManager
import com.mdgd.j_commons.core.repo.network.INetwork
import com.mdgd.j_commons.core.repo.network.NetworkManager
import com.mdgd.j_commons.core.repo.prefs.IPrefs
import com.mdgd.j_commons.core.repo.prefs.PrefsImp
import java.lang.ref.WeakReference

class ComponentProviderImpl(val ctx: Context) : BasicProvider(), IComponentProvider {

    private var repoRef: WeakReference<IRepo>? = null
    private var prefsRef: WeakReference<IPrefs>? = null
    private var networkRef: WeakReference<INetwork>? = null
    private var dbRef: WeakReference<IDataBase>? = null

    override fun getRepo(): IRepo {
        repoRef = checkIfExists(repoRef) { Repo(getNetwork(), getQuakesDB(), getPrefs()) }
        return repoRef!!.get()!!
    }

    private fun getPrefs(): IPrefs {
        prefsRef = checkIfExists(prefsRef) { PrefsImp(ctx) }
        return prefsRef!!.get()!!
    }

    private fun getQuakesDB(): IDataBase {
        dbRef = checkIfExists(dbRef) { SQLiteManager(ctx) }
        return dbRef!!.get()!!
    }

    private fun getNetwork(): INetwork {
        networkRef = checkIfExists(networkRef) { NetworkManager() }
        return networkRef!!.get()!!
    }
}
