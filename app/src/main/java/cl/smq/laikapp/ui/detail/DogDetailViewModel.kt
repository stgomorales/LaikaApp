package cl.smq.laikapp.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import cl.smq.laikapp.data.entities.DogDetail
import cl.smq.laikapp.data.repository.DogRepository
import cl.smq.laikapp.utils.Resource

class DogDetailViewModel  @ViewModelInject constructor(
    private val repository: DogRepository) : ViewModel() {


    private val _breed = MutableLiveData<String>()
    private val _dogDetail = _breed.switchMap { breed ->
        repository.getDogDetail(breed)
    }

    val dogDetail: LiveData<Resource<DogDetail>> = _dogDetail

    fun star(breed: String){
        _breed.value = breed
    }
}