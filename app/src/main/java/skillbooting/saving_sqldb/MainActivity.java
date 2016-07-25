package skillbooting.saving_sqldb;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText et_add, et_read, et_delete, et_update_row, et_update_name;
    DBHandling dbObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_add=(EditText)findViewById(R.id.et_add);
        et_read=(EditText)findViewById(R.id.et_read);
        et_delete=(EditText)findViewById(R.id.et_delete);
        et_update_row=(EditText)findViewById(R.id.et_update_row);
        et_update_name=(EditText)findViewById(R.id.et_update_name);
        dbObj = new DBHandling(this);
        //can operate here itself using the following,\
        // but for neater coding we will have all database functions in the DBHandling class
        //SQLiteDatabase db = dbObj.getWritableDatabase();

    }

    public void addData(View v){
        System.out.println("New RowId = "+dbObj.addDB(et_add.getText().toString()));
    }
    public void readData(View v){
        System.out.println("Total = "+dbObj.readDB(Integer.parseInt(et_read.getText().toString())));
    }
    public void deleteData(View v){
        System.out.println("Delete  = "+dbObj.deleteDB(Integer.parseInt(et_delete.getText().toString())));
    }
    public void updateData(View v){
        System.out.println("Update Count = "+dbObj.updateDB(Integer.parseInt(et_update_row.getText().toString()),
                et_update_name.getText().toString()));
    }
}
