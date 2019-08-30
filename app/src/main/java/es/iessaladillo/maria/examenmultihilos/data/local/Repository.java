package es.iessaladillo.maria.examenmultihilos.data.local;

import androidx.lifecycle.LiveData;

import java.util.List;

import es.iessaladillo.maria.examenmultihilos.data.local.dto.Localidad;

public interface Repository {
    LiveData<List<Localidad>> queryAllLocalidad();
}
