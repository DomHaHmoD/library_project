package com.cesi.library_project.ui.content;

import com.cesi.library_project.database.controllers.AbstractController;
import com.cesi.library_project.database.models.Category;
import com.cesi.library_project.database.models.IIdSetter;
import com.cesi.library_project.providers.AbstractProvider;
import com.cesi.library_project.providers.Providers;
import com.cesi.library_project.providers.ui.AbstractComponentProvider;
import com.cesi.library_project.table.Main_Form;
import com.cesi.library_project.tableau.OeuvreForm;
import com.cesi.library_project.ui.DisplayController;
import com.cesi.library_project.ui.IComponentProvider;
import com.cesi.library_project.ui.scroll.ScrollContent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryTableContent implements IComponentProvider, AbstractController.IModificationListener {
    private final ScrollContent mScrollProvider;
    private final Category mCategory;
    private Composite mChildComposite;

    public CategoryTableContent(Category category) {
        mScrollProvider = new ScrollContent();

        mCategory = category;
    }

    @Nullable
    @Override
    public Composite getComposite() {
        return mChildComposite;
    }

    @Override
    public void implement(@NotNull Composite composite) {

        AbstractProvider provider = Providers.getProvider(mCategory.getKlass());


        //proxy composite to display the internal component easily
        mScrollProvider.implement(composite);
        mChildComposite = new Composite(mScrollProvider.getComposite(), SWT.EMBEDDED);
        mChildComposite.setBackground(DisplayController.getInstance().getColor(255, 255, 255));

        mScrollProvider.setChild(this);

        RowLayout layout = new RowLayout(SWT.HORIZONTAL);
        layout.wrap = true;
        layout.marginTop = layout.marginBottom = layout.marginLeft = layout.marginRight = 24;
        layout.marginHeight = layout.marginWidth = 0;
        layout.spacing = 24;
        mChildComposite.setLayout(layout);

        if (null != provider) {

            provider.getTableController().register(this);

            AbstractController<IIdSetter> controller = provider.getTableController();
            List<IIdSetter> list = controller.list();

            List<Object[]> rows = new ArrayList<>();
            String[] columnNames = null;

            for (IIdSetter object : list) {
                rows.add(object.toArray());
                if(columnNames == null) columnNames = object.getColumnNames();
            }


            mChildComposite.setBackground(DisplayController.getInstance().getColor(0, 0, 0));

            java.awt.Frame frame = SWT_AWT.new_Frame(mChildComposite);

            try {
                try {
                    //frame.add(new OeuvreForm ()); // call old tableau
                    frame.add(new Main_Form ());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace ();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            /*Object[][] data = new Object[rows.size()][];
            int i = 0;
            for(Object[] object : rows) {
                data[i++] = object;
            }

            if(columnNames != null) {
                JTable table = new JTable(data, columnNames);
                frame.add(table);
                frame.add(new JPanel());
            }*/
        }
    }

    @Override
    public void dispose() {
        mScrollProvider.dispose();
    }

    public void resize() {
        mChildComposite.layout();
        DisplayController.getInstance().layout(mChildComposite);
    }

    @Override
    public void onCreate(IIdSetter object) {
        //add the new item
        AbstractProvider provider = Providers.getProvider(mCategory.getKlass());

        AbstractComponentProvider<IIdSetter> component = provider.getThumbnailProvider(object);
        component.implement(mChildComposite);

        mChildComposite.layout();
        DisplayController.getInstance().layout(mChildComposite);

    }

    @Override
    public void onUpdate(IIdSetter object) {

    }
}