package com.example.weatherapp.ui.main.weather_fragment


import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.domain.model.MainWeatherModel
import com.example.domain.type.TypeOfWeather
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.ui.main.MainActivity
import com.example.weatherapp.ui.main.weather_fragment.adapter.WeatherAdapter
import com.example.weatherapp.utils.ActivityExt.Companion.getDateMonthLongSize
import com.example.weatherapp.utils.Resource
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.koin.androidx.viewmodel.ext.android.viewModel


class WeatherFragment : Fragment(R.layout.fragment_weather) {

    private val binding: FragmentWeatherBinding by viewBinding(CreateMethod.INFLATE)
    private var activity: MainActivity? = null
    private var adapterWeather: WeatherAdapter? = null
    private val viewModel: WeatherViewModel by viewModel()
    private var fusedLocationClient: FusedLocationProviderClient? = null
    private var latitude: Double? = null
    private var longitude: Double? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root.also {
        activity = requireActivity() as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        initAdapter()
        initRecycler()
        loadCoordinates()
        initLiveData()
    }

    private fun initAdapter(){
        adapterWeather = WeatherAdapter()
    }

    private fun initRecycler() = with(binding){
        recyclerDays.apply {
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            this.adapter = adapterWeather
        }
    }

    private fun initLiveData(){
        viewModel.loadDataOfWeather.observe(viewLifecycleOwner){
            when(it.status){
                Resource.Status.LOADING -> {

                }
                Resource.Status.SUCCESS -> {
                    setCurrentDayData(it?.data!!)
                    adapterWeather?.submitList(it.data.forecast)
                }
                Resource.Status.ERROR -> {
                    Log.d("TAG663", it.exception?.message.toString())
                }
            }
        }
    }
    private fun loadCoordinates(){
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            latitude = 50.450001
            longitude = 30.523333
            viewModel.loadWeatherData("${latitude},${longitude}")
            return
        }
        fusedLocationClient?.lastLocation
            ?.addOnSuccessListener { location : Location? ->
                latitude = location?.latitude ?: 50.450001
                longitude = location?.longitude ?: 30.523333
                viewModel.loadWeatherData("${latitude},${longitude}")
            }
    }

    private fun setCurrentDayData(item: MainWeatherModel) = with(binding){
        tvCountry.text = "${item.location.country}, ${item.location.name}"
        tvDegrees.text = "${item.current.tempC}°C"
        tvTypeOfWeather.text = item.current.stateOfWeather
        tvDate.text = getDateMonthLongSize(item.location.localtimeEpoch.toLong())
        Glide.with(binding.root).load(isDayOrNight(item.current.isDay,item.current.typeOfWeather)).into(ivTypeOfWeather)
    }

    private fun isDayOrNight(isDay: Int, type: TypeOfWeather): Int{
        return if (isDay == 1) {
            whichDataToSet(type)
        } else {
            R.drawable.ic_clear
        }
    }

    private fun whichDataToSet(type: TypeOfWeather): Int{
        return when(type){
            TypeOfWeather.SUNNY -> R.drawable.ic_sunny
            TypeOfWeather.CLOUDY -> R.drawable.ic_cloudy
            TypeOfWeather.RAINY -> R.drawable.ic_snowy
            TypeOfWeather.OVERCASTY -> R.drawable.ic_snowy
        }
    }

}