package com.cesi.library_project.providers.ui.music;

import com.cesi.library_project.database.controllers.MetaDataController;
import com.cesi.library_project.database.controllers.MusicController;
import com.cesi.library_project.database.models.MetaData;
import com.cesi.library_project.database.models.Music;
import com.cesi.library_project.providers.ui.AbstractComponentProvider;
import com.cesi.library_project.ui.DisplayController;
import com.cesi.library_project.ui.content.CategoryListContent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.RandomAccessFile;

import static java.lang.Integer.parseInt;

public class MusicForm extends AbstractComponentProvider<Music> {
    private Composite mComposite;
    private Image mImage;
    private String note;
    private int note1;

    private int note3;

    public MusicForm(Music object) {
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

        GridLayout layout = new GridLayout(2, false); // 2 columns
        layout.marginTop = layout.marginBottom = layout.marginLeft = layout.marginRight = 0;
        layout.marginHeight = layout.marginWidth = 0;
        layout.verticalSpacing = 6;
        mComposite.setLayout(layout);

        GridData griddata = new GridData(SWT.FILL, SWT.FILL);
        mComposite.setLayoutData(griddata);
        griddata.widthHint = 600;
        griddata.heightHint = 600;

        Label text = new Label(mComposite, SWT.FILL);
        if(getModel() != null) {
            text.setText(getModel().getMetaData().getTitle());
        }

        /**
         * Form for the music
         * **/
        Label textvide = new Label(mComposite, SWT.FILL);
        textvide.setText(" Formulaire de saisie des informations pour la musique ");

        //input field name
        Label textname = new Label(mComposite, SWT.BEGINNING);
        textname.setText ("Titre");
        Text name = new Text(mComposite, SWT.BORDER);
        name.setSize (100, 20);
        //input field duration
        Label textduration = new Label(mComposite, SWT.FILL);
        textduration.setText ("Duree");
        Text duration = new Text(mComposite, SWT.BORDER);
        //input field note
        Label textnote = new Label(mComposite, SWT.FILL);
        textnote.setText ("Note");
        Text note = new Text(mComposite, SWT.BORDER);
        //input field comments
        Label textcomment = new Label(mComposite, SWT.FILL);
        textcomment.setText ("Commentaire");
        Text comment = new Text(mComposite, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
        comment.setSize (200,200);

        Button buttonSubmit = new Button(mComposite, SWT.PUSH);
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
                 * code to create a new music
                 **/

                MetaData metaData = new MetaData();
                metaData.setTitle(name.getText());
                metaData.setCommentaire(comment.getText());
                // modify to convert TextField in int for note
                int note1 = new Integer(String.valueOf (note.getText()));
                System.out.println ("note1:" + note1);
                metaData.setNote(note1);
                MetaDataController.getInstance().create(metaData);
                // modify to convert TextField in int for duration
                int duration1 = new Integer(String.valueOf (duration.getText()));
                System.out.println ("duration:" + duration1);
                Music music = new Music(duration1, metaData);
                MusicController.getInstance()
                        .create(music);
                //TODO create new metadata from inputs
                //TODO insert new metadata
                //TODO create new music
                //TODO insert new music

                composite.dispose (); // add to close the Form Windows

            }
        });

        Button buttonCancel = new Button(mComposite, SWT.PUSH);
        //buttonCancel.setText("Retour sans valider");
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
                composite.dispose (); // add to close the Form Windows
            }
        });

    }

    @Override
    public void dispose() {
        mComposite.dispose();
    }

    public void setLayoutData(GridData griddata) {
    }
}
