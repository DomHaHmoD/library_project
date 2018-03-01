package com.cesi.library_project.ui;

import com.cesi.library_project.utils.Fonts;
import com.cesi.library_project.utils.Utils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.util.HashMap;

public class DisplayController {
    private final static DisplayController CONTROLLER = new DisplayController();
    private final Display mDisplay;
    private final Shell mShell;
    private final GridLayout mLayout;

    public static DisplayController getInstance() {
        return CONTROLLER;
    }

    private DisplayController() {
        mDisplay = new Display();
        mShell = new Shell(mDisplay, SWT.SHELL_TRIM);

        mLayout = new GridLayout(1, true);
        mLayout.horizontalSpacing = mLayout.verticalSpacing = 0;
        mLayout.marginTop = mLayout.marginBottom = 0;
        mLayout.marginLeft = mLayout.marginRight = 0;
        mLayout.marginWidth = mLayout.marginHeight = 0;
        mShell.setLayout(mLayout);
        mShell.setBackground(getColor(100, 100, 100));

        boolean loaded = Fonts.getInstance()
                .init(mDisplay)
                .loadFont("/com/cesi/resources/font.ttf", "font.ttf");

        loaded &= Fonts.getInstance()
                .loadFont("/com/cesi/resources/cesi.ttf","cesi.ttf");

        System.out.println("loaded := " + loaded);
    }

    public Composite getComposite() {
        return mShell;
    }

    public Display getDisplay() {
        return mDisplay;
    }

    public void startApp() {
        mShell.setSize(1600, 1000);
        mShell.setMinimumSize(300, 300);
        mShell.open();


        while (!mShell.isDisposed()) {
            if (!mDisplay.readAndDispatch())
                mDisplay.sleep();
        }

        mDisplay.dispose();
    }

    public void layout(Control control) {
        Composite current_composite = control.getParent();
        while (current_composite != null) {
            current_composite.layout();
            current_composite = current_composite.getParent();
        }
    }

    public Color getColor(int red, int green, int blue) {
        return new Color(mDisplay, red, green, blue);
    }

    private HashMap<String, Image> mImages = new HashMap<>();

    public Image loadImage(String path, int max_height) {
        String key = max_height + " " + path;
        Image cache = mImages.get(key);

        if (null != cache) {
            return cache;
        }
        Image image = Utils.loadImageFromResources(mDisplay, path);
        cache = Utils.resizeImage(image, mDisplay, 1, max_height);
        mImages.put(key, cache);
        return cache;
    }
}
