package co.edu.usbbog.moneybox.helperclasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.edu.usbbog.moneybox.R;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    public List<ListEmelemnt> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapter(List<ListEmelemnt> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.list_element, null);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int positino){
        holder.bindData(mData.get(positino));
    }

    public void setItems(List<ListEmelemnt> items){
        mData=items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView cardIcon;
        TextView concepto, valor, fecha;

        ViewHolder(View itemView){
            super(itemView);
            cardIcon = itemView.findViewById(R.id.cardIcon);
            concepto = itemView.findViewById(R.id.viewConcepto);
            valor = itemView.findViewById(R.id.viewValor);
            fecha = itemView.findViewById(R.id.viewFecha);
        }
        void bindData(final ListEmelemnt item){
            concepto.setText(item.getConcepto());
            valor.setText("$ "+item.getValor());
            fecha.setText(item.getFecha());
        }

    }

}
