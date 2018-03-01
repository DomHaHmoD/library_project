package com.cesi.library_project.providers;

import com.cesi.library_project.database.controllers.FilmController;
import com.cesi.library_project.database.models.Film;
import com.cesi.library_project.providers.ui.film.FilmForm;
import com.cesi.library_project.providers.ui.film.FilmThumbnail;
import com.cesi.library_project.providers.ui.music.MusicForm;


//TODO create FilmForm and replace in modify/create
public class FilmProvider extends AbstractProvider<Film, FilmThumbnail, FilmController, FilmForm>{
    @Override
    protected FilmController createController() {
        return FilmController.getInstance();
    }

    @Override
    public FilmThumbnail getThumbnailProvider(Film object) {
        return new FilmThumbnail(object);
    }

    @Override
    public FilmThumbnail getPageProvider(Film object) {
        return null;
    }

    @Override
    public FilmForm modifyObject(Film object) {
        return new FilmForm (object);
    }

    @Override
    public FilmForm createObject() {
        return new FilmForm(null);
    }
}
