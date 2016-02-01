package hackohio.medqr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class MedDataAdapter extends ArrayAdapter<MedData> {
    ArrayList<MedData> list;

    MedDataAdapter(Context context, java.util.ArrayList<hackohio.medqr.MedData> input) {
        super(context, R.layout.meddata_row, input);
        list = input;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        LayoutInflater blaiseInflater = LayoutInflater.from(getContext());
        View customView = blaiseInflater.inflate(R.layout.meddata_row, parent, false);
        MedData md = list.get(position);

        String name = list.get(position).getName();
        TextView blaiseText = (TextView) customView.findViewById(R.id.textView);

        blaiseText.setText(name);
        return customView;
    }
}


