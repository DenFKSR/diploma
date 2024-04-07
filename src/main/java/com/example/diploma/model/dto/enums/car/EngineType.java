package com.example.diploma.model.dto.enums.car;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;
import java.util.stream.Stream;

//@Getter
//@AllArgsConstructor
public enum EngineType {
    DIESEL,//"diesel"),
    PETROL,//("petrol"),
    ELECTRIC//("electric");

//    private final String type;
//
//    public EngineType parseType(String engineType) {
//        Optional<EngineType> parsedEngineType = Stream.of(DIESEL.getType(), PETROL.getType(), ELECTRIC.getType())
//                .filter(type -> type.equalsIgnoreCase(engineType)).map(EngineType::valueOf).findFirst();
//        if (parsedEngineType.isPresent()) {
//            return parsedEngineType.get();
//        }
//        throw new IllegalArgumentException(String.format("Неправильный тип трансмиссии: %s. Доступные типы трансмиссии: %s, %s, %s",
//                engineType, DIESEL.getType(), PETROL.getType(), ELECTRIC.getType()));
//    }
}
