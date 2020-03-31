package com.example.demo.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.SpringbootApplication;
import com.example.demo.config.ConexionConfig;
import com.example.demo.dao.WeatherDAO;
import com.example.demo.exception.CustomException;
import com.example.demo.modelo.Weather;

@Repository
public class WeatherDAOImpl implements WeatherDAO{

	@Autowired
	private ConexionConfig dataSource;
	
	@Override
	public Weather nuevoWeather(Weather w) throws CustomException {
		try {
			PreparedStatement pst = dataSource.getH2DataSource()
					.getConnection().prepareStatement("Insert into temporal"
							+ "(observation_time, temperature,"
							+ "weather_descriptions, wind_speed, wind_degree,"
							+ "wind_dir, pressure, precip, humidity, city) "
							+ "values (?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, w.getObservation_time());
			pst.setInt(2, w.getTemperature());
			pst.setString(3, w.getWeather_descriptions().get(0));
			pst.setInt(4, w.getWind_speed());
			pst.setInt(5, w.getWind_degree());
			pst.setString(6, w.getWind_dir());
			pst.setInt(7, w.getPressure());
			pst.setDouble(8, w.getPrecip());
			pst.setInt(9, w.getHumidity());
			pst.setString(10, w.getCity());
			pst.executeUpdate();
			ResultSet rst = pst.getGeneratedKeys();
			if (rst != null) {
				while (rst.next()) {
					w.setId(rst.getInt(1));
				}
			}
		} catch (SQLException e) {
			CustomException ce = new CustomException("Se ha producido un error al crear la query: " + e);
			LoggerFactory.getLogger(SpringbootApplication.class)
				.warn(ce.getMessage());
			throw ce;
		}
		return w;
	}

	@Override
	public List<Weather> getWeathers() throws CustomException {
		ResultSet rst;
		List<Weather> ws = null;
		try {
			rst = dataSource.getH2DataSource().getConnection()
					.createStatement().executeQuery("Select * From temporal");
			if (rst != null) {
				ws = new ArrayList<>();
				while(rst.next()) {
					ws.add(CrearWeather(rst));
				}
			}
		} catch (SQLException e) {
			CustomException ce = new CustomException("Se ha producido un error al crear la query: " + e);
			LoggerFactory.getLogger(SpringbootApplication.class)
				.warn(ce.getMessage());
			throw ce;
		}
		return ws;
	}

	private Weather CrearWeather(ResultSet rst) throws CustomException {
		Weather w = new Weather();
		try {
			w.setId(rst.getInt("id"));
			w.setObservation_time(rst.getString("observation_time"));
			w.setTemperature(rst.getInt("temperature"));
			List<String> weather_descriptions = new ArrayList<>();
			weather_descriptions.add(rst.getString("weather_descriptions"));
			w.setWeather_descriptions(weather_descriptions);
			w.setWind_speed(rst.getInt("wind_speed"));
			w.setWind_degree(rst.getInt("wind_degree"));
			w.setWind_dir(rst.getString("wind_dir"));
			w.setPressure(rst.getInt("pressure"));
			w.setPrecip(rst.getDouble("precip"));
			w.setHumidity(rst.getInt("humidity"));
			w.setCity(rst.getString("city"));
		} catch (SQLException e) {
			CustomException ce = new CustomException("Error al crear un Weather");
			throw ce;
		}
		return w;
	}

}
