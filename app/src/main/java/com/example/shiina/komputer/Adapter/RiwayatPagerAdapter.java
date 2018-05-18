package com.example.shiina.komputer.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.shiina.komputer.R;
import com.example.shiina.komputer.RiwayatDiambilFragment;

public class RiwayatPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public RiwayatPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new RiwayatDiambilFragment("DIAMBIL");
        }
        else {
            return  new RiwayatDiambilFragment("DIBATALKAN");
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
       /* if (position == 0) {
            return mContext.getString(R.string.category_numbers);a
        } else if (position == 1) {
            return mContext.getString(R.string.category_family);
        } else if (position == 2) {
            return mContext.getString(R.string.category_colors);
        } else {
            return mContext.getString(R.string.category_phrases);
        }*/
        if (position == 0) {
            return mContext.getString(R.string.category_riwayat_diambil);
        }
        else{
            return mContext.getString(R.string.category_riwayat_ditolak);

        }


        //if using icon
        //return null;
    }
}
