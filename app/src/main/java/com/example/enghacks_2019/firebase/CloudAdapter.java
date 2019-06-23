package com.example.enghacks_2019.firebase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.enghacks_2019.R;
import com.example.enghacks_2019.util.TimeStampUtilKt;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class CloudAdapter extends FirestoreRecyclerAdapter<CloudMessage, CloudAdapter.CloudHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CloudAdapter(@NonNull FirestoreRecyclerOptions<CloudMessage> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CloudHolder cloudHolder, int i, @NonNull CloudMessage cloudMessage) {
        cloudHolder.textViewDesc.setText(cloudMessage.getDescription());
        cloudHolder.textViewTime.setText(TimeStampUtilKt.convertDateToString(cloudMessage.getTimeStamp().toDate()));
    }

    @NonNull
    @Override
    public CloudHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_msg,
                parent,
                false);

        return new CloudHolder(v);
    }

    class CloudHolder extends RecyclerView.ViewHolder {
        TextView textViewDesc;
        TextView textViewTime;

        public CloudHolder(View itemView) {
            super(itemView);

            textViewDesc = itemView.findViewById(R.id.tv_msg_desc);
            textViewTime = itemView.findViewById(R.id.tv_msg_time);
        }
    }
}
