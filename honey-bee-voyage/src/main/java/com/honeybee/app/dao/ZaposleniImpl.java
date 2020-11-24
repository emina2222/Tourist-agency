package com.honeybee.app.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.honeybee.app.entity.AngazovanjeVodica;
import com.honeybee.app.entity.PonudaSmestaja;
import com.honeybee.app.entity.TuristickiVodic;
import com.honeybee.app.entity.Zaposleni;
import com.microsoft.sqlserver.jdbc.SQLServerException;

@Repository
public class ZaposleniImpl implements ZaposleniDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public List<Zaposleni> showAllEmployees() {
		Session session = sessionFactory.getCurrentSession();

		List<Zaposleni> zaposleni=null;
		zaposleni = session.doReturningWork(new ReturningWork<List<Zaposleni>>() {

			@Override
			public List<Zaposleni> execute(Connection connection) throws SQLException {

				List<Zaposleni> zaposleni=new ArrayList<Zaposleni>();
				PreparedStatement stmt = null;
				try {
					String sqlString = "select SIFRA_RADNIKA,ID_KREDENCIJALA,IME,PREZIME,TELEFON,EMAIL,ADRESA,PLATA,DATUM_ZAPOSLENJA from ZAPOSLENI";
					stmt = connection.prepareCall(sqlString);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						Zaposleni a=new Zaposleni(rs.getInt("SIFRA_RADNIKA"),rs.getInt("ID_KREDENCIJALA"),rs.getString("IME")
								,rs.getString("PREZIME"),rs.getString("TELEFON"),rs.getString("EMAIL")
								,rs.getString("ADRESA"),rs.getDouble("PLATA"),rs.getDate("DATUM_ZAPOSLENJA"));
						zaposleni.add(a);
					}
				} finally {
					stmt.close();
				}
				return zaposleni;
			}

		});

		return zaposleni;
	}

	@Override
	@Transactional
	public List<TuristickiVodic> showAllGuides() {
		Session session = sessionFactory.getCurrentSession();

		List<TuristickiVodic> vodici=null;
		vodici = session.doReturningWork(new ReturningWork<List<TuristickiVodic>>() {

			@Override
			public List<TuristickiVodic> execute(Connection connection) throws SQLException {

				List<TuristickiVodic> vodici=new ArrayList<TuristickiVodic>();
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from TURISTICKI_VODIC";
					stmt = connection.prepareCall(sqlString);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						TuristickiVodic a=new TuristickiVodic(rs.getInt("ID_VODICA"),rs.getString("IME"),rs.getString("PREZIME")
								,rs.getString("SLUZBENI_TELEFON"));
						vodici.add(a);
					}
				} finally {
					stmt.close();
				}
				return vodici;
			}

		});

		return vodici;
	}

	@Override
	@Transactional
	public List<PonudaSmestaja> showAllAccommodationOffers(int idAranzmana) {
		Session session = sessionFactory.getCurrentSession();

		List<PonudaSmestaja> ponude=null;
		ponude = session.doReturningWork(new ReturningWork<List<PonudaSmestaja>>() {

			@Override
			public List<PonudaSmestaja> execute(Connection connection) throws SQLException {

				List<PonudaSmestaja> ponude=new ArrayList<PonudaSmestaja>();
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from prikaziSvePonudeSmestajaZaAranzman(?)";
					stmt = connection.prepareCall(sqlString);
					stmt.setInt(1, idAranzmana);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						PonudaSmestaja a=new PonudaSmestaja(rs.getInt("ID_PONUDE"),rs.getInt("ID_ARANZMANA"),
								rs.getString("NAZIV"),rs.getDate("DATUM_POCETKA")
								,rs.getDate("DATUM_ZAVRSETKA"));
						ponude.add(a);
					}
				} finally {
					stmt.close();
				}
				return ponude;
			}

		});

		return ponude;
	}

	@Override
	@Transactional
	public String addNewEngagement(int idAr, int idVodica, int idPonude, double dnevnica) {
		Session session=sessionFactory.getCurrentSession();
		String rezultat=null;
		
		rezultat = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String rez="";
				CallableStatement cstmt=null;
				try {
					String sqlString="{call unosAngazovanjaVodica (?,?,?,?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setInt(1, idVodica);
					cstmt.setInt(2, idAr);
					cstmt.setInt(3, idPonude);
					cstmt.setDouble(4,dnevnica);
					cstmt.registerOutParameter(5, java.sql.Types.NVARCHAR);

					cstmt.executeUpdate();
					rez=cstmt.getString(5);
				} 
				catch(SQLServerException ex) {
					//ex.printStackTrace();
					rez="Došlo je do greške sa bazom.";
				}
				finally {
					cstmt.close();
				}
				return rez;
			}

		});

		return rezultat;
	}

	@Override
	@Transactional
	public String addNewEngagementGroup10(int idAr, int idVodica, double dnevnica) {
		Session session=sessionFactory.getCurrentSession();
		String rezultat=null;
		
		rezultat = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String rez="";
				CallableStatement cstmt=null;
				try {
					String sqlString="{call unosAngazovanjaVodicaAranzman (?,?,?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setInt(1, idVodica);
					cstmt.setInt(2, idAr);
					cstmt.setDouble(3,dnevnica);
					cstmt.registerOutParameter(4, java.sql.Types.NVARCHAR);

					cstmt.executeUpdate();
					rez=cstmt.getString(4);
				} 
				catch(SQLServerException ex) {
					//ex.printStackTrace();
					rez="Došlo je do greške sa bazom.";
				}
				finally {
					cstmt.close();
				}
				return rez;
			}

		});

		return rezultat;
	}

	@Override
	@Transactional
	public List<AngazovanjeVodica> getAllEngagements() {
		Session session = sessionFactory.getCurrentSession();

		List<AngazovanjeVodica> ang=null;
		ang = session.doReturningWork(new ReturningWork<List<AngazovanjeVodica>>() {

			@Override
			public List<AngazovanjeVodica> execute(Connection connection) throws SQLException {

				List<AngazovanjeVodica> ang=new ArrayList<AngazovanjeVodica>();
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from angazovanje_vodica";
					stmt = connection.prepareCall(sqlString);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						AngazovanjeVodica a=new AngazovanjeVodica(rs.getDouble("dnevnica"),rs.getInt("id_vodica"),
								rs.getInt("id_aranzmana"),rs.getDate("datum_pocetka"),rs.getDate("datum_kraja"));
						ang.add(a);
					}
				} finally {
					stmt.close();
				}
				return ang;
			}

		});

		return ang;
	}

	@Override
	@Transactional
	public List<TuristickiVodic> showAllNonengagedGuides(int id) {
		Session session = sessionFactory.getCurrentSession();

		List<TuristickiVodic> vodici=null;
		vodici = session.doReturningWork(new ReturningWork<List<TuristickiVodic>>() {

			@Override
			public List<TuristickiVodic> execute(Connection connection) throws SQLException {

				List<TuristickiVodic> vodici=new ArrayList<TuristickiVodic>();
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from sviNeangazovaniVodici(?)";
					stmt = connection.prepareCall(sqlString);
					stmt.setInt(1, id);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						TuristickiVodic a=new TuristickiVodic(rs.getInt("ID_VODICA"),rs.getString("IME"),rs.getString("PREZIME")
								,rs.getString("SLUZBENI_TELEFON"));
						vodici.add(a);
					}
				} finally {
					stmt.close();
				}
				return vodici;
			}

		});

		return vodici;
	}

	@Override
	@Transactional
	public String deleteEngagement(int idAr, int idVodica) {
		Session session=sessionFactory.getCurrentSession();
		String rezultat=null;
		
		rezultat = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String rez="";
				CallableStatement cstmt=null;
				try {
					String sqlString="{call brisanjeAngazovanja (?,?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setInt(1, idAr);
					cstmt.setInt(2, idVodica);
					cstmt.registerOutParameter(3, java.sql.Types.NVARCHAR);

					cstmt.executeUpdate();
					rez=cstmt.getString(3);
				} 
				catch(SQLServerException ex) {
					//ex.printStackTrace();
					rez="Došlo je do greške sa bazom.";
				}
				finally {
					cstmt.close();
				}
				return rez;
			}

		});

		return rezultat;
	}

	@Override
	@Transactional
	public TuristickiVodic getGuide(int id) {
		Session session=sessionFactory.getCurrentSession();
		TuristickiVodic a=null;
		
		a = session.doReturningWork(new ReturningWork<TuristickiVodic>() {

			@Override
			public TuristickiVodic execute(Connection connection) throws SQLException {

				TuristickiVodic a=null;
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from turisticki_vodic where id_vodica=?";
					stmt = connection.prepareCall(sqlString);
					stmt.setInt(1, id);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						a=new TuristickiVodic(rs.getInt("id_vodica"),rs.getString("ime"),rs.getString("prezime"),
								rs.getString("sluzbeni_telefon"));
					}
				} finally {
					stmt.close();
				}
				return a;
			}

		});

		return a;
	}

	
}
