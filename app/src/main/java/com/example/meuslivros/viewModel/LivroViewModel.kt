package com.example.meuslivros.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.meuslivros.db.AppDatabase
import com.example.meuslivros.model.Livro
import com.example.meuslivros.repository.LivroRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LivroViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData : LiveData<List<Livro>>
    private val repository : LivroRepository

    private var _index = MutableLiveData<Int>(0)
    var index : LiveData<Int> = _index

    init {
        val livroDao = AppDatabase.getDatabase(application).livroDao()
        repository = LivroRepository(livroDao)
        readAllData = repository.realAllData
    }


    fun irAnterior(index: Int){
        if (index > 0){
            _index.value =  index - 1
        }
    }


    fun irProximo(index : Int){
        if (index < readAllData.value!!.size -1){
            _index.value = index + 1
        }
    }


    fun addLivro (livro : Livro){
        viewModelScope.launch(Dispatchers.IO){
            repository.addLivro(livro)
        }
    }
}