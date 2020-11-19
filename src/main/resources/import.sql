insert into cenovnik (datum_vazenja, obrisano) values ('2020-11-30', 0);

insert into poslovna_godina (godina, zakljucana) values (2020, 0);

insert into mesto (drzava, naziv, obrisano, postanski_broj) values ('Srbija', 'Novi Sad', 0, 21000);

insert into pdv (nazivpdv) values ('pdv1');

insert into stopapdv (datum_vazenja, obrisana, procenat, pdv_id) values ('2020-11-18', 0, 1, 1);

insert into preduzece (pib, adresa_preduzeca, email, logo, naziv, obrisano, tekuci_racun, telefon, mesto_id)values ('12345589', 'Janka Cmelika 18', 'mrakita1993@gmail.com', 'putanja do logoa', 'Moja firma', 0, '1234566','063525441', 1);

insert into grupa_robe (naziv_grupe, pdv_id, preduzece_id, obrisano) values ('grupa1', 1, 1, 0);

insert into roba_usluga (jedinica_mere, naziv_robe_usluge, obrisano, grupa_robe_id)values ('kilogram', 'suhomesnato', 0, 1);

insert into stavka_cenovnika (cena, obrisano, cenovnik_id, roba_usluga_id) values ('150', 0, 1, 1);

insert into korisnik (broj_telefona, email, imeiprezime, obrisan, password, preduzece_id)values ('063525441', 'mrakita1993@gmail.com', 'Milica Rakita', 0, 'admin', 1);

insert into faktura (broj_fakture, datum_fakture, datum_valute, iznos_bez_rabata, iznos_za_placanje, osnovica,rabat, status_fakture, ukupan_pdv, korisnik_id, poslovna_godina_id, preduzece_id)values (1, '2020-11-18', '2020-11-18', 500, 500, 500, 0, 'FORMIRANA', 1, 1, 1, 1);

insert into stavka_fakture (iznos_pdva, iznos_stavke, jedinicna_cena, kolicina, obrisana, osnovica_za_pdv,procenat_pdva, rabat, faktura_id, roba_usluga_id)values (20, 250, 150, 2, 0, 2, 1, 0, 1, 1);










