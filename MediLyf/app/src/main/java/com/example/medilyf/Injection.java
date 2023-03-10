package com.example.medilyf;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.medilyf.source.MedicineDataSource;
import com.example.medilyf.source.MedicineRepository;
import com.example.medilyf.source.local.MedicinesLocalDataSource;

/**
 * Created by gautam on 13/07/17.
 */

public class Injection {

    public static MedicineRepository provideMedicineRepository(@NonNull Context context) {
        return MedicineRepository.getInstance((MedicineDataSource) MedicinesLocalDataSource.getInstance(context));
    }
}
