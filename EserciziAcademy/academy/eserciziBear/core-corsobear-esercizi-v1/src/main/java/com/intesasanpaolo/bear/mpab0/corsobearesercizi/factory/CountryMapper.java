package com.intesasanpaolo.bear.mpab0.corsobearesercizi.factory;

import com.intesasanpaolo.bear.mpab0.corsobearesercizi.model.CountryModel;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.resource.CountryResource;

import java.util.ArrayList;
import java.util.List;

public class CountryMapper {

    private CountryMapper() {
    }

    public static CountryResource modelToResource(CountryModel countryModel) {
        String[] info = countryModel.getInfo().split(" - ");
        return new CountryResource(countryModel.getId(), info[0], info[1], info[2]);
    }

    public static List<CountryResource> countryModelListToRescourceList(List<CountryModel> countryModelList) {
        List<CountryResource> countryResourceList = new ArrayList<>();
        countryModelList.forEach(elem -> countryResourceList.add(modelToResource(elem)));

        return countryResourceList;
    }
}
