package com.great_systems.atom

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.great_systems.atom.entity.Charger
import com.great_systems.atom.entity.City
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object Repository {
    const val jsonData = "[{\"city\":\"Moscow\",\"charger\":{\"busy\":true,\"name\":\"Энергия Москвы\",\"address\":\"Измайловское ш., 4А\"}},{\"city\":\"Moscow\",\"charger\":{\"busy\":false,\"name\":\"Lipgart\",\"address\":\"2-я Бауманская ул., 5, стр. 5\"}},{\"city\":\"Saint Petersburg\",\"charger\":{\"busy\":true,\"name\":\"Станция зарядки электромобилей\",\"address\":\"Большой просп. Васильевского острова, 68\"}},{\"city\":\"Moscow\",\"charger\":{\"busy\":false,\"name\":\"Zevs\",\"address\":\"Мясницкая ул., 13, стр. 10\"}},{\"city\":\"Saint Petersburg\",\"charger\":{\"busy\":false,\"name\":\"Punkt E\",\"address\":\"Малая Монетная ул., 2Г\"}}]"

    fun getData(onComplete: (List<City>) -> Unit, onError: (Exception) -> Unit) =
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            try {
                val chargesByCity: List<City>
                val clt = object : TypeToken<List<City>>() {}

                chargesByCity = Gson().fromJson(jsonData, clt.type)
                onComplete.invoke(chargesByCity)
            } catch (ex: Exception) {
                ex.printStackTrace()
                onError.invoke(ex)
            }
        }

}