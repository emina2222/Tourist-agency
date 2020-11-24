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

import com.honeybee.app.entity.DostupnoVozilo;
import com.honeybee.app.entity.PonudaPrevoznika;
import com.honeybee.app.entity.Prevoznik;
import com.honeybee.app.entity.TipVozila;
import com.microsoft.sqlserver.jdbc.SQLServerException;

@Repository
public class PrevozImpl implements PrevozDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public List<Prevoznik> getAllTransporters() {
		Session session = sessionFactory.getCurrentSession();

		List<Prevoznik> prevoz=null;
		prevoz = session.doReturningWork(new ReturningWork<List<Prevoznik>>() {

			@Override
			public List<Prevoznik> execute(Connection connection) throws SQLException {

				List<Prevoznik> prevoz=new ArrayList<Prevoznik>();
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from prevoznik";
					stmt = connection.prepareCall(sqlString);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						Prevoznik a=new Prevoznik(rs.getInt("ID_PREVOZNIKA"),rs.getString("NAZIV")
								,rs.getString("OPIS_DELATNOSTI"));
						prevoz.add(a);
					}
				} finally {
					stmt.close();
				}
				return prevoz;
			}

		});

		return prevoz;
	}

	@Override
	@Transactional
	public String addNewTransporter(String naziv, String opis) {
		Session session=sessionFactory.getCurrentSession();
		String rezultat=null;
		
		rezultat = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String rez="";
				CallableStatement cstmt=null;
				try {
					String sqlString="{call unosNovogPrevoznika (?,?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setString(1, naziv);
					cstmt.setString(2, opis);
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
	public List<DostupnoVozilo> getAllVehicles(int idPrevoznika) {
		Session session = sessionFactory.getCurrentSession();

		List<DostupnoVozilo> prevoz=null;
		prevoz = session.doReturningWork(new ReturningWork<List<DostupnoVozilo>>() {

			@Override
			public List<DostupnoVozilo> execute(Connection connection) throws SQLException {

				List<DostupnoVozilo> prevoz=new ArrayList<DostupnoVozilo>();
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from prikaziVozilaPrevoznika(?)";
					stmt = connection.prepareCall(sqlString);
					stmt.setInt(1, idPrevoznika);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						DostupnoVozilo a=new DostupnoVozilo(rs.getInt("id_vozila"),rs.getInt("id_prevoznika"),rs.getString("NAZIV")
								,rs.getString("registarske_table"));
						prevoz.add(a);
					}
				} finally {
					stmt.close();
				}
				return prevoz;
			}

		});

		return prevoz;
	}

	@Override
	@Transactional
	public String addNewTransportOffer(int idAranzmana, int idPrevoznika, int idVozila, String naziv, String datumOd, String datumDo,
			double cena) {
		Session session=sessionFactory.getCurrentSession();
		String rezultat=null;
		
		rezultat = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String rez="";
				CallableStatement cstmt=null;
				try {
					String sqlString="{call unosPonudePrevoznika (?,?,?,?,?,?,?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setInt(1, idAranzmana);
					cstmt.setInt(2, idPrevoznika);
					cstmt.setInt(3, idVozila);
					cstmt.setString(4, naziv);
					cstmt.setString(5, datumOd);
					cstmt.setString(6, datumDo);
					cstmt.setDouble(7, cena);
					cstmt.registerOutParameter(8, java.sql.Types.NVARCHAR);

					cstmt.executeUpdate();
					rez=cstmt.getString(8);
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
	public List<PonudaPrevoznika> getAllOffers() {
		Session session = sessionFactory.getCurrentSession();

		List<PonudaPrevoznika> prevoz=null;
		prevoz = session.doReturningWork(new ReturningWork<List<PonudaPrevoznika>>() {

			@Override
			public List<PonudaPrevoznika> execute(Connection connection) throws SQLException {

				List<PonudaPrevoznika> prevoz=new ArrayList<PonudaPrevoznika>();
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from ponuda_prevoznika";
					stmt = connection.prepareCall(sqlString);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						PonudaPrevoznika a=new PonudaPrevoznika(rs.getInt("id_ponude_prevoznika"),rs.getInt("id_aranzmana"),rs.getString("NAZIV")
								,rs.getDate("datum_pocetka"),rs.getDate("datum_zavrsetka"));
						prevoz.add(a);
					}
				} finally {
					stmt.close();
				}
				return prevoz;
			}

		});

		return prevoz;
	}

	@Override
	@Transactional
	public String deleteTransportOffer(int id) {
		Session session=sessionFactory.getCurrentSession();
		String rezultat=null;
		
		rezultat = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String rez="";
				CallableStatement cstmt=null;
				try {
					String sqlString="{call brisanjePonudePrevoznika (?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setInt(1, id);
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

	@Override
	@Transactional
	public String addNewVehicle(int idPrevoznika, int idTipa, String naziv, String tablice) {
		Session session=sessionFactory.getCurrentSession();
		String rezultat=null;
		
		rezultat = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String rez="";
				CallableStatement cstmt=null;
				try {
					String sqlString="{call unosVozila (?,?,?,?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setInt(1, idPrevoznika);
					cstmt.setInt(2, idTipa);
					cstmt.setString(3, naziv);
					cstmt.setString(4, tablice);
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
	public List<DostupnoVozilo> getAllVehicles() {
		Session session=sessionFactory.getCurrentSession();
		List<DostupnoVozilo> prevoz=null;
		prevoz = session.doReturningWork(new ReturningWork<List<DostupnoVozilo>>() {

			@Override
			public List<DostupnoVozilo> execute(Connection connection) throws SQLException {

				List<DostupnoVozilo> prevoz=new ArrayList<DostupnoVozilo>();
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from dostupno_vozilo";
					stmt = connection.prepareCall(sqlString);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						DostupnoVozilo a=new DostupnoVozilo(rs.getInt("id_vozila"),rs.getInt("id_tipa_vozila"),rs.getInt("id_prevoznika"),rs.getString("NAZIV")
								,rs.getString("registarske_table"));
						prevoz.add(a);
					}
				} finally {
					stmt.close();
				}
				return prevoz;
			}

		});

		return prevoz;
	}

	@Override
	@Transactional
	public List<TipVozila> getAllTypesOfTransport() {
		Session session=sessionFactory.getCurrentSession();
		List<TipVozila> tip=null;
		
		tip = session.doReturningWork(new ReturningWork<List<TipVozila>>() {

			@Override
			public List<TipVozila> execute(Connection connection) throws SQLException {

				List<TipVozila> tipovi=new ArrayList<TipVozila>();
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from tip_vozila";
					stmt = connection.prepareCall(sqlString);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						TipVozila a=new TipVozila(rs.getInt("id_tipa_vozila"),rs.getString("naziv"));
						tipovi.add(a);
					}
				} finally {
					stmt.close();
				}
				return tipovi;
			}

		});

		return tip;
	}

	@Override
	@Transactional
	public Prevoznik getTransporter(int id) {
		Session session=sessionFactory.getCurrentSession();
		Prevoznik a=null;
		
		a = session.doReturningWork(new ReturningWork<Prevoznik>() {

			@Override
			public Prevoznik execute(Connection connection) throws SQLException {

				Prevoznik a=null;
				PreparedStatement stmt = null;
				try {
					String sqlString = "select id_prevoznika,naziv from prevoznik where id_prevoznika=?";
					stmt = connection.prepareCall(sqlString);
					stmt.setInt(1, id);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						a=new Prevoznik(rs.getInt("id_prevoznika"),rs.getString("naziv"));
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
	public TipVozila getType(int id) {
		Session session=sessionFactory.getCurrentSession();
		TipVozila a=null;
		
		a = session.doReturningWork(new ReturningWork<TipVozila>() {

			@Override
			public TipVozila execute(Connection connection) throws SQLException {

				TipVozila a=null;
				PreparedStatement stmt = null;
				try {
					String sqlString = "select id_tipa_vozila, naziv from tip_vozila where id_tipa_vozila=?";
					stmt = connection.prepareCall(sqlString);
					stmt.setInt(1, id);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						a=new TipVozila(rs.getInt("id_tipa_vozila"),rs.getString("naziv"));
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
