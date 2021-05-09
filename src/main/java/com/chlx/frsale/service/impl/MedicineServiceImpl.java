package com.xiaowang.mesqle.service.impl;

import com.xiaowang.mesqle.dao.MedicineDao;
import com.xiaowang.mesqle.pojo.Medicine;
import com.xiaowang.mesqle.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicineServiceImpl implements MedicineService {
    @Autowired
    private MedicineDao medicineDao;
    @Override
    public void addMedicine(Medicine medicine) {
        this.medicineDao.insertMedicine(medicine);
    }

    @Override
    public Medicine findMedicineById(int id) {
        return this.medicineDao.selectMedicineById(id);
    }
}
