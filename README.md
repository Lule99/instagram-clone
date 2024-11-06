# Sistemi bazirani na znanju 2021/2022

Predmetni projekat u okviru kursa Sistemi bazirani na znanju - SBNZ SIIT FTN

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

## Kredencijali (za testiranje)
 * username: regularUsername1; lozinka: 123
 * username: regularUsername2; lozinka: 123
 * username: regularUsername3; lozinka: 123
