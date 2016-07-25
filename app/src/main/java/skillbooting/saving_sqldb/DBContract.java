package skillbooting.saving_sqldb;

import android.provider.BaseColumns;

/**
 * Created by Spriha Biswas on 7/25/2016.
 */
public class DBContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public DBContract() {}

    /* Inner class that defines the table contents */
    public static abstract class DBEntry implements BaseColumns {

        public static final String TABLE_NAME = "student_names";
        public static final String COLUMN_NAME_ID = "_id";
        public static final String COLUMN_NAME_STUDENT_NAME = "name";

    }

}
