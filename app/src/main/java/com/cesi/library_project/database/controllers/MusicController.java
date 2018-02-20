package com.cesi.library_project.database.controllers;

import com.cesi.library_project.database.db.LibraryDatabase;
import com.cesi.library_project.database.models.Film;
import com.cesi.library_project.database.models.Music;
import com.sun.istack.internal.NotNull;
import za.co.neilson.sqlite.orm.ObjectModel;
import za.co.neilson.sqlite.orm.jdbc.JdbcObjectModel;

import java.sql.ResultSet;
import java.util.HashMap;

public class MusicController extends AbstractController<Music> {
    private static final MusicController CATEGORY_CONTROLLER = new MusicController();

    public static MusicController getInstance() {
        return CATEGORY_CONTROLLER;
    }

    private MusicController() {
        super();
    }

    @NotNull
    @Override
    protected Class<Music> getModelClass() {
        return Music.class;
    }

    @Override
    protected ObjectModel<Music, ResultSet, HashMap<String, Object>> createJDBCObject(LibraryDatabase instance) throws NoSuchFieldException, ClassNotFoundException {
        return new JdbcObjectModel<Music>(instance) {
        };
    }
}
