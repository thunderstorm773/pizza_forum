package com.pizzaforum.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;

import java.util.ArrayList;
import java.util.List;

public class MapperUtil {

    private static MapperUtil mapperUtil;

    private ModelMapper modelMapper;

    private MapperUtil() {
        this.modelMapper = new ModelMapper();
    }

    public static MapperUtil getInstance() {
        if (mapperUtil == null) {
            mapperUtil = new MapperUtil();
        }

        return mapperUtil;
    }

    public ModelMapper getModelMapper() {
        return this.modelMapper;
    }

    public <S, D> List<D> convertAll(List<S> source, Class<D> destination) {
        List<D> convertedItems = new ArrayList<>();
        for (S s : source) {
            D convertedItem = this.getModelMapper().map(s, destination);
            convertedItems.add(convertedItem);
        }

        return convertedItems;
    }

    public <S, D> List<D> convertAll(List<S> sourceList, Class<S> sourceClass,
                                     Class<D> destinationClass,
                                     PropertyMap<S, D> propertyMap) {

        TypeMap<S, D> typeMap = this.getModelMapper().getTypeMap(sourceClass, destinationClass);
        if (typeMap == null) {
            this.getModelMapper().addMappings(propertyMap);
        }

        List<D> convertedItems = new ArrayList<>();
        for (S s : sourceList) {
            D convertedItem = this.getModelMapper().map(s, destinationClass);
            convertedItems.add(convertedItem);
        }

        return convertedItems;
    }
}
