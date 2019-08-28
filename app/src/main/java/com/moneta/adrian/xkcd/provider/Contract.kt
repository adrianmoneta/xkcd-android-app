package com.moneta.adrian.xkcd.provider

object ComicTable {
    const val NAME = "comic"

    //COLUMNS
    const val MONTH_COLUMN = "month"
    const val NUM_COLUMN = "num"
    const val LINK_COLUMN = "link"
    const val YEAR_COLUMN = "year"
    const val NEWS_COLUMN = "news"
    const val SAFE_TITLE_COLUMN = "safe_title"
    const val TRANSCRIPT_COLUMN = "transcript"
    const val ALT_COLUMN = "alt"
    const val IMG_COLUMN = "img"
    const val TITLE_COLUMN = "title"
    const val DAY_COLUMN = "day"
    const val IMG_BLOB_COLUMN = "img_blob"
    const val FAVOURED_COLUMN = "favoured"

    //COMMANDS
    const val CREATE_COMMAND = "CREATE TABLE $NAME (" +
            "$NUM_COLUMN INTEGER NOT NULL, " +
            "$MONTH_COLUMN TEXT NOT NULL, " +
            "$LINK_COLUMN TEXT NOT NULL, " +
            "$YEAR_COLUMN TEXT NOT NULL, " +
            "$NEWS_COLUMN TEXT NOT NULL, " +
            "$SAFE_TITLE_COLUMN TEXT NOT NULL, " +
            "$TRANSCRIPT_COLUMN TEXT NOT NULL, " +
            "$ALT_COLUMN TEXT NOT NULL, " +
            "$IMG_COLUMN TEXT NOT NULL, " +
            "$TITLE_COLUMN TEXT NOT NULL, " +
            "$DAY_COLUMN TEXT NOT NULL, " +
            "$IMG_BLOB_COLUMN BLOB, " +
            "$FAVOURED_COLUMN INTEGER NOT NULL DEFAULT 0, " +
            "PRIMARY KEY ($NUM_COLUMN) ON CONFLICT REPLACE)"
    const val DROP_COMMAND = "DROP TABLE IF EXISTS $NAME"
}
