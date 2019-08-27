package com.moneta.adrian.xkcd.provider

import android.content.ContentProvider
import android.content.ContentResolver
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import com.moneta.adrian.xkcd.provider.UriHelper.BITMAP_PATH
import com.moneta.adrian.xkcd.provider.UriHelper.COMICS_PATH
import com.moneta.adrian.xkcd.provider.UriHelper.COMIC_PATH
import com.moneta.adrian.xkcd.provider.UriHelper.CONTENT_AUTHORITY
import com.moneta.adrian.xkcd.provider.UriHelper.FAVOURITES_PATH

class ComicsContentProvider : ContentProvider() {

    companion object {
        const val COMIC = 100
        const val COMICS = 200
        const val BITMAP = 300
        const val FAVOURITES = 400
    }

    private var _db: ComicDatabase? = null

    private val db get() : ComicDatabase = _db!!
    private val uriMatcher: UriMatcher = buildUriMatcher()
    private val comicsQueryBuilder = SQLiteQueryBuilder().apply { tables = ComicTable.NAME }



    override fun onCreate() : Boolean {
        _db = ComicDatabase(context!!)
        return true
    }

    @Throws(UnsupportedOperationException::class)
    override fun getType(uri: Uri): String {
        return when(uriMatcher.match(uri)) {
            COMIC -> "${ContentResolver.CURSOR_ITEM_BASE_TYPE}/$CONTENT_AUTHORITY/$COMIC_PATH"
            COMICS -> "${ContentResolver.CURSOR_DIR_BASE_TYPE}/$CONTENT_AUTHORITY/$COMICS_PATH"
            BITMAP -> "${ContentResolver.CURSOR_DIR_BASE_TYPE}/$CONTENT_AUTHORITY/$BITMAP_PATH"
            FAVOURITES -> "${ContentResolver.CURSOR_DIR_BASE_TYPE}/$CONTENT_AUTHORITY/$FAVOURITES_PATH"
            else -> throw UnsupportedOperationException("Unknown uri [$uri]")
        }
    }




    override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor? {
        return when(uriMatcher.match(uri)) {
            COMIC -> queryComic(uri)
            else -> throw UnsupportedOperationException("Query operation not supported for uri: [$uri]")
        }
    }

    private fun queryComic(uri: Uri): Cursor {
        val issueNumber = uri.pathSegments.last()
        return comicsQueryBuilder.query(
            db.readableDatabase,
            null,
            "${ComicTable.NUM_COLUMN} = ?",
            arrayOf(issueNumber),
            null,
            null,
            null)
    }



    override fun insert(uri: Uri, contentValues: ContentValues?): Uri? {
        contentValues ?: return null
        when(uriMatcher.match(uri)) {
            COMIC -> insertComic(contentValues)
            else -> throw UnsupportedOperationException("Insert operation not supported for uri: [$uri]")
        }
        return null
    }

    private fun insertComic(cv: ContentValues) {
        db.writableDatabase.insert(ComicTable.NAME, null, cv)
        val issueNumber = cv.getAsString(ComicTable.NUM_COLUMN)?.toIntOrNull() ?: return
        val issueUri = UriHelper.buildComicUri(issueNumber) ?: return
        context?.contentResolver?.notifyChange(issueUri, null)
    }






    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun buildUriMatcher(): UriMatcher {
        val matcher = UriMatcher(UriMatcher.NO_MATCH)
        matcher.addURI(CONTENT_AUTHORITY, "$COMIC_PATH/#", COMIC)
        matcher.addURI(CONTENT_AUTHORITY, "$COMICS_PATH/", COMICS)
        matcher.addURI(CONTENT_AUTHORITY, "$BITMAP_PATH/#", BITMAP)
        matcher.addURI(CONTENT_AUTHORITY, "$FAVOURITES_PATH/", FAVOURITES)
        return matcher
    }
}