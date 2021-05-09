package com.chlx.frsale.dao;

import com.chlx.frsale.pojo.Medicine;

public interface MedicineDao {
    void insertMedicine(Medicine medicine);
    Medicine selectMedicineById(int id);
}
