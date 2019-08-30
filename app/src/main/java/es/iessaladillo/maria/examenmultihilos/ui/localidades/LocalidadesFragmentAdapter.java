package es.iessaladillo.maria.examenmultihilos.ui.localidades;

//public class CompanyFragmentAdapter extends ListAdapter<Company, CompanyFragmentAdapter.ViewHolder> {

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import es.iessaladillo.maria.examenmultihilos.R;
import es.iessaladillo.maria.examenmultihilos.data.local.dto.Localidad;

public class LocalidadesFragmentAdapter extends ListAdapter<Localidad, LocalidadesFragmentAdapter.ViewHolder> {

    public LocalidadesFragmentAdapter() {
        super(new DiffUtil.ItemCallback<Localidad>() {
            @Override
            public boolean areItemsTheSame(@NonNull Localidad oldItem, @NonNull Localidad newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Localidad oldItem, @NonNull Localidad newItem) {
                return TextUtils.equals(oldItem.getNombre(), newItem.getNombre());
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_localidad, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public long getItemId(int position) {
        return super.getItem(position).getId();
    }

    @Override
    protected Localidad getItem(int position) {
        return super.getItem(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView lblNombre;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            lblNombre = ViewCompat.requireViewById(itemView, R.id.lblLocalidad);
        }

        void bind(Localidad localidad){
            lblNombre.setText(localidad.getNombre());
        }
    }
}
