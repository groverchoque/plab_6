package collections;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.deivi.labservicios.MainActivity;
import com.example.deivi.labservicios.R;
import com.loopj.android.http.JsonHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends BaseAdapter {
    private Context context;
    private List<Item> items;
    public ListAdapter(MainActivity context, ArrayList<Item> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return this.items.size ();
    }

    @Override
    public Object getItem(int i) {
        return this.items.get (i);
    }

    @Override
    public long getItemId(int i) {
        return this.items.get (i).getId ();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            LayoutInflater inflate = (LayoutInflater) this.context.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
            view = inflate.inflate (R.layout.item_ui, null);
        }
        TextView titulo = (TextView)view.findViewById (R.id.titletxt);
        TextView descripcion = (TextView)view.findViewById (R.id.description);
        ImageView image = (ImageView) view.findViewById (R.id.imagesource);
        Glide.with (this.context).load (this.items.get(i).getUrl ()).into (image);
        titulo.setText (this.items.get (i).getTitle ());
        descripcion.setText (this.items.get (i).getDescription ());
        return view;
    }
}
