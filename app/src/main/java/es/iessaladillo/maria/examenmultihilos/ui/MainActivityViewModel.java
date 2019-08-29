package es.iessaladillo.maria.examenmultihilos.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private final MutableLiveData<String> localidad =  new MutableLiveData<>();

    public LiveData<String> getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad.setValue(localidad);
    }
}
