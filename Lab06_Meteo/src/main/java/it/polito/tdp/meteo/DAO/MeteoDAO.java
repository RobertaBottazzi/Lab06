package it.polito.tdp.meteo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.meteo.model.Rilevamento;

public class MeteoDAO {
	
	public List<Rilevamento> getAllRilevamenti() {

		final String sql = "SELECT Localita, Data, Umidita FROM situazione ORDER BY data ASC";

		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getDate("Data").toLocalDate(), rs.getInt("Umidita"));
				rilevamenti.add(r);
			}

			conn.close();
			return rilevamenti;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<Rilevamento> getAllRilevamentiLocalitaMese(int mese, String localita) {
		final String sql="SELECT s.Localita, EXTRACT(MONTH FROM s.`Data`) AS MONTH, s.`Data`, s.Umidita  "
				+ "FROM situazione s "
				+ "WHERE s.Localita=? ";
		
		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, localita);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				if(rs.getInt("MONTH")==mese) {
					Rilevamento r= new Rilevamento(rs.getString("Localita"),rs.getDate("Data").toLocalDate(),rs.getInt("Umidita"));
					rilevamenti.add(r);
				}
			}

			conn.close();
			
			return rilevamenti;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public List<Integer> getAllUmiditaMese(int mese) {
		final String sql="SELECT EXTRACT(MONTH FROM s.`Data`) AS MONTH, s.Umidita  "
				+ "FROM situazione s ";
		
		List<Integer> umidita = new ArrayList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				if(rs.getInt("MONTH")==mese) {
					umidita.add(rs.getInt("Umidita"));
				}
			}

			conn.close();
			
			return umidita;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


}
