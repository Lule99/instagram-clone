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
Na kraju je potrebno i run-ovati aplikaciju.

## Kredencijali (za testiranje)
 * username: regularUsername1; lozinka: 123
 * username: regularUsername2; lozinka: 123
 * username: regularUsername3; lozinka: 123
