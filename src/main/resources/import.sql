insert into uloga(naziv) values ('ROLE_ADMIN');
insert into uloga(naziv) values ('ROLE_KORISNIK');

insert into korisnik (broj_telefona, email, ime_prezime, password, preduzece_id, uloga_id) values ('063525441', 'mrakita1993@gmail.com', 'Milica Rakita', '$2a$10$ivwIqMANXL1cxSdCpNJhwegFYzve75xGXPg4xirJIY6bMHOXzEOUO', 1, 1);
insert into korisnik (broj_telefona, email, ime_prezime, password, preduzece_id, uloga_id) values ('063555444', 'roman@snimanje.rs', 'Aleksandar Roman', '$2a$10$ivwIqMANXL1cxSdCpNJhwegFYzve75xGXPg4xirJIY6bMHOXzEOUO', 1, 2);
insert into korisnik (broj_telefona, email, ime_prezime, password, preduzece_id, uloga_id) values ('065555444', 'adrijanvujicic97@gmail.com', 'Adrijan Vujicic', '$2a$10$ivwIqMANXL1cxSdCpNJhwegFYzve75xGXPg4xirJIY6bMHOXzEOUO', 1, 2);
insert into korisnik (broj_telefona, email, ime_prezime, password, preduzece_id, uloga_id) values ('062222333', 'krsticm@gmail.com', 'Milos Krstic', '$2a$10$ivwIqMANXL1cxSdCpNJhwegFYzve75xGXPg4xirJIY6bMHOXzEOUO', 1, 2);
insert into korisnik (broj_telefona, email, ime_prezime, password, preduzece_id, uloga_id) values ('065777888', 'nkovacevic97@gmail.com', 'Nemanja Kovacevic', '$2a$10$ivwIqMANXL1cxSdCpNJhwegFYzve75xGXPg4xirJIY6bMHOXzEOUO', 1, 2);

insert into cenovnik (datum_vazenja, preduzece_id, aktivan) values ('2020-11-30', 1, true);
insert into cenovnik (datum_vazenja, preduzece_id, aktivan) values ('2020-11-25', 1, true);
insert into cenovnik (datum_vazenja, preduzece_id, aktivan) values ('2020-11-20', 1, false);

insert into poslovna_godina (godina, zakljucana) values (2020, 0);
insert into poslovna_godina (godina, zakljucana) values (2019, 1);
insert into poslovna_godina (godina, zakljucana) values (2018, 1);

insert into mesto (drzava, naziv, obrisano, postanski_broj) values ('Srbija', 'Backa Palanka', 0, 21400);

insert into pdv (nazivpdv) values ('10% sveze meso');
insert into pdv (nazivpdv) values ('20% preradjevine');

insert into stopapdv (datum_vazenja, procenat, pdv_id) values ('2020-11-18', 10, 1);
insert into stopapdv (datum_vazenja, procenat, pdv_id) values ('2020-11-18', 10, 2);

insert into preduzece (pib, adresa_preduzeca, email, logo, naziv, tekuci_racun, telefon, mesto_id) values ('100749920', 'Kralja Petra I 36', 'mrakita1993@gmail.com', 'putanja do logoa', 'S.Z.T.R. DRAGSTOR V', '170-50005010000-11','063525441', 1);

insert into grupa_robe (naziv_grupe, pdv_id, preduzece_id) values ('Polutrajni i trajni proizvodi', 1, 1);
insert into grupa_robe (naziv_grupe, pdv_id, preduzece_id) values ('Sveze meso', 1, 1);
insert into grupa_robe (naziv_grupe, pdv_id, preduzece_id) values ('Poluproizvodi - rostilj', 1, 1);
insert into grupa_robe (naziv_grupe, pdv_id, preduzece_id) values ('Sirovine', 1, 1);
insert into grupa_robe (naziv_grupe, pdv_id, preduzece_id) values ('Poluproizvodi', 1, 1);
insert into grupa_robe (naziv_grupe, pdv_id, preduzece_id) values ('Svinjetina', 1, 1);
insert into grupa_robe (naziv_grupe, pdv_id, preduzece_id) values ('Pice', 1, 1);

insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Alpska kobasica', 1);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Bekon meso', 2);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Cevapcici mali', 3);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Cvarci', 1);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Cajna kobasica', 1);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Dimljena butkica', 1);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Dimljena Hamburska slanina', 1);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Dimljena kolenica', 1);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Dimljena rebra', 1);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Dimljene kosti', 2);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Dimljeni gronik', 1);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Dimljeni kare', 1);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Dimljeni papci', 1);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Dimljeni pileci batak BK', 3);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Dimljeni vrat', 1);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Domaca mast', 1);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Domaca suva kobasica', 1);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Dzigernjaca', 1);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Ekstra sunka', 1);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Gurmanska pljeskavica', 3);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Juneci biftek', 4);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Junetina BK', 2);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Junetina SK', 2);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Kare marinirani', 2);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Kranjska kobasica', 5);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Kranjska kobasica - sir', 3);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Kulen', 1);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Kulen ljuti', 1);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Kulen u govedjem crevu', 1);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Marinirana pileca krilca', 3);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Marinirana slanina', 3);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Marinirani pileci batak SK', 3);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Marinirani pileci file', 3);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Novosadska kobasica u govedjem crevu', 1);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Novosadska kobasica u vestackom crevu', 1);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Pica sunka', 1);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Pileca krilca', 2);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Pileci raznjic sa slaninom', 3);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Pileci batak SK', 2);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Pileci file BK', 2);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Pljeskavica', 3);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Prasetina sveza', 2);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Punjena pljeskavica', 3);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Rolovani cevap u slanini', 3);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Rostilj kobasica', 3);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Rozbratna', 1);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Stisnjena sunka', 1);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Suva pecenica', 1);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Suvi vrat', 1);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Suvo soljena slanina', 2);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Svargla', 1);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Svez svinjski rep', 2);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Sveza obradjena slanina', 2);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Sveza svinjska mast', 2);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Sveze svinjske butkice', 2);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Sveze svinjske kolenice', 2);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Svezi svinjski papci', 2);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Svinjska cepana polutka', 2);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Svinjska plecka BK II kat', 2);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Svinjska sveza rebra', 2);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Svinjske sveze kosti 30%', 2);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Svinjski but BK I kat', 2);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Svinjski file van kat', 2);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Svinjski kare BK', 2);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Svinjski kare SK', 2);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Svinjski vrat BK', 2);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Svinjski vrat SK II kat', 6);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('litar', 'Vino 1/1 pet', 7);
insert into roba_usluga (jedinica_mere, naziv_robe_usluge, grupa_robe_id)values ('kilogram', 'Vrat marinirani', 3);

insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('400', 1, 1);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('380', 1, 2);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('450', 1, 3);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('800', 1, 4);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('800', 1, 5);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('400', 1, 6);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('510', 1, 7);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('280', 1, 8);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('430', 1, 9);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('140', 1, 10);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('260', 1, 11);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('720', 1, 12);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('90', 1, 13);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('550', 1, 14);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('720', 1, 15);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('100', 1, 16);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('920', 1, 17);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('170', 1, 18);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('440', 1, 19);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('500', 1, 20);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('1700', 1, 21);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('730', 1, 22);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('520', 1, 23);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('550', 1, 24);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('420', 1, 25);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('490', 1, 26);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('980', 1, 27);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('980', 1, 28);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('1050', 1, 29);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('290', 1, 30);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('550', 1, 31);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('350', 1, 32);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('590', 1, 33);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('400', 1, 34);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('400', 1, 35);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('390', 1, 36);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('290', 1, 37);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('700', 1, 38);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('300', 1, 39);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('520', 1, 40);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('450', 1, 41);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('490', 1, 42);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('500', 1, 43);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('650', 1, 44);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('470', 1, 45);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('570', 1, 46);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('540', 1, 47);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('1400', 1, 48);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('1400', 1, 48);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('990', 1, 49);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('270', 1, 50);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('100', 1, 51);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('430', 1, 52);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('100', 1, 53);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('310', 1, 53);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('220', 1, 54);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('60', 1, 55);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('310', 1, 56);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('510', 1, 57);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('380', 1, 58);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('100', 1, 59);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('520', 1, 60);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('690', 1, 61);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('660', 1, 62);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('450', 1, 63);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('660', 1, 64);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('470', 1, 65);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('250', 1, 66);
insert into stavka_cenovnika (cena, cenovnik_id, roba_usluga_id) values ('550', 1, 67);

insert into faktura (broj_fakture, datum_fakture, datum_valute, iznos_bez_rabata, iznos_za_placanje, osnovica, rabat, status_fakture, ukupan_pdv, korisnik_id, poslovna_godina_id, preduzece_id) values (1, '2020-11-29', '2020-11-29', 1560, 1716, 1560, 0, 'FORMIRANA', 156, 1, 1, 1);

insert into stavka_fakture(iznos_pdva, iznos_stavke, jedinicna_cena, kolicina, osnovica_za_pdv, procenat_pdva, rabat, faktura_id, roba_usluga_id, obrisana) values (80, 880, 400, 2, 800, 10, 0, 1, 1, 0);
insert into stavka_fakture(iznos_pdva, iznos_stavke, jedinicna_cena, kolicina, osnovica_za_pdv, procenat_pdva, rabat, faktura_id, roba_usluga_id, obrisana) values (76, 836, 380, 2, 760, 10, 0, 1, 2, 0)
