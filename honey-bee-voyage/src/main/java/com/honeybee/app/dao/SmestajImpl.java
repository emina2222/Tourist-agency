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

import com.honeybee.app.entity.PonudaSmestaja;
import com.honeybee.app.entity.Smestaj;
import com.honeybee.app.entity.SmestajnaJedinica;
import com.honeybee.app.entity.StavkaSmestaja;
import com.honeybee.app.entity.TipSmestaja;
import com.microsoft.sqlserver.jdbc.SQLServerException;

@Repository
public class SmestajImpl implements SmestajDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	@Transactional
	public List<Smestaj> getAllHotels() {
		Session session = sessionFactory.getCurrentSession();

		List<Smestaj> smestaj=null;
		smestaj = session.doReturningWork(new ReturningWork<List<Smestaj>>() {

			@Override
			public List<Smestaj> execute(Connection connection) throws SQLException {

				List<Smestaj> smestaj=new ArrayList<Smestaj>();
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from smestaj";
					stmt = connection.prepareCall(sqlString);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						Smestaj a=new Smestaj(rs.getInt("ID_SMESTAJA"),rs.getInt("ID_TIPA_SMESTAJA"),rs.getString("NAZIV")
								,rs.getString("OPIS_DELATNOSTI"),rs.getString("KONTAKT_TELEFON"),rs.getString("ADRESA"));
						smestaj.add(a);
					}
				} finally {
					stmt.close();
				}
				return smestaj;
			}

		});

		return smestaj;
	}
	
	@Override
	@Transactional
	public List<TipSmestaja> getAllTypesOfAccommodation() {
		Session session = sessionFactory.getCurrentSession();

		List<TipSmestaja> smestaj=null;
		smestaj = session.doReturningWork(new ReturningWork<List<TipSmestaja>>() {

			@Override
			public List<TipSmestaja> execute(Connection connection) throws SQLException {

				List<TipSmestaja> smestaj=new ArrayList<TipSmestaja>();
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from tip_smestaja";
					stmt = connection.prepareCall(sqlString);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						TipSmestaja a=new TipSmestaja(rs.getInt("ID_TIPA_SMESTAJA"),rs.getString("NAZIV"));
						smestaj.add(a);
					}
				} finally {
					stmt.close();
				}
				return smestaj;
			}

		});

		return smestaj;
	}

	@Override
	@Transactional
	public String addNewAccommodation(int idTipa, String naziv, String opis, String telefon, String adresa) {
		Session session=sessionFactory.getCurrentSession();
		String rezultat=null;
		
		rezultat = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String rez="";
				CallableStatement cstmt=null;
				try {
					String sqlString="{call unosSmestaja (?,?,?,?,?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setInt(1, idTipa);
					cstmt.setString(2, naziv);
					cstmt.setString(3, opis);
					cstmt.setString(4, telefon);
					cstmt.setString(5, adresa);
					cstmt.registerOutParameter(6, java.sql.Types.NVARCHAR);

					cstmt.executeUpdate();
					rez=cstmt.getString(6);
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
	public String addNewOffer(int idAranzmana, String naziv, String datumOd, String datumDo) {
		Session session=sessionFactory.getCurrentSession();
		String rezultat=null;
		
		rezultat = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String rez="";
				CallableStatement cstmt=null;
				try {
					String sqlString="{call unosPonudeSmestaja (?,?,?,?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setInt(1, idAranzmana);
					cstmt.setString(2, naziv);
					cstmt.setString(3, datumOd);
					cstmt.setString(4, datumDo);
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
	public String addNewRoomUnit(int idSobe, int idSmestaja, int brojKreveta, String opis) {
		Session session=sessionFactory.getCurrentSession();
		String rezultat=null;
		
		rezultat = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String rez="";
				CallableStatement cstmt=null;
				try {
					String sqlString="{call unosSmestajneJedinice (?,?,?,?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setInt(1, idSobe);
					cstmt.setInt(2, idSmestaja);
					cstmt.setInt(3, brojKreveta);
					cstmt.setString(4, opis);
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
	public List<SmestajnaJedinica> getAllRoomUnits() {
		Session session = sessionFactory.getCurrentSession();

		List<SmestajnaJedinica> smestaj=null;
		smestaj = session.doReturningWork(new ReturningWork<List<SmestajnaJedinica>>() {

			@Override
			public List<SmestajnaJedinica> execute(Connection connection) throws SQLException {

				List<SmestajnaJedinica> smestaj=new ArrayList<SmestajnaJedinica>();
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from smestajna_jedinica";
					stmt = connection.prepareCall(sqlString);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						SmestajnaJedinica a=new SmestajnaJedinica(rs.getInt("ID_SMESTAJA"),rs.getInt("ID_SMESTAJNE_JEDINICE")
								,rs.getInt("BROJ_KREVETA"),rs.getString("OPSTE_KARAKTERISTIKE"));
						smestaj.add(a);
					}
				} finally {
					stmt.close();
				}
				return smestaj;
			}

		});

		return smestaj;
	}

	@Override
	@Transactional
	public List<StavkaSmestaja> getAllOfferItems() {
		Session session = sessionFactory.getCurrentSession();

		List<StavkaSmestaja> smestaj=null;
		smestaj = session.doReturningWork(new ReturningWork<List<StavkaSmestaja>>() {

			@Override
			public List<StavkaSmestaja> execute(Connection connection) throws SQLException {

				List<StavkaSmestaja> smestaj=new ArrayList<StavkaSmestaja>();
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from stavka_ponude_smestaja";
					stmt = connection.prepareCall(sqlString);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						StavkaSmestaja a=new StavkaSmestaja(rs.getInt("ID_SMESTAJA"),rs.getInt("ID_SMESTAJNE_JEDINICE")
								,rs.getInt("id_ponude"),rs.getString("pansion"),rs.getDouble("cena"));
						smestaj.add(a);
					}
				} finally {
					stmt.close();
				}
				return smestaj;
			}

		});

		return smestaj;
	}

	@Override
	@Transactional
	public List<SmestajnaJedinica> getRoomUnitsInHotel(int id) {
		Session session = sessionFactory.getCurrentSession();

		List<SmestajnaJedinica> smestaj=null;
		smestaj = session.doReturningWork(new ReturningWork<List<SmestajnaJedinica>>() {

			@Override
			public List<SmestajnaJedinica> execute(Connection connection) throws SQLException {

				List<SmestajnaJedinica> smestaj=new ArrayList<SmestajnaJedinica>();
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from smestajna_jedinica where id_smestaja=?";
					stmt = connection.prepareCall(sqlString);
					stmt.setInt(1, id);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						SmestajnaJedinica a=new SmestajnaJedinica(rs.getInt("ID_SMESTAJA"),rs.getInt("ID_SMESTAJNE_JEDINICE")
								,rs.getInt("BROJ_KREVETA"),rs.getString("OPSTE_KARAKTERISTIKE"));
						smestaj.add(a);
					}
				} finally {
					stmt.close();
				}
				return smestaj;
			}

		});

		return smestaj;
	}

	@Override
	@Transactional
	public String addNewAccommodationItem(int idPonude, int idSmJce, int idSmestaja, double cena, String pansion) {
		Session session=sessionFactory.getCurrentSession();
		String rezultat=null;
		
		rezultat = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String rez="";
				CallableStatement cstmt=null;
				try {
					String sqlString="{call unosStavkeSmestaja (?,?,?,?,?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setInt(1, idPonude);
					cstmt.setInt(2, idSmJce);
					cstmt.setInt(3, idSmestaja);
					cstmt.setDouble(4, cena);
					cstmt.setString(5, pansion);
					cstmt.registerOutParameter(6, java.sql.Types.NVARCHAR);

					cstmt.executeUpdate();
					rez=cstmt.getString(6);
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
	public List<PonudaSmestaja> getAllOffers() {
		Session session = sessionFactory.getCurrentSession();

		List<PonudaSmestaja> smestaj=null;
		smestaj = session.doReturningWork(new ReturningWork<List<PonudaSmestaja>>() {

			@Override
			public List<PonudaSmestaja> execute(Connection connection) throws SQLException {

				List<PonudaSmestaja> smestaj=new ArrayList<PonudaSmestaja>();
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from ponuda_smestaja";
					stmt = connection.prepareCall(sqlString);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						PonudaSmestaja a=new PonudaSmestaja(rs.getInt("ID_ponude"),rs.getInt("ID_aranzmana"),rs.getString("NAZIV")
								,rs.getDate("Datum_pocetka"),rs.getDate("datum_zavrsetka"));
						smestaj.add(a);
					}
				} finally {
					stmt.close();
				}
				return smestaj;
			}

		});

		return smestaj;
	}

	@Override
	@Transactional
	public String deleteOfferItem(int idSmestaja, int idSmJce, int idPonude) {
		Session session=sessionFactory.getCurrentSession();
		String rezultat=null;
		
		rezultat = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String rez="";
				CallableStatement cstmt=null;
				try {
					String sqlString="{call brisanjeStavkePonudeSmestaja (?,?,?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setInt(1, idSmestaja);
					cstmt.setInt(2, idSmJce);
					cstmt.setInt(3, idPonude);
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
	public Smestaj getHotel(int id) {
		Session session=sessionFactory.getCurrentSession();
		Smestaj a=null;
		
		a = session.doReturningWork(new ReturningWork<Smestaj>() {

			@Override
			public Smestaj execute(Connection connection) throws SQLException {

				Smestaj a=null;
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from smestaj where id_smestaja=?";
					stmt = connection.prepareCall(sqlString);
					stmt.setInt(1, id);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						a=new Smestaj(rs.getInt("id_smestaja"),rs.getString("naziv"));
					}
				} finally {
					stmt.close();
				}
				return a;
			}

		});

		return a;
	}

	@Override
	@Transactional
	public String deleteOffer(int idPonude) {
		Session session=sessionFactory.getCurrentSession();
		String rezultat=null;
		
		rezultat = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String rez="";
				CallableStatement cstmt=null;
				try {
					String sqlString="{call brisiPonudeSmestaja (?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setInt(1, idPonude);
					cstmt.registerOutParameter(2, java.sql.Types.NVARCHAR);

					cstmt.executeUpdate();
					rez=cstmt.getString(2);
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

}
