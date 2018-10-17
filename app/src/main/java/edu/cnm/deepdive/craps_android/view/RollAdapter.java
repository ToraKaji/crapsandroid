package edu.cnm.deepdive.craps_android.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import edu.cnm.deepdive.craps_android.R;
import edu.cnm.deepdive.craps_android.model.Game.Roll;
import edu.cnm.deepdive.craps_android.model.Game.State;

public class RollAdapter extends ArrayAdapter<Roll> {

private Drawable[] faces;

  public RollAdapter(@NonNull Context context) {
    super(context, R.layout.roll_item);
    faces = new Drawable[6];
    Resources res = context.getResources();
    String pkg = context.getPackageName();
    for(int i = 0;i < 6; i++){
      faces[i]= ContextCompat.getDrawable(
          context, res.getIdentifier("face_" + (i+1),"drawable",pkg));
    }

  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    Roll roll = getItem(position);
    int[] dice = roll.getDice();
    if(convertView == null){
      convertView = LayoutInflater.from(getContext()).inflate(R.layout.roll_item, parent, false);

    }
    ImageView die0 = convertView.findViewById(R.id.die_0);
    die0.setImageDrawable(faces[roll.getDice()[0]-1]);

    ImageView die1 = convertView.findViewById(R.id.die_1);
    die1.setImageDrawable(faces[roll.getDice()[1]-1]);

    TextView total = convertView.findViewById(R.id.total);
    total.setText(getContext().getString(R.string.dice_total_format, dice[0] + dice[1]));
    if(position == 0&&this.getCount()>1){
convertView.setBackgroundColor(Color.argb(122,128,128,128));
    }else if (roll.getState()== State.WIN){
      convertView.setBackgroundColor(Color.argb(255,0,255,0));
    }else if (roll.getState() == State.LOSS){
      convertView.setBackgroundColor(Color.argb(255,255,0,0));
    }else{
      convertView.setBackgroundColor(Color.argb(0,255,255,255));
    }
    return convertView;

  }
}
