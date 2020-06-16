package sudo_dev.dialpadtalking;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

@SuppressLint("NewApi")
public class CustomAdapter  extends BaseAdapter {
    private Context context;
    private ArrayList<model> modelArrayList;

    public CustomAdapter(Context context,ArrayList<model> contactModelArrayList) {

        this.context = context;
        this.modelArrayList = contactModelArrayList;
    }

    @Override
    public int getViewTypeCount() {

        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {

        return modelArrayList.size();
    }

    @Override
    public Object getItem(int position) {

        return modelArrayList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position,View convertView,ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder ();

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService ( Context.LAYOUT_INFLATER_SERVICE );
            assert inflater != null;
            convertView = inflater.inflate ( R.layout.phonebook,null,true );

            holder.tvname = convertView.findViewById ( R.id.name );
            holder.tvnumber =convertView.findViewById ( R.id.number );
            holder.call = convertView.findViewById ( R.id.call );
            holder.sms =convertView.findViewById ( R.id.sms );
            convertView.setTag ( holder );

        } else {

            holder = (ViewHolder) convertView.getTag ();
        }

        holder.tvname.setText ( modelArrayList.get ( position ).getName () );
        holder.tvnumber.setText ( modelArrayList.get ( position ).getNumber () );
        holder.call.setOnClickListener ( new View.OnClickListener () {
            @SuppressLint({"MissingPermission","NewApi"})
            @Override
            public void onClick(View v) {
                Intent intent = new Intent ( Intent.ACTION_CALL );
                intent.setData ( Uri.parse ( "tel:" + modelArrayList.get ( position ).getNumber () ) );
                context.startActivity ( intent );

            }
        } );
        holder.sms.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent ( Intent.ACTION_VIEW );
                smsIntent.setData ( Uri.parse ( "smsto:" ) );
                smsIntent.setType ( "vnd.android-dir/mms-sms" );
                smsIntent.putExtra ( "address",modelArrayList.get ( position ).getNumber () );
                smsIntent.putExtra ( "sms_body","" );
                context.startActivity ( smsIntent );
            }
        } );
        return convertView;
    }

    private class ViewHolder {

        protected TextView tvname, tvnumber,call,sms;

    }

}
