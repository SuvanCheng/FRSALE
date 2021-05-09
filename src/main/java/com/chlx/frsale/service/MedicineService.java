package com.chlx.frsale.service;

import com.xiaowang.mesqle.pojo.Medicine;

public interface MedicineService {
    void addMedicine(Medicine medicine);
    Medicine findMedicineById(int id);
}
