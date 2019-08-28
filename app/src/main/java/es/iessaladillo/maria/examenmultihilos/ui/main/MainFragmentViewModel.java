package es.iessaladillo.maria.examenmultihilos.ui.main;

import androidx.lifecycle.ViewModel;

public class MainFragmentViewModel extends ViewModel {

    private String localidad;

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
}
