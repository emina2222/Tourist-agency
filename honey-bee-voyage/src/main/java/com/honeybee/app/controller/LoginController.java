package com.honeybee.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.honeybee.app.dao.PutnikDAO;
import com.honeybee.app.entity.Autentifikacija;
import com.honeybee.app.entity.Putnik;

@Controller
public class LoginController {
	
	@Autowired
	private PutnikDAO putnikDAO;

	@GetMapping("/showLoginPage")
	public String showLoginPage() {
		return "login-form";
	}
	
	@GetMapping("/registracija") //treba da posalje model korisnika i da uzme podatke iz input forme za korIme i lozinku
	public String showRegistrationPage(Model model)
	{
		model.addAttribute("korisnik",new Autentifikacija());
		return "registracija";
	}
	
	@GetMapping("/registracijaInfo") //salje model putnika i veze ih za atribute iz inputa u registracija-info.jsp
	public String showRegistrationInfo(Model model) {
		model.addAttribute("putnik",new Putnik());
		return "registracija-info";
	}
	
	@PostMapping("/processRegistration") //uzima prosledjene podatke i izvrsava proceduru registracije
	public String processRegistration(@ModelAttribute("korisnik") Autentifikacija a, Model model, RedirectAttributes ra) {
		String username=a.getKorisnickoIme();
		String lozinka=a.getLozinka();
		String rezultat=putnikDAO.registracija(username, lozinka);
		String putanja="";
		
		if(rezultat.equals("Korisničko ime već postoji.")) {
			putanja="redirect:/";
			ra.addAttribute("poruka", rezultat);
		}
		else if(rezultat.equals("Uspesno")) {
			putanja="registracija-info";
		}
		else {
			putanja="redirect:/";
			ra.addAttribute("poruka", "Korisničko ime već postoji.");
		}

		
		System.out.print(rezultat);
		System.out.println(username);
		model.addAttribute("username",username);
		return putanja;
	}
	
	@PostMapping("/processRegistrationInfo") //uzima podatke iz forme i izvrsava proceduru registracije (drugi deo)
	public String processRegistrationInfo(@RequestParam("idKred") String username, @RequestParam("ime") String ime,
			@RequestParam("prezime") String prezime, @RequestParam("email") String email, @RequestParam("brojTelefona") String telefon,
			@RequestParam("brojLicneKarte") String LK,RedirectAttributes redirectAttributes) {
		
		
		String rezultat=putnikDAO.unosPodatakaZaPutnika(username, ime, prezime, LK, telefon, email);
		
		if(rezultat.equals("Uspesno")) {
			rezultat="Uspešno registrovanje.";
		}
		else if(rezultat.equals("Neuspesno")){
			rezultat="Neuspešno registrovanje.";
		}
		
		redirectAttributes.addFlashAttribute("poruka",rezultat);
		
		System.out.print(rezultat);
		
		//putnikDAO.unosPodataka(username, ime, prezime, brojLicneKarte, brojTelefona, email);
		
		return "redirect:/";
	}
}
