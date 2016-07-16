package com.sara.ActiveAndroid;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Bassem on 7/13/2016.
 */

@Table(name = "Item")
public class Item extends Model {

    @Column(name = "remote_id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    int remote_id;

    @Column(name = "name")
    String name;

    @Column(name = "color")
    String color;

    @Column(name = "category", onUpdate = Column.ForeignKeyAction.CASCADE,
            onDelete = Column.ForeignKeyAction.CASCADE)
    CategoryDB categoryDB;

    public Item() {
        super();
    }

    public Item(int remote_id, String name, CategoryDB categoryDB, String color) {

        this.remote_id = remote_id;
        this.categoryDB = categoryDB;
        this.name = name;
        this.color = color;
    }
}
