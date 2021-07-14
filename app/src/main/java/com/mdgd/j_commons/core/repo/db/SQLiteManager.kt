package com.mdgd.j_commons.core.repo.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.mdgd.commons.sqlite.CursorParser
import com.mdgd.j_commons.core.Constants
import com.mdgd.j_commons.dto.Quake
import java.util.*
import java.util.concurrent.locks.ReentrantReadWriteLock

/**
 * Created by Max
 * on 30-Apr-17.
 */

class SQLiteManager(context: Context) : CursorParser<Quake>(), IDataBase {
    val lock = ReentrantReadWriteLock()

    private val mDbHelper: DBHelper = DBHelper(context)

    private fun close() {
        mDbHelper.close()
    }

    private fun openWritable(): SQLiteDatabase {
        return mDbHelper.writableDatabase
    }

    private fun openReadable(): SQLiteDatabase {
        return mDbHelper.readableDatabase
    }

    @Suppress("ProtectedInFinal", "Unused")
    protected fun finalize() {
        close()
    }


    override fun fromCursor(c: Cursor): Quake {
        val quake = Quake()
        quake.id = get(c, DBHelper.COLUMN_QUAKE_ID, "")
        quake.title = get(c, DBHelper.COLUMN_TITLE, "")
        quake.link = get(c, DBHelper.COLUMN_URL, "")
        quake.magnitude = get(c, DBHelper.COLUMN_MAGNITUDE, "")
        quake.date = Date(get(c, DBHelper.COLUMN_TIME, -1L))

        quake.longitude = get(c, DBHelper.COLUMN_LNG, 0.0)
        quake.latitude = get(c, DBHelper.COLUMN_LAT, 0.0)

        return quake
    }

    override fun toContentValues(item: Quake, cv: ContentValues): ContentValues {
        cv.put(DBHelper.COLUMN_QUAKE_ID, item.id)
        cv.put(DBHelper.COLUMN_TITLE, item.title)
        cv.put(DBHelper.COLUMN_URL, item.link)
        cv.put(DBHelper.COLUMN_MAGNITUDE, item.magnitude.toString())
        cv.put(DBHelper.COLUMN_TIME, item.date?.time)
        cv.put(DBHelper.COLUMN_LNG, item.longitude)
        cv.put(DBHelper.COLUMN_LAT, item.latitude)
        return cv
    }

    override fun saveQuakes(quakes: List<Quake>) {
        lock.writeLock().lock()
        try {
            val db = openWritable()
            db.beginTransaction()
            try {
                val cv = ContentValues()
                for (quake in quakes) {
                    toContentValues(quake, cv)

                    val result = db.update(DBHelper.TABLE_QUAKES, cv, DBHelper.COLUMN_QUAKE_ID + " = '" + quake.id + "'", null)
                    if (result == 0) db.insert(DBHelper.TABLE_QUAKES, null, cv)
                }
                db.setTransactionSuccessful()
            } finally {
                db.endTransaction()
            }
        } finally {
            lock.writeLock().unlock()
        }
    }

    override fun getQuakesBulk(dateMs: Long): List<Quake> {
        val quakes = ArrayList<Quake>()
        lock.readLock().lock()
        try {
            val db = openReadable()
            val c: Cursor?
            if (dateMs == 0L) {
                c = db.query(DBHelper.TABLE_QUAKES, null, null, null, null,
                        null, DBHelper.COLUMN_TIME)
            } else {
                c = db.query(DBHelper.TABLE_QUAKES, null, DBHelper.COLUMN_TIME + " < ?",
                        arrayOf(dateMs.toString()), null, null, DBHelper.COLUMN_TIME)
            }

            if (c != null) {
                if (c.moveToLast()) {
                    var counter = 0
                    do {
                        quakes.add(fromCursor(c))
                        counter++
                    } while (counter < Constants.PAGE_SIZE && c.moveToPrevious())
                }
                c.close()
            }
        } finally {
            lock.readLock().unlock()
        }
        return quakes
    }
}
