package com.example.demo.modelo;

import java.util.List;

public class Weather {

	private int id;
	private String observation_time;
	private int temperature;
	private List<String> weather_descriptions;
	private int wind_speed;
	private int wind_degree;
	private String wind_dir;
	private int pressure;
	private double precip;
	private int humidity;
	private String city;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Weather() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Weather [id=" + id + ", observation_time=" + observation_time + ", temperature=" + temperature
				+ ", weather_descriptions=" + weather_descriptions + ", wind_speed=" + wind_speed + ", wind_degree="
				+ wind_degree + ", wind_dir=" + wind_dir + ", pressure=" + pressure + ", precip=" + precip
				+ ", humidity=" + humidity + ", city=" + city + "]";
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + humidity;
		result = prime * result + id;
		result = prime * result + ((observation_time == null) ? 0 : observation_time.hashCode());
		long temp;
		temp = Double.doubleToLongBits(precip);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + pressure;
		result = prime * result + temperature;
		result = prime * result + ((weather_descriptions == null) ? 0 : weather_descriptions.hashCode());
		result = prime * result + wind_degree;
		result = prime * result + ((wind_dir == null) ? 0 : wind_dir.hashCode());
		result = prime * result + wind_speed;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Weather other = (Weather) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (humidity != other.humidity)
			return false;
		if (id != other.id)
			return false;
		if (observation_time == null) {
			if (other.observation_time != null)
				return false;
		} else if (!observation_time.equals(other.observation_time))
			return false;
		if (Double.doubleToLongBits(precip) != Double.doubleToLongBits(other.precip))
			return false;
		if (pressure != other.pressure)
			return false;
		if (temperature != other.temperature)
			return false;
		if (weather_descriptions == null) {
			if (other.weather_descriptions != null)
				return false;
		} else if (!weather_descriptions.equals(other.weather_descriptions))
			return false;
		if (wind_degree != other.wind_degree)
			return false;
		if (wind_dir == null) {
			if (other.wind_dir != null)
				return false;
		} else if (!wind_dir.equals(other.wind_dir))
			return false;
		if (wind_speed != other.wind_speed)
			return false;
		return true;
	}
	public String getObservation_time() {
		return observation_time;
	}
	public void setObservation_time(String observation_time) {
		this.observation_time = observation_time;
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	public List<String> getWeather_descriptions() {
		return weather_descriptions;
	}
	public void setWeather_descriptions(List<String> weather_descriptions) {
		this.weather_descriptions = weather_descriptions;
	}
	public int getWind_speed() {
		return wind_speed;
	}
	public void setWind_speed(int wind_speed) {
		this.wind_speed = wind_speed;
	}
	public int getWind_degree() {
		return wind_degree;
	}
	public void setWind_degree(int wind_degree) {
		this.wind_degree = wind_degree;
	}
	public String getWind_dir() {
		return wind_dir;
	}
	public void setWind_dir(String wind_dir) {
		this.wind_dir = wind_dir;
	}
	public int getPressure() {
		return pressure;
	}
	public void setPressure(int pressure) {
		this.pressure = pressure;
	}
	public double getPrecip() {
		return precip;
	}
	public void setPrecip(double precip) {
		this.precip = precip;
	}
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	
}
