package es.iessaladillo.maria.examenmultihilos.ui.info;


import android.annotation.SuppressLint;
import android.app.Notification;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Calendar;

import es.iessaladillo.maria.examenmultihilos.R;
import es.iessaladillo.maria.examenmultihilos.data.remote.ApiService;
import es.iessaladillo.maria.examenmultihilos.data.remote.dto.Resultado;
import es.iessaladillo.maria.examenmultihilos.databinding.FragmentInfoBinding;
import es.iessaladillo.maria.examenmultihilos.ui.MainActivityViewModel;
import es.iessaladillo.maria.examenmultihilos.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {

    FragmentInfoBinding b;
    private String CHANNEL_ID = Constants.DEFAULT_CHANNEL_ID;
    private int NOTIFICATION_ID = 1;
    private MainActivityViewModel viewModel;

    public InfoFragment() {
        // Required empty public constructor
    }

    public static InfoFragment newInstance() {
        Bundle args = new Bundle();
        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);
        setupViews();
    }

    private void setupViews() {
        viewModel.getLocalidad().observe(this, localidad ->{
            if(localidad != null || localidad.equals("")){
                search(localidad);
            }else{
                Log.e("LOCALIDAD", "NULL");
            }

        });
    }

    private void search(String text) {
        Call<Resultado> request = ApiService.getInstance().getApi().getTiempo(text);
        request.enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mostrarResultado(response.body());
                    mostarNotificacion();
                }
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {
                Toast.makeText(requireActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("ERROR_CONSULTA", t.getMessage());
            }
        });
    }

    private void mostarNotificacion() {
        Notification notification = new NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(b.lblNombre.getText().toString())
                .setContentText(b.lblDescripcion.getText().toString())
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(requireContext());
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    @SuppressLint("DefaultLocale")
    private void mostrarResultado(Resultado resultado) {
        Calendar amanecer = Calendar.getInstance();
        Calendar atardecer = Calendar.getInstance();

        String urlImg = String.format(getString(R.string.url_img), resultado.getWeather().get(0).getIcon());
        Picasso.with(requireContext()).load(urlImg).into(b.imgWeather);

        b.lblNombre.setText(String.format("%s, %s", resultado.getName(), resultado.getSys().getCountry()));
        b.lblDescripcion.setText(resultado.getWeather().get(0).getDescription());
        b.lblTemperaturaMedia.setText(String.format("Temperatura: \n min %.2f \n max %.2f\n media %.2f", resultado.getMain().getTempMin(), resultado.getMain().getTempMax(), resultado.getMain().getTemp()));
        if (resultado.getRain() != null) {
            b.lblLluvia.setText(String.format("Lluvia: %.2f", resultado.getRain().get1h()));
        }else{
            b.lblLluvia.setText(R.string.no_lluvia);
        }
        b.lblHumedad.setText(String.format("Humedad: %d", resultado.getMain().getHumidity()));
        b.lblViento.setText(String.format("Viento:\n velocidad %.2f mps\n dirección: %.2f º", resultado.getWind().getSpeed(), resultado.getWind().getDeg()));
        b.lblNubosidad.setText(String.format("Nubosidad: %d%%", resultado.getClouds().getAll()));
        //Obtenemos el isntante en el que amanece y atardece
        amanecer.setTimeInMillis(resultado.getSys().getSunrise());
        atardecer.setTimeInMillis(resultado.getSys().getSunset());
        b.lblAmanecer.setText(String.format("Amanecer: %02d:%02d Atardecer: %02d:%02d", amanecer.get(Calendar.MINUTE), amanecer.get(Calendar.SECOND), atardecer.get(Calendar.MINUTE), atardecer.get(Calendar.SECOND)));
    }


}
