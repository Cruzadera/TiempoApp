package es.iessaladillo.maria.examenmultihilos.data.local;

import androidx.lifecycle.LiveData;

import java.util.List;

import es.iessaladillo.maria.examenmultihilos.data.local.dto.Localidad;

public class RepositoryImpl implements Repository {

    private final LocalidadDao localidadDao;

    public RepositoryImpl(LocalidadDao localidadDao) {
        this.localidadDao = localidadDao;
    }

    @Override
    public LiveData<List<Localidad>> queryAllLocalidad() {
        return localidadDao.queryAllLocalidad();
    }
}
