package es.iessaladillo.maria.examenmultihilos.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Query;

import java.util.List;

import es.iessaladillo.maria.examenmultihilos.base.BaseDao;
import es.iessaladillo.maria.examenmultihilos.data.local.dto.Localidad;

public interface LocalidadDao extends BaseDao<Localidad> {

    @Query("SELECT * FROM localidad")
    LiveData<List<Localidad>> queryAllLocalidad();
}
