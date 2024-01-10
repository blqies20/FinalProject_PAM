package com.example.finalproject_pam.ui.model


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.finalproject_pam.data.Hewan
import com.example.finalproject_pam.repository.RepositoriHewan

class AddViewModel (private val repositoriHewan: RepositoriHewan): ViewModel() {

    /**
     * Berisi status Hewan saat ini
     */
    var uiStateHewan by mutableStateOf(UIStateHewan())
        private set

    /** Fungsi untuk memvalidasi input **/
    private fun validasiInput(uiState: DetailHewan = uiStateHewan.detailHewan): Boolean{
        return with(uiState){
            JnisHewan.isNotBlank() && gender.isNotBlank() && age.isNotBlank() && price.isNotBlank()
        }
    }

    fun updateUiState(detailHewan: DetailHewan){
        uiStateHewan =
            UIStateHewan(detailHewan = detailHewan, isAddValid = validasiInput(detailHewan))
    }
    /** Fungsi untuk menyimpan data yang di entry **/
    suspend fun saveHewan(){
        if (validasiInput()){
            repositoriHewan.insertHewan(uiStateHewan.detailHewan.toHewan())
        }
    }
}
/**
 * Mewakili staus ui untuk siswa
 */
data class UIStateHewan(
    val detailHewan: DetailHewan = DetailHewan(),
    val isAddValid: Boolean = false
)

data class DetailHewan(
    val id: Int = 0,
    val JnisHewan: String = "",
    val gender: String = "",
    val age: String = "",
    val price : String = ""
)
/** Fungsi untuk mengkonversi data input ke data dalam tabel sesuai jenis datanya **/
fun DetailHewan.toHewan(): Hewan = Hewan(
    id = id,
    JenisHewan = JnisHewan,
    JenisKelamin = gender,
    Usia =  age,
    Harga = price

)

fun Hewan.toUiStateHewan(isAddValid: Boolean = false): UIStateHewan = UIStateHewan(
    detailHewan = this.toDetailHewan(),
    isAddValid = isAddValid
)

fun Hewan.toDetailHewan(): DetailHewan = DetailHewan(
    id = id,
    JnisHewan = JenisHewan,
    gender = JenisKelamin,
    age = Usia,
    price = Harga
)