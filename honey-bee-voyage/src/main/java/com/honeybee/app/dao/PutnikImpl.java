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
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import com.honeybee.app.entity.PrikazRezervacije;
import com.honeybee.app.entity.Putnik;
import com.microsoft.sqlserver.jdbc.SQLServerException;

@Repository
public class PutnikImpl implements PutnikDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	@Transactional
	public Putnik getProfile(String username) {
		Session session=sessionFactory.getCurrentSession();
		
		Putnik p = null;
		p = session.doReturningWork(new ReturningWork<Putnik>() {

			@Override
			public Putnik execute(Connection connection) throws SQLException {

				Putnik putnik = null;
				PreparedStatement cstmt = null;
				try {
					String sqlString = "select * from profil(?)";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setString(1, username); //setuje username na poziciju upitnika (indeksi pocinju od 1)
					
					ResultSet rs = cstmt.executeQuery();
					while (rs.next()) {
						putnik = new Putnik(rs.getInt("ID_PUTNIKA"),rs.getString("IME"), rs.getString("PREZIME"), rs.getString("BROJ_TELEFONA"),
								rs.getString("EMAIL"));
					}
				} finally {
					cstmt.close();
				}
				return putnik;
			}

		});
		 
		
		return p;
	}

	@Override
	@Transactional
	public void registracijaPutnika(String korIme, String lozinka) {
		// dodajKredencijaleKorisniku
		
		Session session = sessionFactory.getCurrentSession();
		String hashPassword=BCrypt.hashpw("lozinka",BCrypt.gensalt());
		//String hashPassword="{bcrypt}$2a$10$Z3atBBlSRf2gRuofjkwHqu7fzzSyj/3/9xvDPPUzpoNx/C/DxVv8.";
		session.doWork(new Work(){
            @Override
            public void execute(Connection conn) throws SQLException {
            	PreparedStatement stmt = null;
                try {
                	String sqlString = "exec dodajKredencijaleKorisniku ?,?,?";
					stmt = conn.prepareCall(sqlString);
					stmt.setString(1, korIme); 
					stmt.setString(2, hashPassword); 
					stmt.setString(3, "ROLE_KLIJENT"); 
					stmt.executeUpdate();
                }
                finally {
                    stmt.close();
                }
            }
        });
		
	}

	@Override
	@Transactional
	public void unosPodataka(String username, String ime, String prezime, String brojLicneKarte, String telefon,
			String email) {
		Session session = sessionFactory.getCurrentSession();
		session.doWork(new Work(){
            @Override
            public void execute(Connection conn) throws SQLException {
            	PreparedStatement stmt = null;
                try {
                	String sqlString = "exec unosPutnika ?,?,?,?,?,?";
					stmt = conn.prepareCall(sqlString);
					stmt.setString(1, username); 
					stmt.setString(2, ime); 
					stmt.setString(3, prezime); 
					stmt.setString(4, brojLicneKarte); 
					stmt.setString(5, telefon); 
					stmt.setString(6, email); 
					stmt.executeUpdate();
                }
                finally {
                    stmt.close();
                }
            }
        });
		
	}
	
	@Override
	@Transactional
	public int getAuthenticationId(String username) {
		Session session=sessionFactory.getCurrentSession();
		Integer id=null;
		id = session.doReturningWork(new ReturningWork<Integer>() {

			@Override
			public Integer execute(Connection connection) throws SQLException {

				Integer id=null;
				PreparedStatement cstmt = null;
				try {
					String sqlString = "select ID_KREDENCIJALA from AUTENTIFIKACIJA where KORISNICKO_IME=?";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setString(1, username); 
					
					ResultSet rs = cstmt.executeQuery();
					while (rs.next()) {
						id=rs.getInt("ID_KREDENCIJALA");
					}
				} finally {
					cstmt.close();
				}
				return id;
			}

		});
		 
		return id;
	}

	@Override
	@Transactional
	public List<PrikazRezervacije> getReservationList(String username) {
		Session session=sessionFactory.getCurrentSession();
		List<PrikazRezervacije> lista=null;
		lista = session.doReturningWork(new ReturningWork<List<PrikazRezervacije>>() {

			@Override
			public List<PrikazRezervacije> execute(Connection connection) throws SQLException {

				List<PrikazRezervacije> lista=new ArrayList<PrikazRezervacije>();
				PreparedStatement cstmt = null;
				try {
					String sqlString = "select * from prikaziRezervisanoPutnika(?)";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setString(1, username); 
					
					ResultSet rs = cstmt.executeQuery();
					while (rs.next()) {
						PrikazRezervacije rez=new PrikazRezervacije(rs.getInt("id_aranzmana"),rs.getInt("broj_putnika"),
								rs.getDouble("cena"),rs.getInt("id_smestaja"), rs.getInt("id_ponude"),rs.getInt("id_rezervacije")
								,rs.getInt("id_putnika"),rs.getInt("broj_dece"));
						lista.add(rez);
					}
				} finally {
					cstmt.close();
				}
				return lista;
			}

		});
		 
		return lista;
	}

	@Override
	@Transactional
	public List<PrikazRezervacije> getAllReservations() {
		Session session=sessionFactory.getCurrentSession();
		List<PrikazRezervacije> lista=null;
		lista = session.doReturningWork(new ReturningWork<List<PrikazRezervacije>>() {

			@Override
			public List<PrikazRezervacije> execute(Connection connection) throws SQLException {

				List<PrikazRezervacije> lista=new ArrayList<PrikazRezervacije>();
				PreparedStatement cstmt = null;
				try {
					String sqlString = "select * from prikaziSveRezervisano()";
					cstmt = connection.prepareCall(sqlString);
					
					ResultSet rs = cstmt.executeQuery();
					while (rs.next()) {
						PrikazRezervacije rez=new PrikazRezervacije(rs.getInt("id_aranzmana"),rs.getInt("broj_putnika"),
								rs.getDouble("cena"),rs.getInt("id_smestaja"), rs.getInt("id_ponude"),rs.getInt("id_rezervacije")
								,rs.getInt("id_putnika"),rs.getInt("broj_dece"));
						lista.add(rez);
					}
				} finally {
					cstmt.close();
				}
				return lista;
			}

		});
		 
		return lista;
	}

	@Override
	@Transactional
	public Putnik getPutnik(int idPutnika) {
		Session session=sessionFactory.getCurrentSession();
		
		Putnik p = null;
		p = session.doReturningWork(new ReturningWork<Putnik>() {

			@Override
			public Putnik execute(Connection connection) throws SQLException {

				Putnik putnik = null;
				PreparedStatement cstmt = null;
				try {
					String sqlString = "select ime,prezime from putnik where id_putnika=?";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setInt(1, idPutnika); //setuje username na poziciju upitnika (indeksi pocinju od 1)
					
					ResultSet rs = cstmt.executeQuery();
					while (rs.next()) {
						putnik = new Putnik(rs.getString("IME"), rs.getString("PREZIME"));
					}
				} finally {
					cstmt.close();
				}
				return putnik;
			}

		});
		 
		
		return p;
	}

	@Override
	@Transactional
	public String registracija(String korIme, String lozinka) {
		Session session=sessionFactory.getCurrentSession();
		String rezultat=null;
		
		rezultat = session.doReturningWork(new ReturningWork<String>() {
			@Override
			public String execute(Connection connection) throws SQLException {

				String hashPassword="{bcrypt}"+BCrypt.hashpw(lozinka,BCrypt.gensalt());
				 hashPassword+=BCrypt.hashpw(lozinka,BCrypt.gensalt());
				String rez="";
				CallableStatement cstmt=null;
				try {
					String sqlString="{call dodajKredencijale (?,?,?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setString(1, korIme);
					cstmt.setString(2, hashPassword);
					cstmt.setString(3, "ROLE_KLIJENT");
					cstmt.registerOutParameter(4, java.sql.Types.NVARCHAR);

					cstmt.executeUpdate();
					rez=cstmt.getString(4);
				} 
				catch(SQLServerException ex) {
					//ex.printStackTrace();
					rez="Korisničko ime već postoji.";
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
	public String unosPodatakaZaPutnika(String username, String ime, String prezime, String brojLicneKarte,
			String telefon, String email) {
		Session session=sessionFactory.getCurrentSession();
		String rezultat=null;
		
		rezultat = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String rez="";
				CallableStatement cstmt=null;
				try {
					String sqlString="{call unosPodatakaPutnika (?,?,?,?,?,?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setString(1, username);
					cstmt.setString(2, ime);
					cstmt.setString(3, prezime);
					cstmt.setString(4, brojLicneKarte);
					cstmt.setString(5, telefon);
					cstmt.setString(6, email);
					cstmt.registerOutParameter(7, java.sql.Types.NVARCHAR);

					cstmt.executeUpdate();
					rez=cstmt.getString(7);
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
