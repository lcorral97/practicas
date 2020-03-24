package com.example.demo.modelo;

import java.util.List;

public class Weather {

	private int id;
	private String observation_time;
	private int temperature;
	private List<String> weather_descriptions;
	private int wind_speed;
	private int wind_degree;
	private String wind_direction;
	private int pressure;
	private double precip;
	private int humidity;
	
	public Weather() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Weather [id=" + id + ", observation_time=" + observation_time + ", temperature="
				+ temperature + ", weather_descriptions=" + weather_descriptions + ", wind_speed=" + wind_speed
				+ ", wind_degree=" + wind_degree + ", wind_direction=" + wind_direction + ", pressure=" + pressure
				+ ", precip=" + precip + ", humidity=" + humidity + "]";
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
		result = prime * result + ((wind_direction == null) ? 0 : wind_direction.hashCode());
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
		if (wind_direction == null) {
			if (other.wind_direction != null)
				return false;
		} else if (!wind_direction.equals(other.wind_direction))
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
	public String getWind_direction() {
		return wind_direction;
	}
	public void setWind_direction(String wind_direction) {
		this.wind_direction = wind_direction;
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
