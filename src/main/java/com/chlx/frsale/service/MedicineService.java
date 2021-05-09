package com.chlx.frsale.service;

import com.chlx.frsale.pojo.Medicine;

public interface MedicineService {
    void addMedicine(Medicine medicine);
    Medicine findMedicineById(int id);
}
