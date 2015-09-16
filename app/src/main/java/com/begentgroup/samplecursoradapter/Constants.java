package com.begentgroup.samplecursoradapter;

import android.provider.BaseColumns;

/**
 * Created by dongja94 on 2015-09-16.
 */
public class Constants {
    public interface MessageTable extends BaseColumns {
        public static final String TABLE_NAME = "messagetbl";
        public static final String FIELD_MATE = "mate";
        public static final String FIELD_TYPE = "type";
        public static final String FIELD_MESSAGE = "message";
    }

}
