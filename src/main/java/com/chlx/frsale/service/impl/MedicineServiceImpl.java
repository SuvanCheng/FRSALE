package com.chlx.frsale.service.impl;

import com.chlx.frsale.dao.MedicineDao;
import com.chlx.frsale.pojo.Medicine;
import com.chlx.frsale.service.MedicineService;
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
