package com.sara.ActiveAndroid;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

/**
 * Created by Bassem on 7/13/2016.
 */

@Table(name = "category")
public class CategoryDB extends Model {

    @Column(name = "cat_id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    int cat_id;

    @Column(name = "cat_name")
    String cat_name;

    public CategoryDB() {
        super();
    }

    public CategoryDB(int cat_id, String cat_name) {
        this.cat_id = cat_id;
        this.cat_name = cat_name;
    }

    public List<Item> getItem() {
        return getMany(Item.class, "category");
    }
}
