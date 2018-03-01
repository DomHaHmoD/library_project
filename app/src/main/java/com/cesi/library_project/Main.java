/**
 * app to manage the differents media
 * start of the app
 * team = Alex Geoffroy Jean-Luc et Dominique
 * version1.0
 * date:01/03/2018
 *
 **/


package com.cesi.library_project;

import com.cesi.library_project.database.db.DatabaseController;
import com.cesi.library_project.ui.DisplayController;
import com.cesi.library_project.ui.test.MainAreaContent;
import com.cesi.library_project.ui.test.NavBarContent;
import org.eclipse.swt.widgets.Composite;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }


    Main() {

    }

    void start() {
        DatabaseController.getInstance().init();
        Composite composite = DisplayController.getInstance().getComposite();

        NavBarContent navbar = new NavBarContent();
        navbar.implement(composite);
        //starting the screen
        MainAreaContent screen = new MainAreaContent();
        //load MainAreaContent's content into the main "composite"
        screen.implement(composite);



        DisplayController.getInstance().startApp();
    }
}
