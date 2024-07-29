package com.example.diploma.service.impl.carImpl;


import com.example.diploma.model.db.repository.CarRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarsProperty {
    private final CarRepo carRepo;

    public List<String> GetInformationCar(Long carId) {
        String s = carRepo.findCarDetailsByCarId(carId);
        String[] words = s.split(",");
        List<String> titleList = new ArrayList<>();
        String[] heading =
                {"Марка: ", "Модель: ", "Цена: ", "Трансмиссия: ",
                        "Кузов: ", "Двигатель: ", "Расход топлива: ", "Количество сидений: "};
                for (int i = 0; i < words.length; i++) {
                    titleList.add(heading[i] + words[i]);
                }
        return titleList;
    }

}
