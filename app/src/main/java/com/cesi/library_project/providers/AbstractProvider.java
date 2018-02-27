package com.cesi.library_project.providers;

import com.cesi.library_project.database.controllers.AbstractController;
import com.cesi.library_project.database.models.Category;
import com.cesi.library_project.database.models.IIdSetter;
import com.cesi.library_project.providers.ui.AbstractComponentProvider;
import org.eclipse.swt.internal.mozilla.IIDStore;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractProvider<MODEL extends IIdSetter,
        COMPONENT_PROVIDER extends AbstractComponentProvider<MODEL>,
        TABLE_CONTROLLER extends AbstractController<MODEL>,
        FORM_COMPONENT_PROVIDER extends AbstractComponentProvider<MODEL>> {

    public static ArrayList[] mObject;
    private TABLE_CONTROLLER mController;

    protected AbstractProvider() {
        mController = createController();
    }

    protected abstract TABLE_CONTROLLER createController();

    public abstract COMPONENT_PROVIDER getThumbnailProvider(MODEL object);

    public abstract COMPONENT_PROVIDER getPageProvider(MODEL object);

    public abstract FORM_COMPONENT_PROVIDER modifyObject(MODEL object);

    public abstract FORM_COMPONENT_PROVIDER createObject();

    public TABLE_CONTROLLER getTableController() {
        return mController;
    }

    public Object[][] getAsArray() {
        List<MODEL> list = mController.list();
        Object[][] array = new Object[list.size()][];
        int i = 0;
        for (MODEL model : list) {
            array[i++] = model.toArray();
        }
        return array;
    }
}
