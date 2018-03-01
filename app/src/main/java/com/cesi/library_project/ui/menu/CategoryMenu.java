/**
 * app to manage the differents media
 * Left aera display for he menu
 * team = Alex Geoffroy Jean-Luc et Dominique
 * version1.0
 * date:01/03/2018
 *
 **/
package com.cesi.library_project.ui.menu;

import com.cesi.library_project.database.controllers.CategoryController;
import com.cesi.library_project.database.models.Category;
import com.cesi.library_project.providers.Providers;
import com.cesi.library_project.providers.ui.AbstractComponentProvider;
import com.cesi.library_project.providers.ui.category.CategoryThumbnail;
import com.cesi.library_project.ui.DisplayController;
import com.cesi.library_project.ui.IComponentProvider;
import com.cesi.library_project.ui.format.Format;
import com.cesi.library_project.ui.listeners.ICategoryClicked;
import com.cesi.library_project.ui.scroll.ScrollContent;
import com.cesi.library_project.ui.test.MainAreaContent;
import com.cesi.library_project.utils.Fonts;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.xml.ws.Provider;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the whole left menu
 */
public class CategoryMenu implements IComponentProvider, ICategoryClicked {

    private GridLayout mGrid;
    private Composite mComposite;
    private ArrayList<CategoryThumbnail> mCategoriesItem;
    private MainAreaContent mParent;
    private ScrollContent mScrollProvider;
    private Category mLastCategory;
    private Format mLastFormat;

    public CategoryMenu(@NotNull MainAreaContent parent) {
        mParent = parent;
    }

    @Nullable
    @Override
    public Composite getComposite() {
        return mComposite;
    }

    /**
     * For each Category from the database, add a new CategpryItem
     * and add it to the internal list of UI
     *
     * @param composite inject the view into it
     */
    @Override
    public void implement(Composite composite) {
        List<Category> list = Providers.CATEGORY_PROVIDER
                .getTableController().list();

        if (mGrid == null) {
            mGrid = new GridLayout(1, true);

            mScrollProvider = new ScrollContent();
            mScrollProvider.implement(composite);
            mScrollProvider.setPreferredWidthProvider(rectangle -> SWT.DEFAULT);

            mComposite = new Composite(mScrollProvider.getComposite(), SWT.PUSH);
            mScrollProvider.setChild(this);

            GridData data = new GridData();
            data.verticalAlignment = SWT.FILL;
            data.horizontalAlignment = SWT.BEGINNING;
            data.grabExcessVerticalSpace = true;

            mGrid.marginWidth = mGrid.marginBottom = mGrid.marginHeight = 0;
            mGrid.marginTop = 0;
            mGrid.marginLeft = mGrid.marginRight = 0;
            mGrid.horizontalSpacing = mGrid.verticalSpacing = 0;
            mScrollProvider.getComposite().setLayoutData(data);
            mComposite.setLayout(mGrid);
        }

        mCategoriesItem = new ArrayList<CategoryThumbnail>();

        Composite temp_composite = new Composite(mComposite, SWT.NONE);
        RowLayout layout = new RowLayout(SWT.VERTICAL);
        layout.marginTop = 12;
        layout.marginBottom = layout.marginLeft = 6;
        temp_composite.setLayout(layout);

        Label title = new Label(temp_composite, SWT.NONE);
        title.setText("Choix par Categorie");
        title.setForeground(DisplayController.getInstance()
                .getColor(120, 120, 120));

        for (Category category : list) {
            CategoryThumbnail item = Providers.CATEGORY_PROVIDER
                    .getThumbnailProvider(category);

            item.setCategoryParent(this);
            mCategoriesItem.add(item);
            item.implement(mComposite);
        }

        // add a separator lign
        Label separator = new Label(mComposite, SWT.HORIZONTAL | SWT.SEPARATOR);
        separator.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

            // create the second menu (by thumbnail or list)
        temp_composite = new Composite(mComposite, SWT.NONE);
        layout = new RowLayout(SWT.VERTICAL);
        layout.marginTop = 12;
        layout.marginBottom = layout.marginLeft = 6;
        temp_composite.setLayout(layout);

        Label display_format = new Label(temp_composite, SWT.NONE);
        display_format.setText("Type de format");
        display_format.setForeground(DisplayController.getInstance()
                .getColor(120, 120, 120));

        CategoryThumbnail[] formats = new CategoryThumbnail[]{
                new CategoryThumbnail("Vignette", Format.THUMBNAIL),
                new CategoryThumbnail("Table", Format.LIST)
        };


        for (CategoryThumbnail category : formats) {
            category.setCategoryParent(this);
            mCategoriesItem.add(category);
            category.implement(mComposite);
        }

        // add a separator lign
        Label separator2 = new Label(mComposite, SWT.HORIZONTAL | SWT.SEPARATOR);
        separator2.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        // create the third menu (rapport)
        temp_composite = new Composite(mComposite, SWT.NONE);
        layout = new RowLayout(SWT.VERTICAL);
        layout.marginTop = 12;
        layout.marginBottom = layout.marginLeft = 6;
        temp_composite.setLayout(layout);

        Label display_format2 = new Label(temp_composite, SWT.NONE);
        display_format2.setText("Information additionnelle");
        display_format2.setForeground(DisplayController.getInstance()
                .getColor(120, 120, 120));

        formats = new CategoryThumbnail[]{
                new CategoryThumbnail ("Rapport", Format.RAPPORT),
                //new CategoryThumbnail ("Table", Format.LIST)
        };


        for (CategoryThumbnail category : formats) {
            category.setCategoryParent(this);
            mCategoriesItem.add(category);
            category.implement(mComposite);
        }


    }

    /**
     * For each UI Element, dispose its ressources
     */
    @Override
    public void dispose() {
        for (IComponentProvider item : mCategoriesItem) {
            item.dispose();
        }
    }

    @Override
    public void onCategoryClicked(@Nullable Category category, @Nullable Format format) {
        if (category != null) {
            mLastCategory = category;
        }

        if (format != null) {
            mLastFormat = format;
        }

        mParent.onCategoryClicked(mLastCategory, mLastFormat);

        for (CategoryThumbnail item : mCategoriesItem) {
            item.onCategoryClicked(mLastCategory, mLastFormat);
        }
    }
}
