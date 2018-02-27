package com.cesi.library_project.database.controllers;

import com.cesi.library_project.database.db.LibraryDatabase;
import com.cesi.library_project.database.models.IIdSetter;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import za.co.neilson.sqlite.orm.ObjectModel;

import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Generic class which takes a class in parameters and manage all the CRUDs operations
 * <p>
 * It is mandatory to extends it using extends AbstractController<class name like Category>
 *
 * @param <A_MODEL_CLASS> the class name which will be used
 */
public abstract class AbstractController<A_MODEL_CLASS extends IIdSetter> implements ICRUD<A_MODEL_CLASS> {

    private List<IModificationListener> mListeners = new ArrayList<>();
    private List<A_MODEL_CLASS> mCache = new ArrayList();
    private HashMap<Long, A_MODEL_CLASS> mCacheIndex = new HashMap<>();

    //the current database instance
    private LibraryDatabase mLibrary;

    //the class instances provider
    private ObjectModel<A_MODEL_CLASS, ResultSet, HashMap<String, Object>> mProvider;

    //we protect the standard constructor
    protected AbstractController() {

    }

    public void register(IModificationListener listener) {
        if(listener != null && !mListeners.contains(listener)) {
            mListeners.add(listener);
        }
    }

    public void unregister(IModificationListener listener) {
        if(listener != null && mListeners.contains(listener)) {
            mListeners.remove(listener);
        }
    }

    /**
     * Get the Class used to implements instances
     *
     * @return the Class to use to implements instances
     */
    @NotNull
    protected abstract Class<A_MODEL_CLASS> getModelClass();

    public void initModelForDatabase(HashMap<Type, ObjectModel<?, ResultSet, HashMap<String, Object>>> objectModels, LibraryDatabase instance) throws NoSuchFieldException, ClassNotFoundException {
        objectModels.put(getModelClass(), createJDBCObject(instance));
    }

    protected abstract ObjectModel<A_MODEL_CLASS, ResultSet, HashMap<String, Object>> createJDBCObject(LibraryDatabase instance) throws NoSuchFieldException, ClassNotFoundException;

    /**
     * Init the current Controller
     * Mandatory to call it after the database initialisation
     *
     * @param database
     */
    @Override
    public void init(@NotNull LibraryDatabase database) {
        mLibrary = database;
        mProvider = mLibrary.getObjectModel(getModelClass());
    }

    /**
     * Create a specific instance in the database
     *
     * @param model the model to save in the database
     */
    @Override
    public void create(@NotNull A_MODEL_CLASS model) {
        try {
            list();
            long id = mProvider.insert(model);
            System.out.println("saving in " + id + " " + model.getClass().getSimpleName());
            if (id > 0) {
                model.setId(id);
                mCache.add(model);
                mCacheIndex.put(model.getId(), model);
            }

            for (IModificationListener listener : mListeners) {
                    listener.onCreate(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieve every object models from the dabase
     *
     * @return a non null list
     */
    @NotNull
    @Override
    public List<A_MODEL_CLASS> list() {
        try {
            if(mCache.size() <= 0) {
                mCache = mProvider.getAll();

                for (A_MODEL_CLASS model : mCache) {
                    mCacheIndex.put(model.getId(), model);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mCache;
    }

    /**
     * Update the given model into the database
     *
     * @param model the model to update
     */
    @Override
    public void update(@NotNull A_MODEL_CLASS model) {
        try {
            /*mProvider.update(model);

            for (IModificationListener listener : mListeners) {
                listener.onUpdate(model);
            }*/

            //list();
            long id = mProvider.insert(model);
            System.out.println("updating in " + id + " " + model.getClass().getSimpleName());
            if (id > 0) {
                model.setId(id);
                mCache.add(model);
                mCacheIndex.put(model.getId(), model);
            }

            for (IModificationListener listener : mListeners) {
                listener.onUpdate(model);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    public A_MODEL_CLASS findById(long id) {
        list();
        return mCacheIndex.get(id);
    }

    /**
     * Delete a given model from the database
     *
     * @param model the existing model
     */
    @Override
    public void delete(@NotNull A_MODEL_CLASS model) {
        try {
            mCacheIndex.remove(model.getId());
            mCache.remove(model);
            //remove(model);

            mProvider.delete(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public interface IModificationListener<A_MODEL_CLASS extends IIdSetter> {

        void onCreate(A_MODEL_CLASS object);

        void onUpdate(A_MODEL_CLASS object);
    }
}
