package com.honeybee.app.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.honeybee.app.entity.Aranzman;
import com.honeybee.app.entity.Atrakcija;
import com.honeybee.app.entity.Destinacija;
import com.honeybee.app.entity.FakultativniIzlet;
import com.honeybee.app.entity.PonudaSmestaja;
import com.honeybee.app.entity.Smestaj;
import com.honeybee.app.entity.TipAtrakcije;
import com.microsoft.sqlserver.jdbc.SQLServerException;

@Repository
public class AranzmanImpl implements AranzmanDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Aranzman> getArrangementsInCategory(int id) {
		Session session = sessionFactory.getCurrentSession();

		List<Aranzman> aranzmani=null;
		aranzmani = session.doReturningWork(new ReturningWork<List<Aranzman>>() {

			@Override
			public List<Aranzman> execute(Connection connection) throws SQLException {

				List<Aranzman> aranzmani=new ArrayList<Aranzman>();
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from prikazAranzmanaUKategoriji(?)";
					stmt = connection.prepareCall(sqlString);
					stmt.setInt(1, id);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						Aranzman a=new Aranzman(rs.getInt("ID_ARANZMANA"),rs.getString("NAZIV"));
						aranzmani.add(a);
					}
				} finally {
					stmt.close();
				}
				return aranzmani;
			}

		});

		return aranzmani;
	}

	@Override
	@Transactional
	public List<Destinacija> getDestinationInArrangement(int id) {
		Session session=sessionFactory.getCurrentSession();
		List<Destinacija> destinacije=null;
		//select * from prikazDestinacijaNaAranzmanu
		
		destinacije = session.doReturningWork(new ReturningWork<List<Destinacija>>() {

			@Override
			public List<Destinacija> execute(Connection connection) throws SQLException {

				List<Destinacija> dest=new ArrayList<Destinacija>();
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from prikazDestinacijaNaAranzmanu(?)";
					stmt = connection.prepareCall(sqlString);
					stmt.setInt(1, id);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						Destinacija d=new Destinacija(rs.getInt("ID_DESTINACIJE"),rs.getString("LOKACIJA"),rs.getString("DRZAVA"),rs.getString("OPIS"));
						dest.add(d);
					}
				} finally {
					stmt.close();
				}
				return dest;
			}

		});

		return destinacije;
	}

	@Override
	@Transactional
	public double getPriceForTour(int id) {
		Session session=sessionFactory.getCurrentSession();
		double cena=0;
		//select * from prikazDestinacijaNaAranzmanu
		
		cena = session.doReturningWork(new ReturningWork<Double>() {

			@Override
			public Double execute(Connection connection) throws SQLException {

				double cena=0;
				//PreparedStatement stmt = null;
				CallableStatement cstmt=null;
				try {
					String sqlString="{call ukupnaCenaTuraOutput(?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setInt(1, id);
					cstmt.registerOutParameter(2, java.sql.Types.FLOAT);

					cstmt.executeUpdate();
					cena=cstmt.getDouble(2);
				} finally {
					cstmt.close();
				}
				return cena;
			}

		});

		return cena;
	}

	@Override
	@Transactional
	public String getTypeOfTransport(int id) {
		Session session=sessionFactory.getCurrentSession();
		String tip=null;
		
		tip = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String tip=null;
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from prikazTipaPrevoza(?)";
					stmt = connection.prepareCall(sqlString);
					stmt.setInt(1, id);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						tip=rs.getString("NAZIV");
					}
				} finally {
					stmt.close();
				}
				return tip;
			}

		});

		return tip;
	}

	@Override
	@Transactional
	public double getPriceForOneDayTrip(int id) {
		Session session=sessionFactory.getCurrentSession();
		double cena=0;
		
		cena = session.doReturningWork(new ReturningWork<Double>() {

			@Override
			public Double execute(Connection connection) throws SQLException {

				double cena=0;
				//PreparedStatement stmt = null;
				CallableStatement cstmt=null;
				try {
					String sqlString="{call racunanjeUkupneCeneJednodnevniIzlet(?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setInt(1, id);
					cstmt.registerOutParameter(2, java.sql.Types.FLOAT);

					cstmt.executeUpdate();
					cena=cstmt.getDouble(2);
				} finally {
					cstmt.close();
				}
				return cena;
			}

		});

		return cena;
	}

	@Override
	@Transactional
	public double getLowestPriceForArrangement(int id) {
		Session session=sessionFactory.getCurrentSession();
		double cena=0;
		
		cena = session.doReturningWork(new ReturningWork<Double>() {

			@Override
			public Double execute(Connection connection) throws SQLException {

				double cena=0;
				CallableStatement cstmt=null;
				try {
					String sqlString="{call najpovoljnijiAranzmanOutput(?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setInt(1, id);
					cstmt.registerOutParameter(2, java.sql.Types.FLOAT);

					cstmt.executeUpdate();
					cena=cstmt.getDouble(2);
				} finally {
					cstmt.close();
				}
				return cena;
			}

		});

		return cena;
	}

	@Override
	@Transactional
	public List<TipAtrakcije> getTypesOfAttractions(int id) {
		Session session=sessionFactory.getCurrentSession();
		List<TipAtrakcije> tipovi=null;
		//select * from prikazDestinacijaNaAranzmanu
		
		tipovi = session.doReturningWork(new ReturningWork<List<TipAtrakcije>>() {

			@Override
			public List<TipAtrakcije> execute(Connection connection) throws SQLException {

				List<TipAtrakcije> tipovi=new ArrayList<TipAtrakcije>();
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from prikaziTipAtrakcije(?)";
					stmt = connection.prepareCall(sqlString);
					stmt.setInt(1, id);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						TipAtrakcije t=new TipAtrakcije(rs.getInt("id_tipa_atrakcije"),rs.getString("naziv"));
						tipovi.add(t);
					}
				} finally {
					stmt.close();
				}
				return tipovi;
			}

		});

		return tipovi;
	}

	@Override
	@Transactional
	public List<Atrakcija> getAttractions(int idAr, int idTipa) {
		Session session=sessionFactory.getCurrentSession();
		List<Atrakcija> atrakcija=null;		
		atrakcija = session.doReturningWork(new ReturningWork<List<Atrakcija>>() {

			@Override
			public List<Atrakcija> execute(Connection connection) throws SQLException {

				List<Atrakcija> atr=new ArrayList<Atrakcija>();
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from prikaziAtrakcije(?,?)";
					stmt = connection.prepareCall(sqlString);
					stmt.setInt(1, idAr);
					stmt.setInt(2, idTipa);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						Atrakcija a=new Atrakcija(rs.getInt("id_atrakcije"),rs.getString("NAZIV"),rs.getString("opis"));
						atr.add(a);
					}
				} finally {
					stmt.close();
				}
				return atr;
			}

		});

		return atrakcija;
	}

	@Override
	@Transactional
	public Aranzman getArrangement(int id) {
		Session session=sessionFactory.getCurrentSession();
		Aranzman a=null;
		
		a = session.doReturningWork(new ReturningWork<Aranzman>() {

			@Override
			public Aranzman execute(Connection connection) throws SQLException {

				Aranzman a=null;
				PreparedStatement stmt = null;
				try {
					String sqlString = "select id_aranzmana,datum_polaska,datum_dolaska,naziv, kapacitet from aranzman where id_aranzmana=?";
					stmt = connection.prepareCall(sqlString);
					stmt.setInt(1, id);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						a=new Aranzman(rs.getInt("id_aranzmana"),rs.getString("naziv"),rs.getDate("datum_polaska"),
								rs.getDate("datum_dolaska"), rs.getInt("kapacitet"));
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
	public List<Smestaj> getAccommodation(int id) {
		Session session=sessionFactory.getCurrentSession();
		List<Smestaj> smestaji=null;		
		smestaji = session.doReturningWork(new ReturningWork<List<Smestaj>>() {

			@Override
			public List<Smestaj> execute(Connection connection) throws SQLException {

				List<Smestaj> sm=new ArrayList<Smestaj>();
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from prikazSmestajaNaAranzmanu(?)";
					stmt = connection.prepareCall(sqlString);
					stmt.setInt(1, id);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						Smestaj a=new Smestaj(rs.getInt("id_smestaja"),rs.getString("NAZIV"));
						sm.add(a);
					}
				} finally {
					stmt.close();
				}
				return sm;
			}

		});

		return smestaji;
	}

	@Override
	@Transactional
	public List<Integer> getNumberOfBedsUnit(int idAr, int idSmestaj) {
		Session session=sessionFactory.getCurrentSession();
		List<Integer> krevetneSobe=null;		
		krevetneSobe = session.doReturningWork(new ReturningWork<List<Integer>>() {

			@Override
			public List<Integer> execute(Connection connection) throws SQLException {

				List<Integer> br=new ArrayList<Integer>();
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from prikazPonudjenihBrojaKrevetaUSmestaju(?,?)";
					stmt = connection.prepareCall(sqlString);
					stmt.setInt(1, idAr);
					stmt.setInt(2, idSmestaj);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						int a=rs.getInt("broj_kreveta");
						br.add(a);
					}
				} finally {
					stmt.close();
				}
				return br;
			}

		});

		return krevetneSobe;
	}

	@Override
	@Transactional
	public List<PonudaSmestaja> getOffers(int id) {
		Session session=sessionFactory.getCurrentSession();
		List<PonudaSmestaja> ponude=null;		
		ponude = session.doReturningWork(new ReturningWork<List<PonudaSmestaja>>() {

			@Override
			public List<PonudaSmestaja> execute(Connection connection) throws SQLException {

				List<PonudaSmestaja> ponude=new ArrayList<PonudaSmestaja>();
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from prikazPonudaDatuma(?)";
					stmt = connection.prepareCall(sqlString);
					stmt.setInt(1, id);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						PonudaSmestaja ps=new PonudaSmestaja(rs.getInt("id_ponude"),rs.getString("naziv"),
								rs.getDate("datum_pocetka"),rs.getDate("datum_zavrsetka"));
						ponude.add(ps);
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
	public double getPriceForSpecificAccomodation(int idAr, int idSmestaja, int idPonude, int brojKreveta) {
		Session session=sessionFactory.getCurrentSession();
		double cena=0;
		
		cena = session.doReturningWork(new ReturningWork<Double>() {

			@Override
			public Double execute(Connection connection) throws SQLException {

				double cena=0;
				//PreparedStatement stmt = null;
				CallableStatement cstmt=null;
				try {
					//String sqlString = "exec ukupnaCenaTuraOutput ?,? out";
					String sqlString="{call ukupnaCenaAranzmanaBrojKrevetaOutput(?,?,?,?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setInt(1, idAr);
					cstmt.setInt(2, idSmestaja);
					cstmt.setInt(3, idPonude);
					cstmt.setInt(4, brojKreveta);
					cstmt.registerOutParameter(5, java.sql.Types.FLOAT);

					cstmt.executeUpdate();
					cena=cstmt.getDouble(5);
				}
				finally {
					cstmt.close();
				}
				return cena;
			}

		});

		return cena;
	}

	@Override
	@Transactional
	public String getNameOfTheCategory(int id) {
		Session session=sessionFactory.getCurrentSession();
		String naziv="";
		
		naziv = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String naziv="";
				PreparedStatement stmt = null;
				try {
					String sqlString = "select naziv from kategorija_aranzmana where id_kategorije in (select id_kategorije from aranzman where id_aranzmana=?)";
					stmt = connection.prepareCall(sqlString);
					stmt.setInt(1, id);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						naziv=rs.getString("naziv");
					}
				} finally {
					stmt.close();
				}
				return naziv;
			}

		});

		return naziv;
	}

	@Override
	@Transactional
	public String getNameOfAccomodation(int id) {
		Session session=sessionFactory.getCurrentSession();
		String naziv=null;
		
		naziv = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String naziv=null;
				PreparedStatement stmt = null;
				try {
					String sqlString = "select naziv from smestaj where id_smestaja=?";
					stmt = connection.prepareCall(sqlString);
					stmt.setInt(1, id);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						naziv=rs.getString("NAZIV");
					}
				} finally {
					stmt.close();
				}
				return naziv;
			}

		});

		return naziv;
	}

	@Override
	@Transactional
	public String getNameOfArrangement(int id) {
		Session session=sessionFactory.getCurrentSession();
		String naziv=null;
		
		naziv = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String naziv=null;
				PreparedStatement stmt = null;
				try {
					String sqlString = "select naziv from aranzman where id_aranzmana=?";
					stmt = connection.prepareCall(sqlString);
					stmt.setInt(1, id);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						naziv=rs.getString("NAZIV");
					}
				} finally {
					stmt.close();
				}
				return naziv;
			}

		});

		return naziv;
	}

	@Override
	@Transactional
	public String getDates(int id) {
		Session session=sessionFactory.getCurrentSession();
		String datumi="";
		
		datumi = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String datumi=null;
				Date datum1=null;
				Date datum2=null;
				String pattern = "dd/MM/yyyy";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				PreparedStatement stmt = null;
				try {
					String sqlString = "select datum_pocetka,datum_zavrsetka from ponuda_smestaja where id_ponude=?";
					stmt = connection.prepareCall(sqlString);
					stmt.setInt(1, id);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						datum1=rs.getDate("datum_pocetka");
						datum2=rs.getDate("datum_zavrsetka");
					}
					datumi=simpleDateFormat.format(datum1)+" - "+simpleDateFormat.format(datum2);
					
				} finally {
					stmt.close();
				}
				return datumi;
			}

		});

		return datumi;
	}

	@Override
	@Transactional
	public String reserve(int idSmestaja, int idPonude, int brojKreveta, int idAranzmana, int idPutnika) {
		// exec rezervacijaAdminPlusBrisanje 12,4,10,200,201,115,2
		Session session=sessionFactory.getCurrentSession();
		String rezultat=null;
		
		rezultat = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String rez="";
				CallableStatement cstmt=null;
				try {
					String sqlString="{call rezervacijaFull (?,?,?,?,?,?,?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setInt(1, idSmestaja);
					cstmt.setInt(2, idPonude);
					cstmt.setInt(3, brojKreveta);
					cstmt.setInt(4, idAranzmana);
					cstmt.setInt(5, idPutnika);
					cstmt.setInt(6, 114);
					cstmt.setInt(7, brojKreveta);
					cstmt.registerOutParameter(8, java.sql.Types.NVARCHAR);

					cstmt.executeUpdate();
					rez=cstmt.getString(8);
				}catch(SQLServerException ex) {
					//ex.printStackTrace();
					rez="Već ste rezervisali ovaj aranžman.";
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
	public double getTravelInsurancePrice(int id) {
		Session session=sessionFactory.getCurrentSession();
		double cena=0;
		
		cena = session.doReturningWork(new ReturningWork<Double>() {

			@Override
			public Double execute(Connection connection) throws SQLException {

				double cena=0;
				PreparedStatement stmt = null;
				try {
					String sqlString = "select cena_osiguranja from aranzman where id_aranzmana=?";
					stmt = connection.prepareCall(sqlString);
					stmt.setInt(1, id);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						cena=rs.getDouble("CENA_OSIGURANJA");
					}
				}
				finally {
					stmt.close();
				}
				return cena;
			}

		});

		return cena;
	}

	@Override
	@Transactional
	public String reserveWithInsurance(int idSmestaja, int idPonude, int brojKreveta, int idAranzmana, int idPutnika,
			String insurance) {
		Session session=sessionFactory.getCurrentSession();
		String rezultat=null;
		
		rezultat = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String rez="";
				CallableStatement cstmt=null;
				try {
					String sqlString="{call rezervacijaFullSaOsiguranjem (?,?,?,?,?,?,?,?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setInt(1, idSmestaja);
					cstmt.setInt(2, idPonude);
					cstmt.setInt(3, brojKreveta);
					cstmt.setInt(4, idAranzmana);
					cstmt.setInt(5, idPutnika);
					cstmt.setInt(6, 114);
					cstmt.setInt(7, brojKreveta);
					cstmt.setString(8, insurance);
					cstmt.registerOutParameter(9, java.sql.Types.NVARCHAR);

					cstmt.executeUpdate();
					rez=cstmt.getString(9);
				} 
				catch(SQLServerException ex) {
					//ex.printStackTrace();
					rez="Već ste rezervisali ovaj aranžman.";
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
	public String reserveTour(int idAranzmana, int idPutnika, int brojPutnika, String osiguranje) {
		Session session=sessionFactory.getCurrentSession();
		String rezultat=null;
		
		rezultat = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String rez="";
				CallableStatement cstmt=null;
				try {
					String sqlString="{call rezervacijaTure (?,?,?,?,?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setInt(1, idAranzmana);
					cstmt.setInt(2, idPutnika);
					cstmt.setInt(3, 114);
					cstmt.setInt(4, brojPutnika);
					cstmt.setString(5, osiguranje);
					cstmt.registerOutParameter(6, java.sql.Types.NVARCHAR);

					cstmt.executeUpdate();
					rez=cstmt.getString(6);
				}
				catch(SQLServerException ex) {
					//ex.printStackTrace();
					rez="Već ste rezervisali ovaj aranžman.";
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
	public String reserveOneDayTrip(int idAranzmana, int idPutnika, int brojPutnika, String osiguranje) {
		Session session=sessionFactory.getCurrentSession();
		String rezultat=null;
		
		rezultat = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String rez="";
				CallableStatement cstmt=null;
				try {
					String sqlString="{call rezervacijaJednodnevniIzlet (?,?,?,?,?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setInt(1, idAranzmana);
					cstmt.setInt(2, idPutnika);
					cstmt.setInt(3, 114);
					cstmt.setInt(4, brojPutnika);
					cstmt.setString(5, osiguranje);
					cstmt.registerOutParameter(6, java.sql.Types.NVARCHAR);

					cstmt.executeUpdate();
					rez=cstmt.getString(6);
				} 
				catch(SQLServerException ex) {
					//ex.printStackTrace();
					rez="Već ste rezervisali ovaj aranžman.";
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
	public List<FakultativniIzlet> prikaziFakultativneIzlete(int id) {
		Session session=sessionFactory.getCurrentSession();
		List<FakultativniIzlet> izleti=null;		
		izleti = session.doReturningWork(new ReturningWork<List<FakultativniIzlet>>() {

			@Override
			public List<FakultativniIzlet> execute(Connection connection) throws SQLException {

				List<FakultativniIzlet> izleti=new ArrayList<FakultativniIzlet>();
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from prikaziFakultativneIzlete(?)";
					stmt = connection.prepareCall(sqlString);
					stmt.setInt(1, id);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						FakultativniIzlet fi=new FakultativniIzlet(rs.getInt("ID_IZLETA"),rs.getString("NAZIV"),
								rs.getDouble("CENA"),rs.getString("OPIS"));
						izleti.add(fi);
					}
				} finally {
					stmt.close();
				}
				return izleti;
			}

		});

		return izleti;
	}

	@Override
	@Transactional
	public PonudaSmestaja getOffer(int id) {
		Session session=sessionFactory.getCurrentSession();
		PonudaSmestaja ponuda=null;		
		ponuda = session.doReturningWork(new ReturningWork<PonudaSmestaja>() {

			@Override
			public PonudaSmestaja execute(Connection connection) throws SQLException {

				PonudaSmestaja ponuda=null;
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from prikaziPonudu(?)";
					stmt = connection.prepareCall(sqlString);
					stmt.setInt(1, id);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						ponuda=new PonudaSmestaja(rs.getInt("ID_PONUDE"),
								rs.getString("naziv"),rs.getDate("DATUM_POCETKA"),rs.getDate("DATUM_ZAVRSETKA"));
					}
				} finally {
					stmt.close();
				}
				return ponuda;
			}

		});

		return ponuda;
	}

	@Override
	@Transactional
	public String cancelFullReservation(int idAr, int idRezervacije, int idPutnika) {
		Session session=sessionFactory.getCurrentSession();
		String rezultat=null;
		
		rezultat = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String rez="";
				CallableStatement cstmt=null;
				try {
					String sqlString="{call otkaziFull (?,?,?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setInt(1, idAr);
					cstmt.setInt(2, idRezervacije);
					cstmt.setInt(3, idPutnika);
					cstmt.registerOutParameter(4, java.sql.Types.NVARCHAR);

					cstmt.executeUpdate();
					rez=cstmt.getString(4);
				}catch(SQLServerException ex) {
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
	public String cancelTourReservation(int idAr, int idRezervacije, int idPutnika) {
		Session session=sessionFactory.getCurrentSession();
		String rezultat=null;
		
		rezultat = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String rez="";
				CallableStatement cstmt=null;
				try {
					String sqlString="{call otkaziTuruIzlet (?,?,?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setInt(1, idAr);
					cstmt.setInt(2, idRezervacije);
					cstmt.setInt(3, idPutnika);
					cstmt.registerOutParameter(4, java.sql.Types.NVARCHAR);

					cstmt.executeUpdate();
					rez=cstmt.getString(4);
				}catch(SQLServerException ex) {
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
	public String reserveTourWithKids(int idAranzmana, int idPutnika, int brojPutnika, int brojDece,
			String osiguranje) {
		Session session=sessionFactory.getCurrentSession();
		String rezultat=null;
		
		rezultat = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String rez="";
				CallableStatement cstmt=null;
				try {
					String sqlString="{call rezervacijaTurePlusDeca (?,?,?,?,?,?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setInt(1, idAranzmana);
					cstmt.setInt(2, idPutnika);
					cstmt.setInt(3, 114);
					cstmt.setInt(4, brojPutnika);
					cstmt.setInt(5, brojDece);
					cstmt.setString(6, osiguranje);
					cstmt.registerOutParameter(7, java.sql.Types.NVARCHAR);

					cstmt.executeUpdate();
					rez=cstmt.getString(7);
				}
				catch(SQLServerException ex) {
					//ex.printStackTrace();
					rez="Već ste rezervisali ovaj aranžman.";
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
	public String reserveOneDayTripWithKids(int idAranzmana, int idPutnika, int brojPutnika, int brojDece,
			String osiguranje) {
		Session session=sessionFactory.getCurrentSession();
		String rezultat=null;
		
		rezultat = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String rez="";
				CallableStatement cstmt=null;
				try {
					String sqlString="{call rezervacijaJednodnevniIzlet (?,?,?,?,?,?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setInt(1, idAranzmana);
					cstmt.setInt(2, idPutnika);
					cstmt.setInt(3, 114);
					cstmt.setInt(4, brojPutnika);
					cstmt.setInt(5, brojDece);
					cstmt.setString(6, osiguranje);
					cstmt.registerOutParameter(7, java.sql.Types.NVARCHAR);

					cstmt.executeUpdate();
					rez=cstmt.getString(7);
				} 
				catch(SQLServerException ex) {
					//ex.printStackTrace();
					rez="Već ste rezervisali ovaj aranžman.";
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
	public List<Aranzman> getAllArrangements() {
		Session session = sessionFactory.getCurrentSession();

		List<Aranzman> aranzmani=null;
		aranzmani = session.doReturningWork(new ReturningWork<List<Aranzman>>() {

			@Override
			public List<Aranzman> execute(Connection connection) throws SQLException {

				List<Aranzman> aranzmani=new ArrayList<Aranzman>();
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from aranzman";
					stmt = connection.prepareCall(sqlString);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						Aranzman a=new Aranzman(rs.getInt("ID_ARANZMANA"),rs.getInt("ID_KATEGORIJE"),rs.getString("NAZIV"),
								rs.getInt("PROFIT_AGENCIJE"),rs.getDate("datum_polaska"),rs.getDate("datum_dolaska"),
								rs.getInt("popust"),rs.getDouble("CENA_OSIGURANJA"),rs.getInt("KAPACITET"),rs.getInt("OBJAVLJEN"));
						aranzmani.add(a);
					}
				} finally {
					stmt.close();
				}
				return aranzmani;
			}

		});

		return aranzmani;
	}

	@Override
	@Transactional
	public String addNewArrangement(int idKat, int profit, String polazak, String dolazak, String naziv, int popust,
			double cenaOsiguranja, int kapacitet) {
		Session session=sessionFactory.getCurrentSession();
		String rezultat=null;
		
		rezultat = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String rez="";
				CallableStatement cstmt=null;
				try {
					String sqlString="{call unosNovogAranzmana (?,?,?,?,?,?,?,?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setInt(1, idKat);
					cstmt.setInt(2, profit);
					cstmt.setString(3,polazak);
					cstmt.setString(4,dolazak);
					cstmt.setString(5, naziv);
					cstmt.setInt(6, popust);
					cstmt.setDouble(7, cenaOsiguranja);
					cstmt.setInt(8, kapacitet);
					cstmt.registerOutParameter(9, java.sql.Types.NVARCHAR);

					cstmt.executeUpdate();
					rez=cstmt.getString(9);
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
	public List<Destinacija> getAllDestinations() {
		Session session = sessionFactory.getCurrentSession();

		List<Destinacija> destinacije=null;
		destinacije = session.doReturningWork(new ReturningWork<List<Destinacija>>() {

			@Override
			public List<Destinacija> execute(Connection connection) throws SQLException {

				List<Destinacija> destinacije=new ArrayList<Destinacija>();
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from DESTINACIJA";
					stmt = connection.prepareCall(sqlString);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						Destinacija a=new Destinacija(rs.getInt("ID_DESTINACIJE"),rs.getString("LOKACIJA"),
								rs.getString("DRZAVA"),rs.getString("OPIS"));
						destinacije.add(a);
					}
				} finally {
					stmt.close();
				}
				return destinacije;
			}

		});

		return destinacije;
	}

	@Override
	@Transactional
	public String addNewDestination(String lokacija, String drzava, String opis) {
		Session session=sessionFactory.getCurrentSession();
		String rezultat=null;
		
		rezultat = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String rez="";
				CallableStatement cstmt=null;
				try {
					String sqlString="{call unosDestinacije (?,?,?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setString(1,lokacija);
					cstmt.setString(2,drzava);
					cstmt.setString(3, opis);
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
	public String publishArrangement(int id) {
		Session session=sessionFactory.getCurrentSession();
		String rezultat=null;
		
		rezultat = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String rez="";
				CallableStatement cstmt=null;
				try {
					String sqlString="{call objaviAranzman (?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setInt(1,id);
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
	public String unpublishArrangement(int id) {
		Session session=sessionFactory.getCurrentSession();
		String rezultat=null;
		
		rezultat = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String rez="";
				CallableStatement cstmt=null;
				try {
					String sqlString="{call sakrijAranzman (?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setInt(1,id);
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
	public String addDestinationToArrangement(int idAr, int idDest) {
		Session session=sessionFactory.getCurrentSession();
		String rezultat=null;
		
		rezultat = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String rez="";
				CallableStatement cstmt=null;
				try {
					String sqlString="{call unosUSpisakDestinacija (?,?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setInt(1,idAr);
					cstmt.setInt(2,idDest);
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
	public List<TipAtrakcije> getAllTypesOfAttractions() {
		Session session=sessionFactory.getCurrentSession();
		List<TipAtrakcije> tipovi=null;
		
		tipovi = session.doReturningWork(new ReturningWork<List<TipAtrakcije>>() {

			@Override
			public List<TipAtrakcije> execute(Connection connection) throws SQLException {

				List<TipAtrakcije> tipovi=new ArrayList<TipAtrakcije>();
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from tip_atrakcije";
					stmt = connection.prepareCall(sqlString);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						TipAtrakcije t=new TipAtrakcije(rs.getInt("id_tipa_atrakcije"),rs.getString("naziv"));
						tipovi.add(t);
					}
				} finally {
					stmt.close();
				}
				return tipovi;
			}

		});

		return tipovi;
	}

	@Override
	@Transactional
	public String addNewAttraction(int idDest, int idTipa, String naziv, String opis) {
		Session session=sessionFactory.getCurrentSession();
		String rezultat=null;
		
		rezultat = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String rez="";
				CallableStatement cstmt=null;
				try {
					String sqlString="{call unosAtrakcije (?,?,?,?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setInt(1,idTipa);
					cstmt.setInt(2,idDest);
					cstmt.setString(3, naziv);
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
	public String addPaidTrip(int idDest, String naziv, double cena, String opis) {
		Session session=sessionFactory.getCurrentSession();
		String rezultat=null;
		
		rezultat = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String rez="";
				CallableStatement cstmt=null;
				try {
					String sqlString="{call unosIzleta (?,?,?,?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setInt(1,idDest);
					cstmt.setString(2,naziv);
					cstmt.setDouble(3, cena);
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
	public String deleteDestinationInArrangement(int idAr, int idDest) {
		Session session=sessionFactory.getCurrentSession();
		String rezultat=null;
		
		rezultat = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String rez="";
				CallableStatement cstmt=null;
				try {
					String sqlString="{call brisiDestinacijuIzSpiska (?,?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setInt(1,idAr);
					cstmt.setInt(2,idDest);
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
	public List<Aranzman> showArrangementsInDestinationList(int idDest) {
		Session session=sessionFactory.getCurrentSession();
		List<Aranzman> tipovi=null;
		
		tipovi = session.doReturningWork(new ReturningWork<List<Aranzman>>() {

			@Override
			public List<Aranzman> execute(Connection connection) throws SQLException {

				List<Aranzman> tipovi=new ArrayList<Aranzman>();
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from ARANZMAN where ID_ARANZMANA in (select ID_ARANZMANA from SPISAK_DESTINACIJA where ID_DESTINACIJE=?)";
					stmt = connection.prepareCall(sqlString);
					stmt.setInt(1, idDest);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						Aranzman a=new Aranzman(rs.getInt("ID_ARANZMANA"),rs.getString("NAZIV"));
						tipovi.add(a);
					}
				} finally {
					stmt.close();
				}
				return tipovi;
			}

		});

		return tipovi;
	}	
	
}
