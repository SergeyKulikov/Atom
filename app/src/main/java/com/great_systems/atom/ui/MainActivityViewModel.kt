package com.great_systems.atom.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.great_systems.atom.entity.Charger
import com.great_systems.atom.entity.City

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val _mapCityCharges: MutableLiveData<HashMap<String, List<Charger>>> = MutableLiveData()
    val mapCityCharges: LiveData<HashMap<String, List<Charger>>>
        get() = _mapCityCharges

    internal fun setCityList(v: List<City>) {
        val tmp = HashMap<String, MutableList<Charger>>()
        v.forEach { cityObj ->
            if (!tmp.containsKey(key = cityObj.city)) {
                tmp[cityObj.city] = mutableListOf(cityObj.charger)
            } else {
                tmp[cityObj.city]?.add(cityObj.charger)
            }
        }
        _mapCityCharges.postValue(tmp as HashMap<String, List<Charger>>)
    }
}