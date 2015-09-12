package com.cibertec.grupo7.pizzahut.adapter.reciclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.cibertec.grupo7.pizzahut.R;
import com.cibertec.grupo7.pizzahut.dao.LocalesDao;
import com.cibertec.grupo7.pizzahut.entities.Locales;
import com.cibertec.grupo7.pizzahut.entities.Provincias;

import java.util.ArrayList;

/**
 * Created by bgeek05 on 10/09/2015.
 */
public class RVLocales extends RecyclerView.Adapter<RVLocales.RVLocalesViewHolder> {
    private ArrayList<Locales> mLstLocal;
    private RVLocalesAdapterCallBack mRVLocalesListener;
    LocalesDao daoLocal = new LocalesDao();

    public interface RVLocalesAdapterCallBack {
        void onLocalClick(Locales local, int position);
    }

    public RVLocales(RVLocalesAdapterCallBack mRVLocalesListener, Provincias provincia) {
        this.mRVLocalesListener = mRVLocalesListener;
        mLstLocal = new ArrayList<>();
        mLstLocal.addAll(daoLocal.listLocales(provincia));
        notifyDataSetChanged();
    }

    @Override
    public RVLocalesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RVLocalesViewHolder(
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_item, viewGroup, false)
        );
    }

    @Override
    public void onBindViewHolder(RVLocalesViewHolder rvLocalesViewHolder, int i) {
        Locales local = mLstLocal.get(i);
        rvLocalesViewHolder.tvRvNombreLocal.setText(local.getNombre());
        rvLocalesViewHolder.tvRvDireccion.setText(local.getDireccion());
        rvLocalesViewHolder.imgLocal.setImageResource(R.drawable.abc_btn_radio_material);
        rvLocalesViewHolder.itemView.setTag(i);
        rvLocalesViewHolder.itemView.setOnClickListener(itemViewOnClickListener);
    }

    View.OnClickListener itemViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mRVLocalesListener != null)
                mRVLocalesListener.onLocalClick(mLstLocal.get((int) view.getTag()), (int) view.getTag());
        }
    };

    @Override
    public int getItemCount() {
        return 0;
    }

    static class RVLocalesViewHolder extends RecyclerView.ViewHolder{

        TextView tvRvNombreLocal, tvRvDireccion;
        ImageView imgLocal;

        public RVLocalesViewHolder(View itemView) {
            super(itemView);
            tvRvNombreLocal = (TextView) itemView.findViewById(R.id.tvRvNombreLocal);
            tvRvDireccion = (TextView) itemView.findViewById(R.id.tvRvDireccion);
            imgLocal = (ImageView) itemView.findViewById(R.id.imgLocal);
        }
    }
}
