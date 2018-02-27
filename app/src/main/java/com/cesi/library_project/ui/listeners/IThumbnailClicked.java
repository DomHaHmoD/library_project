package com.cesi.library_project.ui.listeners;

import com.cesi.library_project.database.models.Category;
import com.cesi.library_project.ui.format.Format;
import org.jetbrains.annotations.Nullable;

/**
 * Manage the event dispatching representing a click on a specific menu item
 */
public interface IThumbnailClicked {

    //void onCategoryClicked(@Nullable Category category,
    //@Nullable Format format);
    //}
    void onThumbnailClicked ();
}
