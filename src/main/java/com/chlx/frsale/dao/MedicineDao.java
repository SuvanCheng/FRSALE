package com.xiaowang.mesqle.dao;

import com.xiaowang.mesqle.pojo.Medicine;

public interface MedicineDao {
    void insertMedicine(Medicine medicine);
    Medicine selectMedicineById(int id);
}
