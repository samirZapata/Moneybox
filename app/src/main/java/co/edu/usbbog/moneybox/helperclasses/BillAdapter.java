package co.edu.usbbog.moneybox.helperclasses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.edu.usbbog.moneybox.R;

public class BillAdapter extends RecyclerView.Adapter<AdapterVH> {

    List<String> items;

    public BillAdapter(List<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public AdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_element, parent, false);
        return new AdapterVH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterVH holder, int position) {
        holder.concepto.setText(items.get(position));
        holder.valor.setText("$ " + items.get(position));
        holder.fecha.setText(items.get(position));
        holder.billT.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class AdapterVH extends RecyclerView.ViewHolder {

    ImageView cardIcon;
    TextView concepto, valor, fecha, billT;
    private BillAdapter adapter;

    public AdapterVH(@NonNull View itemView) {
        super(itemView);

        cardIcon = itemView.findViewById(R.id.cardIcon);
        concepto = itemView.findViewById(R.id.viewConcepto);
        valor = itemView.findViewById(R.id.viewValor);
        fecha = itemView.findViewById(R.id.viewFecha);
        billT = itemView.findViewById(R.id.viewBillT);
    }

    public AdapterVH linkAdapter(BillAdapter adapter) {
        this.adapter = adapter;
        return this;
    }
}
