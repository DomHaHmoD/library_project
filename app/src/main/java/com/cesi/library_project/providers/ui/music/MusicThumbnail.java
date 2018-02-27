package com.cesi.library_project.providers.ui.music;

import com.cesi.library_project.database.controllers.AbstractController;
import com.cesi.library_project.database.controllers.MetaDataController;
import com.cesi.library_project.database.controllers.MusicController;
import com.cesi.library_project.database.models.*;
import com.cesi.library_project.providers.AbstractProvider;
import com.cesi.library_project.providers.MusicProvider;
import com.cesi.library_project.providers.ui.AbstractComponentProvider;
import com.cesi.library_project.ui.DisplayController;
import com.cesi.library_project.ui.content.CategoryListContent;
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

public class MusicThumbnail extends AbstractComponentProvider<Music> implements IThumbnailClicked {
    private Composite mComposite;
    private Image mImage;
    private Object loaded;
    private Device display;
    private String text;
    private MusicProvider provider;
    private Music MUSIC;
    private Object parent;
    private CategoryListContent mCategoryContent;
    private Category category;
    private Composite mContent;



    public MusicThumbnail(Music object) {
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
                .loadImage("/com/cesi/resources/thumbnail_music.png", 150);

        Label label = new Label(mComposite, SWT.NONE);
        label.setImage(mImage);

        // to add title
        GridData data = new GridData();
        data.horizontalAlignment = SWT.CENTER;
        Label text = new Label(mComposite, SWT.NONE);
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

        label.addMouseListener(new MouseListener() {

            @Override
            public void mouseDoubleClick(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseDown(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseUp(MouseEvent mouseEvent) {
                System.out.println ("click" + text);

                String title2 = getModel().getMetaData().getTitle();

                List<Object> list = new ArrayList<> ();
                //list.add();


                // create the form

                Shell shell = new Shell(DisplayController.getInstance().getDisplay(),
                        SWT.SHELL_TRIM);

                //mComposite = new Composite(shell, SWT.NONE);
                //mComposite.setBackground(DisplayController.getInstance().getColor(255, 255, 255));

                GridLayout layout4 = new GridLayout(2, false); // 2 columns
                layout4.marginTop = layout.marginBottom = layout.marginLeft = layout.marginRight = 0;
                layout4.marginHeight = layout.marginWidth = 0;
                layout4.verticalSpacing = 6;
                shell.setLayout(layout4);

                GridData griddata = new GridData(SWT.FILL, SWT.FILL);
                shell.setLayoutData(griddata);
                griddata.widthHint = 600;
                griddata.heightHint = 600;

                Label text = new Label(shell, SWT.FILL);
                if(getModel() != null) {
                    text.setText(getModel().getMetaData().getTitle());
                }

                /**
                 * Form for the music
                 * **/
                Label textvide = new Label(shell, SWT.FILL);
                textvide.setText("Fiche information pour la musique ");

                    //input field id
                Label textid = new Label(shell, SWT.BEGINNING);
                textid.setText ("Id");
                Text id = new Text(shell, SWT.BORDER);
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

                        //int id1 = new Integer(String.valueOf (id));
                        //System.out.println ("id:" + id1);

                        Music music = new Music(duration1, metaData);
                        MusicController.getInstance().update(music);
                        //TODO create new metadata from inputs
                        //TODO insert new metadata
                        //TODO create new music
                        //TODO insert new music

                        // add to close the Form Window
                        shell.dispose ();
                        
                        // TODO add to refresh
                        //mCategoryContent = new CategoryListContent (category);
                        //mCategoryContent.implement(mContent);
                        //mCategoryContent.resize();

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

                shell.open();

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
