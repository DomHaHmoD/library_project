/**
 * app to manage the differents media
 * use to generate a thumbnail (in format list)
 * team = Alex Geoffroy Jean-Luc et Dominique
 * version1.0
 * date:01/03/2018
 *
 **/

package com.cesi.library_project.providers.ui.film;

import com.cesi.library_project.database.controllers.FilmController;
import com.cesi.library_project.database.controllers.MetaDataController;
import com.cesi.library_project.database.controllers.FilmController;
import com.cesi.library_project.database.models.Film;
import com.cesi.library_project.database.models.MetaData;
import com.cesi.library_project.database.models.Film;
import com.cesi.library_project.providers.ui.AbstractComponentProvider;
import com.cesi.library_project.ui.DisplayController;
import com.cesi.library_project.ui.IComponentProvider;
import com.cesi.library_project.ui.listeners.IThumbnailClicked;
import com.cesi.library_project.utils.Fonts;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FilmThumbnail extends AbstractComponentProvider<Film> implements IThumbnailClicked, IComponentProvider {

    private Composite mComposite;
    private Image mImage;
    private boolean loaded;
    private Device display;
    private Film film;
    private DisplayController mChildComposite;

    public FilmThumbnail(Film object) {
        super(object);
    }

    @Nullable
    @Override
    public Composite getComposite() {
        return null;
    }

    @Override
    public void implement(@NotNull Composite composite) {

        //proxy composite to display the internal component easily
        mComposite = new Composite(composite, SWT.NONE);
        mComposite.setBackground(DisplayController.getInstance().getColor(255, 255, 255));

        GridLayout layout = new GridLayout(1, false);
        layout.marginTop = layout.marginBottom = layout.marginLeft = layout.marginRight = 0;
        layout.marginHeight = layout.marginWidth = 0;
        layout.verticalSpacing = 6;
        mComposite.setLayout(layout);

        mImage = DisplayController.getInstance()
                .loadImage("/com/cesi/resources/thumbnail_video.png", 150);

        Label label = new Label(mComposite, SWT.NONE);
        label.setImage(mImage);

        // add title
        GridData data = new GridData();
        data.horizontalAlignment = SWT.CENTER;
        Label text = new Label(mComposite, SWT.NONE);
        if(getModel() != null)
        text.setText(getModel().getMetaData().getTitle());
        text.setLayoutData(data);

        // to add note
        GridData data1 = new GridData();
        data1.horizontalAlignment = SWT.CENTER;
        Label text1 = new Label(mComposite, SWT.NONE);
        loaded = Fonts.getInstance ()
                .loadFont ("/com/cesi/resources/untitled-font-1.ttf", "untitled-font-1.ttf");
        text1.setFont(Fonts.getInstance().getFont("untitled-font-1", 12));

        // Set fore color
        Color color = new Color(display, 255, 215, 0);
        text1.setForeground(color);

        // switch to convert note in stars
        String converttext = Integer.toString(getModel().getMetaData().getNote());
        switch(converttext){
            case "0" : text1.setText(" ");
                break;
            case "1" : text1.setText("b");
                break;
            case "2" : text1.setText("bb");
                break;
            case "3" : text1.setText("bbb");
                break;
            case "4" : text1.setText("bbbb");
                break;
            case "5" : text1.setText("bbbbb");
                break;
        }

        text1.setLayoutData(data1);

        label.addMouseListener(new MouseListener () {

            @Override
            public void mouseDoubleClick(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseDown(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseUp(MouseEvent mouseEvent) {
                /*Shell shell = new Shell();
                //System.out.println ("click" + text);
                FilmFormUpdate filmformupdate = new FilmFormUpdate (film);
                filmformupdate.implement (shell);

                shell.open();*/

                String title2 = getModel().getMetaData().getTitle();

                List<Object> list = new ArrayList<> ();
                //list.add();


                // create the form

                Shell shell = new Shell(DisplayController.getInstance().getDisplay(),
                        SWT.SHELL_TRIM);

                //Composite mComposite2 = new Composite(shell, SWT.NONE);
                //mComposite2.setBackground(DisplayController.getInstance().getColor(255, 255, 255));

                GridLayout layout4 = new GridLayout(2, false); // 2 columns
                layout4.marginTop = layout.marginBottom = layout.marginLeft = layout.marginRight = 0;
                layout4.marginHeight = layout.marginWidth = 0;
                layout4.verticalSpacing = 6;
                //shell.setLayout(layout4);
                shell.setLayout(layout4);

                GridData griddata = new GridData(SWT.FILL, SWT.FILL);
                shell.setLayoutData(griddata);
                //shell.setLayoutData(griddata);
                griddata.widthHint = 600;
                griddata.heightHint = 600;

                Label text = new Label(shell, SWT.FILL);
                if(getModel() != null) {
                    text.setText(getModel().getMetaData().getTitle());
                }

                /**
                 * Form for the video
                 * **/
                Label textvide = new Label(shell, SWT.FILL);
                textvide.setText("Fiche information pour la video ");

                //input field id
                Label textid = new Label(shell, SWT.BEGINNING);
                textid.setText ("Id");
                Label id = new Label(shell, SWT.BORDER);
                String convertid = String.valueOf(getModel().getId ());
                id.setText(convertid);
                id.setSize (100, 20);
                //input field name
                Label textname = new Label(shell, SWT.BEGINNING);
                textname.setText ("Titre");
                Text name = new Text(shell, SWT.BORDER);
                name.setText(getModel().getMetaData ().getTitle ());
                name.setSize (100, 20);
                //input field duration
                Label textduration = new Label(shell, SWT.FILL);
                textduration.setText ("Duree");
                Text duration = new Text(shell, SWT.BORDER);
                String convertduration = String.valueOf(getModel().getDuration());
                duration.setText(convertduration);
                //input field note
                Label textnote = new Label(shell, SWT.FILL);
                textnote.setText ("Note");
                Text note = new Text(shell, SWT.BORDER);
                String convertnote = Integer.toString(getModel().getMetaData().getNote());
                note.setText(convertnote);
                //input field comments
                Label textcomment = new Label(shell, SWT.FILL);
                textcomment.setText ("Commentaire");
                Text comment = new Text(shell, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
                comment.setSize (200,200);
                comment.setText(getModel().getMetaData ().getCommentaire ());

                Button buttonSubmit = new Button(shell, SWT.PUSH);
                buttonSubmit.setText("Valider votre saisie");
                buttonSubmit.addMouseListener(new MouseListener() {


                    @Override
                    public void mouseDoubleClick(MouseEvent mouseEvent) {

                    }

                    @Override
                    public void mouseDown(MouseEvent mouseEvent) {

                    }

                    @Override
                    public void mouseUp(MouseEvent mouseEvent) {

                        /**
                         * code to update a new video
                         **/

                        MetaData metaData = new MetaData();
                        metaData.setTitle(name.getText());
                        metaData.setCommentaire(comment.getText());
                        // modify to convert TextField in int for note
                        int note1 = new Integer(String.valueOf (note.getText()));
                        System.out.println ("note1:" + note1);
                        metaData.setNote(note1);
                        MetaDataController.getInstance().update(metaData); // change create with update
                        // modify to convert TextField in int for duration
                        int duration1 = new Integer(String.valueOf (duration.getText()));
                        System.out.println ("duration:" + duration1);

                        //int id1 = new Integer(String.valueOf (id));
                        //System.out.println ("id:" + id1);

                        Film film = new Film(duration1, metaData);
                        FilmController.getInstance().update(film);

                        // add to close the Form Window

                        shell.dispose ();
                        //shell.layout(true);


                    }
                });

                Button buttonCancel = new Button(shell, SWT.PUSH);
                buttonCancel.setText("Retour sans valider");
                buttonCancel.addMouseListener(new MouseListener() {

                    @Override
                    public void mouseDoubleClick(MouseEvent mouseEvent) {
                    }

                    @Override
                    public void mouseDown(MouseEvent mouseEvent) {
                    }

                    @Override
                    public void mouseUp(MouseEvent mouseEvent) {
                        // add to close the Form Windows
                        shell.dispose (); // add to close the Form Windows
                    }
                });

                shell.open ();

            }
        });

    }

    @Override
    public void dispose() {
        mComposite.dispose();
    }

    @Override
    public void onThumbnailClicked() {
    }
}
