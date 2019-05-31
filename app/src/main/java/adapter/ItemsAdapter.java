package adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.assignment4.DescriptionActivity;
import com.assignment4.R;


import java.io.InputStream;
import java.net.URL;
import java.util.List;

import model.Items;
import url.Url;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {
    private List<Items> itemsList;
    private Context context;
    URL url;

    public ItemsAdapter(Context context, List<Items> itemsList) {
        this.itemsList = itemsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items,viewGroup,false);
        return new ViewHolder(view);
    }
    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder heroesViewHolder, int i) {
        final Items items = itemsList.get(i);
        String imgPath = Url.BASE_URL + "uploads/" + items.getimage();

        StrictMode();

        try{
             url = new URL(imgPath);
            heroesViewHolder.imgPhoto.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));

        }catch (Exception e){
            e.printStackTrace();
        }
        heroesViewHolder.tvName.setText(items.getItemName());
        heroesViewHolder.tvPrice.setText("Rs. " +items.getItemPrice());

        heroesViewHolder.imgPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DescriptionActivity.class);

                intent.putExtra("image", items.getimage());
                intent.putExtra("name", items.getItemName());
                intent.putExtra("price", items.getItemPrice());
                intent.putExtra("description", items.getItemDescription());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgPhoto;
        TextView tvName, tvPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPhoto = itemView.findViewById(R.id.imgPic);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}
