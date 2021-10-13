package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private final String SPLITTER = " of ";
    private final int SPLITTER_LENGTH = SPLITTER.length();

    public EarthquakeAdapter(@NonNull Context context, @NonNull List<Earthquake> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }

        Earthquake earthquake = getItem(position);

        TextView magnitudeView = (TextView) convertView.findViewById(R.id.magnitude);
        magnitudeView.setText(formatMagnitude(earthquake.getMagnitude()));

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(earthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        TextView offsetLocationView = (TextView) convertView.findViewById(R.id.location_offset);
        offsetLocationView.setText(getOffsetLocation(earthquake.getLocation()));

        TextView primaryLocationView = (TextView) convertView.findViewById(R.id.primary_location);
        primaryLocationView.setText(getPrimaryLocation(earthquake.getLocation()));

        TextView dateView = (TextView) convertView.findViewById(R.id.date);
        dateView.setText(formatDate(earthquake.getTimestamp()));

        TextView timeView = (TextView) convertView.findViewById(R.id.time);
        timeView.setText(formatTime(earthquake.getTimestamp()));

        return convertView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    private String getOffsetLocation(String location) {
        int indexOf = location.indexOf(SPLITTER);
        if (indexOf < 0) {
            return "Near the";
        }
        return location.substring(0, indexOf + SPLITTER_LENGTH);
    }

    private String getPrimaryLocation(String location) {
        int indexOf = location.indexOf(SPLITTER);
        if (indexOf < 0) {
            return location;
        }
        return location.substring(indexOf + SPLITTER_LENGTH);
    }

    private String formatMagnitude(double magnitude) {
        return String.format(Locale.getDefault(), "%.1f", magnitude);
    }

    private String formatDate(long timestamp) {
        Date date = new Date(timestamp);
        return DateFormat.format("MMM d, yyyy", date).toString();
    }

    private String formatTime(long timestamp) {
        Date date = new Date(timestamp);
        return DateFormat.format("h:mm a", date).toString();
    }
}
