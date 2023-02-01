# Sistemi bazirani na znanju 2021/2022

## Autori:
  * Njegos Blagojević SW-18-2018
  * Luka Kureljušić SW-23-2018

## Opis projekta

Projekat predstavlja kombinaciju društvene mreže i sistema baziranih na znanju. Sistem omogućava klasične funkcionalnosti kao i svaka društvena mreža ali je deo vezan za preporuke prijatelja i preporuke objava koje bi nam se mogle svideti implementiran preko skupa pravila.

## Tehnologije

Repozitorijum se sastoji od dva projekta. 
Prvi, serverska strana je odrađena korišćenjem Spring Boot-a dok je _"rule based"_ deo sistema implementiran korišćenjem Drools-a. 
Alat za build projekta koji je korišćen je maven. 
Klijentski deo aplikacije je rađen uz pomoć React-a a sam repozitorijum se nalazi na [sledećem linku](https://github.com/blagojevic-nj/insta-clone-front).

## Pokretanje

Najpre je potrebno klonirati repozitorijum komandom ```git clone https://github.com/Lule99/instagram-clone ```.
Zatim je potrebno prvo nad drools-rules projektom izvršiti ```maven clean install```.
Drugi korak jeste izvršavanje ```maven clean package``` nad projektom insta-clone.

Potrebno je imati instaliranu i pokrenutu postgres bazu i u njoj kreirati bazu insta-clone. Specifikaciju db_user + password kao i port baze potrebno je uneti u application.properties linije 3-6.

Na kraju je potrebno i run-ovati aplikaciju.

## Kredencijali (za testiranje)
 * username: regularUsername1; lozinka: 123
 * username: regularUsername2; lozinka: 123
 * username: regularUsername3; lozinka: 123
 
## Dodatna uputstva za panonit testere:
Za najlakse konfigurisanje baze predlazem upotrebu dockera ali kroz gui varijantu (radi lakseg rada) - docker desktop.

### Koraci:
 * Preuzimanje i instalacija docker desktop
 * Instalirani docker desktop > Containers tab > kliknuti get postgres (ili nesto slicno ali bice ponudjeno dugme)
 * Extensions tab > AddExtension > Databases > Open Source management tool for PostgreSQL (inbuilt gui za laksi pregled baze)
 * Baza ce vrv trcati na portu 55000 sa kredencijalima usr/pwd : postgres/postgrespw
 * Prethodno se moze proveriti u Containers > Actions > Open In Terminal > View and edit data using pgAdmin
 * BTW na tom tabu se postaraj da je container running (play dugme)
 * U pg adminu (ona extenzija od malopre) staviti neki random master password > desni klik na server > Register > Server > Name stavi sta god > ConnectionTab > unati parametre sa prethodnog terminala (Host, port, username password)
 * Kreirati bazu insta-clone
 *U aplikaciji u resources insta-clone aplikacije u application.properties fajl uneti odgovarajuce username+password i port
 
