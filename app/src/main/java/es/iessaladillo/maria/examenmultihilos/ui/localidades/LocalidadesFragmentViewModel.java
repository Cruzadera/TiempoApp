package es.iessaladillo.maria.examenmultihilos.ui.localidades;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import es.iessaladillo.maria.examenmultihilos.base.Event;
import es.iessaladillo.maria.examenmultihilos.data.local.Repository;
import es.iessaladillo.maria.examenmultihilos.data.local.dto.Localidad;

public class LocalidadesFragmentViewModel extends ViewModel {

    private final Application application;
    private final LiveData<List<Localidad>> localidades;
    private final MutableLiveData<Localidad> deleteTrigger = new MutableLiveData<>();
    private final MediatorLiveData<Event<String>> successMessage = new MediatorLiveData<>();
    private final MediatorLiveData<Event<String>> errorMessage = new MediatorLiveData<>();

    LocalidadesFragmentViewModel(Application application, Repository repository) {
        this.application = application;
        localidades = repository.queryAllLocalidad();
//        setupSuccessMessage();
//        setupErrorMessage();
    }

//    private void setupSuccessMessage() {
//        successMessage.addSource(deletionResult, resource -> {
//            if (resource.hasSuccess()) {
//                successMessage.setValue(new Event<>(application.getString(R.string.list_deleted_successfully)));
//            }
//        });
//    }
//
//    private void setupErrorMessage() {
//        errorMessage.addSource(deletionResult, resource -> {
//            if (resource.hasError()) {
//                errorMessage.setValue(new Event<>(application.getString(R.string.list_error_deleting)));
//            }
//        });
//    }

    void deleteLocalidad(Localidad localidad) {
        deleteTrigger.setValue(localidad);
    }

    LiveData<List<Localidad>> getLocalidades() {
        return localidades;
    }

    LiveData<Event<String>> getSuccessMessage() {
        return successMessage;
    }

    LiveData<Event<String>> getErrorMessage() {
        return errorMessage;
    }
}
