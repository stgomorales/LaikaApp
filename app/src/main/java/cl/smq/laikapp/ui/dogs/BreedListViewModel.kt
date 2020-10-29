package cl.smq.laikapp.ui.dogs

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import cl.smq.laikapp.data.repository.DogRepository

class BreedListViewModel  @ViewModelInject constructor(
    private val repository: DogRepository): ViewModel(){
    val dogBreeds = repository.getAllDogBreed()
}