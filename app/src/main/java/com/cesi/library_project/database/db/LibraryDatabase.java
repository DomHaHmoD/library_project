/**
 * app to manage the differents media
 * use to interface with de BDD
 * team = Alex Geoffroy Jean-Luc et Dominique
 * version1.0
 * date:01/03/2018
 *
 **/

package com.cesi.library_project.database.db;

import com.cesi.library_project.database.controllers.*;
import com.cesi.library_project.database.models.*;
import za.co.neilson.sqlite.orm.DatabaseDriverInterface;
import za.co.neilson.sqlite.orm.DatabaseInfo;
import za.co.neilson.sqlite.orm.DatabaseModel;
import za.co.neilson.sqlite.orm.ObjectModel;
import za.co.neilson.sqlite.orm.jdbc.JdbcObjectModel;
import za.co.neilson.sqlite.orm.jdbc.JdbcSqliteDatabaseDriverInterface;

import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

public class LibraryDatabase extends DatabaseModel<ResultSet, HashMap<String, Object>> {

    public LibraryDatabase() throws NoSuchFieldException, SQLException, ClassNotFoundException {
        super((Object[]) null);
    }

    @Override
    public ObjectModel<DatabaseInfo, ResultSet, HashMap<String, Object>> onCreateDatabaseInfoModel() throws ClassNotFoundException, NoSuchFieldException {
        return new JdbcObjectModel<DatabaseInfo>(this) {
            //abstract class
        };
    }

    @Override
    protected DatabaseDriverInterface<ResultSet, HashMap<String, Object>> onInitializeDatabaseDriverInterface(Object... objects) {
        return new JdbcSqliteDatabaseDriverInterface(this);
    }

    @Override
    protected void onRegisterObjectModels(HashMap<Type, ObjectModel<?, ResultSet, HashMap<String, Object>>> hashMap) throws ClassNotFoundException, NoSuchFieldException {
        /*
         * Tables Managed By This Model
         */
        for (AbstractController controller : DatabaseController.CONTROLLERS) {
            controller.initModelForDatabase(objectModels, this);
        }
    }

    @Override
    public String getDatabaseName() {
        return "library.db";
    }

    @Override
    public int getDatabaseVersion() {
        return 1;
    }

    @Override
    protected void onInsertDefaultValues() {
        CategoryController.getInstance().init(this);
        FilmController.getInstance().init(this);
        MusicController.getInstance().init(this);
        MetaDataController.getInstance().init(this);

        Category[] categories = new Category[]{
                new Category("Film", "k", false, Film.class.getSimpleName()),
                new Category("Musique", "o", false, Music.class.getSimpleName()),
                new Category("Livre", "m", false, null),
                new Category("Jeux-Video", "n", false, null)
        };

        MetaData meta_data = new MetaData("Une metadata",
                1,
                "carrefour",
                new Date(),
                "il était bien",
                Status.ABANDONNED);
        MetaDataController.getInstance().create(meta_data);
        System.out.println(meta_data.toString());
        Film film = new Film(90, meta_data);

        meta_data = new MetaData("Une chanson",
                0,
                "super u",
                new Date(),
                "c était moins bien, c était mieux avant",
                Status.ABANDONNED);
        MetaDataController.getInstance().create(meta_data);
        System.out.println(meta_data.toString());
        Music music = new Music(120, meta_data);

        meta_data = new MetaData("Sharknado",
                9,
                "leclerc",
                new Date(),
                "un requin des tornades",
                Status.ABANDONNED);
        MetaDataController.getInstance().create(meta_data);
        Film film2 = new Film(90, meta_data);

        System.out.println(music.getMetaData().toString());

        FilmController.getInstance().create(film);
        FilmController.getInstance().create(film2);
        MusicController.getInstance().create(music);

        for (Category category : categories) {
            CategoryController.getInstance().create(category);
        }
    }
}
